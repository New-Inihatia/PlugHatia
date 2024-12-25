package newinihatia.plughatia.items;

import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class AnvilRecipes {
    public static Set<AnvilSmithingRecipe> ironAnvilSmithingRecipes = new HashSet<>();
    public static Set<AnvilSmithingRecipe> steelAnvilSmithingRecipes = new HashSet<>();
    public static Set<AnvilSmithingRecipe> elvenSteelAnvilSmithingRecipes = new HashSet<>();
    public static Set<AnvilSmithingRecipe> mithrilAnvilSmithingRecipes = new HashSet<>();

    public static Set<AnvilWeldingRecipe> ironAnvilWeldingRecipes = new HashSet<>();
    public static Set<AnvilWeldingRecipe> steelAnvilWeldingRecipes = new HashSet<>();
    public static Set<AnvilWeldingRecipe> elvenSteelAnvilWeldingRecipes = new HashSet<>();
    public static Set<AnvilWeldingRecipe> mithrilAnvilWeldingRecipes = new HashSet<>();

    public static int getHammerStrength(ItemStack item) {

        if (item == null) {
            return 0;
        }

        if (ItemManager.areEquivalent(item, ItemManager.ironHammer)) {
            return 1;
        } // else if (ItemManager.areEquivalent(item, ItemManager.steelHammer)) {
//            return 2;
//        } else if (ItemManager.areEquivalent(item, ItemManager.elvenSteelHammer)) {
//            return 3;
//        } else if (ItemManager.areEquivalent(item, ItemManager.mithrilHammer)) {
//            return 4;
//        }
        return 0;
    }
}
