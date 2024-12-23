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


//        ScoreboardManager manager = Bukkit.getScoreboardManager();
//        Scoreboard board = manager.getNewScoreboard();
//
//        List<Team> teams = new ArrayList<>();
//
//        Team hobbits = board.registerNewTeam("Hobbits");
//        hobbits.setAllowFriendlyFire(true);
//        hobbits.setCanSeeFriendlyInvisibles(true);
//        hobbits.setPrefix("Hobbit");
//        hobbits.setColor(ChatColor.YELLOW);
//        teams.add(hobbits);
//
//        Team elfs = board.registerNewTeam("Elfs");
//        elfs.setAllowFriendlyFire(true);
//        elfs.setCanSeeFriendlyInvisibles(true);
//        elfs.setDisplayName("Elfs");
//        elfs.setPrefix("Elf");
//        elfs.setColor(ChatColor.GREEN);
//        teams.add(elfs);
//
//        Team dwarfs = board.registerNewTeam("Dwarfs");
//        dwarfs.setAllowFriendlyFire(true);
//        dwarfs.setCanSeeFriendlyInvisibles(true);
//        dwarfs.setDisplayName("Dwarfs");
//        dwarfs.setPrefix("Dwarf");
//        dwarfs.setColor(ChatColor.DARK_RED);
//        teams.add(dwarfs);
//
//        Team orcs = board.registerNewTeam("Orcs");
//        orcs.setAllowFriendlyFire(true);
//        orcs.setCanSeeFriendlyInvisibles(true);
//        orcs.setDisplayName("Orcs");
//        orcs.setPrefix("Orc");
//        orcs.setColor(ChatColor.DARK_PURPLE);
//        teams.add(orcs);
//
//        Team humans = board.registerNewTeam("Humans");
//        humans.setAllowFriendlyFire(true);
//        humans.setCanSeeFriendlyInvisibles(true);
//        humans.setDisplayName("Humans");
//        humans.setPrefix("Human");
//        humans.setColor(ChatColor.AQUA);
//        teams.add(humans);

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
