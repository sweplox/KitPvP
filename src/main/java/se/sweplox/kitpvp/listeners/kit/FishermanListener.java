package se.sweplox.kitpvp.listeners.kit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import se.sweplox.kitpvp.KitPvP;
import se.sweplox.kitpvp.kit.Kit;
import se.sweplox.kitpvp.kit.kits.FishermanKit;
import se.sweplox.kitpvp.player.PVPPlayer;

public class FishermanListener implements Listener {

    private KitPvP instance;

    public FishermanListener(KitPvP instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        Player player = event.getPlayer();
        PVPPlayer pvpPlayer = instance.getPlayerManager().getPlayerByUUID(player.getUniqueId());
        Kit kit = pvpPlayer.getKit();

        if(pvpPlayer == null) return;
        if(kit == null) return;
        if(!(kit instanceof FishermanKit)) return;
        if(!(event.getCaught() instanceof Player)) return;
        if(player.getInventory().getItemInMainHand().getType() != Material.FISHING_ROD) return;

        Player target = (Player) event.getCaught();
        target.teleport(player.getLocation());
    }
}
