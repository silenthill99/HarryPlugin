package fr.silenthill99.harryplugin.inventory.holder.direction;

import fr.silenthill99.harryplugin.inventory.SilenthillHolder;
import org.bukkit.OfflinePlayer;

public class PlayerRankUpHolder extends SilenthillHolder
{
    private OfflinePlayer target;
    public PlayerRankUpHolder(OfflinePlayer target) {
        this.target = target;
    }

    public OfflinePlayer getTarget() {
        return target;
    }
}
