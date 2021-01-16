package net.iienderr.withdraw.util;

public class MinMaxUtil
{
    public static int getMaxExp() {
        return Integer.parseInt(ConfigManager.getString("MAX_EXP", "minMaxValues"));
    }

    public static int getMinExp() {
        return Integer.parseInt(ConfigManager.getString("MIN_EXP", "minMaxValues"));
    }

    public static int getMaxMoney() {
        return Integer.parseInt(ConfigManager.getString("MAX_MONEY", "minMaxValues"));
    }

    public static int getMinMoney() {
        return Integer.parseInt(ConfigManager.getString("MIN_MONEY", "minMaxValues"));
    }

    public static int getMaxMcmmo() {
        return Integer.parseInt(ConfigManager.getString("MAX_MCMMO", "minMaxValues"));
    }

    public static int getMinMcmmo() {
        return Integer.parseInt(ConfigManager.getString("MIN_MCMMO", "minMaxValues"));
    }
}
