package newinihatia.plughatia.items.steel;

import newinihatia.plughatia.PlugHatia;
import newinihatia.plughatia.items.Heatable;
import org.bukkit.*;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class PigIron extends Heatable {

    @Override
    protected void variablesInit() {
        heatableName = "pig_iron";
        maxHeatedTemp = 1750;
        minHeatedTemp = 1000;
        defaultStackSize = 64;
        hotStackSize = 1;
        regularCustomModelData = 1;
        hotCustomModelData = 2;
    }

    @Override
    protected void itemInit() {
        item = new ItemStack(Material.IRON_INGOT);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Pig Iron");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "heatable_name"), PersistentDataType.STRING, heatableName);
        item.setItemMeta(meta);
    }

    @Override
    public void recipesInit() {
        ItemStack hotItemInstance = create(1, maxHeatedTemp, 0);
        ItemStack coldItemInstance = create(1, 0, 0);

        Bukkit.addRecipe(new BlastingRecipe(NamespacedKey.minecraft("heat_pig_iron"), hotItemInstance, new RecipeChoice.ExactChoice(coldItemInstance), 0f, 15 * 20));
    }
}
