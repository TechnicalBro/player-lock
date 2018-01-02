package com.devsteady.playerlock;

import com.caved_in.commons.yml.Comment;
import com.caved_in.commons.yml.Comments;
import com.caved_in.commons.yml.Path;
import com.caved_in.commons.yml.YamlConfig;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Settings extends YamlConfig {
    @Path("stops.movement")
    @Comments({"Prevent the player from moving from their","current location while locked"})
    private boolean movementBlocked = true;

    @Path("stops.commands")
    @Comments({"While the player is locked prevent","them from performing any commands"})
    private boolean commandsBlocked = true;

    @Path("stops.pvp")
    @Comments({"While a player is locked it prevents them from","dealing and receiving any damage"})
    private boolean pvpBlocked = true;

    @Path("messages.lock.enabled")
    @Comments({"Whether or not to display a message","to players performing actions while locked."})
    private boolean lockMessageEnabled;

    @Path("messages.lock.text")
    @Comments({"The message to display to the user when","they're performing actions while locked"})
    private String lockMessage = "&cYou've been locked- You cannot perform any actions.";

    @Path("players-locked")
    @Comment("Data for the players who are currently locked")
    private Map<UUID, PlayerLockData> playerLockData = new HashMap<>();

    public Settings() {

    }

    public Settings(File file) {
        super(file);
    }

    public Settings movementBlocked(boolean blocked) {
        this.movementBlocked = blocked;
        return this;
    }

    public Settings commandsBlocked(boolean blocked) {
        this.commandsBlocked = blocked;
        return this;
    }

    public Settings pvpBlocked(boolean blocked) {
        this.pvpBlocked = blocked;
        return this;
    }

    public Settings displayLockedMessage(boolean lockMessageEnabled) {
        this.lockMessageEnabled = lockMessageEnabled;
        return this;
    }

    public Settings lockMessage(String text) {
        this.lockMessage = text;
        return this;
    }

    public boolean isMovementBlocked() {
        return movementBlocked;
    }

    public boolean isCommandsBlocked() {
        return commandsBlocked;
    }

    public boolean isPvpBlocked() {
        return pvpBlocked;
    }

    public boolean isLockMessageEnabled() {
        return lockMessageEnabled;
    }

    public String getLockMessage() {
        return lockMessage;
    }

    public boolean isLocked(Player player) {
        return playerLockData.containsKey(player.getUniqueId());
    }

    public void lock(Player player, Player locking) {
        playerLockData.put(player.getUniqueId(), new PlayerLockData(player,locking));
    }

    public void unlock(Player player) {
        if (!playerLockData.containsKey(player.getUniqueId())) {
            return;
        }

        playerLockData.remove(player.getUniqueId());
    }
}
