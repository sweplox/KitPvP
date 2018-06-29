package se.sweplox.kitpvp.storage;

import se.sweplox.kitpvp.arena.Arena;
import se.sweplox.kitpvp.lobby.Lobby;
import se.sweplox.kitpvp.player.PVPPlayer;


public interface Storage {

    void init();

    void checkTables();

    void save(PVPPlayer pvpPlayer);

    void save(Arena arena);

    void saveLobby();

    void load(PVPPlayer pvpPlayer);

    void loadLobby();

    void loadArenas();

    boolean check(PVPPlayer pvpPlayer);
}
