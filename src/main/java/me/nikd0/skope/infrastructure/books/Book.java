package me.nikd0.skope.infrastructure.books;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import xyz.upperlevel.spigot.book.BookUtil;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private String author;
    private String title;
    private BookMeta.Generation generation;

    private List<Page> pages = new ArrayList<>();

    @Override
    public String toString() {
        return String.format("Custom book with title %s, author %s, generation %s and pages: %s",
                this.title,
                this.author,
                this.generation.toString(),
                this.pages.toString());
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setGeneration(Integer generation) throws InvalidParameterException {
        if (generation == null) return this;
        if (generation < 0 || generation > 3) throw new InvalidParameterException("Generation value must be an integer between 0 and 3.");

        return setGeneration(BookMeta.Generation.values()[generation]);
    }

    public Book setGeneration(BookMeta.Generation generation) {
        this.generation = generation;
        return this;
    }

    public BookMeta.Generation getGeneration() {
        return generation;
    }

    public Book setPages(List<Page> pages) {
        this.pages = pages;
        return this;
    }

    public Book addPage(Page page) {
        this.pages.add(page);
        return this;
    }

    public Book removePage(Page page){
        this.pages.remove(page);
        return this;
    }

    public List<Page> getPages() {
        return pages;
    }

    public ItemStack getBook(){
        List<BaseComponent[]> pageComponents = new ArrayList<>();
        for (Page page: pages) {
            pageComponents.add(page.getPage());
        }

        return BookUtil.writtenBook()
                .author(this.author)
                .title(this.title)
                .generation(this.generation)
                .pages(pageComponents)
                .build();
    }

    public void open(Player player){
        BookUtil.openPlayer(player, getBook());
    }
}
