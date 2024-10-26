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

public class EffGrantPerm extends Effect {

    static {
        Skript.registerEffect(EffGrantPerm.class,
                "[skope] grant [player] %offlineplayer% (luckperms|lp) permission %string% [(with state|to be) %-boolean%]"
        );
    }

    @SuppressWarnings("null")
    private Expression<String> permission;

    @SuppressWarnings("null")
    private Expression<OfflinePlayer> player;

    private Expression<Boolean> state;

    @SuppressWarnings({"unchecked", "null"})
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.player = (Expression<OfflinePlayer>) expressions[0];
        this.permission = (Expression<String>) expressions[1];
        this.state = (Expression<Boolean>) expressions[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return String.format("Grant player %s permission %s",
                this.player.toString(event, debug),
                this.permission.toString(event, debug)
        );
    }

    @Override
    protected void execute(Event event) {
        if (this.state != null) LuckPermsUtils.givePermission(player.getSingle(event), permission.getSingle(event), state.getSingle(event));
        else LuckPermsUtils.givePermission(player.getSingle(event), permission.getSingle(event), true);
    }
}
