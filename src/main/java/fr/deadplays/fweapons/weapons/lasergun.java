package fr.deadplays.fweapons.weapons;

import fr.deadplays.fweapons.Utils;
import fr.deadplays.fweapons.main;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.Objects;

public class lasergun implements Listener {
    private main plugin;
    public lasergun(final main plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getPlayer().getItemInHand().getType() == Material.getMaterial(Objects.requireNonNull(this.plugin.getConfig().getString("Laser-item")))) {
                Player player = event.getPlayer();
                Vector directionToFire;
                World world = player.getWorld();
                player.getWorld().playSound(player.getLocation(), this.plugin.getConfig().getString("Laser-sound"), 0.5f, 1f);
                Boolean hasTarget = false;
                directionToFire = player.getEyeLocation().getDirection();
                while(!hasTarget){
                    RayTraceResult result = world.rayTraceEntities(player.getEyeLocation(), directionToFire, plugin.getConfig().getDouble("Laser-bullet-range"), 0.01f, entity -> entity != player);
                    if(result.getHitEntity() == null){
                        RayTraceResult blockResult = world.rayTraceBlocks(player.getEyeLocation(), directionToFire, plugin.getConfig().getDouble("Laser-bullet-range"), FluidCollisionMode.NEVER, false);
                        if(blockResult.getHitBlock() != null){
                            // bounce the laser
                            directionToFire = directionToFire.clone().multiply(-1);
                        } else {
                            hasTarget = true;
                            Utils.drawLine(Particle.ASH, player.getEyeLocation(), result.getHitEntity().getLocation(), 1, Color.RED);
                        }
                    } else {
                        Utils.drawLine(Particle.ASH, player.getEyeLocation(), result.getHitEntity().getLocation(), 1, Color.RED);
                        hasTarget = true;
                        player.sendMessage(result.getHitEntity().getName());
                    }
                }
            }
        }
    }
}
