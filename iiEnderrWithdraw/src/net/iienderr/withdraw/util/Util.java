package net.iienderr.withdraw.util;

import java.util.*;

import net.iienderr.withdraw.util.ConfigManager;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.*;

public class Util
{
    public static int getAmountIndexForXpVoucherString() {
        final String[] lore = ConfigManager.getList("lore", "expVoucher").get(getAmountIndexForXpVoucher()).split(" ");
        for (int i = 0; i < lore.length; ++i) {
            if (lore[i].contains("{amount}")) {
                return i;
            }
        }
        throw new NullPointerException("Lore does not contain an amount");
    }

    public static int getAmountIndexForMoneyVoucherString() {
        final String[] lore = ConfigManager.getList("lore", "moneyVoucher").get(getAmountIndexForMoneyVoucher()).split(" ");
        for (int i = 0; i < lore.length; ++i) {
            if (lore[i].contains("{amount}")) {
                return i;
            }
        }
        throw new NullPointerException("Lore does not contain an amount");
    }

    public static int getAmountIndexForMoneyVoucher() {
        final List<String> lore = ConfigManager.getList("lore", "moneyVoucher");
        for (int i = 0; i < lore.size(); ++i) {
            if (lore.get(i).contains("{amount}")) {
                return i;
            }
        }
        throw new NullPointerException("Lore does not contain an amount");
    }

    public static int getAmountIndexForXpVoucher() {
        final List<String> lore = ConfigManager.getList("lore", "expVoucher");
        for (int i = 0; i < lore.size(); ++i) {
            if (lore.get(i).contains("{amount}")) {
                return i;
            }
        }
        throw new NullPointerException("Lore does not contain an amount");
    }

    public static int getAmountIndexForMcmmoVoucherString() {
        final String[] lore = ConfigManager.getList("lore", "mcmmoVoucher").get(getAmountIndexForMcmmoVoucher()).split(" ");
        for (int i = 0; i < lore.length; ++i) {
            if (lore[i].contains("{amount}")) {
                return i;
            }
        }
        throw new NullPointerException("Lore does not contain an amount");
    }

    public static int getAmountIndexForMcmmoVoucher() {
        final List<String> lore = ConfigManager.getList("lore", "mcmmoVoucher");
        for (int i = 0; i < lore.size(); ++i) {
            if (lore.get(i).contains("{amount}")) {
                return i;
            }
        }
        throw new NullPointerException("Lore does not contain an amount");
    }

    public static void removeItemInHand(final Player player) {
        if (getItemInHand(player).getAmount() > 1) {
            getItemInHand(player).setAmount(getItemInHand(player).getAmount() - 1);
        }
        else {
            setItemInHand(player, new ItemStack(Material.AIR));
        }
    }

    public static ItemStack getItemInHand(final Player player) {
        if (Bukkit.getServer().getVersion().contains("MC: 1.9")) {
            return player.getInventory().getItemInHand();
        }
        return player.getItemInHand();
    }

    public static void setItemInHand(final Player player, final ItemStack item) {
        if (Bukkit.getServer().getVersion().contains("MC: 1.9")) {
            player.getInventory().setItemInHand(item);
        }
        else {
            player.setItemInHand(item);
        }
    }
}
