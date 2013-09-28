package io.github.fourohfour.wolvesvspigs;

import java.util.ArrayList;

import org.bukkit.Color;
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

public class Kit {
	public static void Pig(Player pigee){
		ItemStack head = new ItemStack(397, 1, (short) 3);
		SkullMeta meta = (SkullMeta) head.getItemMeta();
		meta.setOwner("MHF_Pig");
		meta.setDisplayName("Pig Head");
		

		head.setItemMeta(meta);
		head = addLore(head, "Unremovable");
		
		ItemStack chest = createPigKit("ua", Material.LEATHER_CHESTPLATE);
		
		ItemStack legs = createPigKit("ua", Material.LEATHER_LEGGINGS);
		
		ItemStack sword = createPigKit("u", Material.STONE_SWORD);
		
		ItemStack pick = createPigKit("u", Material.IRON_PICKAXE);
		
		ItemStack axe = createPigKit("u", Material.IRON_AXE);
		
		EntityEquipment equ = pigee.getPlayer().getEquipment();
		equ.setHelmet(head);
		equ.setChestplate(chest);
		equ.setLeggings(legs);
		
		Inventory targinv = pigee.getInventory();
		
		targinv.addItem(sword);
		targinv.addItem(pick);
		targinv.addItem(axe);
		
		pigee.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
	}
	public static ItemStack createPigKit(String t, Material m){
		if (t == "ua"){
			ItemStack item = colorLeatherArmor(m,  Color.fromRGB(255, 92, 205));
			item = addLore(item, "Unremovable");
			return item;
		} else if (t == "u"){
			ItemStack item = new ItemStack(m);
			item = addLore(item, "Unremovable");
			return item;
		} else if (t == "s"){
			ItemStack item = new ItemStack(m);
			item = addLore(item, "Sticky");
			return item;
		}
		return null;
	}
	public static ItemStack colorLeatherArmor(Material material, Color color) {
		ItemStack item = new ItemStack(material);
		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(color);
		item.setItemMeta(meta);
		return item;
		}
	
    public static ItemStack addLore(ItemStack item, String lore) {
        ItemStack result = item;
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> l = new ArrayList<String>();
        l.add(lore);
        meta.setLore(l);
        result.setItemMeta(meta);
       
        return result;
    }
}
