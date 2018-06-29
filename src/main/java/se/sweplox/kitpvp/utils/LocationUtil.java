package se.sweplox.kitpvp.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationUtil {

    public static String toString(Location location) {
        String separator = ":";
        return location.getWorld().getName() + separator + location.getX() + separator + location.getY() + separator + location.getZ() + separator + location.getYaw() + separator + location.getPitch();
    }

    public static Location fromString(String string) {
        String[] location = string.split(":");
        World world = Bukkit.getWorld(location[0]);
        double x = Double.parseDouble(location[1]);
        double y = Double.parseDouble(location[2]);
        double z = Double.parseDouble(location[3]);
        float yaw = Float.parseFloat(location[4]);
        float pitch = Float.parseFloat(location[5]);

        return new Location(world, x, y, z, yaw, pitch);
    }
}
