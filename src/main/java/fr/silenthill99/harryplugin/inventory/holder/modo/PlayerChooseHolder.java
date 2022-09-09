package fr.silenthill99.harryplugin.inventory.holder.modo;

import fr.silenthill99.harryplugin.inventory.AbstractInventory;
import fr.silenthill99.harryplugin.inventory.SilenthillHolder;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerChooseHolder extends SilenthillHolder
{

    private final HashMap<Integer, Player> playerPerSlot = new HashMap<>();

    public void add(int solt, Player p)
    {
        playerPerSlot.put(solt, p);
    }

    public Player get(int slot)
    {
        return playerPerSlot.get(slot);
    }

}
