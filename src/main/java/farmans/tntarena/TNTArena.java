package farmans.tntarena;

import farmans.tntarena.commands.*;
import farmans.tntarena.listeners.BreakBlocks;
import farmans.tntarena.listeners.RightClickBlock;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class TNTArena extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("TNTArena bezi.");

        File file = new File(getDataFolder() + File.separator + "config.yml");
        if (!file.exists()) {
            getConfig().addDefault("Coords1", "x,x,x");
            getConfig().addDefault("Coords2", "x,x,x");
            getConfig().addDefault("Delay", 3000);
            getConfig().addDefault("TaskID", -1);
            getConfig().addDefault("Fuse", 2500);
            getConfig().options().copyDefaults(true);
            saveConfig();
        } else {
            getConfig().set("Coords1", "x,x,x");
            getConfig().set("Coords2", "x,x,x");
            getConfig().set("Delay", 3000);
            getConfig().set("TaskID", -1);
            getConfig().set("Fuse", 2500);
            saveConfig();
            reloadConfig();
        }

        getCommand("tntsetup").setExecutor(new SetupCommand(this));
        getCommand("tntstart").setExecutor(new StartCommand(this));
        getCommand("tntinfo").setExecutor(new InfoCommand(this));
        getCommand("tntstop").setExecutor(new StopCommand(this));
        getCommand("tntdelay").setExecutor(new DelayCommand(this));
        getCommand("tntfuse").setExecutor(new FuseCommand(this));

        getServer().getPluginManager().registerEvents(new BreakBlocks(this), this);
        getServer().getPluginManager().registerEvents(new RightClickBlock(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("TNTArena je pozastaveno.");
    }

}
