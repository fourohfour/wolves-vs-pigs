package io.github.fourohfour.countdown;

import io.github.fourohfour.devcountdown.Countdown;
import io.github.fourohfour.devcountdown.Tick;
import io.github.fourohfour.wolvesvspigs.GameStateChangeEvent;
import io.github.fourohfour.wolvesvspigs.Globals;

import org.bukkit.Bukkit;

public class Prepare extends Countdown{
	public static int Count;
	public static int Factor;
	
	public void onRun(int c, int f) {
		Count = c;
		Factor = f;
	}
	
	public void onTick(Tick t) {
			Bukkit.broadcastMessage("§2Only " + String.valueOf(t.getTickID()) + " minutes left to prepare!§r");
	}

	public void onEnd() {
		Bukkit.broadcastMessage("§2It's time to fight!§r");
		Bukkit.broadcastMessage("§2Game Starting!§r");
		Globals.globalvars.put("gamestage", "fight");
		GameStateChangeEvent event = new GameStateChangeEvent();
		Bukkit.getServer().getPluginManager().callEvent(event);
	}

	public void onCancel() {
		Bukkit.broadcastMessage("§2Game Cancelled.§r");
	}
}
