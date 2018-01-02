package com.devsteady.playerlock;

import com.caved_in.commons.command.Arg;
import com.caved_in.commons.command.Command;
import com.caved_in.commons.plugin.Plugins;
import com.devsteady.playerlock.events.PlayerLockEvent;
import com.devsteady.playerlock.events.PlayerUnlockEvent;
import org.bukkit.entity.Player;

public class Commands {
    @Command(identifier = "lock",permissions = "playerlock.lock")
    public void onPlayerLockCommand(Player player, @Arg(name="target")Player target) {
        PlayerLockEvent event = new PlayerLockEvent(target,player);
        Plugins.callEvent(event);
        PlayerLockEvent.handle(event);
    }

    @Command(identifier = "unlock",permissions = "playerlock.unlock")
    public void onPlayerUnlockCommand(Player player, @Arg(name = "target")Player target) {
        //todo check if player is locked

        PlayerUnlockEvent event = new PlayerUnlockEvent(target,player);
        Plugins.callEvent(event);
        PlayerUnlockEvent.handle(event);
    }
}
