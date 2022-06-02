package fr.silenthill99.harryplugin;

import fr.silenthill99.harryplugin.commands.Fondateur;
import fr.silenthill99.harryplugin.commands.Menu;
import fr.silenthill99.harryplugin.commands.Staff;
import fr.silenthill99.harryplugin.commands.StaffTab;
import fr.silenthill99.harryplugin.listener.Events;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Le plugin est opérationnel !");
        commands();
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new Events(), this);
    }

    private void commands()
    {
        getCommand("menu").setExecutor(new Menu());
        getCommand("stafftchat").setExecutor(new Staff());
        getCommand("fondateur").setExecutor(new Fondateur());
        getCommand("fondateur").setTabCompleter(new StaffTab());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
