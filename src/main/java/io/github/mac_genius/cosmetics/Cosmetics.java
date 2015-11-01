package io.github.mac_genius.cosmetics;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import io.github.mac_genius.cosmetics.Listeners.EventListeners;
import io.github.mac_genius.cosmetics.Listeners.MessageListener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Mac on 10/31/2015.
 */
public class Cosmetics extends JavaPlugin {
    private Plugin plugin = this;

    public void onEnable() {
        PluginSettings settings = new PluginSettings(plugin);
        settings.getPlugin().getServer().getPluginManager().registerEvents(new EventListeners(settings), settings.getPlugin());
        /*this.getServer().getMessenger().registerOutgoingPluginChannel(settings.getPlugin(), "Cosmetics");
        this.getServer().getMessenger().registerIncomingPluginChannel(settings.getPlugin(), "Cosmetics", new MessageListener(settings));*/
        plugin.getLogger().info("Plugin Enabled");
    }

    @Override
    public void onDisable() {
        plugin.getLogger().info("Plugin disabled");
    }
}
