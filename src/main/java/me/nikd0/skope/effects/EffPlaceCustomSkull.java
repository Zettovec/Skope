package me.nikd0.skope.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.nikd0.skope.infrastructure.CustomSkull;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.block.data.Rotatable;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffPlaceCustomSkull extends Effect {

    static {
        Skript.registerEffect(EffPlaceCustomSkull.class,
                "[skope] place [a] custom (player[ ]head|skull) %customskull% at [location] %location% [(facing|rotated) %-string%]"
        );
    }

    @SuppressWarnings("null")
    private Expression<CustomSkull> skull;

    @SuppressWarnings("null")
    private Expression<Location> location;

    @SuppressWarnings("null")
    private Expression<String> rotationString;

    @SuppressWarnings({"unchecked", "null"})
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.location = (Expression<Location>) expressions[1];
        this.skull = (Expression<CustomSkull>) expressions[0];

        this.rotationString = (Expression<String>) expressions[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return String.format("Place custom skull with texture %s at location %s",
                this.skull.toString(event, debug),
                this.location.toString(event, debug)
        );
    }

    @Override
    protected void execute(Event event) {
        final Location loc = this.location.getSingle(event);
        loc.getBlock().setType(org.bukkit.Material.PLAYER_HEAD);
        Skull skullBlock = (Skull) loc.getBlock().getState();

        CustomSkull customSkull = this.skull.getSingle(event);
        customSkull.apply(skullBlock);

        if (this.rotationString != null) {
            try {
                Rotatable blockData = (Rotatable) loc.getBlock().getBlockData();
                blockData.setRotation(BlockFace.valueOf(this.rotationString.getSingle(event).toUpperCase()));
                loc.getBlock().setBlockData(blockData);
            } catch (IllegalArgumentException e) {
                Skript.warning("Invalid rotation for skull: '" + this.rotationString.getSingle(event) + "' For applicable values see: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/block/BlockFace.html");
            }
        }
    }
}
