package io.github.mac_genius.cosmetics.StoreMenus;

import io.github.mac_genius.cosmetics.Database.Pets;
import io.github.mac_genius.cosmetics.Database.SQLObjects.Pet;
import io.github.mac_genius.cosmetics.Database.TokoinUpdater;
import io.github.mac_genius.cosmetics.PluginSettings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Mac on 10/23/2015.
 */
public class PetMenu implements Listener {
    private PluginSettings settings;
    private Player player;
    private int fancyshopID;

    public PetMenu(PluginSettings settings, Player player) {
        this.settings = settings;
        this.player = player;
        setInventory();
        BukkitTask fancyShop = settings.getPlugin().getServer().getScheduler().runTaskTimer(settings.getPlugin(), new FancyShop(), 0, 2);
        fancyshopID = fancyShop.getTaskId();
    }

    private void setInventory() {
        Inventory store = Bukkit.createInventory(player, 27, "Pet Shop");
        store.setContents(setRainbowBorder());
        store.setItem(12, getCowIcon());
        store.setItem(13, getPigIcon());
        store.setItem(14, getSheepIcon());
        player.openInventory(store);
    }

    private ItemStack[] setBorder() {
        ItemStack[] border = new ItemStack[27];
        for (int i = 0; i < 27; i++) {
            if (i < 9) {
                border[i] = makeItem(Material.STAINED_GLASS_PANE, ChatColor.GREEN + "", new ArrayList<>(), (byte) 15);
            } else if ((i + 1) % 9 == 0 || i % 9 == 0) {
                border[i] = makeItem(Material.STAINED_GLASS_PANE, ChatColor.GREEN + "", new ArrayList<>(), (byte) 15);
            } else if (i > 17 && i < 27) {
                border[i] = makeItem(Material.STAINED_GLASS_PANE, ChatColor.GREEN + "", new ArrayList<>(), (byte) 15);
            }
        }
        return border;
    }

    private ItemStack[] setRainbowBorder() {
        ItemStack[] border = new ItemStack[27];
        border[0] = makeItem(Material.STAINED_GLASS_PANE, ChatColor.BLACK + "", new ArrayList<>(), (byte) 14);
        border[1] = makeItem(Material.STAINED_GLASS_PANE, ChatColor.BLACK + "", new ArrayList<>(), (byte) 1);
        border[2] = makeItem(Material.STAINED_GLASS_PANE, ChatColor.BLACK + "", new ArrayList<>(), (byte) 4);
        border[3] = makeItem(Material.STAINED_GLASS_PANE, ChatColor.BLACK + "", new ArrayList<>(), (byte) 5);
        border[4] = makeItem(Material.STAINED_GLASS_PANE, ChatColor.BLACK + "", new ArrayList<>(), (byte) 3);
        border[5] = makeItem(Material.STAINED_GLASS_PANE, ChatColor.BLACK + "", new ArrayList<>(), (byte) 2);
        border[6] = makeItem(Material.STAINED_GLASS_PANE, ChatColor.BLACK + "", new ArrayList<>(), (byte) 6);
        border[7] = makeItem(Material.STAINED_GLASS_PANE, ChatColor.BLACK + "", new ArrayList<>(), (byte) 14);
        border[8] = makeItem(Material.STAINED_GLASS_PANE, ChatColor.BLACK + "", new ArrayList<>(), (byte) 1);
        border[9] = makeItem(Material.STAINED_GLASS_PANE, ChatColor.BLACK + "", new ArrayList<>(), (byte) 1);
        border[17] = makeItem(Material.STAINED_GLASS_PANE, ChatColor.BLACK + "", new ArrayList<>(), (byte) 4);
        border[18] = makeItem(Material.STAINED_GLASS_PANE, ChatColor.BLACK + "", new ArrayList<>(), (byte) 5);
        border[19] = makeItem(Material.STAINED_GLASS_PANE, ChatColor.BLACK + "", new ArrayList<>(), (byte) 3);
        border[20] = makeItem(Material.STAINED_GLASS_PANE, ChatColor.BLACK + "", new ArrayList<>(), (byte) 2);
        border[21] = makeItem(Material.STAINED_GLASS_PANE, ChatColor.BLACK + "", new ArrayList<>(), (byte) 6);
        border[22] = makeItem(Material.STAINED_GLASS_PANE, ChatColor.BLACK + "", new ArrayList<>(), (byte) 14);
        border[23] = makeItem(Material.STAINED_GLASS_PANE, ChatColor.BLACK + "", new ArrayList<>(), (byte) 1);
        border[24] = makeItem(Material.STAINED_GLASS_PANE, ChatColor.BLACK + "", new ArrayList<>(), (byte) 4);
        border[25] = makeItem(Material.STAINED_GLASS_PANE, ChatColor.BLACK + "", new ArrayList<>(), (byte) 5);
        border[26] = makeItem(Material.STAINED_GLASS_PANE, ChatColor.BLACK + "", new ArrayList<>(), (byte) 3);
        return border;
    }

    private ItemStack getCowIcon() {
        String name;
        ArrayList<String> lore = new ArrayList<>();
        if (settings.getPlayerData().get(player).getPets().get("Cow")) {
            name = ChatColor.AQUA + "Baby Cow";
            lore.add(ChatColor.WHITE + "A baby cow that will love");
            lore.add(ChatColor.WHITE + "you no matter what.");
            return makeItem(Material.MONSTER_EGG, name, lore, (byte) 92);
        } else {
            name = ChatColor.AQUA + "Baby Cow";
            lore.add(ChatColor.WHITE + "A baby cow that will love");
            lore.add(ChatColor.WHITE + "you no matter what.");
            lore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "Price: " + ChatColor.RESET + "" + ChatColor.WHITE + "1000 tokoins");
            return makeItem(Material.INK_SACK, name, lore, (byte) 8);
        }
    }

    private ItemStack getPigIcon() {
        String name;
        ArrayList<String> lore = new ArrayList<>();
        if (settings.getPlayerData().get(player).getPets().get("Pig")) {
            name = ChatColor.AQUA + "Baby Pig";
            lore.add(ChatColor.WHITE + "A baby pig that will follow");
            lore.add(ChatColor.WHITE + "you on your journeys.");
            return makeItem(Material.MONSTER_EGG, name, lore, (byte) 90);
        } else {
            name = ChatColor.AQUA + "Baby Pig";
            lore.add(ChatColor.WHITE + "A baby pig that will follow");
            lore.add(ChatColor.WHITE + "you on your journeys.");
            lore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "Price: " + ChatColor.RESET + "" + ChatColor.WHITE + "1000 tokoins");
            return makeItem(Material.INK_SACK, name, lore, (byte) 8);
        }
    }

    private ItemStack getSheepIcon() {
        String name;
        ArrayList<String> lore = new ArrayList<>();
        if (settings.getPlayerData().get(player).getPets().get("Sheep")) {
            name = ChatColor.AQUA + "Baby Sheep";
            lore.add(ChatColor.WHITE + "A baby sheep that will keep");
            lore.add(ChatColor.WHITE + "you warm and comfy.");
            return makeItem(Material.MONSTER_EGG, name, lore, (byte) 91);
        } else {
            name = ChatColor.AQUA + "Baby Sheep";
            lore.add(ChatColor.WHITE + "A baby sheep that will keep");
            lore.add(ChatColor.WHITE + "you warm and comfy.");
            lore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "Price: " + ChatColor.RESET + "" + ChatColor.WHITE + "1000 tokoins");
            return makeItem(Material.INK_SACK, name, lore, (byte) 8);
        }
    }

    private ItemStack getPetIcon(String type, String petName) {
        String name = ChatColor.LIGHT_PURPLE + "Pet Equipped  " + ChatColor.GRAY + "(Right Click to open)";
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "Type: " + type);
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

    private Entity spawnPet(String type, String name, boolean isSmall) {
        Entity animals = player.getWorld().spawnEntity(player.getLocation(), EntityType.valueOf(type.toUpperCase()));
        animals.setCustomName(ChatColor.translateAlternateColorCodes('&', name));
        if (animals instanceof Animals) {
            Animals animal = (Animals) animals;
            if (isSmall) {
                animal.setBaby();
                animal.setAgeLock(true);
            }
            animal.setTarget(player);
            return animal;
        }
        if (animals instanceof Creature) {
            ((Creature) animals).setTarget(player);
        }
        return animals;

    }

    @EventHandler
    public void close(InventoryCloseEvent event) {
        if (event.getPlayer() == player) {
            HandlerList.unregisterAll(this);
            settings.getPlugin().getServer().getScheduler().cancelTask(fancyshopID);
        }
    }

    @EventHandler
    public void click(InventoryClickEvent event) {
        if (event.getWhoClicked() == player) {
            if (event.getSlot() == 12) {
                if (settings.getPlayerData().get(player).getPets().get("Cow")) {
                    hasPet("Cow");
                } else {
                    noPet("Cow", 1000);
                }
            }
            if (event.getSlot() == 13) {
                if (settings.getPlayerData().get(player).getPets().get("Pig")) {
                    hasPet("Pig");
                } else {
                    noPet("Pig", 1000);
                }
            }
            if (event.getSlot() == 14) {
                if (settings.getPlayerData().get(player).getPets().get("Sheep")) {
                    hasPet("Sheep");
                } else {
                    noPet("Sheep", 1000);
                }
            }
            event.setCancelled(true);
        }
    }

    private void hasPet(String type) {
        if (!settings.getPlayerData().get(player).isPetEquipped()) {
            HandlerList.unregisterAll(this);
            Pet pet = new Pets(settings).getPet(player.getUniqueId().toString(), type);
            Entity entity = spawnPet(type, pet.getName(), true);
            settings.getPlayerData().get(player).equipPet(entity, type);
            player.getInventory().setItem(5, getPetIcon(type, pet.getName()));
            settings.getPlugin().getServer().getScheduler().cancelTask(fancyshopID);
            player.closeInventory();
        } else {
            if (settings.getPlayerData().get(player).getPetType().equalsIgnoreCase(type)) {
                HandlerList.unregisterAll(this);
                settings.getPlayerData().get(player).unEquipPet();
                player.getInventory().setItem(5, new ItemStack(Material.AIR));
                settings.getPlugin().getServer().getScheduler().cancelTask(fancyshopID);
                player.closeInventory();
            } else {
                HandlerList.unregisterAll(this);
                settings.getPlayerData().get(player).unEquipPet();
                Pet pet = new Pets(settings).getPet(player.getUniqueId().toString(), type);
                Entity entity = spawnPet(pet.getType(), pet.getName(), true);
                settings.getPlayerData().get(player).equipPet(entity, type);
                player.getInventory().setItem(5, getPetIcon(type, pet.getName()));
                settings.getPlugin().getServer().getScheduler().cancelTask(fancyshopID);
                player.closeInventory();
            }
        }
    }

    private void noPet(String type, int price) {
        if (settings.getPlayerData().get(player).isPetEquipped()) {
            settings.getPlayerData().get(player).unEquipPet();
        }
        if (settings.getPlayerData().get(player).getTokoins() >= price) {
            HandlerList.unregisterAll(this);
            Pets pet = new Pets(settings);
            pet.addPet(player, type);
            settings.getPlayerData().get(player).addTokoins(-1*price);
            io.github.mac_genius.cosmetics.Database.PetMenu menu = new io.github.mac_genius.cosmetics.Database.PetMenu(settings);
            menu.unlockPet(player.getUniqueId().toString(), type);
            Entity entity = spawnPet(type, "", true);
            settings.getPlayerData().get(player).equipPet(entity, type);
            player.getInventory().setItem(5, getPetIcon(type, ""));
            settings.getPlugin().getServer().getScheduler().cancelTask(fancyshopID);
            player.closeInventory();
        } else {
            HandlerList.unregisterAll(this);
            player.sendMessage(ChatColor.GREEN + "You don't have enough tokoins to purchase that item.");
            settings.getPlugin().getServer().getScheduler().cancelTask(fancyshopID);
            player.closeInventory();
        }
    }

    public class FancyShop implements Runnable {
        @Override
        public void run() {
            updateRainbowBorder();
        }
    }

    private int generateRandomSlot() {
        int slot = new Random().nextInt(27);
        if (slot == 12) {
            slot = generateRandomSlot();
        }
        return slot;
    }

    private void updateRainbowBorder() {
        if (player.getOpenInventory().getTopInventory() != null) {
            for (int i = 0; i < player.getOpenInventory().getTopInventory().getSize(); i++) {
                try {
                    if (i != 12 && i != 14) {
                        if (player.getOpenInventory().getTopInventory().getItem(i) != null) {
                            if (player.getOpenInventory().getTopInventory().getItem(i).getDurability() == (byte) 14) {
                                player.getOpenInventory().getTopInventory().setItem(i, makeItem(Material.STAINED_GLASS_PANE, ChatColor.GREEN + "", new ArrayList<>(), (byte) 6));
                            } else if (player.getOpenInventory().getTopInventory().getItem(i).getDurability() == (byte) 1) {
                                player.getOpenInventory().getTopInventory().setItem(i, makeItem(Material.STAINED_GLASS_PANE, ChatColor.GREEN + "", new ArrayList<>(), (byte) 14));
                            } else if (player.getOpenInventory().getTopInventory().getItem(i).getDurability() == (byte) 4) {
                                player.getOpenInventory().getTopInventory().setItem(i, makeItem(Material.STAINED_GLASS_PANE, ChatColor.GREEN + "", new ArrayList<>(), (byte) 1));
                            } else if (player.getOpenInventory().getTopInventory().getItem(i).getDurability() == (byte) 5) {
                                player.getOpenInventory().getTopInventory().setItem(i, makeItem(Material.STAINED_GLASS_PANE, ChatColor.GREEN + "", new ArrayList<>(), (byte) 4));
                            } else if (player.getOpenInventory().getTopInventory().getItem(i).getDurability() == (byte) 3) {
                                player.getOpenInventory().getTopInventory().setItem(i, makeItem(Material.STAINED_GLASS_PANE, ChatColor.GREEN + "", new ArrayList<>(), (byte) 5));
                            } else if (player.getOpenInventory().getTopInventory().getItem(i).getDurability() == (byte) 2) {
                                player.getOpenInventory().getTopInventory().setItem(i, makeItem(Material.STAINED_GLASS_PANE, ChatColor.GREEN + "", new ArrayList<>(), (byte) 3));
                            } else if (player.getOpenInventory().getTopInventory().getItem(i).getDurability() == (byte) 6) {
                                player.getOpenInventory().getTopInventory().setItem(i, makeItem(Material.STAINED_GLASS_PANE, ChatColor.GREEN + "", new ArrayList<>(), (byte) 2));
                            }
                        }
                    }
                } catch (NullPointerException e) {
                }
            }
        }
    }
}
