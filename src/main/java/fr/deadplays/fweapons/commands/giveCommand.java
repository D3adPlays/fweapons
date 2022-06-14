package fr.deadplays.fweapons.commands;

import fr.deadplays.fweapons.main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.lang.model.type.ArrayType;
import java.util.Arrays;
import java.util.Objects;

public class giveCommand implements CommandExecutor {

    private final main plugin;

    public giveCommand(final main plugin){
        this.plugin = plugin;
        plugin.getCommand("weapon").setExecutor((CommandExecutor)this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        ItemStack hppotion = new ItemStack(Material.GLASS_BOTTLE, 5);
        ItemMeta hppotionm = hppotion.getItemMeta();
        ItemStack mitraillete = new ItemStack(Material.valueOf(this.plugin.getConfig().getString("Mitraillette-item")), 1);
        ItemMeta mitrailletem = mitraillete.getItemMeta();

        hppotionm
                .setDisplayName("§cBarre de Survie");
        hppotionm
                .addEnchant(Enchantment.DEPTH_STRIDER, 1, true);
        hppotionm
                .addItemFlags(ItemFlag.HIDE_ENCHANTS);
        hppotion
                .setItemMeta(hppotionm);
        mitrailletem.setDisplayName(this.plugin.getConfig().getString("Mitraillette-display-name")
                .replace("{current-ammo}", this.plugin.getConfig().getString("Mitraillette-max-amo"))
                .replace("{max-ammo}", this.plugin.getConfig().getString("Mitraillette-max-amo")));
        mitrailletem
                .setLore(Arrays.asList(this.plugin.getConfig().getString("Mitraillette-max-amo")));
        mitraillete
                .setItemMeta(mitrailletem);



        if (sender instanceof Player) {
            Player player = (Player)sender;

            if(cmd.getName().equalsIgnoreCase("weapon") && args.length >= 1){
                if(args[0].equalsIgnoreCase("hppotion")){
                    player.getInventory().addItem(hppotion);
                    player.sendMessage(this.plugin.getConfig().getString("give-hppotion"));
                }
                if(args[0].equalsIgnoreCase("mitraillette")){
                    player.getInventory().addItem(mitraillete);
                    player.sendMessage(this.plugin.getConfig().getString("give-mitraillette"));
                }

                else{
                    player.sendMessage(this.plugin.getConfig().getString("give-error"));
                }

                return true;

            }

            else if(cmd.getName().equalsIgnoreCase("weapon")){

                player.sendMessage("§cLancement de la procédure de give.");

                player.getInventory().setItem(3, hppotion);
                player.getInventory().setItem(4, mitraillete);

                return true;

            }
        }

        sender.sendMessage("§cErreur ! Merci de reesayer.");
        return false;

    }

}