package fr.silenthill99.harryplugin.inventory.holder.direction;

import fr.silenthill99.harryplugin.inventory.SilenthillHolder;
import fr.silenthill99.harryplugin.inventory.hook.direction.ErreurStaffInventory.*;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;

public class ErreurStaffHolder extends SilenthillHolder
{
    private final OfflinePlayer target;
    public ErreurStaffHolder(OfflinePlayer target)
    {
        this.target = target;
    }

    public OfflinePlayer getTarget() {
        return target;
    }

    public HashMap<Integer, ErreursStaff> erreurs_staff = new HashMap<>();
}
