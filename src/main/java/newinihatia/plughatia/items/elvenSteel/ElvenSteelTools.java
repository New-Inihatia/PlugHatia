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

public class ElvenSteelTools {

    public static void elvenSteelToolsInit() {
        ItemManager.elvenSteelSword = createElvenSteelSword();
        ItemManager.elvenSteelAxe = createElvenSteelAxe();
        ItemManager.elvenSteelPickaxe = createElvenSteelPickaxe();
        ItemManager.elvenSteelShovel = createElvenSteelShovel();
        ItemManager.elvenSteelHoe = createElvenSteelHoe();
    }

    public static ItemStack createElvenSteelSword() {
        ItemStack item = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Elven Steel Sword");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.addEnchant(Enchantment.SHARPNESS, 3, false);
        meta.addEnchant(Enchantment.UNBREAKING, 3, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftElvenSteelTools
    }

    public static ItemStack createElvenSteelAxe() {
        ItemStack item = new ItemStack(Material.IRON_AXE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Elven Steel Axe");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.addEnchant(Enchantment.EFFICIENCY, 3, false);
        meta.addEnchant(Enchantment.UNBREAKING, 3, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftElvenSteelTools
    }

    public static ItemStack createElvenSteelPickaxe() {
        ItemStack item = new ItemStack(Material.IRON_PICKAXE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Elven Steel Pickaxe");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.addEnchant(Enchantment.EFFICIENCY, 3, false);
        meta.addEnchant(Enchantment.UNBREAKING, 3, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftElvenSteelTools
    }

    public static ItemStack createElvenSteelShovel() {
        ItemStack item = new ItemStack(Material.IRON_SHOVEL, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Elven Steel Shovel");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.addEnchant(Enchantment.EFFICIENCY, 3, false);
        meta.addEnchant(Enchantment.UNBREAKING, 3, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftElvenSteelTools
    }

    public static ItemStack createElvenSteelHoe() {
        ItemStack item = new ItemStack(Material.IRON_HOE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Elven Steel Hoe");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.addEnchant(Enchantment.EFFICIENCY, 3, false);
        meta.addEnchant(Enchantment.UNBREAKING, 3, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftElvenSteelTools
    }

}
