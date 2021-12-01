package net.skylyfe.plugins.toggleog;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.skylyfe.plugins.toggleog.Main.options;

public class CommandHandler implements CommandExecutor {

    public static Boolean playerOption = true;

    String[] cmds = new String[] {
            "toggleog"
    };

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase(cmds[0]))

            if (sender instanceof Player) {
                Player player = (Player) sender;

                if (!player.hasPermission("toggleog.use")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',  "&cYou don't have access to that command"));
                    return true; //msg handled in CommandHandler
                }
                if (!options.containsKey(player.getUniqueId().toString())) {
                    options.put(player.getUniqueId().toString(), playerOption);
                    return true;
                }

                playerOption = options.get(player.getUniqueId().toString());

                if(playerOption) {
                    playerOption = false;
                } else {
                    playerOption = true;
                }
                options.put(player.getUniqueId().toString(), playerOption);
                return true;
            }

        return true;
    }
}
