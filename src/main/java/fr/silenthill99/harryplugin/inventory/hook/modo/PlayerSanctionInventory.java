package fr.silenthill99.harryplugin.inventory.hook.modo;

import fr.silenthill99.harryplugin.AdminOptionHolder;
import fr.silenthill99.harryplugin.ItemBuilder;
import fr.silenthill99.harryplugin.Panel;
import fr.silenthill99.harryplugin.inventory.AbstractInventory;
import fr.silenthill99.harryplugin.inventory.InventoryManager;
import fr.silenthill99.harryplugin.inventory.InventoryType;
import fr.silenthill99.harryplugin.inventory.holder.modo.PlayerSanctionHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerSanctionInventory extends AbstractInventory<PlayerSanctionHolder> {
    public PlayerSanctionInventory() {
        super(PlayerSanctionHolder.class);
    }

    @Override
    public void openInventory(Player p, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        SanctionType type = (SanctionType) args[1];
        int page = (int) args[2];

        p.closeInventory();
        ItemStack tete = new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(target.getName()).toItemStack();
        ItemStack retour = new ItemBuilder(Material.SUNFLOWER).setName(ChatColor.YELLOW + "Retour").toItemStack();
        ItemBuilder avertir = new ItemBuilder(Material.GREEN_WOOL).setName(ChatColor.DARK_GREEN + "Avertir");
        ItemBuilder bannir_temporairement = new ItemBuilder(Material.ORANGE_WOOL).setName(ChatColor.GOLD + "Bannir temporairement");
        ItemBuilder bannir = new ItemBuilder(Material.RED_WOOL).setName(ChatColor.DARK_RED + "Bannir");
        ItemBuilder kick = new ItemBuilder(Material.PURPLE_WOOL).setName(ChatColor.DARK_PURPLE + "Kick");
        ItemBuilder freeze = new ItemBuilder(Material.LIGHT_BLUE_WOOL).setName(ChatColor.AQUA + "Freeze");
        ItemBuilder tempmute = new ItemBuilder(Material.PINK_WOOL).setName(ChatColor.LIGHT_PURPLE + "TempMute");
        ItemBuilder mute = new ItemBuilder(Material.MAGENTA_WOOL).setName(ChatColor.LIGHT_PURPLE + "Mute");

        Inventory sanctionner = createInventory(new PlayerSanctionHolder(target, type, page), 54, "Sanctionner " + target.getName());
        sanctionner.setItem(4, tete);
        sanctionner.setItem(8, retour);
        sanctionner.setItem(10, avertir.toItemStack());
        sanctionner.setItem(11, bannir_temporairement.toItemStack());
        sanctionner.setItem(12, bannir.toItemStack());
        sanctionner.setItem(13, kick.toItemStack());
        sanctionner.setItem(14, freeze.toItemStack());
        sanctionner.setItem(15, tempmute.toItemStack());
        sanctionner.setItem(16, mute.toItemStack());
        for (int slot = 18; slot <= 26; slot++)
        {
            sanctionner.setItem(slot, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
        }
        switch (type)
        {
            case WARN: {
                avertir.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                ItemBuilder no_fear = new ItemBuilder(Material.GREEN_WOOL).setName(ChatColor.DARK_GREEN + "NoFearRP");
                ItemBuilder metagaming = new ItemBuilder(Material.GREEN_WOOL).setName(ChatColor.DARK_GREEN + "MétaGaming");
                sanctionner.setItem(10, avertir.toItemStack());
                sanctionner.setItem(27, no_fear.toItemStack());
                sanctionner.setItem(28, metagaming.toItemStack());
                break;
            }
            case BAN_TEMP: {
                bannir_temporairement.addEnchantment(Enchantment.DAMAGE_ALL, 5);

                ItemBuilder sortilege = new ItemBuilder(Material.ORANGE_WOOL).setName(ChatColor.GOLD + "Utilisation d'un des 3 sorts impardonnables").setLore("Durée : 1 mois");

                sanctionner.setItem(11, bannir_temporairement.toItemStack());
                sanctionner.setItem(27, sortilege.toItemStack());
                p.openInventory(sanctionner);
                break;
            }
            case BAN: {
                bannir.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                sanctionner.setItem(12, bannir.toItemStack());
                break;
            }
            case KICK: {
                kick.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                sanctionner.setItem(13, kick.toItemStack());
                break;
            }
            case FREEZE: {
                freeze.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                sanctionner.setItem(14, freeze.toItemStack());
                break;
            }
            case MUTE_TEMP: {
                tempmute.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                sanctionner.setItem(15, tempmute.toItemStack());
                break;
            }
            case MUTE: {
                mute.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                sanctionner.setItem(16, mute.toItemStack());
                break;
            }
        }
        p.openInventory(sanctionner);

    }

    @Override
    public void manageInventory(InventoryClickEvent e, ItemStack current, Player player, PlayerSanctionHolder holder) {
        OfflinePlayer target = holder.getPlayer();
        SanctionType type = holder.getType();
        int page = holder.getPage();
        e.setCancelled(true);
        switch (current.getType())
        {
            case SUNFLOWER:
                InventoryManager.openInventory(player, InventoryType.MODO_PLAYER_MENU);
                break;
            case GREEN_WOOL:
                if (current.getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "Avertir"))
                {
                    openInventory(player, target, SanctionType.WARN, 1);
                    return;
                }
                if (current.getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "NoFearRP"))
                {
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "warn " + target.getName() + " NoFearRP");
                    return;
                }
                if (current.getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "MétaGaming"))
                {
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "warn " + target.getName() + " Métagaming");
                    return;
                }
                break;
            case ORANGE_WOOL:
                if (current.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Bannir temporairement"))
                {
                    openInventory(player, target, SanctionType.BAN_TEMP, 1);
                }
                else if (current.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Utilisation d'un des 3 sorts impardonnables"))
                {
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "tempipban " + target.getName() + " 1mo Mauvaise utilisation d'1 des 3 sortilèges impardonnables");
                }
                break;
            case RED_WOOL:
                if (current.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "Bannir"))
                {
                    openInventory(player, target, SanctionType.BAN, 1);
                }
                break;
            case PURPLE_WOOL:
                if (current.getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Kick"))
                {
                    openInventory(player, target, SanctionType.KICK, 1);
                }
                break;
            case LIGHT_BLUE_WOOL:
                if (current.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Freeze"))
                {
                    openInventory(player, target, SanctionType.FREEZE, 1);
                }
                break;
            case PINK_WOOL:
                if (current.getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE + "TempMute"))
                {
                    openInventory(player, target, SanctionType.MUTE_TEMP, 1);
                }
            case MAGENTA_WOOL:
                if (current.getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE + "Mute"))
                {
                    if (!Panel.titre.get(player).equalsIgnoreCase("Mute"))
                    {
                        openInventory(player, target, SanctionType.MUTE, 1);
                    }
                }
                break;
            default:
                break;
        }
    }

    public static enum SanctionType
    {
        WARN, BAN, BAN_TEMP, FREEZE, KICK, MUTE, MUTE_TEMP;
    }
}
