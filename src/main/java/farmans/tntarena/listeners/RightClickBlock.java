package farmans.tntarena.listeners;

import farmans.tntarena.TNTArena;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;


public class RightClickBlock implements Listener {
    TNTArena plugin;

    public RightClickBlock(TNTArena plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!player.isOp()) {
            return;
        }
        Action action = event.getAction();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getHand() == EquipmentSlot.HAND) {
                if (item.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, "tntarena"), PersistentDataType.BOOLEAN)) {
                    Block block = event.getClickedBlock();
                    float x, y, z;
                    x = block.getX();
                    y = block.getY();
                    z = block.getZ();
                    plugin.getConfig().set("Coords2", x+","+y+","+z);
                    plugin.saveConfig();

                    String blockName = block.getType().name().toLowerCase();
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "Druhy blok " + blockName + " oznacen na X: " + x + " Y: " + y + " Z: " + z);
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "----------------------------------");

                }
            }
        }
    }
}
