package fr.silenthill99.harryplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class Tchat implements Runnable {
    private Player player;
    private int timer;
    private ArmorStand stand;
    private final BukkitTask task;

    private Main main = Main.getInstance();
    public Tchat(Player player, ArmorStand stand)
    {
        this.player = player;
        this.stand = stand;
        this.task = Bukkit.getScheduler().runTaskTimer(main, this, 0, 1);
    }
    @Override
    public void run() {
        timer++;
        stand.teleport(player.getLocation());
        if (timer == 100)
        {
            stand.damage(stand.getHealth());
            task.cancel();
        }
    }
}
