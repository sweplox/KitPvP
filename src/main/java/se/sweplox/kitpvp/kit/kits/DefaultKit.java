package se.sweplox.kitpvp.kit.kits;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import se.sweplox.kitpvp.kit.Kit;
import se.sweplox.kitpvp.utils.ItemBuilder;

public class DefaultKit extends Kit {


    public DefaultKit() {
        super("Default");
    }

    public int getPrice() {
        return 100;
    }

    @Override
    public Material getIcon() {
        return Material.DIAMOND_SWORD;
    }

    public ItemStack[] getContents() {
        ItemStack sword = new ItemBuilder().setMaterial(Material.DIAMOND_SWORD).setName("Hello").toItemStack();
        ItemStack rod = new ItemStack(Material.FISHING_ROD);

        ItemStack[] contents = new ItemStack[] {
                sword,
                null,
                rod
        };

        return contents;
    }

    public ItemStack[] getArmorContents() {
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        ItemStack leggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        ItemStack helmet = new ItemStack(Material.IRON_HELMET);


        ItemStack[] armorContents = new ItemStack[] {
                boots,
                leggings,
                null,
                helmet
        };

        return armorContents;
    }
}
