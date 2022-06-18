package fr.deadplays.fweapons.weapons;

import fr.deadplays.fweapons.main;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class poseidon implements Listener {

    private main plugin;

    public poseidon(final main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onProjectileLand(ProjectileHitEvent event) {


        ItemStack katanaitem = new ItemStack(Material.STONE_SWORD, 1);
        ItemMeta katanaitemm = katanaitem.getItemMeta();

        katanaitemm
                .setDisplayName("§6Katana");
        katanaitemm
                .isUnbreakable();
        katanaitemm
                .addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        katanaitemm
                .addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        katanaitemm
                .addEnchant(Enchantment.DAMAGE_ARTHROPODS, 500, true);
        katanaitemm
                .addEnchant(Enchantment.DAMAGE_UNDEAD, 500, true);
        katanaitemm
                .addEnchant(Enchantment.FIRE_ASPECT, 500, true);
        katanaitemm
                .addItemFlags(ItemFlag.HIDE_ENCHANTS);
        katanaitemm
                .addEnchant(Enchantment.BINDING_CURSE, 1, true);
        katanaitem
                .setItemMeta(katanaitemm);


        Player player = (Player) event.getEntity().getShooter();
        if (event.getEntity() instanceof Trident) {
            if (event.getEntity().getShooter() instanceof Player) {

                World world = player.getWorld();

                world.setTime(0);
                //world.createExplosion(event.getHitBlock().getLocation(), 4.0F, false, true);
                world.setThundering(true);
                world.setStorm(true);


                event.getHitBlock().getLocation().getWorld().strikeLightning(event.getHitBlock().getLocation());

                Bukkit.getScheduler().runTaskLater(this.plugin, new Runnable() {
                    @Override
                    public void run() {
                        world.setThundering(true);
                        event.getHitBlock().getLocation().getWorld().strikeLightning(event.getHitBlock().getLocation());
                        world.setThundering(false);
                    }
                }, 150L);
                Bukkit.getScheduler().runTaskLater(this.plugin, new Runnable() {
                    @Override
                    public void run() {
                        world.setThundering(false);
                        world.setStorm(false);
                        world.setTime(6000);
                        player.getInventory().addItem(katanaitem);
                        player.sendMessage("§6Apparition de la vague dans 5s ! Prenez le Katana afin de le tuer !");

                    }
                }, 100L);


            }
        }
    }

    @EventHandler
    //Create custom chest
    public void onInteract(PlayerInteractEvent event) throws NullPointerException {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack it = event.getItem();
        Inventory inv = Bukkit.createInventory(null, 9, "Poseidon");
        if( action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            try{if(it.getType() != null && it.getType() == Material.TRIDENT) {
                player.spawnParticle(Particle.DRAGON_BREATH, event.getPlayer().getLocation(), 100);
                player.spawnParticle(Particle.CLOUD, event.getPlayer().getLocation(), 100);
                player.playSound(event.getPlayer().getLocation(), Sound.ENTITY_WITHER_DEATH, 100, 10);
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1/4, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5, 3));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 5, 2));
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 2, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 5, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 3, 1));
                player.spawnParticle(Particle.MOB_APPEARANCE, player.getLocation(), 100);

            }}catch(NullPointerException ignored){}
        }

    }
}




