package se.sweplox.kitpvp.kit.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import se.sweplox.kitpvp.kit.Kit;
import se.sweplox.kitpvp.utils.ItemBuilder;

public class TestKit extends Kit {

    public TestKit() {
        super("Test");
    }

    public int getPrice() {
        return 100;
    }

    @Override
    public Material getIcon() {
        return Material.STONE;
    }

    public ItemStack[] getContents() {
        ItemStack sword = new ItemBuilder().setMaterial(Material.DIAMOND_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 3).toItemStack();
        ItemStack rod = new ItemBuilder().setMaterial(Material.FISHING_ROD).setDurability((short) 30).toItemStack();

        ItemStack[] contents = new ItemStack[] {
                sword,
                rod
        };

        return contents;
    }

    public ItemStack[] getArmorContents() {
        ItemStack helmet = new ItemBuilder().setMaterial(Material.DIAMOND_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack();
        ItemStack chestplate = new ItemBuilder().setMaterial(Material.CHAINMAIL_CHESTPLATE).setDurability((short) 100).toItemStack();
        ItemStack boots = new ItemBuilder().setMaterial(Material.IRON_BOOTS).addEnchantment(Enchantment.PROTECTION_FALL, 3).toItemStack();

        ItemStack[] armorContents = new ItemStack[] {
                boots,
                null,
                chestplate,
                helmet
        };

        return armorContents;
    }
}
