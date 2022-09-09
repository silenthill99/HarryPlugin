package fr.silenthill99.harryplugin.commands;

import fr.silenthill99.harryplugin.Main;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Timestamp;

public class Hrp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args)
    {
        if (!(sender instanceof Player))
        {
            System.out.println("Cette Commande ne peut être éxécutée par la console !");
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0)
        {
            player.sendMessage(ChatColor.RED + "Erreur ! Veuillez faire /hrp <message>");
            return false;
        }

        LuckPerms api = LuckPermsProvider.get();
        User user = api.getUserManager().getUser(player.getName());

        StringBuilder bc = new StringBuilder();

        for (String part : args)
        {
            bc.append(part + " ");
        }

        for (Player players : Bukkit.getOnlinePlayers())
        {
            players.sendMessage(ChatColor.DARK_RED + "[/HRP] " + user.getCachedData().getMetaData().getPrefix().replace("&", "§") + player.getName() + ChatColor.WHITE + " : " + bc);
        }
        Main.getInstance().logs.get(player.getUniqueId()).add(ChatColor.YELLOW + "[" + new Timestamp(System.currentTimeMillis()) + "] " + ChatColor.DARK_BLUE + player.getName() + " a envoyé dans le tchat HRP " + ChatColor.BLUE + bc);

        return false;
    }
}
