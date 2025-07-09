package me.nikd0.skope.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.nikd0.skope.infrastructure.books.Page;
import me.nikd0.skope.infrastructure.books.TextComponent;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffPageAddTextComponent extends Effect {

    static {
        Skript.registerEffect(EffPageAddTextComponent.class,
                "[skope] add [(a|the)] text component %textcomponent% to [(a|the)] custom [book] page %custompage%"
        );
    }

    @SuppressWarnings("null")
    private Expression<TextComponent> textComponent;

    @SuppressWarnings("null")
    private Expression<Page> page;

    @SuppressWarnings({"unchecked", "null"})
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.textComponent = (Expression<TextComponent>) expressions[0];
        this.page = (Expression<Page>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return String.format("Add a text component (%s) to a custom page (%s).",
                this.textComponent.toString(event,debug),
                this.page.toString(event, debug)
        );
    }

    @Override
    protected void execute(Event event) {
        this.page.getSingle(event).addText(textComponent.getSingle(event));
    }
}
