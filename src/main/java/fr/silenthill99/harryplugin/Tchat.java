package fr.silenthill99.harryplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class Tchat implements Runnable {
    private final Player player;
    private final ArmorStand stand;
    private final BukkitTask task;

    public Tchat(Player player, ArmorStand stand)
    {
        this.player = player;
        this.stand = stand;
        Main main = Main.getInstance();
        this.task = Bukkit.getScheduler().runTaskTimer(main, this, 0, 1);
    }

    int timer = 0;

    @Override
    public void run() {
        timer++;
        stand.teleport(player.getLocation());
        if (timer == 100)
        {
            stand.remove();
            task.cancel();
        }
    }
}
