package fr.silenthill99.harryplugin.inventory.hook;

import fr.silenthill99.harryplugin.inventory.AbstractInventory;
import fr.silenthill99.harryplugin.inventory.holder.PlayerInventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerInventoryInv extends AbstractInventory<PlayerInventoryHolder>
{
    public PlayerInventoryInv() {
        super(PlayerInventoryHolder.class);
    }

    @Override
    public void openInventory(Player p, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        Inventory inv = Bukkit.createInventory(new PlayerInventoryHolder(target), 45, "Inventaire de " + target.getName());
        for (int slot = 0; slot <= 35; slot++)
        {
            if (target.getPlayer().getInventory().getItem(slot) != null)
            {
                inv.setItem(slot, target.getPlayer().getInventory().getItem(slot));
            }
        }
        inv.setItem(36, target.getPlayer().getInventory().getItem(EquipmentSlot.HEAD));
        inv.setItem(37, target.getPlayer().getInventory().getItem(EquipmentSlot.CHEST));
        inv.setItem(38, target.getPlayer().getInventory().getItem(EquipmentSlot.LEGS));
        inv.setItem(39, target.getPlayer().getInventory().getItem(EquipmentSlot.FEET));
        inv.setItem(40, target.getPlayer().getInventory().getItemInOffHand());
        p.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent e, ItemStack current, Player player, PlayerInventoryHolder holder) {
        OfflinePlayer target = holder.getPlayer();
        e.setCancelled(true);
    }
}
