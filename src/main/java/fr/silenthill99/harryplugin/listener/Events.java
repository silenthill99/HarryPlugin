package fr.silenthill99.harryplugin.listener;

import fr.silenthill99.harryplugin.CustomFiles;
import fr.silenthill99.harryplugin.Items;
import fr.silenthill99.harryplugin.Main;
import fr.silenthill99.harryplugin.Tchat;
import fr.silenthill99.harryplugin.mysql.DbConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Events implements Listener
{
    Main main = Main.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws IOException {
        Player player = event.getPlayer();
        final DbConnection gradeConnection = main.getManager().getGradeConnection();

        Bukkit.getScheduler().runTaskAsynchronously(main, () -> {
            try {
                Connection connection = gradeConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT uuid, reason FROM staff_blacklist WHERE uuid = ?");
                preparedStatement.setString(1, player.getUniqueId().toString());
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next() && !player.isBanned()) {
                    if(player.isOp()) {
                        player.setOp(false);
                    }
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set default");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission clear");
                    String reason = resultSet.getString("reason");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ipban " + player.getName() + " " + reason);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        event.setJoinMessage(ChatColor.AQUA + "[" + ChatColor.GREEN + "+" + ChatColor.AQUA + "] " + player.getName());
        CustomFiles.LOGS.removeLogs(player);
        CustomFiles.LOGS.addLog(player, ChatColor.DARK_BLUE + player.getName() + ChatColor.BLUE + " s'est connecté(e)");
        player.getInventory().setItem(4, Items.CARTE_DU_MARAUDEUR.getItem());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) throws IOException {
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
        CustomFiles.LOGS.addLog(player, ChatColor.DARK_BLUE + player.getName() + ChatColor.BLUE + " s'est déconnecté(e)");
    }


    @SuppressWarnings("deprecation")
    @EventHandler
    public void onChat(PlayerChatEvent event) throws IOException {
        event.setCancelled(true);
        String message = event.getMessage();
        Player player = event.getPlayer();
        for (Player players : Bukkit.getOnlinePlayers())
        {
            if (players.getLocation().distanceSquared(player.getLocation()) <= 2500)
            {
                players.sendMessage(ChatColor.DARK_BLUE + player.getName() + " a dit : " + ChatColor.BLUE + message);
                CustomFiles.LOGS.addLog(players, ChatColor.DARK_BLUE + player.getName() + " a dit " + ChatColor.BLUE + message);
            }
        }
        ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
        stand.setCustomName(ChatColor.GOLD + player.getName() + " ► " + ChatColor.RESET + message);
        stand.setCustomNameVisible(true);
        stand.setInvulnerable(true);
        stand.setGravity(false);
        stand.setInvisible(true);
        new Tchat(player, stand);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event)
    {
        Player player = event.getPlayer();
        if (main.frozenPlayers.containsKey(player.getUniqueId()))
            event.setTo(event.getFrom());
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event)
    {
        Player player = event.getPlayer();
        ItemStack item = event.getItemDrop().getItemStack();
        if (item.equals(Items.CARTE_DU_MARAUDEUR.getItem()))
        {
            event.setCancelled(true);
            player.sendMessage(ChatColor.YELLOW + "Tu peux pas jeter cet item !");
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        if (item != null && item.equals(Items.CARTE_DU_MARAUDEUR.getItem())) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.YELLOW + "Tu peux pas changer de place cet item !");
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event)
    {
        Player player = event.getEntity();
        event.setKeepInventory(true);
    }
}
