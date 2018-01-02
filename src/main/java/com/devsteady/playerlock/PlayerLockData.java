package com.devsteady.playerlock;

import com.caved_in.commons.yml.Path;
import com.caved_in.commons.yml.YamlConfig;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

public class PlayerLockData extends YamlConfig {

    @Path("name")
    private String name;

    @Path("id")
    private String id;

    @Path("issuer-name")
    private String lockedByName;

    @Path("issuer-id")
    private String lockedById;

    @Path("locked-timestamp")
    private long lockedTime;

    public PlayerLockData(File file) {
        super(file);
    }

    public PlayerLockData() {}

    public PlayerLockData(Player locked, Player locking) {
        this.name = locked.getName();
        this.id = locked.getUniqueId().toString();
        this.lockedByName = locking.getName();
        this.lockedById = locking.getUniqueId().toString();
        this.lockedTime = System.currentTimeMillis();
    }

    public UUID getLockedId() {
        return UUID.fromString(id);
    }

    public UUID getIssuerId() {
        return UUID.fromString(lockedById);
    }

    public long getLockedTimestamp() {
        return lockedTime;
    }
}
