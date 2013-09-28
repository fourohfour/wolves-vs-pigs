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
		if (a.equalsIgnoreCase("notp")){
			if (args.length == 1){
				String[] ms = {"§2" + "Use \"/wvpd notp -m\" to not get teleported ingame." + "§r","§2" +  "Use \"/wvpd notp [Player]\" to stop a player being teleported ingame." + "§r"};
				sender.sendMessage(ms);
				return true;
			}
		    String b = args[1];
		    if (b.equalsIgnoreCase("-m")){
				Player target = (Player) sender;
				target.setMetadata("notele", new FixedMetadataValue(plugin, true));
				target.sendMessage("§2" + "You will now not be teleported ingame");
				return true;
		    } else if (true){
			    for (int pindex = 0; pindex< Bukkit.getOnlinePlayers().length; pindex++){
			        if (Bukkit.getServer().getPlayer(b) == Bukkit.getOnlinePlayers()[pindex]){
					    Player target = Bukkit.getOnlinePlayers()[pindex];
					    target.setMetadata("notele", new FixedMetadataValue(plugin, true));
			 		    sender.sendMessage("§2" + target.getName() + " will now not be teleported ingame" + "§r");
			    		return true;
		    	    }
		    	}
			    sender.sendMessage("§2" + b + " is not online. Do /wvp help to view help." + "§r");
    		}
	    } else if (a.equalsIgnoreCase("tp")){
			if (args.length == 1){
				String[] ms = {"§2" + "Use \"/wvpd tp -m\" to get teleported ingame." + "§r","§2" +  "Use \"/wvpd tp [Player]\" to let a player teleported ingame." + "§r"};
				sender.sendMessage(ms);
				return true;
			}
		    String b = args[1];
		    if (b.equalsIgnoreCase("-m")){
				Player target = (Player) sender;
				target.setMetadata("notele", new FixedMetadataValue(plugin, false));
				target.sendMessage("§2" + "You will now be teleported ingame");
				return true;
		    } else if (true){
			    for (int pindex = 0; pindex< Bukkit.getOnlinePlayers().length; pindex++){
			        if (Bukkit.getServer().getPlayer(b) == Bukkit.getOnlinePlayers()[pindex]){
					    Player target = Bukkit.getOnlinePlayers()[pindex];
					    target.setMetadata("notele", new FixedMetadataValue(plugin, false));
			 		    sender.sendMessage("§2" + target.getName() + " will now be teleported ingame" + "§r");
			    		return true;
		    	    }
		    	}
			    sender.sendMessage("§2" + b + " is not online. Do /wvp help to view help." + "§r");
    		}
	    } else if (a.equalsIgnoreCase("help")){
	    	sender.sendMessage("§2" + "Do \"/wvp help\" to view help." + "§r");
	    	return true;
	    }
		return false;
}
}