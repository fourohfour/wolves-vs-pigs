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
		    String[] bhelparray = {"§2" + "/wvp help - displays help" + "§r"};
		    String[] ahelparray = {"§2" + "/wvpa start <time> - Starts the game. You can add a custom timer." + "§r","§2" + "/wvpa stop - Stops the game" + "§r", "§2" + "/wvpa nogb <Player> <-m> - Player cannot break glass. Use -m to target self." + "§r","§2" + "/wvpa gb <Player> <-m>- Player can break glass. Use -m to target self." + "§r"};
		    String[] dhelparray = {"§2" + "/wvpd notp <Player> <-m> - Player will not be teleported ingame. Use -m to target self." + "§r","§2" + "/wvpd tp <Player> <-m>- Player be teleported ingame. Use -m to target self." + "§r"};
		    sender.sendMessage(bhelparray);
		    if (sender.hasPermission("WolvesVSPigs.admin")){
			    sender.sendMessage(ahelparray);
		    } if (sender.hasPermission("WolvesVSPigs.dev")){
		    	sender.sendMessage(dhelparray);
		    }
		    return true;
		}
		return false;
	}
}