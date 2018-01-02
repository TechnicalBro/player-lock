package com.devsteady.playerlock.listeners;

import com.caved_in.commons.chat.Chat;
import com.caved_in.commons.event.PlayerDamagePlayerEvent;
import com.devsteady.playerlock.PlayerLock;
import com.devsteady.playerlock.Settings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PvpListener implements Listener {
    private Settings settings;

    public PvpListener(PlayerLock plugin) {
        this.settings = plugin.getSettings();
    }


    @EventHandler
    public void onPlayerPvp(PlayerDamagePlayerEvent event) {
        Player attacker = event.getPlayer();
        Player target = event.getPlayer();

        if (settings.isLocked(attacker)) {
            event.setCancelled(true);

            if (settings.isLockMessageEnabled()) {
                Chat.message(attacker, settings.getLockMessage());
            }

            return;
        }

        if (settings.isLocked(target)) {
            event.setCancelled(true);

            if (settings.isLockMessageEnabled()) {
                Chat.message(target,settings.getLockMessage());
            }
        }
    }
}
