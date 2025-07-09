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
import me.nikd0.skope.infrastructure.books.Book;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.security.SecureRandom;

public class ExprRandomString extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprRandomString.class,
                String.class,
                ExpressionType.SIMPLE,
                "[a] [new] random string [(with|of) length %-integer%]"
        );
    }

    @SuppressWarnings("null")
    private Expression<Integer> length;

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings({"unchecked", "null"})
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.length = (Expression<Integer>) expressions[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "a random string (10 symbols / of specified length)";
    }

    @Override
    protected String[] get(Event event) {
        if (this.length == null) return new String[]{randomString(10)};
        return new String[]{randomString(this.length.getSingle(event))};
    }

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.!?@#$%^&*()_+-=<>:;{}[]|~";
    static SecureRandom rnd = new SecureRandom();
    String randomString(int len){
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

}
