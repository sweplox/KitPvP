package se.sweplox.kitpvp.kit;

import java.util.HashMap;

public class KitManager {

    private HashMap<String, Kit> kits = new HashMap<String, Kit>();

    public void register(Kit kit) {
        kits.put(kit.getName().toLowerCase(), kit);
    }

    public Kit getKitByName(String name) {
        return kits.get(name.toLowerCase());
    }

    public HashMap<String, Kit> getKits() {
        return kits;
    }
}
