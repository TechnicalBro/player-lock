package com.devsteady.playerlock.listeners;

import com.caved_in.commons.chat.Chat;
import com.caved_in.commons.utilities.StringUtil;
import com.devsteady.playerlock.PlayerLock;
import com.devsteady.playerlock.Settings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {

    private Settings settings;

    public CommandListener(PlayerLock plugin) {
        this.settings = plugin.getSettings();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        if (settings.isLocked(player)) {

            if (!StringUtil.startsWithIgnoreCase(event.getMessage(),"/unlock") && !player.hasPermission("playerlock.unlock")) {
                if (settings.isLockMessageEnabled()) {
                    Chat.message(player, settings.getLockMessage());
                }

                event.setCancelled(true);
            }
        }
    }
}
