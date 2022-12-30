package fr.silenthill99.harryplugin.inventory.hook.direction;

import fr.silenthill99.harryplugin.ItemBuilder;
import fr.silenthill99.harryplugin.inventory.AbstractInventory;
import fr.silenthill99.harryplugin.inventory.InventoryManager;
import fr.silenthill99.harryplugin.inventory.InventoryType;
import fr.silenthill99.harryplugin.inventory.holder.direction.PlayerRankUpHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerRankUpInventory extends AbstractInventory<PlayerRankUpHolder>
{
    public PlayerRankUpInventory() {
        super(PlayerRankUpHolder.class);
    }

    @Override
    public void openInventory(Player p, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        PlayerRankUpHolder holder = new PlayerRankUpHolder(target);

        ItemBuilder builder = new ItemBuilder(Material.YELLOW_WOOL).setName(ChatColor.YELLOW + "Builder");
        ItemBuilder modo_stagiaire = new ItemBuilder(Material.LIME_WOOL).setName(ChatColor.GREEN + "[Modérateur stagiaire]");
        ItemBuilder moderateur = new ItemBuilder(Material.LIME_WOOL).setName(ChatColor.GREEN + "[Modérateur]");
        ItemBuilder administrateur = new ItemBuilder(Material.LIGHT_BLUE_WOOL).setName(ChatColor.BLUE + "[Administrateur]");
        ItemBuilder grade_direction = new ItemBuilder(Material.WRITABLE_BOOK).setName(ChatColor.YELLOW + "Grade direction");
        ItemBuilder unrank = new ItemBuilder(Material.PAPER).setName(ChatColor.RED + "UnRank le joueur");

        Inventory inv = createInventory(holder, 27, "RankUp | " + target.getName());
        inv.setItem(0, builder.toItemStack());
        inv.setItem(1, modo_stagiaire.toItemStack());
        inv.setItem(2, moderateur.toItemStack());
        inv.setItem(3, administrateur.toItemStack());
        inv.setItem(8, grade_direction.toItemStack());
        for (Operateur operateur : Operateur.values())
        {
            holder.operateur.put(operateur.getSlot(), operateur);
            inv.setItem(operateur.getSlot(), new ItemBuilder(Material.RED_DYE).setName(ChatColor.RED + operateur.getName()).toItemStack());
        }
        inv.setItem(18, RETOUR);
        inv.setItem(25, unrank.toItemStack());
        p.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent e, ItemStack current, Player player, PlayerRankUpHolder holder) {
        OfflinePlayer target = holder.getTarget();

        Operateur operateur = holder.operateur.get(e.getSlot());

        e.setCancelled(true);
        switch (current.getType())
        {
            case SUNFLOWER:
                InventoryManager.openInventory(player, InventoryType.MENU_DIRECTION, target);
                break;
            case RED_DYE:
            {
                if (operateur.equals(Operateur.METTRE_OP)) {
                    target.setOp(true);
                    player.sendMessage(ChatColor.GREEN + target.getName() + " est désormais opérateur !");
                    if (target.isOnline()) target.getPlayer().sendMessage(ChatColor.GREEN + "Vous êtes désormais opérateur du serveur !");
                } else {
                    target.setOp(false);
                    player.sendMessage(ChatColor.RED + target.getName() + " n'est plus opérateur du serveur");
                    if (target.isOnline()) target.getPlayer().sendMessage(ChatColor.RED + "Vous n'êtes plus opérateur du serveur !");
                }
                break;
            }
            case LIME_WOOL:
            {
                player.closeInventory();
                if (current.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "[Modérateur stagiaire]")) {
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission clear");
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set modo-stagiaire");
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission set harrypocraft.stagiaire");
                    if (target.isOnline()) {
                        target.getPlayer().sendMessage(ChatColor.GREEN + "Vous êtes désormais Modérateur stagiaire !");
                    }
                    player.sendMessage(ChatColor.GREEN + target.getName() + " est désormais Modérateur stagiaire !");
                } else if (current.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "[Modérateur]")) {
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission clear");
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set moderateur");
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission set harrypocraft.modo");
                    if (target.isOnline()) {
                        target.getPlayer().sendMessage(ChatColor.GREEN + "Suite au succès de votre période de stage, vous êtes désormais Modérateur !");
                    }
                    player.sendMessage(ChatColor.GREEN + target.getName() + " est désormais au rang de Modérateur !");
                }
                break;
            }
            case LIGHT_BLUE_WOOL:
            {
                player.closeInventory();
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set administrateur");
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission clear");
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission set harrypocraft.admin");
                if (target.isOnline()) {
                    target.getPlayer().sendMessage(ChatColor.GREEN + "Vous êtes désormais Administrateur !");
                }
                player.sendMessage(ChatColor.GREEN + target.getName() + " est désormais Administrateur !");
                break;
            }
            case WRITABLE_BOOK:
            {
                InventoryManager.openInventory(player, InventoryType.RANK_UP_SUPER, target);
                break;
            }
            case PAPER:
            {
                player.closeInventory();
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set default");
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission clear");
                Bukkit.dispatchCommand(player, "kick " + target.getName() + " Unrank");
                break;
            }
            case YELLOW_WOOL:
            {
                player.closeInventory();
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set builder");
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission clear");
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission set harrypocraft.builder");
                if (target.isOnline())
                    target.getPlayer().sendMessage(ChatColor.GREEN + "Vous êtes désormais Builder !");
                player.sendMessage(ChatColor.GREEN + target.getName() + " est désormais Builder !");
                break;
            }
            default:
                break;
        }
    }

    public enum Operateur
    {
        METTRE_OP(24, "Mettre op"),
        DE_OP(26, "DeOp le joueur");

        private final int slot;
        private final String name;
        Operateur(int slot, String name)
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
}
