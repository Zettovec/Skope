package me.nikd0.skope.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.nikd0.skope.infrastructure.books.Page;
import me.nikd0.skope.infrastructure.books.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffTextComponentAction extends Effect {

    static {
        Skript.registerEffect(EffTextComponentAction.class,
                "[skope] remove click action from [(a|the)] text component %object%",
                "[skope] remove hover action from [(a|the)] text component %object%",
                "set click action of text component %object% to run [(a|the)] command %string%",
                "set click action of text component %object% to suggest [(a|the)] command %string%",
                "set click action of text component %object% to open (url|link) %string%",
                "set click action of text component %object% to change page to %integer%",
                "set hover action of text component %object% to show text %string%",
                "set hover action of text component %object% to show item %itemstack%"
        );
    }

    @SuppressWarnings("null")
    private Expression<TextComponent> textComponent;

    @SuppressWarnings("null")
    private @Nullable Integer remove; //0 - click, 1 - hover

    @SuppressWarnings("null")
    private @Nullable ClickEvent.Action clickAction;

    @SuppressWarnings("null")
    private @Nullable HoverEvent.Action hoverAction;

    @SuppressWarnings("null")
    private @Nullable Expression<Object> parameter;

    @SuppressWarnings({"unchecked", "null"})
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.textComponent = (Expression<TextComponent>) expressions[0];
        if (matchedPattern <= 1){
            this.remove = matchedPattern;
            return true;
        }

        this.parameter = (Expression<Object>) expressions[1];

        if (matchedPattern == 2) this.clickAction = ClickEvent.Action.RUN_COMMAND;
        else if (matchedPattern == 3) this.clickAction = ClickEvent.Action.SUGGEST_COMMAND;
        else if (matchedPattern == 4) this.clickAction = ClickEvent.Action.OPEN_URL;
        else if (matchedPattern == 5) this.clickAction = ClickEvent.Action.CHANGE_PAGE;
        else if (matchedPattern == 6) this.hoverAction = HoverEvent.Action.SHOW_TEXT;
        else if (matchedPattern == 7) this.hoverAction = HoverEvent.Action.SHOW_ITEM;
        else return false;

        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return String.format("Edit a hover/click action of a text component (%s).",
                this.textComponent.toString(event,debug)
        );
    }

    @Override
    protected void execute(Event event) {
        TextComponent tc = this.textComponent.getSingle(event);

        if (this.remove != null){
            if (this.remove == 0) tc.setClickAction(null);
            if (this.remove == 1) tc.setHoverAction(null);
            return;
        }

        if (clickAction != null) tc.setClickAction(clickAction, parameter.getSingle(event));
        if (hoverAction != null) tc.setHoverAction(hoverAction, parameter.getSingle(event));
    }
}
