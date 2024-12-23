package newinihatia.plughatia.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

import static newinihatia.plughatia.items.Flux.createFlux;
import static newinihatia.plughatia.items.IronHammer.createIronHammer;
import static newinihatia.plughatia.items.elvenSteel.ElvenSteelArmor.elvenSteelArmorInit;
import static newinihatia.plughatia.items.elvenSteel.ElvenSteelIngot.createElvenSteelIngot;
import static newinihatia.plughatia.items.elvenSteel.ElvenSteelTools.elvenSteelToolsInit;
import static newinihatia.plughatia.items.mithril.MithrilArmor.mithrilArmorInit;
import static newinihatia.plughatia.items.mithril.MithrilIngot.createMithrilIngot;
import static newinihatia.plughatia.items.mithril.MithrilTools.mithrilToolsInit;
import static newinihatia.plughatia.items.steel.SteelArmor.steelArmorInit;;
import static newinihatia.plughatia.items.steel.SteelIngot.createSteelIngot;
import static newinihatia.plughatia.items.steel.SteelTools.steelToolsInit;

// for future reference https://minecraft.fandom.com/wiki/Attribute#Operations

public class ItemManager {

    public static ItemStack ironHammer;

    public static ItemStack flux;

    /* Base materials */
    public static ItemStack steelIngot;
    public static ItemStack elvenSteelIngot;
    public static ItemStack mithrilIngot;

    /* Steel Items */

    // Steel Armor
    public static ItemStack steelHelmet;
    public static ItemStack steelChestplate;
    public static ItemStack steelLeggings;
    public static ItemStack steelBoots;
    public static ItemStack steelShield;

    // Steel Tools
    public static ItemStack steelSword;
    public static ItemStack steelAxe;
    public static ItemStack steelPickaxe;
    public static ItemStack steelShovel;
    public static ItemStack steelHoe;
    public static ItemStack steelSpear;

    /* Elven Steel Items */

    // Elven Steel Armor
    public static ItemStack elvenSteelHelmet;
    public static ItemStack elvenSteelChestplate;
    public static ItemStack elvenSteelLeggings;
    public static ItemStack elvenSteelBoots;
    public static ItemStack elvenSteelShield;

    // Elven Steel Tools
    public static ItemStack elvenSteelSword;
    public static ItemStack elvenSteelAxe;
    public static ItemStack elvenSteelPickaxe;
    public static ItemStack elvenSteelShovel;
    public static ItemStack elvenSteelHoe;

    /* Mithril Items */

    // Mithril Armor
    public static ItemStack mithrilHelmet;
    public static ItemStack mithrilChestplate;
    public static ItemStack mithrilLeggings;
    public static ItemStack mithrilBoots;
    public static ItemStack mithrilShield;

    // Mithril Tools
    public static ItemStack mithrilSword;
    public static ItemStack mithrilAxe;
    public static ItemStack mithrilPickaxe;
    public static ItemStack mithrilShovel;
    public static ItemStack mithrilHoe;

    public static void init() {
        ironHammer = createIronHammer(1);

        flux = createFlux(1);

        steelIngot = createSteelIngot(1, 0, 0);
        Bukkit.addRecipe(new BlastingRecipe(NamespacedKey.minecraft("steel_ingot"), steelIngot, Material.IRON_INGOT, 1.0f, 15 * 20));
        steelArmorInit();
        steelToolsInit();

        elvenSteelIngot = createElvenSteelIngot(1, 0, 0);
        elvenSteelArmorInit();
        elvenSteelToolsInit();

        mithrilIngot = createMithrilIngot(1, 0, 0);
        mithrilArmorInit();
        mithrilToolsInit();
    }

    public static boolean checkCraft(ItemStack result, CraftingInventory inv, HashMap<Integer, ItemStack> ingredients) {
        ItemStack[] matrix = inv.getMatrix();
        try {
            // If result equals item being checked
            if (inv.getResult().equals(result)) {
                inv.setResult(null);
            }
        } catch (Exception ex) {
            // If inv.getResult() == null
        }
        for (int i = 0; i < 9; i++) {
            if (ingredients.containsKey(i)) {
                if (matrix[i] == null && ingredients.get(i) == null) {

                }
                else if (matrix[i] == null || !matrix[i].isSimilar(ingredients.get(i))) {
                    return false;
                }
            } else if (matrix[i] != null) {
                return false;
            }

        }

        inv.setResult(result);
        return true;
    }

    public static boolean areEquivalent(ItemStack item1, ItemStack item2) {
        if (item1.getType() != item2.getType()) {
            return false;
        }

        if (item1.getItemMeta().hasCustomModelData() == item2.getItemMeta().hasCustomModelData()) {
            if (item1.getItemMeta().hasCustomModelData()) {
                if (item1.getItemMeta().getCustomModelData() != item2.getItemMeta().getCustomModelData()) {
                    return false;
                }
            }
        }
        else {
            return false;
        }
        return true;
    }

}

