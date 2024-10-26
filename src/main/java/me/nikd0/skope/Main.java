package me.nikd0.skope;

import me.nikd0.skope.infrastructure.utils.LuckPermsUtils;
import me.nikd0.skope.infrastructure.utils.SkriptUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;
    private static String version;

    @Override
    public void onEnable(){
        instance = this;
        version = this.getDescription().getVersion();

        if (!SkriptUtils.checkSkript()) return;

        try {
            SkriptUtils.loadAddon();
            logInfo(String.format("§bYou're using §3Skope§b version §3%s§b! Thanks for the support!", this.getVersion()));
            if (luckPermsEnabled()) LuckPermsUtils.load();
        } catch (Exception e){
            logSevere("Could not load Skope! An error occurred:");
            e.printStackTrace();
            disablePlugin();
        }
    }

    public static Main getInstance() {
        return instance;
    }

    public static String getVersion() {
        return version;
    }

    public static boolean luckPermsEnabled(){
        return Bukkit.getPluginManager().getPlugin("LuckPerms") != null;
    }

    public static void logInfo(String text){
        instance.getLogger().info(text);
    }

    public static void logWarning(String text){
        instance.getLogger().warning(text);
    }

    public static void logSevere(String text){
        instance.getLogger().severe(text);
    }

    public static void disablePlugin(){
        logSevere("The plugin has been disabled!");
        instance.setEnabled(false);
    }

}
