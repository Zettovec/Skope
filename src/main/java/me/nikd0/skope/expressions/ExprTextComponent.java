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
import me.nikd0.skope.infrastructure.books.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Custom text component for a book")
@Description("A custom text component (with click/hover actions which you may add to custom pages.")
@Examples("set {_tc} to a new text component \"Hello!\"")
public class ExprTextComponent extends SimpleExpression<TextComponent> {

    static {
        Skript.registerExpression(ExprTextComponent.class,
                TextComponent.class,
                ExpressionType.SIMPLE,
                "[a] [new] [skope] text component [with text] %string%",
                "[a] [new] [skope] text component [with text] %string% to run [(a|the)] command %string% on click",
                "[a] [new] [skope] text component [with text] %string% to suggest [(a|the)] command %string% on click",
                "[a] [new] [skope] text component [with text] %string% to open (url|link) %string% on click",
                "[a] [new] [skope] text component [with text] %string% to change page to %integer% on click",
                "[a] [new] [skope] text component [with text] %string% to show text %string% on hover",
                "[a] [new] [skope] text component [with text] %string% to show item %itemstack% on hover"
        );
    }

    @SuppressWarnings("null")
    private Expression<String> text;

    @SuppressWarnings("null")
    private @Nullable ClickEvent.Action clickAction;

    @SuppressWarnings("null")
    private @Nullable HoverEvent.Action hoverAction;

    @SuppressWarnings("null")
    private @Nullable Expression<Object> parameter;

    @Override
    public Class<? extends TextComponent> getReturnType() {
        return TextComponent.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings({"unchecked", "null"})
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.text = (Expression<String>) expressions[0];
        if (matchedPattern == 0) return true;

        this.parameter = (Expression<Object>) expressions[1];

        if (matchedPattern == 1) this.clickAction = ClickEvent.Action.RUN_COMMAND;
        else if (matchedPattern == 2) this.clickAction = ClickEvent.Action.SUGGEST_COMMAND;
        else if (matchedPattern == 3) this.clickAction = ClickEvent.Action.OPEN_URL;
        else if (matchedPattern == 4) this.clickAction = ClickEvent.Action.CHANGE_PAGE;
        else if (matchedPattern == 5) this.hoverAction = HoverEvent.Action.SHOW_TEXT;
        else if (matchedPattern == 6) this.hoverAction = HoverEvent.Action.SHOW_ITEM;
        else return false;

        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "a new custom text component (with a click/hover action)";
    }

    @Override
    protected TextComponent[] get(Event event) {
        TextComponent textComponent = new TextComponent(this.text.getSingle(event));

        if (clickAction != null) textComponent.setClickAction(clickAction, parameter.getSingle(event));
        if (hoverAction != null) textComponent.setHoverAction(hoverAction, parameter.getSingle(event));

        return new TextComponent[]{ textComponent };
    }

}
