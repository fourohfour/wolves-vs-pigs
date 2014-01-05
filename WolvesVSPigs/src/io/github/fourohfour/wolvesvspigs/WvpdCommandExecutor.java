//Command Executor for WVPD commands
//Initialised by main class in on enable
//By FourOhFour
//http://fourohfour.github.io

package io.github.fourohfour.wolvesvspigs;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class WvpdCommandExecutor implements CommandExecutor{
	
	private WolvesVSPigs plugin;
	
	public WvpdCommandExecutor(WolvesVSPigs plugin){
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 0){
			return false;
		}
		String a = args[0];
		if (a.equalsIgnoreCase("getGlobalVar")){
			if (args.length<2){
				sender.sendMessage("§2" + "Please state a global value to get the value of." + "§r");
				return true;
			}
			String b = args[1];
			String bval = Globals.globalvars.get(b).toString();
			sender.sendMessage(bval);
			return true;
		}
		if (a.equalsIgnoreCase("setCountdownTime")){
			if (args.length<2){
				sender.sendMessage("§2" + "Please state parameters - countdown runtime factor." + "§r");
				return true;
			}
			Integer[] argumentl = {Integer.parseInt(args[2]), Integer.parseInt(args[3])};
			Globals.cdpresets.put(args[1], argumentl);
			return true;
		}
		if (a.equalsIgnoreCase("getCountdownTime")){
			sender.sendMessage(Globals.cdpresets.get(args[1])[0].toString() + " " + Globals.cdpresets.get(args[1])[1].toString());
			return true;
		}
	if (a.equalsIgnoreCase("setbasemeta")){
		String b = args[1];
		Player target = Bukkit.getPlayer(b);
		target.setMetadata("IngameTP", new FixedMetadataValue(plugin, true));
		target.setMetadata("CanBreakGlass", new FixedMetadataValue(plugin, false));
		target.setMetadata("CanPlaceGlass", new FixedMetadataValue(plugin, false));
		target.setMetadata("CanBreakBlocksPreGame", new FixedMetadataValue(plugin, false));
		return true;
	}
		return false;
}
}