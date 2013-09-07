package io.github.fourohfour.wolvesvspigs;

import java.util.Set;

import io.github.fourohfour.wolvesvspigs.Countdown;
import io.github.fourohfour.wolvesvspigs.WvpCommandExecutor;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scheduler.BukkitWorker;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public final class WolvesVSPigs extends JavaPlugin{
	@Override
	public void onEnable(){
		getCommand("wvpa").setExecutor(new WvpCommandExecutor(this));
	}
	@Override
	public void onDisable(){
		//stuff
	}
	public int triggercount(final int runTime,final String runType,final int runFactor,final String[] runReasons, final Boolean runIsComp){
	    BukkitTask i;
		i = Bukkit.getScheduler().runTaskAsynchronously(this, new Runnable() {
	    	@Override
	    	public void run() {
	    		Countdown.count(runTime, runType, runFactor, runReasons, runIsComp);
	    	}
	    });
		return i.getTaskId();
	    
	}
	public void stopcount(){
		Bukkit.broadcastMessage("§2" + "Stopping Game..." + "§r");
		BukkitWorker aws = Bukkit.getServer().getScheduler().getActiveWorkers().get(0);
		Bukkit.getLogger().info(aws.toString());
		int awsid = aws.getTaskId();
		Bukkit.getScheduler().cancelTask(awsid);
		ScoreboardManager mainmanager = Bukkit.getScoreboardManager();
		Scoreboard mainboard = mainmanager.getMainScoreboard();
		Set<Team> teams = mainboard.getTeams();
		Team[] tarray = teams.toArray(new Team[teams.size()]);
		for (int index = 0; index < tarray.length; index++){
			Team targett = tarray[index];
			targett.unregister();
		}
		Bukkit.broadcastMessage("§2" + "Game Stopped" + "§r");
		}
	}

