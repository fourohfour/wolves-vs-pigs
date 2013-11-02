package io.github.fourohfour.wolvesvspigs;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class CreateWolves {
	public static void getWolves(){
		Player[] OnlinePlayers = Bukkit.getOnlinePlayers();
		Random rand = new Random();
		Integer online = OnlinePlayers.length;
		
		ArrayList<Player> pigs = new ArrayList<Player>();
		
		ScoreboardManager smanager = Bukkit.getServer().getScoreboardManager();
		Scoreboard mainboard = smanager.getMainScoreboard();
		Team wolves = mainboard.registerNewTeam("Wolves");
		wolves.setAllowFriendlyFire(false);
		wolves.setPrefix("§7[Wolf]");
		wolves.setSuffix("§r");
		
		for (int playerindex = 0; playerindex < online; playerindex++){
			if (mainboard.getTeam("Pigs").hasPlayer(OnlinePlayers[playerindex])){
				pigs.add(OnlinePlayers[playerindex]);
			}
		}
		
		Integer pigpresize = pigs.size();
		for (int pigindex = 0; pigindex < (pigpresize/4); pigindex++){
			Integer random = rand.nextInt(pigs.size());
			Player target = pigs.get(random);
			wolves.addPlayer(target);
			pigs.remove(random);
		}
	}

}
