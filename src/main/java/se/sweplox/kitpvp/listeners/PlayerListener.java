package se.sweplox.kitpvp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import se.sweplox.kitpvp.Configuration;
import se.sweplox.kitpvp.KitPvP;
import se.sweplox.kitpvp.arena.Arena;
import se.sweplox.kitpvp.events.PlayerJoinArenaEvent;
import se.sweplox.kitpvp.kit.Kit;
import se.sweplox.kitpvp.player.PVPPlayer;
import se.sweplox.kitpvp.utils.ItemBuilder;
import se.sweplox.kitpvp.utils.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerListener implements Listener {

    private KitPvP instance;

    public PlayerListener(KitPvP instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        instance.getPlayerManager().load(player);

        if(instance.getLobbyManager().getLobby() == null) return;

        player.teleport(instance.getLobbyManager().getLobby().getLocation());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        instance.getPlayerManager().unload(player);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        PVPPlayer pvpPlayer = instance.getPlayerManager().getPlayerByUUID(player.getUniqueId());

        if(pvpPlayer == null) return;

        if(event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();

            if(block.getType() == Material.WALL_SIGN || block.getType() == Material.SIGN_POST) {
                Sign sign = (Sign) block.getState();
                String[] lines = sign.getLines();

                if (!lines[0].equalsIgnoreCase(Configuration.SIGN_LINE_ONE)) return;
                if (lines[1].equals("")) return;

                String name = ChatColor.stripColor(lines[1]);
                Arena arena = instance.getArenaManager().getArenaByName(name);

                if (arena == null) {
                    player.sendMessage(Configuration.ARENA_DOES_NOT_EXIST.replace("%arena%", name));
                    return;
                }

                if (arena.getLocation() == null) {
                    player.sendMessage(Configuration.ARENA_LOCATION_NOT_SET.replace("%arena%", name));
                    return;
                }

                pvpPlayer.setArena(arena);
                PlayerJoinArenaEvent playerJoinArenaEvent = new PlayerJoinArenaEvent(player, arena);
                instance.getServer().getPluginManager().callEvent(playerJoinArenaEvent);
            }
        }

        ItemStack item = event.getItem();

        if(item == null) return;
        if(!item.hasItemMeta()) return;

        if(item.getType() == Configuration.KIT_SELECTOR_MATERIAL) {
            if(!item.getItemMeta().getDisplayName().equals(Configuration.KIT_SELECTOR_NAME)) return;

            Inventory inventory = Bukkit.createInventory(null, Configuration.KIT_SELECTOR_SIZE, Configuration.KIT_SELECTOR_TITLE);

            int i = 0;
            for(Kit kit : pvpPlayer.getArena().getKits().values()) {
                if(pvpPlayer.ownsKit(kit)) {
                    List<String> lore = new ArrayList<>();
                    Configuration.KIT_OWNED_LORE.forEach(line -> lore.add(StringUtil.color(line)));
                    ItemStack itemStack = new ItemBuilder().setMaterial(kit.getIcon()).setName(Configuration.KIT_OWNED_NAME.replace("%kit%", kit.getName())).setLore(lore).toItemStack();
                    inventory.setItem(i, itemStack);
                    i++;
                    continue;
                }
            }

            player.openInventory(inventory);
        } else if(item.getType() == Configuration.KIT_SHOP_MATERIAL) {
            if(!item.getItemMeta().getDisplayName().equals(Configuration.KIT_SHOP_NAME)) return;

            Inventory inventory = Bukkit.createInventory(null, Configuration.KIT_SHOP_SIZE, Configuration.KIT_SHOP_TITLE);

            int i = 0;
            for(Kit kit : pvpPlayer.getArena().getKits().values()) {
                if(!pvpPlayer.ownsKit(kit)) {
                    List<String> lore = new ArrayList<>();
                    Configuration.KIT_NOT_OWNED_LORE.forEach(line -> lore.add(StringUtil.color(line).replace("%price%", String.valueOf(kit.getPrice()))));
                    ItemStack itemStack = new ItemBuilder().setMaterial(kit.getIcon()).setName(Configuration.KIT_NOT_OWNED_NAME.replace("%kit%", kit.getName())).setLore(lore).toItemStack();
                    inventory.setItem(i, itemStack);
                    i++;
                }
            }

            player.openInventory(inventory);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        PVPPlayer pvpPlayer = instance.getPlayerManager().getPlayerByUUID(player.getUniqueId());

        if(pvpPlayer == null) return;

        pvpPlayer.setDeaths(pvpPlayer.getDeaths() + 1);
        pvpPlayer.setArena(null);
        pvpPlayer.setKit(null);

        if(player.getKiller() instanceof Player) {
            Player killer = player.getKiller();
            PVPPlayer pvpKiller = instance.getPlayerManager().getPlayerByUUID(killer.getUniqueId());

            if(pvpKiller == null) return;

            pvpKiller.setKills(pvpKiller.getKills() + 1);
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        new BukkitRunnable() {
            @Override
            public void run() {
                player.teleport(instance.getLobbyManager().getLobby().getLocation());
            }
        }.runTaskLater(instance, 1L);
    }
}
