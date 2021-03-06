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
        ItemStack invocationitem = new ItemStack(Material.valueOf(this.plugin.getConfig().getString("Invocation-item")), 5);
        ItemMeta invocationitemm = invocationitem.getItemMeta();
        ItemStack poseidonitem = new ItemStack(Material.valueOf(this.plugin.getConfig().getString("Poseidon-item")), 1);
        ItemMeta poseidonitemm = poseidonitem.getItemMeta();
        ItemStack superpoisson = new ItemStack(Material.valueOf(this.plugin.getConfig().getString("Superpoisson-item")), 1);
        ItemMeta superpoissonm = superpoisson.getItemMeta();

        superpoissonm
                .setDisplayName("┬žaLanceur de poisson");
        superpoissonm
                .addEnchant(Enchantment.LURE, 1, true);
        superpoissonm
                .addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        superpoissonm
                .addItemFlags(ItemFlag.HIDE_ENCHANTS);
        superpoissonm
                .isUnbreakable();
        superpoisson.setItemMeta(superpoissonm);

        hppotionm
                .setDisplayName("┬žcBarre de Survie");
        hppotionm
                .addEnchant(Enchantment.DEPTH_STRIDER, 1, true);
        hppotionm
                .addItemFlags(ItemFlag.HIDE_ENCHANTS);
        hppotionm
                .addEnchant(Enchantment.BINDING_CURSE, 1, true);
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
        laserm
                .setCustomModelData(12341);
        laser
                .setItemMeta(laserm);

        invocationitemm.setDisplayName("┬žcItem d'Invocation");

        invocationitemm
                .addItemFlags(ItemFlag.HIDE_ENCHANTS);

        invocationitemm.addEnchant(Enchantment.DURABILITY, 4, true);

        invocationitemm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        invocationitemm.isUnbreakable();

        invocationitem
                .setItemMeta(invocationitemm);

        invocationitemm
                .addEnchant(Enchantment.BINDING_CURSE, 1, true);

        poseidonitemm.setDisplayName("┬žcLance de Poseidon");

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
                                player.sendMessage("┬žbVoici la liste de toute les armes disponibles :" + "\n" + "┬žb- hppotion" + "\n" + "┬žb- mitraillette" + "\n" + "┬žb- laser" + "\n" + "┬žb- invocation" + "\n" + "┬žb- poseidon" + "\n" + "┬žb- superpoisson");
                                return true;
                            }

                            if (args[0].equalsIgnoreCase("all")) {
                                player.getInventory().addItem(hppotion);
                                player.getInventory().addItem(mitraillete);
                                player.getInventory().addItem(laser);
                                player.getInventory().addItem(invocationitem);
                                player.getInventory().addItem(poseidonitem);
                                player.getInventory().addItem(superpoisson);
                                player.sendMessage("┬žaVous avez re├žu tout les items disponibles");
                                return true;
                            }

                            Player target = Bukkit.getPlayer(args[1]);

                            if (target == null) {

                                if (args[0].equalsIgnoreCase("give")) {
                                    if (args[1].equalsIgnoreCase("hppotion")) {
                                        player.getInventory().addItem(hppotion);
                                        player.sendMessage("┬žaVous avez re├žu une barre de survie");
                                        return true;
                                    } else if (args[1].equalsIgnoreCase("mitraillette")) {
                                        player.getInventory().addItem(mitraillete);
                                        player.sendMessage("┬žaVous avez re├žu une mitraillette");
                                        return true;
                                    } else if (args[1].equalsIgnoreCase("laser")) {
                                        player.getInventory().addItem(laser);
                                        player.sendMessage("┬žaVous avez re├žu un laser");
                                        return true;
                                    } else if (args[1].equalsIgnoreCase("all")) {
                                        player.getInventory().addItem(hppotion);
                                        player.getInventory().addItem(mitraillete);
                                        player.getInventory().addItem(laser);
                                        player.getInventory().addItem(invocationitem);
                                        player.getInventory().addItem(poseidonitem);
                                        player.getInventory().addItem(superpoisson);
                                        player.sendMessage("┬žaVous avez re├žu tout les items disponibles");
                                        return true;
                                    } else if (args[1].equalsIgnoreCase("invocation")) {
                                        player.getInventory().addItem(invocationitem);
                                        player.sendMessage("┬žaVous avez re├žu une barre d'invocation");
                                        return true;
                                    } else if (args[1].equalsIgnoreCase("poseidon")) {
                                        player.getInventory().addItem(poseidonitem);
                                        player.sendMessage("┬žaVous avez re├žu une barre de poseidon");
                                        return true;
                                    } else if (args[1].equalsIgnoreCase("superpoisson")) {
                                        player.getInventory().addItem(superpoisson);
                                        player.sendMessage("┬žaVous avez re├žu une barre de poseidon");
                                        return true;
                                    } else {
                                        player.sendMessage("┬žcCette arme n'existe pas");
                                        return true;
                                    }
                                }
                            }

                            if (args.length <= 3) {



                                if (target != null) {

                                    if (args[0].equalsIgnoreCase("give")) {

                                        if (args[2].equalsIgnoreCase("hppotion")) {
                                            target.getInventory().addItem(hppotion);
                                            target.sendMessage("┬žaVous avez re├žu une barre de survie");
                                            if (target != player) {
                                                player.sendMessage("┬žaVous avez donn├ę une barre de survie ├á " + target.getName());
                                            }
                                            return true;
                                        } else if (args[2].equalsIgnoreCase("mitraillette")) {
                                            target.getInventory().addItem(mitraillete);
                                            target.sendMessage("┬žaVous avez re├žu une mitraillette");
                                            if (target != player) {
                                                player.sendMessage("┬žaVous avez donn├ę une mitraillette ├á " + target.getName());
                                            }
                                            return true;
                                        } else if (args[2].equalsIgnoreCase("laser")) {
                                            target.getInventory().addItem(laser);
                                            target.sendMessage("┬žaVous avez re├žu un laser");
                                            if (target != player) {
                                                player.sendMessage("┬žaVous avez donn├ę un laser ├á " + target.getName());
                                            }
                                            return true;
                                        } else if (args[2].equalsIgnoreCase("all")) {
                                            target.getInventory().addItem(hppotion);
                                            target.getInventory().addItem(mitraillete);
                                            target.getInventory().addItem(laser);
                                            target.getInventory().addItem(invocationitem);
                                            target.getInventory().addItem(poseidonitem);
                                            target.getInventory().addItem(superpoisson);
                                            target.sendMessage("┬žaVous avez re├žu tout les items disponibles");
                                            if (target != player) {
                                                player.sendMessage("┬žaVous avez donn├ę tout les items disponibles ├á " + target.getName());
                                            }
                                            return true;
                                        } else if (args[2].equalsIgnoreCase("invocation")) {
                                            target.getInventory().addItem(invocationitem);
                                            target.sendMessage("┬žaVous avez re├žu une barre d'invocation");
                                            if (target != player) {
                                                player.sendMessage("┬žaVous avez donn├ę une barre d'invocation ├á " + target.getName());
                                            }
                                            return true;
                                        } else if (args[2].equalsIgnoreCase("poseidon")) {
                                            target.getInventory().addItem(poseidonitem);
                                            target.sendMessage("┬žaVous avez re├žu un poseidon");
                                            if (target != player) {
                                                player.sendMessage("┬žaVous avez donn├ę un poseidon ├á " + target.getName());
                                            }
                                            return true;
                                        } else if (args[2].equalsIgnoreCase("superpoisson")) {
                                            target.getInventory().addItem(superpoisson);
                                            target.sendMessage("┬žaVous avez re├žu un super poisson");
                                            if (target != player) {
                                                player.sendMessage("┬žaVous avez donn├ę un super poisson ├á " + target.getName());
                                            }
                                            return true;
                                        } else {
                                            player.sendMessage("┬žcCette arme n'existe pas");
                                            return true;
                                        }
                                    }
                                }
                            }

                            return true;
                        }





                }
            } catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage("┬žc┬žoErreur de syntaxe : ");
                sender.sendMessage("┬žd┬žnsyntaxe : /weapon all/give <player> <weapon>");
                sender.sendMessage("┬žbVoici la liste de toute les armes disponibles :" + "\n" + "┬žb- hppotion" + "\n" + "┬žb- mitraillette" + "\n" + "┬žb- laser" + "\n" + "┬žb- invocation" + "\n" + "┬žb- poseidon" + "\n" + "┬žb- superpoisson");
            } catch (NullPointerException | NullArgumentException e) {
                sender.sendMessage("┬žcErreur de syntaxe");
                sender.sendMessage("┬žcsyntaxe : /weapon all/give <player> <weapon>");
            }
        }
        return false;
    }
}