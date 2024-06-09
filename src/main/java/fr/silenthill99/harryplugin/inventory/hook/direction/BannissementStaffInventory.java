package fr.silenthill99.harryplugin.inventory.hook.direction;

import fr.silenthill99.harryplugin.ItemBuilder;
import fr.silenthill99.harryplugin.Main;
import fr.silenthill99.harryplugin.inventory.AbstractInventory;
import fr.silenthill99.harryplugin.inventory.InventoryManager;
import fr.silenthill99.harryplugin.inventory.InventoryType;
import fr.silenthill99.harryplugin.inventory.holder.direction.BannissementStaffHolder;
import fr.silenthill99.harryplugin.mysql.DbConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class BannissementStaffInventory extends AbstractInventory<BannissementStaffHolder> {

    public static final Main main = Main.getInstance();

    public BannissementStaffInventory() {
        super(BannissementStaffHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args) {
        OfflinePlayer target = (OfflinePlayer) args[0];
        BannissementStaffHolder holder = new BannissementStaffHolder(target);
        Inventory inv = createInventory(holder, 9, ChatColor.DARK_RED + "Bannissement immédiat | " + target.getName());
        int slot = 0;
        for (Sanctions sanctions : Sanctions.values()) {
            holder.sanctions.put(slot, sanctions);
            inv.setItem(slot++, new ItemBuilder(Material.REDSTONE_BLOCK).setName(ChatColor.DARK_RED + sanctions.getName()).toItemStack());
        }
        inv.setItem(inv.getSize() -1, RETOUR);
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent e, ItemStack current, Player player, BannissementStaffHolder holder) throws IOException {
        OfflinePlayer target = holder.getTarget();
        Sanctions sanctions = holder.sanctions.get(e.getSlot());

        switch (current.getType()) {
            case REDSTONE_BLOCK:{
                player.closeInventory();
                if (target.isOp()) {
                    target.setOp(false);
                }
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + target.getName() + " permission clear");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + target.getName() + " parent set default");

                final DbConnection gradeConnection = main.getManager().getGradeConnection();
                Bukkit.getScheduler().runTaskAsynchronously(main, () -> {
//                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ipban " + target.getName() + " " + sanctions.getReason());
                    try {
                        Connection connection = gradeConnection.getConnection();
                        PreparedStatement preparedStatement =
                                connection.prepareStatement("INSERT INTO staff_blacklist VALUES (?, ?, ?)");
                        preparedStatement.setString(1, target.getUniqueId().toString());
                        preparedStatement.setString(2, sanctions.getReason());
                        preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                break;
            }
            case SUNFLOWER:{
                InventoryManager.openInventory(player, InventoryType.DIRECTION_ERREURS_STAFF, target);
                break;
            }
            default:
                break;
        }
    }

    public enum Sanctions {
        CORRUPTION("Corruption", "Corruption (A ne pas débannir)");
        private final String name;
        private final String reason;

        Sanctions(String name, String reason) {
            this.name = name;
            this.reason = reason;
        }

        public String getName() {
            return this.name;
        }

        public String getReason() {
            return this.reason;
        }
    }
}
