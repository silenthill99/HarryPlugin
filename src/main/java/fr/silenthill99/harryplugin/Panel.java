package fr.silenthill99.harryplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class Panel
{

    public static HashMap<Player, String> titre = new HashMap<>();
    public static HashMap<Player, Integer> page = new HashMap<>();


    public static void carte(Player player)
    {

        ItemBuilder bureau = new ItemBuilder(Material.FILLED_MAP).setName("Bureau de Dumbledore");
        ItemBuilder quidditch = new ItemBuilder(Material.FILLED_MAP).setName("Terrain de Quidditch");
        ItemBuilder grande_salle = new ItemBuilder(Material.FILLED_MAP).setName("Grande salle");
        ItemBuilder gryffondor = new ItemBuilder(Material.FILLED_MAP).setName("Salle commune de Gryffondor");
        ItemBuilder toilettes = new ItemBuilder(Material.FILLED_MAP).setName("Toilettes de Mimi Geignarde");
        ItemBuilder infirmerie = new ItemBuilder(Material.FILLED_MAP).setName("Infirmerie");
        ItemBuilder pont = new ItemBuilder(Material.FILLED_MAP).setName("Pont couvert");
        ItemBuilder serpentard = new ItemBuilder(Material.FILLED_MAP).setName("Salle commune de Serpentard");
        ItemBuilder cabane = new ItemBuilder(Material.FILLED_MAP).setName("Cabane d'Hagrid");
        ItemBuilder bibliotheque = new ItemBuilder(Material.FILLED_MAP).setName("Bibliothèque");
        ItemBuilder tombe = new ItemBuilder(Material.FILLED_MAP).setName("Tombe de Dumbledore");
        ItemBuilder escaliers = new ItemBuilder(Material.FILLED_MAP).setName("Grands escaliers");
        ItemBuilder saule_cogneur = new ItemBuilder(Material.FILLED_MAP).setName("Saule Cogneur");
        ItemBuilder tour_dastronomie = new ItemBuilder(Material.FILLED_MAP).setName("Tour d'astronomie");
        ItemBuilder cour_de_metamorphose = new ItemBuilder(Material.FILLED_MAP).setName("Cour de métamorphose");
        ItemBuilder salle_de_metamorphoses = new ItemBuilder(Material.FILLED_MAP).setName("Salle de métamorphoses");
        ItemBuilder chambre = new ItemBuilder(Material.FILLED_MAP).setName("Chambre des secrets");

        Inventory carte = Bukkit.createInventory(null, 54, "Carte du maraudeur");
        carte.setItem(0, bureau.toItemStack());
        carte.setItem(1, quidditch.toItemStack());
        carte.setItem(2, grande_salle.toItemStack());
        carte.setItem(3, gryffondor.toItemStack());
        carte.setItem(4, toilettes.toItemStack());
        carte.setItem(5, infirmerie.toItemStack());
        carte.setItem(6, pont.toItemStack());
        carte.setItem(7, serpentard.toItemStack());
        carte.setItem(8, cabane.toItemStack());
        carte.setItem(9, bibliotheque.toItemStack());
        carte.setItem(10, tombe.toItemStack());
        carte.setItem(11, escaliers.toItemStack());
        carte.setItem(12, saule_cogneur.toItemStack());
        carte.setItem(13, tour_dastronomie.toItemStack());
        carte.setItem(14, cour_de_metamorphose.toItemStack());
        carte.setItem(15, salle_de_metamorphoses.toItemStack());
        carte.setItem(16, chambre.toItemStack());
        player.openInventory(carte);
    }

    /*public static void panel_admin(Player player, OfflinePlayer target, String name)
    {
        if (name.equals("sanctions"))
        {
            ItemBuilder retour = new ItemBuilder(Material.SUNFLOWER).setName(ChatColor.YELLOW + "Retour");
            ItemBuilder menace_ddos = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + "Menace DDoS");
            ItemBuilder contournement_de_sanctions = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + "Contournement de sanctions / Double compte");

            Inventory sanctions = Bukkit.createInventory(new AdminOptionHolder(target), 54, "Sanctions Administratives");
            sanctions.setItem(0, menace_ddos.toItemStack());
            sanctions.setItem(1, contournement_de_sanctions.toItemStack());
            sanctions.setItem(53, retour.toItemStack());
            player.openInventory(sanctions);
        }
    }*/
}
