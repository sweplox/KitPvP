package se.sweplox.kitpvp.kit;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class Kit {

    private String name;

    public Kit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int getPrice();

    public abstract Material getIcon();

    public abstract ItemStack[] getContents();

    public abstract ItemStack[] getArmorContents();
}
