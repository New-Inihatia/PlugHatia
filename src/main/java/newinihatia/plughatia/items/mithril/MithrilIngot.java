package newinihatia.plughatia.items.mithril;

import newinihatia.plughatia.PlugHatia;
import org.bukkit.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class MithrilIngot {

    public static ItemStack createMithrilIngot(int amount, double temperature) {
        ItemStack item = new ItemStack(Material.IRON_INGOT, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Mithril Ingot");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);

        // temperature management
        meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "temperature"), PersistentDataType.DOUBLE, temperature);
        World world = Bukkit.getServer().getWorlds().get(0);
        meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "temperature_since"), PersistentDataType.LONG, world.getFullTime());
        if (temperature >= 2500) {
            meta.setCustomModelData(6);
        } else {
            meta.setCustomModelData(5);
        }

        item.setItemMeta(meta);
        return item;

        // Recipe handled by events.craftMithrilIngot

    }

    public static ItemStack createMithrilIngot(int amount, double temperature, long temperatureSince) {
        ItemStack item = new ItemStack(Material.IRON_INGOT, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Mithril Ingot");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);

        // temperature management
        meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "temperature"), PersistentDataType.DOUBLE, temperature);
        World world = Bukkit.getServer().getWorlds().get(0);
        meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "temperature_since"), PersistentDataType.LONG, temperatureSince);
        if (temperature >= 2500) {
            meta.setCustomModelData(6);
        } else {
            meta.setCustomModelData(5);
        }

        item.setItemMeta(meta);
        return item;

        // Recipe handled by events.craftMithrilIngot

    }

}
