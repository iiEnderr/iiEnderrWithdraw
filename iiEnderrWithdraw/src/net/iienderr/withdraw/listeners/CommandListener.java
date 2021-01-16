package net.iienderr.withdraw.listeners;

import net.iienderr.withdraw.util.*;
import org.bukkit.event.player.*;
import net.iienderr.withdraw.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class CommandListener implements Listener
{
    private String moneyCommand;
    private String expCommand;
    private String mcmmoCommand;

    public CommandListener() {
        this.moneyCommand = ConfigManager.getString("MONEY_WITHDRAW_COMMAND", "commands");
        this.expCommand = ConfigManager.getString("EXP_WITHDRAW_COMMAND", "commands");
        this.mcmmoCommand = ConfigManager.getString("MCMMO_WITHDRAW_COMMAND", "commands");
    }

    @EventHandler
    public void onPlayerCommandPreprocess(final PlayerCommandPreprocessEvent e) {
        final Player player = e.getPlayer();
        final String command = e.getMessage().split(" ")[0];
        final String[] cmd = e.getMessage().split(" ");
        String[] args;
        if (cmd.length == 1) {
            args = new String[0];
        }
        else {
            args = new String[] { cmd[1] };
        }
        if (command.equalsIgnoreCase(this.moneyCommand)) {
            iiEnderrWithdraw.getCommandHandler().onCommand((CommandSender)player, (Command)iiEnderrWithdraw.getPlugin().getCommand("withdrawMoney"), null, args);
            e.setCancelled(true);
        }
        else if (command.equalsIgnoreCase(this.expCommand)) {
            iiEnderrWithdraw.getCommandHandler().onCommand((CommandSender)player, (Command)iiEnderrWithdraw.getPlugin().getCommand("xpBottle"), null, args);
            e.setCancelled(true);
        }
        else if (command.equalsIgnoreCase(this.mcmmoCommand)) {
            iiEnderrWithdraw.getCommandHandler().onCommand((CommandSender)player, (Command)iiEnderrWithdraw.getPlugin().getCommand("withdrawMcmmo"), null, args);
            e.setCancelled(true);
        }
    }
}
