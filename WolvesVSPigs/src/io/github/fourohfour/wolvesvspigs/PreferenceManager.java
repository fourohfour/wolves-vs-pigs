//Contains the WVP Preference "API"

package io.github.fourohfour.wolvesvspigs;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

public class PreferenceManager {
	
	private static WolvesVSPigs plugin;
	
	public PreferenceManager(WolvesVSPigs plugin){
		PreferenceManager.plugin = plugin;
	}
	
	public static boolean changePreference(CommandSender Sender, String[] Messages, String[] args){

		String b = args[2];
		Boolean val = Resources.StrToBoolean(args[1]);
		
		if (val == null){
			Sender.sendMessage("§2" + "Please give a preference value of True or False." + "§r");
			return true;
		}

		if (b.equalsIgnoreCase("-m")){
			Player target = (Player) Sender;
			target.setMetadata(args[0], new FixedMetadataValue(plugin, val));
			if (val == true){
			    Sender.sendMessage("§2" + "You will now " + Messages[0] + "§r");
			    return true;
			} else if (val == false){
				Sender.sendMessage("§2" + "You will now " + Messages[1] + " " + Messages[0] + "§r");
				return true;
			}
		}
		
		else for (int pindex = 0; pindex< Bukkit.getOnlinePlayers().length; pindex++){
				if (Bukkit.getServer().getPlayer(b) == Bukkit.getOnlinePlayers()[pindex]){
					Player target = Bukkit.getOnlinePlayers()[pindex];
					setPlayerMetadata(target, args[0], val, plugin);
					if (val == true){
					    Sender.sendMessage("§2" + target.getName() + " will now " + Messages[0] + "§r");
					    return true;
					} else if (val == false){
						Sender.sendMessage("§2" + target.getName() + " will now " + Messages[1] + " " + Messages[0] + "§r");
						return true;
					}
				}
		}
		Sender.sendMessage("§2" + b + " is not online. Do /wvp help to view help." + "§r");
		return false;
	}
	
	public static String getStrValueForPlayer(String target, String key){
		if (target == null){
			return "";
		}

	    Object targetp = Bukkit.getPlayer(target);
	    if (targetp == null){
	    	return "§2 - Player Not Online§r";
	    }
	    
	    Player targetpl = Bukkit.getPlayer(target);
	    Boolean targm = targetpl.getMetadata(key).get(0).asBoolean();
	    
	    if (targm){
	    	String targmas = "§2 - §r§ATrue§r";
	    	return targmas;
	    } else {
	    	String targmas = "§2 - §r§CFalse§r";
	    	return targmas;
	    }
	    
	}
	public static void setPlayerMetadata(Entity player, String key, Object value, Plugin plugin){
		 player.setMetadata(key,new FixedMetadataValue(plugin,value));
		}
}
