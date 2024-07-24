package farmans.tntarena.commands;

import farmans.tntarena.TNTArena;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class InfoCommand implements CommandExecutor {
    TNTArena plugin;

    public InfoCommand(TNTArena plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().toLowerCase().equals("tntinfo")) {
            return false;
        }


        String[] coords1 = plugin.getConfig().get("Coords1").toString().split(",");
        String[] coords2 = plugin.getConfig().get("Coords2").toString().split(",");
        int delay = plugin.getConfig().getInt("Delay");
        int taskID = plugin.getConfig().getInt("TaskID");

        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "Task ID " + taskID);
        if (coords1[0].equals("x")) {
            sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "Prvni souradnice neni zapsana");
        } else {
            sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "Prvni souradnice X: " + coords1[0] + " Y: " + coords1[1] + " Z: " + coords1[2]);
        } if (coords2[0].equals("x")) {
            sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "Druha souradnice neni zapsana");
        } else {
            sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "Druha souradnice X: " + coords2[0] + " Y: " + coords2[1] + " Z: " + coords2[2]);
        }
        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "Delay je nastaven na " + delay + " milisekund");
        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "----------------------------------");
        return true;
    }
}
