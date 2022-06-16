package fr.deadplays.fweapons.weapons;

import fr.deadplays.fweapons.Utils;
import fr.deadplays.fweapons.main;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class mitraillette implements Listener {
    private main plugin;

    public mitraillette(final main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static ArrayList<Player> mitrailletteCd = new ArrayList<Player>();

    //on player join
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (mitrailletteCd.contains(player)) {
            mitrailletteCd.remove(player);
        }
    }

    @EventHandler
    //On right click
    public void onRightClick(PlayerInteractEvent event) throws NullPointerException {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getPlayer().getItemInHand().getType() == Material.getMaterial(Objects.requireNonNull(this.plugin.getConfig().getString("Mitraillette-item")))) {
                Player player = event.getPlayer();
                event.setCancelled(true);
                World world = player.getWorld();
                Vector directionToFire = player.getEyeLocation().getDirection();
                try{String parsed = event.getItem().getLore().get(0);} catch (NullPointerException ignored){} // pcq spigot c code avec le cul
                String parsed = event.getItem().getLore().get(0);
                Integer amoLeft = Integer.parseInt(parsed);
                Integer maxAmo = plugin.getConfig().getInt("Mitraillette-max-amo");
                if (!(amoLeft <= 0)) {
                    mitrailletteCd.remove(player);
                    ItemMeta im = event.getItem().getItemMeta();
                    im.setLore(Arrays.asList(Integer.toString(Integer.parseInt(im.getLore().get(0)) - 1)));
                    im.setDisplayName(this.plugin.getConfig().getString("Mitraillette-display-name").replace("{current-ammo}", Integer.toString(Integer.parseInt(im.getLore().get(0)))).replace("{max-ammo}", Integer.toString(maxAmo)));
                    event.getItem().setItemMeta(im);
                    world.playSound(player.getLocation(), this.plugin.getConfig().getString("Mitraillette-sound"), 0.5f, 1f);
                    //recoil player
                    RayTraceResult result = world.rayTrace(player.getEyeLocation(), directionToFire, plugin.getConfig().getDouble("Mitraillette-bullet-range"), FluidCollisionMode.ALWAYS, true, 0.01f, entity -> entity != player && !entity.isDead());
                    if (result.getHitEntity() != null) {
                        LivingEntity injured = (LivingEntity) result.getHitEntity();
                        Utils.debugPlayer(player, "Debug 3");
                        Utils.drawLine(Particle.REDSTONE, player.getEyeLocation(), result.getHitPosition().toLocation(world), 5f, Color.WHITE);
                        if (result.getHitPosition().getY() >= injured.getEyeLocation().getY() - 0.2) {
                            Utils.debugPlayer(player, "Headshot " + injured.getName());
                            player.playSound(player.getLocation(), this.plugin.getConfig().getString("fweapons-headshot-sound"), 1.0f, 1.0f);

                            injured.setNoDamageTicks(0);
                            injured.damage(this.plugin.getConfig().getDouble("Mitraillette-damage") * this.plugin.getConfig().getDouble("fweapons-headshot-multiplier"));
                            injured.setNoDamageTicks(0);
                            injured.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.08);
                            Bukkit.getScheduler().runTaskLater(this.plugin, new Runnable() {
                                @Override
                                public void run() {
                                    injured.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1);
                                }
                            }, 5L);
                            return;
                        }
                        Utils.debugPlayer(player, "Hit " + injured.getName());
                        player.playSound(player.getLocation(), this.plugin.getConfig().getString("fweapons-hit-sound"), 1.0f, 1.0f);
                        injured.setNoDamageTicks(0);
                        injured.damage(this.plugin.getConfig().getDouble("Mitraillette-damage"));
                        injured.setNoDamageTicks(0);
                        injured.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.08);
                        Bukkit.getScheduler().runTaskLater(this.plugin, new Runnable() {
                            @Override
                            public void run() {
                                injured.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1);
                            }
                        }, 5L);

                    } else if (result.getHitBlock() != null) {
                        Utils.debugPlayer(player, "Debug 6");
                        Utils.drawLine(Particle.REDSTONE, player.getEyeLocation(), result.getHitPosition().toLocation(world), 5f, Color.WHITE);
                        world.playSound(player.getLocation(), this.plugin.getConfig().getString("fweapons-bullet-flyby"), 1.0f, 1.0f);
                    }
                }
            }
        }
    }

    @EventHandler
    //On left click
    public void onLeftClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (event.getPlayer().getItemInHand().getType() == Material.getMaterial(Objects.requireNonNull(this.plugin.getConfig().getString("Mitraillette-item")))) {
                Player player = event.getPlayer();
                if (player.isSneaking()) {
                    if (mitrailletteCd.contains(player)) {
                        return;
                    }
                    mitrailletteCd.add(player);
                    event.setCancelled(true);
                    String parsed = event.getItem().getLore().get(0);
                    Integer maxAmo = plugin.getConfig().getInt("Mitraillette-max-amo");
                    Integer amoLeft = Integer.parseInt(parsed);
                    if (amoLeft <= 0) {
                        event.getPlayer().playSound(player.getLocation(), this.plugin.getConfig().getString("Mitraillette-reload"), 1.0f, 1.0f);
                        event.getPlayer().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.08);
                        Bukkit.getScheduler().runTaskLater(this.plugin, new Runnable() {
                            @Override
                            public void run() {
                                mitrailletteCd.remove(player);
                                event.getPlayer().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.01);
                                ItemMeta im = event.getItem().getItemMeta();
                                im.setLore(Arrays.asList(Integer.toString(maxAmo)));
                                im.setDisplayName(plugin.getConfig().getString("Mitraillette-display-name")
                                        .replace("{current-ammo}", Integer.toString(maxAmo))
                                        .replace("{max-ammo}", Integer.toString(maxAmo)));
                                event.getItem().setItemMeta(im);
                            }
                        }, 70L);
                    }
                } else {
                    event.setCancelled(true);
                    ItemStack casque = player.getInventory().getHelmet();
                    if (casque != null && casque.getType() == Material.CARVED_PUMPKIN && casque.getItemMeta().getLore().contains("Scope")) {
                        player.getActivePotionEffects().clear();
                        player.getInventory().setHelmet(new ItemStack(Material.AIR));
                    } else {
                        ItemStack pumpkin = new ItemStack(Material.CARVED_PUMPKIN);
                        ItemMeta pumpkinMeta = pumpkin.getItemMeta();
                        pumpkinMeta.setLore(Arrays.asList("Scope"));
                        pumpkin.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
                        pumpkin.setItemMeta(pumpkinMeta);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20000, 2));
                        player.getActivePotionEffects().clear();
                        player.removePotionEffect(PotionEffectType.SLOW);
                    }
                }
            }
        }
    }
}
