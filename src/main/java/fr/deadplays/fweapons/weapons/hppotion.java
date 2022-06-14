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
        ItemStack customsword = new ItemStack(Material.STICK, 1);
        ItemMeta customM = customsword.getItemMeta();
        customM.setDisplayName("§cLaser");
        customM.addEnchant(Enchantment.DEPTH_STRIDER, 1, true);
        customM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        customsword.setItemMeta(customM);

        player.getInventory().setItem(3, customsword);


        ItemStack hppotion = new ItemStack(Material.GLASS_BOTTLE, 1);
        ItemMeta hppotionm = hppotion.getItemMeta();
        hppotionm.setDisplayName("§cBarre de Survie");
        hppotionm.addEnchant(Enchantment.DEPTH_STRIDER, 1, true);
        hppotionm.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        hppotion.setItemMeta(hppotionm);

        player.getInventory().setItem(2, hppotion);
        //ItemStack customstick = new ItemStack(Material.STICK, 1);

        player.updateInventory();
        //create q new thread and run latter
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

        if(it.getType() == Material.STICK && it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase("§cLaser")) {
            if(action == Action.RIGHT_CLICK_AIR) {
                player.sendMessage("Piu");
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2, 3));
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1, 0));
            }

        }

        if(it.getType() == Material.GLASS_BOTTLE && it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase("§cBarre de Survie")) {
            if(action == Action.RIGHT_CLICK_AIR) {
                player.sendMessage("Vous avez bus la potions");
                it.setAmount(-1);
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100*1, 0));
                player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 5, 1));

            }

        }
    }




}