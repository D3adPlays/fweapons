package fr.deadplays.fweapons.weapons;

import fr.deadplays.fweapons.main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.Random;

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
                EntityType type;
                switch (new Random().nextInt(5)) {
                    case 2:
                        type = EntityType.SALMON;
                        break;
                    case 3:
                        type = EntityType.COD;
                        break;
                    case 4:
                        type = EntityType.PUFFERFISH;
                        break;
                    default:
                        type = EntityType.TROPICAL_FISH;
                        break;
                }
                Entity fish = player.getWorld().spawnEntity(player.getEyeLocation(), type);
                fish.setVelocity(event.getPlayer().getEyeLocation().getDirection().multiply(2));
                fish.setMetadata("fish", new FixedMetadataValue(plugin, 1));
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_FISHING_BOBBER_RETRIEVE, 1, 1);
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 10);
                Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                    @Override
                    public void run() {
                        fish.getLocation().getWorld().spawnParticle(Particle.CLOUD, fish.getLocation(), 10);
                        fish.remove();
                    }
                }, 20 * 6);
            }
        }

    }
    @EventHandler
    public void fishLand(EntityDamageEvent event) {
        if (event.getEntity().hasMetadata("fish")) {
            event.setCancelled(true);
            event.getEntity().getLocation().getWorld().playSound(event.getEntity().getLocation(), Sound.ENTITY_TNT_PRIMED, 1, 1);
            event.getEntity().getLocation().getWorld().spawnParticle(Particle.CLOUD, event.getEntity().getLocation(), 10);
            event.getEntity().remove();

            //bukkit run later
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    event.getEntity().getWorld().createExplosion(event.getEntity().getLocation(), 10);
                }
            }, 20 * 6);
        }
    }



}
