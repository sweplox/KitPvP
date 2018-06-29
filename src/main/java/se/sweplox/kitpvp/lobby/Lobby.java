package se.sweplox.kitpvp.lobby;

import org.bukkit.Location;

public class Lobby {

    private Location location;

    public Lobby(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
