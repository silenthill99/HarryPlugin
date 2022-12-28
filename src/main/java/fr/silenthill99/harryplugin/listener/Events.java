package fr.silenthill99.harryplugin.listener;

import fr.silenthill99.harryplugin.Main;
import fr.silenthill99.harryplugin.inventory.AbstractInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Events implements Listener
{
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
        player.getInventory().setItem(4, AbstractInventory.CARTE_DU_MARAUDEUR);
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
