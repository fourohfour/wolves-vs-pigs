package io.github.fourohfour.wolvesvspigs;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.github.fourohfour.wolvesvspigs.Countdown;
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
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitWorker;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public final class WolvesVSPigs extends JavaPlugin implements Listener{
	@Override
	public void onEnable(){
		getCommand("wvpa").setExecutor(new WvpaCommandExecutor(this));
		getCommand("wvp").setExecutor(new WvpCommandExecutor(this));
		getCommand("wvpd").setExecutor(new WvpdCommandExecutor(this));
		getServer().getPluginManager().registerEvents(this, this);
		for (int playeri = 0; playeri < Bukkit.getOnlinePlayers().length; playeri++){
			Bukkit.getOnlinePlayers()[playeri].setMetadata("notele", new FixedMetadataValue(this, false));
			Bukkit.getOnlinePlayers()[playeri].setMetadata("nogb", new FixedMetadataValue(this, true));
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
	for (int toi = 0; toi<tobers.size(); toi++){
		event.getDrops().removeAll(tobers);
	}
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
		if (blockt == Material.GLASS){
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
	@EventHandler
	public void onBlockBreak(final BlockBreakEvent breakb){
		    Material blockt = breakb.getBlock().getType();
		    if (blockt == Material.GLASS && (breakb.getPlayer().getMetadata("nogb").get(0).asBoolean() == true)){
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
		joined.setMetadata("notele", new FixedMetadataValue(this, false));
		joined.setMetadata("nogb", new FixedMetadataValue(this, true));
	}
	public void triggercount(final int runTime,final String runType,final int runFactor,final String[] runReasons, final Boolean runIsComp){
		Bukkit.getScheduler().runTaskAsynchronously(this, new Runnable() {
	    	@Override
	    	public void run() {
	    		Countdown.count(runTime, runType, runFactor, runReasons, runIsComp);
	    	}
	    });
	}
	
	public Boolean stopcount(){
		Bukkit.broadcastMessage("§2" + "Stopping Game..." + "§r");
		BukkitWorker aws = Bukkit.getServer().getScheduler().getActiveWorkers().get(0);
		int awsid = aws.getTaskId();
		Bukkit.getScheduler().cancelTask(awsid);
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

