package newinihatia.plughatia.items.steel;

import newinihatia.plughatia.PlugHatia;
import newinihatia.plughatia.items.Heatable;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class CrucibleOfSteel extends Heatable {

    @Override
    protected void variablesInit() {
        heatableName = "crucible_of_steel";
        maxHeatedTemp = 1750;
        minHeatedTemp = 1000;
        defaultStackSize = 1;
        hotStackSize = 1;
        regularCustomModelData = 1;
        hotCustomModelData = 2;
    }

    @Override
    protected void itemInit() {
        item = new ItemStack(Material.BUCKET);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Crucible of Steel");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.setMaxStackSize(defaultStackSize);
        meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "heatable_name"), PersistentDataType.STRING, heatableName);
        item.setItemMeta(meta);
    }

    @Override
    public void recipesInit() {
        ItemStack hotItemInstance = create(1, maxHeatedTemp, 0);
        ItemStack coldItemInstance = create(1, 0, 0);

        Bukkit.addRecipe(new BlastingRecipe(NamespacedKey.minecraft("crucible_of_steel_smelted"), hotItemInstance, new RecipeChoice.ExactChoice(coldItemInstance), 1.0f, 15 * 20));

        ShapelessRecipe recipe = new ShapelessRecipe(NamespacedKey.minecraft("crucible_of_steel"), coldItemInstance);
        recipe.addIngredient(1, Material.BUCKET);
        recipe.addIngredient(2, Material.IRON_INGOT);
        recipe.addIngredient(1, Material.CHARCOAL);

        Bukkit.getServer().addRecipe(recipe);
    }
}
