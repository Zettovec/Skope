package me.nikd0.skope.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.nikd0.skope.infrastructure.books.Book;
import me.nikd0.skope.infrastructure.books.Page;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffBookAddPage extends Effect {

    static {
        Skript.registerEffect(EffBookAddPage.class,
                "[skope] add [(a|the)] custom [book] page %custompage% to [(a|the)] custom book %custombook%"
        );
    }

    @SuppressWarnings("null")
    private Expression<Page> page;

    @SuppressWarnings("null")
    private Expression<Book> book;

    @SuppressWarnings({"unchecked", "null"})
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.page = (Expression<Page>) expressions[0];
        this.book = (Expression<Book>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return String.format("Add a custom page (%s) to a custom book (%s).",
                this.page.toString(event, debug),
                this.book.toString(event, debug)
        );
    }

    @Override
    protected void execute(Event event) {
        this.book.getSingle(event).addPage(page.getSingle(event));
    }
}
