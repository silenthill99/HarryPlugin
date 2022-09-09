package fr.silenthill99.harryplugin.inventory;

import fr.silenthill99.harryplugin.inventory.hook.modo.PlayerChooseInventory;
import fr.silenthill99.harryplugin.inventory.hook.modo.PlayerMenuInventory;
import fr.silenthill99.harryplugin.inventory.hook.modo.PlayerSanctionInventory;

import java.util.Arrays;
import java.util.List;

public enum InventoryType
{

    MODO_PLAYER_MENU(new PlayerMenuInventory()),
    MODO_PLAYER_SANCTION(new PlayerSanctionInventory()),
    PLAYER_CHOOSE(new PlayerChooseInventory())
    ;
    private final AbstractInventory<?> inv;

    InventoryType(AbstractInventory<?> inv) {
        this.inv = inv;
    }

    public AbstractInventory<?> getInv() {
        return inv;
    }

    public static List<InventoryType> getValues()
    {
        return Arrays.asList(values());
    }
}
