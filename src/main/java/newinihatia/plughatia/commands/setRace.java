package newinihatia.plughatia.commands;

import newinihatia.plughatia.objects.PlayerObj;
import newinihatia.plughatia.utils.PlayerStorageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.UUID;

public class setRace implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


        if (!cmd.getName().equalsIgnoreCase("setRace")) {
            return false;
        }

        if (args.length != 2) {
            sender.sendMessage("Command format: setRace <target_player> <race>");
            return false;
        }

        String raceStr = args[1];
        String targetUsername = args[0];

        PlayerObj JSONplayer = PlayerStorageUtil.findPlayer(targetUsername);
        if (JSONplayer == null) {
            sender.sendMessage("Player "  + targetUsername + " not found!");
            return false;
        }
        UUID uuid = JSONplayer.getUuid();
        PlayerObj newJSONplayer = JSONplayer;
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
        if (offlinePlayer.isOnline()) {
            Player targetPlayer = offlinePlayer.getPlayer();
            Collection<PotionEffect> previousEffects = targetPlayer.getActivePotionEffects();
            for (PotionEffect effect : targetPlayer.getActivePotionEffects()) {
                targetPlayer.removePotionEffect(effect.getType());
            }

            if (raceStr.equalsIgnoreCase("Hobbit")) {
                newJSONplayer.setRace("Hobbit");
                PlayerStorageUtil.updatePlayer(uuid, newJSONplayer);
                sender.sendMessage(ChatColor.GOLD + targetUsername + " is now a Hobbit!");

                targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, PotionEffect.INFINITE_DURATION, 0, false, false));
                targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, PotionEffect.INFINITE_DURATION, 0, false, false));
                targetPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(22);
            } else if (raceStr.equalsIgnoreCase("Elf")) {
                newJSONplayer.setRace("Elf");
                PlayerStorageUtil.updatePlayer(uuid, newJSONplayer);
                sender.sendMessage(ChatColor.GOLD + targetUsername + " is now an Elf!");

                targetPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(18);
            } else if (raceStr.equalsIgnoreCase("Dwarf")) {
                newJSONplayer.setRace("Dwarf");
                PlayerStorageUtil.updatePlayer(uuid, newJSONplayer);
                sender.sendMessage(ChatColor.GOLD + targetUsername + " is now a Dwarf!");

                targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, PotionEffect.INFINITE_DURATION, 0, false, false));
                targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, PotionEffect.INFINITE_DURATION, 0, false, false));
                targetPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(22);
            } else if (raceStr.equalsIgnoreCase("Orc")) {
                newJSONplayer.setRace("Orc");
                PlayerStorageUtil.updatePlayer(uuid, newJSONplayer);
                sender.sendMessage(ChatColor.GOLD + targetUsername + " is now an Orc!");

                targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, PotionEffect.INFINITE_DURATION, 0, false, false));
                targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, PotionEffect.INFINITE_DURATION, 0, false, false));
                targetPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
            } else if (raceStr.equalsIgnoreCase("Human")) {
                newJSONplayer.setRace("Human");
                PlayerStorageUtil.updatePlayer(uuid, newJSONplayer);
                targetPlayer.sendMessage(ChatColor.GOLD + targetUsername + " is now a Human!");

                targetPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
            } else {
                // giving back previous effects if race setting failed
                for (PotionEffect effect : previousEffects) {
                    targetPlayer.addPotionEffect(effect);
                }
                sender.sendMessage("That is not a valid race!");
                sender.sendMessage("Valid races: Hobbit, Elf, Human, Dwarf, Orc");
                return false;
            }
        }
        else {
            if (raceStr.equalsIgnoreCase("Hobbit")) {
                newJSONplayer.setRace("Hobbit");
                PlayerStorageUtil.updatePlayer(uuid, newJSONplayer);
                sender.sendMessage(ChatColor.GOLD + targetUsername + " is now a Hobbit!");
            } else if (raceStr.equalsIgnoreCase("Elf")) {
                newJSONplayer.setRace("Elf");
                PlayerStorageUtil.updatePlayer(uuid, newJSONplayer);
                sender.sendMessage(ChatColor.GOLD + targetUsername + " is now an Elf!");
            } else if (raceStr.equalsIgnoreCase("Dwarf")) {
                newJSONplayer.setRace("Dwarf");
                PlayerStorageUtil.updatePlayer(uuid, newJSONplayer);
                sender.sendMessage(ChatColor.GOLD + targetUsername + " is now a Dwarf!");
            } else if (raceStr.equalsIgnoreCase("Orc")) {
                newJSONplayer.setRace("Orc");
                PlayerStorageUtil.updatePlayer(uuid, newJSONplayer);
                sender.sendMessage(ChatColor.GOLD + targetUsername + " is now an Orc!");
            } else if (raceStr.equalsIgnoreCase("Human")) {
                newJSONplayer.setRace("Human");
                PlayerStorageUtil.updatePlayer(uuid, newJSONplayer);
                sender.sendMessage(ChatColor.GOLD + targetUsername + " is now a Human!");
            } else {
                sender.sendMessage("That is not a valid race!");
                sender.sendMessage("Valid races: Hobbit, Elf, Human, Dwarf, Orc");
                return false;
            }
        }

        return true;

    }

}