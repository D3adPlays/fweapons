package fr.deadplays.fweapons.weapons;

import fr.deadplays.fweapons.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class laser implements Listener {

    private main plugin;

    public laser(final main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static ArrayList<Integer> arrowList = new ArrayList<Integer>();


    @EventHandler
    public void playerInterractEvent(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getItem() != null && event.getPlayer() instanceof Player) {
            ItemStack item = event.getItem();
            Player player = event.getPlayer();
            if (item.getItemMeta().hasCustomModelData() && item.getItemMeta().getCustomModelData() == 12341 && item.getType() == Material.getMaterial(plugin.getConfig().getString("Laser-item"))) {
                Vector playerDirection = player.getLocation().getDirection();
                player.getLocation().getWorld().playSound(player.getLocation(), Sound.UI_LOOM_TAKE_RESULT, 1, 1);
                Arrow bullet = player.launchProjectile(Arrow.class, playerDirection);
                bullet.setMetadata("bouncing", new FixedMetadataValue(plugin, 1));
                bullet.setVelocity(bullet.getVelocity().multiply(10));
                bullet.setSilent(true);
                arrowList.add((Integer) bullet.getEntityId());
            }
        }
    }

    @EventHandler
    public void projectileHitEvent(ProjectileHitEvent event) {
        if (event.getEntity() != null && event.getEntity() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getEntity();
            Vector arrowVector = arrow.getVelocity();
            Location hitLoc = arrow.getLocation();
            BlockIterator b = new BlockIterator(hitLoc.getWorld(), hitLoc.toVector(), arrowVector, 0, 3);

            Block hitBlock = event.getEntity().getLocation().getBlock();
            Block blockBefore = hitBlock;
            Block nextBlock = b.next();
            while (b.hasNext() && nextBlock.getType() == Material.AIR) {
                blockBefore = nextBlock;
                nextBlock = b.next();
            }

            BlockFace blockFace = nextBlock.getFace(blockBefore);
            if (blockFace != null) {

                if (blockFace == BlockFace.UP || blockFace == BlockFace.SELF) {
                    arrowList.remove((Object) arrow.getEntityId());
                    arrow.remove();
                    return;
                }
                if (blockFace == BlockFace.SELF) {
                    blockFace = BlockFace.UP;
                }
                Vector hitPlain = new Vector(blockFace.getModX(), blockFace.getModY(), blockFace.getModZ());
                double dotProduct = arrowVector.dot(hitPlain);
                Vector u = hitPlain.multiply(dotProduct).multiply(2.0);
                float speed = (float) Math.sqrt(Math.pow(arrowVector.getX(), 2) + Math.pow(arrowVector.getY(), 2) + Math.pow(arrowVector.getZ(), 2));
                Arrow newArrow = arrow.getWorld().spawnArrow(arrow.getLocation(), arrowVector.subtract(u), speed, 12.0F);
                newArrow.setMetadata("bouncing", new FixedMetadataValue(plugin, 1));
                arrowList.add((Integer) arrow.getEntityId());
                arrowList.remove((Object) arrow.getEntityId());
                arrow.remove();

            }
        }
    }
}





