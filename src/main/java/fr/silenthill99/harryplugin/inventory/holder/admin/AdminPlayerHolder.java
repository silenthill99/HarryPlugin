package fr.silenthill99.harryplugin.inventory.holder.admin;

import fr.silenthill99.harryplugin.inventory.SilenthillHolder;
import org.bukkit.OfflinePlayer;

public class AdminPlayerHolder extends SilenthillHolder
{
    private OfflinePlayer target;
    public AdminPlayerHolder(OfflinePlayer target) {
        this.target = target;
    }

    public OfflinePlayer getTarget() {
        return target;
    }
}
