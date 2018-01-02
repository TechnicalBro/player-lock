package com.devsteady.playerlock.listeners;

import com.caved_in.commons.chat.Chat;
import com.caved_in.commons.player.Players;
import com.devsteady.playerlock.PlayerLock;
import com.devsteady.playerlock.Settings;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MovementListener implements Listener {
    private Settings settings;

    public MovementListener(PlayerLock plugin) {
        this.settings = plugin.getSettings();
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        /* If the player is locked teleport them to themselves when locked. */
        if (settings.isLocked(e.getPlayer())) {

            Players.teleport(e.getPlayer(),e.getPlayer().getLocation());

            /* If there's a message to send them then we'll do that too */
            if (settings.isLockMessageEnabled()) {
                Chat.message(e.getPlayer(),settings.getLockMessage());
            }
        }
    }
}
