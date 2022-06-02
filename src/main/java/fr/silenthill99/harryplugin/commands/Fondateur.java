package fr.silenthill99.harryplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Fondateur implements CommandExecutor
{
    private ArrayList<UUID> fondateur = new ArrayList<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage("Cette commande ne peut pas être exécutée par la console !");
            return false;
        }

        Player player = (Player) sender;

        if (args.length >= 2)
        {
            player.sendMessage(ChatColor.RED + "Cette commande a trop d'arguments !");
            return false;
        }

        if ((args.length == 0 || args[0].equalsIgnoreCase("on")) && !fondateur.contains(player.getUniqueId()))
        {
            fondateur.add(player.getUniqueId());
            player.sendMessage(ChatColor.GREEN + "Vous êtes désormais en mode fondateur !");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set fondateur");
            return false;
        }

        if ((args.length == 0 || args[0].equalsIgnoreCase("off")) && fondateur.contains(player.getUniqueId()))
        {
            fondateur.remove(player.getUniqueId());
            player.sendMessage(ChatColor.RED + "Vous avez quitté le mode fondateur !");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set default");
            return false;
        }

        if (!args[0].equalsIgnoreCase("on") && !args[0].equalsIgnoreCase("off"))
        {
            player.sendMessage(ChatColor.RED + "\"" + args[0] + "\" n'est pas un argument valable !");
            return false;
        }

        if (args[0].equalsIgnoreCase("on") && fondateur.contains(player.getUniqueId()))
        {
            player.sendMessage(ChatColor.RED + "requête annulée, vous êtes déjà en mode fondateur !");
        }
        else if (args[0].equalsIgnoreCase("off") && !fondateur.contains(player.getUniqueId()))
        {
            player.sendMessage(ChatColor.RED + "requête annulée, vous n'êtes pas encore en mode fondateur !");
        }
        return false;
    }
}
