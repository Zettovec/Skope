package me.nikd0.skope.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

@Name("Item in item frame")
@Description("Get the item shown on an item frame")
@Examples("set {_item} to the shown item on item frame {_itemFrame}")
public class ExprItemInItemFrame extends SimpleExpression<ItemStack> {

    static {
        Skript.registerExpression(ExprItemInItemFrame.class,
                ItemStack.class,
                ExpressionType.SIMPLE,
                "[the] [skope] shown item on [item frame] %entity%"
        );
    }

    @SuppressWarnings("null")
    private Expression<Entity> itemFrame;

    @Override
    public Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings({"unchecked", "null"})
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.itemFrame = (Expression<Entity>) expressions[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Shown item on item frame: " + this.itemFrame.toString(event, debug);
    }

    @Override
    protected ItemStack[] get(Event event) {
        if (!(this.itemFrame.getSingle(event) instanceof ItemFrame)) return null;

        return new ItemStack[]{((ItemFrame) this.itemFrame.getSingle(event)).getItem()};
    }

}
