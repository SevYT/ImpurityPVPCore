package me.impurity.impuritypvp.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitCreator implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            World world = Bukkit.getWorld("world_the_end");
            Location location = new Location(world, 400.5, 100, 400.5);
            location.setYaw(180.0F);
            location.setPitch(0.0F);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bWarping you to the kitcreator..."));
            player.teleport(location);
        }
        return true;
    }
}
