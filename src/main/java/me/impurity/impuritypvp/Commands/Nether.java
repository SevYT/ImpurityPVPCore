package me.impurity.impuritypvp.Commands;

import me.impurity.impuritypvp.Util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Nether implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.getWorld().getName().equalsIgnoreCase("world_nether")) {
                World nhub = Bukkit.getWorld("world_nether");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bWarping you to the nether..."));
                player.teleport(Utils.randomNhubLocation(player, nhub));
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bYou are already in the nether."));
            }
        }
        return true;
    }
}
