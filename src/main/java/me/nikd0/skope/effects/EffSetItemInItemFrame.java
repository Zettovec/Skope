package me.nikd0.skope.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class EffSetItemInItemFrame extends Effect {

    static {
        Skript.registerEffect(EffSetItemInItemFrame.class,
                "[skope] set [the] shown item on [item frame] %entity% to %itemstack%"
        );
    }

    @SuppressWarnings("null")
    private Expression<Entity> itemFrame;

    @SuppressWarnings("null")
    private Expression<ItemStack> newItem;

    @SuppressWarnings({"unchecked", "null"})
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.itemFrame = (Expression<Entity>) expressions[0];
        this.newItem = (Expression<ItemStack>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return String.format("Set the shown item on item frame '%s' to '%s'",
                this.newItem.toString(event, debug),
                this.itemFrame.toString(event, debug)
        );
    }

    @Override
    protected void execute(Event event) {
        if (!(this.itemFrame.getSingle(event) instanceof ItemFrame)) return;

        ((ItemFrame) this.itemFrame.getSingle(event)).setItem(newItem.getSingle(event));
    }
}
