package me.nikd0.skope.infrastructure.books;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;
import xyz.upperlevel.spigot.book.BookUtil;

import java.security.InvalidParameterException;

public class TextComponent {
    private String text;
    private ChatColor color; //might get removed
    private ChatColor style; //might get removed
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

    public TextComponent setColor(ChatColor color) throws InvalidParameterException {
        if (isStyle(color)) throw new InvalidParameterException("Expected colour but got style instead.");
        this.color = color;
        return this;
    }

    public ChatColor getColor() {
        return color;
    }

    public TextComponent setStyle(ChatColor style) throws InvalidParameterException {
        if (!isStyle(style)) throw new InvalidParameterException("Expected style but got colour instead.");
        this.style = style;
        return this;
    }

    public ChatColor getStyle() {
        return style;
    }

    public TextComponent setText(String text) {
        this.text = text;
        return this;
    }

    public String getText() {
        return text;
    }

    private boolean isStyle(@NotNull ChatColor chatColor){
        return chatColor.equals(ChatColor.MAGIC)
                || chatColor.equals(ChatColor.BOLD)
                || chatColor.equals(ChatColor.STRIKETHROUGH)
                || chatColor.equals(ChatColor.ITALIC)
                || chatColor.equals(ChatColor.UNDERLINE)
                || chatColor.equals(ChatColor.RESET);
    }

    public BaseComponent getTextComponent(){
        BookUtil.TextBuilder textBuilder = BookUtil.TextBuilder.of(text);

        if (color != null) textBuilder.color(color);
        if (style != null) textBuilder.style(style);
        if (clickAction != null) textBuilder.onClick(clickAction);
        if (hoverAction != null) textBuilder.onHover(hoverAction);
        return textBuilder.build();
    }
}
