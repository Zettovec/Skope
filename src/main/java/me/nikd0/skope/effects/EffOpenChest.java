package me.nikd0.skope.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.Nullable;

public class EffOpenChest extends Effect {

    static {
        Skript.registerEffect(EffOpenChest.class,
                "[skope] (show|open) virtual chest [inventory] with %integer% row[s] named %string% (to|for) [player] %player%"
        );
    }

    @SuppressWarnings("null")
    private Expression<String> name;

    @SuppressWarnings("null")
    private Expression<Player> player;

    private Expression<Integer> rows;

    @SuppressWarnings({"unchecked", "null"})
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.name = (Expression<String>) expressions[1];
        this.rows = (Expression<Integer>) expressions[0];
        this.player = (Expression<Player>) expressions[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return String.format("Open chest named %s to player %s",
                this.player.toString(event, debug),
                this.name.toString(event, debug)
        );
    }

    @Override
    protected void execute(Event event) {
        Player p = player.getSingle(event);
        Inventory inv = Bukkit.createInventory(p, rows.getSingle(event) * 9, name.getSingle(event));
        p.openInventory(inv);
    }
}
