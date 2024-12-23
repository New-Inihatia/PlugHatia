package newinihatia.plughatia.events.elvenSteel;

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

public class CraftElvenSteelIngot implements Listener {

    @EventHandler
    public static void onCraftElvenSteelIngot(PrepareItemCraftEvent e) {

        // Make sure crafter is an Elf
        List<HumanEntity> viewers = e.getViewers();
        for (HumanEntity temp : viewers) {
            Player p = Bukkit.getPlayer(temp.getName());
            UUID uuid = p.getUniqueId();
            if (!PlayerStorageUtil.findPlayer(uuid).getRace().equalsIgnoreCase("Elf")) {
                return;
            }
        }

        // If crafting GUI is not a crafting table
        if (e.getInventory().getMatrix().length < 9) {
            return;
        }

        ItemStack emerald = new ItemStack(Material.EMERALD);

        // Elven Steel
        ItemManager.checkCraft(ItemManager.elvenSteelIngot, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, emerald);
            put(1, ItemManager.steelIngot);
            put(2, emerald);
            put(3, ItemManager.steelIngot);
            put(4, emerald);
            put(5, ItemManager.steelIngot);
            put(6, emerald);
            put(7, ItemManager.steelIngot);
            put(8, emerald);
        }});

    }

}
