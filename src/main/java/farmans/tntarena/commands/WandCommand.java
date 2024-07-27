package farmans.tntarena.commands;

import farmans.tntarena.TNTArena;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.messaging.ChannelNameTooLongException;

import java.util.ArrayList;
import java.util.Arrays;

public class WandCommand implements CommandExecutor {
    TNTArena plugin;

    public WandCommand(TNTArena plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().toLowerCase().equals("tntwand") || args.length == 0) {
           return false;
        }
        int fuse = Integer.parseInt(args[0]);
        int distance = 60;
        if (args.length >= 2) distance = Integer.parseInt(args[1]);


        Player player = (Player)sender;

        ItemStack itemWand = new ItemStack(Material.BLAZE_ROD);
        ItemMeta wandMeta = itemWand.getItemMeta();
        wandMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "T" + ChatColor.WHITE + "" + ChatColor.BOLD + "N" + ChatColor.RED + "" + ChatColor.BOLD + "T" + ChatColor.WHITE + " Wand");
        wandMeta.setLore(new ArrayList<String>(Arrays.asList(new String[]{"Cas odpaleni: " + fuse + " ms", "Vzdalenost: " + distance + " bloku"})));
        PersistentDataContainer data = wandMeta.getPersistentDataContainer();
        data.set(new NamespacedKey(plugin, "tntwand"), PersistentDataType.BOOLEAN, true);
        data.set(new NamespacedKey(plugin, "tntwandfuse"), PersistentDataType.INTEGER, fuse);
        data.set(new NamespacedKey(plugin, "tntwanddistance"), PersistentDataType.INTEGER, distance);

        itemWand.setItemMeta(wandMeta);
        player.getInventory().addItem(itemWand);

        return true;
    }
}
