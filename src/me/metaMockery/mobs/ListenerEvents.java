package me.metaMockery.mobs;



import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ListenerEvents implements Listener {
	static Main plugin;
	public ListenerEvents(Main instance) {
		plugin = instance;
	}
	
	Random r = new Random();
	
	
	
	@EventHandler
	public void onDamageByEntity(EntityDamageByEntityEvent event) {
		LivingEntity damager = (LivingEntity) event.getDamager();
		
		// Hungry Bone
		if (damager.getEquipment().getItemInMainHand().getType().equals(Material.BONE))
			if (damager.getEquipment().getItemInMainHand().getItemMeta().getDisplayName().contains("Hungry Bone"))
				if (damager.getEquipment().getItemInMainHand().getItemMeta().hasLore()) {
					LivingEntity damagee = (LivingEntity) event.getEntity();
					//each 20 ticks 1 second
					PotionEffect pe = new PotionEffect(PotionEffectType.HUNGER, 300, 2);
					damagee.addPotionEffect(pe);
					pe = new PotionEffect(PotionEffectType.WEAKNESS, 300, 1);
					damagee.addPotionEffect(pe);
					//damagee.damage(6.0D);
				}
		
		// Heavy Sword
		if (damager.getEquipment().getItemInMainHand().getType().equals(Material.IRON_SWORD))
			if (damager.getEquipment().getItemInMainHand().getItemMeta().getDisplayName().contains("Heavy Sword"))
				if (damager.getEquipment().getItemInMainHand().getItemMeta().hasLore()) {
					LivingEntity damagee = (LivingEntity) event.getEntity();
					//each 20 ticks 1 second
					damagee.damage(10.0D);
				}
		
		// Golden Scythe
		if (damager.getEquipment().getItemInMainHand().getType().equals(Material.GOLDEN_HOE))
			if (damager.getEquipment().getItemInMainHand().getItemMeta().getDisplayName().contains("Golden Scythe"))
				if (damager.getEquipment().getItemInMainHand().getItemMeta().hasLore()) {
					LivingEntity damagee = (LivingEntity) event.getEntity();
					//each 20 ticks 1 second
					damagee.damage(16.0D);
					PotionEffect pe = new PotionEffect(PotionEffectType.HEAL, 1, 0);
					damager.addPotionEffect(pe);
				}
		
		// Silent Killer, Rare Spider
		if (damager.getCustomName().contains("Silent Killer")) {
			LivingEntity damagee = (LivingEntity) event.getEntity();
			PotionEffect pe = new PotionEffect(PotionEffectType.POISON, 12000, 4);
			damagee.addPotionEffect(pe);
			pe = new PotionEffect(PotionEffectType.WITHER, 12000, 4);
			damagee.addPotionEffect(pe);
		}	
			
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		if (event.getEntityType().equals(EntityType.PLAYER)) {
			if (event.getCause().equals(DamageCause.FIRE_TICK) || event.getCause().equals(DamageCause.FIRE)) {
				event.getEntity().setFireTicks(19999980);
			}
		}
		
		if (event.getEntityType() == EntityType.ZOMBIE) 
			if (event.getEntity().getCustomName().contains("Swarm")) {
				Location loc = event.getEntity().getLocation();
				loc.setY(loc.getY() + 1);
				
				for (int i = 1; i < r.nextInt(5); i++) {
					loc.getWorld().spawnEntity(loc, EntityType.SILVERFISH);
				}
			}
		if (event.getEntityType() == EntityType.SPIDER)
			if (event.getEntity().getCustomName().contains("Broodmother")) {
				Location loc = event.getEntity().getLocation();
				loc.setY(loc.getY() + 1);
				
				for (int i = 1, j = r.nextInt(2); i <= j; i++) {
					loc.getWorld().spawnEntity(loc, EntityType.CAVE_SPIDER);
				}
			}
		
	}
	
	@EventHandler
	public void onExplosion(EntityExplodeEvent event) {
		if (event.getEntityType() == EntityType.CREEPER) 
			if (event.getEntity().getCustomName().contains("Nether Creeper")) {
				event.setCancelled(true);
				Location loc = event.getLocation();
				loc.getWorld().createExplosion(event.getLocation(), 4F, true);
				
				for (Entity entity: event.getEntity().getNearbyEntities(4, 4, 4)) {
					entity.setFireTicks(198000);
				}
				
				int x = (int) loc.getX();
				int y = (int) loc.getY();
				int z = (int) loc.getZ();
				
				for (int xx = -5; xx <= 5; xx++) {
					loc.setX(x + xx);
					for (int yy = -5; yy <= 5; yy++) {
						loc.setY(y + yy);
						for (int zz = -5; zz <= 5; zz++) {
							loc.setZ(z + zz);
							
							if (loc.getBlock().getType().equals(Material.FIRE)) {
								loc.setY(loc.getY() - 1);
								if ( !(loc.getBlock().getType().equals(Material.BEDROCK)) ) {
									loc.getBlock().setType(Material.NETHERRACK);
									loc.setY(loc.getY() + 1);
									loc.getBlock().setType(Material.FIRE);
								}
							}
						}
					}
				}
				
			}
		
		if (event.getEntityType() == EntityType.CREEPER) 
			if (event.getEntity().getCustomName().contains("Ender Creeper")) {
				event.setCancelled(true);
						
				for (Entity entity: event.getEntity().getNearbyEntities(5, 5, 5)) {
					if (entity.getType().equals(EntityType.PLAYER)) {
						entity.getLocation().getWorld().playSound(entity.getLocation(), 
								Sound.ENTITY_ENDERMAN_TELEPORT, 1.5F, 1.5F);
						plugin.randomTeleport(entity);
						entity.getLocation().getWorld().playSound(entity.getLocation(), 
								Sound.BLOCK_PORTAL_TRAVEL, 1.5F, 1.5F);
					}
				}
			}
		
		if (event.getEntityType() == EntityType.CREEPER) 
			if (event.getEntity().getCustomName().contains("Ice Creeper")) {
				event.setCancelled(true);
				Location loc = event.getLocation();
				loc.getWorld().createExplosion(event.getLocation(), 4F, false);
				
				int x = (int) loc.getX();
				int y = (int) loc.getY();
				int z = (int) loc.getZ();
				
				for (int xx = -5; xx <= 5; xx++) {
					loc.setX(x + xx);
					for (int yy = -5; yy <= 5; yy++) {
						loc.setY(y + yy);
						for (int zz = -5; zz <= 5; zz++) {
							loc.setZ(z + zz);
							
							if (loc.getBlock().getType().equals(Material.WATER)) {
								loc.getBlock().setType(Material.PACKED_ICE);
							}
							else if ( !(loc.getBlock().getType().equals(Material.AIR)) && 
								 !(loc.getBlock().getType().equals(Material.PACKED_ICE)) ) {
								
								loc.setX(loc.getX() - 1); // LEFT
								if (loc.getBlock().getType().equals(Material.AIR) ) {
									loc.getBlock().setType(Material.PACKED_ICE);
									loc.setX(loc.getX() + 1);
								}
								
								loc.setX(loc.getX() + 1); // RIGHT
								if (loc.getBlock().getType().equals(Material.AIR) ) {
									loc.getBlock().setType(Material.PACKED_ICE);
									loc.setX(loc.getX() - 1);
								}
								
								loc.setY(loc.getY() - 1); // DOWN
								if (loc.getBlock().getType().equals(Material.AIR) ) {
									loc.getBlock().setType(Material.PACKED_ICE);
									loc.setY(loc.getY() + 1);
								}
								
								loc.setY(loc.getY() + 1); // UP
								if (loc.getBlock().getType().equals(Material.AIR) ) {
									loc.getBlock().setType(Material.PACKED_ICE);
									loc.setY(loc.getY() - 1);
								}
								
								loc.setZ(loc.getZ() + 1); // FORWARD
								if (loc.getBlock().getType().equals(Material.AIR) ) {
									loc.getBlock().setType(Material.PACKED_ICE);
									loc.setZ(loc.getZ() - 1);
								}
								
								loc.setX(loc.getZ() - 1); // BACKWARD
								if (loc.getBlock().getType().equals(Material.AIR) ) {
									loc.getBlock().setType(Material.PACKED_ICE);
									loc.setZ(loc.getZ() + 1);
								}
								
							}
						}
					
					}
				}
			}
		
		if (event.getEntityType() == EntityType.CREEPER) 
			if (event.getEntity().getCustomName().contains("Death Creeper")) {
				event.setCancelled(true);
				
				event.getLocation().getWorld().createExplosion(event.getLocation(), 5F, false);
						
				for (Entity entity: event.getEntity().getNearbyEntities(5, 5, 5)) {
					if (entity.getType().equals(EntityType.PLAYER)) {
						Player p = (Player) entity;
						
						PotionEffect pe = new PotionEffect(PotionEffectType.BLINDNESS, 3600, 1);
						p.addPotionEffect(pe);
						pe = new PotionEffect(PotionEffectType.WITHER, 3600, 1);
						p.addPotionEffect(pe);
						pe = new PotionEffect(PotionEffectType.SLOW, 3600, 1);
						p.addPotionEffect(pe);
						pe = new PotionEffect(PotionEffectType.SLOW_DIGGING, 3600, 1);
						p.addPotionEffect(pe);
						pe = new PotionEffect(PotionEffectType.WEAKNESS, 3600, 1);
						p.addPotionEffect(pe);
					}
				}
			}
				
	}
	
	
	@EventHandler
	public void onMovement(PlayerMoveEvent event) {
		Player player = (Player) event.getPlayer();
		if (player.getInventory().getItemInMainHand() != null) 
			if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Heavy Sword")) 
				if (player.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
					PotionEffect pe = new PotionEffect(PotionEffectType.SLOW, 40, 0);
					player.addPotionEffect(pe);
				}
		
		for (Entity entity: player.getNearbyEntities(4, 4, 4)) {
			if (entity.getType() == EntityType.IRON_GOLEM) {
				IronGolem golem = (IronGolem) entity;
				golem.setTarget(player);
				break;
			}
		}
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent event) {
		Player p = (Player) event.getEntity().getKiller();
		Entity e = event.getEntity();
		
		if (p.getEquipment().getItemInMainHand().getItemMeta().getDisplayName().contains("Golden Scythe")) 
			if (p.getEquipment().getItemInMainHand().getItemMeta().hasLore()) {
				// Heart of Gold
				p.setAbsorptionAmount(p.getAbsorptionAmount() + 6.0);
				
				// Midas' Touch
				ItemStack golden_nugget = new ItemStack(Material.GOLD_NUGGET);
				ItemStack golden_ingot = new ItemStack(Material.GOLD_NUGGET);
				ItemStack golden_block = new ItemStack(Material.GOLD_BLOCK);
				
				//30% - 1-3
				if (r.nextInt(1000) < 300) 
					for (int i = 0; i <= r.nextInt(3); i++) 
						e.getWorld().dropItemNaturally(e.getLocation(), golden_nugget);
				
				//5% - 1-2
				if (r.nextInt(1000) < 50)
					for (int i = 0; i <= r.nextInt(2); i++) 
						e.getWorld().dropItemNaturally(e.getLocation(), golden_ingot);
				
				//0.1% - 1
				if (r.nextInt(1000) < 1)
					e.getWorld().dropItemNaturally(e.getLocation(), golden_block);
				
			}
		
	}
	
	
	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		Player player = (Player) event.getPlayer();
		if (player.getInventory().getItemInMainHand().getType().equals(Material.PAPER))
			if (player.getInventory().getItemInMainHand().getItemMeta().hasLore())
				if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Repair Kit")) {
					
					//Left Click
					if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
						ItemStack item = player.getInventory().getItemInOffHand();
						if (item == null) return;
						try {
							Damageable dur = (Damageable) item.getItemMeta();
							if (dur.getDamage() == 0) return;
							else if (dur.getDamage() < 100) dur.setDamage(0);
							else dur.setDamage(dur.getDamage() - 100);
							
							item.setItemMeta((ItemMeta) dur);
						}
						catch(Exception e) {
							return;
						}
						player.getInventory().getItemInMainHand().setAmount(
								player.getInventory().getItemInMainHand().getAmount() - 1);
						
						player.playSound(player.getLocation(), Sound.ENTITY_DOLPHIN_EAT, 1.0F, 1.0F);
					}
					
				}
		
		if (player.getInventory().getItemInMainHand().getType().equals(Material.ENDER_PEARL))
				if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Endless Pearl")) {
					//Right Click
					if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
						Bukkit.getScheduler().runTaskLater(plugin, () -> {
							player.getInventory().setItemInMainHand(plugin.getItem("endless_pearl"));
					    }, 1L);
						
					}
				}
	}
	
	
	
}
