package io.github.mac_genius.cosmetics;

import io.github.mac_genius.cosmetics.CacheStorage.PlayerData;
import io.github.mac_genius.cosmetics.Database.SQLConnect;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.fusesource.jansi.Ansi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mac on 10/31/2015.
 */
public class PluginSettings {
    private Plugin plugin;
    private Map<Player, PlayerData> playerData;
    private SQLConnect connect;

    public PluginSettings(Plugin plugin) {
        this.plugin = plugin;
        playerData = Collections.synchronizedMap(new HashMap<Player, PlayerData>());
        plugin.saveConfig();
        setupSQL();
    }

    private void setupSQL() {
        connect = new SQLConnect(this);
        if (connect.testConnection()) {
            connect.databaseSetup();
            plugin.getLogger().info(Ansi.ansi().fg(Ansi.Color.GREEN) + "Connected to the database!" + Ansi.ansi().fg(Ansi.Color.WHITE));
        } else {
            plugin.getLogger().warning(Ansi.ansi().fg(Ansi.Color.RED) + "Could not connect to the database!" + Ansi.ansi().fg(Ansi.Color.WHITE));
        }
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public Map<Player, PlayerData> getPlayerData() {
        return playerData;
    }
}
