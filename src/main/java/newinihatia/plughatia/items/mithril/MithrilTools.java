package newinihatia.plughatia.items.mithril;

import newinihatia.plughatia.items.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MithrilTools {

    public static void mithrilToolsInit() {
        ItemManager.mithrilSword = createMithrilSword();
        ItemManager.mithrilAxe = createMithrilAxe();
        ItemManager.mithrilPickaxe = createMithrilPickaxe();
        ItemManager.mithrilShovel = createMithrilShovel();
        ItemManager.mithrilHoe = createMithrilHoe();
    }

    public static ItemStack createMithrilSword() {
        ItemStack item = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Mithril Sword");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.addEnchant(Enchantment.SHARPNESS, 5, false);
        meta.addEnchant(Enchantment.UNBREAKING, 3, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftMithrilTools
    }

    public static ItemStack createMithrilAxe() {
        ItemStack item = new ItemStack(Material.IRON_AXE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Mithril Axe");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.addEnchant(Enchantment.EFFICIENCY, 5, false);
        meta.addEnchant(Enchantment.UNBREAKING, 3, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftMithrilTools
    }

    public static ItemStack createMithrilPickaxe() {
        ItemStack item = new ItemStack(Material.IRON_PICKAXE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Mithril Pickaxe");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.addEnchant(Enchantment.FORTUNE, 1, false);
        meta.addEnchant(Enchantment.EFFICIENCY, 5, false);
        meta.addEnchant(Enchantment.UNBREAKING, 3, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftMithrilTools
    }

    public static ItemStack createMithrilShovel() {
        ItemStack item = new ItemStack(Material.IRON_SHOVEL, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Mithril Shovel");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.addEnchant(Enchantment.EFFICIENCY, 5, false);
        meta.addEnchant(Enchantment.UNBREAKING, 3, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftMithrilTools
    }

    public static ItemStack createMithrilHoe() {
        ItemStack item = new ItemStack(Material.IRON_HOE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Mithril Hoe");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.addEnchant(Enchantment.EFFICIENCY, 5, false);
        meta.addEnchant(Enchantment.UNBREAKING, 3, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftMithrilTools
    }

}
