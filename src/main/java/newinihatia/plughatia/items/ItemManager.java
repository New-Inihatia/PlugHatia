package newinihatia.plughatia.items;

import newinihatia.plughatia.PlugHatia;
import newinihatia.plughatia.items.steel.CrucibleOfSteel;
import newinihatia.plughatia.items.steel.DoubleSteelIngot;
import newinihatia.plughatia.items.steel.PigIron;
import newinihatia.plughatia.items.steel.SteelIngot;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;

import static newinihatia.plughatia.items.Flux.initFlux;
import static newinihatia.plughatia.items.IronHammer.initIronHammer;
import static newinihatia.plughatia.items.anvils.IronAnvil.initIronAnvil;
import static newinihatia.plughatia.items.elvenSteel.ElvenSteelArmor.elvenSteelArmorInit;
import static newinihatia.plughatia.items.elvenSteel.ElvenSteelIngot.createElvenSteelIngot;
import static newinihatia.plughatia.items.elvenSteel.ElvenSteelTools.elvenSteelToolsInit;
import static newinihatia.plughatia.items.mithril.MithrilArmor.mithrilArmorInit;
import static newinihatia.plughatia.items.mithril.MithrilIngot.createMithrilIngot;
import static newinihatia.plughatia.items.mithril.MithrilTools.mithrilToolsInit;
import static newinihatia.plughatia.items.steel.SteelArmor.steelArmorInit;;
import static newinihatia.plughatia.items.steel.SteelTools.steelToolsInit;

// for future reference https://minecraft.fandom.com/wiki/Attribute#Operations

public class ItemManager {

    /* Heatables */
    public static Map<String, Heatable> heatables = new HashMap<>();

    public static SteelIngot steelIngotHeatable = new SteelIngot();
    public static DoubleSteelIngot doubleSteelIngotHeatable = new DoubleSteelIngot();
    public static PigIron pigIronHeatable = new PigIron();
    public static CrucibleOfSteel crucibleOfSteelHeatable = new CrucibleOfSteel();

    public static ItemStack ironHammer;

    public static ItemStack crucibleOfSteel;

    public static ItemStack flux;

    public static ItemStack pigIron;

    /* Anvils */
    public static ItemStack ironAnvil;

    /* Base materials */
    public static ItemStack steelIngot;
    public static ItemStack doubleSteelIngot;

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

        ironHammer = initIronHammer();

        crucibleOfSteel = crucibleOfSteelHeatable.init();

        flux = initFlux();

        pigIron = pigIronHeatable.init();

        ironAnvil = initIronAnvil();

        steelIngot = steelIngotHeatable.init();
        doubleSteelIngot = doubleSteelIngotHeatable.init();
        steelArmorInit();
        steelToolsInit();

        elvenSteelIngot = createElvenSteelIngot(1, 0, 0);
        elvenSteelArmorInit();
        elvenSteelToolsInit();

        mithrilIngot = createMithrilIngot(1, 0, 0);
        mithrilArmorInit();
        mithrilToolsInit();

        heatables.put(crucibleOfSteelHeatable.heatableName, crucibleOfSteelHeatable);
        heatables.put(pigIronHeatable.heatableName, pigIronHeatable);
        heatables.put(steelIngotHeatable.heatableName, steelIngotHeatable);
        heatables.put(doubleSteelIngotHeatable.heatableName, doubleSteelIngotHeatable);

        recipesInit();
    }

    public static void recipesInit() {
        // Heatables
        crucibleOfSteelHeatable.recipesInit();
        pigIronHeatable.recipesInit();
        steelIngotHeatable.recipesInit();
        doubleSteelIngotHeatable.recipesInit();
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

    public static boolean heatableItemsAreEquivalent(ItemStack item1, ItemStack item2) {
        if (item1.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(PlugHatia.getPlugin(), "heatable_name"), PersistentDataType.STRING)
                &&
                item2.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(PlugHatia.getPlugin(), "heatable_name"), PersistentDataType.STRING)) {
            if (item1.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(PlugHatia.getPlugin(), "heatable_name"), PersistentDataType.STRING)
                    ==
                    item2.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(PlugHatia.getPlugin(), "heatable_name"), PersistentDataType.STRING)) {
                return true;
            }
        } else {
            return areEquivalent(item1, item2);
        }
        return true;
    }

}

