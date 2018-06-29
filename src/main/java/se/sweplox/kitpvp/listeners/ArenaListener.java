package se.sweplox.kitpvp.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import se.sweplox.kitpvp.Configuration;
import se.sweplox.kitpvp.arena.Arena;
import se.sweplox.kitpvp.events.PlayerJoinArenaEvent;
import se.sweplox.kitpvp.utils.ItemBuilder;

public class ArenaListener implements Listener {

    @EventHandler
    public void onPlayerJoinArena(PlayerJoinArenaEvent event) {
        Player player = event.getPlayer();
        Arena arena = event.getArena();

        player.teleport(arena.getLocation());
        player.sendMessage(Configuration.JOINED_ARENA.replace("%arena%", arena.getName()));

        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        ItemStack kitSelector = new ItemBuilder().setMaterial(Configuration.KIT_SELECTOR_MATERIAL).setName(Configuration.KIT_SELECTOR_NAME).toItemStack();
        ItemStack kitShop = new ItemBuilder().setMaterial(Configuration.KIT_SHOP_MATERIAL).setName(Configuration.KIT_SHOP_NAME).toItemStack();

        player.getInventory().setItem(Configuration.KIT_SELECTOR_SLOT, kitSelector);
        player.getInventory().setItem(Configuration.KIT_SHOP_SLOT, kitShop);
    }
}
