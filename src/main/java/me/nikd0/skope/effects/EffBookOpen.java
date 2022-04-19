package me.nikd0.skope.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.nikd0.skope.infrastructure.books.Book;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffBookOpen extends Effect {

    static {
        Skript.registerEffect(EffBookOpen.class,
                "[skope] open [(a|the)] custom book %object% to [player] %player%"
        );
    }

    @SuppressWarnings("null")
    private Expression<Book> book;

    @SuppressWarnings("null")
    private Expression<Player> player;

    @SuppressWarnings({"unchecked", "null"})
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.book = (Expression<Book>) expressions[0];
        this.player = (Expression<Player>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return String.format("Open player %s a custom book: %s",
                this.player.toString(event, debug),
                this.book.toString(event, debug));
    }

    @Override
    protected void execute(Event event) {
        this.book.getSingle(event).open(player.getSingle(event));
    }
}
