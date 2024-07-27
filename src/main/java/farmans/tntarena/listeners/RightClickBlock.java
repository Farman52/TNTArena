package farmans.tntarena.listeners;

import farmans.tntarena.TNTArena;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

        if (event.getHand() == EquipmentSlot.HAND) {
            if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
                if (item.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, "tntarena"), PersistentDataType.BOOLEAN)) {
                    Block block = event.getClickedBlock();
                    float x, y, z;
                    x = block.getX();
                    y = block.getY();
                    z = block.getZ();
                    plugin.getConfig().set("Coords2", x + "," + y + "," + z);
                    plugin.saveConfig();

                    String blockName = block.getType().name().toLowerCase();
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "Druhy blok " + blockName + " oznacen na X: " + x + " Y: " + y + " Z: " + z);
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TNTArena: " + ChatColor.RESET + "----------------------------------");

                }
            } else {
                if (item.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, "tntwand"), PersistentDataType.BOOLEAN)) {
                    Set<Material> materials = new HashSet<>();
                    materials.add(Material.AIR);
                    Block block = player.getTargetBlock(materials, item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "tntwanddistance"), PersistentDataType.INTEGER));
                    World world = player.getWorld();

                    float x, y, z;
                    x = block.getX();
                    y = block.getY()+1;
                    z = block.getZ();

                    TNTPrimed TNT = (TNTPrimed)world.spawnEntity(new Location(world, x, y, z), EntityType.PRIMED_TNT);
                    TNT.setFuseTicks(item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "tntwandfuse"), PersistentDataType.INTEGER)/50);
                }
            }
        }
    }
}
