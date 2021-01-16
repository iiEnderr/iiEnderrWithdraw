package net.iienderr.withdraw;

import org.bukkit.plugin.java.*;
import net.milkbowl.vault.economy.*;
import net.iienderr.withdraw.commands.*;
import java.text.*;
import org.bukkit.*;
import net.iienderr.withdraw.util.*;
import org.bukkit.event.*;
import net.iienderr.withdraw.listeners.*;
import org.bukkit.command.*;
import net.iienderr.withdraw.commands.commands.*;
import org.bukkit.plugin.*;

public class iiEnderrWithdraw extends JavaPlugin
{
    public static Economy econ;
    private static CommandHandler commandHandler;
    private static JavaPlugin plugin;
    public final DecimalFormat format;

    public iiEnderrWithdraw() {
        this.format = new DecimalFormat("###,###");
    }

    public static JavaPlugin getPlugin() {
        return iiEnderrWithdraw.plugin;
    }

    public static CommandHandler getCommandHandler() {
        return iiEnderrWithdraw.commandHandler;
    }

    public void onEnable() {
        iiEnderrWithdraw.plugin = this;
        if (!this.setupEconomy()) {
            this.getLogger().severe(String.format("[%s] Disabled due to no Vault dependency found!", this.getName()));
            Bukkit.getServer().getPluginManager().disablePlugin((Plugin)this);
            return;
        }
        final ConfigManager configManager = new ConfigManager(this.getConfig(), this);
        configManager.setupConfig();
        ItemUtil.setupMaterials();
        SoundUtil.setupSounds();
        Bukkit.getPluginManager().registerEvents((Listener)new PlayerInteractListener(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new CommandListener(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new DispenseListener(), (Plugin)this);
        iiEnderrWithdraw.commandHandler = new CommandHandler();
        this.getCommand("xpBottle").setExecutor((CommandExecutor)iiEnderrWithdraw.commandHandler);
        this.getCommand("withdrawMoney").setExecutor((CommandExecutor)iiEnderrWithdraw.commandHandler);
        this.getCommand("withdrawMcmmo").setExecutor((CommandExecutor)iiEnderrWithdraw.commandHandler);
        this.getCommand("givevoucher").setExecutor((CommandExecutor)iiEnderrWithdraw.commandHandler);
        this.getCommand("exp").setExecutor((CommandExecutor)new Exp());
        iiEnderrWithdraw.commandHandler.setup();
    }

    public void onDisable() {
    }

    private void log(final String msg) {
        final String prefix = String.format("[%s] ", this.getDescription().getName());
        this.getLogger().info(prefix + msg);
    }

    private boolean setupEconomy() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        final RegisteredServiceProvider<Economy> rsp = (RegisteredServiceProvider<Economy>)Bukkit.getServer().getServicesManager().getRegistration((Class)Economy.class);
        if (rsp == null) {
            return false;
        }
        iiEnderrWithdraw.econ = (Economy)rsp.getProvider();
        return iiEnderrWithdraw.econ != null;
    }

    static {
        iiEnderrWithdraw.econ = null;
    }
}
