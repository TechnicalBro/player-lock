package com.devsteady.playerlock.events;

import com.devsteady.playerlock.PlayerLock;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerLockEvent extends PlayerEvent implements Cancellable{
    private static HandlerList handlers = new HandlerList();

    private boolean cancelled = false;

    private Player issuer;


    public PlayerLockEvent(Player who, Player issuer) {
        super(who);
        this.issuer = issuer;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }

    public Player getIssuer() {
        return issuer;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public static void handle(PlayerLockEvent event) {
        PlayerLock.getInstance().getSettings().lock(event.getPlayer(),event.getIssuer());
    }
}
