package fr.silenthill99.harryplugin.inventory.hook.direction;

import fr.silenthill99.harryplugin.ItemBuilder;
import fr.silenthill99.harryplugin.inventory.AbstractInventory;
import fr.silenthill99.harryplugin.inventory.InventoryManager;
import fr.silenthill99.harryplugin.inventory.InventoryType;
import fr.silenthill99.harryplugin.inventory.holder.direction.ErreurStaffHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ErreurStaffInventory extends AbstractInventory<ErreurStaffHolder>
{
    public ErreurStaffInventory()
    {
        super(ErreurStaffHolder.class);
    }

    @Override
    public void openInventory(Player p, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        ItemBuilder tete = new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(target.getName());
        ItemBuilder ban_immediat = new ItemBuilder(Material.CHEST).setName(ChatColor.DARK_RED + "Bannissement immédiat");
        ErreurStaffHolder holder = new ErreurStaffHolder(target);

        Inventory direction = Bukkit.createInventory(holder, 27, "Erreurs staff : " + target.getName());
        int slot = 0;
        for (ErreursStaff erreursStaff : ErreursStaff.values())
        {
            holder.erreurs_staff.put(slot, erreursStaff);
            direction.setItem(slot++, new ItemBuilder(Material. REDSTONE).setName(ChatColor.DARK_RED + erreursStaff.getName()).setLore(erreursStaff.getLore()).toItemStack());
        }
        direction.setItem(18, ban_immediat.toItemStack());
        direction.setItem(22, tete.toItemStack());
        direction.setItem(26, RETOUR);
        p.openInventory(direction);
    }

    @Override
    public void manageInventory(InventoryClickEvent e, ItemStack current, Player player, ErreurStaffHolder holder) {
        OfflinePlayer target = holder.getTarget();
        ErreursStaff erreursStaff = holder.erreurs_staff.get(e.getSlot());
        e.setCancelled(true);
        switch (current.getType())
        {
            case REDSTONE:
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " Erreur staff : " + erreursStaff.getName());
                break;
            case CHEST: {
                InventoryManager.openInventory(player, InventoryType.STAFF_BAN_IMMEDIAT, target);
                break;
            }
            case SUNFLOWER:
                InventoryManager.openInventory(player, InventoryType.MENU_DIRECTION, target);
                break;
            default:
                break;
        }
    }

    public enum ErreursStaff
    {
        FLY_SANS_VANISH("Fly Sans Vanish"),
        GOD_EN_WZ("God en WZ", "Pas valable si la personne est en vanish"),
        ABUS_DE_POUVOIR("Abus de pouvoir", "Dans certains cas, passible d'un dérank immédiat"),
        ABUS_DE_PERMISSIONS("Abus de permissions"),
        NON_RESPECT("Non respect de la hiérarchie"),
        REGLEMENTATION("Non respect | Règlement staff"),
        FREEWARN("FreeWarn"),
        FREEBAN("FreeBan"),
        ABSENCE("Abscence non justifiée"),
        FAVORITISME("Favoritisme", "A ne pas confondre avec ","l'attribution de circonstances atténuantes"),
        REFUS("Refus de prise d'interadmin non justifié")
        ;
        private final String name;
        private final String[] lore;
        ErreursStaff(String name, String... lore)
        {
            this.name = name;
            this.lore = lore;
        }
        public String getName()
        {
            return this.name;
        }
        public String[] getLore()
        {
            return this.lore;
        }
    }
}
