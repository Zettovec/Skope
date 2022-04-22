package me.nikd0.skope.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.nikd0.skope.infrastructure.books.Book;
import me.nikd0.skope.infrastructure.books.Page;
import me.nikd0.skope.infrastructure.books.TextComponent;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprPage extends SimpleExpression<Page> {

    static {
        Skript.registerExpression(ExprPage.class,
                Page.class,
                ExpressionType.SIMPLE,
                "[a] [new] [skope] custom [book] page [with text %-string%]"
        );
    }

    @SuppressWarnings("null")
    private Expression<String> text;

    @Override
    public Class<? extends Page> getReturnType() {
        return Page.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings({"unchecked", "null"})
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.text = (Expression<String>) expressions[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "a new custom book page with text";
    }

    @Override
    protected Page[] get(Event event) {
        if (this.text == null) return new Page[]{new Page()};

        return new Page[] {
            new Page()
                .setText(text.getSingle(event))
        };
    }

}
