package net.iienderr.withdraw.commands;

import org.bukkit.command.*;
import net.iienderr.withdraw.util.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.plugin.*;
import net.iienderr.withdraw.commands.commands.*;

public class CommandHandler implements CommandExecutor
{
    private ArrayList<WithdrawCommand> commands;

    public CommandHandler() {
        this.commands = new ArrayList<WithdrawCommand>();
    }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String[] array = new String[0];
        final int[] length = new int[1];
        final int[] i = {0};
        final String[] message = new String[1];
        final String[] array2 = new String[0];
        final int[] length2 = new int[1];
        final int[] j = {0};
        final String[] message2 = new String[1];
        this.commands.stream().filter(command -> command.getName().equalsIgnoreCase(cmd.getName())).forEach(command -> {
            if (!sender.hasPermission("globewithdraw." + command.getName().toLowerCase())) {
                ConfigManager.getString("NO_PERMISSION", "commandMessages").split("%");
                for (length[0] = array.length; i[0] < length[0]; ++i[0]) {
                    message[0] = array[i[0]];
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message[0]));
                }
                return;
            }
            else {
                if (!command.execute(sender, args)) {
                    command.getUsage().split("%");
                    for (length2[0] = array2.length; j[0] < length2[0]; ++j[0]) {
                        message2[0] = array2[j[0]];
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message2[0]));
                    }
                }
                return;
            }
        });
        return true;
    }

    public void setup() {
        this.commands.add(new XpBottle());
        this.commands.add(new WithdrawMoney());
        this.commands.add(new GetVoucher());
        Optional.ofNullable(Bukkit.getPluginManager().getPlugin("ClaimLevels")).filter(Plugin::isEnabled).ifPresent(ignored -> this.commands.add(new WithdrawMcmmo()));
    }
}