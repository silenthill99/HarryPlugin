package fr.silenthill99.harryplugin.inventory.hook.admin;

import fr.silenthill99.harryplugin.ItemBuilder;
import fr.silenthill99.harryplugin.inventory.AbstractInventory;
import fr.silenthill99.harryplugin.inventory.InventoryManager;
import fr.silenthill99.harryplugin.inventory.InventoryType;
import fr.silenthill99.harryplugin.inventory.holder.admin.AdminPlayerHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AdminPlayerInventory extends AbstractInventory<AdminPlayerHolder>
{
    public AdminPlayerInventory() {
        super(AdminPlayerHolder.class);
    }

    @Override
    public void openInventory(Player p, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        AdminPlayerHolder holder = new AdminPlayerHolder(target);

        ItemBuilder sanctions_admin = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + "Sanctions Administratives");
        ItemBuilder direction = new ItemBuilder(Material.RED_CONCRETE).setName(ChatColor.DARK_RED + "Menu direction");

        Inventory admin = createInventory(holder, 9, "Administration");
        admin.setItem(0, sanctions_admin.toItemStack());
        admin.setItem(7, direction.toItemStack());
        admin.setItem(8, RETOUR);
        p.openInventory(admin);
    }

    @Override
    public void manageInventory(InventoryClickEvent e, ItemStack current, Player player, AdminPlayerHolder holder) {
        e.setCancelled(true);
        OfflinePlayer target = holder.getTarget();
        switch (current.getType())
        {
            case RED_CONCRETE:
                if (!player.hasPermission("harrypocraft.admin"))
                {
                    player.sendMessage(ChatColor.DARK_RED + "VOus n'avez pas accès à cette partie !");
                    return;
                }
                InventoryManager.openInventory(player, InventoryType.MENU_DIRECTION, target);
                break;
            case SUNFLOWER:
                InventoryManager.openInventory(player, InventoryType.MODO_PLAYER_MENU, target);
                break;
        }
    }
}
