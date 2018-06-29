package se.sweplox.kitpvp.arena;

import org.bukkit.Location;
import se.sweplox.kitpvp.kit.Kit;

import java.util.HashMap;

public class Arena {

    private String name;
    private Location location;
    private HashMap<String, Kit> kits;

    public Arena(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public HashMap<String, Kit> getKits() {
        return kits;
    }

    public void setKits(HashMap<String, Kit> kits) {
        this.kits = kits;
    }
}
