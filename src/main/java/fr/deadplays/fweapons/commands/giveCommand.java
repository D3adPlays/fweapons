package fr.deadplays.fweapons.commands;

import fr.deadplays.fweapons.main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

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
            Player player = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("weapon")) {

                // Give the weapon on a specific player
                if (args.length == 2) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target != null) {
                        target.getInventory().addItem(hppotion);
                        target.getInventory().addItem(mitraillete);
                        target.updateInventory();
                        player.sendMessage(this.plugin.getConfig().getString("give-weapon-success")
                                .replace("{player}", target.getName()
                                        .replace("{sender}", player.getName())));


                    } else if (args.length == 2 && sender instanceof Player && msg.equalsIgnoreCase("hppotion")) {
                        player.getInventory().addItem(hppotion);
                        player.updateInventory();
                        // send success message to sender
                        sender.sendMessage(this.plugin.getConfig().getString("give-weapon-success-sender")
                                .replace("{player}", player.getName()
                                        .replace("{weapon}", "§cBarre de Survie")));
                        if(player != sender){
                            player.sendMessage(this.plugin.getConfig().getString("give-weapon-success")
                                    .replace("{player}", player.getName()
                                            .replace("{sender}", sender.getName())
                                            .replace("{weapon}", "§cBarre de Survie")));
                        }



                    } else if (args.length == 2 && sender instanceof Player && msg.equalsIgnoreCase("mitraillette")) {
                        player.getInventory().addItem(mitraillete);
                        player.updateInventory();
                        sender.sendMessage(this.plugin.getConfig().getString("give-weapon-success-sender")
                                .replace("{player}", player.getName()
                                        .replace("{weapon}", "§cMitraillette")));
                        if(player != sender){
                            player.sendMessage(this.plugin.getConfig().getString("give-weapon-success")
                                .replace("{player}", player.getName()
                                        .replace("{weapon}", "§cMitraillette")));}


                    } else {
                        sender.sendMessage(this.plugin.getConfig().getString("give-weapon-fail")
                                .replace("{player}", args[1]));
                        sender.sendMessage(this.plugin.getConfig().getString("give-weapon-list")
                                .replace("{weapons}", "Barre de Survie, Mitraillette"));


                    }
                }
            }

        }
        sender.sendMessage(this.plugin.getConfig().getString("give-weapon-fail")
                .replace("{player}", args[1]));
        sender.sendMessage(this.plugin.getConfig().getString("give-weapon-list")
                //replace weapons by the list of weapons
                .replace("{weapons}", "Barre de Survie, Mitraillette"));
        return false;

    }
}