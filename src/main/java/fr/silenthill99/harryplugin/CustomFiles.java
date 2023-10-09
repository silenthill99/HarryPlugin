package fr.silenthill99.harryplugin;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public enum CustomFiles
{
    LOGS(new File(Main.getInstance().getDataFolder(), "logs.yml")),
    GRYFFONDOR(new File(Main.getInstance().getDataFolder(), "élèves/maisons/Gryffondor.yml"));

    private final File file;
    private final YamlConfiguration config;

    CustomFiles(File file)
    {
        this.file = file;
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void addLog(Player player, String log) throws IOException {
        List<String> message = config.getStringList(player.getName() + ".logs");
        message.add(ChatColor.YELLOW + "[" + new Timestamp(System.currentTimeMillis()) + "] " + log);
        config.set(player.getName() + ".logs", message);
        config.save(file);
    }

    public void removeLogs(Player player) throws IOException {
        List<String> message = config.getStringList(player.getName() + ".logs");
        message.clear();
        config.set(player.getName() + ".logs", message);
        config.save(file);
    }

    public void addEleves(Player player) throws IOException {
        List<String> eleves = config.getStringList("élèves");
        eleves.add(player.getName());
        config.set("élèves", eleves);
        config.save(file);
    }

    public void removeEleves(Player player) throws IOException {
        List<String> eleves = config.getStringList("élèves");
        eleves.remove(player.getName());
        config.set("élèves", eleves);
        config.save(file);
    }

    public File getFile()
    {
        return this.file;
    }

    public YamlConfiguration getConfig()
    {
        return this.config;
    }
}
