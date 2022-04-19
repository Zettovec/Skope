package me.nikd0.skope.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.nikd0.skope.infrastructure.books.Page;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffPageAddText extends Effect {

    static {
        Skript.registerEffect(EffPageAddText.class,
                "[skope] add [plain] text %string% to [(a|the)] custom [book] page %object%"
        );
    }

    @SuppressWarnings("null")
    private Expression<String> text;

    @SuppressWarnings("null")
    private Expression<Page> page;

    @SuppressWarnings({"unchecked", "null"})
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.text = (Expression<String>) expressions[0];
        this.page = (Expression<Page>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return String.format("Add a plain text (%s) to a custom page (%s).",
                this.text.toString(event,debug),
                this.page.toString(event, debug)
        );
    }

    @Override
    protected void execute(Event event) {
        this.page.getSingle(event).addText(text.getSingle(event));
    }
}
