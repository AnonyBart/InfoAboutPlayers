package bart_.spigot.main;

import bart_.spigot.sqlite.CreateTable;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;



/**
 * Created by Bart_ on 16/12/2016.
 */
public class Main extends JavaPlugin {

    public static Main instance;
    public static Economy econ = null;

    @Override
    public void onEnable() {
        instance = this;
        FilesCreate.configfile();
        if (bart_.spigot.sqlite.Connecting.getSQLConnection()==null) {
            getLogger().warning(ChatColor.RED + "WARNING!!!");
            getLogger().warning("Disabling InfoAboutPlayers for problems with SQLite database, for errors look the log.");
            getLogger().warning("For support contact the user Bart_ on spigotmc.org");
            onDisable();
        }
        CreateTable.Create();
        if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (FilesCreate.config.getBoolean("Settings.autoupdatedatabase") == true) {
            Schedule.AutoUpdate();
        }
        getCommand("infoname").setExecutor(new Commands());
        getCommand("infoip").setExecutor(new Commands());
        getCommand("infouuid").setExecutor(new Commands());
        Bukkit.getPluginManager().registerEvents(new Eventi(), this);
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new Servers());
        getLogger().info("Plugin InfoAboutPlayers ENABLED, created by the developer Bart_. https://www.spigotmc.org/members/bart_.25718/");
        getLogger().info("Version 2.5");
    }

    @Override
    public void onDisable() {
        instance = null;
        getLogger().info("Plugin InfoAboutPlayers DISABLED, created by the developer Bart_. https://www.spigotmc.org/members/bart_.25718/");
        getLogger().info("Version 2.5");
        Bukkit.getPluginManager().disablePlugin(this);
    }


    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static String CheckPlayer(String player, Player p) {
        getServersList(p);
        String found = "false";
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        while (Servers.serverList == null);
        if (Servers.serverList != null) {
            for (String s : Servers.serverList) {
                out.writeUTF("PlayerList");
                out.writeUTF(s);
                p.sendPluginMessage(Main.instance, "BungeeCord", out.toByteArray());
                while(Servers.playerList == null);
                for (String players : Servers.playerList) {
                    if (players.equalsIgnoreCase(player)) {
                        found = "true, " + s;
                        return found;
                    }
                }
            }
        }
        return found;
    }

    public static void getServersList(Player p) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("GetServers");
        p.sendPluginMessage(Main.instance, "BungeeCord", out.toByteArray());
    }

}