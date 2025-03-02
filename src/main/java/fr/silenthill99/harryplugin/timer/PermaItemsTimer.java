package fr.silenthill99.harryplugin.timer;

import fr.silenthill99.harryplugin.Items;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PermaItemsTimer extends BukkitRunnable {
    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getInventory().setItem(4, Items.CARTE_DU_MARAUDEUR.getItem());
        }
    }
}