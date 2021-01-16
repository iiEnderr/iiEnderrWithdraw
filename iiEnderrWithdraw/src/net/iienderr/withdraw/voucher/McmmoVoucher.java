package net.iienderr.withdraw.voucher;

import org.bukkit.inventory.*;
import net.iienderr.withdraw.util.*;
import org.bukkit.*;
import java.util.*;

public class McmmoVoucher extends Voucher
{
    public McmmoVoucher(final int amount, final String ownerName) {
        super(ItemUtil.getMcmmoMaterial(), "mcmmoVoucher", amount, ownerName);
    }

    public static int getAmountFromItem(final ItemStack item) {
        final int index = Util.getAmountIndexForMcmmoVoucher();
        final List<String> lore = (List<String>)item.getItemMeta().getLore();
        final String amount = lore.get(index).split(" ")[Util.getAmountIndexForMcmmoVoucherString()].toLowerCase().replace("Credits", "").replace(",", "");
        return Integer.parseInt(ChatColor.stripColor(amount));
    }
}
