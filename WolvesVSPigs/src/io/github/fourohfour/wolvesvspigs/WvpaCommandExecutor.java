package io.github.fourohfour.wolvesvspigs;

import io.github.fourohfour.wolvesvspigs.Countdown;
import io.github.fourohfour.wolvesvspigs.WolvesVSPigs;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class WvpaCommandExecutor implements CommandExecutor{
	
	private static WolvesVSPigs plugin;
	
	public WvpaCommandExecutor(WolvesVSPigs plugin){
		WvpaCommandExecutor.plugin = plugin;
	}
    @Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0){
			return false;
		}
		String a = args[0];
		if (a.equalsIgnoreCase("start")){
			if (args.length > 1){
				start(Integer.parseInt(args[1]));
			} else {
				start(64);
			}
			return true;
	    } else if (a.equalsIgnoreCase("stop")){
	    	plugin.stopcount();
	    	return true;
	    } else if (a.equalsIgnoreCase("help")){
	    	sender.sendMessage("§2" + "Do \"/wvp help\" to view help." + "§r");
	    	return true;
	    }else if (a.equalsIgnoreCase("nogb")){
			if (args.length == 1){
				String[] ms = {"§2" + "Use \"/wvpd nogb -m\" to not be able to break glass." + "§r","§2" +  "Use \"/wvpd nogb [Player]\" to stop a player being able to break glass." + "§r"};
				sender.sendMessage(ms);
				return true;
			}
		    String b = args[1];
		    if (b.equalsIgnoreCase("-m")){
				Player target = (Player) sender;
				target.setMetadata("nogb", new FixedMetadataValue(plugin, true));
				target.sendMessage("§2" + "You cannot now break glass.");
				return true;
		    } else if (true){
			    for (int pindex = 0; pindex< Bukkit.getOnlinePlayers().length; pindex++){
			        if (Bukkit.getServer().getPlayer(b) == Bukkit.getOnlinePlayers()[pindex]){
					    Player target = Bukkit.getOnlinePlayers()[pindex];
					    target.setMetadata("nogb", new FixedMetadataValue(plugin, true));
			 		    sender.sendMessage("§2" + target.getName() + " cannot now break glass" + "§r");
			    		return true;
		    	    }
		    	}
			    sender.sendMessage("§2" + b + " is not online. Do /wvp help to view help." + "§r");
    		}
	    } else if (a.equalsIgnoreCase("gb")){
			if (args.length == 1){
				String[] ms = {"§2" + "Use \"/wvpd tp -m\" to be able to break glass." + "§r","§2" +  "Use \"/wvpd tp [Player]\" to let a player be able to break glass." + "§r"};
				sender.sendMessage(ms);
				return true;
			}
		    String b = args[1];
		    if (b.equalsIgnoreCase("-m")){
				Player target = (Player) sender;
				target.setMetadata("nogb", new FixedMetadataValue(plugin, false));
				target.sendMessage("§2" + "You can now break glass.");
				return true;
		    } else if (true){
			    for (int pindex = 0; pindex< Bukkit.getOnlinePlayers().length; pindex++){
			        if (Bukkit.getServer().getPlayer(b) == Bukkit.getOnlinePlayers()[pindex]){
					    Player target = Bukkit.getOnlinePlayers()[pindex];
					    target.setMetadata("notele", new FixedMetadataValue(plugin, false));
			 		    sender.sendMessage("§2" + target.getName() + " can now break glass" + "§r");
			    		return true;
		    	    }
		    	}
			    sender.sendMessage("§2" + b + " is not online. Do /wvp help to view help." + "§r");
			    return true;
    		}
	    }
		return false;
		}
    public void beforematch(int t) {
		String[] l = {"explore before the match"};
		plugin.triggercount(t, "seconds", 1000, l , false);
	}
    public static Boolean start(int t) {
    	ScoreboardManager manager = Bukkit.getScoreboardManager();
		Player[] onp = Bukkit.getOnlinePlayers();
		int np = onp.length;
		Scoreboard players = manager.getMainScoreboard();
		Team pigs = players.registerNewTeam("Pigs");
		pigs.setAllowFriendlyFire(false);
		pigs.setPrefix("§d[Pig]");
		pigs.setSuffix("§r");
	    for(int index=0; index < np; index++){
	        pigs.addPlayer(onp[index]);
	        Kit.Pig(onp[index]);
	        if (onp[index].getMetadata("notele").get(0).asBoolean() == false){
	        	Countdown.tele(onp[index]);
	        }
	    }
		WvpaCommandExecutor wvpace = new WvpaCommandExecutor(plugin);
		wvpace.beforematch(t);
	    return true;
    }
}
