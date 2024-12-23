package newinihatia.plughatia.events.mithril;

import newinihatia.plughatia.items.ItemManager;
import newinihatia.plughatia.utils.PlayerStorageUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CraftMithrilIngot implements Listener {

    @EventHandler
    public static void onCraftMithrilIngot(PrepareItemCraftEvent e) {

        // Make sure crafter is a Dwarf
        List<HumanEntity> viewers = e.getViewers();
        for (HumanEntity temp : viewers) {
            Player p = Bukkit.getPlayer(temp.getName());
            UUID uuid = p.getUniqueId();
            if (!PlayerStorageUtil.findPlayer(uuid).getRace().equalsIgnoreCase("Dwarf")) {
                return;
            }
        }

        // If crafting GUI is not a crafting table
        if (e.getInventory().getMatrix().length < 9) {
            return;
        }

        ItemStack diamond = new ItemStack(Material.DIAMOND);

        // Mithril Ingot
        ItemManager.checkCraft(ItemManager.mithrilIngot, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, diamond);
            put(1, ItemManager.steelIngot);
            put(2, diamond);
            put(3, ItemManager.steelIngot);
            put(4, diamond);
            put(5, ItemManager.steelIngot);
            put(6, diamond);
            put(7, ItemManager.steelIngot);
            put(8, diamond);
        }});

    }

}
