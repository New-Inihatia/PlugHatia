package newinihatia.plughatia.utils;

import com.google.gson.Gson;
import newinihatia.plughatia.PlugHatia;
import newinihatia.plughatia.objects.PlayerObj;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class PlayerStorageUtil {

    private static ArrayList<PlayerObj> players = new ArrayList<>();

    public static PlayerObj createPlayer(Player player, String race, String CLASS) {

        PlayerObj newPlayer = new PlayerObj(player.getUniqueId(), player.getDisplayName(), player.getDisplayName(), race, CLASS);
        players.add(newPlayer);

        try {
            savePlayers();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newPlayer;

    }

    public static PlayerObj findPlayer(UUID uuid) {

        for (PlayerObj thePlayer : players) {
            if (thePlayer.getUuid().equals(uuid)) {
                return thePlayer;
            }
        }
        return null;
    }

    public static PlayerObj findPlayer(String username) {

        for (PlayerObj thePlayer : players) {
            if (thePlayer.getUsername().equals(username)) {
                return thePlayer;
            }
        }
        return null;
    }

    public static PlayerObj updatePlayer(UUID uuid, PlayerObj newPlayer) {

        for (PlayerObj thePlayer : players) {
            if (thePlayer.getUuid().equals(uuid)) {
                thePlayer.setUuid(newPlayer.getUuid());
                thePlayer.setUsername(newPlayer.getUsername());
                thePlayer.setRace(newPlayer.getRace());
                thePlayer.setCLASS(newPlayer.getCLASS());

                try {
                    savePlayers();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return thePlayer;
            }
        }
        return null;
    }

    public static void savePlayers() throws IOException {

        Gson gson = new Gson();
        File file = new File(PlugHatia.getPlugin().getDataFolder().getAbsolutePath() + "/players.json");
        file.getParentFile().mkdir();
        file.createNewFile();
        Writer writer = new FileWriter(file, false);
        gson.toJson(players, writer);
        writer.flush();
        writer.close();
        System.out.println("Players saved.");

    }

    public static void loadPlayers() throws IOException {

        Gson gson = new Gson();
        File file = new File(PlugHatia.getPlugin().getDataFolder().getAbsolutePath() + "/players.json");
        if (file.exists()) {
            Reader reader = new FileReader(file);
            PlayerObj[] p = gson.fromJson(reader, PlayerObj[].class);
            players = new ArrayList<>(Arrays.asList(p));
            System.out.println("Players loaded.");
        }

    }

}