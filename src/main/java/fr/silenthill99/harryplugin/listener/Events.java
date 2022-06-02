package fr.silenthill99.harryplugin.listener;

import fr.silenthill99.harryplugin.AdminOptionHolder;
import fr.silenthill99.harryplugin.Panel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Events implements Listener
{
    @EventHandler
    public void onClick(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();
        Inventory inv = event.getClickedInventory();
        if (event.getView().getTitle().equals("Choisissez un joueur"))
        {
            event.setCancelled(true);
            if (current.getType() == Material.PLAYER_HEAD) {
                Player target = Bukkit.getPlayer(current.getItemMeta().getDisplayName());
                Panel.panel_modo(player, target);
            }
        }
        else if (event.getView().getTitle().startsWith("Menu de "))
        {
            AdminOptionHolder holder = (AdminOptionHolder) inv.getHolder();
            OfflinePlayer target = holder.getPlayer();
            event.setCancelled(true);
            switch (current.getType())
            {
                case GREEN_WOOL:
                    Panel.panel_modo(player, target, "sanctionner");
                    break;
                case LAPIS_LAZULI:
                    Panel.options(player, target);
                    break;
                default:
                    break;
            }
        }
        else if (event.getView().getTitle().startsWith("Sanctionner "))
        {
            AdminOptionHolder holder = (AdminOptionHolder) inv.getHolder();
            OfflinePlayer target = holder.getPlayer();
            event.setCancelled(true);
            switch (current.getType())
            {
                case SUNFLOWER:
                    Panel.panel_modo(player, target);
                    break;
                case GREEN_WOOL:
                    if (current.getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "Avertir"))
                    {
                        if (!Panel.titre().equalsIgnoreCase("avertir")) {
                            Panel.panel_modo(player, target, "avertir");
                        }
                    }
                    break;
                case ORANGE_WOOL:
                    if (current.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Bannir temporairement"))
                    {
                        if (!Panel.titre().equalsIgnoreCase("Bannir temporairement"))
                        {
                            Panel.panel_modo(player, target, "Bannir temporairement");
                        }
                    }
                    break;
                case RED_WOOL:
                    if (current.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "Bannir"))
                    {
                        if (!Panel.titre().equalsIgnoreCase("Bannir"))
                        {
                            Panel.panel_modo(player, target, "Bannir");
                        }
                    }
                    break;
                case PURPLE_WOOL:
                    if (current.getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Kick"))
                    {
                        if (!Panel.titre().equalsIgnoreCase("Kick"))
                        {
                            Panel.panel_modo(player, target, "Kick");
                        }
                    }
                    break;
                case LIGHT_BLUE_WOOL:
                    if (current.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Freeze"))
                    {
                        if (!Panel.titre().equalsIgnoreCase("Freeze"))
                        {
                            Panel.panel_modo(player, target, "Freeze");
                        }
                    }
                    break;
                case PINK_WOOL:
                    if (current.getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE + "TempMute"))
                    {
                        if (!Panel.titre().equalsIgnoreCase("TempMute"))
                        {
                            Panel.panel_modo(player, target, "TempMute");
                        }
                    }
                case MAGENTA_WOOL:
                    if (current.getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE + "Mute"))
                    {
                        if (!Panel.titre().equalsIgnoreCase("Mute"))
                        {
                            Panel.panel_modo(player, target, "Mute");
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        else if (event.getView().getTitle().equals("Options"))
        {
            AdminOptionHolder holder = (AdminOptionHolder) inv.getHolder();
            OfflinePlayer target = holder.getPlayer();
            event.setCancelled(true);
            switch (current.getType())
            {
                case SUNFLOWER:
                    Panel.panel_modo(player, target);
                    break;
                case ENDER_PEARL:
                    if (target.isOnline())
                    {
                        player.teleport(target.getPlayer().getLocation());
                        player.sendMessage(ChatColor.GREEN + "Vous avez été téléporté à " + target.getName());
                    }
                    else
                    {
                        player.sendMessage(ChatColor.RED + "Ce joueur n'est pas connecté ou n'existe pas !");
                    }
                    break;
                case ENDER_EYE:
                    if (target.isOnline())
                    {
                        target.getPlayer().teleport(player.getLocation());
                        player.sendMessage(ChatColor.GREEN + target.getName() + " a été téléporté à votre position");
                    }
                    else
                    {
                        player.sendMessage(ChatColor.RED + "Ce joueur n'est pas connecté ou n'existe pas !");
                    }
                    break;
                case CHEST:
                    Panel.inventaire(player, target);
                    break;
                default:
                    break;
            }
        }
        else if (event.getView().getTitle().startsWith("Inventaire de "))
        {
            event.setCancelled(true);
        }
    }
}
