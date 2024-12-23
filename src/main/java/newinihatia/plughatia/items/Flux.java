package newinihatia.plughatia.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Flux {
    public static ItemStack createFlux(int amount) {
        ItemStack item = new ItemStack(Material.BLAZE_POWDER, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Flux");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.setCustomModelData(1);
        item.setItemMeta(meta);

        AnvilRecipe anvilRecipe = new AnvilRecipe(item, new ItemStack(Material.SAND), 0, new ItemStack(Material.GRAVEL), 0, 1, 7);
        AnvilRecipes.ironAnvilRecipes.add(anvilRecipe);
        AnvilRecipes.steelAnvilRecipes.add(anvilRecipe);
        AnvilRecipes.elvenSteelAnvilRecipes.add(anvilRecipe);
        AnvilRecipes.mithrilAnvilRecipes.add(anvilRecipe);

        return item;
    }
}
