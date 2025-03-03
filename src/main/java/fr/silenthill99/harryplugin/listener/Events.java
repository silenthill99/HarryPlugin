package fr.silenthill99.harryplugin.listener;

import fr.silenthill99.harryplugin.CustomFiles;
import fr.silenthill99.harryplugin.Items;
import fr.silenthill99.harryplugin.Main;
import fr.silenthill99.harryplugin.Tchat;
import fr.silenthill99.harryplugin.mysql.RequestSQL;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class Events implements Listener
{
    Main main = Main.getInstance();
    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws IOException {
        Player player = event.getPlayer();

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
    public void onDeath(PlayerDeathEvent event) {
        event.setKeepInventory(true);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (!Objects.equals(event.getHand(), EquipmentSlot.HAND)) return;
        if (!event.hasBlock()) return;

        Block block = event.getClickedBlock();

        assert block != null;
        if(block.getType().equals(Material.FIRE)) {
            assert item != null;
            if (item.getType().equals(Material.GUNPOWDER)) {
                player.sendMessage("Poudre de cheminette");
            }
        }
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();

        if (!event.getHand().equals(EquipmentSlot.HAND)) return;

        if (entity instanceof Player) {
            Player target = (Player) entity;
            if (player.isOp() && target.hasMetadata("NPC")) {
                TextComponent message = new TextComponent(target.getUniqueId().toString());

                message.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, target.getUniqueId().toString()));
                message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Clique ici pour copier l'UUID")));

                player.sendMessage(ChatColor.AQUA + "UUID pour les développeurs :");
                player.spigot().sendMessage(message);
            }

            if (target.getUniqueId().equals(UUID.fromString("610656c6-9f84-2110-a81d-d20cd0579bd8"))) {
                player.sendMessage(ChatColor.GREEN + "Bonjour " + player.getName());
            }
        }
    }

    @EventHandler
    public void onLoad(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        main.getLogger().info(String.valueOf(RequestSQL.isBlacklist(player.getUniqueId())));
        if (RequestSQL.isBlacklist(player.getUniqueId())) {
            if (player.isOp()) {
                player.setOp(false);
            }
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission clear");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set default");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ipban " + player.getName() + " " + RequestSQL.getReason(player.getUniqueId()));
        }
    }
}
