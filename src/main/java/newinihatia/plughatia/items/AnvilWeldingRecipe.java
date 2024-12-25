package newinihatia.plughatia.items;

import org.bukkit.inventory.ItemStack;

public class AnvilWeldingRecipe {
    private ItemStack[] ingredients = {null, null};
    private ItemStack result;
    private int experience;
    private int minimumHammerStrength = 1;

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
}
