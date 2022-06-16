package fr.deadplays.fweapons.misc;

import fr.deadplays.fweapons.Utils;
import fr.deadplays.fweapons.main;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class recoilEngine implements Listener {
    private main plugin;
    public recoilEngine(final main plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    public static void recoilPlayer(Player player, String recoilType, int recoilAmmount){
        PersistentDataContainer playerRecoilCounter = player.getPersistentDataContainer();
        NamespacedKey recoilKey = new NamespacedKey(main.getPlugin(), recoilType);
        if(playerRecoilCounter.has(recoilKey, PersistentDataType.INTEGER)){
            int recoilCounter = playerRecoilCounter.get(recoilKey, PersistentDataType.INTEGER) + 1;
            playerRecoilCounter.set(recoilKey, PersistentDataType.INTEGER, recoilCounter);
            Utils.debugPlayer(player, "recoilCounter: " + recoilCounter);
        }
    }
}
