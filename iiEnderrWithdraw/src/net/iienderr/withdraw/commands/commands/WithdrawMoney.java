package net.iienderr.withdraw.commands.commands;

import net.iienderr.withdraw.commands.*;
import java.text.*;
import org.bukkit.command.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import net.iienderr.withdraw.*;
import org.bukkit.*;
import net.iienderr.withdraw.voucher.*;
import org.bukkit.inventory.*;
import net.iienderr.withdraw.util.*;

public class WithdrawMoney implements WithdrawCommand
{
    private String NOT_ENOUGH_MONEY;
    private String SUCCEED;
    private String MAX;
    private String MIN;
    public final DecimalFormat format;

    public WithdrawMoney() {
        this.NOT_ENOUGH_MONEY = ChatColor.translateAlternateColorCodes('&', ConfigManager.getString("NOT_ENOUGH_MONEY", "commandMessages"));
        this.SUCCEED = ChatColor.translateAlternateColorCodes('&', ConfigManager.getString("MONEY_WITHDRAW", "commandMessages"));
        this.MAX = ChatColor.translateAlternateColorCodes('&', ConfigManager.getString("MONEY_MAX", "commandMessages"));
        this.MIN = ChatColor.translateAlternateColorCodes('&', ConfigManager.getString("MONEY_MIN", "commandMessages"));
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
            amount = (int)Math.round(iiEnderrWithdraw.econ.getBalance((OfflinePlayer)player));
        }
        if (amount > MinMaxUtil.getMaxMoney()) {
            for (final String message : this.MAX.split("%")) {
                sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
            }
            return true;
        }
        if (amount < MinMaxUtil.getMinMoney()) {
            for (final String message : this.MIN.split("%")) {
                sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
            }
            return true;
        }
        if (amount < 1) {
            return false;
        }
        if (iiEnderrWithdraw.econ.getBalance((OfflinePlayer)player) < amount) {
            for (final String message : this.NOT_ENOUGH_MONEY.split("%")) {
                sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message));
            }
            return true;
        }
        iiEnderrWithdraw.econ.withdrawPlayer((OfflinePlayer)player, (double)amount);
        final MoneyVoucher voucher = new MoneyVoucher(amount, player.getName());
        player.getInventory().addItem(new ItemStack[] { voucher.getVoucher() });
        for (final String message2 : this.SUCCEED.replace("{amount}", this.format.format(amount)).split("%")) {
            sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', message2));
        }
        player.playSound(player.getLocation(), SoundUtil.getMoneySound(), 1.0f, 1.0f);
        return true;
    }

    @Override
    public String getName() {
        return "withdrawMoney";
    }

    @Override
    public String getUsage() {
        return ConfigManager.getString("MONEY_USAGE", "commandMessages");
    }
}