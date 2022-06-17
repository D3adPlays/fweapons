package fr.deadplays.fweapons.weapons;

import fr.deadplays.fweapons.main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class superfish implements Listener {
    private main plugin;
    public superfish(final main plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }

    @EventHandler
    public void OnInteraction(PlayerInteractEvent event){

        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = player.getItemInHand();

        if (action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) {
            if (item.getType() == Material.STONE_HOE) {
                // get direction of player
                double direction = player.getLocation().getYaw();
                // get location of player
                double x = player.getLocation().getX();
                double y = player.getLocation().getY();
                double z = player.getLocation().getZ();
                // get location of player + 1 block
                double x1 = x + 1;
                double y1 = y;
                double z1 = z;
                // spawn fish on player's location



            }
        }

    }



}
