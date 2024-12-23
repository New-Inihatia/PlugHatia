package newinihatia.plughatia.menus;

import newinihatia.plughatia.PlugHatia;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {

    private final Map<Integer, Button> buttons = new HashMap<>();
    private int size = 9*3;
    private String title = "Custom Menu";
    private Menu parent;
    private int maxStackSize = 64;

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

    protected final void setSize(int size) {
        this.size = size;
    }

    protected final void setTitle(String title) {
        this.title = title;
    }

    protected final void setMaxStackSize(int maxStackSize) {
        this.maxStackSize = maxStackSize;
    }


    public final void displayTo(Player player) {
        Inventory inventory = Bukkit.createInventory(player, size, title);
        inventory.setMaxStackSize(maxStackSize);
        for (Map.Entry<Integer, Button> entry : buttons.entrySet()) {
            Button button = entry.getValue();
            inventory.setItem(button.getSlot(), button.getItem());
        }

        if (player.hasMetadata("NewInihatiaMenu")) {
            player.closeInventory();
        }
        player.setMetadata("NewInihatiaMenu", new FixedMetadataValue(PlugHatia.getPlugin(), this));
        player.openInventory(inventory);
    }

}
