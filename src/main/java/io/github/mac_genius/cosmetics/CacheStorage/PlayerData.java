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
 * Used to store data about each player
 */
public class PlayerData {
    private Player player;
    private PluginSettings settings;
    private Entity pet;
    private String petType;
    private HashMap<String, Boolean> pets;
    private int tokoins;

    /**
     * Constructor for player data
     * @param player The player that will have data stored
     * @param settings PluginSettings
     */
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

    /**
     * Checks to see if a player has a pet equipped
     * @return Whether a pet is equipped or not
     */
    public boolean isPetEquipped() {
        return pet != null;
    }

    /**
     * Equips a pet
     * @param entity the pet to be equipped
     * @param petType the pet type
     */
    public void equipPet(Entity entity, String petType) {
        pet = entity;
        this.petType = petType;
    }

    /**
     * Removes the player's current pet
     */
    public void unEquipPet() {
        pet.remove();
        pet = null;
    }

    /**
     * Gets the pet type
     * @return the pet type
     */

    public String getPetType() {
        return petType;
    }

    /**
     * The player's pet
     * @return the player's pet
     */
    public Entity getPet() {
        return pet;
    }

    /**
     * Renames the equipped pet
     * @param name of the pet
     */
    public void renamePet(String name) {
        pet.setCustomName(ChatColor.translateAlternateColorCodes('&', name));
        new Pets(settings).setName(player.getUniqueId().toString(), petType, name);
    }

    /**
     * A map of pets to whether the player has bought them
     * @return map of pets to whether they have been bought
     */
    public HashMap<String, Boolean> getPets() {
        return pets;
    }

    /**
     * Gets the player's tokoin amount
     * @return tokoins
     */
    public int getTokoins() {
        return tokoins;
    }

    /**
     * Adds tokoins to the total tokoin amount
     * @param toAdd the tokoin amount
     */
    public void addTokoins(int toAdd) {
        new TokoinUpdater(settings, player).addTokoins(toAdd);
        this.tokoins += toAdd;
    }
}
