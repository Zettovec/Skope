package me.nikd0.skope.infrastructure.books;

import net.md_5.bungee.api.chat.BaseComponent;
import xyz.upperlevel.spigot.book.BookUtil;

public class TextComponent {
    private String text;
    private BookUtil.ClickAction clickAction; //ToDo
    private BookUtil.HoverAction hoverAction; //ToDo

    public TextComponent(String text){
        this.text = text;
    }

    @Override
    public String toString() { //ToDo - Add all variables
        return String.format("Custom (book) text component with text '%s'.",
                this.text);
    }

    public TextComponent setText(String text) {
        this.text = text;
        return this;
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
