package newinihatia.plughatia.items.elvenSteel;

import newinihatia.plughatia.PlugHatia;
import org.bukkit.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ElvenSteelIngot {

    public static ItemStack createElvenSteelIngot(int amount, double temperature) {
        ItemStack item = new ItemStack(Material.IRON_INGOT, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Elven Steel Ingot");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);

        // temperature management
        meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "temperature"), PersistentDataType.DOUBLE, temperature);
        World world = Bukkit.getServer().getWorlds().get(0);
        meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "temperature_since"), PersistentDataType.LONG, world.getGameTime());
        if (temperature >= 1700) {
            meta.setCustomModelData(4);
        } else {
            meta.setCustomModelData(3);
        }

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createElvenSteelIngot(int amount, double temperature, long temperatureSince) {
        ItemStack item = new ItemStack(Material.IRON_INGOT, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Elven Steel Ingot");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);

        // temperature management
        meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "temperature"), PersistentDataType.DOUBLE, temperature);
        World world = Bukkit.getServer().getWorlds().get(0);
        meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "temperature_since"), PersistentDataType.LONG, temperatureSince);
        if (temperature >= 1700) {
            meta.setCustomModelData(4);
        } else {
            meta.setCustomModelData(3);
        }

        item.setItemMeta(meta);
        return item;
    }

}
