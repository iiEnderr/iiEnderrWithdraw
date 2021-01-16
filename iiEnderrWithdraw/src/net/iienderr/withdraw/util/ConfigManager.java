package net.iienderr.withdraw.util;

import org.bukkit.configuration.file.*;
import net.iienderr.withdraw.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import org.bukkit.configuration.*;
import java.io.*;

public class ConfigManager
{
    private static FileConfiguration config;
    private static iiEnderrWithdraw main;

    public ConfigManager(final FileConfiguration c, final iiEnderrWithdraw m) {
        ConfigManager.config = c;
        ConfigManager.main = m;
    }

    public static void setString(final String value, final String key, final String... path) {
        createSection(path).set(key, (Object)value);
        ConfigManager.main.saveConfig();
    }

    public static String getString(final String key, final String... path) {
        return getSection(path).get(key).toString();
    }

    public static void setList(final List<String> list, final String key, final String... path) {
        createSection(path).set(key, (Object)list);
        ConfigManager.main.saveConfig();
    }

    public static List<String> getList(final String key, final String... path) {
        return (List<String>)getSection(path).getList(key).stream().map(Object::toString).collect(Collectors.toList());
    }

    private static ConfigurationSection createSection(final String... path) {
        ConfigurationSection section = null;
        for (final String p : path) {
            if (section != null) {
                if (section.getConfigurationSection(p) == null) {
                    section = section.createSection(p);
                }
                else {
                    section = section.getConfigurationSection(p);
                }
            }
            else if (ConfigManager.config.getConfigurationSection(p) == null) {
                section = ConfigManager.config.createSection(p);
            }
            else {
                section = ConfigManager.config.getConfigurationSection(p);
            }
        }
        if (section == null) {
            throw new IllegalArgumentException("Section cannot be null");
        }
        return section;
    }

    private static ConfigurationSection getSection(final String... path) {
        ConfigurationSection section = null;
        for (final String p : path) {
            if (section != null) {
                section = section.getConfigurationSection(p);
            }
            else {
                section = ConfigManager.config.getConfigurationSection(p);
            }
        }
        if (section == null) {
            throw new IllegalArgumentException("Section cannot be null");
        }
        return section;
    }

    public static FileConfiguration getConfiguration() {
        return ConfigManager.config;
    }

    public void setupConfig() {
        if (!ConfigManager.main.getDataFolder().exists()) {
            ConfigManager.main.getDataFolder().mkdir();
        }
        if (!new File(ConfigManager.main.getDataFolder(), "config.yml").exists()) {
            ConfigManager.main.saveDefaultConfig();
        }
    }
}
