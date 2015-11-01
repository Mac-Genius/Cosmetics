package io.github.mac_genius.cosmetics.StoreMenus;

import io.github.mac_genius.cosmetics.Database.Pets;
import io.github.mac_genius.cosmetics.PluginSettings;
import io.github.mac_genius.cosmetics.Util.CancelListener;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

/**
 * Created by Mac on 10/23/2015.
 */
public class Rename implements Listener {
    private PluginSettings settings;
    private Player player;
    int taskID;

    public Rename(PluginSettings settings, Player player) {
        this.settings = settings;
        this.player = player;
        player.sendMessage(ChatColor.GREEN + "Please type a name or type 'cancel'.");
        BukkitTask task = settings.getPlugin().getServer().getScheduler().runTaskLater(settings.getPlugin(), new CancelListener(this, player, ChatColor.GREEN + "Renaming cancelled"), 1200);
        taskID = task.getTaskId();
    }

    private ItemStack getPetIcon(String type, String petName) {
        String name = ChatColor.LIGHT_PURPLE + "Pet Equipped  " + ChatColor.GRAY + "(Right Click to open)";
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + type);
        lore.add(ChatColor.WHITE + "Name: " + petName);
        return makeItem(Material.BONE, name, lore, (byte) 0);
    }

    private ItemStack makeItem(Material type, String name, ArrayList<String> lore, byte color) {
        ItemStack item = new ItemStack(type, 1, color);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    private String getpetType(String string) {
        if (string.equalsIgnoreCase("cow")) {
            return "Cow";
        } else if (string.equalsIgnoreCase("pig")) {
            return "Pig";
        } else if (string.equalsIgnoreCase("sheep")) {
            return "Sheep";
        } else {
            return "";
        }
    }

    @EventHandler
    public void name(AsyncPlayerChatEvent event) {
        if (event.getPlayer() == player) {
            settings.getPlugin().getServer().getScheduler().cancelTask(taskID);
            HandlerList.unregisterAll(this);
            if (!event.getMessage().equalsIgnoreCase("cancel")) {
                if (event.getMessage().length() < 26) {
                    settings.getPlayerData().get(player).renamePet(event.getMessage());
                    player.getInventory().setItem(5, getPetIcon(settings.getPlayerData().get(player).getPetType(), ChatColor.translateAlternateColorCodes('&', event.getMessage())));
                    player.sendMessage(ChatColor.GREEN + "Your pet has been renamed to " + event.getMessage() + "!");
                } else {
                    player.sendMessage(ChatColor.GREEN + "The name was too long, try again.");
                    settings.getPlugin().getServer().getPluginManager().registerEvents(new Rename(settings, player), settings.getPlugin());
                }
            } else {
                player.sendMessage(ChatColor.GREEN + "Renaming cancelled");
            }
            event.setCancelled(true);
        }
    }
}
