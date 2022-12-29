package fr.silenthill99.harryplugin.inventory.hook.modo;

import fr.silenthill99.harryplugin.ItemBuilder;
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
    ItemBuilder avertir = new ItemBuilder(Material.GREEN_WOOL).setName(ChatColor.DARK_GREEN + "Avertir");
    ItemBuilder bannir_temporairement = new ItemBuilder(Material.ORANGE_WOOL).setName(ChatColor.GOLD + "Bannir temporairement");
    ItemBuilder bannir = new ItemBuilder(Material.RED_WOOL).setName(ChatColor.DARK_RED + "Bannir");
    ItemBuilder kick = new ItemBuilder(Material.PURPLE_WOOL).setName(ChatColor.DARK_PURPLE + "Kick");
    ItemBuilder freeze = new ItemBuilder(Material.LIGHT_BLUE_WOOL).setName(ChatColor.AQUA + "Freeze");
    ItemBuilder tempmute = new ItemBuilder(Material.PINK_WOOL).setName(ChatColor.LIGHT_PURPLE + "TempMute");
    ItemBuilder mute = new ItemBuilder(Material.MAGENTA_WOOL).setName(ChatColor.LIGHT_PURPLE + "Mute");
    public PlayerSanctionInventory() {
        super(PlayerSanctionHolder.class);
    }

    @Override
    public void openInventory(Player p, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        SanctionType type = (SanctionType) args[1];
        int page = (int) args[2];
        PlayerSanctionHolder holder = new PlayerSanctionHolder(target, type, page);

        ItemStack tete = new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(target.getName()).toItemStack();
        ItemStack page_suivante = new ItemBuilder(Material.GREEN_DYE).setName(ChatColor.GREEN + "Page suivante").toItemStack();
        ItemStack page_precedente = new ItemBuilder(Material.RED_DYE).setName(ChatColor.RED + "Page précédente").toItemStack();
        Inventory inv = createInventory(holder, 54, "Sanctionner " + target.getName());
        inv.setItem(4, tete);
        inv.setItem(8, RETOUR);
        inv.setItem(10, avertir.toItemStack());
        inv.setItem(11, bannir_temporairement.toItemStack());
        inv.setItem(12, bannir.toItemStack());
        inv.setItem(13, kick.toItemStack());
        inv.setItem(14, freeze.toItemStack());
        inv.setItem(15, tempmute.toItemStack());
        inv.setItem(16, mute.toItemStack());
        for (int slot = 18; slot <= 26; slot++)
        {
            inv.setItem(slot, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
        }
        if (!type.equals(SanctionType.MENU))
        {
            inv.setItem(53, page_suivante);
            inv.setItem(45, page_precedente);
        }
        switch (type)
        {
            case WARN: {
                avertir.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                inv.setItem(10, avertir.toItemStack());
                int slot = 27;
                for (Warns warns : Warns.values())
                {
                    if (slot == warns.getPage())
                    {
                        holder.warns.put(slot, warns);
                        inv.setItem(slot++, new ItemBuilder(Material.GREEN_WOOL).setName(ChatColor.DARK_GREEN + warns.getName()).toItemStack());
                    }
                }
                break;
            }
            case BAN_TEMP: {
                bannir_temporairement.addEnchantment(Enchantment.DAMAGE_ALL, 5);

                ItemBuilder sortilege = new ItemBuilder(Material.ORANGE_WOOL).setName(ChatColor.GOLD + "Utilisation d'un des 3 sorts impardonnables").setLore("Durée : 1 mois");

                inv.setItem(11, bannir_temporairement.toItemStack());
                inv.setItem(27, sortilege.toItemStack());
                p.openInventory(inv);
                break;
            }
            case BAN: {
                bannir.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                inv.setItem(12, bannir.toItemStack());
                int slot = 27;
                for (Ban ban : Ban.values())
                {
                    holder.ban.put(slot, ban);
                    inv.setItem(slot++, new ItemBuilder(Material.RED_WOOL).setName(ChatColor.DARK_RED + ban.getName()).toItemStack());
                }
                break;
            }
            case KICK: {
                kick.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                inv.setItem(13, kick.toItemStack());
                break;
            }
            case FREEZE: {
                freeze.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                inv.setItem(14, freeze.toItemStack());
                break;
            }
            case MUTE_TEMP: {
                tempmute.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                inv.setItem(15, tempmute.toItemStack());
                break;
            }
            case MUTE: {
                mute.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                inv.setItem(16, mute.toItemStack());
                break;
            }
        }
        p.openInventory(inv);

    }

    @Override
    public void manageInventory(InventoryClickEvent e, ItemStack current, Player player, PlayerSanctionHolder holder) {
        OfflinePlayer target = holder.getPlayer();
        SanctionType type = holder.getType();
        int page = holder.getPage();
        Ban ban = holder.ban.get(e.getSlot());
        Warns warns = holder.warns.get(e.getSlot());
        e.setCancelled(true);
        switch (current.getType())
        {
            case SUNFLOWER:
                InventoryManager.openInventory(player, InventoryType.MODO_PLAYER_MENU, target);
                break;
            case GREEN_WOOL:
            {
                if (current.isSimilar(avertir.toItemStack()) && !type.equals(SanctionType.WARN)) {
                    openInventory(player, target, SanctionType.WARN, 1);
                    return;
                }
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " " + warns.getName());
                break;
            }
            case ORANGE_WOOL: {
                if (current.isSimilar(bannir_temporairement.toItemStack()) && !type.equals(SanctionType.BAN_TEMP)) {
                    openInventory(player, target, SanctionType.BAN_TEMP, 1);
                    return;
                } else if (current.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Utilisation d'un des 3 sorts impardonnables")) {
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "tempipban " + target.getName() + " 1mo Mauvaise utilisation d'1 des 3 sortilèges impardonnables");
                }
                break;
            }
            case RED_WOOL:
                if (current.isSimilar(bannir.toItemStack()) && !type.equals(SanctionType.BAN))
                {
                    openInventory(player, target, SanctionType.BAN, 1);
                    return;
                }
                player.closeInventory();
                Bukkit.dispatchCommand(player, "ipban " + target.getName() + " " + ban.getName());
                break;
            case PURPLE_WOOL:
                if (current.isSimilar(kick.toItemStack()) && !type.equals(SanctionType.KICK))
                {
                    openInventory(player, target, SanctionType.KICK, 1);
                    return;
                }
                break;
            case LIGHT_BLUE_WOOL:
                if (current.isSimilar(freeze.toItemStack()) && !type.equals(SanctionType.FREEZE))
                {
                    openInventory(player, target, SanctionType.FREEZE, 1);
                    return;
                }
                break;
            case PINK_WOOL:
                if (current.isSimilar(tempmute.toItemStack()) && !type.equals(SanctionType.MUTE_TEMP))
                {
                    openInventory(player, target, SanctionType.MUTE_TEMP, 1);
                    return;
                }
            case MAGENTA_WOOL:
                if (current.isSimilar(mute.toItemStack()) && !type.equals(SanctionType.MUTE))
                {
                    openInventory(player, target, SanctionType.MUTE, 1);
                    return;
                }
                break;
            case GREEN_DYE:
                openInventory(player, target, type, page + 1);
                break;
            case RED_DYE:
                openInventory(player, target, type, page - 1);
                break;
            default:
                break;
        }
    }

    public enum SanctionType
    {
        MENU, WARN, BAN, BAN_TEMP, FREEZE, KICK, MUTE, MUTE_TEMP
    }

    public enum Warns
    {
        NO_FEAR_RP(1, "NoFearRP"),
        METAGAMING(1, "MétaGaming");

        private final String name;
        private final int page;

        Warns(int page, String name)
        {
            this.page = page;
            this.name = name;
        }

        public int getPage()
        {
            return this.page;
        }
        public String getName()
        {
            return this.name;
        }
    }

    public enum Ban
    {
        VERENIUM_RP(1, "Vient de Vérénium RP")
        ;
        private final String name;
        private final int page;
        Ban(int page, String name)
        {
            this.page = page;
            this.name = name;
        }

        public int getPage()
        {
            return this.page;
        }

        public String getName()
        {
            return this.name;
        }
    }
}
