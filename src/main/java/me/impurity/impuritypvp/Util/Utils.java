package me.impurity.impuritypvp.Util;

import me.impurity.impuritypvp.ImpurityPVP;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {
    public static FileConfiguration config() {
        return ImpurityPVP.getPlugin().getConfig();
    }
    public static void teleportPlayerToSpawn(Player player) {
        World world = Bukkit.getWorld("world");
        Location location = new Location(world, 0.5, 100, 0.5);
        location.setYaw(0.0F);
        location.setPitch(0.0F);
        player.teleport(location);
    }

    public static int randomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static Location randomNhubLocation(Player player, World world) {
        world = Bukkit.getWorld("world_nether");
        int x = randomNumber(-10, 10);
        int z = randomNumber(-10, 10);
        int y = config().getInt("YLevel");
        Location location = new Location(world, x, y, z);
        location.setPitch(90.0F);
        Chunk chunk = location.getChunk();
        if (!chunk.isLoaded()) {
            chunk.load(true);
            location.setY(world.getHighestBlockYAt(location));
        }
        return location;
    }
}
