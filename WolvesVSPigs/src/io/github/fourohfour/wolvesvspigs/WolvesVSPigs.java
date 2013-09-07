package io.github.fourohfour.wolvesvspigs;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class WolvesVSPigs extends JavaPlugin{
	@Override
	public void onEnable(){
		getCommand("wvpa").setExecutor(new WvpCommandExecutor(this));
	}
	@Override
	public void onDisable(){
		//stuff
	}
	public void triggercount(final int runTime,final String runType,final int runFactor,final String[] runReasons, final Boolean runIsComp){
	    Bukkit.getScheduler().runTaskAsynchronously(this, new Runnable() {
	    	@Override
	    	public void run() {
	    		Countdown.count(runTime, runType, runFactor, runReasons, runIsComp);
	    	}
	    });
	}
	}
