package fr.silenthill99.harryplugin.inventory.holder.admin;

import fr.silenthill99.harryplugin.inventory.SilenthillHolder;
import fr.silenthill99.harryplugin.inventory.hook.admin.AdminSanctionsInventory.*;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;

public class AdminSanctionsHolder extends SilenthillHolder
{
    private final OfflinePlayer target;

    public AdminSanctionsHolder(OfflinePlayer target)
    {
        this.target = target;
    }

    public OfflinePlayer getTarget()
    {
        return this.target;
    }

    public HashMap<Integer, Sanctions> sanctions = new HashMap<>();
}
