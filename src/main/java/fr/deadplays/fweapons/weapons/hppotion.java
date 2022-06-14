package fr.deadplays.fweapons.weapons;


import fr.deadplays.fweapons.Utils;
import fr.deadplays.fweapons.main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.net.MalformedURLException;
import java.net.URL;


public class hppotion implements Listener {

    private main plugin;
    public hppotion(final main plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        player.getInventory().clear();
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1);
        player.updateInventory();


        String resourcepack = plugin.getConfig().getString("fweapons-resource-pack-url");
        try {
            URL u = new URL(plugin.getConfig().getString("fweapons-resource-pack-url"));
            Bukkit.getScheduler().runTaskLater(this.plugin, new Runnable() {
                    @Override
                    public void run() {
                        player.setResourcePack(resourcepack);
                    }
            }, 100L);
        } catch (MalformedURLException e) {

            System.out.println("[FWeapons] Erreur lors de la création de l'URL du resource pack. Url est invalide.");
            System.out.println("[FWeapons] URL : " + resourcepack);

            player.sendMessage("Une erreur est survenue, veuillez contacter un administrateur.");
        }

    }

    @EventHandler
    public void onResourcepackStatusEvent(PlayerResourcePackStatusEvent event){
        if(event.getStatus() == PlayerResourcePackStatusEvent.Status.DECLINED)
            event.getPlayer().kickPlayer(Utils.chat(this.plugin.getConfig().getString("fweapons-resource-pack-error")));
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack it = event.getItem();

        if(it == null)return;


        if(it.getType() == Material.GLASS_BOTTLE && it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase("§cBarre de Survie")) {
            if(action == Action.RIGHT_CLICK_AIR) {
                it.setAmount(it.getAmount()-1);
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 0));
                player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 5, 1));
            }

        }
        if(it.getType() == Material.SNOWBALL && it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase("§cGrenade Incapacitante")) {
            if(action == Action.RIGHT_CLICK_AIR) {
                it.setAmount(it.getAmount()-1);
                // On snowball land
                player.getWorld().createExplosion(player.getLocation(), 0.0F, false, false);
                // On snowball hit
                player.getWorld().createExplosion(player.getLocation(), 0.0F, false, false);





            }

        }
    }



}