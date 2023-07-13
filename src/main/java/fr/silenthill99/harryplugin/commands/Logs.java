package fr.silenthill99.harryplugin.commands;

import fr.silenthill99.harryplugin.CustomFiles;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Logs implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args)
    {
        if (args.length != 1)
        {
            sender.sendMessage(ChatColor.RED + "Veuillez faire /logs <joueur>");
            return false;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

        if (!CustomFiles.LOGS.getConfig().contains(target.getName() + ".logs"))
        {
            sender.sendMessage(ChatColor.RED + "Ce joueur n'a pas de logs");
            return false;
        }

        for (String logs : CustomFiles.LOGS.getConfig().getStringList(target.getName() + ".logs"))
        {
            sender.sendMessage(logs);
        }

        return false;
    }
}
