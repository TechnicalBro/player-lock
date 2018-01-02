package com.devsteady.playerlock.events;

import com.devsteady.playerlock.PlayerLock;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerUnlockEvent extends PlayerEvent implements Cancellable {
    private static HandlerList handlers = new HandlerList();

    private boolean cancelled = false;

    private Player issuer;

    public PlayerUnlockEvent(Player who, Player issuer) {
        super(who);
        this.issuer = issuer;
    }

    public Player getIssuer() {
        return issuer;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }


    public static void handle(PlayerUnlockEvent event) {
        PlayerLock.getInstance().getSettings().unlock(event.getPlayer());
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
    }
}
