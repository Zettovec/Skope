package me.nikd0.skope.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.nikd0.skope.infrastructure.books.Page;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffPageNewLine extends Effect {

    static {
        Skript.registerEffect(EffPageNewLine.class,
                "[skope] add [a] new line to [(a|the)] custom [book] page %object%",
                "[skope] add %integer% [of] new lines to [(a|the)] custom [book] page %object%"
        );
    }

    @SuppressWarnings("null")
    private Expression<Page> page;

    @SuppressWarnings("null")
    private Expression<Integer> amount;

    @SuppressWarnings({"unchecked", "null"})
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        if (matchedPattern == 0){
            this.page = (Expression<Page>) expressions[0];
        } else {
            this.amount = (Expression<Integer>) expressions[0];
            this.page = (Expression<Page>) expressions[1];
        }
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        if (this.amount != null){
            return String.format("Add %s new lines to a custom page (%s).",
                    this.amount.toString(event, debug),
                    this.page.toString(event, debug)
            );
        }
        return String.format("Add a new line to a custom page (%s).",
                this.page.toString(event, debug)
        );
    }

    @Override
    protected void execute(Event event) {
        if (this.amount != null){
            for (int i = 0; i < this.amount.getSingle(event); i++){
                this.page.getSingle(event).newLine();
            }
            return;
        }

        this.page.getSingle(event).newLine();
    }
}
