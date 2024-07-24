package farmans.tntarena.listeners;

import farmans.tntarena.TNTArena;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class BreakBlocks implements Listener {
    TNTArena plugin;

    public BreakBlocks(TNTArena plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        float x, y, z;

        Player player = event.getPlayer();
        if (!player.isOp()) {
            return;
        }
        ItemStack item = player.getInventory().getItemInMainHand();
        Block block = event.getBlock();

        if (!item.hasItemMeta() || !item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, "tntarena"), PersistentDataType.BOOLEAN)) {
            return;
        }
        x = block.getX();
        y = block.getY();
        z = block.getZ();
        plugin.getConfig().set("Coords1", x+","+y+","+z);
        plugin.saveConfig();

        Material type = block.getType();
        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "Prvni blok " + type.name().toLowerCase() + " oznacen na X: "+ x + " Y: " + y + " Z: " + z);
        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "----------------------------------");

        event.setCancelled(true);

    }
}
