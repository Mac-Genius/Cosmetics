package io.github.mac_genius.cosmetics.Listeners;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import io.github.mac_genius.cosmetics.CacheStorage.PlayerData;
import io.github.mac_genius.cosmetics.PluginSettings;
import io.github.mac_genius.cosmetics.StoreMenus.BoneMenu;
import io.github.mac_genius.cosmetics.StoreMenus.StoreMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.*;

import java.util.regex.Matcher;

/**
 * Created by Mac on 10/31/2015.
 */
public class EventListeners implements Listener {
    private PluginSettings settings;

    public EventListeners(PluginSettings settings) {
        this.settings = settings;
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        settings.getPlayerData().put(event.getPlayer(), new PlayerData(event.getPlayer(), settings));
    }

    @EventHandler
    public void playerKick(PlayerKickEvent event) {
        settings.getPlayerData().remove(event.getPlayer());
    }

    @EventHandler
    public void playerLeave(PlayerQuitEvent event) {
        settings.getPlayerData().remove(event.getPlayer());
    }

    @EventHandler
    public void clickStore(PlayerInteractEvent event) {
        if (event.getItem() != null) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (event.getItem().getType() == Material.EMERALD && event.getItem().getItemMeta() != null && event.getItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "One Stop Shop  " + ChatColor.GRAY + "(Right Click to open)")) {
                    settings.getPlugin().getServer().getPluginManager().registerEvents(new StoreMenu(settings, event.getPlayer()), settings.getPlugin());
                } else if (event.getItem().getType() == Material.BONE && event.getPlayer().getInventory().getHeldItemSlot() == 5) {
                    settings.getPlugin().getServer().getPluginManager().registerEvents(new BoneMenu(settings, event.getPlayer()), settings.getPlugin());
                }
            }
        }
    }

    @EventHandler
    public void clickPet(PlayerInteractAtEntityEvent event) {
        for (Player p : settings.getPlayerData().keySet()) {
            if (event.getRightClicked().equals(settings.getPlayerData().get(p).getPet())) {
                event.getPlayer().sendMessage(ChatColor.GREEN + event.getRightClicked().getCustomName() + " isn't your pet.");
                event.setCancelled(true);
            }
        }
    }
}
