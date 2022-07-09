package me.nikd0.skope.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.Nullable;

public class EffOpenDropper extends Effect {

    static {
        Skript.registerEffect(EffOpenDropper.class,
                "[skope] open [a] dropper named %string% to [player] %player%",
                "[skope] (show|open) virtual dropper [inventory] named %string% (to|for) [player] %player%"
        );
    }

    @SuppressWarnings("null")
    private Expression<String> name;

    @SuppressWarnings("null")
    private Expression<Player> player;

    @SuppressWarnings({"unchecked", "null"})
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.name = (Expression<String>) expressions[0];
        this.player = (Expression<Player>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return String.format("Open dispenser named %s to player %s",
                this.player.toString(event, debug),
                this.name.toString(event, debug)
        );
    }

    @Override
    protected void execute(Event event) {
        Player p = player.getSingle(event);
        String title = name.getSingle(event);
        Inventory inv = Bukkit.createInventory(p, InventoryType.DROPPER, title);
        p.openInventory(inv);
    }
}
