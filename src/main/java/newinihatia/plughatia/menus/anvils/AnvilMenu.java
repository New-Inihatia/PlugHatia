package newinihatia.plughatia.menus.anvils;

import com.google.common.collect.Multimap;
import newinihatia.plughatia.PlugHatia;
import newinihatia.plughatia.events.EventManager;
import newinihatia.plughatia.events.ItemTemperatureControl;
import newinihatia.plughatia.items.AnvilRecipes;
import newinihatia.plughatia.items.AnvilSmithingRecipe;
import newinihatia.plughatia.items.AnvilWeldingRecipe;
import newinihatia.plughatia.items.ItemManager;
import newinihatia.plughatia.menus.Button;
import newinihatia.plughatia.menus.Menu;
import newinihatia.plughatia.utils.PlayerStorageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class AnvilMenu extends Menu {

//    private Inventory anvilInventory;
    private ItemStack ironHammerClone;
    private Integer greenPointer = null;
    private Integer redPointer = null;
    private AnvilSmithingRecipe selectedSmithingRecipe = null;
    private Set<AnvilWeldingRecipe> anvilWeldingRecipes;
    private final int hammerSlot = 5+18;
    private final int barSize = 24;
    protected String anvilTypeString;

    private BossBar createGreenBar(Integer greenPointer) {
        if (greenPointer == null) {
            return null;
        }
        BossBar bar = Bukkit.createBossBar("Green", BarColor.GREEN, BarStyle.SOLID);
        bar.setProgress((double) greenPointer / barSize);
        bar.setColor(BarColor.GREEN);
        return bar;
    }
    private BossBar createRedBar(Integer redPointer) {
        if (redPointer == null) {
            return null;
        }
        BossBar bar = Bukkit.createBossBar("Red", BarColor.RED, BarStyle.SOLID);
        bar.setProgress((double) redPointer / barSize);
        bar.setColor(BarColor.RED);
        return bar;
    }

    private static final Map<String, Integer> hitLocationMap = Map.of(
            "HL", 5,
            "HM", 6,
            "HH", 5+9,
            "D", 6+9,
            "P", 7,
            "B", 8,
            "U", 7+9,
            "S", 8+9
    );

    public String getAnvilTypeString() {
        return anvilTypeString;
    }

    public AnvilMenu(Set<AnvilWeldingRecipe> anvilWeldingRecipes) {
        this.setSize(9*3);
        this.setMaxStackSize(1);
        this.setItemable(true);

        ironHammerClone = ItemManager.ironHammer.clone();
        ItemMeta ironHammerCloneMeta = ironHammerClone.getItemMeta();
        Multimap<Attribute, AttributeModifier> modifiers = ironHammerCloneMeta.getAttributeModifiers();
        for (Map.Entry<Attribute, AttributeModifier> entry : modifiers.entries()) {
            ironHammerCloneMeta.removeAttributeModifier(entry.getKey());
        }

        this.anvilWeldingRecipes = anvilWeldingRecipes;

        resetAnvil();
    }

    private void hit(int value, Player player) {
        if (greenPointer == null) {
            return;
        }
        if (selectedSmithingRecipe == null) {
            return;
        }

        ItemStack hammer = getItem(hammerSlot);

        if (AnvilRecipes.getHammerStrength(hammer) < selectedSmithingRecipe.getMinimumHammerStrength()) {
            player.sendMessage(ChatColor.RED + "Hammer is too weak!");
            return;
        }

        greenPointer += value;
        if (greenPointer > barSize) greenPointer = barSize;
        if (greenPointer < 0) greenPointer = 0;

        EventManager.anvilEvents.updatePlayerBars(player, new BossBar[] {createGreenBar(greenPointer), createRedBar(redPointer)});

        // update last three hits
        ItemStack finalizeBuffer1 = getButton(3+9).getItem();
        addButton(new Button(2+9) {
            @Override
            public ItemStack getItem() {
                return finalizeBuffer1;
            }

            @Override
            public void onClick(Player player) {

            }
        });
        ItemStack finalizeBuffer2 = getButton(4+9).getItem();
        addButton(new Button(3+9) {
            @Override
            public ItemStack getItem() {
                return finalizeBuffer2;
            }

            @Override
            public void onClick(Player player) {

            }
        });
        addButton(new Button(4+9) {
            @Override
            public ItemStack getItem() {
                if (value == -1) {
                    return getAnvilMenu().getInventoryItem(5);
                }
                else if (value == -2) {
                    return getAnvilMenu().getInventoryItem(6);
                }
                else if (value == -3) {
                    return getAnvilMenu().getInventoryItem(5+9);
                }
                else if (value == -4) {
                    return getAnvilMenu().getInventoryItem(6+9);
                }

                else if (value == 1) {
                    return getAnvilMenu().getInventoryItem(7);
                }
                else if (value == 2) {
                    return getAnvilMenu().getInventoryItem(8);
                }
                else if (value == 3) {
                    return getAnvilMenu().getInventoryItem(7+9);
                }
                else if (value == 4) {
                    return getAnvilMenu().getInventoryItem(8+9);
                }
                return null;
            }

            @Override
            public void onClick(Player player) {

            }
        });

        addItem(18+2, ItemTemperatureControl.adjustTemperature(getItem(18+2)));
        addItem(18+3, ItemTemperatureControl.adjustTemperature(getItem(18+3)));
        if (checkSmithingRecipe(player)) {
            addItem(18+2, null);
            addItem(18+3, selectedSmithingRecipe.getResult());

            // Turn recipes back into book
            addButton(new Button(4+18) {
                @Override
                public ItemStack getItem() {
                    ItemStack book = new ItemStack(Material.BOOK);
                    ItemMeta meta = book.getItemMeta();
                    meta.setDisplayName("Recipes");
                    List<String> lore = new ArrayList<>();
                    meta.setLore(lore);
                    book.setItemMeta(meta);
                    return book;
                }

                @Override
                public void onClick(Player player) {
                    recipesFunction(player);
                }
            });

            player.giveExp(selectedSmithingRecipe.getExperience());

            resetAnvil();
        }

        // Remove hammer durability
        Damageable hammerMeta = (Damageable) hammer.getItemMeta();
        hammerMeta.setDamage(hammerMeta.getDamage() + Math.abs(value));
        hammer.setItemMeta(hammerMeta);
        addItem(hammerSlot, hammer);

        displayTo(player);
    }

    private boolean checkSmithingRecipe(Player player) {
        if (selectedSmithingRecipe == null) {
            return false;
        }

        boolean[] recipePossibility = checkSmithingRecipePossibility(selectedSmithingRecipe, player);
        if (!recipePossibility[0] || !recipePossibility[1] || !recipePossibility[2] || !recipePossibility[3]) {
            return false;
        }

        if (!Objects.equals(greenPointer, redPointer)) {
            return false;
        }

        int i = 2;
        for (String hit : selectedSmithingRecipe.getLastThreeHits()) {
            if (hit != null) {
                if (!getButton(i).getItem().isSimilar(getButton(i+9).getItem())) {
                    return false;
                }
            }
            i++;
        }
        return true;
    }

    private void weld(Player player) {

        addItem(0, ItemTemperatureControl.adjustTemperature(getItem(0)));
        addItem(1, ItemTemperatureControl.adjustTemperature(getItem(1)));
        AnvilWeldingRecipe weldingRecipe = checkWeldingRecipe(player);
        if (weldingRecipe != null) {
            removeItem(0);
            removeItem(1);
            addItem(9, weldingRecipe.getResult());
            ItemStack hammer = getItem(hammerSlot);
            Damageable hammerMeta = (Damageable) hammer.getItemMeta();
            hammerMeta.setDamage(hammerMeta.getDamage() - Math.abs(5));
            hammer.setItemMeta(hammerMeta);
            addItem(hammerSlot, hammer);
            player.giveExp(weldingRecipe.getExperience());
        }
    }

    private AnvilWeldingRecipe checkWeldingRecipe(Player player) {

        if (getItem(0) == null || getItem(1) == null) {
            return null;
        }
        if (getItem(9) == null) {
            return null;
        }
        if (!getItem(9).isSimilar(ItemManager.flux)) {
            return null;
        }
        ItemStack hammer = getItem(hammerSlot);
        if (hammer == null) {
            return null;
        }

        Set<ItemStack> ingredients = new HashSet<>();
        ingredients.add(getItem(0));
        ingredients.add(getItem(1));
        for (AnvilWeldingRecipe weldingRecipe : anvilWeldingRecipes) {
            boolean hasIngredients = true;
            boolean properTemps = true;
            boolean properHammer = AnvilRecipes.getHammerStrength(hammer) >= weldingRecipe.getMinimumHammerStrength();
            boolean allowedRace = weldingRecipe.getAllowedRaces().contains(PlayerStorageUtil.findPlayer(player.getUniqueId()).getRace());
            Set<ItemStack> ingredientsCopy = new HashSet<>(ingredients);
            for (ItemStack item : weldingRecipe.getIngredients()) {
                boolean hasIngredient = false;
                boolean properTemp = false;
                for (ItemStack ingredient : ingredientsCopy) {
                    if (ingredient != null) {
                        if (ItemManager.heatableItemsAreEquivalent(item, ingredient)) {
                            if (item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(PlugHatia.getPlugin(), "temperature"))) {
                                double temperature = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(PlugHatia.getPlugin(), "temperature"), PersistentDataType.DOUBLE);
                                if (temperature > 0) {
                                    if (ingredient.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(PlugHatia.getPlugin(), "temperature"))) {
                                        if (ingredient.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(PlugHatia.getPlugin(), "temperature"), PersistentDataType.DOUBLE) >= temperature) {
                                            properTemp = true;
                                        }
                                    }
                                }
                            } else {
                                properTemp = true;
                            }
                            ingredientsCopy.remove(ingredient);
                            hasIngredient = true;
                            break;
                        }
                    }
                }
                if (hasIngredient) {
                    if (!properTemp) {
                        properTemps = false;
                    }
                } else {
                    hasIngredients = false;
                }
            }
            if (hasIngredients && properTemps && properHammer && allowedRace) {
                return weldingRecipe;
            }
        }
        return null;
    }

    private AnvilMenu getAnvilMenu() {
        return this;
    }

    public void recipesFunction(Player player) {};

    protected boolean[] checkSmithingRecipePossibility(AnvilSmithingRecipe recipe, Player player) {
        Set<ItemStack> ingredients = new HashSet<>();
        ingredients.add(getItem(2+18));
        ingredients.add(getItem(3+18));
        boolean hasIngredients = true;
        boolean properTemps = true;
        boolean properHammer = AnvilRecipes.getHammerStrength(getItem(hammerSlot)) >= recipe.getMinimumHammerStrength();
        boolean allowedRace = recipe.getAllowedRaces().contains(PlayerStorageUtil.findPlayer(player.getUniqueId()).getRace());
        Set<ItemStack> ingredientsCopy = new HashSet<>(ingredients);
        for (ItemStack item : recipe.getIngredients()) {
            boolean hasIngredient = false;
            boolean properTemp = false;
            for (ItemStack ingredient : ingredientsCopy) {
                if (ingredient != null) {
                    if (ItemManager.heatableItemsAreEquivalent(item, ingredient)) {
                        if (item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(PlugHatia.getPlugin(), "temperature"))) {
                            double temperature = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(PlugHatia.getPlugin(), "temperature"), PersistentDataType.DOUBLE);
                            if (temperature > 0) {
                                if (ingredient.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(PlugHatia.getPlugin(), "temperature"))) {
                                    if (ingredient.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(PlugHatia.getPlugin(), "temperature"), PersistentDataType.DOUBLE) >= temperature) {
                                        properTemp = true;
                                    }
                                }
                            }
                        } else {
                            properTemp = true;
                        }
                        ingredientsCopy.remove(ingredient);
                        hasIngredient = true;
                        break;
                    }
                }
            }
            if (hasIngredient) {
                if (!properTemp) {
                    properTemps = false;
                }
            } else {
                hasIngredients = false;
                break;
            }
        }
        return new boolean[] {hasIngredients, properTemps, properHammer, allowedRace};
    }

    protected abstract class SmithingRecipesMenu extends Menu {

        protected AnvilMenu anvilMenu;
        public Set<AnvilSmithingRecipe> recipes;

        public SmithingRecipesMenu(AnvilMenu anvilMenu, Set<AnvilSmithingRecipe> recipes, Player player) {
            super(AnvilMenu.this);
            this.anvilMenu = anvilMenu;
            this.recipes = recipes;
            init(player);
        }

        protected void init(Player player) {
            setSize(9*3);
            setTitle("Recipes");
            addButton(new Button(9*3 - 1) {
                @Override
                public ItemStack getItem() {
                    ItemStack door = new ItemStack(Material.OAK_DOOR);
                    ItemMeta meta = door.getItemMeta();
                    meta.setDisplayName("Back to Anvil");
                    List<String> lore = new ArrayList<>();
                    meta.setLore(lore);
                    door.setItemMeta(meta);
                    return door;
                }

                @Override
                public void onClick(Player player) {
                    getAnvilMenu().displayTo(player);
                }
            });

            int slot = 0;
            for (AnvilSmithingRecipe recipe : recipes) {
                boolean[] recipePossibilityBools = checkSmithingRecipePossibility(recipe, player);
                boolean hasIngredients = recipePossibilityBools[0];
                boolean properTemps = recipePossibilityBools[1];
                boolean properHammer = recipePossibilityBools[2];
                boolean allowedRace = recipePossibilityBools[3];

                if (hasIngredients && allowedRace) {
                    boolean finalProperTemps = properTemps;
                    addButton(new Button(slot) {
                        @Override
                        public ItemStack getItem() {
                            ItemStack item = recipe.getResult().clone();
                            ItemMeta meta = item.getItemMeta();
                            List<String> lore = new ArrayList<>();
                            if (!finalProperTemps) {
                                lore.add(ChatColor.RED + "Ingredients need to be heated!");
                            }
                            if (!properHammer) {
                                lore.add(ChatColor.RED + "Hammer is too weak!");
                            }
                            meta.setLore(lore);
                            item.setItemMeta(meta);
                            return item;
                        }

                        @Override
                        public void onClick(Player player) {
                            // Adding buttons will overwrite old ones
                            int hitSlot = 2;
                            String[] orderStrings = {"Last: ", "Second: ", "First: "};
                            for (String hitType : recipe.getLastThreeHits()) {
                                if (hitType != null) {
                                    int finalHitSlot = hitSlot;
                                    anvilMenu.addButton(new Button(finalHitSlot) {
                                        @Override
                                        public ItemStack getItem() {
                                            ItemStack wantedHit = getAnvilMenu().getInventoryItem(hitLocationMap.get(hitType)).clone();

                                            ItemMeta meta = wantedHit.getItemMeta();
                                            meta.setDisplayName(orderStrings[finalHitSlot - 2] + wantedHit.getItemMeta().getDisplayName());
                                            wantedHit.setItemMeta(meta);
                                            return wantedHit;
                                        }

                                        @Override
                                        public void onClick(Player player) {

                                        }
                                    });
                                }
                                hitSlot++;
                            }

                            greenPointer = recipe.getGreenPointerStart();
                            redPointer = recipe.getRedPointer();
                            BossBar[] bossBars = {createGreenBar(greenPointer), createRedBar(redPointer)};
                            EventManager.anvilEvents.updatePlayerBars(player, bossBars);


                            // "Retexture" recipe book
                            anvilMenu.addButton(new Button(4+18) {
                                @Override
                                public ItemStack getItem() {
                                    ItemStack book = recipe.getResult().clone();
                                    ItemMeta meta = book.getItemMeta();
                                    meta.setDisplayName("Recipes");
                                    List<String> lore = new ArrayList<>();
                                    meta.setLore(lore);
                                    book.setItemMeta(meta);
                                    return book;
                                }

                                @Override
                                public void onClick(Player player) {
                                    recipesFunction(player);
                                }
                            });

                            anvilMenu.selectedSmithingRecipe = recipe;

                            getAnvilMenu().displayTo(player);
                        }
                    });
                    slot++;
                }
            }
        }

    }

    private void resetAnvil() {
        greenPointer = null;
        redPointer = null;
        selectedSmithingRecipe = null;

        // Weld
        addButton(new Button(8+2) {
            @Override
            public ItemStack getItem() {
                ItemStack weld = ironHammerClone;
                ItemMeta meta = weld.getItemMeta();
                meta.setDisplayName("Weld");
                List<String> lore = new ArrayList<>();
                meta.setLore(lore);
                weld.setItemMeta(meta);
                return weld;
            }

            @Override
            public void onClick(Player player) {
                weld(player);
            }
        });

        // Red Hits
        addButton(new Button(5) {
            @Override
            public ItemStack getItem() {
                ItemStack lightHit = ironHammerClone;
                ItemMeta meta = lightHit.getItemMeta();
                meta.setDisplayName(ChatColor.RED + "Hit, Light");
                List<String> lore = new ArrayList<>();
                meta.setLore(lore);
                lightHit.setItemMeta(meta);
                return lightHit;
            }

            @Override
            public void onClick(Player player) {
                hit(-1, player);
            }
        });
        addButton(new Button(6) {
            @Override
            public ItemStack getItem() {
                ItemStack mediumHit = ironHammerClone;
                ItemMeta meta = mediumHit.getItemMeta();
                meta.setDisplayName(ChatColor.RED + "Hit, Medium");
                List<String> lore = new ArrayList<>();
                meta.setLore(lore);
                mediumHit.setItemMeta(meta);
                return mediumHit;
            }

            @Override
            public void onClick(Player player) {
                hit(-2, player);
            }
        });
        addButton(new Button(5+9) {
            @Override
            public ItemStack getItem() {
                ItemStack heavyHit = ironHammerClone;
                ItemMeta meta = heavyHit.getItemMeta();
                meta.setDisplayName(ChatColor.RED + "Hit, Heavy");
                List<String> lore = new ArrayList<>();
                meta.setLore(lore);
                heavyHit.setItemMeta(meta);
                return heavyHit;
            }

            @Override
            public void onClick(Player player) {
                hit(-3, player);
            }
        });
        addButton(new Button(6+9) {
            @Override
            public ItemStack getItem() {
                ItemStack draw = new ItemStack(Material.IRON_SHOVEL);
                ItemMeta meta = draw.getItemMeta();
                meta.setDisplayName(ChatColor.RED + "Draw");
                List<String> lore = new ArrayList<>();
                meta.setLore(lore);
                draw.setItemMeta(meta);
                return draw;
            }

            @Override
            public void onClick(Player player) {
                hit(-4, player);
            }
        });

        // Green Hit
        addButton(new Button(7) {
            @Override
            public ItemStack getItem() {
                ItemStack punch = new ItemStack(Material.SPYGLASS);
                ItemMeta meta = punch.getItemMeta();
                meta.setDisplayName(ChatColor.GREEN + "Punch");
                List<String> lore = new ArrayList<>();
                meta.setLore(lore);
                punch.setItemMeta(meta);
                return punch;
            }

            @Override
            public void onClick(Player player) {
                hit(1, player);
            }
        });
        addButton(new Button(8) {
            @Override
            public ItemStack getItem() {
                ItemStack bend = new ItemStack(Material.IRON_PICKAXE);
                ItemMeta meta = bend.getItemMeta();
                meta.setDisplayName(ChatColor.GREEN + "Bend");
                List<String> lore = new ArrayList<>();
                meta.setLore(lore);
                bend.setItemMeta(meta);
                return bend;
            }

            @Override
            public void onClick(Player player) {
                hit(2, player);
            }
        });
        addButton(new Button(7+9) {
            @Override
            public ItemStack getItem() {
                ItemStack upset = new ItemStack(Material.IRON_AXE);
                ItemMeta meta = upset.getItemMeta();
                meta.setDisplayName(ChatColor.GREEN + "Upset");
                List<String> lore = new ArrayList<>();
                meta.setLore(lore);
                upset.setItemMeta(meta);
                return upset;
            }

            @Override
            public void onClick(Player player) {
                hit(3, player);
            }
        });
        addButton(new Button(8+9) {
            @Override
            public ItemStack getItem() {
                ItemStack shrink = new ItemStack(Material.IRON_HOE);
                ItemMeta meta = shrink.getItemMeta();
                meta.setDisplayName(ChatColor.GREEN + "Shrink");
                List<String> lore = new ArrayList<>();
                meta.setLore(lore);
                shrink.setItemMeta(meta);
                return shrink;
            }

            @Override
            public void onClick(Player player) {
                hit(4, player);
            }
        });

        // Recipe Book
        addButton(new Button(4+18) {
            @Override
            public ItemStack getItem() {
                ItemStack book = new ItemStack(Material.BOOK);
                ItemMeta meta = book.getItemMeta();
                meta.setDisplayName("Recipes");
                List<String> lore = new ArrayList<>();
                meta.setLore(lore);
                book.setItemMeta(meta);
                return book;
            }

            @Override
            public void onClick(Player player) {
                recipesFunction(player);
            }
        });


        // Filling
        for (int i = 2; i < 5; i++) {
            addButton(new Button(i) {
                @Override
                public ItemStack getItem() {
                    ItemStack frame = new ItemStack(Material.ITEM_FRAME);
                    ItemMeta meta = frame.getItemMeta();
                    meta.setDisplayName(" ");
                    List<String> lore = new ArrayList<>();
                    meta.setLore(lore);
                    frame.setItemMeta(meta);
                    return frame;
                }

                @Override
                public void onClick(Player player) {

                }
            });
        }
        for (int i = 11; i < 14; i++) {
            addButton(new Button(i) {
                @Override
                public ItemStack getItem() {
                    ItemStack frame = new ItemStack(Material.ITEM_FRAME);
                    ItemMeta meta = frame.getItemMeta();
                    meta.setDisplayName(" ");
                    List<String> lore = new ArrayList<>();
                    meta.setLore(lore);
                    frame.setItemMeta(meta);
                    return frame;
                }

                @Override
                public void onClick(Player player) {

                }
            });
        }
        for (int i = 18; i < 2+18; i++) {
            glassFilling(i);
        }
        for (int i = 24; i < 27; i++) {
            glassFilling(i);
        }
    }

    private void glassFilling(int slot) {
        addButton(new Button(slot) {
            @Override
            public ItemStack getItem() {
                ItemStack glass = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
                ItemMeta meta = glass.getItemMeta();
                meta.setDisplayName(" ");
                List<String> lore = new ArrayList<>();
                meta.setLore(lore);
                glass.setItemMeta(meta);
                return glass;
            }

            @Override
            public void onClick(Player player) {

            }
        });
    }

    private void barGlassFilling(int slot) {
        addButton(new Button(slot) {
            @Override
            public ItemStack getItem() {
                ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                ItemMeta meta = glass.getItemMeta();
                meta.setDisplayName(" ");
                List<String> lore = new ArrayList<>();
                meta.setLore(lore);
                glass.setItemMeta(meta);
                return glass;
            }

            @Override
            public void onClick(Player player) {

            }
        });
    }

    @Override
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

        EventManager.anvilEvents.updatePlayerBars(player, new BossBar[] {createGreenBar(greenPointer), createRedBar(redPointer)});
    }

    public BossBar[] getBars() {
        return new BossBar[] {createGreenBar(greenPointer), createRedBar(redPointer)};
    }

}
