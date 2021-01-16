package net.iienderr.withdraw.voucher;

import org.bukkit.*;
import org.bukkit.inventory.*;
import java.text.*;
import net.iienderr.withdraw.util.*;
import java.util.*;
import java.util.stream.*;
import org.bukkit.inventory.meta.*;

public class Voucher
{
    private Material material;
    private String name;
    private int amount;
    private String ownerName;
    private ItemStack voucher;
    private static final DecimalFormat format;

    Voucher(final Material material, final String name, final int amount, final String ownerName) {
        this.material = material;
        this.name = name;
        this.amount = amount;
        this.ownerName = ownerName;
        this.voucher = mkVoucher(this);
    }

    private static ItemStack mkVoucher(final Voucher v) {
        final ItemStack voucher = new ItemStack(v.getMaterial(), 1);
        final ItemMeta meta = voucher.getItemMeta();
        final String title = ConfigManager.getString("title", v.getName());
        final List<String> l = ConfigManager.getList("lore", v.getName());
        final List<String> lore = l.stream().map(s -> ChatColor.translateAlternateColorCodes('&', s).replace("{amount}", Voucher.format.format(v.getAmount())).replace("{owner}", v.getOwnerName())).collect(Collectors.toList());
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', title));
        meta.setLore((List)lore);
        voucher.setItemMeta(meta);
        return voucher;
    }

    public ItemStack getVoucher() {
        return this.voucher;
    }

    private Material getMaterial() {
        return this.material;
    }

    private String getName() {
        return this.name;
    }

    public int getAmount() {
        return this.amount;
    }

    private String getOwnerName() {
        return this.ownerName;
    }

    static {
        format = new DecimalFormat("#,###.##");
    }
}
