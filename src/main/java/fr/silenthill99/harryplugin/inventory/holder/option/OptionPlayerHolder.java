package fr.silenthill99.harryplugin.inventory.holder.option;

import fr.silenthill99.harryplugin.inventory.SilenthillHolder;
import org.bukkit.OfflinePlayer;

public class OptionPlayerHolder extends SilenthillHolder {
    private final OfflinePlayer target;
    public OptionPlayerHolder(OfflinePlayer target) {
       this.target = target;
    }

    public OfflinePlayer getPlayer() {
        return target;
    }
}
