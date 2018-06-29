package se.sweplox.kitpvp.arena;


import java.util.HashMap;

public class ArenaManager {

    private HashMap<String, Arena> arenas = new HashMap<>();

    public void load(Arena arena) {
        arenas.put(arena.getName().toLowerCase(), arena);
    }

    public void unload(Arena arena) { arenas.remove(arena.getName().toLowerCase()); }

    public Arena getArenaByName(String name) {
        return arenas.get(name.toLowerCase());
    }

    public HashMap<String, Arena> getArenas() {
        return arenas;
    }
}
