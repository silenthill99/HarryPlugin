package fr.silenthill99.harryplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class Panel
{

    public static HashMap<Player, String> titre = new HashMap<>();
    public static HashMap<Player, Integer> page = new HashMap<>();

    public static void options(Player player, OfflinePlayer target)
    {

        ItemBuilder se_teleporter = new ItemBuilder(Material.ENDER_PEARL).setName(ChatColor.GREEN + "Se téléporter");
        ItemBuilder teleporter_ici = new ItemBuilder(Material.ENDER_EYE).setName(ChatColor.GREEN + "Téléporter ici");
        ItemBuilder inventaire = new ItemBuilder(Material.CHEST).setName(ChatColor.GOLD + "Voir l'inventaire");
        ItemBuilder check = new ItemBuilder(Material.RED_DYE).setName(ChatColor.RED + "Sanctions de " + target.getName());
        ItemBuilder historique = new ItemBuilder(Material.BOOK).setName(ChatColor.GOLD + "Historique de sanctions");
        ItemBuilder logs = new ItemBuilder(Material.LAPIS_LAZULI).setName(ChatColor.BLUE + "Voir les logs");
        ItemBuilder retour =  new ItemBuilder(Material.SUNFLOWER).setName(ChatColor.YELLOW + "Retour");

        Inventory options = Bukkit.createInventory(new AdminOptionHolder(target), 9, "Options");
        options.setItem(0, se_teleporter.toItemStack());
        options.setItem(1, teleporter_ici.toItemStack());
        options.setItem(2, inventaire.toItemStack());
        options.setItem(3, check.toItemStack());
        options.setItem(4, historique.toItemStack());
        options.setItem(5, logs.toItemStack());
        options.setItem(8, retour.toItemStack());
        player.openInventory(options);
    }

    public static void inventaire(Player player, OfflinePlayer target)
    {
        Inventory inventaire = Bukkit.createInventory(new AdminOptionHolder(target), 45, "Inventaire de " + target.getName());
        for (int slot = 0; slot <= 35; slot++)
        {
            if (target.getPlayer().getInventory().getItem(slot) != null)
            {
                inventaire.setItem(slot, target.getPlayer().getInventory().getItem(slot));
            }
        }
        inventaire.setItem(36, target.getPlayer().getInventory().getItem(EquipmentSlot.HEAD));
        inventaire.setItem(37, target.getPlayer().getInventory().getItem(EquipmentSlot.CHEST));
        inventaire.setItem(38, target.getPlayer().getInventory().getItem(EquipmentSlot.LEGS));
        inventaire.setItem(39, target.getPlayer().getInventory().getItem(EquipmentSlot.FEET));
        inventaire.setItem(40, target.getPlayer().getInventory().getItemInOffHand());
        player.openInventory(inventaire);
    }

    public static void panel_admin(Player player, OfflinePlayer target)
    {

        ItemBuilder sanctions_admin = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + "Sanctions Administratives");
        ItemBuilder direction = new ItemBuilder(Material.RED_CONCRETE).setName(ChatColor.DARK_RED + "Menu direction");
        ItemBuilder retour = new ItemBuilder(Material.NETHER_STAR).setName(ChatColor.YELLOW + "Retour");

        Inventory admin = Bukkit.createInventory(new AdminOptionHolder(target), 9, "Administration");
        admin.setItem(0, sanctions_admin.toItemStack());
        admin.setItem(7, direction.toItemStack());
        admin.setItem(8, retour.toItemStack());
        player.openInventory(admin);
    }

    public static void panel_direction(Player player, OfflinePlayer target, String menu)
    {
        if (menu.equals("Direction"))
        {
            ItemBuilder tete = new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(target.getName()).setName(ChatColor.GREEN + "" + ChatColor.BOLD + "Menu de " + target.getName());
            ItemBuilder retour = new ItemBuilder(Material.SUNFLOWER).setName(ChatColor.YELLOW + "Retour au menu");
            ItemBuilder erreurs_staff = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + "Erreurs staff");
            ItemBuilder rank_up = new ItemBuilder(Material.BOOK).setName(ChatColor.YELLOW + "RankUp " + target.getName());

            Inventory direction = Bukkit.createInventory(new AdminOptionHolder(target), 36, "Menu direction");
            direction.setItem(4, tete.toItemStack());
            direction.setItem(8, retour.toItemStack());
            direction.setItem(9, erreurs_staff.toItemStack());
            direction.setItem(10, rank_up.toItemStack());
            player.openInventory(direction);
        }
        else if (menu.equals("Erreurs staff"))
        {
            ItemBuilder fly_sans_vanish = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + "Fly Sans Vanish");
            ItemBuilder god_en_warzone = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + "God en WZ").setLore("Pas valable si la personne est en vanish");
            ItemBuilder abus_de_pouvoir = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + "Abus de pouvoir").setLore("Dans certains cas, passible d'un dérank immédiat");
            ItemBuilder abus_de_permissions = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + "Abus de permissions");
            ItemBuilder non_respect = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + "Non respect de la hiérarchie");
            ItemBuilder reglementation = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + "Non respect | Règlement staff");
            ItemBuilder freewarn = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + " FreeWarn");
            ItemBuilder freeban = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + "FreeBan");
            ItemBuilder absence = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + "Abscence non justifiée");
            ItemBuilder favoritisme = new ItemBuilder(Material.REDSTONE).setName(ChatColor.DARK_RED + "Favoritisme").setLore("A ne pas confondre avec l'attribution de circonstances atténuantes");
            ItemBuilder tete = new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(target.getName());
            ItemBuilder retour = new ItemBuilder(Material.SUNFLOWER).setName(ChatColor.YELLOW + "Retour");

            Inventory direction = Bukkit.createInventory(new AdminOptionHolder(target), 27, "Erreurs staff : " + target.getName());
            direction.setItem(0, fly_sans_vanish.toItemStack());
            direction.setItem(1, god_en_warzone.toItemStack());
            direction.setItem(2, abus_de_pouvoir.toItemStack());
            direction.setItem(3, abus_de_permissions.toItemStack());
            direction.setItem(4, non_respect.toItemStack());
            direction.setItem(5, reglementation.toItemStack());
            direction.setItem(6, freewarn.toItemStack());
            direction.setItem(7, freeban.toItemStack());
            direction.setItem(8, absence.toItemStack());
            direction.setItem(9, favoritisme.toItemStack());
            direction.setItem(22, tete.toItemStack());
            direction.setItem(26, retour.toItemStack());
            player.openInventory(direction);
        }
        else if (menu.equals("RankUp"))
        {
            ItemBuilder builder = new ItemBuilder(Material.YELLOW_WOOL).setName(ChatColor.YELLOW + "Builder");
            ItemBuilder modo_stagiaire = new ItemBuilder(Material.LIME_WOOL).setName(ChatColor.GREEN + "[Modérateur stagiaire]");
            ItemBuilder moderateur = new ItemBuilder(Material.LIME_WOOL).setName(ChatColor.GREEN + "[Modérateur]");
            ItemBuilder administrateur = new ItemBuilder(Material.LIGHT_BLUE_WOOL).setName(ChatColor.BLUE + "[Administrateur]");
            ItemBuilder grade_direction = new ItemBuilder(Material.WRITABLE_BOOK).setName(ChatColor.YELLOW + "Grade direction");
            ItemBuilder retour = new ItemBuilder(Material.SUNFLOWER).setName(ChatColor.YELLOW + "Retour");
            ItemBuilder mettre_op = new ItemBuilder(Material.RED_DYE).setName(ChatColor.RED + "Mettre op");
            ItemBuilder unrank = new ItemBuilder(Material.PAPER).setName(ChatColor.RED + "UnRank le joueur");
            ItemBuilder deop_le_joueur = new ItemBuilder(Material.RED_DYE).setName(ChatColor.RED + "DeOp le joueur");

            Inventory rankup = Bukkit.createInventory(new AdminOptionHolder(target), 27, "RankUp | " + target.getName());
            rankup.setItem(0, builder.toItemStack());
            rankup.setItem(1, modo_stagiaire.toItemStack());
            rankup.setItem(2, moderateur.toItemStack());
            rankup.setItem(3, administrateur.toItemStack());
            rankup.setItem(8, grade_direction.toItemStack());
            rankup.setItem(18, retour.toItemStack());
            rankup.setItem(24, mettre_op.toItemStack());
            rankup.setItem(25, unrank.toItemStack());
            rankup.setItem(26, deop_le_joueur.toItemStack());
            player.openInventory(rankup);
        }
        else if (menu.equals("Grade Direction"))
        {

            ItemBuilder fondateur = new ItemBuilder(Material.RED_WOOL).setName(ChatColor.DARK_RED + "[Fondateur]");
            ItemBuilder retour = new ItemBuilder(Material.SUNFLOWER).setName(ChatColor.YELLOW + "Retour");

            Inventory direction = Bukkit.createInventory(new AdminOptionHolder(target), 9, "Grade Direction");
            direction.setItem(2, fondateur.toItemStack());
            direction.setItem(8, retour.toItemStack());
            player.openInventory(direction);
        }
    }

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

    public static void panel_admin(Player player, OfflinePlayer target, String name)
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
    }
}
