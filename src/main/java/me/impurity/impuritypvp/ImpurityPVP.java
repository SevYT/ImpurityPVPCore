package me.impurity.impuritypvp;

import me.impurity.impuritypvp.Commands.*;
import me.impurity.impuritypvp.Listeners.EntityInteract;
import me.impurity.impuritypvp.Listeners.EventHandlers;
import org.bukkit.Rotation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class ImpurityPVP extends JavaPlugin implements TabExecutor {

    PluginManager pl = getServer().getPluginManager();

    public static Plugin getPlugin() {
        return getPlugin(ImpurityPVP.class);
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        getLogger().info("Registering Events...");
        pl.registerEvents(new EventHandlers(), this);
        pl.registerEvents(new EntityInteract(this), this);
        getLogger().info("Registering Commands...");
        getCommand("spawn").setExecutor(new Spawn());
        getCommand("kitcreator").setExecutor(new KitCreator());
        getCommand("nether").setExecutor(new Nether());
        getCommand("rot").setExecutor(this);
        getCommand("anarchy").setExecutor(new Anarchy());
        getCommand("hopper32k").setExecutor(new HopperMeta());
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && args.length > 0) {
            Player player = (Player)sender;
            for (Entity entity : player.getWorld().getEntities()) {
                if (entity instanceof ItemFrame) {
                    ItemFrame frame = (ItemFrame)entity;
                    frame.setRotation(Rotation.valueOf(args[0].toUpperCase()));
                }
            }
        }
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        for (Rotation rotation : Rotation.values())
            list.add(rotation.name().toLowerCase());
        return list;
    }
}
