//Command Executor for WVPA commands
//Initialised by main class in on enable
//By FourOhFour
//http://fourohfour.github.io

package io.github.fourohfour.wolvesvspigs;

import io.github.fourohfour.wolvesvspigs.WolvesVSPigs;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

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
		String b = getArgsOne(args);
		
		if (a.equalsIgnoreCase("start")){
				Globals.globalvars.put("gamestage", "pregame");
				GameStateChangeEvent event = new GameStateChangeEvent();
				Bukkit.getServer().getPluginManager().callEvent(event);
			return true;
	    }
		
		else if (a.equalsIgnoreCase("stop")){
	    	plugin.stopcount();
	    	return true;
	    }
		
		else if (a.equalsIgnoreCase("help")){
	    	sender.sendMessage("§2" + "Do \"/wvp help\" to view help." + "§r");
	    	return true;
	    }
		
		if (a.equalsIgnoreCase("cp")){
			if (args.length != 4){ 
			    sender.sendMessage("§2" + "/wvpa cp [Preference] [Value] [Target]" + "§r");
			    return false;
			}
			if (b.equalsIgnoreCase("IngameTP")){
			    String[] messages = {"be teleported Ingame.", "not"};
			    String[] cpargs = {"IngameTP", args[2], args[3]};
		     	PreferenceManager.changePreference(sender, messages, cpargs);
		     	return true;
			}
			
			else if (b.equalsIgnoreCase("CanBreakGlass")){
				String[] messages = {"be able to break glass.", "not"};
				String[] cpargs = {"CanBreakGlass", args[2], args[3]};
				PreferenceManager.changePreference(sender, messages, cpargs);
				return true;
			}
			
			else if (b.equalsIgnoreCase("CanPlaceGlass")){
				String[] messages = {"be able to place glass", "not"};
				String[] cpargs = {"CanPlaceGlass", args[2], args[3]};
				PreferenceManager.changePreference(sender, messages, cpargs);
				return true;
			}
			
			else if (b.equalsIgnoreCase("CanBreakBlocksPreGame")){
				String[] messages = {"be able to break blocks pre-game", "not"};
				String[] cpargs = {"CanBreakBlocksPreGame", args[2], args[3]};
				PreferenceManager.changePreference(sender, messages, cpargs);
				return true;
			}
			
	    } 

		else if (a.equalsIgnoreCase("lp")){
			if (b != null){
			if (b.equalsIgnoreCase("-m")){
				b = sender.getName();
			}
			}
			String[] allpref = {
				"§2IngameTP - Whether a player is teleported Ingame§r" + PreferenceManager.getStrValueForPlayer(b, "IngameTP"),
				"§2CanBreakGlass - Whether a player can break glass§r" + PreferenceManager.getStrValueForPlayer(b, "CanBreakGlass"),
				"§2CanPlaceGlass - Whether a player can place glass§r" + PreferenceManager.getStrValueForPlayer(b, "CanPlaceGlass"),
				"§2CanBreakBlocksPreGame - Whether a player can break blocks pre-game§r" + PreferenceManager.getStrValueForPlayer(b, "CanBreakBlocksPreGame")
			};
			sender.sendMessage(allpref);
			return true;
		}
		
		else if (a.equalsIgnoreCase("help")){
	    	sender.sendMessage("§2" + "Do \"/wvp help\" to view help." + "§r");
	    	return true;
	    }

		return false;
		}
    public static String getArgsOne(String[] args){
    	if (args.length < 2){
		    return null;
    	} else return args[1];
    }
}
