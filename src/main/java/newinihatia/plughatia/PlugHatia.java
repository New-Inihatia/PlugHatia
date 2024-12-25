package newinihatia.plughatia;

import newinihatia.plughatia.commands.*;
import newinihatia.plughatia.events.*;
import newinihatia.plughatia.items.ItemManager;
import newinihatia.plughatia.utils.PlayerStorageUtil;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class PlugHatia extends JavaPlugin {

    private static PlugHatia plugin;
    public static Server server;

    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;

        EventManager.init();

        ItemManager.init();

        getCommand("setRace").setExecutor(new setRace());
        getCommand("speed").setExecutor(new speed());
        getCommand("leap").setExecutor(new leap());
        getCommand("setName").setExecutor(new setName());

        try {
            PlayerStorageUtil.loadPlayers();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "PlugHatia has started!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        getServer().getConsoleSender().sendMessage(ChatColor.RED + "PlugHatia has shut down!");

    }

    public static PlugHatia getPlugin() {
        return plugin;
    }

}
