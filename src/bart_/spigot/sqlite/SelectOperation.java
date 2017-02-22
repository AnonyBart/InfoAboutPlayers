package bart_.spigot.sqlite;


import java.sql.*;

/**
 * Created by Bart_ on 16/12/2016.
 */
public class SelectOperation {

    public static int find(String UUID)
    {
        int posizione = -1;
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = Connecting.getSQLConnection();
            c.setAutoCommit(false);
            System.out.println("Searching the UUID");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM PLAYERS;" );
            while ( rs.next() ) {
                if (rs.getString("UUID").equals(UUID)) {
                    posizione = rs.getInt("ID");
                    rs.close();
                    stmt.close();
                    c.close();
                    System.out.println("UUID found");
                    return posizione;
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("UUID not found");
        return posizione;
    }

    public static int findIP(String IP)
    {
        int posizione = -1;
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = Connecting.getSQLConnection();
            c.setAutoCommit(false);
            System.out.println("Searching the IP");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM PLAYERS;" );
            while ( rs.next() ) {
                if (rs.getString("ADDRESS").equalsIgnoreCase(IP)) {
                    posizione = rs.getInt("ID");
                    rs.close();
                    stmt.close();
                    c.close();
                    System.out.println("IP found");
                    return posizione;
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("IP not found");
        return posizione;
    }

    public static int findname(String name)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = Connecting.getSQLConnection();
            c.setAutoCommit(false);
            System.out.println("Searching the player name");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM PLAYERS;" );
            while ( rs.next() ) {
                if (rs.getString("NAME").equals(name)) {
                    rs.close();
                    stmt.close();
                    c.close();
                    System.out.println("Player name found");
                    return rs.getInt("ID");
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Player name not found");
        return -1;
    }

    public static void update(int id, int banned, String lastposition, double balance)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = Connecting.getSQLConnection();
            c.setAutoCommit(false);
            System.out.println("Updating player informations...");
            stmt = c.createStatement();
            String sql = null;
            if (banned == 1 || banned == 0) {
                sql = "UPDATE PLAYERS set BANNED = " + banned + " where ID=" + id + ";";
                stmt.executeUpdate(sql);
            }
            if (lastposition != null) {
                sql = "UPDATE PLAYERS set LASTPOSITION = '" + lastposition + "' where ID=" + id + ";";
                stmt.executeUpdate(sql);
            }
            if (balance != Integer.MIN_VALUE) {
                sql = "UPDATE PLAYERS set BALANCE = " + balance + " where ID=" + id + ";";
                stmt.executeUpdate(sql);
            }
            c.commit();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

}
