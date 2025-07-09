package me.nikd0.skope.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Nullable;

@Name("Open a custom GUI")
@Description({
        "Open a GUI which uses a custom texture determined by an NBT value (when using Optifine CIT resource pack).",
        "WARNING: Due to Minecraft limitations, number of rows must be between 1 and 6. If this requirement is not met, default (3 row inventory) is opened."
})
@Examples("open a custom gui with id \"potion_menu\" with 4 rows named \"Potion Menu\" to player")
public class EffOpenCustomGUI extends Effect {

    static {
        Skript.registerEffect(EffOpenCustomGUI.class,
                "[skope] open [a] [new] (custom|miwose) (GUI|gui) (with|of) id %string% with %integer% row[s] (with name|named) %string% to [player] %player%"
        );
    }

    @SuppressWarnings("null")
    private Expression<String> id;

    @SuppressWarnings("null")
    private Expression<String> name;

    @SuppressWarnings("null")
    private Expression<Integer> rows;

    @SuppressWarnings("null")
    private Expression<Player> player;

    @SuppressWarnings({"unchecked", "null"})
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.id = (Expression<String>) expressions[0];
        this.rows = (Expression<Integer>) expressions[1];
        this.name = (Expression<String>) expressions[2];
        this.player = (Expression<Player>) expressions[3];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return String.format("Open a new custom GUI with id '%s', %s rows, named '%s' to player %s.",
                this.id.toString(event, debug),
                this.rows.toString(event, debug),
                this.name.toString(event, debug),
                this.player.toString(event, debug)
        );
    }

    @Override
    protected void execute(Event event) {
        String id = this.id.getSingle(event);
        String name = this.name.getSingle(event);
        Integer rows = this.rows.getSingle(event);
        Player player = this.player.getSingle(event);

        ItemStack customTextureItem = new ItemStack(Material.DIAMOND_HOE);
        ItemMeta itemMeta = customTextureItem.getItemMeta();
        itemMeta.setDisplayName("§r");
        customTextureItem.setItemMeta(itemMeta);
        NBTItem item = new NBTItem(customTextureItem);
        item.setString("customgui", id);
        item.setInteger("HideFlags", 63);
        item.applyNBT(customTextureItem);

        final int inventorySize = (rows >= 1 && rows <= 6) ? rows * 9 : 27;
        Inventory inventory = Bukkit.createInventory(null, inventorySize, name);
        inventory.setItem(inventorySize - 1, customTextureItem);
        player.openInventory(inventory);
    }
}
