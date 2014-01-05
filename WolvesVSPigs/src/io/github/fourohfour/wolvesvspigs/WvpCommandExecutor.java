//Command Executor for WVP commands
//Initialised by main class in on enable
//By FourOhFour
//http://fourohfour.github.io

package io.github.fourohfour.wolvesvspigs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WvpCommandExecutor implements CommandExecutor{
	
	@SuppressWarnings("unused")
	private WolvesVSPigs plugin;
	
	public WvpCommandExecutor(WolvesVSPigs plugin){
		this.plugin = plugin;
}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 0){
			return false;
		}
		String a = args[0];
		if (a.equalsIgnoreCase("help")) {
		    sender.sendMessage("§2" + "=-=-=Wolves VS Pigs Help=-=-=" + "§r");
		    
		    String[] tiparray = {
		    	"§2" + "TIPS:" + "§r",
		    	"§2" + "When Targeting a Player, use their name or use -m to target oneself." + "§r",
		        };
		    
		    String[] bhelparray = {
			    "§2" + "BASIC COMMANDS:" + "§r",
		    	"§2" + "/wvp help - displays help" + "§r",
		    	"§2" + "/wvp timeleft - how long is left on the timer" + "§r"
		        };
		    
		    String[] ahelparray = {
			    "§2" + "ADMIN COMMANDS:" + "§r",
		    	"§2" + "/wvpa start <time> - Starts the game. You can add a custom timer." + "§r",
		    	"§2" + "/wvpa stop - Stops the game" + "§r",
		    	"§2" + "/wvpa cp [Preference] [Value] [Target] - Change Preference" + "§r",
		    	"§2" + "/wvpa lp <Target> - Lists all Preferences. Add Target to view a player's values." + "§r"
		    	};
		    
		    String[] dhelparray = {
			    "§2" + "DEV COMMANDS:" + "§r",
		    	"§2" + "/wvpd getGameStage - Gets the current gamestage." + "§r"
		        };
		    
		    sender.sendMessage(bhelparray);
		    if (sender.hasPermission("WolvesVSPigs.admin")){
			    sender.sendMessage(ahelparray);
		    } if (sender.hasPermission("WolvesVSPigs.dev")){
		    	sender.sendMessage(dhelparray);
		    }
		    sender.sendMessage(tiparray);
		    return true;
		}
		if (a.equalsIgnoreCase("timeleft")) {
			sender.sendMessage("§2" + Globals.globalvars.get("cleft").toString() + "§r");
			return true;
		}
		return false;
	}
}