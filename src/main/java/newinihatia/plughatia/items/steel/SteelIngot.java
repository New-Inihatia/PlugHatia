package newinihatia.plughatia.items.steel;

import newinihatia.plughatia.PlugHatia;
import newinihatia.plughatia.items.AnvilSmithingRecipe;
import newinihatia.plughatia.items.AnvilRecipes;
import newinihatia.plughatia.items.Heatable;
import newinihatia.plughatia.items.ItemManager;
import org.bukkit.*;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class SteelIngot extends Heatable {

    @Override
    protected void variablesInit() {
        heatableName = "steel_ingot";
        maxHeatedTemp = 1750;
        minHeatedTemp = 1000;
        defaultStackSize = 64;
        hotStackSize = 1;
        regularCustomModelData = 3;
        hotCustomModelData = 4;
    }

    @Override
    protected void itemInit() {
        item = new ItemStack(Material.IRON_INGOT);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Steel Ingot");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "heatable_name"), PersistentDataType.STRING, heatableName);
        item.setItemMeta(meta);
    }

    @Override
    public void recipesInit() {
        ItemStack hotItemInstance = create(1, maxHeatedTemp, 0);
        ItemStack coldItemInstance = create(1, 0, 0);

        AnvilSmithingRecipe anvilSmithingRecipe = new AnvilSmithingRecipe(coldItemInstance, new ItemStack[] {ItemManager.pigIronHeatable.create(1, ItemManager.pigIronHeatable.minHeatedTemp)}, new String[] {"HH", "HM", "HL"}, 8, 0, 1);
        AnvilRecipes.ironAnvilSmithingRecipes.add(anvilSmithingRecipe);
        AnvilRecipes.steelAnvilSmithingRecipes.add(anvilSmithingRecipe);
        AnvilRecipes.elvenSteelAnvilSmithingRecipes.add(anvilSmithingRecipe);
        AnvilRecipes.mithrilAnvilSmithingRecipes.add(anvilSmithingRecipe);

        Bukkit.addRecipe(new BlastingRecipe(NamespacedKey.minecraft("heat_steel_ingot"), hotItemInstance, new RecipeChoice.ExactChoice(coldItemInstance), 0f, 15 * 20));
    }
}
