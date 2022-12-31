package fr.silenthill99.harryplugin.inventory.hook.admin;

import fr.silenthill99.harryplugin.ItemBuilder;
import fr.silenthill99.harryplugin.inventory.AbstractInventory;
import fr.silenthill99.harryplugin.inventory.InventoryManager;
import fr.silenthill99.harryplugin.inventory.InventoryType;
import fr.silenthill99.harryplugin.inventory.holder.admin.AdminSanctionsHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AdminSanctionsInventory extends AbstractInventory<AdminSanctionsHolder> {
    public AdminSanctionsInventory() {
        super(AdminSanctionsHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        AdminSanctionsHolder holder = new AdminSanctionsHolder(target);

        Inventory inv = createInventory(holder, 54, "Sanctions Administratives");
        int slot = 0;
        for (Sanctions sanctions : Sanctions.values())
        {
            holder.sanctions.put(slot, sanctions);
            inv.setItem(slot++, new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + sanctions.getName()).toItemStack());
        }
        inv.setItem(53, RETOUR);
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent e, ItemStack current, Player player, AdminSanctionsHolder holder)
    {
        OfflinePlayer target = holder.getTarget();
        Sanctions sanctions = holder.sanctions.get(e.getSlot());
        switch (current.getType())
        {
            case REDSTONE:
            {
                Bukkit.dispatchCommand(player, "ipban " + target.getName() + " " + sanctions.getName());
                break;
            }
            case  SUNFLOWER:
            {
                InventoryManager.openInventory(player, InventoryType.ADMIN_MENU, target);
                break;
            }
            default:
            {
                break;
            }
        }
    }

    public enum Sanctions
    {
        MENACE_DDOS("Menace DDoS"),
        CONTOURNEMENT("Contournement de sanctions / Double compte")
        ;
        private final String name;

        Sanctions(String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return this.name;
        }
    }
}
