package farmans.tntarena.commands;

import farmans.tntarena.TNTArena;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Random;

public class StartCommand implements CommandExecutor {
    TNTArena plugin;

    public StartCommand(TNTArena plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String commandName = command.getName().toLowerCase();

        if (!commandName.equals("tntstart")) {
            return false;
        }
        if (args.length == 0 || !args[0].equals("confirm")) {
            String[] coords1 = plugin.getConfig().get("Coords1").toString().split(",");
            String[] coords2 = plugin.getConfig().get("Coords2").toString().split(",");
            int delay = plugin.getConfig().getInt("Delay");

            sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "Prvni souradnice X: " + coords1[0] + " Y: " + coords1[1] + " Z: " + coords1[2]);
            sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "Druha souradnice X: " + coords2[0] + " Y: " + coords2[1] + " Z: " + coords2[2]);
            sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "Delay je nastaven na " + delay + " milisekund");
            sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "Pro potvrzeni napis /tntstart confirm");
            sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "----------------------------------");

            return true;
        }
        if (plugin.getConfig().getInt("TaskID") != -1) {
            sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "Uz ti tady bezi jedny tntcka pico");
            sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "----------------------------------");
            return true;
        }

        Random random = new Random();
        String[] coords1 = plugin.getConfig().get("Coords1").toString().split(",");
        String[] coords2 = plugin.getConfig().get("Coords2").toString().split(",");
        if (coords1[0].equals("x") || coords2.equals("x")) {
            return false;
        }

        Player player = (Player)sender;
        World world = player.getWorld();

        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "Zacaly padat TNT");
        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "----------------------------------");
        int fallingTNTs = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                int randomX, randomY, randomZ;
                if (Float.parseFloat(coords2[0]) >= Float.parseFloat(coords1[0]) ) {
                    randomX = random.nextInt((int) Float.parseFloat(coords2[0]) - (int) Float.parseFloat(coords1[0]) + 1) + (int) Float.parseFloat(coords1[0]);
                } else {
                    randomX = random.nextInt((int) Float.parseFloat(coords1[0]) - (int) Float.parseFloat(coords2[0]) + 1) + (int) Float.parseFloat(coords2[0]);
                }
                if (Float.parseFloat(coords2[1]) >= Float.parseFloat(coords1[1]) ) {
                    randomY = random.nextInt((int) Float.parseFloat(coords2[1]) - (int) Float.parseFloat(coords1[1]) + 1) + (int) Float.parseFloat(coords1[1]);
                } else {
                    randomY = random.nextInt((int) Float.parseFloat(coords1[1]) - (int) Float.parseFloat(coords2[1]) + 1) + (int) Float.parseFloat(coords2[1]);
                }
                if (Float.parseFloat(coords2[2]) >= Float.parseFloat(coords1[2]) ) {
                    randomZ = random.nextInt((int) Float.parseFloat(coords2[2]) - (int) Float.parseFloat(coords1[2]) + 1) + (int) Float.parseFloat(coords1[2]);
                } else {
                    randomZ = random.nextInt((int) Float.parseFloat(coords1[2]) - (int) Float.parseFloat(coords2[2]) + 1) + (int) Float.parseFloat(coords2[2]);
                }
//DODĚLAT RUNNABLE REPEATING TASK
                world.spawnEntity(new Location(world, randomX, randomY, randomZ), EntityType.PRIMED_TNT);
            }
        }, 20, (int)plugin.getConfig().get("Delay")/50);
        plugin.getConfig().set("TaskID", fallingTNTs);
        plugin.saveConfig();

        return true;
    }
}
