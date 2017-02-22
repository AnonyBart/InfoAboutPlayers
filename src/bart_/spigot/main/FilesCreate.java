package bart_.spigot.main;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FilesCreate {
    public static File configf;
    public static FileConfiguration config;

    public static void configfile() {

        configf = new File(Main.instance.getDataFolder(), "config.yml");

        if (configf.exists()) {
            // config.yml esiste già
        }

        if (!configf.exists()) {
            configf.getParentFile().mkdirs();
            try {
                Bukkit.getConsoleSender().sendMessage("Creating config.yml");
                configf.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            config = YamlConfiguration.loadConfiguration(configf);
            try {
                Bukkit.getConsoleSender().sendMessage("Saving config.yml");
                config.save(configf);
            } catch (IOException e) {
                e.printStackTrace();
            }

            config = new YamlConfiguration();
            // Qua i valori
            config.createSection("Settings");
            config.set("Settings.usingbungeecord", false);
            config.set("Settings.autoupdatedatabase", true);
            config.set("Settings.storeallplayers", true);
            config.createSection("AdvancedSettings");
            config.set("AdvancedSettings.autoupdatetime", 600);
            config.set("AdvancedSettings.storeplayersjoined", 300);
            try {
                Bukkit.getConsoleSender().sendMessage("Saving & Loading config.yml");
                config.save(configf);
                config.load(configf);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(configf);
    }


}
