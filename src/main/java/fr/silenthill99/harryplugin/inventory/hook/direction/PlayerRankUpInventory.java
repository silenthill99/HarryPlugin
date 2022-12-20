package fr.silenthill99.harryplugin.inventory.hook.direction;

import fr.silenthill99.harryplugin.ItemBuilder;
import fr.silenthill99.harryplugin.Panel;
import fr.silenthill99.harryplugin.inventory.AbstractInventory;
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
        ItemBuilder builder = new ItemBuilder(Material.YELLOW_WOOL).setName(ChatColor.YELLOW + "Builder");
        ItemBuilder modo_stagiaire = new ItemBuilder(Material.LIME_WOOL).setName(ChatColor.GREEN + "[Modérateur stagiaire]");
        ItemBuilder moderateur = new ItemBuilder(Material.LIME_WOOL).setName(ChatColor.GREEN + "[Modérateur]");
        ItemBuilder administrateur = new ItemBuilder(Material.LIGHT_BLUE_WOOL).setName(ChatColor.BLUE + "[Administrateur]");
        ItemBuilder grade_direction = new ItemBuilder(Material.WRITABLE_BOOK).setName(ChatColor.YELLOW + "Grade direction");
        ItemBuilder retour = new ItemBuilder(Material.SUNFLOWER).setName(ChatColor.YELLOW + "Retour");
        ItemBuilder mettre_op = new ItemBuilder(Material.RED_DYE).setName(ChatColor.RED + "Mettre op");
        ItemBuilder unrank = new ItemBuilder(Material.PAPER).setName(ChatColor.RED + "UnRank le joueur");
        ItemBuilder deop_le_joueur = new ItemBuilder(Material.RED_DYE).setName(ChatColor.RED + "DeOp le joueur");

        Inventory rankup = Bukkit.createInventory(new PlayerRankUpHolder(target), 27, "RankUp | " + target.getName());
        rankup.setItem(0, builder.toItemStack());
        rankup.setItem(1, modo_stagiaire.toItemStack());
        rankup.setItem(2, moderateur.toItemStack());
        rankup.setItem(3, administrateur.toItemStack());
        rankup.setItem(8, grade_direction.toItemStack());
        rankup.setItem(18, retour.toItemStack());
        rankup.setItem(24, mettre_op.toItemStack());
        rankup.setItem(25, unrank.toItemStack());
        rankup.setItem(26, deop_le_joueur.toItemStack());
        p.openInventory(rankup);
    }

    @Override
    public void manageInventory(InventoryClickEvent e, ItemStack current, Player player, PlayerRankUpHolder holder) {
        OfflinePlayer target = holder.getTarget();
        e.setCancelled(true);
        switch (current.getType())
        {
            case SUNFLOWER:
                //Panel.panel_direction(player, target, "Direction");
                break;
            case RED_DYE:
                if (current.getItemMeta().getDisplayName().equals(ChatColor.RED + "Mettre op"))
                {
                    target.setOp(true);
                    player.sendMessage(ChatColor.GREEN + target.getName() + " est désormais opérateur !");
                    try
                    {
                        target.getPlayer().sendMessage(ChatColor.GREEN + "Vous êtes désormais opérateur du serveur !");
                    }
                    catch (NullPointerException exception)
                    {
                    }
                }
                else if (current.getItemMeta().getDisplayName().equals(ChatColor.RED + "DeOp le joueur"))
                {
                    target.setOp(false);
                    player.sendMessage(ChatColor.RED + target.getName() + " n'est plus opérateur du serveur");
                    try
                    {
                        target.getPlayer().sendMessage(ChatColor.RED + "Vous n'êtes plus opérateur du serveur !");
                    }
                    catch (NullPointerException exception)
                    {
                    }
                }
                break;
            case LIME_WOOL:
                player.closeInventory();
                if (current.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "[Modérateur stagiaire]"))
                {
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission clear");
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set modo-stagiaire");
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission set harrypocraft.stagiaire");
                    if (target.isOnline())
                    {
                        target.getPlayer().sendMessage(ChatColor.GREEN + "Vous êtes désormais Modérateur stagiaire !");
                    }
                    player.sendMessage(ChatColor.GREEN + target.getName() + " est désormais Modérateur stagiaire !");
                }
                else if (current.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "[Modérateur]"))
                {
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission clear");
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set moderateur");
                    Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission set harrypocraft.modo");
                    if (target.isOnline())
                    {
                        target.getPlayer().sendMessage(ChatColor.GREEN + "Suite au succès de votre période de stage, vous êtes désormais Modérateur !");
                    }
                    player.sendMessage(ChatColor.GREEN + target.getName() + " est désormais au rang de Modérateur !");
                }
                break;
            case LIGHT_BLUE_WOOL:
                player.closeInventory();
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set administrateur");
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission clear");
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission set harrypocraft.admin");
                if (target.isOnline())
                {
                    target.getPlayer().sendMessage(ChatColor.GREEN + "Vous êtes désormais Administrateur !");
                }
                player.sendMessage(ChatColor.GREEN + target.getName() + " est désormais Administrateur !");
                break;
            case WRITABLE_BOOK:
                //Panel.panel_direction(player, target, "Grade Direction");
                break;
            case PAPER:
                player.closeInventory();
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set default");
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission clear");
                Bukkit.dispatchCommand(player, "kick " + target.getName() + " Unrank");
                break;
            case YELLOW_WOOL:
                player.closeInventory();
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set builder");
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission clear");
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission set harrypocraft.builder");
                if (target.isOnline()) target.getPlayer().sendMessage(ChatColor.GREEN + "Vous êtes désormais Builder !");
                player.sendMessage(ChatColor.GREEN + target.getName() + " est désormais Builder !");
                break;
            default:
                break;
        }
    }
}
