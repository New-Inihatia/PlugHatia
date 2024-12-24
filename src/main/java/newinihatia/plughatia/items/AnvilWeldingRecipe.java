package newinihatia.plughatia.items;

import org.bukkit.inventory.ItemStack;

public class AnvilWeldingRecipe {
    private ItemStack[] ingredients = {null, null};
    private ItemStack result;
    private int experience;

    public AnvilWeldingRecipe(ItemStack result, ItemStack ingredient1, ItemStack ingredient2, int experience) {
        this.result = result;
        this.ingredients[0] = ingredient1;
        this.ingredients[1] = ingredient2;
        this.experience = experience;
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
