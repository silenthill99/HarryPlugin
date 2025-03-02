package fr.silenthill99.harryplugin;

import fr.silenthill99.harryplugin.commands.*;
import fr.silenthill99.harryplugin.inventory.InventoryManager;
import fr.silenthill99.harryplugin.listener.Events;
import fr.silenthill99.harryplugin.timer.PermaItemsTimer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin {

    private static Main instance;

    public static Main getInstance()
    {
        return instance;
    }
    PermaItemsTimer timer;

    @Override
    public void onEnable() {

        instance = this;
        getLogger().info("Le plugin est opérationnel !");
        commands();
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new Events(), this);
        pm.registerEvents(new InventoryManager(), this);
        timer = new PermaItemsTimer();
        timer.runTaskTimer(this, 0, 1);
    }

    @SuppressWarnings("DataFlowIssue")
    private void commands()
    {
        getCommand("menu").setExecutor(new Menu());
        getCommand("stafftchat").setExecutor(new Staff());
        getCommand("fondateur").setExecutor(new Fondateur());
        getCommand("fondateur").setTabCompleter(new StaffTab());
        getCommand("administrateur").setExecutor(new Administrateur());
        getCommand("administrateur").setTabCompleter(new StaffTab());
        getCommand("moderateur").setExecutor(new Moderateur());
        getCommand("moderateur").setTabCompleter(new StaffTab());
        getCommand("stagiaire").setExecutor(new Stagiaire());
        getCommand("stagiaire").setTabCompleter(new StaffTab());
        getCommand("discord").setExecutor(new Discord());
        getCommand("news").setExecutor(new News());
        getCommand("hrp").setExecutor(new Hrp());
        getCommand("logs").setExecutor(new Logs());
        getCommand("worldname").setExecutor(new WorldName());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
    }

    public HashMap<UUID, Location> frozenPlayers = new HashMap<>();
}
