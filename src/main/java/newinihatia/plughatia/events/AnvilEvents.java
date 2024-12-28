package newinihatia.plughatia.events;

import newinihatia.plughatia.PlugHatia;
import newinihatia.plughatia.items.ItemManager;
import newinihatia.plughatia.menus.Menu;
import newinihatia.plughatia.menus.anvils.AnvilMenu;
import newinihatia.plughatia.menus.anvils.IronAnvilMenu;
import newinihatia.plughatia.utils.AnvilStorageUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.io.IOException;
import java.util.*;

public class AnvilEvents implements Listener {

    private final Map<UUID, BossBar[]> anvilBars = new HashMap<>(); // <playerUUID, [greenBar, redBar]>
    private final Map<UUID, Block> anvilLastClicked = new HashMap<>(); // <playerUUID, menu>
    public Map<Block, AnvilMenu> anvils = new HashMap<>();

    public final void initAnvilEvents() {
        Map<int[], AnvilMenu> loadedAnvils = new HashMap<>();
        try {
            loadedAnvils = AnvilStorageUtil.loadAnvils();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Map.Entry<int[], AnvilMenu> entry : loadedAnvils.entrySet()) {
            World world = Bukkit.getWorlds().get(entry.getKey()[3]);
            Block anvil = world.getBlockAt(entry.getKey()[0], entry.getKey()[1], entry.getKey()[2]);

            AnvilMenu anvilMenu = entry.getValue();

            anvil.setMetadata("anvil_menu", new FixedMetadataValue(PlugHatia.getPlugin(), anvilMenu));

            anvils.put(anvil, anvilMenu);
        }
    }

    public final void saveAnvils() {
        try {
            AnvilStorageUtil.saveAnvils(anvils);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onAnvilPlace(BlockPlaceEvent event) {
        if (event.getBlockPlaced().getType() != Material.ANVIL) {
            return;
        }
        AnvilMenu anvilMenu;
        if (event.getItemInHand().isSimilar(ItemManager.ironAnvil)) {
            anvilMenu = new IronAnvilMenu();
            event.getBlockPlaced().setMetadata("anvil_menu", new FixedMetadataValue(PlugHatia.getPlugin(), anvilMenu));
        } else {
            return;
        }
        anvils.put(event.getBlockPlaced(), anvilMenu);
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
        if (!block.hasMetadata("anvil_menu")) {
            System.out.println("No anvil_menu metadata?");
            return;
        }
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }


        AnvilMenu anvilMenu = (AnvilMenu) block.getMetadata("anvil_menu").get(0).value();

        Player player = event.getPlayer();

        anvilMenu.displayTo(player);

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
        if (!block.hasMetadata("anvil_menu")) {
            return;
        }
        event.setDropItems(false);
        player.getInventory().addItem(ItemManager.ironAnvil);
        AnvilMenu anvilMenu = (AnvilMenu) block.getMetadata("anvil_menu").get(0).value();

        anvils.remove(block);

        World world = block.getWorld();
        Location location = block.getLocation();
        for (Map.Entry<Integer, ItemStack> entry : anvilMenu.getItems().entrySet()) {
            ItemStack item = entry.getValue();
            if (item != null) {
                world.dropItemNaturally(location, item);
            }
        }
    }

    private void playerCloseAnvil(Player player) {
        if (!player.hasMetadata("NewInihatiaMenu")) {
            return;
        }
        if (player.getMetadata("NewInihatiaMenu").get(0).value() == null) {
            return;
        }
        if (!player.hasMetadata("NewInihatiaAnvil")) {
            return;
        }

        AnvilMenu anvilMenu = (AnvilMenu) player.getMetadata("NewInihatiaMenu").get(0).value();

        Block block = (Block) player.getMetadata("NewInihatiaAnvil").get(0).value();

        if (block.hasMetadata("anvil_menu")) {
            block.removeMetadata("anvil_menu", PlugHatia.getPlugin());
        }
        block.setMetadata("anvil_menu", new FixedMetadataValue(PlugHatia.getPlugin(), anvilMenu));
        anvils.put(block, anvilMenu);

        player.removeMetadata("NewInihatiaAnvil", PlugHatia.getPlugin());

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
