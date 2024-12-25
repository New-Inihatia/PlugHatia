package newinihatia.plughatia.items.anvils;

import newinihatia.plughatia.items.AnvilRecipes;
import newinihatia.plughatia.items.AnvilSmithingRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class IronAnvil {
    public static ItemStack initIronAnvil() {
        ItemStack item = createIronAnvil(1);

        ShapelessRecipe recipe = new ShapelessRecipe(NamespacedKey.minecraft("iron_anvil"), item);
        recipe.addIngredient(1, Material.ANVIL);
        recipe.addIngredient(1, Material.IRON_INGOT);
        Bukkit.getServer().addRecipe(recipe);

        return item;
    }

    public static ItemStack createIronAnvil(int amount) {
        ItemStack item = new ItemStack(Material.ANVIL, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Iron Anvil");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.setCustomModelData(1);
        item.setItemMeta(meta);

        return item;
    }
}
