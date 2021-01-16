package net.iienderr.withdraw.commands.commands;

import net.iienderr.withdraw.commands.*;
import java.text.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import net.iienderr.withdraw.*;
import net.iienderr.withdraw.util.*;

public class Exp extends CommandHandler
{
    public final DecimalFormat format;

    public Exp() {
        this.format = new DecimalFormat("###,###.##");
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        this.readCommand((Player)sender, commandLabel);
        return false;
    }

    public void readCommand(final Player player, final String command) {
        if (command.equalsIgnoreCase("exp") || command.equalsIgnoreCase("xp")) {
            player.sendMessage(net.iienderr.withdraw.util.MessageUtils.translateColorCodes(iiEnderrWithdraw.getPlugin().getConfig().getString("commandMessages.EXP_BAL").replace("{amount}", this.format.format(new net.iienderr.withdraw.util.XPUtil(player).getTotalExperience()))));
        }
    }
}