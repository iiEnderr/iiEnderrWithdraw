package net.iienderr.withdraw.commands.commands;

import net.iienderr.withdraw.commands.*;
import java.text.*;
import net.md_5.bungee.api.*;
import org.bukkit.command.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import me.xCyanide.claimlevels.structure.*;
import net.iienderr.withdraw.voucher.*;
import org.bukkit.inventory.*;
import net.iienderr.withdraw.util.*;

public class WithdrawMcmmo implements WithdrawCommand
{
    private String NOT_ENOUGH_MCMMO;
    private String SUCCEED;
    private String MAX;
    private String MIN;
    public final DecimalFormat format;

    public WithdrawMcmmo() {
        this.NOT_ENOUGH_MCMMO = ChatColor.translateAlternateColorCodes('&', ConfigManager.getString("NOT_ENOUGH_MCMMO", "commandMessages"));
        this.SUCCEED = ChatColor.translateAlternateColorCodes('&', ConfigManager.getString("MCMMO_WITHDRAW", "commandMessages"));
        this.MAX = ChatColor.translateAlternateColorCodes('&', ConfigManager.getString("MCMMO_MAX", "commandMessages"));
        this.MIN = ChatColor.translateAlternateColorCodes('&', ConfigManager.getString("MCMMO_MIN", "commandMessages"));
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
        int amount;
        try {
            amount = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException ex) {
            if (!args[0].equalsIgnoreCase("all")) {
                return false;
            }
            amount = Math.round((float)PlayerManager.getPlayerMap().get(player.getUniqueId().toString()).getCredits());
        }
        if (amount > MinMaxUtil.getMaxMcmmo()) {
            for (final String message : this.MAX.split("%")) {
                sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
            }
            return true;
        }
        if (amount < MinMaxUtil.getMinMcmmo()) {
            for (final String message : this.MIN.split("%")) {
                sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
            }
            return true;
        }
        if (amount < 1) {
            return false;
        }
        if (PlayerManager.getPlayerMap().get(player.getUniqueId().toString()).getCredits() < amount) {
            for (final String message : this.NOT_ENOUGH_MCMMO.split("%")) {
                sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
            }
            return true;
        }
        PlayerManager.getPlayerMap().get(player.getUniqueId().toString()).removeCredits(amount);
        final McmmoVoucher voucher = new McmmoVoucher(amount, player.getName());
        player.getInventory().addItem(new ItemStack[] { voucher.getVoucher() });
        for (final String message2 : this.SUCCEED.replace("{amount}", this.format.format(amount)).split("%")) {
            sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message2));
        }
        player.playSound(player.getLocation(), SoundUtil.getMcmmoSound(), 1.0f, 1.0f);
        return true;
    }

    @Override
    public String getName() {
        return "withdrawMcmmo";
    }

    @Override
    public String getUsage() {
        return ConfigManager.getString("MCMMO_USAGE", "commandMessages");
    }
}
