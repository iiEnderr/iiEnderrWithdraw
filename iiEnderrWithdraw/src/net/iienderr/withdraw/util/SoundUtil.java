package net.iienderr.withdraw.util;

import net.iienderr.withdraw.util.ConfigManager;
import org.bukkit.*;

public class SoundUtil
{
    private static Sound MONEY_SOUND;
    private static Sound EXP_SOUND;
    private static Sound MCMMO_SOUND;

    public static void setupSounds() {
        SoundUtil.MONEY_SOUND = Sound.valueOf(ConfigManager.getString("MONEY_SOUND", "sounds").toUpperCase());
        SoundUtil.EXP_SOUND = Sound.valueOf(ConfigManager.getString("EXP_SOUND", "sounds").toUpperCase());
        SoundUtil.MCMMO_SOUND = Sound.valueOf(ConfigManager.getString("MCMMO_SOUND", "sounds").toUpperCase());
    }

    public static Sound getMoneySound() {
        return SoundUtil.MONEY_SOUND;
    }

    public static Sound getExpSound() {
        return SoundUtil.EXP_SOUND;
    }

    public static Sound getMcmmoSound() {
        return SoundUtil.MCMMO_SOUND;
    }
}
