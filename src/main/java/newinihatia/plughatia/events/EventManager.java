package newinihatia.plughatia.events;

import newinihatia.plughatia.PlugHatia;
import newinihatia.plughatia.events.elvenSteel.CraftElvenSteelArmor;
import newinihatia.plughatia.events.elvenSteel.CraftElvenSteelIngot;
import newinihatia.plughatia.events.elvenSteel.CraftElvenSteelTools;
import newinihatia.plughatia.events.mithril.CraftMithrilArmor;
import newinihatia.plughatia.events.mithril.CraftMithrilIngot;
import newinihatia.plughatia.events.mithril.CraftMithrilTools;
import newinihatia.plughatia.events.steel.CraftSteelArmor;
import newinihatia.plughatia.events.steel.CraftSteelTools;
import newinihatia.plughatia.events.steel.HarvestCrucibleOfSteel;
import newinihatia.plughatia.menus.MenuListener;
import org.bukkit.Bukkit;

import static org.bukkit.Bukkit.getServer;

public class EventManager {

    public static void init() {

        int maxPlayerCount = Bukkit.getServer().getMaxPlayers();
        HumanResilience.init(maxPlayerCount);

        BannedItems.init();

        getServer().getPluginManager().registerEvents(new PlayerJoin(), PlugHatia.getPlugin());

        getServer().getPluginManager().registerEvents(new PlayerChangeMode(), PlugHatia.getPlugin());

        getServer().getPluginManager().registerEvents(new PlayerSneak(), PlugHatia.getPlugin());

        getServer().getPluginManager().registerEvents(new CraftSteelArmor(), PlugHatia.getPlugin());
        getServer().getPluginManager().registerEvents(new CraftSteelTools(), PlugHatia.getPlugin());

        getServer().getPluginManager().registerEvents(new BannedItems(), PlugHatia.getPlugin());

        getServer().getPluginManager().registerEvents(new CraftElvenSteelIngot(), PlugHatia.getPlugin());
        getServer().getPluginManager().registerEvents(new CraftElvenSteelArmor(), PlugHatia.getPlugin());
        getServer().getPluginManager().registerEvents(new CraftElvenSteelTools(), PlugHatia.getPlugin());

        getServer().getPluginManager().registerEvents(new MineHardBlock(), PlugHatia.getPlugin());

        getServer().getPluginManager().registerEvents(new CraftMithrilIngot(), PlugHatia.getPlugin());
        getServer().getPluginManager().registerEvents(new CraftMithrilArmor(), PlugHatia.getPlugin());
        getServer().getPluginManager().registerEvents(new CraftMithrilTools(), PlugHatia.getPlugin());

        getServer().getPluginManager().registerEvents(new PlayerRespawn(), PlugHatia.getPlugin());

        getServer().getPluginManager().registerEvents(new HumanResilience(), PlugHatia.getPlugin());

        getServer().getPluginManager().registerEvents(new HobbitEquipBoots(), PlugHatia.getPlugin());

        getServer().getPluginManager().registerEvents(new MobNeutralityTowardsOrcs(), PlugHatia.getPlugin());

        getServer().getPluginManager().registerEvents(new SpearThrow(), PlugHatia.getPlugin());

        getServer().getPluginManager().registerEvents(new MenuListener(), PlugHatia.getPlugin());

        getServer().getPluginManager().registerEvents(new AnvilUse(), PlugHatia.getPlugin());

        getServer().getPluginManager().registerEvents(new HarvestCrucibleOfSteel(), PlugHatia.getPlugin());

        getServer().getPluginManager().registerEvents(new ItemTemperatureControl(), PlugHatia.getPlugin());

    }

}
