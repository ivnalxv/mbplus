package me.metaMockery.mobs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	@Override
	public void onEnable() {
		// server startup
		// reloads
		// plugin reloads
		
		this.getServer().getPluginManager().registerEvents(new ListenerEvents(this), this);
		this.getServer().getPluginManager().registerEvents(new SpawnEvents(this), this);
		this.getServer().getPluginManager().registerEvents(new LootEvents(this), this);
	}
	
	@Override
	public void onDisable() {
		// server shutdown
		// reloads
		// plugin reloads
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("fly")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("*console goes flying*");
				return true;
			}
			Player player = (Player) sender;
			
			if (player.getAllowFlight()) player.setAllowFlight(false);
			else player.setAllowFlight(true);
			
			return true;
		}
		
		
		if (label.equalsIgnoreCase("mbp_item")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Sorry console");
				return true;
			}
			
			Player player = (Player) sender;
			
			if (args.length == 0) {
				sender.sendMessage("Choose item!");
				sender.sendMessage("* hungry_bone");
				sender.sendMessage("* heavy_sword");
				sender.sendMessage("* golden_scythe");
				sender.sendMessage("* repair_kit");
				sender.sendMessage("* endless_pearl");
				return true;
			}
			
			int num = 1;
			if (args.length > 1 && isNum(args[1]))
				num = Integer.parseInt(args[1]);
			String item_name = "";
			Location loc = player.getLocation();
			World world = player.getWorld();
			
			if (args[0].equalsIgnoreCase("hungry_bone")) 
				item_name = "hungry_bone";
			
			if (args[0].equalsIgnoreCase("heavy_sword")) 
				item_name = "heavy_sword";
			
			if (args[0].equalsIgnoreCase("golden_scythe")) 
				item_name = "golden_scythe";
			
			if (args[0].equalsIgnoreCase("repair_kit")) 
				item_name = "repair_kit";
			
			if (args[0].equalsIgnoreCase("endless_pearl")) 
				item_name = "endless_pearl";
			
			if (item_name == "") return true;
			
			for (int i = 0; i < num; i++) {
				world.dropItemNaturally(loc, getItem(item_name));
			}
			
			
			
			
			return true;
			
		}
		
		return false;
	}
	
	
	
	public ItemStack getItem(String name) {
		if (name.equalsIgnoreCase("hungry_bone")) {
			ItemStack item;
			item = new ItemStack(Material.BONE);
			ItemMeta meta = item.getItemMeta();
			List<String> lore = new ArrayList<String>();
			
			
			meta.setDisplayName("Hungry Bone");
			lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "Inflicts:");
			lore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "*  Hunger III 0:15");
			lore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "*  Weakness II 0:15");
			//lore.add(ChatColor.BLUE + "" + ChatColor.ITALIC + "Deals 6 Damage");
			meta.setLore(lore);
			
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			
			item.setItemMeta(meta);
			return item;
		}
		if (name.equalsIgnoreCase("heavy_sword")) {
			ItemStack item;
			item = new ItemStack(Material.IRON_SWORD);
			ItemMeta meta = item.getItemMeta();
			List<String> lore = new ArrayList<String>();
			
			
			meta.setDisplayName("Heavy Sword");
			
			lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "Inflicts on wielder:");
			lore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "*  Slowness I");
			lore.add(ChatColor.BLUE + "" + ChatColor.ITALIC + "Deals 10 " + 
					 ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "" + ChatColor.BOLD + "True" + "" + 
					 ChatColor.BLUE + "" + ChatColor.ITALIC + " Damage");
			meta.setLore(lore);
			
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			
			item.setItemMeta(meta);
			return item;
		}
		if (name.equalsIgnoreCase("golden_scythe")) {
			ItemStack item;
			item = new ItemStack(Material.GOLDEN_HOE);
			ItemMeta meta = item.getItemMeta();
			List<String> lore = new ArrayList<String>();
			
			meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Golden Scythe");
			
			lore.add(ChatColor.YELLOW + "" + ChatColor.ITALIC + "Heart of Gold");
			lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "   Gives you 3 Golden Hearts");
			lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "   for killing creatures");
			
			
			lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "Midas' Touch");
			lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "   Have a chance to turn");
			lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "   creature's body to gold");
			lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "   upon killing it");
			
			
			
			lore.add("");
			lore.add(ChatColor.BLUE + "" + ChatColor.ITALIC + "Deals 16 " + 
					 ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "" + ChatColor.BOLD + "True" + "" + 
					 ChatColor.BLUE + "" + ChatColor.ITALIC + " Damage");
			meta.setLore(lore);
			
			
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			
			item.setItemMeta(meta);
			
			return item;
		}
		if (name.equalsIgnoreCase("repair_kit")) {
			ItemStack item;
			item = new ItemStack(Material.PAPER);
			ItemMeta meta = item.getItemMeta();
			List<String> lore = new ArrayList<String>();
			
			meta.setDisplayName(ChatColor.GREEN + "Repair Kit");
			
			lore.add(ChatColor.BLUE + "Restores " + ChatColor.GOLD + "100" + ChatColor.BLUE + " Durability");
			lore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "Left click to use: ");
			lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "   Restores 100 durability");
			lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "   of an item in off hand");
			
			meta.setLore(lore);
			meta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
			
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			
			
			item.setItemMeta(meta);
			
			return item;
		}
		if (name.equalsIgnoreCase("endless_pearl")) {
			ItemStack item;
			item = new ItemStack(Material.ENDER_PEARL);
			ItemMeta meta = item.getItemMeta();
			
			
			meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Endless Pearl");		
			
			meta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			
			
			item.setItemMeta(meta);
			
			return item;
		}
		
		
		return null;
	}
	
	
	public void randomTeleport(Entity e) {
		Location loc = (Location) e.getLocation();
		Random r = new Random();
		
		if (r.nextBoolean()) loc.setX(loc.getX() + r.nextInt(300));
		else loc.setX(loc.getX() - r.nextInt(300));
		
		if (r.nextBoolean()) loc.setY(loc.getY() + r.nextInt(70));
		else {
			if (loc.getY() - r.nextInt(70) > 5.0) loc.setY(loc.getY() - r.nextInt(70));
			else loc.setY(5.0);
		}
		
		if (r.nextBoolean()) loc.setZ(loc.getZ() + r.nextInt(300));
		else loc.setZ(loc.getZ() - r.nextInt(300));
		
		e.teleport(loc);
	}
	
	public boolean isNum(String num) {
		try {
			Integer.parseInt(num);
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
