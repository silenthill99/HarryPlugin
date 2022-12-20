package fr.silenthill99.harryplugin.inventory.hook.direction;

import fr.silenthill99.harryplugin.ItemBuilder;
import fr.silenthill99.harryplugin.Panel;
import fr.silenthill99.harryplugin.inventory.AbstractInventory;
import fr.silenthill99.harryplugin.inventory.InventoryManager;
import fr.silenthill99.harryplugin.inventory.InventoryType;
import fr.silenthill99.harryplugin.inventory.holder.direction.DirectionHolder;
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
        ItemBuilder fly_sans_vanish = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + "Fly Sans Vanish");
        ItemBuilder god_en_warzone = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + "God en WZ").setLore("Pas valable si la personne est en vanish");
        ItemBuilder abus_de_pouvoir = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + "Abus de pouvoir").setLore("Dans certains cas, passible d'un dérank immédiat");
        ItemBuilder abus_de_permissions = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + "Abus de permissions");
        ItemBuilder non_respect = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + "Non respect de la hiérarchie");
        ItemBuilder reglementation = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + "Non respect | Règlement staff");
        ItemBuilder freewarn = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + " FreeWarn");
        ItemBuilder freeban = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + "FreeBan");
        ItemBuilder absence = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + "Abscence non justifiée");
        ItemBuilder favoritisme = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + "Favoritisme").setLore("A ne pas confondre avec l'attribution de circonstances atténuantes");
        ItemBuilder tete = new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(target.getName());
        ItemBuilder retour = new ItemBuilder(Material.SUNFLOWER).setName(ChatColor.YELLOW + "Retour");

        Inventory direction = Bukkit.createInventory(new ErreurStaffHolder(target), 27, "Erreurs staff : " + target.getName());
        direction.setItem(0, fly_sans_vanish.toItemStack());
        direction.setItem(1, god_en_warzone.toItemStack());
        direction.setItem(2, abus_de_pouvoir.toItemStack());
        direction.setItem(3, abus_de_permissions.toItemStack());
        direction.setItem(4, non_respect.toItemStack());
        direction.setItem(5, reglementation.toItemStack());
        direction.setItem(6, freewarn.toItemStack());
        direction.setItem(7, freeban.toItemStack());
        direction.setItem(8, absence.toItemStack());
        direction.setItem(9, favoritisme.toItemStack());
        direction.setItem(22, tete.toItemStack());
        direction.setItem(26, retour.toItemStack());
        p.openInventory(direction);
    }

    @Override
    public void manageInventory(InventoryClickEvent e, ItemStack current, Player player, ErreurStaffHolder holder) {
        OfflinePlayer target = holder.getTarget();
        e.setCancelled(true);
        switch (current.getType())
        {
            case REDSTONE:
                player.closeInventory();
                String name = ChatColor.stripColor(current.getItemMeta().getDisplayName());
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " Erreur staff : " + name);
                break;
            case SUNFLOWER:
                InventoryManager.openInventory(player, InventoryType.MENU_DIRECTION, target);
                break;
            default:
                break;
        }
    }
}
