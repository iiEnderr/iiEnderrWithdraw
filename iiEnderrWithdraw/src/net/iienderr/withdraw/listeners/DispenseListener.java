package net.iienderr.withdraw.listeners;

import org.bukkit.event.block.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.event.*;

public class DispenseListener implements Listener
{
    @EventHandler
    public void onXpDispense(final BlockDispenseEvent event) {
        final ItemStack item = event.getItem();
        if (item.getType().equals((Object)Material.EXP_BOTTLE) && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().endsWith("(Click)")) {
            event.setCancelled(true);
        }
    }
}