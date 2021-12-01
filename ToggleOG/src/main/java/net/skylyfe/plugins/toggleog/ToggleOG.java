package net.skylyfe.plugins.toggleog;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

import static net.skylyfe.plugins.toggleog.CommandHandler.playerOption;
import static net.skylyfe.plugins.toggleog.Main.options;

public class ToggleOG extends PlaceholderExpansion {

    private final Main plugin;

    public ToggleOG(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getAuthor() {
        return "Tobi3944";
    }

    @Override
    public String getIdentifier() {
        return "toggleog";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {

        // %toggleog_prefix%
        if (!player.hasPermission("toggleog.use")) {
            return "";
        }

        if(!options.containsKey(player.getUniqueId().toString())) {
            options.put(player.getUniqueId().toString(), playerOption);
            if (identifier.equals("prefix")) {
                return "&lOG&r ";
            }
        }
        if(options.get(player.getUniqueId().toString())) {
            if (identifier.equals("prefix")) {
                return "&lOG&r ";
            }
        }
        return "";
    }
}
