package me.nikd0.skope.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.nikd0.skope.infrastructure.CustomSkull;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.security.SecureRandom;

public class ExprCustomSkull extends SimpleExpression<CustomSkull> {

    static {
        Skript.registerExpression(ExprCustomSkull.class,
                CustomSkull.class,
                ExpressionType.SIMPLE,
                "[a] [new] custom (player[ ]head|skull) (by|with) [texture] url %string%",
                "[a] [new] custom (player[ ]head|skull) (by|with) owner %offlineplayer%"
        );
    }

    @SuppressWarnings("null")
    private Expression<String> textureURL;

    @SuppressWarnings("null")
    private Expression<OfflinePlayer> owner;

    private CustomSkull.SkullType skullType;

    @Override
    public Class<? extends CustomSkull> getReturnType() {
        return CustomSkull.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings({"unchecked", "null"})
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        skullType = (matchedPattern == 0) ? CustomSkull.SkullType.BY_URL : CustomSkull.SkullType.BY_OWNER;

        if (skullType == CustomSkull.SkullType.BY_URL) this.textureURL = (Expression<String>) expressions[0];
        else this.owner = (Expression<OfflinePlayer>) expressions[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "custom skull (by texture URL or owner)";
    }

    @Override
    protected CustomSkull[] get(Event event) {
        return new CustomSkull[] {
            (skullType == CustomSkull.SkullType.BY_URL) ?
                new CustomSkull(this.textureURL.getSingle(event)) :
                new CustomSkull(this.owner.getSingle(event))
        };
    }

}
