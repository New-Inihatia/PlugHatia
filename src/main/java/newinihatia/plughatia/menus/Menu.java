package newinihatia.plughatia.menus;

import newinihatia.plughatia.PlugHatia;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu implements Serializable {

    protected final Map<Integer, Button> buttons = new HashMap<>();
    protected int size = 9*3;
    protected String title = "Custom Menu";
    private Menu parent;
    protected int maxStackSize = 64;
    protected boolean itemable = false;
    protected final Map<Integer, ItemStack> items = new HashMap<>();

    public Menu() {
        this(null);
    }

    public Menu(@Nullable Menu parent) {
        this.parent = parent;
    }

    protected final void addButton(Button button) {
        buttons.put(button.getSlot(), button);
    }

    protected final Button getButton(int slot) {
        return buttons.get(slot);
    }

    public final Map<Integer, Button> getButtons() {
        return buttons;
    }

    protected final void removeButton(Button button) {
        buttons.remove(button.getSlot());
    }

    protected final void removeButton(int slot) {
        buttons.remove(slot);
    }


    public final void addItem(int slot, ItemStack item) {
        items.put(slot, item);
    }

    public final Map<Integer, ItemStack> getItems() {
        return items;
    }

    protected final ItemStack getItem(int slot) {
        return items.get(slot);
    }

    protected final void removeItem(int slot) {
        items.remove(slot);
    }

    public final boolean getItemable() {
        return itemable;
    }

    public final void setItemable(boolean itemable) {
        this.itemable = itemable;
    }

    public final ItemStack getInventoryItem(int slot) {
        if (buttons.containsKey(slot)) {
            return buttons.get(slot).getItem();
        }
        else if (items.containsKey(slot)) {
            return items.get(slot);
        }
        return null;
    }

    protected final void setSize(int size) {
        this.size = size;
    }

    protected final int getSize() {
        return size;
    }

    protected final void setTitle(String title) {
        this.title = title;
    }

    public final String getTitle() {
        return title;
    }

    protected final void setMaxStackSize(int maxStackSize) {
        this.maxStackSize = maxStackSize;
    }

    public final int getMaxStackSize() {
        return maxStackSize;
    }


    public void displayTo(Player player) {
        Inventory inventory = Bukkit.createInventory(player, size, title);
        inventory.setMaxStackSize(maxStackSize);
        for (Map.Entry<Integer, Button> entry : buttons.entrySet()) {
            Button button = entry.getValue();
            inventory.setItem(button.getSlot(), button.getItem());
        }

        for (Map.Entry<Integer, ItemStack> entry: items.entrySet()) {
            ItemStack item = entry.getValue();
            inventory.setItem(entry.getKey(), item);
        }

        if (player.hasMetadata("NewInihatiaMenu")) {
            player.closeInventory();
        }
        player.setMetadata("NewInihatiaMenu", new FixedMetadataValue(PlugHatia.getPlugin(), this));
        player.openInventory(inventory);
    }

}
