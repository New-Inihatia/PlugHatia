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

public class CraftElvenSteelTools implements Listener {

    @EventHandler
    public static void onCraftElvenSteelTools(PrepareItemCraftEvent e) {

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

        ItemStack stick = new ItemStack(Material.STICK);

        // Elf Sword
        ItemManager.checkCraft(ItemManager.elvenSteelSword, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, null);
            put(1, ItemManager.elvenSteelIngot);
            put(2, null);
            put(3, null);
            put(4, ItemManager.elvenSteelIngot);
            put(5, null);
            put(6, null);
            put(7, stick);
            put(8, null);
        }});

        // Elf Axe (Blade on left)
        boolean axeCrafted = ItemManager.checkCraft(ItemManager.elvenSteelAxe, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, ItemManager.elvenSteelIngot);
            put(1, ItemManager.elvenSteelIngot);
            put(2, null);
            put(3, ItemManager.elvenSteelIngot);
            put(4, stick);
            put(5, null);
            put(6, null);
            put(7, stick);
            put(8, null);
        }});

        // Elf Axe (Blade on right)
        if (axeCrafted == false) {
            ItemManager.checkCraft(ItemManager.elvenSteelAxe, e.getInventory(), new HashMap<Integer, ItemStack>() {{
                put(0, null);
                put(1, ItemManager.elvenSteelIngot);
                put(2, ItemManager.elvenSteelIngot);
                put(3, null);
                put(4, stick);
                put(5, ItemManager.elvenSteelIngot);
                put(6, null);
                put(7, stick);
                put(8, null);
            }});
        }

        // Elf Pickaxe
        ItemManager.checkCraft(ItemManager.elvenSteelPickaxe, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, ItemManager.elvenSteelIngot);
            put(1, ItemManager.elvenSteelIngot);
            put(2, ItemManager.elvenSteelIngot);
            put(3, null);
            put(4, stick);
            put(5, null);
            put(6, null);
            put(7, stick);
            put(8, null);
        }});

        // Elf Shovel
        ItemManager.checkCraft(ItemManager.elvenSteelShovel, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, null);
            put(1, ItemManager.elvenSteelIngot);
            put(2, null);
            put(3, null);
            put(4, stick);
            put(5, null);
            put(6, null);
            put(7, stick);
            put(8, null);
        }});

        // Elf Hoe (Blade on left)
        boolean hoeCrafted = ItemManager.checkCraft(ItemManager.elvenSteelHoe, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, ItemManager.elvenSteelIngot);
            put(1, ItemManager.elvenSteelIngot);
            put(2, null);
            put(3, null);
            put(4, stick);
            put(5, null);
            put(6, null);
            put(7, stick);
            put(8, null);
        }});

        if (hoeCrafted == false) {
            // Elf Hoe (Blade on right)
            ItemManager.checkCraft(ItemManager.elvenSteelHoe, e.getInventory(), new HashMap<Integer, ItemStack>(){{
                put(0, null);
                put(1, ItemManager.elvenSteelIngot);
                put(2, ItemManager.elvenSteelIngot);
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
