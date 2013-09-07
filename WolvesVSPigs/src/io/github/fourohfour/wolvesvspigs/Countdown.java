package io.github.fourohfour.wolvesvspigs;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class Countdown {
    public static void tele() {
        for (int p = 0; p < Bukkit.getOnlinePlayers().length; p++) {
            Player target = Bukkit.getOnlinePlayers()[p];
            Location centre = new Location(Bukkit.getWorld("world"), 0, 70, 0);
            target.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 5));
            target.teleport(centre);
        }
    }
    public static boolean count(int c, String time, int factor, String[] reason, Boolean comp){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard players = manager.getMainScoreboard();
        for (int d=c; d > 0; d=d-1){
            if (d == 1){
                try {
                    for (int iplayer = 0; iplayer< Bukkit.getOnlinePlayers().length; iplayer++) {
                        Player reciever = Bukkit.getOnlinePlayers()[iplayer];
                        Team pteam = players.getPlayerTeam(reciever);
                        String pteamn = pteam.getName();
                        if (pteamn == "Pigs"){
                            reciever.sendMessage("§2" + d + " " + time.substring(0, time.length()-1) + " more to " + reason[0] + "!" + "§r");
                        } else if ((pteamn == "Wolves") && (comp == true)){
                            reciever.sendMessage("§2" + d + " " + time + " more to " + reason[1] + "!" + "§r");
                        }
                        Thread.sleep(factor);
                    }
                    return true;
                } catch (InterruptedException e) {
                    return false;
                }
            } else if ((d % 10) == 0){
                for (int iplayer = 0; iplayer< Bukkit.getOnlinePlayers().length; iplayer++) {
                    Player reciever = Bukkit.getOnlinePlayers()[iplayer];
                    Team pteam = players.getPlayerTeam(reciever);
                    String pteamn = pteam.getName();
                    if (pteamn == "Pigs"){
                        reciever.sendMessage("§2" + d + " " + time + " more to " + reason[0] + "!" + "§r");
                    } else if ((pteamn == "Wolves") && (comp == true)){
                        reciever.sendMessage("§2" + d + " " + time + " more to " + reason[1] + "!" + "§r");
                    }
                }
            } else if ((d < 10) && ((d % 5) == 0)){
                for (int iplayer = 0; iplayer< Bukkit.getOnlinePlayers().length; iplayer++) {
                    Player reciever = Bukkit.getOnlinePlayers()[iplayer];
                    Team pteam = players.getPlayerTeam(reciever);
                    String pteamn = pteam.getName();
                    if (pteamn == "Pigs"){
                        reciever.sendMessage("§2" + d + " " + time + " more to " + reason[0] + "!" + "§r");
                    } else if ((pteamn == "Wolves") && (comp == true)){
                        reciever.sendMessage("§2" + d + " " + time + " more to " + reason[1] + "!" + "§r");
                    }
                }
            }
            try {
                Thread.sleep(factor);
            } catch (InterruptedException e) {
                    return false;
            }
        }
        return false;
    }
}