package newinihatia.plughatia.events.steel;

import newinihatia.plughatia.items.ItemManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class CraftSteelArmor implements Listener {

    @EventHandler
    public static void onPrepareCraftSteelArmor(PrepareItemCraftEvent e) {
        if (e.getInventory().getMatrix().length < 9) {
            return;
        }

        // Steel Helmet
        ItemManager.checkCraft(ItemManager.steelHelmet, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, ItemManager.steelIngot);
            put(1, ItemManager.steelIngot);
            put(2, ItemManager.steelIngot);
            put(3, ItemManager.steelIngot);
            put(4, null);
            put(5, ItemManager.steelIngot);
            put(6, null);
            put(7, null);
            put(8, null);
        }});

        // Steel Chestplate
        ItemManager.checkCraft(ItemManager.steelChestplate, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, ItemManager.steelIngot);
            put(1, null);
            put(2, ItemManager.steelIngot);
            put(3, ItemManager.steelIngot);
            put(4, ItemManager.steelIngot);
            put(5, ItemManager.steelIngot);
            put(6, ItemManager.steelIngot);
            put(7, ItemManager.steelIngot);
            put(8, ItemManager.steelIngot);
        }});

        // Steel Leggings
        ItemManager.checkCraft(ItemManager.steelLeggings, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, ItemManager.steelIngot);
            put(1, ItemManager.steelIngot);
            put(2, ItemManager.steelIngot);
            put(3, ItemManager.steelIngot);
            put(4, null);
            put(5, ItemManager.steelIngot);
            put(6, ItemManager.steelIngot);
            put(7, null);
            put(8, ItemManager.steelIngot);
        }});

        // Steel Boots
        ItemManager.checkCraft(ItemManager.steelBoots, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, null);
            put(1, null);
            put(2, null);
            put(3, ItemManager.steelIngot);
            put(4, null);
            put(5, ItemManager.steelIngot);
            put(6, ItemManager.steelIngot);
            put(7, null);
            put(8, ItemManager.steelIngot);
        }});

        // Wood for shield
        ItemStack wood = new ItemStack(Material.OAK_PLANKS);
        // Steel Shield
        ItemManager.checkCraft(ItemManager.steelShield, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, wood);
            put(1, ItemManager.steelIngot);
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
