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

public class CraftMithrilTools implements Listener {

    @EventHandler
    public static void onCraftMithrilTools(PrepareItemCraftEvent e) {

        // Make sure crafter is an Dwarf
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

        ItemStack stick = new ItemStack(Material.STICK);

        // Mithril Sword
        ItemManager.checkCraft(ItemManager.mithrilSword, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, null);
            put(1, ItemManager.mithrilIngot);
            put(2, null);
            put(3, null);
            put(4, ItemManager.mithrilIngot);
            put(5, null);
            put(6, null);
            put(7, stick);
            put(8, null);
        }});

        // Mithril Axe (Blade on left)
        boolean axeCrafted = ItemManager.checkCraft(ItemManager.mithrilAxe, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, ItemManager.mithrilIngot);
            put(1, ItemManager.mithrilIngot);
            put(2, null);
            put(3, ItemManager.mithrilIngot);
            put(4, stick);
            put(5, null);
            put(6, null);
            put(7, stick);
            put(8, null);
        }});

        // Mithril Axe (Blade on right)
        if (axeCrafted == false) {
            ItemManager.checkCraft(ItemManager.mithrilAxe, e.getInventory(), new HashMap<Integer, ItemStack>() {{
                put(0, null);
                put(1, ItemManager.mithrilIngot);
                put(2, ItemManager.mithrilIngot);
                put(3, null);
                put(4, stick);
                put(5, ItemManager.mithrilIngot);
                put(6, null);
                put(7, stick);
                put(8, null);
            }});
        }

        // Mithril Pickaxe
        ItemManager.checkCraft(ItemManager.mithrilPickaxe, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, ItemManager.mithrilIngot);
            put(1, ItemManager.mithrilIngot);
            put(2, ItemManager.mithrilIngot);
            put(3, null);
            put(4, stick);
            put(5, null);
            put(6, null);
            put(7, stick);
            put(8, null);
        }});

        // Mithril Shovel
        ItemManager.checkCraft(ItemManager.mithrilShovel, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, null);
            put(1, ItemManager.mithrilIngot);
            put(2, null);
            put(3, null);
            put(4, stick);
            put(5, null);
            put(6, null);
            put(7, stick);
            put(8, null);
        }});

        // Mithril Hoe (Blade on left)
        boolean hoeCrafted = ItemManager.checkCraft(ItemManager.mithrilHoe, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, ItemManager.mithrilIngot);
            put(1, ItemManager.mithrilIngot);
            put(2, null);
            put(3, null);
            put(4, stick);
            put(5, null);
            put(6, null);
            put(7, stick);
            put(8, null);
        }});

        if (hoeCrafted == false) {
            // Mithril Hoe (Blade on right)
            ItemManager.checkCraft(ItemManager.mithrilHoe, e.getInventory(), new HashMap<Integer, ItemStack>(){{
                put(0, null);
                put(1, ItemManager.mithrilIngot);
                put(2, ItemManager.mithrilIngot);
                put(3, null);
                put(4, stick);
                put(5, null);
                put(6, null);
                put(7, stick);
                put(8, null);
            }});
        }

    }

}
