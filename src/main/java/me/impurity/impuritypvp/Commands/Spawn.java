package me.impurity.impuritypvp.Commands;

import me.impurity.impuritypvp.Util.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Utils.teleportPlayerToSpawn(player);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bWarping you to spawn..."));
        }
        return true;
    }
}
