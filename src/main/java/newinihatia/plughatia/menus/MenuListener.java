package newinihatia.plughatia.menus;

import newinihatia.plughatia.PlugHatia;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Map;

public class MenuListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        if (inventory == player.getInventory()) {
            return;
        }
        if (player.hasMetadata("NewInihatiaMenu")) {
            Menu menu = (Menu) player.getMetadata("NewInihatiaMenu").get(0).value();

            Map<Integer, Button> buttons = menu.getButtons();
            int slot = event.getSlot();
            if (slot < 0) {
                return;
            }
            if (buttons.containsKey(slot)) {
                Button button = buttons.get(slot);
                button.onClick(player);
                if (!button.takeable) {
                    event.setCancelled(true);
                }
            }
            else if (menu.getItemable() && event.getCursor().getType() != Material.AIR) {
                ItemStack itemToAdd = event.getCursor().clone();
                itemToAdd.setAmount(Math.min(itemToAdd.getAmount(), menu.getMaxStackSize()));
                menu.addItem(slot, itemToAdd);
                player.removeMetadata("NewInihatiaMenu", PlugHatia.getPlugin());
                player.setMetadata("NewInihatiaMenu", new FixedMetadataValue(PlugHatia.getPlugin(), menu));
            }
            else if (!menu.getItemable()) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (player.hasMetadata("NewInihatiaMenu")) {
            player.removeMetadata("NewInihatiaMenu", PlugHatia.getPlugin());
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (player.hasMetadata("NewInihatiaMenu")) {
            player.removeMetadata("NewInihatiaMenu", PlugHatia.getPlugin());
        }
    }
}
