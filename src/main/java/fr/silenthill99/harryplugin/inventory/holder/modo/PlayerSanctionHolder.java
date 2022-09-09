package fr.silenthill99.harryplugin.inventory.holder.modo;

import fr.silenthill99.harryplugin.inventory.SilenthillHolder;
import fr.silenthill99.harryplugin.inventory.hook.modo.PlayerSanctionInventory.SanctionType;
import org.bukkit.OfflinePlayer;

public class PlayerSanctionHolder extends SilenthillHolder
{

    private final OfflinePlayer player;
    private final SanctionType type;
    private final int page;

    public PlayerSanctionHolder(OfflinePlayer player, SanctionType type, int page) {
        this.player = player;
        this.type = type;
        this.page = page;
    }

    public OfflinePlayer getPlayer() {
        return player;
    }

    public SanctionType getType() {
        return type;
    }

    public int getPage() {
        return page;
    }
}
