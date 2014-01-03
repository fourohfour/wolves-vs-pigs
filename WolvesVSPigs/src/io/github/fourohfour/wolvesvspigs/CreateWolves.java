//Automatically creates wolves.
package io.github.fourohfour.wolvesvspigs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class CreateWolves {
	public static boolean createWolves(Kit k){
		
		//Get the Scoreboard Objects
		ScoreboardManager smanager = Bukkit.getServer().getScoreboardManager();
		Scoreboard mainboard = smanager.getMainScoreboard();
		
		//Create Wolves team
		Team wolves = mainboard.registerNewTeam("Wolves");
		Bukkit.getLogger().info("Wolves team created");
		wolves.setAllowFriendlyFire(false);
		wolves.setPrefix("§7");
		wolves.setSuffix("§r");
		
		//Get pigs and put them in an ArrayList
		Set<OfflinePlayer> pigset = mainboard.getTeam("Pigs").getPlayers();
		List<OfflinePlayer> piglist = new ArrayList<OfflinePlayer>();
		piglist.addAll(pigset);
		Bukkit.getLogger().info("Piglist: " + piglist.toString());
		
		//Create some needed objects / variables
		Random rand = new Random();  //Get a random Object
		int pigs = piglist.size();   //An integer representing the amount of pigs.
		Bukkit.getLogger().info("Pigs amount: " + String.valueOf(pigs));
		if (pigs < 2){
			return false;
		}
		int w = (pigs / 4);          //An integer representing a quarter of the pigs (The amount of wolves)
		if (pigs < 4){
			w++;
		}
		Bukkit.getLogger().info("W amount: " + String.valueOf(w));
		//Iterate w times
		for (int i = 0; i < w; i++){
			while (true){
		        int r = rand.nextInt(pigs);                //Get a random integer
				Bukkit.getLogger().info("r: " + String.valueOf(r));
		        if (!(wolves.hasPlayer(piglist.get(r)))){  //Check if that player has already been chosen
		    		Bukkit.getLogger().info("Inside if!");
			        wolves.addPlayer(piglist.get(r));      //If not, change them to a wolf
			        Bukkit.broadcastMessage("§2"+ piglist.get(r).getName() + " is now a wolf!" + "§r");
			        break;                                 //Break the loop so it can choose another player
		        }
			}
		}
		
		//Get wolves
		Set<OfflinePlayer> wolfset = mainboard.getTeam("Wolves").getPlayers();
		//Convert to a list
		List<OfflinePlayer> wolflist = new ArrayList<OfflinePlayer>();
		wolflist.addAll(wolfset);
		
		//Iterate through it and give wolves the kit and then teleport them.
		for(int i = 0; i < wolves.getSize(); i++){
		    k.Wolf((Player) wolflist.get(i));
			k.tele((Player) wolflist.get(i));
		}
		return true;
		
	}

}
