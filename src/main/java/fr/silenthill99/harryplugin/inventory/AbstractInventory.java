package fr.silenthill99.harryplugin.inventory;

import fr.silenthill99.harryplugin.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public abstract class AbstractInventory<T extends SilenthillHolder>
{

    public static final ItemStack RETOUR = new ItemBuilder(Material.SUNFLOWER).setName(ChatColor.YELLOW + "Retour").toItemStack();

    public static final ItemStack CLOSE = new ItemBuilder(Material.BARRIER).setName(ChatColor.YELLOW + "Fermer").toItemStack();

    private final Class<T> holderClass;

    public AbstractInventory(Class<T> holderClass) {
        this.holderClass = holderClass;
    }

    public Boolean isInstance(SilenthillHolder nh)
    {
        return nh.getClass().isAssignableFrom(holderClass);
    }

    protected Inventory createInventory(T holder, int size, String name)
    {
        return Bukkit.createInventory(holder, size, name);
    }

    public void onPlayerJoin(PlayerJoinEvent e) {}
    public void onPlayerLeft(PlayerQuitEvent e) {}
    public void onInteract(PlayerInteractEvent e) {}
    public abstract void openInventory(Player player, Object... args);
    public void closeInventory(Player p, InventoryCloseEvent e) {}
    public void manageInventory(InventoryClickEvent e, ItemStack current, Player player, T holder) throws IOException {}
    public void voidInventory(InventoryClickEvent e, Player player, T holder){}
    public void moveFromInventory(InventoryClickEvent e, Inventory from, Player player, T holder){}
    public void actualizeInventory(Player p, Inventory inv, T holder) {}
}
