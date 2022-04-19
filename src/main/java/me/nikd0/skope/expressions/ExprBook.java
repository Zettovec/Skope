package me.nikd0.skope.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.nikd0.skope.infrastructure.books.Book;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprBook extends SimpleExpression<Book> {

    static {
        Skript.registerExpression(ExprBook.class,
                Book.class,
                ExpressionType.SIMPLE,
                "[a] [new] [skope] custom book [(with name|named|with title|titled) %-string%] [(with author|authored by) %-string%] [(with|of) generation %-integer%]"
        );
    }

    @SuppressWarnings("null")
    private Expression<String> title;

    @SuppressWarnings("null")
    private Expression<String> author;

    @SuppressWarnings("null")
    private Expression<Integer> generation;

    @Override
    public Class<? extends Book> getReturnType() {
        return Book.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings({"unchecked", "null"})
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.title = (Expression<String>) expressions[0];
        this.author = (Expression<String>) expressions[1];
        this.generation = (Expression<Integer>) expressions[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "a new custom book with a title, author and generation.";
    }

    @Override
    protected Book[] get(Event event) {
        Book book = new Book();
        if (this.author != null) book.setAuthor(this.author.getSingle(event));
        if (this.title != null) book.setTitle(this.title.getSingle(event));
        if (this.generation != null) book.setGeneration(this.generation.getSingle(event));

        return new Book[] { book };
    }

}
