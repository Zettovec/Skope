package me.nikd0.skope.expressions;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.registrations.Classes;
import me.nikd0.skope.infrastructure.CustomSkull;
import me.nikd0.skope.infrastructure.books.Book;
import me.nikd0.skope.infrastructure.books.Page;
import me.nikd0.skope.infrastructure.books.TextComponent;
import org.w3c.dom.Text;

/**
 * Custom Skript types
 */
public class Types {
    // Register custom Skript types
    static {
        if (Classes.getClassInfoNoError("customskull") == null) {
            Classes.registerClass(new ClassInfo<>(CustomSkull.class, "customskull")
                    .user("customskull?")
                    .name("Skope Custom Skull")
                    .description("Custom Skull specified by a texture URL or by owner.")
                    .since("0.1.5")
            );
        }

        if (Classes.getClassInfoNoError("custombook") == null) {
            Classes.registerClass(new ClassInfo<>(Book.class, "custombook")
                    .user("custombook?")
                    .name("Skope Custom Book")
                    .description("Custom Book which may contain custom Skope Pages.")
                    .since("0.1.5")
            );
        }

        if (Classes.getClassInfoNoError("custompage") == null) {
            Classes.registerClass(new ClassInfo<>(Page.class, "custompage")
                    .user("custompage?")
                    .name("Skope Custom Page")
                    .description("Custom Page which may be used in a Custom Book, may contain Skope Text Components.")
                    .since("0.1.5")
            );
        }

        if (Classes.getClassInfoNoError("textcomponent") == null) {
            Classes.registerClass(new ClassInfo<>(TextComponent.class, "textcomponent")
                    .user("textcomponent?")
                    .name("Skope Custom Text Component")
                    .description("Custom Text component which may be used in a Custom Page.")
                    .since("0.1.5")
            );
        }

    }
}
