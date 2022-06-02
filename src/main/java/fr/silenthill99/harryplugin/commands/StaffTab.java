package fr.silenthill99.harryplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class StaffTab implements TabCompleter
{

    private final List<String> arguments = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        arguments.add("on");
        arguments.add("off");
        return arguments;
    }
}
