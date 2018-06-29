package se.sweplox.kitpvp.kit.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import se.sweplox.kitpvp.kit.Kit;
import se.sweplox.kitpvp.utils.ItemBuilder;

public class FighterKit extends Kit {


    public FighterKit() {
        super("Fighter");
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public Material getIcon() {
        return Material.DIAMOND_SWORD;
    }

    @Override
    public ItemStack[] getContents() {
        ItemStack sword = new ItemBuilder().setMaterial(Material.DIAMOND_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 1).toItemStack();
        ItemStack rod = new ItemBuilder().setMaterial(Material.FISHING_ROD).toItemStack();

        ItemStack[] contents = new ItemStack[36];
        contents[0] = sword;
        contents[1] = rod;

        return contents;
    }

    @Override
    public ItemStack[] getArmorContents() {
        ItemStack boots = new ItemBuilder().setMaterial(Material.IRON_BOOTS).toItemStack();
        ItemStack leggings = new ItemBuilder().setMaterial(Material.IRON_LEGGINGS).toItemStack();
        ItemStack chestplate = new ItemBuilder().setMaterial(Material.IRON_CHESTPLATE).toItemStack();
        ItemStack helmet = new ItemBuilder().setMaterial(Material.IRON_HELMET).toItemStack();

        return new ItemStack[] {
                boots,
                leggings,
                chestplate,
                helmet
        };
    }
}
