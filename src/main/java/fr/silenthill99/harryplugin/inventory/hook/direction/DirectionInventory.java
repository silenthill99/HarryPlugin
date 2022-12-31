package fr.silenthill99.harryplugin.inventory.hook.direction;

import fr.silenthill99.harryplugin.ItemBuilder;
import fr.silenthill99.harryplugin.inventory.AbstractInventory;
import fr.silenthill99.harryplugin.inventory.InventoryManager;
import fr.silenthill99.harryplugin.inventory.InventoryType;
import fr.silenthill99.harryplugin.inventory.holder.direction.DirectionHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DirectionInventory extends AbstractInventory<DirectionHolder> {
    public DirectionInventory() {
        super(DirectionHolder.class);
    }

    @Override
    public void openInventory(Player p, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        ItemBuilder tete = new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(target.getName()).setName(ChatColor.GREEN + "" + ChatColor.BOLD + "Menu de " + target.getName());
        ItemBuilder erreurs_staff = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + "Erreurs staff");
        ItemBuilder rank_up = new ItemBuilder(Material.BOOK).setName(ChatColor.YELLOW + "RankUp " + target.getName());

        Inventory inv = Bukkit.createInventory(new DirectionHolder(target), 36, "Menu direction");
        inv.setItem(4, tete.toItemStack());
        inv.setItem(8, RETOUR);
        inv.setItem(9, erreurs_staff.toItemStack());
        inv.setItem(10, rank_up.toItemStack());
        p.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent e, ItemStack current, Player player, DirectionHolder holder) {
        OfflinePlayer target = holder.getTarget();
        e.setCancelled(true);
        switch (current.getType())
        {
            case SUNFLOWER:
                InventoryManager.openInventory(player, InventoryType.ADMIN_MENU, target);
                break;
            case REDSTONE:
                InventoryManager.openInventory(player, InventoryType.DIRECTION_ERREURS_STAFF, target);
                break;
            case BOOK:
                InventoryManager.openInventory(player, InventoryType.RANK_UP, target);
                break;
            default:
                break;
        }
    }
}
