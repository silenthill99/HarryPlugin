package fr.silenthill99.harryplugin.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.awt.*;

public class Discord implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args)
    {
        TextComponent discord = new TextComponent(ChatColor.BLUE + "" + ChatColor.BOLD + "Clique ici pour rejoindre le discord");
        discord.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/kqSssvKDce"));
        sender.spigot().sendMessage(discord);
        return false;
    }
}
