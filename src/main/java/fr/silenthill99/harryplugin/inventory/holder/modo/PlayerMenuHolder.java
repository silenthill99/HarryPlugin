package fr.silenthill99.harryplugin.inventory.holder.modo;

import fr.silenthill99.harryplugin.inventory.SilenthillHolder;
import org.bukkit.OfflinePlayer;

public class PlayerMenuHolder extends SilenthillHolder
{
    private OfflinePlayer player;

    public PlayerMenuHolder(OfflinePlayer player)
    {
        this.player = player;
    }

    public OfflinePlayer getPlayer() {
        return player;
    }
}
