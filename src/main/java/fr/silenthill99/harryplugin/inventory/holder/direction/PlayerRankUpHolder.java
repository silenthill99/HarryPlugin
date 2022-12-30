package fr.silenthill99.harryplugin.inventory.holder.direction;

import fr.silenthill99.harryplugin.inventory.SilenthillHolder;
import fr.silenthill99.harryplugin.inventory.hook.direction.PlayerRankUpInventory.*;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;

public class PlayerRankUpHolder extends SilenthillHolder
{
    private final OfflinePlayer target;
    public PlayerRankUpHolder(OfflinePlayer target) {
        this.target = target;
    }

    public OfflinePlayer getTarget() {
        return target;
    }

    public HashMap<Integer, Operateur> operateur = new HashMap<>();
}
