package fr.deadplays.fweapons.commands;

import fr.deadplays.fweapons.main;
import org.apache.commons.lang.NullArgumentException;
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
        ItemStack laser = new ItemStack(Material.valueOf(this.plugin.getConfig().getString("Laser-item")), 1);
        ItemMeta laserm = laser.getItemMeta();
        ItemStack invocationitem = new ItemStack(Material.valueOf(this.plugin.getConfig().getString("Invocation-item")), 1);
        ItemMeta invocationitemm = invocationitem.getItemMeta();
        ItemStack poseidonitem = new ItemStack(Material.valueOf(this.plugin.getConfig().getString("Poseidon-item")), 1);
        ItemMeta poseidonitemm = poseidonitem.getItemMeta();




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
        laserm.setDisplayName(this.plugin.getConfig().getString("Laser-display-name")
                .replace("{max-ammo}", this.plugin.getConfig().getString("Laser-max-amo"))
                .replace("{current-ammo}", this.plugin.getConfig().getString("Laser-max-amo")));
        laserm
                .setLore(Arrays.asList(this.plugin.getConfig().getString("Laser-max-amo")));
        laser
                .setItemMeta(laserm);
        invocationitemm.setDisplayName(this.plugin.getConfig().getString("Invocation-display-name")
                .replace("{max-ammo}", this.plugin.getConfig().getString("Invocation-max-amo"))
                .replace("{current-ammo}", this.plugin.getConfig().getString("Invocation-max-amo")));
        invocationitemm
                .setLore(Arrays.asList(this.plugin.getConfig().getString("Invocation-max-amo")));
        invocationitem
                .setItemMeta(invocationitemm);

        poseidonitemm.setDisplayName(this.plugin.getConfig().getString("Poseidon-display-name")
                .replace("{max-ammo}", this.plugin.getConfig().getString("Poseidon-max-amo"))
                .replace("{current-ammo}", this.plugin.getConfig().getString("Poseidon-max-amo")));

        poseidonitemm
                .setLore(Arrays.asList(this.plugin.getConfig().getString("Poseidon-max-amo")));
        poseidonitemm
                .addEnchant(Enchantment.LOYALTY, 5, true);
        poseidonitemm
                .addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
        poseidonitemm
                .isUnbreakable();

        poseidonitemm
                .addEnchant(Enchantment.BINDING_CURSE, 1, true);
        poseidonitem
                .setItemMeta(poseidonitemm);





        if (sender instanceof Player) {
            try {
                if (cmd.getName().equalsIgnoreCase("weapon")) {
                    Player player = (Player) sender;

                        if (args.length <= 3) {


                            if (args[0].equalsIgnoreCase("list")) {
                                player.sendMessage("§bVoici la liste de toute les armes disponibles :" + "\n" + "§b- hppotion" + "\n" + "§b- mitraillette" + "\n" + "§b- laser" + "\n" + "§b- invocation" + "\n" + "§b- poseidon");
                                return true;
                            }

                            if (args[0].equalsIgnoreCase("all")) {
                                player.getInventory().addItem(hppotion);
                                player.getInventory().addItem(mitraillete);
                                player.getInventory().addItem(laser);
                                player.getInventory().addItem(invocationitem);
                                player.getInventory().addItem(poseidonitem);
                                player.sendMessage("§aVous avez reçu tout les items disponibles");
                                return true;
                            }

                            Player target = Bukkit.getPlayer(args[1]);

                            if (target == null) {

                                if (args[0].equalsIgnoreCase("give")) {
                                    if (args[1].equalsIgnoreCase("hppotion")) {
                                        player.getInventory().addItem(hppotion);
                                        player.sendMessage("§aVous avez reçu une barre de survie");
                                        return true;
                                    } else if (args[1].equalsIgnoreCase("mitraillette")) {
                                        player.getInventory().addItem(mitraillete);
                                        player.sendMessage("§aVous avez reçu une mitraillette");
                                        return true;
                                    } else if (args[1].equalsIgnoreCase("laser")) {
                                        player.getInventory().addItem(laser);
                                        player.sendMessage("§aVous avez reçu un laser");
                                        return true;
                                    } else if (args[1].equalsIgnoreCase("all")) {
                                        player.getInventory().addItem(hppotion);
                                        player.getInventory().addItem(mitraillete);
                                        player.getInventory().addItem(laser);
                                        player.getInventory().addItem(invocationitem);
                                        player.getInventory().addItem(poseidonitem);
                                        player.sendMessage("§aVous avez reçu tout les items disponibles");
                                        return true;
                                    } else if (args[1].equalsIgnoreCase("invocation")) {
                                        player.getInventory().addItem(invocationitem);
                                        player.sendMessage("§aVous avez reçu une barre d'invocation");
                                        return true;
                                    } else if (args[1].equalsIgnoreCase("poseidon")) {
                                        player.getInventory().addItem(poseidonitem);
                                        player.sendMessage("§aVous avez reçu une barre de poseidon");
                                        return true;
                                    } else {
                                        player.sendMessage("§cCette arme n'existe pas");
                                        return true;
                                    }
                                }
                            }

                            if (args.length <= 3) {



                                if (target != null) {

                                    if (args[0].equalsIgnoreCase("give")) {

                                        if (args[2].equalsIgnoreCase("hppotion")) {
                                            target.getInventory().addItem(hppotion);
                                            target.sendMessage("§aVous avez reçu une barre de survie");
                                            if (target != player) {
                                                player.sendMessage("§aVous avez donné une barre de survie à " + target.getName());
                                            }
                                            return true;
                                        } else if (args[2].equalsIgnoreCase("mitraillette")) {
                                            target.getInventory().addItem(mitraillete);
                                            target.sendMessage("§aVous avez reçu une mitraillette");
                                            if (target != player) {
                                                player.sendMessage("§aVous avez donné une mitraillette à " + target.getName());
                                            }
                                            return true;
                                        } else if (args[2].equalsIgnoreCase("laser")) {
                                            target.getInventory().addItem(laser);
                                            target.sendMessage("§aVous avez reçu un laser");
                                            if (target != player) {
                                                player.sendMessage("§aVous avez donné un laser à " + target.getName());
                                            }
                                            return true;
                                        } else if (args[2].equalsIgnoreCase("all")) {
                                            target.getInventory().addItem(hppotion);
                                            target.getInventory().addItem(mitraillete);
                                            target.getInventory().addItem(laser);
                                            target.getInventory().addItem(invocationitem);
                                            target.getInventory().addItem(poseidonitem);
                                            target.sendMessage("§aVous avez reçu tout les items disponibles");
                                            if (target != player) {
                                                player.sendMessage("§aVous avez donné tout les items disponibles à " + target.getName());
                                            }
                                            return true;
                                        } else if (args[2].equalsIgnoreCase("invocation")) {
                                            target.getInventory().addItem(invocationitem);
                                            target.sendMessage("§aVous avez reçu une barre d'invocation");
                                            if (target != player) {
                                                player.sendMessage("§aVous avez donné une barre d'invocation à " + target.getName());
                                            }
                                            return true;
                                        } else if (args[2].equalsIgnoreCase("poseidon")) {
                                            target.getInventory().addItem(poseidonitem);
                                            target.sendMessage("§aVous avez reçu un poseidon");
                                            if (target != player) {
                                                player.sendMessage("§aVous avez donné un poseidon à " + target.getName());
                                            }
                                            return true;
                                        } else {
                                            player.sendMessage("§cCette arme n'existe pas");
                                            return true;
                                        }
                                    }
                                }
                            }

                            return true;
                        }





                }
            } catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage("§c§oErreur de syntaxe : ");
                sender.sendMessage("§d§nsyntaxe : /weapon all/give <player> <weapon>");
                sender.sendMessage("§bVoici la liste de toute les armes disponibles :" + "\n" + "§b- hppotion" + "\n" + "§b- mitraillette" + "\n" + "§b- laser" + "\n" + "§b- invocation" + "\n" + "§b- poseidon");
            } catch (NullPointerException e) {
                sender.sendMessage("§cErreur de syntaxe");
                sender.sendMessage("§csyntaxe : /weapon all/give <player> <weapon>");
            } catch (NullArgumentException e) {
                sender.sendMessage("§cErreur de syntaxe");
                sender.sendMessage("§csyntaxe : /weapon all/give <player> <weapon>");
            }
        }
        return false;
    }
}