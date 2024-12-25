package newinihatia.plughatia.events;

import newinihatia.plughatia.PlugHatia;
import newinihatia.plughatia.items.Heatable;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;


public class ItemTemperatureControl implements Listener {

    private static final double TEMPERATURE_PER_SECOND = 7;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        ItemStack item = e.getCurrentItem();

        System.out.println(item);

        if (item == null) {
            return;
        }

        ItemMeta meta = item.getItemMeta();

        Inventory inventory = e.getClickedInventory();

        if (inventory != null) {
            if (inventory.getType() == InventoryType.BLAST_FURNACE) {
                if (e.getSlotType() == InventoryType.SlotType.CRAFTING) {
                    item = e.getCursor();
                    meta = item.getItemMeta();
                    if (meta.getPersistentDataContainer().has(new NamespacedKey(PlugHatia.getPlugin(), "temperature_since"), PersistentDataType.LONG)) {
                        System.out.println("getSlot item: " + item);
                        meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "temperature"), PersistentDataType.DOUBLE, 0.0);
                        meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "temperature_since"), PersistentDataType.LONG, (long) 0);
                        System.out.println("reset temp stuff for heating");
                        item.setItemMeta(meta);
                        e.getCursor().setItemMeta(meta);
                        return;
                    }
                }
            }
        }

        item = e.getCurrentItem();
        meta = item.getItemMeta();

        if (item.getType() == Material.AIR) {
            return;
        }
        if (!item.hasItemMeta()) {
            return;
        }



        World world = Bukkit.getServer().getWorlds().get(0);
        long gameTime = world.getGameTime();
        if (inventory != null) {
            if (inventory.getType() == InventoryType.BLAST_FURNACE) {
                if (e.getSlotType() == InventoryType.SlotType.RESULT) {
                    if (meta.getPersistentDataContainer().has(new NamespacedKey(PlugHatia.getPlugin(), "temperature_since"), PersistentDataType.LONG)) {
                        meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "temperature_since"), PersistentDataType.LONG, gameTime);
                        System.out.println("fixing temp since: " + gameTime);
                    }
                }
            }
        }

        item.setItemMeta(meta);

        item = adjustTemperature(item);

        e.setCurrentItem(item);
    }

    public static ItemStack adjustTemperature(ItemStack itemp) {

        if (itemp == null) {
            return itemp;
        }

        ItemStack item = itemp.clone();
        if (!item.hasItemMeta()) {
            return item;
        }


        ItemMeta meta = item.getItemMeta();

        if (meta.getPersistentDataContainer().has(new NamespacedKey(PlugHatia.getPlugin(), "temperature"), PersistentDataType.DOUBLE)) {
            item = Heatable.update(item);
        }
        return item;
    }
}
