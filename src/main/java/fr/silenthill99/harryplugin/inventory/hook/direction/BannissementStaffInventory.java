package fr.silenthill99.harryplugin.inventory.hook.direction;

import fr.silenthill99.harryplugin.ItemBuilder;
import fr.silenthill99.harryplugin.Main;
import fr.silenthill99.harryplugin.inventory.AbstractInventory;
import fr.silenthill99.harryplugin.inventory.InventoryManager;
import fr.silenthill99.harryplugin.inventory.InventoryType;
import fr.silenthill99.harryplugin.inventory.holder.direction.BannissementStaffHolder;
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

public class BannissementStaffInventory extends AbstractInventory<BannissementStaffHolder> {
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

    @SuppressWarnings("DataFlowIssue")
    @Override
    public void manageInventory(InventoryClickEvent e, ItemStack current, Player player, BannissementStaffHolder holder) throws IOException {
        OfflinePlayer target = holder.getTarget();
        Sanctions sanctions = holder.sanctions.get(e.getSlot());

        switch (current.getType()) {
            case REDSTONE_BLOCK:{
                player.closeInventory();
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " permission clear");
                Bukkit.dispatchCommand(player, "lp user " + target.getName() + " parent set default");
                Bukkit.dispatchCommand(player, "ipban " + target.getName() + " " + sanctions.getReason());
                File ban = new File(Main.getInstance().getDataFolder(), "staff_blacklist.yml");
                YamlConfiguration config = YamlConfiguration.loadConfiguration(ban);
                if (config.getConfigurationSection("bannissements") == null)
                    config.createSection("bannissements");
                config.getConfigurationSection("bannissements").set(String.valueOf(target.getUniqueId()), sanctions.getReason());
                config.save(ban);
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
