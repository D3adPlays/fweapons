package fr.deadplays.fweapons.weapons;

import fr.deadplays.fweapons.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class extrawolf implements Listener {
    private main plugin;

    public extrawolf(final main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents((Listener) this, (Plugin) plugin);
    }

    @EventHandler
    public void onInteraction(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            Player p = event.getPlayer();
            Player player = event.getPlayer();
           ItemStack item = player.getItemInHand();

            if (item.getType() == Material.BONE) {
                player.playSound(player.getLocation(), Sound.ENTITY_WOLF_HOWL, 4.0F, 1.0F);
                for(int i=0; i<5; i++){
                    Wolf wolf = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);

                    wolf.setAdult();
                    wolf.setTamed(true);
                    wolf.setOwner(p);
                    wolf.setBreed(false);
                    wolf.setCustomName(ChatColor.YELLOW + p.getName() + "Loup");
                    wolf.setCustomNameVisible(true);
                    wolf.setHealth(wolf.getMaxHealth());
                    wolf.setCanPickupItems(false);
                }



                player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
            }
        }
    }
}


