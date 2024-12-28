package newinihatia.plughatia.items;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AnvilWeldingRecipe {
    private ItemStack[] ingredients = {null, null};
    private ItemStack result;
    private int experience;
    private int minimumHammerStrength = 1;
    private List<String> allowedRaces = new ArrayList<>(List.of("Human", "Hobbit", "Elf", "Dwarf", "Orc"));

    public AnvilWeldingRecipe(ItemStack result, ItemStack ingredient1, ItemStack ingredient2, int experience) {
        this.result = result;
        this.ingredients[0] = ingredient1;
        this.ingredients[1] = ingredient2;
        this.experience = experience;
    }

    public AnvilWeldingRecipe(ItemStack result, ItemStack ingredient1, ItemStack ingredient2, int experience, int minimumHammerStrength) {
        this.result = result;
        this.ingredients[0] = ingredient1;
        this.ingredients[1] = ingredient2;
        this.experience = experience;
        this.minimumHammerStrength = minimumHammerStrength;
    }

    public AnvilWeldingRecipe(ItemStack result, ItemStack ingredient1, ItemStack ingredient2, int experience, int minimumHammerStrength, List<String> allowedRaces) {
        this.result = result;
        this.ingredients[0] = ingredient1;
        this.ingredients[1] = ingredient2;
        this.experience = experience;
        this.minimumHammerStrength = minimumHammerStrength;
        this.allowedRaces = allowedRaces;
    }

    public int getMinimumHammerStrength() {
        return minimumHammerStrength;
    }

    public ItemStack[] getIngredients() {
        return ingredients;
    }

    public ItemStack getResult() {
        return result;
    }

    public int getExperience() {
        return experience;
    }

    public List<String> getAllowedRaces() {
        return allowedRaces;
    }
}
