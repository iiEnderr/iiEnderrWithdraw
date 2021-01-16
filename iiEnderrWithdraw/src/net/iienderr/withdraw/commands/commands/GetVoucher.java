package net.iienderr.withdraw.commands.commands;

import net.iienderr.withdraw.commands.*;
import org.bukkit.command.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import net.iienderr.withdraw.util.*;
import org.bukkit.inventory.*;
import net.iienderr.withdraw.voucher.*;

public class GetVoucher implements WithdrawCommand
{
    @Override
    public boolean execute(final CommandSender sender, final String[] args) {
        if (args.length < 2) {
            return false;
        }
        Player target = null;
        Label_0053: {
            if (args.length == 3) {
                try {
                    target = Bukkit.getPlayer(args[2]);
                    break Label_0053;
                }
                catch (Exception ex) {
                    return false;
                }
            }
            if (args.length >= 3) {
                return false;
            }
            if (!(sender instanceof Player)) {
                return false;
            }
            target = (Player)sender;
        }
        if (target == null) {
            return false;
        }
        VoucherType type;
        try {
            type = VoucherType.valueOf(args[0].toUpperCase());
        }
        catch (Exception ex2) {
            return false;
        }
        int amount;
        try {
            amount = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException ex3) {
            return false;
        }
        Voucher voucher = null;
        switch (type) {
            case EXP: {
                voucher = new ExpVoucher(amount, ConfigManager.getString("defaultOwnerName", "misc"));
                break;
            }
            case MONEY: {
                voucher = new MoneyVoucher(amount, ConfigManager.getString("defaultOwnerName", "misc"));
                break;
            }
            case MCMMO: {
                voucher = new McmmoVoucher(amount, ConfigManager.getString("defaultOwnerName", "misc"));
                break;
            }
            default: {
                throw new NullPointerException("No valid voucher type");
            }
        }
        target.getInventory().addItem(new ItemStack[] { voucher.getVoucher() });
        return true;
    }

    @Override
    public String getName() {
        return "givevoucher";
    }

    @Override
    public String getUsage() {
        return "/givevoucher <exp/money/mcmmo> <amount> [player]";
    }
}
