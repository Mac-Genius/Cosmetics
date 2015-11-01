package io.github.mac_genius.cosmetics.Util;

import io.github.mac_genius.cosmetics.PluginSettings;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

/**
 * Created by Mac on 10/31/2015.
 */
public class CancelListener implements Runnable {
    private Listener toCancel;
    private Player player;
    private String message;

    public CancelListener(Listener toCancel, Player player, String message) {
        this.toCancel = toCancel;
        this.player = player;
        this.message = message;
    }

    @Override
    public void run() {
        HandlerList.unregisterAll(toCancel);
        player.sendMessage(message);
    }
}
