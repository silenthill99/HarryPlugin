package fr.silenthill99.harryplugin.inventory.holder.direction;

import fr.silenthill99.harryplugin.inventory.SilenthillHolder;
import org.bukkit.OfflinePlayer;

public class DirectionHolder extends SilenthillHolder
{

    private final OfflinePlayer target;

    public DirectionHolder(OfflinePlayer target)
    {
        this.target = target;
    }

    public OfflinePlayer getTarget() {
        return target;
    }
}
