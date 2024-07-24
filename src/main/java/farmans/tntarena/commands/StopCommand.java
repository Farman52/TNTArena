package farmans.tntarena.commands;

import farmans.tntarena.TNTArena;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StopCommand implements CommandExecutor {
    TNTArena plugin;

    public StopCommand(TNTArena plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int taskID = (int)plugin.getConfig().get("TaskID");

        if (!command.getName().toLowerCase().equals("tntstop") || taskID == -1) {
            return false;
        }
        Bukkit.getScheduler().cancelTask((int)plugin.getConfig().get("TaskID"));
        plugin.getConfig().set("TaskID", -1);
        plugin.saveConfig();
        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "Prestaly padat TNT");
        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "----------------------------------");


        return true;
    }
}
