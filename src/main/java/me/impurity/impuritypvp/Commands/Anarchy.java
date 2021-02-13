package me.impurity.impuritypvp.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Anarchy implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.performCommand("join Main");
            Bukkit.broadcastMessage(ChatColor.AQUA + player.getName() + ChatColor.GRAY + " went to the Main Server");
        }
        return true;
    }
}
