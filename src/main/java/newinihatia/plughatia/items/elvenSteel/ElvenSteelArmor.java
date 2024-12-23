package newinihatia.plughatia.items.elvenSteel;

import newinihatia.plughatia.items.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ElvenSteelArmor {

    public static void elvenSteelArmorInit()
    {
        ItemManager.elvenSteelHelmet = createElvenSteelHelmet();
        ItemManager.elvenSteelChestplate = createElvenSteelChestplate();
        ItemManager.elvenSteelLeggings = createElvenSteelLeggings();
        ItemManager.elvenSteelBoots = createElvenSteelBoots();
        ItemManager.elvenSteelShield = createElvenSteelShield();
    }

    public static ItemStack createElvenSteelHelmet() {
        ItemStack item = new ItemStack(Material.IRON_HELMET, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Elven Steel Helmet");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.addEnchant(Enchantment.PROTECTION, 3, false);
        meta.addEnchant(Enchantment.UNBREAKING, 3, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftElvenSteelArmor
    }

    public static ItemStack createElvenSteelChestplate() {
        ItemStack item = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Elven Steel Chestplate");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.addEnchant(Enchantment.PROTECTION, 3, false);
        meta.addEnchant(Enchantment.UNBREAKING, 3, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftElvenSteelArmor
    }

    public static ItemStack createElvenSteelLeggings() {
        ItemStack item = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Elven Steel Leggings");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.addEnchant(Enchantment.PROTECTION, 3, false);
        meta.addEnchant(Enchantment.UNBREAKING, 3, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftElvenSteelArmor
    }

    public static ItemStack createElvenSteelBoots() {
        ItemStack item = new ItemStack(Material.IRON_BOOTS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Elven Steel Boots");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.addEnchant(Enchantment.PROTECTION, 3, false);
        meta.addEnchant(Enchantment.UNBREAKING, 3, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftElvenSteelArmor
    }

    public static ItemStack createElvenSteelShield() {
        ItemStack item = new ItemStack(Material.SHIELD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Elven Steel Shield");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.addEnchant(Enchantment.UNBREAKING, 3, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftElvenSteelArmor
    }

}
