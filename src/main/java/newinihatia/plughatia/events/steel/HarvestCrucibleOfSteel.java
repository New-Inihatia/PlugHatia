package newinihatia.plughatia.events.steel;

import newinihatia.plughatia.items.ItemManager;
import newinihatia.plughatia.items.steel.CrucibleOfSteel;
import newinihatia.plughatia.items.steel.PigIron;
import newinihatia.plughatia.items.steel.SteelIngot;
import newinihatia.plughatia.objects.PlayerObj;
import newinihatia.plughatia.utils.PlayerStorageUtil;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class HarvestCrucibleOfSteel implements Listener {
    @EventHandler
    public static void onHarvestCrucibleOfSteel(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();


        if (inventory == null) {
            return;
        }
        if (inventory.getType() != InventoryType.BLAST_FURNACE) {
            return;
        }
        if (event.getSlotType() != InventoryType.SlotType.RESULT) {
            return;
        }
        if (!event.getCurrentItem().isSimilar(ItemManager.crucibleOfSteelHeatable.create(1, ItemManager.crucibleOfSteelHeatable.maxHeatedTemp, 0))) {
            return;
        }

        Inventory playerInv = player.getInventory();
        Random random = new Random();
        int bucketNum = random.nextInt(4);
        if (bucketNum > 0) {
            playerInv.addItem(new ItemStack(Material.BUCKET));
        }

        PlayerObj JSONPlayer = PlayerStorageUtil.findPlayer(player.getUniqueId());

        // Orcs will sometimes get Steel straight away
        if (JSONPlayer.getRace().equalsIgnoreCase("Orc")) {
            for (int i = 0; i < 2; i++) {
                if (random.nextInt(2) == 1) {
                    playerInv.addItem(ItemManager.steelIngotHeatable.create(1, ItemManager.steelIngotHeatable.maxHeatedTemp));
                } else {
                    playerInv.addItem(ItemManager.pigIronHeatable.create(1, ItemManager.pigIronHeatable.maxHeatedTemp));
                }
            }
        } else {
            playerInv.addItem(ItemManager.pigIronHeatable.create(2, ItemManager.pigIronHeatable.maxHeatedTemp));
        }

        event.setCurrentItem(null);
        event.setCancelled(true);
    }
}
