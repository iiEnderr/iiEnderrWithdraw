package net.iienderr.withdraw.commands.commands;

import net.iienderr.withdraw.commands.*;
import java.text.*;
import net.md_5.bungee.api.*;
import org.bukkit.command.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import net.iienderr.withdraw.voucher.*;
import org.bukkit.inventory.*;
import net.iienderr.withdraw.util.*;

public class XpBottle implements WithdrawCommand
{
    private String NOT_ENOUGH_XP;
    private String SUCCEED;
    private String MAX;
    private String MIN;
    public final DecimalFormat format;

    public XpBottle() {
        this.NOT_ENOUGH_XP = ChatColor.translateAlternateColorCodes('&', ConfigManager.getString("NOT_ENOUGH_EXP", "commandMessages"));
        this.SUCCEED = ChatColor.translateAlternateColorCodes('&', ConfigManager.getString("EXP_WITHDRAW", "commandMessages"));
        this.MAX = org.bukkit.ChatColor.translateAlternateColorCodes('&', ConfigManager.getString("EXP_MAX", "commandMessages"));
        this.MIN = org.bukkit.ChatColor.translateAlternateColorCodes('&', ConfigManager.getString("EXP_MIN", "commandMessages"));
        this.format = new DecimalFormat("#,###.##");
    }

    @Override
    public boolean execute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
            return false;
        }
        if (!(sender instanceof Player)) {
            return false;
        }
        final Player player = (Player)sender;
        final int oldExp = ExpUtil.getTotalExperience(player);
        int exp;
        try {
            exp = Integer.valueOf(args[0]);
        }
        catch (NumberFormatException ex) {
            if (!args[0].equalsIgnoreCase("all")) {
                return false;
            }
            exp = ExpUtil.getTotalExperience(player);
        }
        if (exp > MinMaxUtil.getMaxExp()) {
            for (final String message : this.MAX.split("%")) {
                sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
            }
            return true;
        }
        if (exp < MinMaxUtil.getMinExp()) {
            for (final String message : this.MIN.split("%")) {
                sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
            }
            return true;
        }
        if (exp < 1) {
            return false;
        }
        if (oldExp < exp) {
            for (final String message : this.NOT_ENOUGH_XP.split("%")) {
                sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
            }
            return true;
        }
        ExpUtil.setTotalExperience(player, oldExp - exp);
        final ExpVoucher voucher = new ExpVoucher(exp, player.getName());
        player.getInventory().addItem(new ItemStack[] { voucher.getVoucher() });
        for (final String message2 : this.SUCCEED.replace("{amount}", this.format.format(exp)).split("%")) {
            sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message2));
        }
        player.playSound(player.getLocation(), SoundUtil.getExpSound(), 1.0f, 1.0f);
        return true;
    }

    @Override
    public String getName() {
        return "xpBottle";
    }

    @Override
    public String getUsage() {
        return ConfigManager.getString("EXP_USAGE", "commandMessages");
    }
}