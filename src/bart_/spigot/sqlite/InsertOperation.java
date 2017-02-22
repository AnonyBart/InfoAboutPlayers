package bart_.spigot.sqlite;

import bart_.spigot.main.Main;
import org.bukkit.plugin.Plugin;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by Bart_ on 16/12/2016.
 */
public class InsertOperation {


    public static void Insert(String name, String UUID, String address , int banned, String lastposition, double  balance)
    {
        Plugin plugin = Main.instance;

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = Connecting.getSQLConnection();
            c.setAutoCommit(false);
            if (c == null) {
                plugin.getLogger().warning("Can't get the conncection to the sqlite!");
                plugin.getLogger().warning("Disabling plugin...");
                plugin.onDisable();
                return;
            }
            System.out.println("Insert of " + name + " in database");
            stmt = c.createStatement();
            String sql = "INSERT INTO PLAYERS (NAME,UUID,ADDRESS,BANNED,LASTPOSITION,BALANCE) " +
                    "VALUES ('" + name + "', '" + UUID + "', '" + address + "', " + banned + ", '" + lastposition + "', " + balance + " );";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }


}
