package io.github.fourohfour.wolvesvspigs;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

//Class that mainly gives players kits
public class Kit {
	
	//Give player a pig kit
	public static void Pig(Player pigee){
		pigee.getInventory().clear(); //Clear their inventory
		pigee.getActivePotionEffects().clear(); //And their potion effects
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

		ItemStack sword = createPigKit("s", Material.STONE_SWORD);

		ItemStack pick = createPigKit("s", Material.IRON_PICKAXE);

		ItemStack axe = createPigKit("s", Material.IRON_AXE);

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
		pigee.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
	}
	
	public static void Spectator(Player spectatee){
		spectatee.getInventory().clear();
		spectatee.getActivePotionEffects().clear();
		spectatee.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0));
		tele(spectatee);
	}
	
	//Give player a wolf kit
	public static void Wolf(Player wolfee){
		wolfee.getInventory().clear(); //Clear their inventory
		wolfee.getActivePotionEffects().clear(); //Potion effects cleared too
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
		ItemStack chest = createWolfKit("a", Material.CHAINMAIL_CHESTPLATE);
		
		ItemStack legs = createWolfKit("a", Material.CHAINMAIL_LEGGINGS);
		
		ItemStack boots = createWolfKit("a", Material.CHAINMAIL_BOOTS);
		
		ItemStack sword = createWolfKit("s", Material.IRON_SWORD);
		
		ItemStack pick = createWolfKit("s", Material.WOOD_PICKAXE);
		

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
		
		//remove speed
		wolfee.removePotionEffect(PotionEffectType.SPEED);
		//Give them mining fatigue
		wolfee.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, 2));
	}
	
	//Creates Kit for pigs
	public static ItemStack createPigKit(String t, Material m){
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
	
	public static ItemStack createWolfKit(String t, Material m){
		ItemStack item = new ItemStack(m);
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
    public static void tele(Player p) {
        Location centre = new Location(p.getWorld(), 0, 70, 0);
        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 5));
        p.teleport(centre);
    }
}
