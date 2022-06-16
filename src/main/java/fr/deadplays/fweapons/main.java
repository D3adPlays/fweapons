package fr.deadplays.fweapons;

import fr.deadplays.fweapons.commands.giveCommand;
import fr.deadplays.fweapons.misc.recoilEngine;
import fr.deadplays.fweapons.weapons.hppotion;
import fr.deadplays.fweapons.weapons.mitraillette;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class main extends JavaPlugin {


    FileConfiguration config;
    public main() {
        this.config = this.getConfig();
    }

    public static main getPlugin() {
        return pluginGetter;
    }

    private static main pluginGetter;


    @Override
    public void onEnable() {
        pluginGetter = this;
        this.getLogger().log(Level.INFO, Utils.chat("&aMade with &c&l<3&a by D3adPlays and Méliodas for &eFuzeIII."));
        this.getLogger().log(Level.INFO, Utils.chat("&aLoading Config"));
        this.createConfig();
        this.getLogger().log(Level.INFO, Utils.chat("&aLoading Commands"));
        new hppotion(this);
        new giveCommand(this);
        mitraillette.mitrailletteCd.clear();
        new mitraillette(this);
        new recoilEngine(this);
    }

    public void createConfig(){
        this.getLogger().log(Level.INFO, Utils.chat("&aConfig not found, creating one"));
        //.replace("&", "§");
        //add debug mode
        this.config.addDefault("fweapons-debug-mode", true);
        //set hit sound
        this.config.addDefault("fweapons-hit-sound", "minecraft:meliodas.hitmarker");
        this.config.addDefault("fweapons-headshot-sound", "minecraft:meliodas.headshot");
        this.config.addDefault("fweapons-bullet-flyby", "minecraft:meliodas.bulletfliby");
        this.config.addDefault("fweapons-headshot-multiplier", 1.25);
        this.config.addDefault("fweapons-resource-pack-url", "https://cdn.discordapp.com/attachments/985278698485858375/986283282381557770/eMeliodouilWeapon.zip");
        this.config.addDefault("fweapons-resource-pack-error", "&cVous devez installer le pack de ressource pour jouer.");
        this.config.addDefault("fweapons-wallbang-chance-rate", 1);

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

        this.config.addDefault("Laser-item", "STICK");
        this.config.addDefault("Laser-reload", "minecraft:meliodas.laserreload");
        this.config.addDefault("Laser-sound", "minecraft:meliodas.laserfire");
        this.config.addDefault("Laser-block-hit", "minecraft:meliodas.laseroff");
        this.config.addDefault("Laser-reload-sound", "minecraft:meliodas:custom");
        this.config.addDefault("Laser-display-name", Utils.chat("&cLaser [{current-ammo}/{max-ammo}]"));
        this.config.addDefault("Laser-lore", "0");
        this.config.addDefault("Laser-max-amo", 20);
        this.config.addDefault("Laser-min-amo", 0);
        this.config.addDefault("Laser-reload-cooldown", 3);
        this.config.addDefault("Laser-bullet-range", 50f);
        this.config.addDefault("Laser-damage", 3);
        this.config.addDefault("Laser-recoil", true);

        this.config.addDefault("Invocation-item", "FEATHER");
        this.config.addDefault("Invocation-reload", "minecraft:meliodas.invocationreload");
        this.config.addDefault("Invocation-sound", "minecraft:meliodas.invocationfire");
        this.config.addDefault("Invocation-block-hit", "minecraft:meliodas.invocationoff");
        this.config.addDefault("Invocation-reload-sound", "minecraft:meliodas:custom");
        this.config.addDefault("Invocation-display-name", Utils.chat("&cInvocation [{current-ammo}/{max-ammo}]"));
        this.config.addDefault("Invocation-lore", "0");
        this.config.addDefault("Invocation-max-amo", 20);
        this.config.addDefault("Invocation-min-amo", 0);
        this.config.addDefault("Invocation-reload-cooldown", 3);
        this.config.addDefault("Invocation-bullet-range", 50f);
        this.config.addDefault("Invocation-damage", 0);
        this.config.addDefault("Invocation-recoil", true);

        this.config.addDefault("Poseidon-item", "TRIDENT");
        this.config.addDefault("Poseidon-reload", "minecraft:meliodas.poseidonreload");
        this.config.addDefault("Poseidon-sound", "minecraft:meliodas.poseidonfire");
        this.config.addDefault("Poseidon-block-hit", "minecraft:meliodas.poseidonoff");
        this.config.addDefault("Poseidon-reload-sound", "minecraft:meliodas:custom");
        this.config.addDefault("Poseidon-display-name", Utils.chat("&cPoseidon [{current-ammo}/{max-ammo}]"));
        this.config.addDefault("Poseidon-lore", "0");
        this.config.addDefault("Poseidon-max-amo", 20);
        this.config.addDefault("Poseidon-min-amo", 0);
        this.config.addDefault("Poseidon-reload-cooldown", 3);
        this.config.addDefault("Poseidon-bullet-range", 50f);
        this.config.addDefault("Poseidon-damage", 3);
        this.config.addDefault("Poseidon-recoil", true);


        // add error reponse for /weapon commands
        this.config.addDefault("fweapons-error-no-permission", "&cVous n'avez pas la permission d'utiliser cette commande.");
        this.config.addDefault("give-mitraillette", "Arme : Mitraillette Give");
        this.config.addDefault("give-hppotion", "Arme : HP Potion Give");
        // add error reponse for /weapon commands (give-weapon-fail)
        this.config.addDefault("give-weapon-fail", "&cVous n'avez pas assez de place dans votre inventaire et/ou avez spécifier un carractère invalide.");
        this.config.addDefault("give-weapon-success", "&aVous avez reçu l'arme : {weapon} de la part de {sender}.");
        this.config.addDefault("give-weapon-list", "&eVoici la liste des armes : \n {weapons}");
        this.config.addDefault("give-weapon-success-sender", "&e Vous avez envoyer {weapon} à {player}.");
        // replace & with § on all messages
        //add lasergun configuration
        this.config.addDefault("Lasergun-item", "DIAMOND_HOE");
        this.config.addDefault("Lasergun-reload", "minecraft:meliodas.akreload");
        this.config.addDefault("Lasergun-sound", "minecraft:meliodas.akfire");
        this.config.addDefault("Lasergun-block-hit", "minecraft:meliodas.akloose");
        this.config.addDefault("Lasergun-reload-sound", "minecraft:meliodas:custom");
        this.config.addDefault("Lasergun-display-name", Utils.chat("&cLasergun [{current-ammo}/{max-ammo}]"));


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
