package io.github.mac_genius.cosmetics.CacheStorage;

import io.github.mac_genius.cosmetics.Database.PetMenu;
import io.github.mac_genius.cosmetics.Database.Pets;
import io.github.mac_genius.cosmetics.Database.TokoinUpdater;
import io.github.mac_genius.cosmetics.PluginSettings;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by Mac on 10/31/2015.
 */
public class PlayerData {
    private Player player;
    private PluginSettings settings;
    private Entity pet;
    private String petType;
    private HashMap<String, Boolean> pets;
    private int tokoins;

    public PlayerData(Player player, PluginSettings settings) {
        this.player = player;
        this.settings = settings;
        PetMenu menu = new PetMenu(settings);
        menu.addMenu(player);
        pets = menu.getMenu(player.getUniqueId().toString());
        TokoinUpdater updater = new TokoinUpdater(settings, player);
        updater.addPlayer();
        tokoins = updater.getTokoins();
    }

    public boolean isPetEquipped() {
        return pet != null;
    }

    public void equipPet(Entity entity, String petType) {
        pet = entity;
        this.petType = petType;
    }

    public void unEquipPet() {
        pet.remove();
        pet = null;
    }

    public String getPetType() {
        return petType;
    }

    public Entity getPet() {
        return pet;
    }

    public void renamePet(String name) {
        pet.setCustomName(ChatColor.translateAlternateColorCodes('&', name));
        new Pets(settings).setName(player.getUniqueId().toString(), petType, name);
    }

    public HashMap<String, Boolean> getPets() {
        return pets;
    }

    public int getTokoins() {
        return tokoins;
    }

    public void addTokoins(int toAdd) {
        new TokoinUpdater(settings, player).addTokoins(toAdd);
        this.tokoins += toAdd;
    }
}
