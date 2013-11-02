package io.github.fourohfour.wolvesvspigs;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.github.fourohfour.countdown.Fight;
import io.github.fourohfour.countdown.PreGame;
import io.github.fourohfour.countdown.Prepare;
import io.github.fourohfour.wolvesvspigs.WvpCommandExecutor;
import io.github.fourohfour.wolvesvspigs.WvpaCommandExecutor;
import io.github.fourohfour.wolvesvspigs.WvpdCommandExecutor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public final class WolvesVSPigs extends JavaPlugin implements Listener{
	@Override
	public void onEnable(){
		Resources.setGlobalsToDefaults();
		getCommand("wvpa").setExecutor(new WvpaCommandExecutor(this));
		getCommand("wvp").setExecutor(new WvpCommandExecutor(this));
		getCommand("wvpd").setExecutor(new WvpdCommandExecutor(this));
		getServer().getPluginManager().registerEvents(this, this);
		new PreferenceManager(this);
		for (int playeri = 0; playeri < Bukkit.getOnlinePlayers().length; playeri++){
			Player target = Bukkit.getOnlinePlayers()[playeri];
			target.setMetadata("IngameTP", new FixedMetadataValue(this, true));
			target.setMetadata("CanBreakGlass", new FixedMetadataValue(this, false));
			target.setMetadata("CanPlaceGlass", new FixedMetadataValue(this, false));
			target.setMetadata("CanBreakBlocksPreGame", new FixedMetadataValue(this, false));
		}
	}
	@Override
	public void onDisable(){
		//stuff
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event){
		List<Integer> tobers = new ArrayList<Integer>();
		int droplen = event.getDrops().size();
		for (int dropi = 0; dropi < droplen; dropi++){
			if(event.getDrops().get(dropi).getItemMeta().hasLore()){
				if(event.getDrops().get(dropi).getItemMeta().getLore().get(0).equals("Unremovable")){
					Bukkit.getLogger().info("point");
					tobers.add(dropi);
				}
			}
		}
		event.getDrops().removeAll(tobers);
	}

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		if (event.getItemDrop().getItemStack().getItemMeta().hasLore()){
			if (event.getItemDrop().getItemStack().getItemMeta().getLore().get(0).equals("Unremovable")){
				event.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void onBlockPlace(final BlockPlaceEvent placeb){
		Material blockt = placeb.getBlockPlaced().getType();
		if (placeb.getPlayer().hasMetadata("CanPlaceGlass")){
			if (blockt == Material.GLASS && (placeb.getPlayer().getMetadata("CanPlaceGlass").get(0).asBoolean() == false)){
				Bukkit.getScheduler().runTask(this, new Runnable() {
					@Override
					public void run(){
						Block nblock = placeb.getBlock();
						nblock.setType(Material.SAND);
						Player playert = placeb.getPlayer();
						playert.sendMessage("§2" + "Glass is not allowed. Transmuting..." + "§r");
					}
				});
			}
		}
	}
	@EventHandler
	public void onBlockBreak(final BlockBreakEvent breakb){
		if((Globals.globalvars.get("gamestage").equals("pre-game")) && (breakb.getPlayer().getMetadata("CanBreakBlocksPreGame").get(0).asBoolean() == false)){
			breakb.getPlayer().sendMessage("§2"+ "You may not break blocks until the game has begun" + "§r");
			breakb.setCancelled(true);
		}
		Material blockt = breakb.getBlock().getType();
		if (blockt == Material.GLASS && (breakb.getPlayer().getMetadata("CanBreakGlass").get(0).asBoolean() == false)){
			Block ob = breakb.getBlock();
			final Location bloc = new Location(breakb.getPlayer().getWorld(), ob.getX(), ob.getY(), ob.getZ());
			Bukkit.getScheduler().runTask(this, new Runnable() {
				@Override
				public void run(){
					Player pl = breakb.getPlayer();
					pl.getWorld().getBlockAt(bloc).setType(Material.GLASS);
					pl.sendMessage("§2" + "You may not escape. Turn and Fight." + "§r");
				}
			});
		}
	}
	@EventHandler
	public void onJoinEvent(PlayerJoinEvent join){
		Player joined = join.getPlayer();
		joined.setMetadata("IngameTP", new FixedMetadataValue(this, true));
		joined.setMetadata("CanBreakGlass", new FixedMetadataValue(this, false));
		joined.setMetadata("CanPlaceGlass", new FixedMetadataValue(this, false));
		joined.setMetadata("CanBreakBlocksPreGame", new FixedMetadataValue(this, false));
	}

	@EventHandler
	public void onGameStateChangeEvent(GameStateChangeEvent event){
		String state = event.getState();
		if (state == "pre-game"){
			
			PluginDescriptionFile pyml = this.getDescription();
			String pver = pyml.getVersion();
			Bukkit.broadcastMessage("§2"+ "Wolves Vs Pigs" + "§r");
			Bukkit.broadcastMessage("§2"+ "Version "+ pver + "§r");
			Bukkit.broadcastMessage("§2"+ "Plugin by ObsidianFire99 AKA fourohfour" + "§r");
			Bukkit.broadcastMessage("§2"+ "/wvp help - Command and Game Help." + "§r");
			
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
				if (onp[index].getMetadata("IngameTP").get(0).asBoolean() == true){
					Kit.tele(onp[index]);
				}
			}
			
			PreGame cd = new PreGame();
			cd.run(Globals.cdpresets.get("pregame")[0], Globals.cdpresets.get("pregame")[1], this);
			Globals.countdowns.add(cd);
		}
		if (state == "prepare"){
			Prepare cd = new Prepare();
			cd.run(Globals.cdpresets.get("prepare")[0], Globals.cdpresets.get("prepare")[1], this);
			Globals.countdowns.add(cd);
		}
		if (state == "fight"){
			Fight cd = new Fight();
			cd.run(Globals.cdpresets.get("fight")[0], Globals.cdpresets.get("fight")[1], this);
			Globals.countdowns.add(cd);
	    }
	}

	public Boolean stopcount(){
		Bukkit.broadcastMessage("§2" + "Stopping Game..." + "§r");
		for (int idex = 0; idex < Globals.countdowns.size(); idex++){
			Globals.countdowns.get(idex).cancel();
		}
		Globals.globalvars.put("gamestage", "none");
		ScoreboardManager mainmanager = Bukkit.getScoreboardManager();
		Scoreboard mainboard = mainmanager.getMainScoreboard();
		Set<Team> teams = mainboard.getTeams();
		Team[] tarray = teams.toArray(new Team[teams.size()]);
		for (int index = 0; index < tarray.length; index++){
			Team targett = tarray[index];
			targett.unregister();
		}
		for (int indexp = 0; indexp < Bukkit.getOnlinePlayers().length; indexp ++){
			clearinv(Bukkit.getOnlinePlayers()[indexp]);

		}

		Bukkit.broadcastMessage("§2" + "Game Stopped" + "§r");
		return true;
	}

	public static void clearinv(Player targ){
		PlayerInventory inv = targ.getInventory();
		inv.setArmorContents(new ItemStack[4]);
		inv.clear();
		targ.removePotionEffect(PotionEffectType.SPEED);
	}
}

