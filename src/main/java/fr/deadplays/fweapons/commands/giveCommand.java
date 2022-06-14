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

        ItemStack hppotion = new ItemStack(Material.GLASS_BOTTLE, 1);
        ItemMeta hppotionm = hppotion.getItemMeta();

        hppotionm.setDisplayName("§cBarre de Survie");
        hppotionm.addEnchant(Enchantment.DEPTH_STRIDER, 1, true);
        hppotionm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        hppotion.setItemMeta(hppotionm);


        ItemStack grenadeincape = new ItemStack(Material.SNOWBALL, 1);
        ItemMeta grenadeinacpem = grenadeincape.getItemMeta();

        grenadeinacpem.setDisplayName("§cGrenade Incapacitante");
        grenadeinacpem.addEnchant(Enchantment.DEPTH_STRIDER, 1, true);
        grenadeinacpem.addItemFlags(ItemFlag.HIDE_ENCHANTS);




        ItemStack mitraillete = new ItemStack(Material.valueOf(this.plugin.getConfig().getString("Mitraillette-item")), 1);
        ItemMeta mitrailletem = mitraillete.getItemMeta();

        mitrailletem.setDisplayName(this.plugin.getConfig().getString("Mitraillette-display-name")
                .replace("{current-ammo}", this.plugin.getConfig().getString("Mitraillette-max-amo"))
                .replace("{max-ammo}", this.plugin.getConfig().getString("Mitraillette-max-amo")));
        mitrailletem.setLore(Arrays.asList(this.plugin.getConfig().getString("Mitraillette-max-amo")));
        mitraillete.setItemMeta(mitrailletem);



        if (sender instanceof Player) {
            Player player = (Player)sender;

            if(cmd.getName().equalsIgnoreCase("weapon")) {

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