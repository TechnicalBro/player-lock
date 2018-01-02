package com.devsteady.playerlock.listeners;

import com.caved_in.commons.chat.Chat;
import com.caved_in.commons.utilities.StringUtil;
import com.devsteady.playerlock.PlayerLock;
import com.devsteady.playerlock.Settings;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PveListener implements Listener {

    private Settings settings;

    public PveListener(PlayerLock plugin) {
        this.settings = plugin.getSettings();
    }

    /**
     * Handle lock check for the player. If the settings permits then send them a message
     * and and return a boolean defining whether or not to cancel the event.
     * @param player player to check locks on.
     * @return boolean stating whether or not to cancel the interactions.
     */
    private boolean handleLockCheck(Player player) {
        if (!settings.isLocked(player)) {
            return false;
        }

        if (settings.isLockMessageEnabled()) {
            Chat.message(player,settings.getLockMessage());
        }

        return true;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player  = event.getPlayer();
        event.setCancelled(handleLockCheck(player));
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        event.setCancelled(handleLockCheck(player));
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Player)) {
            event.setCancelled(true);
            return;
        }

        Player player = (Player)entity;
        event.setCancelled(handleLockCheck(player));
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerInteract(PlayerInteractEvent event) {
        event.setCancelled(handleLockCheck(event.getPlayer()));
    }
}
