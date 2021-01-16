package net.iienderr.withdraw.commands;

import org.bukkit.command.*;

public interface WithdrawCommand
{
    boolean execute(final CommandSender p0, final String[] p1);

    String getName();

    String getUsage();
}
