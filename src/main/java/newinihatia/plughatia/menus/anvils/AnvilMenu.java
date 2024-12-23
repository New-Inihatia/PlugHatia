package newinihatia.plughatia.menus.anvils;

import com.google.common.collect.Multimap;
import newinihatia.plughatia.PlugHatia;
import newinihatia.plughatia.items.AnvilRecipe;
import newinihatia.plughatia.items.ItemManager;
import newinihatia.plughatia.menus.Button;
import newinihatia.plughatia.menus.Menu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class AnvilMenu extends Menu {

    private Inventory anvilInventory;
    private ItemStack ironHammerClone;
    private Integer greenPointer = null;
    private Integer redPointer = null;
    private AnvilRecipe selectedRecipe = null;

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


    public AnvilMenu() {
        this.setTitle("Anvil");
        this.setSize(9*5);
        this.setMaxStackSize(1);

        ironHammerClone = ItemManager.ironHammer.clone();
        ItemMeta ironHammerCloneMeta = ironHammerClone.getItemMeta();
        Multimap<Attribute, AttributeModifier> modifiers = ironHammerCloneMeta.getAttributeModifiers();
        for (Map.Entry<Attribute, AttributeModifier> entry : modifiers.entries()) {
            ironHammerCloneMeta.removeAttributeModifier(entry.getKey());
        }

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
                anvilInventory = player.getOpenInventory().getTopInventory();
                recipesFunction(player);
            }
        });


        /* Bars */
        // Green Bar
        for (int i = 9*3; i < 9*4; i++) {
            barGlassFilling(i);
        }

        // Red Bar
        for (int i = 9*4; i < 9*5; i++) {
            barGlassFilling(i);
        }

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
        for (int i = 23; i < 27; i++) {
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

    private void hit(int value, Player player) {
        if (greenPointer == null) {
            return;
        }

        int oldGreenPointer = greenPointer;
        greenPointer += value;
        if (greenPointer > 8) greenPointer = 8;
        if (greenPointer < 0) greenPointer = 0;

        barGlassFilling(9*3 + oldGreenPointer);

        addButton(new Button(9*3 + greenPointer) {
            @Override
            public ItemStack getItem() {
                ItemStack green = new ItemStack(Material.EMERALD);
                ItemMeta meta = green.getItemMeta();
                meta.setDisplayName(" ");
                List<String> lore = new ArrayList<>();
                meta.setLore(lore);
                green.setItemMeta(meta);
                return green;
            }

            @Override
            public void onClick(Player player) {

            }
        });

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
                    return anvilInventory.getItem(5);
                }
                else if (value == -2) {
                    return anvilInventory.getItem(6);
                }
                else if (value == -3) {
                    return anvilInventory.getItem(5+9);
                }
                else if (value == -4) {
                    return anvilInventory.getItem(6+9);
                }

                else if (value == 1) {
                    return anvilInventory.getItem(7);
                }
                else if (value == 2) {
                    return anvilInventory.getItem(8);
                }
                else if (value == 3) {
                    return anvilInventory.getItem(7+9);
                }
                else if (value == 4) {
                    return anvilInventory.getItem(8+9);
                }
                return null;
            }

            @Override
            public void onClick(Player player) {

            }
        });

        anvilInventory = player.getOpenInventory().getTopInventory();

        if (checkRecipe()) {
            anvilInventory.setItem(18+2, null);
            anvilInventory.setItem(18+3, selectedRecipe.getResult());
        }

        for (Map.Entry<Integer, Button> entry : getButtons().entrySet()) {
            Button button = entry.getValue();
            anvilInventory.setItem(button.getSlot(), button.getItem());
        }

        if (player.hasMetadata("NewInihatiaMenu")) {
            player.closeInventory();
        }
        player.setMetadata("NewInihatiaMenu", new FixedMetadataValue(PlugHatia.getPlugin(), getAnvilMenu()));
        player.openInventory(anvilInventory);
    }

    private boolean checkRecipe() {
        if (selectedRecipe == null) {
            return false;
        }

        boolean[] recipePossibility = checkRecipePossibility(selectedRecipe);
        if (!recipePossibility[0] || !recipePossibility[1]) {
            return false;
        }

        if (!Objects.equals(greenPointer, redPointer)) {
            return false;
        }

        int i = 2+9;
        for (String hit : selectedRecipe.getLastThreeHits()) {
            if (hit != null) {
                if (hitLocationMap.get(hit) != i) {
                    return false;
                }
            }
            i++;
        }
        return true;
    }

    public AnvilMenu getAnvilMenu() {
        return this;
    }

    public void recipesFunction(Player player) {};

    protected boolean[] checkRecipePossibility(AnvilRecipe recipe) {
        Set<ItemStack> ingredients = new HashSet<>();
        ingredients.add(anvilInventory.getItem(2+18));
        ingredients.add(anvilInventory.getItem(3+18));
        boolean hasIngredients = true;
        boolean properTemps = true;
        Set<ItemStack> ingredientsCopy = new HashSet<>(ingredients);
        for (Map.Entry<ItemStack, Double> entry : recipe.getItems().entrySet()) {
            ItemStack item = entry.getKey();
            Double temperature = entry.getValue();
            boolean hasIngredient = false;
            boolean properTemp = false;
            for (ItemStack ingredient : ingredientsCopy) {
                if (ingredient != null) {
                    if (ingredient.isSimilar(item)) {
                        if (temperature > 0) {
                            if (ingredient.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(PlugHatia.getPlugin(), "temperature"))) {
                                if (ingredient.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(PlugHatia.getPlugin(), "temperature"), PersistentDataType.DOUBLE) < temperature) {
                                    properTemp = true;
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
        return new boolean[] {hasIngredients, properTemps};
    }

    protected abstract class RecipesMenu extends Menu {

        private AnvilMenu anvilMenu;
        public Set<AnvilRecipe> recipes;

        public RecipesMenu(AnvilMenu anvilMenu, Set<AnvilRecipe> recipes) {
            super(AnvilMenu.this);
            this.anvilMenu = anvilMenu;
            this.recipes = recipes;
            init();
        }

        protected void init() {
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
                    for (Map.Entry<Integer, Button> entry : anvilMenu.getButtons().entrySet()) {
                        Button button = entry.getValue();
                        anvilInventory.setItem(button.getSlot(), button.getItem());
                    }

                    if (player.hasMetadata("NewInihatiaMenu")) {
                        player.closeInventory();
                    }
                    player.setMetadata("NewInihatiaMenu", new FixedMetadataValue(PlugHatia.getPlugin(), getAnvilMenu()));
                    player.openInventory(anvilInventory);
                }
            });

            Set<ItemStack> ingredients = new HashSet<>();
            ingredients.add(anvilInventory.getItem(2+18));
            ingredients.add(anvilInventory.getItem(3+18));
            int slot = 0;
            for (AnvilRecipe recipe : recipes) {
                boolean[] recipePossibilityBools = checkRecipePossibility(recipe);
                boolean hasIngredients = recipePossibilityBools[0];
                boolean properTemps = recipePossibilityBools[1];

                if (hasIngredients) {
                    boolean finalProperTemps = properTemps;
                    addButton(new Button(slot) {
                        @Override
                        public ItemStack getItem() {
                            ItemStack item = recipe.getResult();
                            ItemMeta meta = item.getItemMeta();
                            List<String> lore = new ArrayList<>();
                            if (!finalProperTemps) {
                                lore.add(ChatColor.RED + "Ingredients need to be heated!");
                            }
                            meta.setLore(lore);
                            item.setItemMeta(meta);
                            return item;
                        }

                        @Override
                        public void onClick(Player player) {
                            // Adding buttons will overwrite old ones
                            int hitSlot = 2;
                            for (String hitType : recipe.getLastThreeHits()) {
                                if (hitType != null) {
                                    anvilMenu.addButton(new Button(hitSlot) {
                                        @Override
                                        public ItemStack getItem() {
                                            return anvilInventory.getItem(hitLocationMap.get(hitType));
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

                            anvilMenu.addButton(new Button(9*3 + greenPointer) {
                                @Override
                                public ItemStack getItem() {
                                    ItemStack green = new ItemStack(Material.EMERALD);
                                    ItemMeta meta = green.getItemMeta();
                                    meta.setDisplayName(" ");
                                    List<String> lore = new ArrayList<>();
                                    meta.setLore(lore);
                                    green.setItemMeta(meta);
                                    return green;
                                }

                                @Override
                                public void onClick(Player player) {

                                }
                            });

                            anvilMenu.addButton(new Button(9*4 + redPointer) {
                                @Override
                                public ItemStack getItem() {
                                    ItemStack red = new ItemStack(Material.REDSTONE);
                                    ItemMeta meta = red.getItemMeta();
                                    meta.setDisplayName(" ");
                                    List<String> lore = new ArrayList<>();
                                    meta.setLore(lore);
                                    red.setItemMeta(meta);
                                    return red;
                                }

                                @Override
                                public void onClick(Player player) {

                                }
                            });

                            anvilMenu.selectedRecipe = recipe;

                            for (Map.Entry<Integer, Button> entry : anvilMenu.getButtons().entrySet()) {
                                Button button = entry.getValue();
                                anvilInventory.setItem(button.getSlot(), button.getItem());
                            }

                            if (player.hasMetadata("NewInihatiaMenu")) {
                                player.closeInventory();
                            }
                            player.setMetadata("NewInihatiaMenu", new FixedMetadataValue(PlugHatia.getPlugin(), getAnvilMenu()));
                            player.openInventory(anvilInventory);
                        }
                    });
                    slot++;
                }
            }
        }

    }

}
