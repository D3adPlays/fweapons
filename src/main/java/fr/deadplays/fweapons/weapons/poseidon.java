package fr.deadplays.fweapons.weapons;

import fr.deadplays.fweapons.main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;


public class poseidon implements CommandExecutor {

    private main plugin;

    public poseidon(final main plugin){
        this.plugin = plugin;
        plugin.getCommand("poseidon").setExecutor((CommandExecutor)this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args){
        // if sender is a player
        if (sender instanceof Player) {

        }
            if (cmd.getName().equalsIgnoreCase("poseidon")) {
                Player player = (Player) sender;




        }


        return false;
    }
}