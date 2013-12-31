package io.github.fourohfour.countdown;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import io.github.fourohfour.devcountdown.Countdown;
import io.github.fourohfour.devcountdown.Tick;
import io.github.fourohfour.wolvesvspigs.GameStateChangeEvent;
import io.github.fourohfour.wolvesvspigs.Globals;

public class Fight extends Countdown{
	public static int Count;
	public static int Factor;
	
	public void onRun(int c, int f) {
		Count = c;
		Factor = f;
	}
	
	public void onTick(Tick t) {
			Player[] onp = Bukkit.getOnlinePlayers();
			for (int index = 0; index < onp.length; index++){
				Scoreboard b = Bukkit.getScoreboardManager().getMainScoreboard();
				String team = b.getPlayerTeam(onp[index]).getName();
				if (team == "Pigs"){
					onp[index].sendMessage("§2" + String.valueOf(t.getTickID()) + " minutes left to survive!§r");
				} else if (team == "Wolves"){
					onp[index].sendMessage("§2" + String.valueOf(t.getTickID()) + " minutes left to catch all the Pigs!§r");
				} else{
					onp[index].sendMessage("§2" + String.valueOf(t.getTickID()) + " minutes left of the match!§r");
				}
			}
		}

	public void onEnd() {
		Bukkit.broadcastMessage("§2Game Over!§r");
		Globals.globalvars.put("gamestage", "none");
		GameStateChangeEvent event = new GameStateChangeEvent();
		Bukkit.getServer().getPluginManager().callEvent(event);
	}

	public void onCancel() {
		Bukkit.getServer().getLogger().info("Countdown id=\"fight\" cancelled");
	}
}
