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

public class CraftElvenSteelArmor implements Listener {

    @EventHandler
    public static void onCraftElvenSteelArmor(PrepareItemCraftEvent e) {

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

        // Elven Steel Helmet
        ItemManager.checkCraft(ItemManager.elvenSteelHelmet, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, ItemManager.elvenSteelIngot);
            put(1, ItemManager.elvenSteelIngot);
            put(2, ItemManager.elvenSteelIngot);
            put(3, ItemManager.elvenSteelIngot);
            put(4, null);
            put(5, ItemManager.elvenSteelIngot);
            put(6, null);
            put(7, null);
            put(8, null);
        }});

        // Elven Steel Chestplate
        ItemManager.checkCraft(ItemManager.elvenSteelChestplate, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, ItemManager.elvenSteelIngot);
            put(1, null);
            put(2, ItemManager.elvenSteelIngot);
            put(3, ItemManager.elvenSteelIngot);
            put(4, ItemManager.elvenSteelIngot);
            put(5, ItemManager.elvenSteelIngot);
            put(6, ItemManager.elvenSteelIngot);
            put(7, ItemManager.elvenSteelIngot);
            put(8, ItemManager.elvenSteelIngot);
        }});

        // Elven Steel Leggings
        ItemManager.checkCraft(ItemManager.elvenSteelLeggings, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, ItemManager.elvenSteelIngot);
            put(1, ItemManager.elvenSteelIngot);
            put(2, ItemManager.elvenSteelIngot);
            put(3, ItemManager.elvenSteelIngot);
            put(4, null);
            put(5, ItemManager.elvenSteelIngot);
            put(6, ItemManager.elvenSteelIngot);
            put(7, null);
            put(8, ItemManager.elvenSteelIngot);
        }});

        // Elven Steel Boots
        ItemManager.checkCraft(ItemManager.elvenSteelBoots, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, null);
            put(1, null);
            put(2, null);
            put(3, ItemManager.elvenSteelIngot);
            put(4, null);
            put(5, ItemManager.elvenSteelIngot);
            put(6, ItemManager.elvenSteelIngot);
            put(7, null);
            put(8, ItemManager.elvenSteelIngot);
        }});

        // Wood for shield
        ItemStack wood = new ItemStack(Material.OAK_PLANKS);
        // Elven Steel Shield
        ItemManager.checkCraft(ItemManager.elvenSteelShield, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, wood);
            put(1, ItemManager.elvenSteelIngot);
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
