package me.impurity.impuritypvp.Listeners;

import me.impurity.impuritypvp.ImpurityPVP;
import me.impurity.impuritypvp.Util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Rotation;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EntityInteract implements Listener {
    ImpurityPVP plugin;

    public EntityInteract(ImpurityPVP impurityPVP) {
        this.plugin = impurityPVP;
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClick(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() instanceof ItemFrame) {
            ItemFrame frame = (ItemFrame)event.getRightClicked();
            frame.setRotation(Rotation.NONE);
            setupGui(event.getPlayer(), frame);
            event.setCancelled(true);
        }
    }

    private void setupGui(Player player, ItemFrame frame) {
        ItemStack item = frame.getItem();
        item.setAmount(item.getMaxStackSize());
        Inventory inventory = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', Utils.config().getString("FrameFormat")));
        for (int i = 0; i < inventory.getSize(); i++)
            inventory.setItem(i, item);
        frame.setRotation(Rotation.NONE);
        player.openInventory(inventory);
        if (!frame.isInvulnerable())
            frame.setInvulnerable(true);
    }
}
