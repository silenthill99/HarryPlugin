package fr.silenthill99.harryplugin.commands;

import fr.silenthill99.harryplugin.Panel;
import fr.silenthill99.harryplugin.inventory.InventoryManager;
import fr.silenthill99.harryplugin.inventory.InventoryType;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Carte implements CommandExecutor
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

        if (args.length != 0)
        {
            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Erreur ! Cette commande n'as pas d'arguments !");
            return false;
        }

        InventoryManager.openInventory(player, InventoryType.CARTE_DU_MARAUDEUR);
        player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "je jure solennellement que mes intentions sont mauvaises");

        return false;
    }
}
