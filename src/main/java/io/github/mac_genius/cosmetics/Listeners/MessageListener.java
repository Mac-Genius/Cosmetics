package io.github.mac_genius.cosmetics.Listeners;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import io.github.mac_genius.cosmetics.PluginSettings;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.ArrayList;

/**
 * Created by Mac on 10/31/2015.
 */
public class MessageListener implements PluginMessageListener {
    private PluginSettings settings;

    public MessageListener(PluginSettings settings) {
        this.settings = settings;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        player.sendMessage("derp");
        settings.getPlugin().getLogger().info("Recieved");
        if (!channel.equals("Cosmetics")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(bytes);
        String subChannel = in.readUTF();
        if (subChannel.equals("enable")) {
            player.getInventory().setItem(4, getStoreIcon());
        } else if (subChannel.equals("Testing")) {
            settings.getPlugin().getLogger().info("Testing complete");
        }
    }

    private ItemStack getStoreIcon() {
        String name = ChatColor.GREEN + "One Stop Shop  " + ChatColor.GRAY + "(Right Click to open)";
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.ITALIC + "A place to get all of your visual goods!");
        return makeItem(Material.EMERALD, name, lore, (byte) 0);
    }

    private ItemStack makeItem(Material type, String name, ArrayList<String> lore, byte color) {
        ItemStack item = new ItemStack(type, 1, color);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }
}
