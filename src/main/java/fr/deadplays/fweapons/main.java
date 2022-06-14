package fr.deadplays.fweapons;

import fr.deadplays.fweapons.commands.giveCommand;
import fr.deadplays.fweapons.weapons.hppotion;
import fr.deadplays.fweapons.weapons.mitraillette;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public final class main extends JavaPlugin {


    FileConfiguration config;
    public main() {
        this.config = this.getConfig();
    }
    @Override
    public void onEnable() {
        this.getLogger().log(Level.INFO, Utils.chat("&aMade with &c&l<3&a by D3adPlays and Méliodas for &eFuzeIII."));
        this.getLogger().log(Level.INFO, Utils.chat("&aLoading Config"));
        this.createConfig();
        this.getLogger().log(Level.INFO, Utils.chat("&aLoading Commands"));
        new hppotion(this);
        new giveCommand(this);
        new mitraillette(this);
        Filter f = new Filter(){
            public boolean isLoggable(LogRecord line){
                if(line.getMessage().contains("Played sound minecraft:meliodas.")){
                    return false;
                }
                return true;
            }
        };
        PluginLogger.getGlobal().setFilter(f);
    }

    public void createConfig(){
        this.getLogger().log(Level.INFO, Utils.chat("&aConfig not found, creating one"));
        //set hit sound
        this.config.addDefault("fweapons-hit-sound", "minecraft:meliodas.hitmarker");
        this.config.addDefault("fweapons-headshot-sound", "minecraft:meliodas.headshot");
        this.config.addDefault("fweapons-bullet-flyby", "minecraft:meliodas.bulletfliby");
        this.config.addDefault("fweapons-headshot-multiplier", 1.25);
        this.config.addDefault("fweapons-resource-pack-url", "https://cdn.discordapp.com/attachments/985278698485858375/986283282381557770/eMeliodouilWeapon.zip");
        this.config.addDefault("fweapons-resource-pack-error", "&cVous devez installer le pack de ressource pour jouer.");

        this.config.addDefault("Mitraillette-item", "DIAMOND_HOE");
        this.config.addDefault("Mitraillette-reload", "minecraft:meliodas.akreload");
        this.config.addDefault("Mitraillette-sound", "minecraft:meliodas.akfire");
        this.config.addDefault("Mitraillette-block-hit", "minecraft:meliodas.akloose");
        this.config.addDefault("Mitraillette-reload-sound", "minecraft:meliodas:custom");
        this.config.addDefault("Mitraillette-display-name", Utils.chat("&cMitraillette [{current-ammo}/{max-ammo}]"));
        this.config.addDefault("Mitraillette-lore", "0");
        this.config.addDefault("Mitraillette-max-amo", 20);
        this.config.addDefault("Mitraillette-min-amo", 0);
        this.config.addDefault("Mitraillette-reload-cooldown", 3);
        this.config.addDefault("Mitraillette-bullet-range", 50f);
        this.config.addDefault("Mitraillette-damage", 3);
        this.config.addDefault("Mitraillette-recoil", true);
        this.config.options().copyDefaults(true);
        this.saveConfig();
        this.reloadConfig();
        this.getLogger().log(Level.INFO, Utils.chat("&aConfig loaded"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
