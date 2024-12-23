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

public class CraftMithrilArmor implements Listener {

    @EventHandler
    public static void onPrepareCraftMithrilArmor(PrepareItemCraftEvent e) {
        if (e.getInventory().getMatrix().length < 9) {
            return;
        }

        // Make sure crafter is a Dwarf
        List<HumanEntity> viewers = e.getViewers();
        for (HumanEntity temp : viewers) {
            Player p = Bukkit.getPlayer(temp.getName());
            UUID uuid = p.getUniqueId();
            if (!PlayerStorageUtil.findPlayer(uuid).getRace().equalsIgnoreCase("Dwarf")) {
                return;
            }
        }

        // Mithril Helmet
        ItemManager.checkCraft(ItemManager.mithrilHelmet, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, ItemManager.mithrilIngot);
            put(1, ItemManager.mithrilIngot);
            put(2, ItemManager.mithrilIngot);
            put(3, ItemManager.mithrilIngot);
            put(4, null);
            put(5, ItemManager.mithrilIngot);
            put(6, null);
            put(7, null);
            put(8, null);
        }});

        // Mithril Chestplate
        ItemManager.checkCraft(ItemManager.mithrilChestplate, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, ItemManager.mithrilIngot);
            put(1, null);
            put(2, ItemManager.mithrilIngot);
            put(3, ItemManager.mithrilIngot);
            put(4, ItemManager.mithrilIngot);
            put(5, ItemManager.mithrilIngot);
            put(6, ItemManager.mithrilIngot);
            put(7, ItemManager.mithrilIngot);
            put(8, ItemManager.mithrilIngot);
        }});

        // Mithril Leggings
        ItemManager.checkCraft(ItemManager.mithrilLeggings, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, ItemManager.mithrilIngot);
            put(1, ItemManager.mithrilIngot);
            put(2, ItemManager.mithrilIngot);
            put(3, ItemManager.mithrilIngot);
            put(4, null);
            put(5, ItemManager.mithrilIngot);
            put(6, ItemManager.mithrilIngot);
            put(7, null);
            put(8, ItemManager.mithrilIngot);
        }});

        // Steel Boots
        ItemManager.checkCraft(ItemManager.mithrilBoots, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, null);
            put(1, null);
            put(2, null);
            put(3, ItemManager.mithrilIngot);
            put(4, null);
            put(5, ItemManager.mithrilIngot);
            put(6, ItemManager.mithrilIngot);
            put(7, null);
            put(8, ItemManager.mithrilIngot);
        }});

        // Wood for shield
        ItemStack wood = new ItemStack(Material.OAK_PLANKS);
        // Steel Shield
        ItemManager.checkCraft(ItemManager.mithrilShield, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, wood);
            put(1, ItemManager.mithrilIngot);
            put(2, wood);
            put(3, wood);
            put(4, wood);
            put(5, wood);
            put(6, null);
            put(7, wood);
            put(8, null);
        }});
    }

}
