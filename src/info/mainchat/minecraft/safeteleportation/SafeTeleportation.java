/*
 * ©2013 Omar Stefan Evans <omar@evansbros.info>
 *
 * Permission is hereby granted to all persons to use and redistribute this software
 * for any purpose, with or without modification, provided that this notice appears
 * in all copies or substantial portions of the software.
 */

package info.mainchat.minecraft.safeteleportation;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class SafeTeleportation extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        //Don't need to actually do anything.
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onTeleport(PlayerTeleportEvent event) {
        Location destination = event.getTo();
        Player player = event.getPlayer();

        if (destination.getY() < 1.0) {
            event.setCancelled(true);
            player.sendMessage("A mysterious force attempted to send you into the void. Fortunately for you, it failed.");
        } else if (destination.getY() > 255.0 && player.getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
            player.sendMessage("A mysterious force attempted to fling you into the sky. Fortunately for you, mere mortal, it failed.");
        }
    }
}
