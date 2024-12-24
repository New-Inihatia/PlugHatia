package newinihatia.plughatia.items;

import newinihatia.plughatia.PlugHatia;
import org.bukkit.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Heatable {

    public String heatableName;
    public double maxHeatedTemp;
    public double minHeatedTemp;
    public int defaultStackSize;
    public int hotStackSize;
    public int regularCustomModelData;
    public int hotCustomModelData;

    private final static double TEMPERATURE_PER_SECOND = 7;

    protected ItemStack item;

    public ItemStack init() {
        variablesInit();
        itemInit();
        ItemStack itemInstance = create(1, 0, 0);
        return itemInstance;
    }

    protected abstract void variablesInit();
    protected abstract void itemInit();
    public abstract void recipesInit();

    public static ItemStack update(ItemStack itemInstance) {
        System.out.println("Updating item: " + itemInstance);
        World world = Bukkit.getServer().getWorlds().get(0);
        long gameTime = world.getGameTime();

        ItemStack itemInstanceClone = itemInstance.clone();
        ItemMeta meta = itemInstanceClone.getItemMeta();

        double temperature = meta.getPersistentDataContainer().get(new NamespacedKey(PlugHatia.getPlugin(), "temperature"), PersistentDataType.DOUBLE);
        if (temperature > 0) {
            if (meta.getPersistentDataContainer().has(new NamespacedKey(PlugHatia.getPlugin(), "temperature_since"), PersistentDataType.LONG)) {
                long temperatureSince = meta.getPersistentDataContainer().get(new NamespacedKey(PlugHatia.getPlugin(), "temperature_since"), PersistentDataType.LONG);
                long timePassedSeconds = (gameTime - temperatureSince) / 20; // assuming 20 ticks per second
                meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "temperature"), PersistentDataType.DOUBLE, Math.max(temperature - timePassedSeconds * TEMPERATURE_PER_SECOND, 0));
                meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "temperature_since"), PersistentDataType.LONG, gameTime);
                List<String> lore = new ArrayList<>();
                lore.add("Temperature: " + meta.getPersistentDataContainer().get(new NamespacedKey(PlugHatia.getPlugin(), "temperature"), PersistentDataType.DOUBLE));
                meta.setLore(lore);
            }
        } else {
            if (meta.getPersistentDataContainer().has(new NamespacedKey(PlugHatia.getPlugin(), "temperature_since"), PersistentDataType.LONG)) {
                meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "temperature"), PersistentDataType.DOUBLE, 0.0);
                meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "temperature_since"), PersistentDataType.LONG, (long) 0);
                List<String> lore = new ArrayList<>();
                lore.add("Temperature: " + meta.getPersistentDataContainer().get(new NamespacedKey(PlugHatia.getPlugin(), "temperature"), PersistentDataType.DOUBLE));
                meta.setLore(lore);
            }
        }

        temperature = meta.getPersistentDataContainer().get(new NamespacedKey(PlugHatia.getPlugin(), "temperature"), PersistentDataType.DOUBLE);
        String instanceName = meta.getPersistentDataContainer().get(new NamespacedKey(PlugHatia.getPlugin(), "heatable_name"), PersistentDataType.STRING);
        Heatable heatable = ItemManager.heatables.get(instanceName);

        if (temperature >= heatable.minHeatedTemp) {
            meta.setCustomModelData(heatable.hotCustomModelData);
            meta.setMaxStackSize(heatable.hotStackSize);
        } else {
            meta.setCustomModelData(heatable.regularCustomModelData);
            meta.setMaxStackSize(heatable.defaultStackSize);
        }
        itemInstanceClone.setItemMeta(meta);

        return itemInstanceClone;
    }

    public ItemStack create(int amount, double temperature) {
        World world = Bukkit.getServer().getWorlds().get(0);
        return create(amount, temperature, world.getGameTime());
    }

    public ItemStack create(int amount, double temperature, long temperatureSince) {
        ItemStack itemInstance = item.clone();
        itemInstance.setAmount(amount);
        ItemMeta meta = itemInstance.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "temperature"), PersistentDataType.DOUBLE, temperature);
        meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "temperature_since"), PersistentDataType.LONG, temperatureSince);
        if (temperature >= minHeatedTemp) {
            meta.setCustomModelData(hotCustomModelData);
            meta.setMaxStackSize(hotStackSize);
        } else {
            meta.setCustomModelData(regularCustomModelData);
            meta.setMaxStackSize(defaultStackSize);
            System.out.println("Set stack size to default stack size: " + defaultStackSize);
        }
        List<String> lore = new ArrayList<>();
        lore.add("Temperature: " + temperature);
        meta.setLore(lore);
        itemInstance.setItemMeta(meta);

        return itemInstance;
    }
}
