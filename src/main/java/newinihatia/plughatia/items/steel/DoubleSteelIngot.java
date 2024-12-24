package newinihatia.plughatia.items.steel;

import newinihatia.plughatia.PlugHatia;
import newinihatia.plughatia.items.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class DoubleSteelIngot extends Heatable {
    @Override
    protected void variablesInit() {
        heatableName = "double_steel_ingot";
        maxHeatedTemp = 1750;
        minHeatedTemp = 1000;
        defaultStackSize = 32;
        hotStackSize = 1;
        regularCustomModelData = 5;
        hotCustomModelData = 6;
    }

    @Override
    protected void itemInit() {
        item = new ItemStack(Material.IRON_INGOT);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Double Steel Ingot");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "heatable_name"), PersistentDataType.STRING, heatableName);
        item.setItemMeta(meta);
    }

    @Override
    public void recipesInit() {
        ItemStack hotItemInstance = create(1, maxHeatedTemp, 0);
        ItemStack minHotItemInstance = create(1, minHeatedTemp, 0);
        ItemStack coldItemInstance = create(1, 0, 0);

        AnvilWeldingRecipe anvilWeldingRecipe = new AnvilWeldingRecipe(coldItemInstance, minHotItemInstance, minHotItemInstance, 1);
        AnvilRecipes.ironAnvilWeldingRecipes.add(anvilWeldingRecipe);
        AnvilRecipes.steelAnvilWeldingRecipes.add(anvilWeldingRecipe);
        AnvilRecipes.elvenSteelAnvilWeldingRecipes.add(anvilWeldingRecipe);
        AnvilRecipes.mithrilAnvilWeldingRecipes.add(anvilWeldingRecipe);

        Bukkit.addRecipe(new BlastingRecipe(NamespacedKey.minecraft("heat_double_steel_ingot"), hotItemInstance, new RecipeChoice.ExactChoice(coldItemInstance), 0f, 15 * 20));
    }
}
