package io.github.mac_genius.cosmetics.Database;

import io.github.mac_genius.cosmetics.PluginSettings;
import org.bukkit.entity.Player;
import org.fusesource.jansi.Ansi;

import java.sql.*;
import java.util.HashMap;

/**
 * Created by Mac on 10/31/2015.
 */
public class PetMenu {
    private PluginSettings settings;
    private SQLConnect connect;

    /**
     * @param settings
     */
    public PetMenu(PluginSettings settings) {
        this.settings = settings;
        connect = new SQLConnect(settings);
    }

    /**
     *
     * @param player
     */
    public void addMenu(Player player) {
        Connection connection = connect.getConnection();
        String uuid = player.getUniqueId().toString();
        try {
            PreparedStatement fetch = connection.prepareStatement("SELECT * FROM PetMenu WHERE Uuid='" + uuid + "'");
            ResultSet results = fetch.executeQuery();
            String fetchuuid = "";
            while (results.next()) {
                fetchuuid = results.getString(2);
            }
            if (fetchuuid.equals("")) {
                addToTable(player);
            }
            connection.close();
        } catch (SQLException e) {
            settings.getPlugin().getLogger().warning(Ansi.ansi().fg(Ansi.Color.RED) + "Could not fetch the pet info." + Ansi.ansi().fg(Ansi.Color.WHITE));
        }
    }

    private void addToTable(Player player) {
        Connection connection = connect.getConnection();
        try {
            PreparedStatement add = connection.prepareStatement("INSERT INTO PetMenu(Uuid) VALUES(?)");
            add.setString(1, player.getUniqueId().toString());
            add.executeUpdate();
            connection.close();
        } catch (SQLException c) {
            settings.getPlugin().getLogger().warning(Ansi.ansi().fg(Ansi.Color.RED) + "Could not add the player to the PetMenu list." + Ansi.ansi().fg(Ansi.Color.WHITE));
        }
    }

    public HashMap<String, Boolean> getMenu(String uuid) {
        Connection connection = connect.getConnection();
        HashMap<String, Boolean> menu = new HashMap<>();
        try {
            PreparedStatement fetch = connection.prepareStatement("SELECT * FROM PetMenu WHERE Uuid='" + uuid + "'");
            ResultSetMetaData metaData = fetch.getMetaData();
            ResultSet results = fetch.executeQuery();
            while (results.next()) {
                for (int i = 3; i < metaData.getColumnCount() + 1; i++) {
                    menu.put(metaData.getColumnName(i), results.getBoolean(i));
                }
            }
            connection.close();
            return menu;
        } catch (SQLException e) {
            settings.getPlugin().getLogger().warning(Ansi.ansi().fg(Ansi.Color.RED) + "Could not fetch the pet info." + Ansi.ansi().fg(Ansi.Color.WHITE));
            return menu;
        }
    }

    public void setPet(String petName, String uuid, int bool) {
        Connection connection = connect.getConnection();
        try {
            PreparedStatement register = connection.prepareStatement("UPDATE PetMenu SET " + petName + "='" + bool + "' WHERE Uuid='" + uuid + "'");
            register.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            settings.getPlugin().getLogger().warning(Ansi.ansi().fg(Ansi.Color.RED) + "Could not update the player." + Ansi.ansi().fg(Ansi.Color.WHITE));
        }
    }

    /**
     * @param uuid
     * @param pet
     */
    public void unlockPet(String uuid, String pet) {
        Connection connection = connect.getConnection();
        try {
            PreparedStatement register = connection.prepareStatement("UPDATE PetMenu SET " + pet + "='1' WHERE Uuid='" + uuid + "'");
            register.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            settings.getPlugin().getLogger().warning(Ansi.ansi().fg(Ansi.Color.RED) + "Could not update the player." + Ansi.ansi().fg(Ansi.Color.WHITE));
        }
    }
}
