package fr.silenthill99.harryplugin.listener;

import fr.silenthill99.harryplugin.AdminOptionHolder;
import fr.silenthill99.harryplugin.Main;
import fr.silenthill99.harryplugin.Panel;
import fr.silenthill99.harryplugin.inventory.InventoryManager;
import fr.silenthill99.harryplugin.inventory.InventoryType;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Events implements Listener
{
    @EventHandler
    public void onClick(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();
        Inventory inv = event.getClickedInventory();
        if (event.getView().getTitle().equals("Options"))
        {
            AdminOptionHolder holder = (AdminOptionHolder) inv.getHolder();
            OfflinePlayer target = holder.getPlayer();
            event.setCancelled(true);
            switch (current.getType())
            {
                case SUNFLOWER:
                    InventoryManager.openInventory(player, InventoryType.MODO_PLAYER_MENU, target);
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
                    if (target.isOnline())
                    {
                        Panel.inventaire(player, target);
                    }
                    else
                    {
                        player.sendMessage(ChatColor.RED + "Ce joueur n'est pas connecté ou n'existe pas !");
                    }
                    break;
                case RED_DYE:
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "check " + target.getName());
                    break;
                case LAPIS_LAZULI:
                {
                    player.closeInventory();
                    for (String logs : Main.getInstance().logs.get(target.getUniqueId()))
                    {
                        player.sendMessage(logs);
                    }
                    break;
                }
                default:
                    break;
            }
        }
        else if (event.getView().getTitle().startsWith("Inventaire de "))
        {
            AdminOptionHolder holder = (AdminOptionHolder) inv.getHolder();
            OfflinePlayer target = holder.getPlayer();
            event.setCancelled(true);
        }
        else if (event.getView().getTitle().equalsIgnoreCase("Administration"))
        {
            AdminOptionHolder holder = (AdminOptionHolder) inv.getHolder();
            OfflinePlayer target = holder.getPlayer();
            event.setCancelled(true);
            switch (current.getType())
            {
                case NETHER_STAR:
                    InventoryManager.openInventory(player, InventoryType.MODO_PLAYER_MENU, target);
                    break;
                case REDSTONE:
                    Panel.panel_admin(player, target, "sanctions");
                    break;
                case RED_CONCRETE:
                    if (!Main.isPlayerInGroup(player, "fondateur"))
                    {
                        player.sendMessage(ChatColor.DARK_RED + "Tu n'as pas accès à ceci !");
                        return;
                    }
                    Panel.panel_direction(player, target, "Direction");
                    break;
                default:
                    break;
            }
        }
        else if (event.getView().getTitle().equalsIgnoreCase("Menu direction"))
        {
            AdminOptionHolder holder = (AdminOptionHolder) inv.getHolder();
            OfflinePlayer target = holder.getPlayer();
            event.setCancelled(true);
            switch (current.getType())
            {
                case SUNFLOWER:
                    Panel.panel_admin(player, target);
                    break;
                case REDSTONE:
                    Panel.panel_direction(player, target, "Erreurs staff");
                    break;
                case BOOK:
                    Panel.panel_direction(player, target, "RankUp");
                    break;
                default:
                    break;
            }
        }
        else if (event.getView().getTitle().startsWith("Erreurs staff : "))
        {
            AdminOptionHolder holder = (AdminOptionHolder) inv.getHolder();
            OfflinePlayer target = holder.getPlayer();
            event.setCancelled(true);
            switch (current.getType())
            {
                case REDSTONE:
                    if (current.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "Fly Sans Vanish"))
                    {
                        player.closeInventory();
                        Bukkit.dispatchCommand(player, "warn " + target.getName() + " Erreur staff : Fly sans vanish");
                    }
                    else if(current.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "God en WZ"))
                    {
                        player.closeInventory();
                        Bukkit.dispatchCommand(player, "warn " + target.getName() + " Erreur staff : God en WarZone");
                    }
                    else if (current.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "Abus de pouvoir"))
                    {
                        player.closeInventory();
                        Bukkit.dispatchCommand(player, "warn " + target.getName() + " Erreur staff : Abus de pouvoir");
                    }
                    else if (current.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "Abus de permissions"))
                    {
                        player.closeInventory();
                        Bukkit.dispatchCommand(player, "warn " + target.getName() + " Erreur staff : Abus de permissions");
                    }
                    else if (current.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "Non respect de la hiérarchie"))
                    {
                        player.closeInventory();
                        Bukkit.dispatchCommand(player, "warn " + target.getName() + " Erreur staff : Non respect de la hiérarchie");
                    }
                    else if (current.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "Non respect | Règlement staff"))
                    {
                        player.closeInventory();
                        Bukkit.dispatchCommand(player, "warn " + target.getName() + " Erreur staff : Non respect de la règlementation staff");
                    }
                    else if (current.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + " FreeWarn"))
                    {
                        player.closeInventory();
                        Bukkit.dispatchCommand(player, "warn " + target.getName() + " Erreur staff : FreeWarn");
                    }
                    else if (current.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "FreeBan"))
                    {
                        player.closeInventory();
                        Bukkit.dispatchCommand(player, "warn " + target.getName() + "Erreur staff : Freeban");
                    }
                    else if (current.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "Abscence non justifiée"))
                    {
                        player.closeInventory();
                        Bukkit.dispatchCommand(player, "warn " + target.getName() + "Erreur staff : Abscence non justifiée");
                    }
                    else if (current.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "Favoritisme"))
                    {
                        player.closeInventory();
                        Bukkit.dispatchCommand(player, "warn " + target.getName() + " Erreur staff : Favoritisme");
                    }
                    break;
                case SUNFLOWER:
                    Panel.panel_direction(player, target, "Direction");
                    break;
                default:
                    break;
            }
        }
        else if (event.getView().getTitle().startsWith("Carte du maraudeur"))
        {
            event.setCancelled(true);
            switch (current.getType())
            {
                case FILLED_MAP:
                    if (current.getItemMeta().getDisplayName().equals("Bureau de Dumbledore"))
                    {
                        Location bureau = new Location(player.getWorld(), -1985.5, 195, 430.5);
                        player.teleport(bureau);
                    }
                    else if (current.getItemMeta().getDisplayName().equals("Terrain de Quidditch"))
                    {
                        Location quidditch = new Location(player.getWorld(), -1731.5, 83, 722.5);
                        player.teleport(quidditch);
                    }
                    else if (current.getItemMeta().getDisplayName().equals("Grande salle"))
                    {
                        Location grande_salle = new Location(player.getWorld(), -2007.5, 121, 393.5);
                        player.teleport(grande_salle);
                    }
                    else if (current.getItemMeta().getDisplayName().equals("Salle commune de Gryffondor"))
                    {
                        Location gryffondor = new Location(player.getWorld(), -1940.5, 133, 478.5);
                        player.teleport(gryffondor);
                    }
                    else if (current.getItemMeta().getDisplayName().equals("Toilettes de Mimi Geignarde"))
                    {
                        Location toilettes = new Location(player.getWorld(), -1978.5, 123, 479.5);
                        player.teleport(toilettes);
                    }
                    else if (current.getItemMeta().getDisplayName().equals("Infirmerie"))
                    {
                        Location infirmerie = new Location(player.getWorld(), -1942.5, 155, 456.5);
                        player.teleport(infirmerie);
                    }
                    else if (current.getItemMeta().getDisplayName().equals("Pont couvert"))
                    {
                        Location pont = new Location(player.getWorld(), -1864.5, 124, 454.5);
                        player.teleport(pont);
                    }
                    else if (current.getItemMeta().getDisplayName().equals("Salle commune de Serpentard"))
                    {
                        Location serpentard = new Location(player.getWorld(), -2114.5, 6, 486.5);
                        player.teleport(serpentard);
                    }
                    else if (current.getItemMeta().getDisplayName().equals("Cabane d'Hagrid"))
                    {
                        Location hagrid = new Location(player.getWorld(), -1717.5, 73, 373.5);
                        player.teleport(hagrid);
                    }
                    else if (current.getItemMeta().getDisplayName().equals("Bibliothèque"))
                    {
                        Location bibliotheque = new Location(player.getWorld(), -1963.5, 134, 534.5);
                        player.teleport(bibliotheque);
                    }
                    else if (current.getItemMeta().getDisplayName().equals("Tombe de Dumbledore"))
                    {
                        Location tombe = new Location(player.getWorld(), -2079.5, 55, 267.5);
                        player.teleport(tombe);
                    }
                    else if (current.getItemMeta().getDisplayName().equals("Grands escaliers"))
                    {
                        Location escaliers = new Location(player.getWorld(), -1994.5, 130, 416.5);
                        player.teleport(escaliers);
                    }
                    else if (current.getItemMeta().getDisplayName().equals("Saule Cogneur"))
                    {
                        Location saule_cogneur = new Location(player.getWorld(), -1944.5, 121, 661.5);
                        player.teleport(saule_cogneur);
                    }
                    else if (current.getItemMeta().getDisplayName().equals("Tour d'astronomie"))
                    {
                        Location tour_dastronomie = new Location(player.getWorld(), -1998.5, 205, 545.5);
                        player.teleport(tour_dastronomie);
                    }
                    else if (current.getItemMeta().getDisplayName().equals("Cour de métamorphose"))
                    {
                        Location cour_de_metamorphose = new Location(player.getWorld(), -1987.5, 121, 582.5);
                        player.teleport(cour_de_metamorphose);
                    }
                    else if (current.getItemMeta().getDisplayName().equals("Chambre des secrets"))
                    {
                        Location chambre = new Location(player.getWorld(), -1901.5, 39, 520.5);
                        player.teleport(chambre);
                    }
                    else if (current.getItemMeta().getDisplayName().equals("Salle de métamorphoses"))
                    {
                        Location metamorphoses = new Location(player.getWorld(), -2025.5, 122, 583.5, 90f, 0f);
                        player.teleport(metamorphoses);
                    }
                    break;
                default:
                    break;
            }
        }
        else if (event.getView().getTitle().startsWith("RankUp | " ))
        {
            AdminOptionHolder holder = (AdminOptionHolder) inv.getHolder();
            OfflinePlayer target = holder.getPlayer();
            event.setCancelled(true);
            switch (current.getType())
            {
                case SUNFLOWER:
                    Panel.panel_direction(player, target, "Direction");
                    break;
                case RED_DYE:
                    if (current.getItemMeta().getDisplayName().equals(ChatColor.RED + "Mettre op"))
                    {
                        target.setOp(true);
                        player.sendMessage(ChatColor.GREEN + target.getName() + " est désormais opérateur !");
                        try
                        {
                            target.getPlayer().sendMessage(ChatColor.GREEN + "Vous êtes désormais opérateur du serveur !");
                        }
                        catch (NullPointerException e)
                        {
                        }
                    }
                    else if (current.getItemMeta().getDisplayName().equals(ChatColor.RED + "DeOp le joueur"))
                    {
                        target.setOp(false);
                        player.sendMessage(ChatColor.RED + target.getName() + " n'est plus opérateur du serveur");
                        try
                        {
                            target.getPlayer().sendMessage(ChatColor.RED + "Vous n'êtes plus opérateur du serveur !");
                        }
                        catch (NullPointerException e)
                        {
                        }
                    }
                    break;
                case LIME_WOOL:
                    player.closeInventory();
                    if (current.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "[Modérateur stagiaire]"))
                    {
                        Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission clear");
                        Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set modo-stagiaire");
                        Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission set harrypocraft.stagiaire");
                        if (target.isOnline())
                        {
                            target.getPlayer().sendMessage(ChatColor.GREEN + "Vous êtes désormais Modérateur stagiaire !");
                        }
                        player.sendMessage(ChatColor.GREEN + target.getName() + " est désormais Modérateur stagiaire !");
                    }
                    else if (current.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "[Modérateur]"))
                    {
                        Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission clear");
                        Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set moderateur");
                        Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission set harrypocraft.modo");
                        if (target.isOnline())
                        {
                            target.getPlayer().sendMessage(ChatColor.GREEN + "Suite au succès de votre période de stage, vous êtes désormais Modérateur !");
                        }
                        player.sendMessage(ChatColor.GREEN + target.getName() + " est désormais au rang de Modérateur !");
                    }
                    break;
                case LIGHT_BLUE_WOOL:
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set administrateur");
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission clear");
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission set harrypocraft.admin");
                    if (target.isOnline())
                    {
                        target.getPlayer().sendMessage(ChatColor.GREEN + "Vous êtes désormais Administrateur !");
                    }
                    player.sendMessage(ChatColor.GREEN + target.getName() + " est désormais Administrateur !");
                    break;
                case WRITABLE_BOOK:
                    Panel.panel_direction(player, target, "Grade Direction");
                    break;
                case PAPER:
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set default");
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission clear");
                    Bukkit.dispatchCommand(player, "kick " + target.getName() + " Unrank");
                    break;
                case YELLOW_WOOL:
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set builder");
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission clear");
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission set harrypocraft.builder");
                    if (target.isOnline()) target.getPlayer().sendMessage(ChatColor.GREEN + "Vous êtes désormais Builder !");
                    player.sendMessage(ChatColor.GREEN + target.getName() + " est désormais Builder !");
                    break;
                default:
                    break;
            }
        }
        else if (event.getView().getTitle().equals("Sanctions Administratives"))
        {
            AdminOptionHolder holder = (AdminOptionHolder) inv.getHolder();
            OfflinePlayer target = holder.getPlayer();
            event.setCancelled(true);
            switch (current.getType())
            {
                case SUNFLOWER:
                    Panel.panel_admin(player, target);
                    break;
                case REDSTONE:
                    if (current.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "Menace DDoS"))
                    {
                        player.closeInventory();
                        Bukkit.dispatchCommand(player, "ipban " + target.getName() + " Menace d'attaque DDoS");
                    }
                    else if (current.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "Contournement de sanctions / Double compte"))
                    {
                        player.closeInventory();
                        Bukkit.dispatchCommand(player, "ipban " + target.getName() + " Contournement de sanctions / Double compte");
                    }
                    break;
                default:
                    break;
            }
        }
        else if (event.getView().getTitle().equals("Grade Direction"))
        {
            AdminOptionHolder holder = (AdminOptionHolder) inv.getHolder();
            OfflinePlayer target = holder.getPlayer();
            event.setCancelled(true);
            switch (current.getType())
            {
                case SUNFLOWER:
                    Panel.panel_direction(player, target, "RankUp");
                    break;
                case RED_WOOL:
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set fondateur");
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission clear");
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission set harrypocraft.fondateur");
                    if (target.isOnline())
                    {
                        target.getPlayer().sendMessage(ChatColor.GREEN + "Vous êtes désormais Fondateur !");
                    }
                    player.sendMessage(ChatColor.GREEN + target.getName() + " est désormais Fondateur !");
                    break;
                default:
                    break;
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        event.setJoinMessage(ChatColor.AQUA + "[" + ChatColor.GREEN + "+" + ChatColor.AQUA + "] " + player.getName());
        if(!Main.getInstance().logs.containsKey(player.getUniqueId()))
        {
            Main.getInstance().logs.remove(player.getUniqueId());
        }
        ArrayList<String> list = new ArrayList<>();
        list.add(ChatColor.YELLOW + "[" + new Timestamp(System.currentTimeMillis()) + "] " + ChatColor.DARK_BLUE + player.getName() + ChatColor.BLUE + " s'est connecté(e)");
        Main.getInstance().logs.put(player.getUniqueId(), list);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event)
    {
        Player player = event.getPlayer();
        event.setQuitMessage(ChatColor.AQUA + "[" + ChatColor.RED + "-" + ChatColor.AQUA + "] " + player.getName());
        if (!player.getGameMode().equals(GameMode.ADVENTURE))
        {
            player.setGameMode(GameMode.ADVENTURE);
        }
        if (player.hasPermission("essentials.fly"))
        {
            Bukkit.dispatchCommand(player, "fly off");
        }
        if (player.hasPermission("essentials.god"))
        {
            Bukkit.dispatchCommand(player, "god off");
        }
        if (player.hasPermission("essentials.vanish"))
        {
            Bukkit.dispatchCommand(player, "vanish off");
        }
        Main.getInstance().logs.get(player.getUniqueId()).add(ChatColor.YELLOW + "[" + new Timestamp(System.currentTimeMillis()) + "] " + ChatColor.DARK_BLUE + player.getName() + ChatColor.BLUE + " s'est déconnecté(e)");
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event)
    {
        event.setCancelled(true);
        String message = event.getMessage();
        Player player = event.getPlayer();
        for (Player players : Bukkit.getOnlinePlayers())
        {
            if (players.getLocation().distanceSquared(player.getLocation()) <= 2500)
            {
                players.sendMessage(ChatColor.DARK_BLUE + player.getName() + " a dit : " + ChatColor.BLUE + message);
            }
        }
        Main.getInstance().logs.get(player.getUniqueId()).add(ChatColor.YELLOW + "[" + new Timestamp(System.currentTimeMillis()) + "] " + ChatColor.DARK_BLUE + player.getName() + " a dit " + ChatColor.BLUE + message);
    }
}
