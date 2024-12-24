package newinihatia.plughatia.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Flux {

    public static ItemStack initFlux() {
        ItemStack item = createFlux(1);
        AnvilSmithingRecipe anvilSmithingRecipe = new AnvilSmithingRecipe(item, new ItemStack[] {new ItemStack(Material.SAND), new ItemStack(Material.GRAVEL)}, 1, 7, 1);
        AnvilRecipes.ironAnvilSmithingRecipes.add(anvilSmithingRecipe);
        AnvilRecipes.steelAnvilSmithingRecipes.add(anvilSmithingRecipe);
        AnvilRecipes.elvenSteelAnvilSmithingRecipes.add(anvilSmithingRecipe);
        AnvilRecipes.mithrilAnvilSmithingRecipes.add(anvilSmithingRecipe);

        return item;
    }

    public static ItemStack createFlux(int amount) {
        ItemStack item = new ItemStack(Material.BLAZE_POWDER, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Flux");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.setCustomModelData(1);
        item.setItemMeta(meta);

        return item;
    }
}
