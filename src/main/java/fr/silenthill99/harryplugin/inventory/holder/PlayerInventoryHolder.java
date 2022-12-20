package fr.silenthill99.harryplugin.inventory.holder;

import fr.silenthill99.harryplugin.inventory.SilenthillHolder;
import org.bukkit.OfflinePlayer;

public class PlayerInventoryHolder extends SilenthillHolder
{
    private OfflinePlayer target;

    public PlayerInventoryHolder(OfflinePlayer target)
    {
        this.target = target;
    }

    public OfflinePlayer getPlayer() {
        return target;
    }
}
