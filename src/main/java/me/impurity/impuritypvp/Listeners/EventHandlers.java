package me.impurity.impuritypvp.Listeners;

import me.impurity.impuritypvp.Util.Utils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class EventHandlers implements Listener {

    Location l1 = new Location(Bukkit.getWorld("world"), -17, 99, 1);
    Location l2 = new Location(Bukkit.getWorld("world"), -17, 103, -0);

    public static boolean isNamedItem(ItemStack item) {
        if (item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            return meta.hasDisplayName();
        }
        return false;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Utils.teleportPlayerToSpawn(event.getPlayer());
        event.getPlayer().getInventory().clear();
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        World world = Bukkit.getWorld("world");
        Location location = new Location(world, 0.5, 100, 0.5);
        location.setYaw(0.0F);
        location.setPitch(0.0F);
        event.setRespawnLocation(location);
    }

    @EventHandler
    public void onDamageEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            if (event.getDamager() instanceof Player) {
                Player enemy = (Player) event.getDamager();
                if (enemy.getLocation().getY() >= 128) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            Player p = (Player) entity;
            if (p.getLocation().getY() >= 128) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        Player p = event.getPlayer();
        if (p.getLocation().getY() >= 128) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!event.getPlayer().isOp()) {
            Player p = event.getPlayer();
            if (p.getLocation().getY() >= 128) {
                event.setCancelled(true);
            }
            int block = event.getBlock().getY();
            if (block >= 128) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!event.getPlayer().isOp()) {
            Player p = event.getPlayer();
            if (p.getLocation().getY() >= 128) {
                event.setCancelled(true);
            }
            int block = event.getBlock().getY();
            if (block >= 128) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPortal(PlayerPortalEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getWorld().getName().equalsIgnoreCase("world")) {
            World nhub = Bukkit.getWorld("world_nether");
            Block block = player.getLocation().getBlock();
            if (block.getType() == Material.PORTAL) {
                player.teleport(Utils.randomNhubLocation(player, nhub));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bWarping you to the nether..."));
            } else if (block.getType() == Material.END_GATEWAY) {
                World world = Bukkit.getWorld("world_the_end");
                Location location = new Location(world, 400.5, 100, 400.5);
                location.setYaw(180.0F);
                location.setPitch(0.0F);
                player.teleport(location);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bWarping you to the kitcreator..."));
            } else if (block.getType() == Material.WATER && event.getPlayer().getWorld().getName().equalsIgnoreCase("world")) {
                Location l1 = new Location(Bukkit.getWorld("world"), -17, 99, 1);
                Location l2 = new Location(Bukkit.getWorld("world"), -17, 103, -0);
                if (player.getLocation(l1).getBlockX() >= l1.getBlockX()
                        && player.getLocation(l1).getBlockY() >= l1.getBlockY()
                        && player.getLocation(l1).getBlockZ() >= l1.getBlockZ()
                        && player.getLocation(l2).getBlockX() <= l2.getBlockX()
                        && player.getLocation(l2).getBlockY() <= l2.getBlockY()
                        && player.getLocation(l2).getBlockZ() <= l2.getBlockZ()) {
                    World world = Bukkit.getWorld("hopper32k");
                    Location location = new Location(world, 0.5, 151, 0.5);
                    location.setYaw(0.0F);
                    location.setPitch(0.0F);
                    player.teleport(location);
                    player.sendMessage(ChatColor.AQUA + "Warping you to the Hopper Meta Auto32k Arena...");
                }
            }
        }
    }

    @EventHandler
    public void onChat(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().equalsIgnoreCase("/kit")) {
            event.getPlayer().performCommand("pvpkit");
            event.setCancelled(true);
        }
        if (event.getMessage().equalsIgnoreCase("/tps")) {
            event.getPlayer().performCommand("impuritycore:tps");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.getDrops().clear();
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        if (item != null) {
            System.out.println(item.getType());
            if (item.getItemMeta() instanceof SkullMeta) {
                ItemMeta meta = item.getItemMeta();
                SkullMeta skullMeta = (SkullMeta) meta;
                Player player = (Player) event.getWhoClicked();
                System.out.println(player.getPlayerProfile());
                skullMeta.setPlayerProfile(player.getPlayerProfile());
                item.setItemMeta(skullMeta);
            }
        }
    }
}
