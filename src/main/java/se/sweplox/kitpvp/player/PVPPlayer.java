package se.sweplox.kitpvp.player;

import org.bukkit.entity.Player;
import se.sweplox.kitpvp.arena.Arena;
import se.sweplox.kitpvp.kit.Kit;

import java.util.HashMap;

public class PVPPlayer {

    private Player player;
    private Arena arena;
    private Kit kit;
    private HashMap<String, Kit> kits;
    private int kills;
    private int deaths;

    public PVPPlayer(Player player) {
        this.player = player;
        kits = new HashMap<>();
    }

    public Player getPlayer() { return player; }

    public Kit getKit() {
        return kit;
    }

    public void setKit(Kit kit) {
        this.kit = kit;
    }

    public HashMap<String, Kit> getKits() {
        return kits;
    }

    public void setKits(HashMap<String, Kit> kits) {
        this.kits = kits;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public double getKdr() {
        if(deaths == 0) return (double) kills;
        return (double) kills / deaths;
    }

    public boolean ownsKit(Kit kit) { return kits.containsKey(kit.getName().toLowerCase()); }

    public void giveKit(Kit kit) { kits.put(kit.getName().toLowerCase(), kit); }

    public Arena getArena() { return arena; }

    public void setArena(Arena arena) { this.arena = arena; }
}
