package net.iienderr.withdraw.util;

import net.iienderr.withdraw.util.ConfigManager;
import org.bukkit.*;

public class ItemUtil
{
    private static Material MONEY_MATERIAL;
    private static Material EXP_MATERIAL;
    private static Material MCMMO_MATERIAL;

    public static void setupMaterials() {
        ItemUtil.MONEY_MATERIAL = Material.valueOf(ConfigManager.getString("MONEY_ITEM", "items").toUpperCase());
        ItemUtil.EXP_MATERIAL = Material.valueOf(ConfigManager.getString("EXP_ITEM", "items").toUpperCase());
        ItemUtil.MCMMO_MATERIAL = Material.valueOf(ConfigManager.getString("MCMMO_ITEM", "items").toUpperCase());
    }

    public static Material getMoneyMaterial() {
        return ItemUtil.MONEY_MATERIAL;
    }

    public static Material getExpMaterial() {
        return ItemUtil.EXP_MATERIAL;
    }

    public static Material getMcmmoMaterial() {
        return ItemUtil.MCMMO_MATERIAL;
    }
}
