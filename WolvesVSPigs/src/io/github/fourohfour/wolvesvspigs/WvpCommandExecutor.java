package io.github.fourohfour.wolvesvspigs;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class WvpCommandExecutor implements CommandExecutor{
	
	private WolvesVSPigs plugin;
	
	public WvpCommandExecutor(WolvesVSPigs plugin){
		this.plugin = plugin;
	}
	ScoreboardManager manager = Bukkit.getScoreboardManager();
    @Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String a = args[0];
		Bukkit.broadcastMessage(a);
		if (a.equals("ready")){
			Player[] onp = Bukkit.getOnlinePlayers();
			int np = onp.length;
			Scoreboard players = manager.getMainScoreboard();
			Team pigs = players.registerNewTeam("Pigs");
			pigs.setAllowFriendlyFire(false);
			pigs.setPrefix("§d[Pig]");
			pigs.setSuffix("§r");
		    for(int index=0; index < np; index++){
		        pigs.addPlayer(onp[index]);
		    }
			Countdown.tele();
		    triggercountdown();
		    return true;
		    }
		return false;
		}
    public void triggercountdown() {
		String[] l = {"explore before the match"};
		plugin.triggercount(64, "seconds", 1000, l , false);
	}

}
