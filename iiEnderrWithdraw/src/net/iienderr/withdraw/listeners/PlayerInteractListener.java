package net.iienderr.withdraw.listeners;

import java.text.*;
import org.bukkit.event.player.*;
import net.iienderr.withdraw.*;
import org.bukkit.*;
import net.iienderr.withdraw.util.*;
import me.xCyanide.claimlevels.structure.*;
import net.iienderr.withdraw.voucher.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.event.*;

public class PlayerInteractListener implements Listener
{
    private String EXP_TITLE;
    private String MCMMO_TITLE;
    private String MONEY_TITLE;
    private String MONEY_SUCCEED;
    private String EXP_SUCCEED;
    private String MCMMO_SUCCEED;
    public final DecimalFormat format;

    public PlayerInteractListener() {
        this.MONEY_SUCCEED = ChatColor.translateAlternateColorCodes('&', ConfigManager.getString("MONEY_DEPOSIT", "commandMessages"));
        this.EXP_SUCCEED = ChatColor.translateAlternateColorCodes('&', ConfigManager.getString("EXP_DEPOSIT", "commandMessages"));
        this.MCMMO_SUCCEED = ChatColor.translateAlternateColorCodes('&', ConfigManager.getString("MCMMO_DEPOSIT", "commandMessages"));
        this.MONEY_TITLE = ChatColor.translateAlternateColorCodes('&', ConfigManager.getString("title", "moneyVoucher"));
        this.EXP_TITLE = ChatColor.translateAlternateColorCodes('&', ConfigManager.getString("title", "expVoucher"));
        this.MCMMO_TITLE = ChatColor.translateAlternateColorCodes('&', ConfigManager.getString("title", "mcmmoVoucher"));
        this.format = new DecimalFormat("#,###.##");
    }

    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent e) {
        final Player player = e.getPlayer();
        if (Util.getItemInHand(player) == null) {
            return;
        }
        final ItemStack item = Util.getItemInHand(player);
        if (!item.hasItemMeta()) {
            return;
        }
        if (!item.getItemMeta().hasDisplayName()) {
            return;
        }
        if (!item.getItemMeta().hasLore()) {
            return;
        }
        if (item.getType().equals((Object)ItemUtil.getMoneyMaterial()) && item.getItemMeta().getDisplayName().equals(this.MONEY_TITLE)) {
            e.setCancelled(true);
            iiEnderrWithdraw.econ.depositPlayer((OfflinePlayer)player, (double)MoneyVoucher.getAmountFromItem(item));
            player.playSound(player.getLocation(), SoundUtil.getMoneySound(), 1.0f, 1.0f);
            Util.removeItemInHand(player);
            for (final String message : this.MONEY_SUCCEED.replace("{amount}", this.format.format(MoneyVoucher.getAmountFromItem(item))).split("%")) {
                player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
            }
        }
        else if (item.getType().equals((Object)ItemUtil.getExpMaterial())) {
            e.setCancelled(true);
            ExpUtil.setTotalExperience(player, ExpUtil.getTotalExperience(player) + ExpVoucher.getAmountFromItem(item));
            player.playSound(player.getLocation(), SoundUtil.getExpSound(), 1.0f, 1.0f);
            Util.removeItemInHand(player);
            for (final String message : this.EXP_SUCCEED.replace("{amount}", this.format.format(ExpVoucher.getAmountFromItem(item))).split("%")) {
                player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
            }
        }
        else if (item.getType().equals((Object)ItemUtil.getMcmmoMaterial())) {
            e.setCancelled(true);
            PlayerManager.getPlayerMap().get(player.getUniqueId().toString()).addCredits(McmmoVoucher.getAmountFromItem(item));
            player.playSound(player.getLocation(), SoundUtil.getMcmmoSound(), 1.0f, 1.0f);
            Util.removeItemInHand(player);
            for (final String message : this.MCMMO_SUCCEED.replace("{amount}", this.format.format(McmmoVoucher.getAmountFromItem(item))).split("%")) {
                player.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
            }
        }
    }
}