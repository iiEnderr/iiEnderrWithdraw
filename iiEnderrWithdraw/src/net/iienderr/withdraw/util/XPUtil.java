package net.iienderr.withdraw.util;

import org.bukkit.entity.*;
import java.math.*;

public class XPUtil
{
    private final Player player;

    public XPUtil(final Player player) {
        this.player = player;
    }

    public int getTotalExperience() {
        int experience = 0;
        final int level = this.player.getLevel();
        if (level >= 0 && level <= 15) {
            experience = (int)Math.ceil(Math.pow(level, 2.0) + 6 * level);
            final int requiredExperience = 2 * level + 7;
            final double currentExp = Double.parseDouble(Float.toString(this.player.getExp()));
            experience += (int)Math.ceil(currentExp * requiredExperience);
            return experience;
        }
        if (level > 15 && level <= 30) {
            experience = (int)Math.ceil(2.5 * Math.pow(level, 2.0) - 40.5 * level + 360.0);
            final int requiredExperience = 5 * level - 38;
            final double currentExp = Double.parseDouble(Float.toString(this.player.getExp()));
            experience += (int)Math.ceil(currentExp * requiredExperience);
            return experience;
        }
        experience = (int)Math.ceil(4.5 * Math.pow(level, 2.0) - 162.5 * level + 2220.0);
        final int requiredExperience = 9 * level - 158;
        final double currentExp = Double.parseDouble(Float.toString(this.player.getExp()));
        experience += (int)Math.ceil(currentExp * requiredExperience);
        return experience;
    }

    public void setTotalExperience(final int xp) {
        if (xp >= 0 && xp < 351) {
            final int a = 1;
            final int b = 6;
            final int c = -xp;
            final int level = (int)(-b + Math.sqrt(Math.pow(b, 2.0) - 4 * a * c)) / (2 * a);
            final int xpForLevel = (int)(Math.pow(level, 2.0) + 6 * level);
            final int remainder = xp - xpForLevel;
            final int experienceNeeded = 2 * level + 7;
            float experience = remainder / (float)experienceNeeded;
            experience = this.round(experience, 2);
            this.player.setLevel(level);
            this.player.setExp(experience);
        }
        else if (xp >= 352 && xp < 1507) {
            final double a2 = 2.5;
            final double b2 = -40.5;
            final int c2 = -xp + 360;
            final double dLevel = (-b2 + Math.sqrt(Math.pow(b2, 2.0) - 4.0 * a2 * c2)) / (2.0 * a2);
            final int level2 = (int)Math.floor(dLevel);
            final int xpForLevel2 = (int)(2.5 * Math.pow(level2, 2.0) - 40.5 * level2 + 360.0);
            final int remainder2 = xp - xpForLevel2;
            final int experienceNeeded2 = 5 * level2 - 38;
            float experience2 = remainder2 / (float)experienceNeeded2;
            experience2 = this.round(experience2, 2);
            this.player.setLevel(level2);
            this.player.setExp(experience2);
        }
        else {
            final double a2 = 4.5;
            final double b2 = -162.5;
            final int c2 = -xp + 2220;
            final double dLevel = (-b2 + Math.sqrt(Math.pow(b2, 2.0) - 4.0 * a2 * c2)) / (2.0 * a2);
            final int level2 = (int)Math.floor(dLevel);
            final int xpForLevel2 = (int)(4.5 * Math.pow(level2, 2.0) - 162.5 * level2 + 2220.0);
            final int remainder2 = xp - xpForLevel2;
            final int experienceNeeded2 = 9 * level2 - 158;
            float experience2 = remainder2 / (float)experienceNeeded2;
            experience2 = this.round(experience2, 2);
            this.player.setLevel(level2);
            this.player.setExp(experience2);
        }
    }

    private float round(final float d, final int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, 5);
        return bd.floatValue();
    }
}
