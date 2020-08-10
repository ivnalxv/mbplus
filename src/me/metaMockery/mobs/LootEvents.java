package me.metaMockery.mobs;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;


public class LootEvents implements Listener {
	static Main plugin;
	public LootEvents(Main instance) {
		plugin = instance;
	}
	
	Random r = new Random();
	
	
	@EventHandler
	public void onDeathLooting(EntityDeathEvent event) {
		if (event.getEntityType() == EntityType.CREEPER) 
			if (event.getEntity().getCustomName().contains("Ender Creeper")) {
				if (r.nextInt(1000) < 250) {
					Location loc = (Location) event.getEntity().getLocation();
					loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.ENDER_PEARL));
					if (r.nextInt(1000) < 500) loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.ENDER_PEARL));
				}
				
			}
		
	}
	
}