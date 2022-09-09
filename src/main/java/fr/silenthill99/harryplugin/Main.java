package fr.silenthill99.harryplugin;

import fr.silenthill99.harryplugin.commands.*;
import fr.silenthill99.harryplugin.inventory.InventoryManager;
import fr.silenthill99.harryplugin.listener.Events;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin {

    public HashMap<UUID, ArrayList<String>> logs = new HashMap<>();

    private static Main instance;

    public static Main getInstance()
    {
        return instance;
    }

    @Override
    public void onEnable() {

        instance = this;
        getLogger().info("Le plugin est opérationnel !");
        commands();
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new Events(), this);
        pm.registerEvents(new InventoryManager(), this);
        /*
        Bukkit.getScheduler().runTaskTimer(this, new TimerTask() {
            @Override
            public void run() {
                ScoreboardManager manager = Bukkit.getScoreboardManager();
                Scoreboard b = manager.getNewScoreboard();
                for (Player players : Bukkit.getOnlinePlayers())
                {
                    User user = api.getUserManager().getUser(players.getName());

                    Objective o = b.registerNewObjective("Gold", "");
                    o.setDisplaySlot(DisplaySlot.SIDEBAR);
                    o.setDisplayName(ChatColor.BOLD + "Harry" + ChatColor.GOLD + "" + ChatColor.BOLD + "PoCraft");

                    Score pseudo = o.getScore("Pseudo : " + players.getName());
                    Score espace = o.getScore("");
                    Score grade = o.getScore(ChatColor.WHITE + "Grade : " + user.getCachedData().getMetaData().getPrefix().replace("&", "§"));

                    pseudo.setScore(3);
                    espace.setScore(2);
                    grade.setScore(1);

                    players.setScoreboard(b);
                }
            }
        }, 0, 20);

         */
    }

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
        getCommand("carte").setExecutor(new Carte());
        getCommand("discord").setExecutor(new Discord());
        getCommand("news").setExecutor(new News());
        getCommand("hrp").setExecutor(new Hrp());
        getCommand("logs").setExecutor(new Logs());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
    }
}
