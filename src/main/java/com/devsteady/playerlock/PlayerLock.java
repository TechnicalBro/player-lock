package com.devsteady.playerlock;

import com.caved_in.commons.plugin.BukkitPlugin;
import com.caved_in.commons.yml.InvalidConfigurationException;
import com.devsteady.playerlock.listeners.CommandListener;
import com.devsteady.playerlock.listeners.MovementListener;
import com.devsteady.playerlock.listeners.PveListener;
import com.devsteady.playerlock.listeners.PvpListener;
import org.bukkit.entity.Player;

import java.io.File;

public class PlayerLock extends BukkitPlugin{
    private static PlayerLock instance;

    public static PlayerLock getInstance() {
        return instance;
    }

    private Settings configuration;

    @Override
    public void startup() {
        instance = this;

        registerCommands(new Commands());

        if (getSettings().isMovementBlocked()) {
            registerListeners(new MovementListener(this));
        }

        if (getSettings().isPvpBlocked()) {
            registerListeners(new PvpListener(this));
        }

        if (getSettings().isCommandsBlocked()) {
            registerListeners(new CommandListener(this));
        }

        registerListeners(new PveListener(this));
    }

    @Override
    public void shutdown() {

        File configFile = new File(getDataFolder(), "config.yml");
        getLogger().info("Saving config.yml ....");
        try {
            configuration.save(configFile);
            getLogger().info("... Complete!");
        } catch (InvalidConfigurationException e) {
            getLogger().severe("... Error saving config.yml");
        }

    }

    @Override
    public String getAuthor() {
        return "Brandon Curtis";
    }

    @Override
    public void initConfig() {

        File configFile = new File(getDataFolder(),"config.yml");

        configuration = new Settings();

        if (!configFile.exists()) {
            try {
                configuration.init(configFile);
            } catch (InvalidConfigurationException e) {
                getLogger().severe("Unable to initialize configuration file.");
            }
        } else {
            try {
                configuration.load(configFile);
            } catch (InvalidConfigurationException e) {
                getLogger().severe("Unable to load the configuration file.");
            }
        }
    }

    public Settings getSettings() {
        return configuration;
    }

    public void setSettings(Settings settings) {
        this.configuration = settings;
    }

    public static class API {
        public static boolean isLocked(Player player) {
            return PlayerLock.getInstance().getSettings().isLocked(player);
        }
    }
}
