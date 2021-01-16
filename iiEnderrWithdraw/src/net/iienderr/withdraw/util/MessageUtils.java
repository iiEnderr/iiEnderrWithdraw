package net.iienderr.withdraw.util;

import org.bukkit.*;

public class MessageUtils
{
    public static String translateColorCodes(final String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String chatColorMessageToConfigCodes(final String message) {
        return message.replaceAll("§", "&");
    }
}
