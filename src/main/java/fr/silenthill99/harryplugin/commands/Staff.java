package fr.silenthill99.harryplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Staff implements CommandExecutor
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
            player.sendMessage(ChatColor.RED + "Veuillez faire /stafftchat <message>");
            return false;
        }

        StringBuilder bc = new StringBuilder();

        for (String part : args)
        {
            bc.append(part + " ");
        }

        for (Player players : Bukkit.getOnlinePlayers())
        {
            if (player.hasPermission("harrypocraft.staff"))
            {
                players.sendMessage(ChatColor.RED + "[TCHAT STAFF] " + ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " >> " + ChatColor.GREEN + bc);
            }
        }

        return false;
    }
}
