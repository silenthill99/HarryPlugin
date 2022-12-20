package fr.silenthill99.harryplugin.inventory.hook.modo;

import fr.silenthill99.harryplugin.ItemBuilder;
import fr.silenthill99.harryplugin.Main;
import fr.silenthill99.harryplugin.inventory.AbstractInventory;
import fr.silenthill99.harryplugin.inventory.InventoryManager;
import fr.silenthill99.harryplugin.inventory.InventoryType;
import fr.silenthill99.harryplugin.inventory.holder.modo.PlayerMenuHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerMenuInventory extends AbstractInventory<PlayerMenuHolder>
{
    public PlayerMenuInventory()
    {
        super(PlayerMenuHolder.class);
    }

    @Override
    public void openInventory(Player p, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        p.closeInventory();
        ItemStack tete = new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(target.getName()).toItemStack();
        ItemStack sanctionner = new ItemBuilder(Material.GREEN_WOOL).setName(ChatColor.DARK_GREEN + "Sanctionner " + target.getName()).toItemStack();
        ItemStack administration = new ItemBuilder(Material.RED_WOOL).setName(ChatColor.DARK_RED + "Administration").toItemStack();
        ItemStack options = new ItemBuilder(Material.LAPIS_LAZULI).setName(ChatColor.AQUA + "Options").toItemStack();

        Inventory menu = Bukkit.createInventory(new PlayerMenuHolder(target), 27, "Menu de " + target.getName());
        menu.setItem(4, tete);
        menu.setItem(10,sanctionner);
        menu.setItem(13, administration);
        menu.setItem(16, options);
        p.openInventory(menu);

    }

    @Override
    public void manageInventory(InventoryClickEvent e, ItemStack current, Player player, PlayerMenuHolder holder)
    {
        OfflinePlayer target = holder.getPlayer();
        e.setCancelled(true);
        switch (current.getType())
        {
            case GREEN_WOOL:
                InventoryManager.openInventory(player, InventoryType.MODO_PLAYER_SANCTION, target, PlayerSanctionInventory.SanctionType.MENU, 1);
                break;
            case LAPIS_LAZULI:
                InventoryManager.openInventory(player, InventoryType.OPTION_PLAYER, target);
                break;
            case RED_WOOL:
                if (!Main.isPlayerInGroup(player, "administrateur"))
                {
                    player.sendMessage(ChatColor.DARK_RED + "Tu n'as pas accès à cette partie du panel !");
                    return;
                }
                InventoryManager.openInventory(player, InventoryType.ADMIN_MENU, target);
                break;
            default:
                break;
        }
    }
}
