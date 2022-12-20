package fr.silenthill99.harryplugin.inventory.holder.direction;

import fr.silenthill99.harryplugin.inventory.SilenthillHolder;
import org.bukkit.OfflinePlayer;

public class PlayerRankUpSuperHolder extends SilenthillHolder
{
    private OfflinePlayer target;
    public PlayerRankUpSuperHolder(OfflinePlayer target) {
        this.target = target;
    }

    public OfflinePlayer getTarget() {
        return target;
    }
}
