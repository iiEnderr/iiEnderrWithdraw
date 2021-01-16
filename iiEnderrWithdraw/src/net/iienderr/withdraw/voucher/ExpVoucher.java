package net.iienderr.withdraw.voucher;

import org.bukkit.inventory.*;
import net.iienderr.withdraw.util.*;
import org.bukkit.*;
import java.util.*;

public class ExpVoucher extends Voucher
{
    public ExpVoucher(final int amount, final String ownerName) {
        super(ItemUtil.getExpMaterial(), "expVoucher", amount, ownerName);
    }

    public static int getAmountFromItem(final ItemStack item) {
        final int index = Util.getAmountIndexForXpVoucher();
        final List<String> lore = (List<String>)item.getItemMeta().getLore();
        final String amount = lore.get(index).split(" ")[Util.getAmountIndexForXpVoucherString()].toLowerCase().replace("xp", "").replace(",", "");
        return Integer.parseInt(ChatColor.stripColor(amount));
    }
}
