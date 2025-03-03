package fr.silenthill99.harryplugin.inventory.hook.modo;

import fr.silenthill99.harryplugin.ItemBuilder;
import fr.silenthill99.harryplugin.Main;
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
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class PlayerSanctionInventory extends AbstractInventory<PlayerSanctionHolder> {
    Main main = Main.getInstance();
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
            if (page > 1)
                inv.setItem(45, page_precedente);
            if (!type.equals(SanctionType.FREEZE))
                inv.setItem(53, page_suivante);
        }
        switch (type)
        {
            case WARN:
            {
                avertir.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                inv.setItem(10, avertir.toItemStack());
                int slot = 27;
                for (Warns warns : Warns.values())
                {
                    if (page == warns.getPage())
                    {
                        holder.warns.put(slot, warns);
                        inv.setItem(slot++, new ItemBuilder(Material.GREEN_WOOL).setName(ChatColor.DARK_GREEN + warns.getName()).toItemStack());
                    }
                }
                break;
            }
            case BAN_TEMP:
            {
                bannir_temporairement.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                int slot = 27;
                for (BanTemp ban_temp : BanTemp.values())
                {
                    if (page == ban_temp.getPage())
                    {
                        holder.ban_temp.put(slot, ban_temp);
                        inv.setItem(slot++, new ItemBuilder(Material.ORANGE_WOOL).setName(ChatColor.GOLD + ban_temp.getName()).setLore(ban_temp.getLore()).toItemStack());
                    }
                }
                inv.setItem(11, bannir_temporairement.toItemStack());
                p.openInventory(inv);
                break;
            }
            case BAN:
            {
                bannir.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                inv.setItem(12, bannir.toItemStack());
                int slot = 27;
                for (Ban ban : Ban.values())
                {
                    if (page == ban.getPage())
                    {
                        holder.ban.put(slot, ban);
                        inv.setItem(slot++, new ItemBuilder(Material.RED_WOOL).setName(ChatColor.DARK_RED + ban.getName()).toItemStack());
                    }
                }
                break;
            }
            case KICK:
            {
                kick.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                int slot = 27;
                for (Kick kicks : Kick.values())
                {
                    if (page == kicks.getPage())
                    {
                        holder.kicks.put(slot, kicks);
                        inv.setItem(slot++, new ItemBuilder(Material.PURPLE_WOOL).setName(ChatColor.DARK_PURPLE + kicks.getName()).toItemStack());
                    }
                }
                inv.setItem(13, kick.toItemStack());
                break;
            }
            case FREEZE:
            {
                freeze.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                inv.setItem(14, freeze.toItemStack());
                for (Freeze freezed : Freeze.values())
                {
                    holder.freeze.put(freezed.getSlot(), freezed);
                    inv.setItem(freezed.getSlot(), new ItemBuilder(Material.LIGHT_BLUE_WOOL).setName(ChatColor.AQUA + freezed.getName()).toItemStack());
                }
                break;
            }
            case MUTE_TEMP:
            {
                tempmute.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                inv.setItem(15, tempmute.toItemStack());
                int slot = 27;
                for (TempMute tempMute : TempMute.values())
                {
                    if (page == tempMute.getPage())
                    {
                        holder.temp_mute.put(slot, tempMute);
                        inv.setItem(slot++, new ItemBuilder(Material.PINK_WOOL).setName(ChatColor.LIGHT_PURPLE + tempMute.getName()).setLore(tempMute.getLore()).toItemStack());
                    }
                }
                break;
            }
            case MUTE: {
                mute.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                inv.setItem(16, mute.toItemStack());
                int slot = 27;
                for (Mute mute : Mute.values())
                {
                    if (page == mute.getPage())
                    {
                        holder.mute.put(slot, mute);
                        inv.setItem(slot++, new ItemBuilder(Material.MAGENTA_WOOL).setName(ChatColor.LIGHT_PURPLE + mute.getName()).toItemStack());
                    }
                }
                break;
            }
        }
        p.openInventory(inv);

    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public void manageInventory(InventoryClickEvent e, ItemStack current, Player player, PlayerSanctionHolder holder) {
        OfflinePlayer target = holder.getPlayer();
        SanctionType type = holder.getType();
        int page = holder.getPage();

        Ban ban = holder.ban.get(e.getSlot());
        Warns warns = holder.warns.get(e.getSlot());
        BanTemp ban_temp = holder.ban_temp.get(e.getSlot());
        Kick kicks = holder.kicks.get(e.getSlot());
        Freeze freezed = holder.freeze.get(e.getSlot());
        TempMute tempMute = holder.temp_mute.get(e.getSlot());
        Mute muted = holder.mute.get(e.getSlot());

        switch (current.getType())
        {
            case SUNFLOWER:
            {
                InventoryManager.openInventory(player, InventoryType.MODO_PLAYER_MENU, target);
                break;
            }
            case GREEN_WOOL:
            {
                if (current.isSimilar(avertir.toItemStack()) && !type.equals(SanctionType.WARN)) {
                    removeEffects();
                    openInventory(player, target, SanctionType.WARN, 1);
                    return;
                }
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " " + warns.getName());
                break;
            }
            case ORANGE_WOOL:
            {
                if (current.isSimilar(bannir_temporairement.toItemStack()) && !type.equals(SanctionType.BAN_TEMP))
                {
                    removeEffects();
                    openInventory(player, target, SanctionType.BAN_TEMP, 1);
                    return;
                }
                if (ban_temp.getReason() == null)
                {
                    Bukkit.dispatchCommand(player, "tempipban " + target.getName() + " " + ban_temp.getDuration() + " " + ban_temp.getName());
                    return;
                }
                player.closeInventory();
                Bukkit.dispatchCommand(player, "tempipban " + target.getName() + " " + ban_temp.getDuration() + " " + ban_temp.getReason());
                break;
            }
            case RED_WOOL:
            {
                if (current.isSimilar(bannir.toItemStack()) && !type.equals(SanctionType.BAN)) {
                    removeEffects();
                    openInventory(player, target, SanctionType.BAN, 1);
                    return;
                }
                player.closeInventory();
                Bukkit.dispatchCommand(player, "ipban " + target.getName() + " " + ban.getName());
                break;
            }
            case PURPLE_WOOL:
            {
                if (current.isSimilar(kick.toItemStack()) && !type.equals(SanctionType.KICK)) {
                    removeEffects();
                    openInventory(player, target, SanctionType.KICK, 1);
                    return;
                }
                player.closeInventory();
                Bukkit.dispatchCommand(player, "kick " + target.getName() + kicks.getName());
                break;
            }
            case LIGHT_BLUE_WOOL:
            {
                if (current.isSimilar(freeze.toItemStack()) && !type.equals(SanctionType.FREEZE))
                {
                    removeEffects();
                    openInventory(player, target, SanctionType.FREEZE, 1);
                    return;
                }
                player.closeInventory();
                if (freezed.equals(Freeze.FREEZE))
                {
                    if (main.frozenPlayers.containsKey(target.getUniqueId()))
                    {
                        player.sendMessage(ChatColor.RED + "Cette personne est déjà freeze !");
                        return;
                    }

                    if (!target.isOnline())
                    {
                        player.sendMessage(ChatColor.RED + "Cette personne n'est pas connectée ou n'existe pas !");
                        return;
                    }
                    main.frozenPlayers.put(target.getUniqueId(), Objects.requireNonNull(target.getPlayer()).getLocation());
                    player.sendMessage(ChatColor.GREEN + "Vous avez freeze " + target.getName());
                    target.getPlayer().sendMessage(ChatColor.GOLD + "Vous avez été freeze par " + ChatColor.RED + player.getName() + ". " + ChatColor.GOLD + "Veuillez ne pas vous déconnecter sous peine d'un bannissement d'une durée de 5 jours.");
                }
                else
                {
                    if (!main.frozenPlayers.containsKey(target.getUniqueId()))
                    {
                        player.sendMessage(ChatColor.GREEN + "Cette personne est déjà UnFreeze.");
                        return;
                    }
                    main.frozenPlayers.remove(target.getUniqueId());
                    player.sendMessage(ChatColor.GREEN + "Vous avez UnFreeze " + target.getName());
                    if (target.isOnline()) target.getPlayer().sendMessage(ChatColor.GREEN + "Vous avez été UnFreeze");
                }
                break;
            }
            case PINK_WOOL:
            {
                if (current.isSimilar(tempmute.toItemStack()) && !type.equals(SanctionType.MUTE_TEMP))
                {
                    removeEffects();
                    openInventory(player, target, SanctionType.MUTE_TEMP, 1);
                    return;
                }
                player.closeInventory();
                Bukkit.dispatchCommand(player, "tempmute " + target.getName() + " " + tempMute.getDuration() + " " + tempMute.getReason());
                break;
            }
            case MAGENTA_WOOL:
            {
                if (current.isSimilar(mute.toItemStack()) && !type.equals(SanctionType.MUTE))
                {
                    removeEffects();
                    openInventory(player, target, SanctionType.MUTE, 1);
                    return;
                }
                player.closeInventory();
                Bukkit.dispatchCommand(player, "mute " + target.getName() + " " + muted.getName());
                break;
            }
            case GREEN_DYE:
            {
                openInventory(player, target, type, page + 1);
                break;
            }
            case RED_DYE:
            {
                openInventory(player, target, type, page - 1);
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void closeInventory(Player p, InventoryCloseEvent e) {
        removeEffects();
    }

    private void removeEffects()
    {
        avertir.removeEnchantment(Enchantment.DAMAGE_ALL);
        bannir_temporairement.removeEnchantment(Enchantment.DAMAGE_ALL);
        bannir.removeEnchantment(Enchantment.DAMAGE_ALL);
        kick.removeEnchantment(Enchantment.DAMAGE_ALL);
        freeze.removeEnchantment(Enchantment.DAMAGE_ALL);
        tempmute.removeEnchantment(Enchantment.DAMAGE_ALL);
        mute.removeEnchantment(Enchantment.DAMAGE_ALL);
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

    public enum BanTemp
    {
        SORTS(1, "Utilisation d'un des 3 sorts impardonnables", "1mo", null, "Durée : 1 mois")
        ;
        private final int page;
        private final String name;
        private final String duration;
        private final String reason;
        private final String[] lore;

        BanTemp(int page, String name, String duration, String reason, String... lore)
        {
            this.page = page;
            this.name = name;
            this.duration = duration;
            this.reason = reason;
            this.lore = lore;
        }

        public int getPage()
        {
            return this.page;
        }

        public String getName()
        {
            return this.name;
        }

        public String getDuration()
        {
            return this.duration;
        }

        public String getReason()
        {
            return this.reason;
        }

        public String[] getLore()
        {
            return this.lore;
        }
    }

    public enum Kick
    {
        ;
        private final int page;
        private final String name;

        Kick(int page, String name)
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

    public enum Freeze
    {
        FREEZE(37, "Freeze le joueur"),
        UN_FREEZE(43, "UnFreeze le joueur")
        ;
        private final int slot;
        private final String name;

        Freeze(int slot, String name)
        {
            this.slot = slot;
            this.name = name;
        }

        public int getSlot()
        {
            return this.slot;
        }

        public String getName()
        {
            return this.name;
        }
    }

    public enum TempMute
    {
        ;
        private final int page;
        private final String name;
        private final String duration;
        private final String reason;
        private final String[] lore;

        TempMute(int page, String name, String duration, String reason, String... lore)
        {
            this.page = page;
            this.name = name;
            this.duration = duration;
            this.reason = reason;
            this.lore = lore;
        }

        public int getPage()
        {
            return this.page;
        }

        public String getName()
        {
            return this.name;
        }

        public String getDuration()
        {
            return this.duration;
        }

        public String getReason()
        {
            return this.reason;
        }

        public String[] getLore()
        {
            return this.lore;
        }
    }

    public enum Mute
    {
        ;
        private final int page;
        private final String name;

        Mute(int page, String name)
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
