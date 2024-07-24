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

import java.util.ArrayList;
import java.util.Arrays;

public class SetupCommand implements CommandExecutor {
    TNTArena plugin;

    public SetupCommand(TNTArena plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String commandName = command.getName().toLowerCase();

        if (!commandName.equals("tntsetup")) {
            return false;
        }

        Player player = (Player)sender;

        ItemStack itemWand = new ItemStack(Material.STICK);
        ItemMeta wandMeta = itemWand.getItemMeta();
        wandMeta.setDisplayName(ChatColor.MAGIC + "LLL" + ChatColor.DARK_PURPLE + "NEW MAGIC WAND" + ChatColor.RESET + ChatColor.MAGIC + "LLL");
        wandMeta.setLore(new ArrayList<String>(Arrays.asList(new String[]{ChatColor.GRAY + "" + ChatColor.ITALIC + "- Tyler, the Creator"})));
        PersistentDataContainer data = wandMeta.getPersistentDataContainer();
        data.set(new NamespacedKey(plugin, "tntarena"), PersistentDataType.BOOLEAN, true);

        itemWand.setItemMeta(wandMeta);
        player.getInventory().addItem(itemWand);

        return true;
    }
}

