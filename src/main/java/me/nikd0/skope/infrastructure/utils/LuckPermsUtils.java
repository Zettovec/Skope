package me.nikd0.skope.infrastructure.utils;

import me.nikd0.skope.Main;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.node.Node;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.UUID;

public class LuckPermsUtils {
    private static LuckPerms luckPerms;

    public static void load() {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            luckPerms = provider.getProvider();

        }
        Main.logInfo("Loading LuckPerms...");
    }

    public static void givePermission(OfflinePlayer player, String permission, boolean state) {

        UUID userUuid = player.getUniqueId();

        luckPerms.getUserManager().modifyUser(userUuid, user -> {
            user.data().add(Node.builder(permission).value(state).build());
        });

    }

    public static void removePermission(OfflinePlayer player, String permission) {

        UUID userUuid = player.getUniqueId();

        luckPerms.getUserManager().modifyUser(userUuid, user -> {
            user.data().remove(Node.builder(permission).build());
        });

    }

}
