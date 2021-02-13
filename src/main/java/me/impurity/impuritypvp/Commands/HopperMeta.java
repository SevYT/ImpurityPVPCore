package me.impurity.impuritypvp.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HopperMeta implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            World world = Bukkit.getWorld("Hopper32k");
            Location location = new Location(world, 0.5, 151, 0.5);
            location.setYaw(0.0F);
            location.setPitch(0.0F);
            player.sendMessage(ChatColor.AQUA + "Warping you to the Hopper Meta Auto32k Arena...");
            player.teleport(location);
        }
        return true;
    }
}
