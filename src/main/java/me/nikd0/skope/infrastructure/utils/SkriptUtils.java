package me.nikd0.skope.infrastructure.utils;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import me.nikd0.skope.Main;
import org.bukkit.Bukkit;

import java.io.IOException;

public class SkriptUtils {
    private static SkriptAddon addonInstance;

    public static boolean checkSkript(){
        if (Bukkit.getPluginManager().getPlugin("Skript") != null) return true;

        Main.logSevere("Could not load Skope! Skript (a required dependency) was not found.");
        Main.disablePlugin();
        return false;
    }

    public static void loadAddon() throws IOException {
        getAddonInstance().loadClasses("me.nikd0.skope", "effects", "expressions", "events");
    }

    public static SkriptAddon getAddonInstance() {
        if (addonInstance == null) {
            addonInstance = Skript.registerAddon(Main.getInstance());
        }
        return addonInstance;
    }

}
