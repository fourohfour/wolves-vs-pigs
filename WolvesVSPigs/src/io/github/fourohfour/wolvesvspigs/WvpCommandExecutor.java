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
		String a = args[0];
		if (a.equals("help")) {
		    sender.sendMessage("§2" + "=-=-=Wolves VS Pigs Help=-=-=" + "§r");
		    String[] bhelparray = {"§2" + "/wvp help - displays help" + "§r"};
		    String[] ahelparray = {"§2" + "/wvpa start - Starts the game" + "§r","§2" + "/wvpa stop - Stops the game" + "§r"};
		    sender.sendMessage(bhelparray);
		    if (sender.hasPermission("WolvesVSPigs.admin")){
			    sender.sendMessage(ahelparray);
		    }
		    return true;
		}
		return false;
	}
}