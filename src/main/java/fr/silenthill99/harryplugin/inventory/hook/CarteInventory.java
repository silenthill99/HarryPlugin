package fr.silenthill99.harryplugin.inventory.hook;

import fr.silenthill99.harryplugin.ItemBuilder;
import fr.silenthill99.harryplugin.inventory.AbstractInventory;
import fr.silenthill99.harryplugin.inventory.holder.CarteHolder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CarteInventory extends AbstractInventory<CarteHolder> {
    public CarteInventory()
    {
        super(CarteHolder.class);
    }

    @Override
    public void openInventory(Player p, Object... args)
    {
        CarteHolder holder = new CarteHolder();
        Inventory inv = createInventory(holder, 54, "Carte du maraudeur");
        int slot = 0;
        for (CarteDuMaraudeur carte : CarteDuMaraudeur.values())
        {
            holder.carte.put(slot, carte);
            inv.setItem(slot++, new ItemBuilder(Material.FILLED_MAP).setName(carte.getName()).toItemStack());
        }
        p.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent e, ItemStack current, Player player, CarteHolder holder) {
        CarteDuMaraudeur carte = holder.carte.get(e.getSlot());
        switch (current.getType())
        {
            case FILLED_MAP:
            {
                player.teleport(carte.getLoc());
                break;
            }
            default:
                break;
        }
    }

    static World world = Bukkit.getWorld("Hogwarts 1.0 (by jstoeckm2)");
    public enum CarteDuMaraudeur
    {
        BUREAU("Bureau de Dumbledore", new Location(world, -1985.5, 195, 430.5)),
        QUIDDITCH("Terrain de Quidditch", new Location(world, -1731.5, 83, 722.5)),
        GRANDE_SALLE("Grande salle", new Location(world, -2007.5, 121, 393.5)),
        GRYFFONDOR("Salle commune de Gryffondor", new Location(world, -1940.5, 133, 478.5)),
        TOILETTES("Toilettes de Mimi Geignarde", new Location(world, -1978.5, 123, 479.5)),
        INFIRMERIE("Infirmerie", new Location(world, -1942.5, 155, 456.5)),
        PONT("Pont couvert", new Location(world, -1864.5, 124, 454.5)),
        SERPENTARD("Salle commune de Serpentard", new Location(world, -2114.5, 6, 486.5)),
        CABANE("Cabane d'Hagrid", new Location(world, -1717.5, 73, 373.5)),
        BIBLIOTHEQUE("Bibliothèque", new Location(world, -1963.5, 134, 534.5)),
        TOMBE("Tombe de Dumbledore", new Location(world, -2079.5, 55, 267.5)),
        ESCALIERS("Grands escaliers", new Location(world, -1994.5, 130, 416.5)),
        SAULE_COGNEUR("Saule cogneur", new Location(world, -1944.5, 121, 661.5)),
        TOUR_DASTRONOMIE("Tour d'astronomie", new Location(world, -1998.5, 205, 545.5)),
        COUR_DE_METAMORPHOSE("Cour de métamorphose", new Location(world, -1987.5, 121, 582.5)),
        SALLE_DE_METAMORPHOSE("Salle de métamorphoses", new Location(world, -2025.5, 122, 583.5, 90f, 0f)),
        CHAMBRE("Chambre des secrets", new Location(world, -1901.5, 39, 520.5));

        private final String name;
        private final Location loc;

        CarteDuMaraudeur(String name, Location loc)
        {
            this.name = name;
            this.loc = loc;
        }

        public String getName()
        {
            return this.name;
        }

        public Location getLoc()
        {
            return this.loc;
        }

    }
}