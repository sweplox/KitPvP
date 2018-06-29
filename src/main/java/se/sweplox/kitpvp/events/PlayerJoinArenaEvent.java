package se.sweplox.kitpvp.events;


import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import se.sweplox.kitpvp.arena.Arena;

public class PlayerJoinArenaEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();

    private Player player;
    private Arena arena;

    public PlayerJoinArenaEvent(Player player, Arena arena) {
        this.arena = arena;
        this.player = player;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public HandlerList getHandlers() {
        return handlerList;
    }

    public Arena getArena() {
        return arena;
    }

    public Player getPlayer() {
        return player;
    }

}
