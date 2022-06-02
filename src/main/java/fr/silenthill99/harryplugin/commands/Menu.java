package fr.silenthill99.harryplugin.commands;

import fr.silenthill99.harryplugin.ItemBuilder;
import fr.silenthill99.harryplugin.Panel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Menu implements CommandExecutor {
    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (!(sender instanceof Player))
        {
            sender.sendMessage("Cette commande ne peut pas être éxécutée par la console !");
            return false;
        }

        Player player = (Player) sender;
        if (args.length == 0)
        {
            int slot = 0;
            Inventory choix = Bukkit.createInventory(null, 54, "Choisissez un joueur");
            for (Player players : Bukkit.getOnlinePlayers())
            {
                if (Bukkit.getOnlinePlayers().size() <= 54)
                {
                    ItemBuilder tete = new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(players.getName()).setName(players.getName());
                    choix.setItem(slot, tete.toItemStack());
                    slot++;
                }
                else
                {
                    player.sendMessage(ChatColor.RED + "Il y a trop de joueurs en ligne. Veuillez faire /menu <joueur>");
                }
            }
            player.openInventory(choix);
            return false;
        }
        if (args.length >= 2)
        {
            player.sendMessage(ChatColor.RED + "Vous avez mis trop d'arguments !");
            return false;
        }
        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
        Panel.panel_modo(player, target);
        return false;
    }
}
