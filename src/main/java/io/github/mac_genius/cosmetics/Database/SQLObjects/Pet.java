package io.github.mac_genius.cosmetics.Database.SQLObjects;

/**
 * An object that represents a player's pet.
 */
public class Pet {
    private String owner;
    private String name;
    private String type;

    /**
     * Constructor
     * @param owner the pet owner's name
     * @param name the pet's name
     * @param type the pet's type
     */
    public Pet(String owner, String name, String type) {
        this.owner = owner;
        this.name = name;
        this.type = type;
    }

    /**
     * The name of the pet
     * @return the pet's name
     */
    public String getName() {
        return name;
    }

    /**
     * The pet owner's name
     * @return owner's name
     */
    public String getOwner() {
        return owner;
    }

    /**
     * The pet's type
     * @return the type
     */
    public String getType() {
        return type;
    }
}
