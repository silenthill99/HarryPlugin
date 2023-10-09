package fr.silenthill99.harryplugin.inventory;

import fr.silenthill99.harryplugin.inventory.hook.CarteInventory;
import fr.silenthill99.harryplugin.inventory.hook.PlayerInventoryInv;
import fr.silenthill99.harryplugin.inventory.hook.admin.AdminPlayerInventory;
import fr.silenthill99.harryplugin.inventory.hook.admin.AdminSanctionsInventory;
import fr.silenthill99.harryplugin.inventory.hook.direction.*;
import fr.silenthill99.harryplugin.inventory.hook.modo.PlayerChooseInventory;
import fr.silenthill99.harryplugin.inventory.hook.modo.PlayerMenuInventory;
import fr.silenthill99.harryplugin.inventory.hook.modo.PlayerSanctionInventory;
import fr.silenthill99.harryplugin.inventory.hook.option.OptionPlayerInventory;

import java.util.Arrays;
import java.util.List;

public enum InventoryType
{

    ADMIN_MENU(new AdminPlayerInventory()),
    ADMIN_SANCTIONS(new AdminSanctionsInventory()),
    CARTE_DU_MARAUDEUR(new CarteInventory()),
    DIRECTION_ERREURS_STAFF(new ErreurStaffInventory()),
    MENU_DIRECTION(new DirectionInventory()),
    MODO_PLAYER_MENU(new PlayerMenuInventory()),
    MODO_PLAYER_SANCTION(new PlayerSanctionInventory()),
    OPTION_PLAYER(new OptionPlayerInventory()),
    PLAYER_CHOOSE(new PlayerChooseInventory()),
    PLAYER_INVENTORY(new PlayerInventoryInv()),
    RANK_UP(new PlayerRankUpInventory()),
    RANK_UP_SUPER(new PlayerRankUpSuperInventory()),
    STAFF_BAN_IMMEDIAT(new BannissementStaffInventory())
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
