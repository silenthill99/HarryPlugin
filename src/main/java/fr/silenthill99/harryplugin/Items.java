package fr.silenthill99.harryplugin;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Items
{
    CARTE_DU_MARAUDEUR(new ItemBuilder(Material.MAP).setName("Carte du maraudeur").toItemStack())
    ;
    private final ItemStack item;

    Items(ItemStack item)
    {
        this.item = item;
    }

    public ItemStack getItem()
    {
        return this.item;
    }
}
