package se.sweplox.kitpvp.storage.type;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import se.sweplox.kitpvp.KitPvP;
import se.sweplox.kitpvp.arena.Arena;
import se.sweplox.kitpvp.kit.Kit;
import se.sweplox.kitpvp.lobby.Lobby;
import se.sweplox.kitpvp.player.PVPPlayer;
import se.sweplox.kitpvp.storage.Storage;
import se.sweplox.kitpvp.utils.KitUtil;
import se.sweplox.kitpvp.utils.LocationUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class YAMLStorage implements Storage {

    private KitPvP instance;

    public YAMLStorage(KitPvP instance) {
        this.instance = instance;
    }

    private File players;
    private File arenas;
    private File lobby;

    private FileConfiguration playersConfig;
    private FileConfiguration arenasConfig;
    private FileConfiguration lobbyConfig;

    @Override
    public void init() {
        instance.getDataFolder().mkdirs();

        players = new File(instance.getDataFolder(), "players.yml");
        arenas = new File(instance.getDataFolder(), "arenas.yml");
        lobby = new File(instance.getDataFolder(), "lobby.yml");

        playersConfig = YamlConfiguration.loadConfiguration(players);
        arenasConfig = YamlConfiguration.loadConfiguration(arenas);
        lobbyConfig = YamlConfiguration.loadConfiguration(lobby);

        if(!players.exists()) {
            try {
                players.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(!arenas.exists()) {
            try {
                arenas.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(!lobby.exists()) {
            try {
                lobby.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        loadArenas();
        loadLobby();
    }

    @Override
    public void checkTables() {

    }

    @Override
    public void save(PVPPlayer pvpPlayer) {
        String path = "Player." + pvpPlayer.getPlayer().getUniqueId().toString();
        playersConfig.set(path + ".uuid", pvpPlayer.getPlayer().getUniqueId().toString());
        playersConfig.set(path + ".kits", KitUtil.toString(pvpPlayer.getKits().keySet()));
        playersConfig.set(path + ".kills", pvpPlayer.getKills());
        playersConfig.set(path + ".deaths", pvpPlayer.getDeaths());
        save(playersConfig, players);
    }

    @Override
    public void save(Arena arena) {
        String path = "Arena." + arena.getName().toLowerCase();
        arenasConfig.set(path + ".name", arena.getName());
        if(arena.getLocation() != null) arenasConfig.set(path + ".location", LocationUtil.toString(arena.getLocation()));
        arenasConfig.set(path + ".kits", KitUtil.toString(arena.getKits().keySet()));
        arenasConfig.set(path + ".soup", arena.isSoup());
        save(arenasConfig, arenas);
    }

    @Override
    public void saveLobby() {
        if(instance.getLobbyManager().getLobby() == null) return;

        String location = LocationUtil.toString(instance.getLobbyManager().getLobby().getLocation());
        lobbyConfig.set("lobby_location", location);
        save(lobbyConfig, lobby);
    }

    @Override
    public void load(PVPPlayer pvpPlayer) {
        String path = "Player." + pvpPlayer.getPlayer().getUniqueId().toString();

        if(playersConfig.get(path) == null) return;

        HashMap<String, Kit> kits = new HashMap<>();
        if(playersConfig.getString(path + ".kits") != null) {
            Set<String> kitSet = KitUtil.fromString(playersConfig.getString(path + ".kits"));
            kitSet.forEach(kit -> {
                if (instance.getKitManager().getKitByName(kit) != null) {
                    kits.put(kit, instance.getKitManager().getKitByName(kit));
                }
            });
        }

        pvpPlayer.setKits(kits);
        pvpPlayer.setKills(playersConfig.getInt(path + ".kills"));
        pvpPlayer.setDeaths(playersConfig.getInt(path + ".deaths"));
    }

    @Override
    public void loadLobby() {
        if(lobbyConfig.getString("lobby_location") == null) return;

        Location location = LocationUtil.fromString(lobbyConfig.getString("lobby_location"));
        Lobby lobby = new Lobby(location);
        instance.getLobbyManager().setLobby(lobby);
    }

    @Override
    public void loadArenas() {
        if(arenasConfig.getConfigurationSection("Arena") == null) return;

        for(String string : arenasConfig.getConfigurationSection("Arena").getKeys(false)) {
            String path = "Arena." + string;

            Location location = null;
            if(arenasConfig.getString(path + ".location") != null) {
                location = LocationUtil.fromString(arenasConfig.getString(path + ".location"));
            }

            HashMap<String, Kit> kits = new HashMap<>();
            if(arenasConfig.getString(path + ".kits") != null) {
                Set<String> kitSet = KitUtil.fromString(arenasConfig.getString(path + ".kits"));
                kitSet.forEach(kit -> {
                    if (instance.getKitManager().getKitByName(kit) != null) {
                        kits.put(kit, instance.getKitManager().getKitByName(kit));
                    }
                });
            }

            boolean soup = arenasConfig.getBoolean(path + ".soup");

            Arena arena = new Arena(arenasConfig.getString(path + ".name"));
            arena.setLocation(location);
            arena.setKits(kits);
            arena.setSoup(soup);

            instance.getArenaManager().load(arena);
        }
    }

    @Override
    public boolean check(PVPPlayer pvpPlayer) {
        return true;
    }

    private void save(FileConfiguration fileConfiguration, File file) {
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
