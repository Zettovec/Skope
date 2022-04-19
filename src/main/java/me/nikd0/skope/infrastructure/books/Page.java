package me.nikd0.skope.infrastructure.books;

import net.md_5.bungee.api.chat.BaseComponent;
import xyz.upperlevel.spigot.book.BookUtil;

import java.util.ArrayList;
import java.util.List;

public class Page {
    private List<TextComponent> texts = new ArrayList<>();

    @Override
    public String toString() {
        return String.format("Custom (book) page with text components: %s",
                this.texts.toString());
    }

    public Page setTexts(List<TextComponent> texts){
        this.texts = texts;
        return this;
    }

    public Page setText(TextComponent text){
        this.texts = new ArrayList<>();
        return addText(text);
    }

    public Page addText(TextComponent text){
        this.texts.add(text);
        return this;
    }

    public Page newLine(){
        this.texts.add(
                new TextComponent("\n")
        );
        return this;
    }

    public BaseComponent[] getPage(){
        BookUtil.PageBuilder pageBuilder = new BookUtil.PageBuilder();

        for (TextComponent text: texts) {
            pageBuilder.add(text.getTextComponent());
        }
        return pageBuilder.build();
    }
}
