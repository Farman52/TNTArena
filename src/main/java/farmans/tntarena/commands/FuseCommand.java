package farmans.tntarena.commands;

import farmans.tntarena.TNTArena;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FuseCommand implements CommandExecutor {
    TNTArena plugin;

    public FuseCommand(TNTArena plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().toLowerCase().equals("tntfuse") || args.length == 0) {
            return false;
        }
        int fuse = Integer.parseInt(args[0]);
        plugin.getConfig().set("Fuse", fuse);
        plugin.saveConfig();
        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "Cas odpaleni byl nastaven na " + fuse + " milisekund");
        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "----------------------------------");

        return true;
    }
}
