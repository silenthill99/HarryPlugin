package fr.silenthill99.harryplugin;

import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class AdminOptionHolder implements InventoryHolder
{

    private OfflinePlayer op;

    public AdminOptionHolder(OfflinePlayer op)
    {
        this.op = op;
    }

    public OfflinePlayer getPlayer()
    {
        return op;
    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}
