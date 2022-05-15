package me.nikd0.skope.infrastructure.books;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.inventory.ItemStack;
import xyz.upperlevel.spigot.book.BookUtil;

public class TextComponent {
    private String text;
    private BookUtil.ClickAction clickAction;
    private BookUtil.HoverAction hoverAction;

    public TextComponent(String text){
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format("Custom (book) text component with text '%s' and possibly a click and/or a hover action.",
                this.text);
    }

    public TextComponent setText(String text) {
        this.text = text;
        return this;
    }

    public void setClickAction(BookUtil.ClickAction clickAction) {
        this.clickAction = clickAction;
    }

    public void setClickAction(ClickEvent.Action action, Object parameter){
        if (action == ClickEvent.Action.RUN_COMMAND) this.clickAction = BookUtil.ClickAction.runCommand((String) parameter);
        else if (action == ClickEvent.Action.SUGGEST_COMMAND) this.clickAction = BookUtil.ClickAction.suggestCommand((String) parameter);
        else if (action == ClickEvent.Action.OPEN_URL) this.clickAction = BookUtil.ClickAction.openUrl((String) parameter);
        else if (action == ClickEvent.Action.CHANGE_PAGE) this.clickAction = BookUtil.ClickAction.changePage((int) parameter);
        else throw new IllegalArgumentException("This click action is not recognised.");
    }

    public BookUtil.ClickAction getClickAction() {
        return clickAction;
    }

    public void setHoverAction(BookUtil.HoverAction hoverAction) {
        this.hoverAction = hoverAction;
    }

    public void setHoverAction(HoverEvent.Action action, Object parameter){
        if (action == HoverEvent.Action.SHOW_TEXT) this.hoverAction = BookUtil.HoverAction.showText((String) parameter);
        else if (action == HoverEvent.Action.SHOW_ITEM) this.hoverAction = BookUtil.HoverAction.showItem((ItemStack) parameter);
        else throw new IllegalArgumentException("This hover action is not recognised.");
    }

    public BookUtil.HoverAction getHoverAction() {
        return hoverAction;
    }

    public String getText() {
        return text;
    }

    public BaseComponent getTextComponent(){
        BookUtil.TextBuilder textBuilder = BookUtil.TextBuilder.of(text);

        if (clickAction != null) textBuilder.onClick(clickAction);
        if (hoverAction != null) textBuilder.onHover(hoverAction);
        return textBuilder.build();
    }
}
