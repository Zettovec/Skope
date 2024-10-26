package me.nikd0.skope.luckperms;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.nikd0.skope.infrastructure.utils.LuckPermsUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffRemovePerm extends Effect {

    static {
        Skript.registerEffect(EffRemovePerm.class,
                "[skope] deny [player] %offlineplayer% (luckperms|lp) permission %string%"
        );
    }

    @SuppressWarnings("null")
    private Expression<String> permission;

    @SuppressWarnings("null")
    private Expression<OfflinePlayer> player;

    @SuppressWarnings({"unchecked", "null"})
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.player = (Expression<OfflinePlayer>) expressions[0];
        this.permission = (Expression<String>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return String.format("Deny player %s permission %s",
                this.player.toString(event, debug),
                this.permission.toString(event, debug)
        );
    }

    @Override
    protected void execute(Event event) {
        LuckPermsUtils.removePermission(player.getSingle(event), permission.getSingle(event));
    }
}
