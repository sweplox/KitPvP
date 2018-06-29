package se.sweplox.kitpvp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import se.sweplox.kitpvp.Configuration;
import se.sweplox.kitpvp.KitPvP;
import se.sweplox.kitpvp.kit.Kit;
import se.sweplox.kitpvp.player.PVPPlayer;
import se.sweplox.kitpvp.utils.ItemBuilder;
import se.sweplox.kitpvp.utils.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventoryListener implements Listener {

    private KitPvP instance;

    public InventoryListener(KitPvP instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        PVPPlayer pvpPlayer = instance.getPlayerManager().getPlayerByUUID(player.getUniqueId());
        if(pvpPlayer == null) return;

        Inventory inventory = event.getClickedInventory();

        if(inventory == null) return;

        String prefix = Configuration.KIT_BUY_TITLE.replace("%kit%", "");
        if(inventory.getTitle().startsWith(prefix)) {
            if(event.getSlot() == 12) {
                String name = ChatColor.stripColor(inventory.getTitle().replace(prefix, ""));
                Kit kit = instance.getKitManager().getKitByName(name);

                if(name == null) {
                    player.closeInventory();
                    return;
                }

                if(kit == null) {
                    player.closeInventory();
                    return;
                }

                if(kit.getPrice() > instance.getEconomy().getBalance(player)) {
                    player.sendMessage(Configuration.NOT_ENOUGH_MONEY.replace("%kit%", kit.getName()));
                    player.closeInventory();
                    return;
                }

                pvpPlayer.giveKit(kit);
                instance.getEconomy().withdrawPlayer(player, kit.getPrice());
                player.sendMessage(Configuration.BOUGHT_KIT.replace("%kit%", kit.getName()).replace("%price%", String.valueOf(kit.getPrice())));
                player.closeInventory();
                return;
            }

            player.closeInventory();
        }

        if(inventory.getItem(event.getSlot()) == null) return;

        String name = ChatColor.stripColor(inventory.getItem(event.getSlot()).getItemMeta().getDisplayName());
        if(name == null) return;

        if(instance.getKitManager().getKitByName(name) == null) return;
        Kit kit = instance.getKitManager().getKitByName(name);

        if(inventory.getTitle().equals(Configuration.KIT_SELECTOR_TITLE)) {
            player.getInventory().setContents(kit.getContents());
            player.getInventory().setArmorContents(kit.getArmorContents());
            player.sendMessage(Configuration.SELECTED_KIT.replace("%kit%", kit.getName()));
            player.closeInventory();
            return;
        } else if(inventory.getTitle().equals(Configuration.KIT_SHOP_TITLE)) {
            List<String> lore = new ArrayList<>();
            Configuration.KIT_BUY_CONFIRM_LORE.forEach(string -> lore.add(string.replace("%price%", String.valueOf(kit.getPrice()))));

            Inventory buyInventory = Bukkit.createInventory(null, Configuration.KIT_BUY_SIZE, Configuration.KIT_BUY_TITLE.replace("%kit%", name));
            ItemStack confirmItem = new ItemBuilder().setMaterial(Configuration.KIT_BUY_CONFIRM_MATERIAL).setDurability((short) Configuration.KIT_BUY_CONFIRM_DURABILITY).setName(Configuration.KIT_BUY_CONFIRM_NAME).setLore(lore).toItemStack();
            ItemStack cancelItem = new ItemBuilder().setMaterial(Material.STAINED_GLASS_PANE).setDurability((short) Configuration.KIT_BUY_CANCEL_DURABILITY).setName(Configuration.KIT_BUY_CANCEL_NAME).setLore(Configuration.KIT_BUY_CANCEL_LORE).toItemStack();
            buyInventory.setItem(Configuration.KIT_BUY_CONFIRM_SLOT, confirmItem);
            buyInventory.setItem(Configuration.KIT_BUY_CANCEL_SLOT, cancelItem);
            player.openInventory(buyInventory);
            return;
        }
    }
}
