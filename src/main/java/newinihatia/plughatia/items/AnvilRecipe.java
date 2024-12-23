package newinihatia.plughatia.items;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class AnvilRecipe {

    private Map<ItemStack, Double> items = new HashMap<>();
    private ItemStack result;
    private int greenPointerStart;
    private int redPointer;
    private String[] lastThreeHits = {null, null, null}; // ordered from first to last
    /*
    * Hits are denoted by capitalized initials, e.g.: Hit, Heavy = HH
    */

    public AnvilRecipe(ItemStack result, ItemStack item, double itemTemperature, int greenPointerStart, int redPointer) {
        this.result = result;
        this.items.put(item, itemTemperature);
        this.greenPointerStart = greenPointerStart;
        this.redPointer = redPointer;
    }

    public AnvilRecipe(ItemStack result, ItemStack item, double itemTemperature, String[] lastThreeHits, int greenPointerStart, int redPointer) {
        this.result = result;
        this.items.put(item, itemTemperature);
        this.lastThreeHits = lastThreeHits;
        this.greenPointerStart = greenPointerStart;
        this.redPointer = redPointer;
    }

    public AnvilRecipe(ItemStack result, ItemStack item1, double item1Temperature, ItemStack item2, double item2Temperature, int greenPointerStart, int redPointer) {
        this.result = result;
        this.items.put(item1, item1Temperature);
        this.items.put(item2, item2Temperature);
        this.greenPointerStart = greenPointerStart;
        this.redPointer = redPointer;
    }

    public AnvilRecipe(ItemStack result, ItemStack item1, double item1Temperature, ItemStack item2, double item2Temperature, String[] lastThreeHits, int greenPointerStart, int redPointer) {
        this.result = result;
        this.items.put(item1, item1Temperature);
        this.items.put(item2, item2Temperature);
        this.lastThreeHits = lastThreeHits;
        this.greenPointerStart = greenPointerStart;
        this.redPointer = redPointer;
    }

    public Map<ItemStack, Double> getItems() {
        return items;
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
}
