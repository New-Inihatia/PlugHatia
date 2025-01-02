package newinihatia.plughatia.utils;

import com.google.gson.Gson;
import newinihatia.plughatia.PlugHatia;
import newinihatia.plughatia.items.ItemManager;
import newinihatia.plughatia.menus.anvils.AnvilMenu;
import newinihatia.plughatia.menus.anvils.IronAnvilMenu;
import newinihatia.plughatia.objects.AnvilObj;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;

import java.io.*;
import java.util.*;

public class AnvilStorageUtil {

    public static void saveAnvils(Map<Block, AnvilMenu> anvils) throws IOException {

        ArrayList<AnvilObj> anvilObjs = new ArrayList<AnvilObj>();

        for (Map.Entry<Block, AnvilMenu> entry : anvils.entrySet()) {
            anvilObjs.add(new AnvilObj(entry.getKey(), entry.getValue()));
        }


        System.out.println("Attempting Anvils");

        Gson gson = new Gson();
        File file = new File(PlugHatia.getPlugin().getDataFolder().getAbsolutePath() + "/anvils.json");
        file.getParentFile().mkdir();
        file.createNewFile();
        Writer writer = new FileWriter(file, false);
        gson.toJson(anvilObjs, writer);
        writer.flush();
        writer.close();
        System.out.println("Anvils saved.");

    }

    public static Map<int[], AnvilMenu> loadAnvils() throws IOException {

        ArrayList<AnvilObj> anvilObjs = new ArrayList<>();

        Gson gson = new Gson();
        File file = new File(PlugHatia.getPlugin().getDataFolder().getAbsolutePath() + "/anvils.json");
        if (file.exists()) {
            Reader reader = new FileReader(file);
            AnvilObj[] a = gson.fromJson(reader, AnvilObj[].class);
            anvilObjs = new ArrayList<>(Arrays.asList(a));
            System.out.println("Anvils loaded.");
        }

        Map<int[], AnvilMenu> anvils = new HashMap<>();

        for (AnvilObj anvilObj : anvilObjs) {
            if (anvilObj.getAnvilType().equals("iron")) {
                anvils.put(anvilObj.getLocation(), new IronAnvilMenu());
                System.out.println("Added anvil at " + anvilObj.getLocation()[0] + ", " + anvilObj.getLocation()[1] + ", " + anvilObj.getLocation()[2] + ", " + anvilObj.getLocation()[3]);
            }
            else {
                continue;
            }
            for (Map.Entry<Integer, String> entry : anvilObj.getItems().entrySet()) {
                anvils.get(anvilObj.getLocation()).addItem(entry.getKey(), ItemManager.deserializeItemStack(entry.getValue()));
            }
        }

        System.out.println("Anvils interpreted.");

        return anvils;
    }
}
