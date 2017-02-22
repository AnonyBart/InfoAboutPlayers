package bart_.spigot.main;

import bart_.spigot.sqlite.InsertOperation;
import bart_.spigot.sqlite.SelectOperation;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Bart_ on 16/12/2016.
 */
public class Eventi implements Listener {

    private boolean storeallplayers = FilesCreate.config.getBoolean("Settings.storeallplayers");
    private int ticks = FilesCreate.config.getInt("AdvancedSettings.storeplayersjoined") * 20;

    @EventHandler
    private void onJoin(PlayerJoinEvent e) {
        String name = e.getPlayer().getName();
        String UUID = String.valueOf(e.getPlayer().getUniqueId());
        Player p = Bukkit.getPlayerExact(name);
        // String name, String UUID, String address , int banned, String lastposition, double balance
        // int id, int banned, String lastposition, double balance

        if (storeallplayers == true) {
            if (SelectOperation.find(UUID) == -1) {
                Schedule.Add(p);
            } else {
                Schedule.Update(p);
            }
        }

        if (storeallplayers == false) {
            if (SelectOperation.find(UUID) == -1) {
                Main.instance.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
                    public void run() {
                        Player player = Bukkit.getPlayerExact(name);
                        if (player != null) {
                            Schedule.Add(player);
                        }
                    }
                }, ticks);// 60 L == 3 sec, 20 ticks == 1 sec
            } else {
                final int index = SelectOperation.find(UUID);
                Main.instance.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
                    public void run() {
                        Player player = Bukkit.getPlayerExact(name);
                        if (player != null) {
                            Schedule.Update(player);
                        }
                    }
                }, ticks);// 60 L == 3 sec, 20 ticks == 1 sec
            }
        }
    }

    @EventHandler
    private void onLeave(PlayerQuitEvent e) {
        Player p = Bukkit.getPlayerExact(e.getPlayer().getName());
        Schedule.Update(p);
    }

}
