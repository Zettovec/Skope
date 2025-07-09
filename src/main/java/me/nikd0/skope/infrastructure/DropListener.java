package me.nikd0.skope.infrastructure;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropListener implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        event.setCancelled(true);
        new QPressEvent(event.getPlayer()).call();
    }

}
