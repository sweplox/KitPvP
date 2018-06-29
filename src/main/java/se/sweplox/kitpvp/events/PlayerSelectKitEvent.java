package se.sweplox.kitpvp.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import se.sweplox.kitpvp.kit.Kit;

public class PlayerSelectKitEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();

    private Player player;
    private Kit kit;

    public PlayerSelectKitEvent(Player player, Kit kit) {
        this.player = player;
        this.kit = kit;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public HandlerList getHandlers() {
        return handlerList;
    }

    public Player getPlayer() {
        return player;
    }

    public Kit getKit() { return kit; }
}
