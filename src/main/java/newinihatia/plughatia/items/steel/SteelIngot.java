package newinihatia.plughatia.items.steel;

import newinihatia.plughatia.PlugHatia;
import org.bukkit.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class SteelIngot {

    public static ItemStack createSteelIngot(int amount, double temperature) {
        ItemStack item = new ItemStack(Material.IRON_INGOT, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Steel Ingot");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "temperature"), PersistentDataType.DOUBLE, temperature);
        World world = Bukkit.getServer().getWorlds().get(0);
        meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "temperature_since"), PersistentDataType.LONG, world.getGameTime());
        if (temperature >= 1000) {
            meta.setCustomModelData(2);
        } else {
            meta.setCustomModelData(1);
        }
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createSteelIngot(int amount, double temperature, long temperatureSince) {
        ItemStack item = new ItemStack(Material.IRON_INGOT, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Steel Ingot");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "temperature"), PersistentDataType.DOUBLE, temperature);
        meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "temperature_since"), PersistentDataType.LONG, temperatureSince);
        if (temperature >= 1000) {
            meta.setCustomModelData(2);
        } else {
            meta.setCustomModelData(1);
        }
        item.setItemMeta(meta);
        return item;
    }

}
