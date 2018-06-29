package se.sweplox.kitpvp.kit.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import se.sweplox.kitpvp.kit.Kit;
import se.sweplox.kitpvp.utils.ItemBuilder;

public class ArcherKit extends Kit {


    public ArcherKit() {
        super("Archer");
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public Material getIcon() {
        return Material.BOW;
    }

    @Override
    public ItemStack[] getContents() {
        ItemStack sword = new ItemBuilder().setMaterial(Material.DIAMOND_SWORD).toItemStack();
        ItemStack rod = new ItemBuilder().setMaterial(Material.FISHING_ROD).toItemStack();
        ItemStack bow = new ItemBuilder().setMaterial(Material.BOW).addEnchantment(Enchantment.ARROW_DAMAGE, 1).toItemStack();

        ItemStack[] contents = new ItemStack[36];
        contents[0] = sword;
        contents[1] = rod;
        contents[2] = bow;

        return contents;
    }

    @Override
    public ItemStack[] getArmorContents() {
        ItemStack boots = new ItemBuilder().setMaterial(Material.CHAINMAIL_BOOTS).toItemStack();
        ItemStack leggings = new ItemBuilder().setMaterial(Material.CHAINMAIL_LEGGINGS).toItemStack();
        ItemStack chestplate = new ItemBuilder().setMaterial(Material.CHAINMAIL_CHESTPLATE).toItemStack();
        ItemStack helmet = new ItemBuilder().setMaterial(Material.CHAINMAIL_HELMET).toItemStack();

        return new ItemStack[] {
                boots,
                leggings,
                chestplate,
                helmet
        };
    }
}
