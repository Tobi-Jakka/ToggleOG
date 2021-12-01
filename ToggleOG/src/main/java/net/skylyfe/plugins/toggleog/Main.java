package net.skylyfe.plugins.toggleog;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class Main extends JavaPlugin {

    public static Main plugin;

    CommandHandler commandHandler = new CommandHandler();

    public static Map<String, Boolean> options = new HashMap<String, Boolean>();

    @Override
    public void onEnable() {
        // Plugin startup logic

        if(this.getConfig().contains("data")) {
            this.restoreBools();
        }

        plugin = this;

        this.saveDefaultConfig(); // <-- create config.yml

        for (String command : commandHandler.cmds)
        {
            getCommand(command).setExecutor(commandHandler);
        }

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new ToggleOG(this).register();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (!options.isEmpty()) {
        this.SaveBools();
        }
    }

    public void SaveBools() {
        for (Map.Entry<String, Boolean> entry : options.entrySet()) {
            this.getConfig().set("data." + entry.getKey(), entry.getValue());
        }
        this.saveConfig();
    }

    public void restoreBools() {
        this.getConfig().getConfigurationSection("data").getKeys(false).forEach(key -> {
            Boolean playerOptions = (Boolean) this.getConfig().get("data." + key);
            options.put(key, playerOptions);
        });
    }

}
