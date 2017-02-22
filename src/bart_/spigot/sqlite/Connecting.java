package bart_.spigot.sqlite;

import bart_.spigot.main.Main;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;

/**
 * Created by Bart_ on 16/12/2016.
 */
public class Connecting {


    public static Connection getSQLConnection() {
        String dbname = "players";
        Plugin plugin = Main.instance;
        File dataFolder = new File(plugin.getDataFolder(), dbname+".db");

        Connection c = null;


        if (!dataFolder.exists()){
            try {
                dataFolder.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "File write error: "+dbname+".db");
            }
        }
        try {
            if(c!=null&&!c.isClosed()){
                return c;
            }
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            return c;
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE,"SQLite exception on initialize", ex);
        } catch (ClassNotFoundException ex) {
            plugin.getLogger().log(Level.SEVERE, "You need the SQLite JBDC library. Google it. Put it in /lib folder.");
        }
        return null;
    }

}
