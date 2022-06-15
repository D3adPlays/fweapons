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

    private main plugin;

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
            try {


                if (cmd.getName().equalsIgnoreCase("weapon")) {
                    Player player = (Player) sender;
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target != null) {
                        // /weapon give <player> <weapon>
                        if (args.length <= 3) {
                            if (args[0].equalsIgnoreCase("give")) {
                                if (args[2].equalsIgnoreCase("hppotion")) {
                                    target.getInventory().addItem(hppotion);
                                    target.sendMessage("§aVous avez reçu une barre de survie");
                                    return true;
                                } else if (args[2].equalsIgnoreCase("mitraillette")) {
                                    target.getInventory().addItem(mitraillete);
                                    target.sendMessage("§aVous avez reçu une mitraillette");
                                    return true;
                                }
                            } else if (args[0].equalsIgnoreCase("all")) {
                                target.getInventory().addItem(hppotion);
                                target.getInventory().addItem(mitraillete);
                                target.sendMessage("§aVous avez reçu une barre de survie et une mitraillette");
                                return true;

                            } else {
                                player.sendMessage("§csyntaxe : /weapon all/give <player> <weapon>");
                                return true;
                            }
                        }
                        if (args[1] == null) {
                            player.sendMessage("§cErreur de syntaxe");
                            player.sendMessage("§csyntaxe : /weapon all/give <player> <weapon>");

                            return true;
                        }
                        // clear error
                        if (args[2] == null) {
                            player.sendMessage("§cErreur de syntaxe");
                            player.sendMessage("§csyntaxe : /weapon all/give <player> <weapon>");
                            return true;
                        }


                    }

                }
            } catch (NullPointerException e) {
                sender.sendMessage("§cErreur de syntaxe");
                sender.sendMessage("§csyntaxe : /weapon all/give <player> <weapon>");
            }
        }
        return false;
    }
}