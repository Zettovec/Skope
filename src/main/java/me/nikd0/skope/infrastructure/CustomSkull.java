package me.nikd0.skope.infrastructure;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.nikd0.skope.Main;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Skull;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.UUID;

public class CustomSkull {

    private String textureURL;
    private GameProfile profile;
    private OfflinePlayer owner;
    final private SkullType type;

    public CustomSkull(@NotNull String textureURL){
        this.textureURL = textureURL;
        this.type = SkullType.BY_URL;

        this.profile = new GameProfile(UUID.randomUUID(), null);

        String json = "{\"textures\":{\"SKIN\":{\"url\":\"" + textureURL + "\"}}}";
        String encodedData = Base64.getEncoder().encodeToString(json.getBytes());
        profile.getProperties().put("textures", new Property("textures", encodedData));
    }

    public CustomSkull(@NotNull OfflinePlayer owner){
        this.owner = owner;
        this.type = SkullType.BY_OWNER;
    }

    public void apply(Skull skull){
        if (this.type == SkullType.BY_URL) {
            try {
                Field profileField = skull.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(skull, profile);
                skull.update(true);
            } catch (Exception e) {
                Main.logSevere("Failed to apply custom skull texture!");
                e.printStackTrace();
            }
        } else if (this.type == SkullType.BY_OWNER) {
            skull.setOwningPlayer(this.owner);
            skull.update(true);
        }
    }

    public String toString(){
        if (this.type == SkullType.BY_URL) {
            return "CustomSkull{textureURL='" + textureURL + "'}";
        } else {
            return "CustomSkull{owner=" + owner.getName() + "}";
        }
    }

    public enum SkullType {
        BY_URL,
        BY_OWNER;
    }

}
