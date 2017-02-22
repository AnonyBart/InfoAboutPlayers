package bart_.spigot.main;

import bart_.spigot.sqlite.Connecting;
import bart_.spigot.sqlite.SelectOperation;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Bart_ on 16/12/2016.
 */
public class Commands implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("infoname")) {
            if (args.length == 1) {
                int index = SelectOperation.findname(args[0]);
                if (index != -1) {
                    Connection c = null;
                    Statement stmt = null;
                    try {
                        Class.forName("org.sqlite.JDBC");
                        c = Connecting.getSQLConnection();
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM PLAYERS WHERE ID=" + index + ";");
                        boolean banned = false;
                        if (rs.getInt("BANNED") == 1) banned = true;
                        sender.sendMessage(ChatColor.GREEN + "InfoAboutPlayers-->");
                        sender.sendMessage(ChatColor.YELLOW + "Name: " + ChatColor.GRAY + rs.getString("NAME"));
                        sender.sendMessage(ChatColor.YELLOW + "UUID: " + ChatColor.GRAY + rs.getString("UUID"));
                        sender.sendMessage(ChatColor.YELLOW + "ADDRESS: " + ChatColor.GRAY + rs.getString("ADDRESS"));
                        sender.sendMessage(ChatColor.YELLOW + "BANNED: " + ChatColor.GRAY + banned);
                        sender.sendMessage(ChatColor.YELLOW + "LASTPOSITION: " + ChatColor.GRAY + rs.getString("LASTPOSITION"));
                        sender.sendMessage(ChatColor.YELLOW + "BALANCE: " + ChatColor.GRAY + rs.getString("BALANCE"));
                        rs.close();
                        stmt.close();
                        c.close();
                    } catch (Exception e) {
                        System.err.println(e.getClass().getName() + ": " + e.getMessage());
                        System.exit(0);
                    }
                    return true;
                } else sender.sendMessage(ChatColor.RED + "Player " + args[0] + " not found!");
            } else sender.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GREEN + "/info <playername>");
            return true;
        } else if (label.equalsIgnoreCase("infoip")) {
            if (args.length == 1) {
                int index = SelectOperation.findIP(args[0]);
                if (index != -1) {
                    Connection c = null;
                    Statement stmt = null;
                    try {
                        Class.forName("org.sqlite.JDBC");
                        c = Connecting.getSQLConnection();
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM PLAYERS WHERE ID=" + index + ";");
                        boolean banned = false;
                        if (rs.getInt("BANNED") == 1) banned = true;
                        sender.sendMessage(ChatColor.GREEN + "InfoAboutPlayers-->");
                        sender.sendMessage(ChatColor.YELLOW + "Name: " + ChatColor.GRAY + rs.getString("NAME"));
                        sender.sendMessage(ChatColor.YELLOW + "UUID: " + ChatColor.GRAY + rs.getString("UUID"));
                        sender.sendMessage(ChatColor.YELLOW + "ADDRESS: " + ChatColor.GRAY + rs.getString("ADDRESS"));
                        sender.sendMessage(ChatColor.YELLOW + "BANNED: " + ChatColor.GRAY + banned);
                        sender.sendMessage(ChatColor.YELLOW + "LASTPOSITION: " + ChatColor.GRAY + rs.getString("LASTPOSITION"));
                        sender.sendMessage(ChatColor.YELLOW + "BALANCE: " + ChatColor.GRAY + rs.getString("BALANCE"));
                        rs.close();
                        stmt.close();
                        c.close();
                    } catch (Exception e) {
                        System.err.println(e.getClass().getName() + ": " + e.getMessage());
                        System.exit(0);
                    }
                    return true;
                } else sender.sendMessage(ChatColor.RED + "IP " + args[0] + " not found!");
            } else sender.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GREEN + "/info <ip>");
            return true;
        } else if (label.equalsIgnoreCase("infouuid")) {
            if (args.length == 1) {
                int index = SelectOperation.find(args[0]);
                if (index != -1) {
                    Connection c = null;
                    Statement stmt = null;
                    try {
                        Class.forName("org.sqlite.JDBC");
                        c = Connecting.getSQLConnection();
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM PLAYERS WHERE ID=" + index + ";");
                        boolean banned = false;
                        if (rs.getInt("BANNED") == 1) banned = true;
                        sender.sendMessage(ChatColor.GREEN + "InfoAboutPlayers-->");
                        sender.sendMessage(ChatColor.YELLOW + "Name: " + ChatColor.GRAY + rs.getString("NAME"));
                        sender.sendMessage(ChatColor.YELLOW + "UUID: " + ChatColor.GRAY + rs.getString("UUID"));
                        sender.sendMessage(ChatColor.YELLOW + "ADDRESS: " + ChatColor.GRAY + rs.getString("ADDRESS"));
                        sender.sendMessage(ChatColor.YELLOW + "BANNED: " + ChatColor.GRAY + banned);
                        sender.sendMessage(ChatColor.YELLOW + "LASTPOSITION: " + ChatColor.GRAY + rs.getString("LASTPOSITION"));
                        sender.sendMessage(ChatColor.YELLOW + "BALANCE: " + ChatColor.GRAY + rs.getString("BALANCE"));
                        rs.close();
                        stmt.close();
                        c.close();
                    } catch (Exception e) {
                        System.err.println(e.getClass().getName() + ": " + e.getMessage());
                        System.exit(0);
                    }
                    return true;
                } else sender.sendMessage(ChatColor.RED + "UUID " + args[0] + " not found!");
            } else sender.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GREEN + "/info <ip>");
            return true;
        }

        return false;
    }
}
