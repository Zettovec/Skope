package me.nikd0.skope.infrastructure;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

// Player Q button press event – Not cancellable (just detects the press)
public class QPressEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    final private Player player;

    public QPressEvent(Player player) {
        this.player = player;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static @NotNull HandlerList getHandlerList(){
        return handlers;
    }

    public void call() {
        Bukkit.getPluginManager().callEvent(this);
    }

    public Player getPlayer() {
        return player;
    }

}
