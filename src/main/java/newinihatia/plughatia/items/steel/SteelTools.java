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

public class SteelTools {

    private static final double steelMiningEfficiencyBonus = 1.5;

    public static void steelToolsInit() {
        ItemManager.steelSword = createSteelSword();
        ItemManager.steelAxe = createSteelAxe();
        ItemManager.steelPickaxe = createSteelPickaxe();
        ItemManager.steelShovel = createSteelShovel();
        ItemManager.steelHoe = createSteelHoe();
        ItemManager.steelSpear = createSteelSpear();
    }

    public static ItemStack createSteelSword() {
        ItemStack item = new ItemStack(Material.IRON_SWORD, 1);
        Damageable meta = (Damageable) item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Steel Sword");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.setMaxDamage(500);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attack_damage", 5.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", -2.4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftSteelTools
    }

    public static ItemStack createSteelAxe() {
        ItemStack item = new ItemStack(Material.IRON_AXE, 1);
        Damageable meta = (Damageable) item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Steel Axe");
        List<String> lore = new ArrayList<>();

        meta.setLore(lore);
        meta.setMaxDamage(502);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attack_damage", 8.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", -3.1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        meta.addAttributeModifier(Attribute.PLAYER_MINING_EFFICIENCY, new AttributeModifier(UUID.randomUUID(), "player_mining_efficiency", steelMiningEfficiencyBonus, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftSteelTools
    }

    public static ItemStack createSteelPickaxe() {
        ItemStack item = new ItemStack(Material.IRON_PICKAXE, 1);
        Damageable meta = (Damageable) item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Steel Pickaxe");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.setMaxDamage(500);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attack_damage", 3.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", -2.8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        meta.addAttributeModifier(Attribute.PLAYER_MINING_EFFICIENCY, new AttributeModifier(UUID.randomUUID(), "player_mining_efficiency", steelMiningEfficiencyBonus, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftSteelTools
    }

    public static ItemStack createSteelShovel() {
        ItemStack item = new ItemStack(Material.IRON_SHOVEL, 1);
        Damageable meta = (Damageable) item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Steel Shovel");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.setMaxDamage(500);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attack_damage", 4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", -3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        meta.addAttributeModifier(Attribute.PLAYER_MINING_EFFICIENCY, new AttributeModifier(UUID.randomUUID(), "player_mining_efficiency", steelMiningEfficiencyBonus, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftSteelTools
    }

    public static ItemStack createSteelHoe() {
        ItemStack item = new ItemStack(Material.IRON_HOE, 1);
        Damageable meta = (Damageable) item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Steel Hoe");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.setMaxDamage(500);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attack_damage", 0.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", -1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        meta.addAttributeModifier(Attribute.PLAYER_MINING_EFFICIENCY, new AttributeModifier(UUID.randomUUID(), "player_mining_efficiency", steelMiningEfficiencyBonus, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftSteelTools
    }

    public static ItemStack createSteelSpear() {
        ItemStack item = new ItemStack(Material.TRIDENT, 1);
        Damageable meta = (Damageable) item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Steel Spear");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.setMaxDamage(75);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attack_damage", 6, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", -3.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        meta.addAttributeModifier(Attribute.PLAYER_ENTITY_INTERACTION_RANGE, new AttributeModifier(UUID.randomUUID(), "player_entity_interaction_range", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        item.setItemMeta(meta);
        return item;

        // Crafting recipe in events.craftSteelTools
    }

}
