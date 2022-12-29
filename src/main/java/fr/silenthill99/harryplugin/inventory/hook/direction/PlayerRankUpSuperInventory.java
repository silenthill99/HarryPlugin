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
        ItemBuilder fondateur = new ItemBuilder(Material.RED_WOOL).setName(ChatColor.DARK_RED + "[Fondateur]");
        ItemBuilder retour = new ItemBuilder(Material.SUNFLOWER).setName(ChatColor.YELLOW + "Retour");

        Inventory direction = Bukkit.createInventory(new PlayerRankUpSuperHolder(target), 9, "Grade Direction");
        direction.setItem(2, fondateur.toItemStack());
        direction.setItem(8, retour.toItemStack());
        p.openInventory(direction);
    }

    @Override
    public void manageInventory(InventoryClickEvent e, ItemStack current, Player player, PlayerRankUpSuperHolder holder) {
        OfflinePlayer target = holder.getTarget();
        e.setCancelled(true);
        switch (current.getType())
        {
            case SUNFLOWER:
                InventoryManager.openInventory(player, InventoryType.RANK_UP, target);
                break;
            case RED_WOOL:
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set fondateur");
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission clear");
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission set harrypocraft.fondateur");
                if (target.isOnline())
                {
                    target.getPlayer().sendMessage(ChatColor.GREEN + "Vous êtes désormais Fondateur !");
                }
                player.sendMessage(ChatColor.GREEN + target.getName() + " est désormais Fondateur !");
                break;
            default:
                break;
        }
    }
}
