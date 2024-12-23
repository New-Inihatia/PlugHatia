package newinihatia.plughatia.events.steel;

import newinihatia.plughatia.items.ItemManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class CraftSteelTools implements Listener {

    @EventHandler
    public static void onPrepareCraftSteelTools(PrepareItemCraftEvent e) {
        if (e.getInventory().getMatrix().length < 9) {
            return;
        }

        ItemStack stick = new ItemStack(Material.STICK);

        // Steel Sword
        ItemManager.checkCraft(ItemManager.steelSword, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, null);
            put(1, ItemManager.steelIngot);
            put(2, null);
            put(3, null);
            put(4, ItemManager.steelIngot);
            put(5, null);
            put(6, null);
            put(7, stick);
            put(8, null);
        }});

        // Steel Axe (Blade on left)
        boolean axeCrafted = ItemManager.checkCraft(ItemManager.steelAxe, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, ItemManager.steelIngot);
            put(1, ItemManager.steelIngot);
            put(2, null);
            put(3, ItemManager.steelIngot);
            put(4, stick);
            put(5, null);
            put(6, null);
            put(7, stick);
            put(8, null);
        }});

        // Steel Axe (Blade on right)
        if (axeCrafted == false) {
            ItemManager.checkCraft(ItemManager.steelAxe, e.getInventory(), new HashMap<Integer, ItemStack>() {{
                put(0, null);
                put(1, ItemManager.steelIngot);
                put(2, ItemManager.steelIngot);
                put(3, null);
                put(4, stick);
                put(5, ItemManager.steelIngot);
                put(6, null);
                put(7, stick);
                put(8, null);
            }});
        }

        // Steel Pickaxe
        ItemManager.checkCraft(ItemManager.steelPickaxe, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, ItemManager.steelIngot);
            put(1, ItemManager.steelIngot);
            put(2, ItemManager.steelIngot);
            put(3, null);
            put(4, stick);
            put(5, null);
            put(6, null);
            put(7, stick);
            put(8, null);
        }});

        // Steel Shovel
        ItemManager.checkCraft(ItemManager.steelShovel, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, null);
            put(1, ItemManager.steelIngot);
            put(2, null);
            put(3, null);
            put(4, stick);
            put(5, null);
            put(6, null);
            put(7, stick);
            put(8, null);
        }});

        // Steel Hoe (Blade on left)
        boolean hoeCrafted = ItemManager.checkCraft(ItemManager.steelHoe, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, ItemManager.steelIngot);
            put(1, ItemManager.steelIngot);
            put(2, null);
            put(3, null);
            put(4, stick);
            put(5, null);
            put(6, null);
            put(7, stick);
            put(8, null);
        }});

        if (!hoeCrafted) {
            // Steel Hoe (Blade on right)
            ItemManager.checkCraft(ItemManager.steelHoe, e.getInventory(), new HashMap<Integer, ItemStack>(){{
                put(0, null);
                put(1, ItemManager.steelIngot);
                put(2, ItemManager.steelIngot);
                put(3, null);
                put(4, stick);
                put(5, null);
                put(6, null);
                put(7, stick);
                put(8, null);
            }});
        }

        // Steel Spear
        ItemManager.checkCraft(ItemManager.steelSpear, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, null);
            put(1, null);
            put(2, ItemManager.steelIngot);
            put(3, null);
            put(4, stick);
            put(5, null);
            put(6, stick);
            put(7, null);
            put(8, null);
        }});
    }

}
