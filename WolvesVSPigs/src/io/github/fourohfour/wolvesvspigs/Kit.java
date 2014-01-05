//Kit class
//Has classes to give Pig, Wolf and Spectator kits to players
//By FourOhFour
//http://fourohfour.github.io

package io.github.fourohfour.wolvesvspigs;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

//Class that mainly gives players kits
public class Kit {
	public JavaPlugin plugin;
	public Kit(JavaPlugin plugin){
		this.plugin = plugin;
	}
	//Give player a pig kit
	public void Pig(final Player pigee){
		pigee.setGameMode(GameMode.SURVIVAL);
		pigee.setAllowFlight(false);
		
		clearInventory(pigee);

		//Create a head item stack
		ItemStack head = new ItemStack(397, 1, (short) 3);
		//Get it's meta
		SkullMeta meta = (SkullMeta) head.getItemMeta();
		//Set the meta owner to the Minecraft Pig Head
		meta.setOwner("MHF_Pig");
		//Set it's name to Pig Head
		meta.setDisplayName("Pig Head");
		//Save the meta and add the Unremovable tag
		head.setItemMeta(meta);
		head = addLore(head, "§a" + "Sticky");
		
		//Create some more item stacks for the other kit
		//u is unremovable (for armour), and s is for sticky. Sticky can be moved around in the inventory.
		ItemStack chest = createPigKit("a", Material.LEATHER_CHESTPLATE);

		ItemStack legs = createPigKit("a", Material.LEATHER_LEGGINGS);

		ItemStack sword = createPigKit("s", Material.STICK);

		ItemStack pick = createPigKit("s", Material.IRON_PICKAXE);

		ItemStack axe = createPigKit("s", Material.IRON_AXE);
		
		sword.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
		
		//Give player the equipment
		EntityEquipment equ = pigee.getPlayer().getEquipment();
		equ.setHelmet(head);
		equ.setChestplate(chest);
		equ.setLeggings(legs);
		
		//Get the player's inventory
		Inventory targinv = pigee.getInventory();
		
		//And add the items
		targinv.addItem(sword);
		targinv.addItem(pick);
		targinv.addItem(axe);
		
		//Give them speed indefinatly
		Bukkit.getScheduler().runTask(plugin, new Runnable() {

			@Override
			public void run() {
				clearPotionEffects(pigee);
				pigee.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
			}
		});
	}
	
	public void Spectator(Player spectatee){
		clearInventory(spectatee);
		clearPotionEffects(spectatee);
		spectatee.setAllowFlight(true);
		spectatee.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0));
		this.tele(spectatee);
	}
	
	//Give player a wolf kit
	public void Wolf(final Player wolfee){
		wolfee.setAllowFlight(false);
		
		clearInventory(wolfee);
		
		//Create a head item stack
		ItemStack head = new ItemStack(397, 1, (short) 3);
		//Get it's meta
		SkullMeta meta = (SkullMeta) head.getItemMeta();
		//Set the meta owner to the Wolf Head
		//From this thread: http://www.reddit.com/r/MinecraftHeads/comments/1tm13e/request_hostile_wolf/
		meta.setOwner("Pablo_Penguin");
		//Set it's name to Wolf Head
		meta.setDisplayName("Wolf Head");

		//Save the meta and add the Unremovable tag
		head.setItemMeta(meta);
		head = addLore(head, "§a" + "Sticky");

		//Create some more item stacks for the other kit
		//u is unremovable (for armour), and s is for sticky. Sticky can be moved around in the inventory.
		ItemStack chest = createWolfKit("a", Material.CHAINMAIL_CHESTPLATE, 1);
		
		ItemStack legs = createWolfKit("a", Material.CHAINMAIL_LEGGINGS, 1);
		
		ItemStack boots = createWolfKit("a", Material.CHAINMAIL_BOOTS, 1);
		
		ItemStack sword = createWolfKit("s", Material.IRON_SWORD, 1);
		
		ItemStack pick = createWolfKit("s", Material.WOOD_PICKAXE, 1);
		
		ItemStack ladders = createWolfKit("s", Material.LADDER, 64);
		
		ItemStack comp = createWolfKit("s", Material.COMPASS, 1);
		
		//Give player the equipment
		EntityEquipment equ = wolfee.getPlayer().getEquipment();
		equ.setHelmet(head);
		equ.setChestplate(chest);
		equ.setLeggings(legs);
		equ.setBoots(boots);

		//Get the player's inventory
		Inventory targinv = wolfee.getInventory();

		//And add the items
		targinv.addItem(sword);
		targinv.addItem(pick);
		targinv.addItem(ladders);
		targinv.addItem(comp);
		
		//Give them mining fatigue
		Bukkit.getScheduler().runTask(plugin, new Runnable() {

			@Override
			public void run() {
				clearPotionEffects(wolfee);
				wolfee.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, 2));
			}
		});
	}
	
	//Creates Kit for pigs
	public ItemStack createPigKit(String t, Material m){
		if (t == "a"){
			ItemStack item = colorLeatherArmor(m,  Color.fromRGB(255, 92, 205));
			item = addLore(item, "§a" + "Sticky");
			return item;
		} else if (t == "s"){
			ItemStack item = new ItemStack(m);
			item = addLore(item, "§a" + "Sticky");
			return item;
		}
		return null;
	}
	
	public ItemStack createWolfKit(String t, Material m, int a){
		ItemStack item = new ItemStack(m, a);
		if (t == "a"){
			item = addLore(item, "§a" + "Sticky");
			return item;
		} else if (t == "s"){
			item = addLore(item, "§a" + "Sticky");
			return item;
		}
		return null;
	}
	
	//Colours armour a certain colour.
	public static ItemStack colorLeatherArmor(Material material, Color color) {
		ItemStack item = new ItemStack(material);
		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(color);
		item.setItemMeta(meta);
		return item;
		}
	
	//Adds lore to an item
    public static ItemStack addLore(ItemStack item, String lore) {
        ItemStack result = item;
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> l = new ArrayList<String>();
        l.add(lore);
        meta.setLore(l);
        result.setItemMeta(meta);
       
        return result;
    }
    
    //Teleports a player to the centre and gives the resistance so they don't die from the drop
    public void tele(Player p) {
        Location centre = new Location(p.getWorld(), 0, 70, 0);
        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 5));
        p.teleport(centre);
    }
    
    public static Player clearPotionEffects(Player p){
    	for(PotionEffect effect : p.getActivePotionEffects())
    	{
    	    p.removePotionEffect(effect.getType());
    	}
    	return p;
    }
    
    public static Player clearInventory(Player p){
    	p.getInventory().clear();
    	p.getInventory().setArmorContents(new ItemStack[4]);
    	return p;
    }
}
