package me.nikd0.skope.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.jetbrains.annotations.Nullable;

public class EvtHotbarSwitch extends SkriptEvent {

    static {
        Skript.registerEvent("Hotbar Switch", EvtHotbarSwitch.class, PlayerItemHeldEvent.class, "[player] (hotbar|item held) (switch|change)");
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
        return "Player hotbar switch event.";
    }

    //i may add next slot / previous slot in the future

}
