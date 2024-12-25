package newinihatia.plughatia.events;

import newinihatia.plughatia.PlugHatia;
import newinihatia.plughatia.items.ItemManager;
import newinihatia.plughatia.menus.Menu;
import newinihatia.plughatia.menus.anvils.AnvilMenu;
import newinihatia.plughatia.menus.anvils.IronAnvilMenu;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AnvilEvents implements Listener {

    private final Map<UUID, BossBar[]> anvilBars = new HashMap<>(); // <playerUUID, [greenBar, redBar]>
    private final Map<UUID, Block> anvilLastClicked = new HashMap<>(); // <playerUUID, menu>

    @EventHandler
    public void onAnvilPlace(BlockPlaceEvent event) {
        if (event.getBlockPlaced().getType() != Material.ANVIL) {
            return;
        }
        if (event.getItemInHand().isSimilar(ItemManager.ironAnvil)) {
            event.getBlockPlaced().setMetadata("anvil_type", new FixedMetadataValue(PlugHatia.getPlugin(), "iron"));
        } else {
            return;
        }
        event.getBlockPlaced().setMetadata("anvil_menu", new FixedMetadataValue(PlugHatia.getPlugin(), null));
        event.getBlockPlaced().setMetadata("anvil_inventory", new FixedMetadataValue(PlugHatia.getPlugin(), null));
    }

    @EventHandler
    public void onAnvilOpen(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();


        if (block == null) {
            return;
        }
        if (block.getType() != Material.ANVIL) {
            return;
        }
        if (!block.hasMetadata("anvil_type")) {
            return;
        }
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        List<MetadataValue> anvilInventoryList = block.getMetadata("anvil_inventory");
        Inventory lastInventoryValue = (Inventory) anvilInventoryList.get(0).value();

        List<MetadataValue> anvilMenuList = block.getMetadata("anvil_menu");
        AnvilMenu lastMenuValue = (AnvilMenu) anvilMenuList.get(0).value();

        Player player = event.getPlayer();

        if (lastInventoryValue == null) {
            if (block.getMetadata("anvil_type").get(0).value().toString() == "iron") {
                new IronAnvilMenu().displayTo(player);
            }
        } else {
            player.openInventory(lastInventoryValue);
            if (player.hasMetadata("NewInihatiaMenu")) {
                player.removeMetadata("NewInihatiaMenu", PlugHatia.getPlugin());
            }
            player.setMetadata("NewInihatiaMenu", new FixedMetadataValue(PlugHatia.getPlugin(), lastMenuValue));
            updatePlayerBars(player, lastMenuValue.getBars());
        }

        if (player.hasMetadata("NewInihatiaAnvil")) {
            player.removeMetadata("NewInihatiaAnvil", PlugHatia.getPlugin());
        }
        player.setMetadata("NewInihatiaAnvil", new FixedMetadataValue(PlugHatia.getPlugin(), block));

        anvilLastClicked.put(player.getUniqueId(), block);

        event.setCancelled(true);
    }

    @EventHandler
    public void onOpenAnvilInventory(InventoryOpenEvent event) {
        Player player = (Player) event.getPlayer();
        if (!player.hasMetadata("NewInihatiaMenu")) {
            return;
        }
        Menu menu = (Menu) player.getMetadata("NewInihatiaMenu").get(0).value();
        if (menu == null) {
            return;
        }

        if (!menu.getTitle().endsWith("Anvil")) {
            return;
        }

        if (anvilLastClicked.containsKey(player.getUniqueId())) {
            if (player.hasMetadata("NewInihatiaAnvil")) {
                player.removeMetadata("NewInihatiaAnvil", PlugHatia.getPlugin());
            }
            player.setMetadata("NewInihatiaAnvil", new FixedMetadataValue(PlugHatia.getPlugin(), anvilLastClicked.get(player.getUniqueId())));
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onAnvilClose(InventoryCloseEvent event) {
        playerCloseAnvil((Player) event.getPlayer());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onAnvilCloseByLeave(PlayerQuitEvent event) {
        playerCloseAnvil(event.getPlayer());
    }

    // give player floating items in anvil
    @EventHandler
    public void onAnvilBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        if (block.getType() != Material.ANVIL) {
            return;
        }
        if (!block.hasMetadata("anvil_type")) {
            return;
        }
        event.setDropItems(false);
        player.getInventory().addItem(ItemManager.ironAnvil);
        Inventory anvilInventory = (Inventory) block.getMetadata("anvil_inventory").get(0).value();
        Menu anvilMenu = (Menu) block.getMetadata("anvil_menu").get(0).value();
        if (anvilInventory != null) {
            for (int i = 0; i < anvilInventory.getSize(); i++) {
                if (anvilInventory.getItem(i) != null) {
                    if (!anvilMenu.getButtons().containsKey(i)) {
                        player.getInventory().addItem(anvilInventory.getItem(i));
                    }
                }
            }
        }
    }

    private void playerCloseAnvil(Player player) {
        if (!player.hasMetadata("NewInihatiaMenu")) {
            System.out.println("no menu metadata");
            return;
        }
        Menu menu = (Menu) player.getMetadata("NewInihatiaMenu").get(0).value();
        if (menu == null) {
            System.out.println("menu is null");
            return;
        }

        if (!player.hasMetadata("NewInihatiaAnvil")) {
            System.out.println("no anvil metadata");
            return;
        }

        System.out.println("its workinininginigning");

        Block block = (Block) player.getMetadata("NewInihatiaAnvil").get(0).value();

        if (block.hasMetadata("anvil_inventory")) {
            block.removeMetadata("anvil_inventory", PlugHatia.getPlugin());
        }
        if (block.hasMetadata("anvil_menu")) {
            block.removeMetadata("anvil_menu", PlugHatia.getPlugin());
        }
        block.setMetadata("anvil_inventory", new FixedMetadataValue(PlugHatia.getPlugin(), player.getOpenInventory().getTopInventory()));
        block.setMetadata("anvil_menu", new FixedMetadataValue(PlugHatia.getPlugin(), menu));

        player.removeMetadata("NewInihatiaAnvil", PlugHatia.getPlugin());

        System.out.println(anvilBars.get(player.getUniqueId()));

        removePlayerBars(player);
    }

    private void removePlayerBars(Player player) {
        if (anvilBars.containsKey(player.getUniqueId())) {
            for (BossBar bossBar : anvilBars.get(player.getUniqueId())) {
                if (bossBar != null) {
                    bossBar.removePlayer(player);
                }
            }
            anvilBars.remove(player.getUniqueId());
        }
    }

    public void updatePlayerBars(Player player, BossBar[] bars) {
        removePlayerBars(player);
        for (BossBar bossBar : bars) {
            if (bossBar != null) {
                bossBar.addPlayer(player);
            }
        }
        anvilBars.put(player.getUniqueId(), bars);
    }
}
