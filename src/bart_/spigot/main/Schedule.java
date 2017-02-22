package bart_.spigot.main;

import bart_.spigot.sqlite.InsertOperation;
import bart_.spigot.sqlite.SelectOperation;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Bart_ on 16/12/2016.
 */
public class Schedule {

    public static void Add(Player player) {
        String name = player.getName();
        String UUID = String.valueOf(player.getUniqueId());
        String address = String.valueOf(player.getAddress().getAddress());
        address = address.replaceAll("/", "");
        int banned = 0;
        if (player.isBanned() == true) banned = 1;
        if (player.isBanned() == false) banned = 0;
        String lastposition;
        if (FilesCreate.config.getBoolean("Settings.usingbungeecord") == true) {
            String server = Main.CheckPlayer(player.getName(), player);
            String[] risultatos = server.split(", ");
            lastposition = "Server: " + risultatos[1] + ", World: " + player.getWorld() + ", X: " + player.getLocation().getX() + ", Y: " + player.getLocation().getY() + ", Z: " + player.getLocation().getZ();
        } else {
            lastposition = "Server: " + Bukkit.getServer().getName() + ", World: " + player.getWorld() + ", X: " + player.getLocation().getX() + ", Y: " + player.getLocation().getY() + ", Z: " + player.getLocation().getZ();
        }
        double balance = Main.econ.getBalance(Bukkit.getOfflinePlayer(player.getUniqueId()));
        System.out.print("Name: " + name);
        System.out.print("UUID: " + UUID);
        System.out.print("adress: " + address);
        System.out.print("banned: " + banned);
        System.out.print("lastposition: " + lastposition);
        System.out.print("balance: " + balance);
        InsertOperation.Insert(name, UUID, address, banned, lastposition, balance);
    }



    public static void Update(Player player) {
        String UUID = String.valueOf(player.getUniqueId());
        int banned = 0;
        if (player.isBanned() == true) banned = 1;
        if (player.isBanned() == false) banned = 0;
        String lastposition;
        if (FilesCreate.config.getBoolean("Settings.usingbungeecord") == true) {
            String server = Main.CheckPlayer(player.getName(), player);
            Bukkit.getLogger().info("Point 6");
            String[] risultatos = server.split(", ");
            lastposition = "Server: " + risultatos[1] + ", World: " + player.getWorld() + ", X: " + player.getLocation().getX() + ", Y: " + player.getLocation().getY() + ", Z: " + player.getLocation().getZ();
        } else {
            lastposition = "Server: " + Bukkit.getServer().getName() + ", World: " + player.getWorld() + ", X: " + player.getLocation().getX() + ", Y: " + player.getLocation().getY() + ", Z: " + player.getLocation().getZ();
        }
        double balance = Main.econ.getBalance(Bukkit.getOfflinePlayer(player.getUniqueId()));
        SelectOperation.update(SelectOperation.find(UUID), banned, lastposition, balance);
    }



    private static int ticks = FilesCreate.config.getInt("AdvancedSettings.autoupdatetime") * 20;
    public static void AutoUpdate() {
        Main.instance.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
            public void run() {
                if (Bukkit.getOnlinePlayers().size() != 0) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        Schedule.Update(player);
                    }
                }
                AutoUpdate();
            }
        }, ticks);// 60 L == 3 sec, 20 ticks == 1 sec
    }

}
