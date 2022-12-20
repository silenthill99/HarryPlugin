package fr.silenthill99.harryplugin.inventory.hook.direction;

import fr.silenthill99.harryplugin.ItemBuilder;
import fr.silenthill99.harryplugin.Panel;
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
        ItemBuilder retour = new ItemBuilder(Material.SUNFLOWER).setName(ChatColor.YELLOW + "Retour au menu");
        ItemBuilder erreurs_staff = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + "Erreurs staff");
        ItemBuilder rank_up = new ItemBuilder(Material.BOOK).setName(ChatColor.YELLOW + "RankUp " + target.getName());

        Inventory direction = Bukkit.createInventory(new DirectionHolder(target), 36, "Menu direction");
        direction.setItem(4, tete.toItemStack());
        direction.setItem(8, retour.toItemStack());
        direction.setItem(9, erreurs_staff.toItemStack());
        direction.setItem(10, rank_up.toItemStack());
        p.openInventory(direction);
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
                //Panel.panel_direction(player, target, "Erreurs staff");
                break;
            case BOOK:
                //Panel.panel_direction(player, target, "RankUp");
                break;
            default:
                break;
        }
    }
}
