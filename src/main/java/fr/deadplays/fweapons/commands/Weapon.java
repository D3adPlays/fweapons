package fr.deadplays.fweapons.commands;

import fr.deadplays.fweapons.main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Weapon implements CommandExecutor {

    private main plugin;
    public Weapon(final main plugin){
        this.plugin = plugin;
        plugin.getCommand("weapon").setExecutor((CommandExecutor)this);
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        return false;
    }
}
