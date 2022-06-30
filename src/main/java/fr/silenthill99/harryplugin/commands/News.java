package fr.silenthill99.harryplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class News implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage("Cette commande ne peut pas être éxécutée par la console !");
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0)
        {
            player.sendMessage(ChatColor.RED + "Veuillez faire /news <message>");
            return false;
        }

        StringBuilder message = new StringBuilder();

        for (String part : args)
        {
            message.append(part + " ");
        }

        for (Player players : Bukkit.getOnlinePlayers())
        {
            players.sendMessage(ChatColor.RED + "[News de La gazette du sorcier] " + ChatColor.YELLOW + "par " + player.getName() + " : " + ChatColor.GREEN + message);
        }

        return false;
    }
}
