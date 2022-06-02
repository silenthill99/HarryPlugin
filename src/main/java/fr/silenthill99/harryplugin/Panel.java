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

public class Panel
{

    private static String titre;

    public static String titre() {
        return titre;
    }

    public static void panel_modo(Player player, OfflinePlayer target)
    {
        player.closeInventory();
        ItemBuilder tete = new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(target.getName());
        ItemBuilder sanctionner = new ItemBuilder(Material.GREEN_WOOL).setName(ChatColor.DARK_GREEN + "Sanctionner " + target.getName());
        ItemBuilder options = new ItemBuilder(Material.LAPIS_LAZULI).setName(ChatColor.AQUA + "Options");

        Inventory menu = Bukkit.createInventory(new AdminOptionHolder(target), 27, "Menu de " + target.getName());
        menu.setItem(4, tete.toItemStack());
        menu.setItem(10,sanctionner.toItemStack());
        menu.setItem(16, options.toItemStack());
        player.openInventory(menu);
    }

    public static void panel_modo(Player player, OfflinePlayer target, String titre)
    {
        Panel.titre = titre;
        player.closeInventory();
        ItemBuilder tete;
        ItemBuilder retour;
        ItemBuilder avertir;
        ItemBuilder bannir_temporairement;
        ItemBuilder bannir;
        ItemBuilder kick;
        ItemBuilder freeze;
        ItemBuilder tempmute;
        ItemBuilder mute;
        switch (titre) {
            case "sanctionner": {
                tete = new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(target.getName());
                retour = new ItemBuilder(Material.SUNFLOWER).setName(ChatColor.YELLOW + "Retour");
                avertir = new ItemBuilder(Material.GREEN_WOOL).setName(ChatColor.DARK_GREEN + "Avertir");
                bannir_temporairement = new ItemBuilder(Material.ORANGE_WOOL).setName(ChatColor.GOLD + "Bannir temporairement");
                bannir = new ItemBuilder(Material.RED_WOOL).setName(ChatColor.DARK_RED + "Bannir");
                kick = new ItemBuilder(Material.PURPLE_WOOL).setName(ChatColor.DARK_PURPLE + "Kick");
                freeze = new ItemBuilder(Material.LIGHT_BLUE_WOOL).setName(ChatColor.AQUA + "Freeze");
                tempmute = new ItemBuilder(Material.PINK_WOOL).setName(ChatColor.LIGHT_PURPLE + "TempMute");
                mute = new ItemBuilder(Material.MAGENTA_WOOL).setName(ChatColor.LIGHT_PURPLE + "Mute");

                Inventory sanctionner = Bukkit.createInventory(new AdminOptionHolder(target), 54, "Sanctionner " + target.getName());
                sanctionner.setItem(4, tete.toItemStack());
                sanctionner.setItem(8, retour.toItemStack());
                sanctionner.setItem(10, avertir.toItemStack());
                sanctionner.setItem(11, bannir_temporairement.toItemStack());
                sanctionner.setItem(12, bannir.toItemStack());
                sanctionner.setItem(13, kick.toItemStack());
                sanctionner.setItem(14, freeze.toItemStack());
                sanctionner.setItem(15, tempmute.toItemStack());
                sanctionner.setItem(16, mute.toItemStack());
                for (int slot = 18; slot <= 26; slot++) {
                    sanctionner.setItem(slot, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
                }
                player.openInventory(sanctionner);
                break;
            }
            case "avertir": {
                tete = new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(target.getName());
                retour = new ItemBuilder(Material.SUNFLOWER).setName(ChatColor.YELLOW + "Retour");
                avertir = new ItemBuilder(Material.GREEN_WOOL).setName(ChatColor.DARK_GREEN + "Avertir").addEnchantment(Enchantment.DAMAGE_ALL, 5);
                bannir_temporairement = new ItemBuilder(Material.ORANGE_WOOL).setName(ChatColor.GOLD + "Bannir temporairement");
                bannir = new ItemBuilder(Material.RED_WOOL).setName(ChatColor.DARK_RED + "Bannir");
                kick = new ItemBuilder(Material.PURPLE_WOOL).setName(ChatColor.DARK_PURPLE + "Kick");
                freeze = new ItemBuilder(Material.LIGHT_BLUE_WOOL).setName(ChatColor.AQUA + "Freeze");
                tempmute = new ItemBuilder(Material.PINK_WOOL).setName(ChatColor.LIGHT_PURPLE + "TempMute");
                mute = new ItemBuilder(Material.MAGENTA_WOOL).setName(ChatColor.LIGHT_PURPLE + "Mute");

                Inventory sanctionner = Bukkit.createInventory(new AdminOptionHolder(target), 54, "Sanctionner " + target.getName());
                sanctionner.setItem(4, tete.toItemStack());
                sanctionner.setItem(8, retour.toItemStack());
                sanctionner.setItem(10, avertir.toItemStack());
                sanctionner.setItem(11, bannir_temporairement.toItemStack());
                sanctionner.setItem(12, bannir.toItemStack());
                sanctionner.setItem(13, kick.toItemStack());
                sanctionner.setItem(14, freeze.toItemStack());
                sanctionner.setItem(15, tempmute.toItemStack());
                sanctionner.setItem(16, mute.toItemStack());
                for (int slot = 18; slot <= 26; slot++) {
                    sanctionner.setItem(slot, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
                }
                player.openInventory(sanctionner);
                break;
            }
            case "Bannir temporairement": {
                tete = new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(target.getName());
                retour = new ItemBuilder(Material.SUNFLOWER).setName(ChatColor.YELLOW + "Retour");
                avertir = new ItemBuilder(Material.GREEN_WOOL).setName(ChatColor.DARK_GREEN + "Avertir");
                bannir_temporairement = new ItemBuilder(Material.ORANGE_WOOL).setName(ChatColor.GOLD + "Bannir temporairement").addEnchantment(Enchantment.DAMAGE_ALL, 5);
                bannir = new ItemBuilder(Material.RED_WOOL).setName(ChatColor.DARK_RED + "Bannir");
                kick = new ItemBuilder(Material.PURPLE_WOOL).setName(ChatColor.DARK_PURPLE + "Kick");
                freeze = new ItemBuilder(Material.LIGHT_BLUE_WOOL).setName(ChatColor.AQUA + "Freeze");
                tempmute = new ItemBuilder(Material.PINK_WOOL).setName(ChatColor.LIGHT_PURPLE + "TempMute");
                mute = new ItemBuilder(Material.MAGENTA_WOOL).setName(ChatColor.LIGHT_PURPLE + "Mute");

                Inventory sanctionner = Bukkit.createInventory(new AdminOptionHolder(target), 54, "Sanctionner " + target.getName());
                sanctionner.setItem(4, tete.toItemStack());
                sanctionner.setItem(8, retour.toItemStack());
                sanctionner.setItem(10, avertir.toItemStack());
                sanctionner.setItem(11, bannir_temporairement.toItemStack());
                sanctionner.setItem(12, bannir.toItemStack());
                sanctionner.setItem(13, kick.toItemStack());
                sanctionner.setItem(14, freeze.toItemStack());
                sanctionner.setItem(15, tempmute.toItemStack());
                sanctionner.setItem(16, mute.toItemStack());
                for (int slot = 18; slot <= 26; slot++) {
                    sanctionner.setItem(slot, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
                }
                player.openInventory(sanctionner);
                break;
            }
            case "Bannir": {
                tete = new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(target.getName());
                retour = new ItemBuilder(Material.SUNFLOWER).setName(ChatColor.YELLOW + "Retour");
                avertir = new ItemBuilder(Material.GREEN_WOOL).setName(ChatColor.DARK_GREEN + "Avertir");
                bannir_temporairement = new ItemBuilder(Material.ORANGE_WOOL).setName(ChatColor.GOLD + "Bannir temporairement");
                bannir = new ItemBuilder(Material.RED_WOOL).setName(ChatColor.DARK_RED + "Bannir").addEnchantment(Enchantment.DAMAGE_ALL, 5);
                kick = new ItemBuilder(Material.PURPLE_WOOL).setName(ChatColor.DARK_PURPLE + "Kick");
                freeze = new ItemBuilder(Material.LIGHT_BLUE_WOOL).setName(ChatColor.AQUA + "Freeze");
                tempmute = new ItemBuilder(Material.PINK_WOOL).setName(ChatColor.LIGHT_PURPLE + "TempMute");
                mute = new ItemBuilder(Material.MAGENTA_WOOL).setName(ChatColor.LIGHT_PURPLE + "Mute");

                Inventory sanctionner = Bukkit.createInventory(new AdminOptionHolder(target), 54, "Sanctionner " + target.getName());
                sanctionner.setItem(4, tete.toItemStack());
                sanctionner.setItem(8, retour.toItemStack());
                sanctionner.setItem(10, avertir.toItemStack());
                sanctionner.setItem(11, bannir_temporairement.toItemStack());
                sanctionner.setItem(12, bannir.toItemStack());
                sanctionner.setItem(13, kick.toItemStack());
                sanctionner.setItem(14, freeze.toItemStack());
                sanctionner.setItem(15, tempmute.toItemStack());
                sanctionner.setItem(16, mute.toItemStack());
                for (int slot = 18; slot <= 26; slot++) {
                    sanctionner.setItem(slot, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
                }
                player.openInventory(sanctionner);
                break;
            }
            case "Kick": {
                tete = new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(target.getName());
                retour = new ItemBuilder(Material.SUNFLOWER).setName(ChatColor.YELLOW + "Retour");
                avertir = new ItemBuilder(Material.GREEN_WOOL).setName(ChatColor.DARK_GREEN + "Avertir");
                bannir_temporairement = new ItemBuilder(Material.ORANGE_WOOL).setName(ChatColor.GOLD + "Bannir temporairement");
                bannir = new ItemBuilder(Material.RED_WOOL).setName(ChatColor.DARK_RED + "Bannir");
                kick = new ItemBuilder(Material.PURPLE_WOOL).setName(ChatColor.DARK_PURPLE + "Kick").addEnchantment(Enchantment.DAMAGE_ALL, 5);
                freeze = new ItemBuilder(Material.LIGHT_BLUE_WOOL).setName(ChatColor.AQUA + "Freeze");
                tempmute = new ItemBuilder(Material.PINK_WOOL).setName(ChatColor.LIGHT_PURPLE + "TempMute");
                mute = new ItemBuilder(Material.MAGENTA_WOOL).setName(ChatColor.LIGHT_PURPLE + "Mute");

                Inventory sanctionner = Bukkit.createInventory(new AdminOptionHolder(target), 54, "Sanctionner " + target.getName());
                sanctionner.setItem(4, tete.toItemStack());
                sanctionner.setItem(8, retour.toItemStack());
                sanctionner.setItem(10, avertir.toItemStack());
                sanctionner.setItem(11, bannir_temporairement.toItemStack());
                sanctionner.setItem(12, bannir.toItemStack());
                sanctionner.setItem(13, kick.toItemStack());
                sanctionner.setItem(14, freeze.toItemStack());
                sanctionner.setItem(15, tempmute.toItemStack());
                sanctionner.setItem(16, mute.toItemStack());
                for (int slot = 18; slot <= 26; slot++) {
                    sanctionner.setItem(slot, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
                }
                player.openInventory(sanctionner);
                break;
            }
            case "Freeze": {
                tete = new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(target.getName());
                retour = new ItemBuilder(Material.SUNFLOWER).setName(ChatColor.YELLOW + "Retour");
                avertir = new ItemBuilder(Material.GREEN_WOOL).setName(ChatColor.DARK_GREEN + "Avertir");
                bannir_temporairement = new ItemBuilder(Material.ORANGE_WOOL).setName(ChatColor.GOLD + "Bannir temporairement");
                bannir = new ItemBuilder(Material.RED_WOOL).setName(ChatColor.DARK_RED + "Bannir");
                kick = new ItemBuilder(Material.PURPLE_WOOL).setName(ChatColor.DARK_PURPLE + "Kick");
                freeze = new ItemBuilder(Material.LIGHT_BLUE_WOOL).setName(ChatColor.AQUA + "Freeze").addEnchantment(Enchantment.DAMAGE_ALL, 5);
                tempmute = new ItemBuilder(Material.PINK_WOOL).setName(ChatColor.LIGHT_PURPLE + "TempMute");
                mute = new ItemBuilder(Material.MAGENTA_WOOL).setName(ChatColor.LIGHT_PURPLE + "Mute");

                Inventory sanctionner = Bukkit.createInventory(new AdminOptionHolder(target), 54, "Sanctionner " + target.getName());
                sanctionner.setItem(4, tete.toItemStack());
                sanctionner.setItem(8, retour.toItemStack());
                sanctionner.setItem(10, avertir.toItemStack());
                sanctionner.setItem(11, bannir_temporairement.toItemStack());
                sanctionner.setItem(12, bannir.toItemStack());
                sanctionner.setItem(13, kick.toItemStack());
                sanctionner.setItem(14, freeze.toItemStack());
                sanctionner.setItem(15, tempmute.toItemStack());
                sanctionner.setItem(16, mute.toItemStack());
                for (int slot = 18; slot <= 26; slot++) {
                    sanctionner.setItem(slot, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
                }
                player.openInventory(sanctionner);
                break;
            }
            case "TempMute": {
                tete = new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(target.getName());
                retour = new ItemBuilder(Material.SUNFLOWER).setName(ChatColor.YELLOW + "Retour");
                avertir = new ItemBuilder(Material.GREEN_WOOL).setName(ChatColor.DARK_GREEN + "Avertir");
                bannir_temporairement = new ItemBuilder(Material.ORANGE_WOOL).setName(ChatColor.GOLD + "Bannir temporairement");
                bannir = new ItemBuilder(Material.RED_WOOL).setName(ChatColor.DARK_RED + "Bannir");
                kick = new ItemBuilder(Material.PURPLE_WOOL).setName(ChatColor.DARK_PURPLE + "Kick");
                freeze = new ItemBuilder(Material.LIGHT_BLUE_WOOL).setName(ChatColor.AQUA + "Freeze");
                tempmute = new ItemBuilder(Material.PINK_WOOL).setName(ChatColor.LIGHT_PURPLE + "TempMute").addEnchantment(Enchantment.DAMAGE_ALL, 5);
                mute = new ItemBuilder(Material.MAGENTA_WOOL).setName(ChatColor.LIGHT_PURPLE + "Mute");

                Inventory sanctionner = Bukkit.createInventory(new AdminOptionHolder(target), 54, "Sanctionner " + target.getName());
                sanctionner.setItem(4, tete.toItemStack());
                sanctionner.setItem(8, retour.toItemStack());
                sanctionner.setItem(10, avertir.toItemStack());
                sanctionner.setItem(11, bannir_temporairement.toItemStack());
                sanctionner.setItem(12, bannir.toItemStack());
                sanctionner.setItem(13, kick.toItemStack());
                sanctionner.setItem(14, freeze.toItemStack());
                sanctionner.setItem(15, tempmute.toItemStack());
                sanctionner.setItem(16, mute.toItemStack());
                for (int slot = 18; slot <= 26; slot++) {
                    sanctionner.setItem(slot, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
                }
                player.openInventory(sanctionner);
                break;
            }
            case "Mute": {
                tete = new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(target.getName());
                retour = new ItemBuilder(Material.SUNFLOWER).setName(ChatColor.YELLOW + "Retour");
                avertir = new ItemBuilder(Material.GREEN_WOOL).setName(ChatColor.DARK_GREEN + "Avertir");
                bannir_temporairement = new ItemBuilder(Material.ORANGE_WOOL).setName(ChatColor.GOLD + "Bannir temporairement");
                bannir = new ItemBuilder(Material.RED_WOOL).setName(ChatColor.DARK_RED + "Bannir");
                kick = new ItemBuilder(Material.PURPLE_WOOL).setName(ChatColor.DARK_PURPLE + "Kick");
                freeze = new ItemBuilder(Material.LIGHT_BLUE_WOOL).setName(ChatColor.AQUA + "Freeze");
                tempmute = new ItemBuilder(Material.PINK_WOOL).setName(ChatColor.LIGHT_PURPLE + "TempMute");
                mute = new ItemBuilder(Material.MAGENTA_WOOL).setName(ChatColor.LIGHT_PURPLE + "Mute").addEnchantment(Enchantment.DAMAGE_ALL, 5);

                Inventory sanctionner = Bukkit.createInventory(new AdminOptionHolder(target), 54, "Sanctionner " + target.getName());
                sanctionner.setItem(4, tete.toItemStack());
                sanctionner.setItem(8, retour.toItemStack());
                sanctionner.setItem(10, avertir.toItemStack());
                sanctionner.setItem(11, bannir_temporairement.toItemStack());
                sanctionner.setItem(12, bannir.toItemStack());
                sanctionner.setItem(13, kick.toItemStack());
                sanctionner.setItem(14, freeze.toItemStack());
                sanctionner.setItem(15, tempmute.toItemStack());
                sanctionner.setItem(16, mute.toItemStack());
                for (int slot = 18; slot <= 26; slot++) {
                    sanctionner.setItem(slot, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
                }
                player.openInventory(sanctionner);
                break;
            }
        }

    }

    public static void options(Player player, OfflinePlayer target)
    {

        ItemBuilder se_teleporter = new ItemBuilder(Material.ENDER_PEARL).setName(ChatColor.GREEN + "Se téléporter");
        ItemBuilder teleporter_ici = new ItemBuilder(Material.ENDER_EYE).setName(ChatColor.GREEN + "Téléporter ici");
        ItemBuilder inventaire = new ItemBuilder(Material.CHEST).setName(ChatColor.GOLD + "Voir l'inventaire");
        ItemBuilder retour =  new ItemBuilder(Material.SUNFLOWER).setName(ChatColor.YELLOW + "Retour");

        Inventory options = Bukkit.createInventory(new AdminOptionHolder(target), 9, "Options");
        options.setItem(0, se_teleporter.toItemStack());
        options.setItem(1, teleporter_ici.toItemStack());
        options.setItem(2, inventaire.toItemStack());
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

}
