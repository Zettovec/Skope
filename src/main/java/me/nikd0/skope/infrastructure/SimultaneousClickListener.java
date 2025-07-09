package me.nikd0.skope.infrastructure;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SimultaneousClickListener implements Listener {
    private Map<UUID, Long> lastRightClick = new HashMap<>();
    private Map<UUID, Long> lastLeftClick = new HashMap<>();
    private static final long SIMULTANEOUS_THRESHOLD = 50; // milliseconds

    @EventHandler
    public void onSimultaneousClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();
        long currentTime = System.currentTimeMillis();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            lastRightClick.put(playerId, currentTime);
        }

        if (event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR) {
            lastLeftClick.put(playerId, currentTime);
        }

        // Check if clicks were within the threshold
        Long lastRight = lastRightClick.get(playerId);
        Long lastLeft = lastLeftClick.get(playerId);

        if (lastRight != null && lastLeft != null && Math.abs(lastRight - lastLeft) <= SIMULTANEOUS_THRESHOLD) {
            event.setCancelled(true); //block all simultaneous clicks
        }
    }

}
