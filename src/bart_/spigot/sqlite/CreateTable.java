package bart_.spigot.sqlite;

import bart_.spigot.main.Main;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.sql.*;

/**
 * Created by Bart_ on 16/12/2016.
 */
public class CreateTable {

    public static void Create()
    {
        String dbname = "players";
        Plugin plugin = Main.instance;
        File dataFolder = new File(plugin.getDataFolder(), dbname+".db");

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            System.out.println("Creating Tables in database");
            stmt = c.createStatement();
            String sql = "CREATE TABLE PLAYERS " +
                    "(ID INTEGER PRIMARY KEY NOT NULL," +
                    " NAME TEXT NOT NULL, " +
                    " UUID TEXT NOT NULL, " +
                    " ADDRESS TEXT NOT NULL, " +
                    " BANNED INTEGER NOT NULL, " +
                    " LASTPOSITION TEXT, " +
                    " BALANCE REAL NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        InsertOperation.Insert("test" , "test" , "test" , 0, "test", 0.0);
        System.out.println("Table created successfully");
    }


}
