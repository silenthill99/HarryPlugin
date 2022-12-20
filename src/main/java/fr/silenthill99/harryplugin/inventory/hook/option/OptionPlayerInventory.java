package fr.silenthill99.harryplugin.inventory.hook.option;

import fr.silenthill99.harryplugin.ItemBuilder;
import fr.silenthill99.harryplugin.Main;
import fr.silenthill99.harryplugin.Panel;
import fr.silenthill99.harryplugin.inventory.AbstractInventory;
import fr.silenthill99.harryplugin.inventory.InventoryManager;
import fr.silenthill99.harryplugin.inventory.InventoryType;
import fr.silenthill99.harryplugin.inventory.holder.option.OptionPlayerHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class OptionPlayerInventory extends AbstractInventory<OptionPlayerHolder> {
    public OptionPlayerInventory()
    {
        super(OptionPlayerHolder.class);
    }

    @Override
    public void openInventory(Player p, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        ItemStack se_teleporter = new ItemBuilder(Material.ENDER_PEARL).setName(ChatColor.GREEN + "Se téléporter").toItemStack();
        ItemStack teleporter_ici = new ItemBuilder(Material.ENDER_EYE).setName(ChatColor.GREEN + "Téléporter ici").toItemStack();
        ItemStack inventaire = new ItemBuilder(Material.CHEST).setName(ChatColor.GOLD + "Voir l'inventaire").toItemStack();
        ItemStack check = new ItemBuilder(Material.RED_DYE).setName(ChatColor.RED + "Sanctions de " + target.getName()).toItemStack();
        ItemStack historique = new ItemBuilder(Material.BOOK).setName(ChatColor.GOLD + "Historique de sanctions").toItemStack();
        ItemStack logs = new ItemBuilder(Material.LAPIS_LAZULI).setName(ChatColor.BLUE + "Voir les logs").toItemStack();
        ItemStack retour =  new ItemBuilder(Material.SUNFLOWER).setName(ChatColor.YELLOW + "Retour").toItemStack();

        Inventory options = Bukkit.createInventory(new OptionPlayerHolder(target), 9, "Options");
        options.setItem(0, se_teleporter);
        options.setItem(1, teleporter_ici);
        options.setItem(2, inventaire);
        options.setItem(3, check);
        options.setItem(4, historique);
        options.setItem(5, logs);
        options.setItem(8, retour);
        p.openInventory(options);
    }

    @Override
    public void manageInventory(InventoryClickEvent e, ItemStack current, Player player, OptionPlayerHolder holder) {
        OfflinePlayer target = holder.getPlayer();
        e.setCancelled(true);
        switch (current.getType())
        {
            case SUNFLOWER:
                InventoryManager.openInventory(player, InventoryType.MODO_PLAYER_MENU, target);
                break;
            case ENDER_PEARL:
                if (target.isOnline())
                {
                    player.teleport(target.getPlayer().getLocation());
                    player.sendMessage(ChatColor.GREEN + "Vous avez été téléporté à " + target.getName());
                }
                else
                {
                    player.sendMessage(ChatColor.RED + "Ce joueur n'est pas connecté ou n'existe pas !");
                }
                break;
            case ENDER_EYE:
                if (target.isOnline())
                {
                    target.getPlayer().teleport(player.getLocation());
                    player.sendMessage(ChatColor.GREEN + target.getName() + " a été téléporté à votre position");
                }
                else
                {
                    player.sendMessage(ChatColor.RED + "Ce joueur n'est pas connecté ou n'existe pas !");
                }
                break;
            case CHEST:
                if (target.isOnline())
                {
                    InventoryManager.openInventory(player, InventoryType.PLAYER_INVENTORY, target);
                }
                else
                {
                    player.sendMessage(ChatColor.RED + "Ce joueur n'est pas connecté ou n'existe pas !");
                }
                break;
            case RED_DYE:
                player.closeInventory();
                Bukkit.dispatchCommand(player, "check " + target.getName());
                break;
            case LAPIS_LAZULI:
            {
                player.closeInventory();
                for (String logs : Main.getInstance().logs.get(target.getUniqueId()))
                {
                    player.sendMessage(logs);
                }
                break;
            }
            default:
                break;
        }
    }
}
