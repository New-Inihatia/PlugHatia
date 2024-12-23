package newinihatia.plughatia.items.steel;

import newinihatia.plughatia.items.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SteelArmor {

    public static void steelArmorInit()
    {
        ItemManager.steelHelmet = createSteelHelmet();
        ItemManager.steelChestplate = createSteelChestplate();
        ItemManager.steelLeggings = createSteelLeggings();
        ItemManager.steelBoots = createSteelBoots();
        ItemManager.steelShield = createSteelShield();
    }

    public static ItemStack createSteelHelmet() {
        ItemStack item = new ItemStack(Material.IRON_HELMET, 1);
        Damageable meta = (Damageable) item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Steel Helmet");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.setMaxDamage(330);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armor_toughness", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftSteelArmor
    }

    public static ItemStack createSteelChestplate() {
        ItemStack item = new ItemStack(Material.IRON_CHESTPLATE, 1);
        Damageable meta = (Damageable) item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Steel Chestplate");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.setMaxDamage(480);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 7, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armor_toughness", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftSteelArmor
    }

    public static ItemStack createSteelLeggings() {
        ItemStack item = new ItemStack(Material.IRON_LEGGINGS, 1);
        Damageable meta = (Damageable) item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Steel Leggings");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.setMaxDamage(450);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 6, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS));
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armor_toughness", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS));
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftSteelArmor
    }

    public static ItemStack createSteelBoots() {
        ItemStack item = new ItemStack(Material.IRON_BOOTS, 1);
        Damageable meta = (Damageable) item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Steel Boots");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.setMaxDamage(390);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET));
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armor_toughness", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET));
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftSteelArmor
    }

    public static ItemStack createSteelShield() {
        ItemStack item = new ItemStack(Material.SHIELD, 1);
        Damageable meta = (Damageable) item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Steel Shield");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.setMaxDamage(504);
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftSteelArmor
    }

}
