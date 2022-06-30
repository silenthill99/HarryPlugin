package fr.silenthill99.harryplugin;

import fr.silenthill99.harryplugin.commands.*;
import fr.silenthill99.harryplugin.listener.Events;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

import java.util.TimerTask;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        LuckPerms api = LuckPermsProvider.get();

        getLogger().info("Le plugin est opérationnel !");
        commands();
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new Events(), this);
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
        getCommand("moderateur").setExecutor(new Moderateur());
        getCommand("stagiaire").setExecutor(new Stagiaire());
        getCommand("stagiaire").setTabCompleter(new StaffTab());
        getCommand("carte").setExecutor(new Carte());
        getCommand("discord").setExecutor(new Discord());
        getCommand("news").setExecutor(new News());
        getCommand("hrp").setExecutor(new Hrp());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
    }
}
