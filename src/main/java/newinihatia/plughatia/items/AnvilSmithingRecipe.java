package newinihatia.plughatia.items;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AnvilSmithingRecipe {

    private List<ItemStack> ingredients = new ArrayList<>();
    private ItemStack result;
    private int greenPointerStart;
    private int redPointer;
    private int experience;
    private int mininmumHammerStrength = 1;
    private List<String> allowedRaces = new ArrayList<>(List.of("Human", "Hobbit", "Elf", "Dwarf", "Orc"));
    private String[] lastThreeHits = {null, null, null}; // ordered from first to last
    /*
    * Hits are denoted by capitalized initials, e.g.: Hit, Heavy = HH
    */

    public AnvilSmithingRecipe(ItemStack result, ItemStack[] ingredients, int greenPointerStart, int redPointer, int experience) {
        this.result = result;
        for (ItemStack ingredient : ingredients) {
            this.ingredients.add(ingredient);
        }
        this.greenPointerStart = greenPointerStart;
        this.redPointer = redPointer;
        this.experience = experience;
    }

    public AnvilSmithingRecipe(ItemStack result, ItemStack[] ingredients, int greenPointerStart, int redPointer, int experience, int mininmumHammerStrength) {
        this.result = result;
        for (ItemStack ingredient : ingredients) {
            this.ingredients.add(ingredient);
        }
        this.greenPointerStart = greenPointerStart;
        this.redPointer = redPointer;
        this.experience = experience;
        this.mininmumHammerStrength = mininmumHammerStrength;
    }

    public AnvilSmithingRecipe(ItemStack result, ItemStack[] ingredients, int greenPointerStart, int redPointer, int experience, int mininmumHammerStrength, List<String> allowedRaces) {
        this.result = result;
        for (ItemStack ingredient : ingredients) {
            this.ingredients.add(ingredient);
        }
        this.greenPointerStart = greenPointerStart;
        this.redPointer = redPointer;
        this.experience = experience;
        this.mininmumHammerStrength = mininmumHammerStrength;
        this.allowedRaces = allowedRaces;
    }

    public AnvilSmithingRecipe(ItemStack result, ItemStack[] ingredients, String[] lastThreeHits, int greenPointerStart, int redPointer, int experience) {
        this.result = result;
        for (ItemStack ingredient : ingredients) {
            this.ingredients.add(ingredient);
        }
        this.lastThreeHits = lastThreeHits;
        this.greenPointerStart = greenPointerStart;
        this.redPointer = redPointer;
        this.experience = experience;
    }

    public AnvilSmithingRecipe(ItemStack result, ItemStack[] ingredients, String[] lastThreeHits, int greenPointerStart, int redPointer, int experience, int mininmumHammerStrength) {
        this.result = result;
        for (ItemStack ingredient : ingredients) {
            this.ingredients.add(ingredient);
        }
        this.lastThreeHits = lastThreeHits;
        this.greenPointerStart = greenPointerStart;
        this.redPointer = redPointer;
        this.experience = experience;
        this.mininmumHammerStrength = mininmumHammerStrength;
    }

    public AnvilSmithingRecipe(ItemStack result, ItemStack[] ingredients, String[] lastThreeHits, int greenPointerStart, int redPointer, int experience, int mininmumHammerStrength, List<String> allowedRaces) {
        this.result = result;
        for (ItemStack ingredient : ingredients) {
            this.ingredients.add(ingredient);
        }
        this.greenPointerStart = greenPointerStart;
        this.redPointer = redPointer;
        this.experience = experience;
        this.mininmumHammerStrength = mininmumHammerStrength;
        this.allowedRaces = allowedRaces;
    }

    public int getMinimumHammerStrength() {
        return mininmumHammerStrength;
    }

    public List<ItemStack> getIngredients() {
        return ingredients;
    }

    public ItemStack getResult() {
        return result;
    }

    public String[] getLastThreeHits() {
        return lastThreeHits;
    }

    public int getGreenPointerStart() {
        return greenPointerStart;
    }

    public int getRedPointer() {
        return redPointer;
    }

    public int getExperience() {
        return experience;
    }

    public List<String> getAllowedRaces() {
        return allowedRaces;
    }
}
