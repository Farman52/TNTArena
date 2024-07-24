package farmans.tntarena.commands;

import farmans.tntarena.TNTArena;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DelayCommand implements CommandExecutor {
    TNTArena plugin;

    public DelayCommand(TNTArena plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().toLowerCase().equals("tntdelay")) {
            return false;
        }

        if (args.length == 1) {
            int delay = Integer.parseInt(args[0]);
            plugin.getConfig().set("Delay", delay);
            plugin.saveConfig();
            sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "Delay byl nastaven na " + delay + " milisekund");
            sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "----------------------------------");
            return true;
        }

        return false;
    }
}
