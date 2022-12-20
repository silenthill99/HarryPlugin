package fr.silenthill99.harryplugin.inventory.holder.option;

import fr.silenthill99.harryplugin.inventory.SilenthillHolder;
import org.bukkit.OfflinePlayer;

public class OptionPlayerHolder extends SilenthillHolder {
    private OfflinePlayer op;
    public OptionPlayerHolder(OfflinePlayer op) {
       this.op = op;
    }

    public OfflinePlayer getPlayer() {
        return op;
    }
}
