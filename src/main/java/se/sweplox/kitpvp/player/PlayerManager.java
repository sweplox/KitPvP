package se.sweplox.kitpvp.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import se.sweplox.kitpvp.KitPvP;
import se.sweplox.kitpvp.kit.kits.DefaultKit;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {

    private KitPvP instance;

    public PlayerManager(KitPvP instance) {
        this.instance = instance;
    }

    private HashMap<UUID, PVPPlayer> players = new HashMap<>();

    public void load(Player player) {
        PVPPlayer pvpPlayer = new PVPPlayer(player);

        pvpPlayer.giveKit(new DefaultKit());
        instance.getStorage().load(pvpPlayer);

        players.put(player.getUniqueId(), pvpPlayer);
    }

    public void unload(Player player) {
        PVPPlayer pvpPlayer = players.get(player.getUniqueId());
        instance.getStorage().save(pvpPlayer);

        players.remove(player.getUniqueId());
    }

    public PVPPlayer getPlayerByUUID(UUID uuid) {
        return players.get(uuid);
    }

    public HashMap<UUID, PVPPlayer> getPlayers() {
        return players;
    }
}
