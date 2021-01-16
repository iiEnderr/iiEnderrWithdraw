package net.iienderr.withdraw.voucher;

import org.bukkit.inventory.*;
import net.iienderr.withdraw.util.*;
import org.bukkit.*;
import java.util.*;

public class MoneyVoucher extends Voucher
{
    public MoneyVoucher(final int amount, final String ownerName) {
        super(ItemUtil.getMoneyMaterial(), "moneyVoucher", amount, ownerName);
    }

    public static int getAmountFromItem(final ItemStack item) {
        final int index = Util.getAmountIndexForMoneyVoucher();
        final List<String> lore = (List<String>)item.getItemMeta().getLore();
        final String amount = lore.get(index).split(" ")[Util.getAmountIndexForMoneyVoucherString()].replace("$", "").replace(",", "");
        return Integer.parseInt(ChatColor.stripColor(amount));
    }
}
