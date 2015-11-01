package io.github.mac_genius.cosmetics.Database.SQLObjects;

/**
 * Created by Mac on 10/31/2015.
 */
public class Pet {
    private String owner;
    private String name;
    private String type;

    public Pet(String owner, String name, String type) {
        this.owner = owner;
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getType() {
        return type;
    }
}
