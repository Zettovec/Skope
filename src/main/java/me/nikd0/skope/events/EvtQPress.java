package me.nikd0.skope.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.nikd0.skope.infrastructure.QPressEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

// Player Q button press event – Not cancellable
public class EvtQPress extends SkriptEvent {

    static {
        Skript.registerEvent(
                "Q Button Press",
                EvtQPress.class,
                QPressEvent.class,
                "q (button|key) press"
        );

        EventValues.registerEventValue(QPressEvent.class, Player.class, new Getter<Player, QPressEvent>() {
            @Override
            public Player get(QPressEvent event) {
                return event.getPlayer();
            }
        }, 0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Literal<?>[] args, int matchedPattern, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(Event e) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "Player Q button press event";
    }

}
