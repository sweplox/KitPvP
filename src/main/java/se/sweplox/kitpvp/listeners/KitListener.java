package se.sweplox.kitpvp.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import se.sweplox.kitpvp.events.PlayerSelectKitEvent;
import se.sweplox.kitpvp.kit.Kit;
import se.sweplox.kitpvp.kit.kits.ArcherKit;

public class KitListener implements Listener {

    @EventHandler
    public void onPlayerSelectKitEvent(PlayerSelectKitEvent event) {
        Player player = event.getPlayer();
        Kit kit = event.getKit();

        if(event.getKit() instanceof ArcherKit) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000000, 1));
        }


    }
}
