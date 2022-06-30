package fr.silenthill99.harryplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Moderateur implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage("Cette commande ne peut pas être exécutée par la console !");
            return false;
        }

        Player player = (Player) sender;

        if (args.length != 1)
        {
            player.sendMessage(ChatColor.RED + "Erreur ! Veuillez faire /moderateur <on|off>");
            return false;
        }

        if (!args[0].equalsIgnoreCase("on") && !args[0].equalsIgnoreCase("off"))
        {
            player.sendMessage(ChatColor.RED + "\"" + args[0] + "\" n'est pas un argument valable !");
            return false;
        }

        if (args[0].equalsIgnoreCase("on"))
        {
            player.sendMessage(ChatColor.GREEN + "Vous êtes désormais en mode modérateur !");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set moderateur");
        }

        if (args[0].equalsIgnoreCase("off"))
        {
            player.sendMessage(ChatColor.RED + "Vous avez quitté le mode stagiaire !");
            Bukkit.dispatchCommand(player, "fly off");
            Bukkit.dispatchCommand(player, "god off");
            Bukkit.dispatchCommand(player, "vanish off");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set default");
        }
        return false;
    }
}
