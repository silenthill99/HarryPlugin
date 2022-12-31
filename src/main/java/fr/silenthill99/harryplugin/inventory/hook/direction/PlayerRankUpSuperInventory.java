package fr.silenthill99.harryplugin.inventory.hook.direction;

import fr.silenthill99.harryplugin.ItemBuilder;
import fr.silenthill99.harryplugin.inventory.AbstractInventory;
import fr.silenthill99.harryplugin.inventory.InventoryManager;
import fr.silenthill99.harryplugin.inventory.InventoryType;
import fr.silenthill99.harryplugin.inventory.holder.direction.PlayerRankUpSuperHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerRankUpSuperInventory extends AbstractInventory<PlayerRankUpSuperHolder> {
    public PlayerRankUpSuperInventory() {
        super(PlayerRankUpSuperHolder.class);
    }

    @Override
    public void openInventory(Player p, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        PlayerRankUpSuperHolder holder = new PlayerRankUpSuperHolder(target);

        ItemBuilder fondateur = new ItemBuilder(Material.RED_WOOL).setName(ChatColor.DARK_RED + "[Fondateur]");

        Inventory inv = createInventory(holder, 9, "Grade Direction");
        inv.setItem(2, fondateur.toItemStack());
        inv.setItem(8, RETOUR);
        p.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent e, ItemStack current, Player player, PlayerRankUpSuperHolder holder) {
        OfflinePlayer target = holder.getTarget();
        switch (current.getType())
        {
            case SUNFLOWER:
            {
                InventoryManager.openInventory(player, InventoryType.RANK_UP, target);
                break;
            }
            case RED_WOOL:
            {
                player.closeInventory();
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set fondateur");
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission clear");
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission set harrypocraft.fondateur");
                if (target.isOnline()) {
                    target.getPlayer().sendMessage(ChatColor.GREEN + "Vous êtes désormais Fondateur !");
                }
                player.sendMessage(ChatColor.GREEN + target.getName() + " est désormais Fondateur !");
                break;
            }
            default:
                break;
        }
    }
}
