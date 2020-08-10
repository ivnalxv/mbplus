package me.metaMockery.mobs;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpawnEvents implements Listener{
	
	static Main plugin;
	public SpawnEvents(Main instance) {
		plugin = instance;
	}
	
	Random r = new Random();
	
	
	
	@EventHandler
	public void onSpawn(CreatureSpawnEvent event) {
		// Common - white
		// Uncommon - green
		// Rare - blue
		// Mythical - purple
		// Calamity - red
		EntityEquipment eq;
		
		
		if (event.getEntityType() == EntityType.CREEPER) {
			Creeper creeper = (Creeper) event.getEntity();
			
			if (r.nextInt(1000) < 100) {
				// Normal Creeper
				// Spawn chance: 10%
				// Health: 80 HP
				// Movement Speed: 0.3
				// (Base MS: 0.25)
				// 
				// Rarity: Common
				
				
				creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(80.0);
				creeper.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
				creeper.setHealth(80.0);
			}
			else if (r.nextInt(1000) < 111) {
				// Nether Creeper
				// Spawn chance: 10%
				// Health: 120 HP
				// Movement Speed: 0.35
				// (Base MS: 0.25)
				// 
				// Rarity: Uncommon
				
				creeper.setCustomName(ChatColor.GREEN + "" + ChatColor.BOLD + "Nether Creeper");
				creeper.setCustomNameVisible(true);
				
				creeper.setExplosionRadius(5);
				creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(120.0);
				creeper.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.35);
				creeper.setHealth(120.0);
			}
			else if (r.nextInt(1000) < 125) {
				// Ender Creeper
				// Spawn chance: 10%
				// Health: 120 HP
				// Movement Speed: 0.35
				// (Base MS: 0.25)
				// 
				// Rarity: Uncommon
				
				creeper.setCustomName(ChatColor.GREEN + "" + ChatColor.BOLD + "Ender Creeper");
				creeper.setCustomNameVisible(true);
				
				creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(120.0);
				creeper.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.35);
				creeper.setHealth(120.0);
			}
			else if (r.nextInt(1000) < 141) {
				// Ice Creeper
				// Spawn chance: 10%
				// Health: 120 HP
				// Movement Speed: 0.35
				// (Base MS: 0.25)
				// 
				// Rarity: Uncommon
				
				creeper.setCustomName(ChatColor.GREEN + "" + ChatColor.BOLD + "Ice Creeper");
				creeper.setCustomNameVisible(true);
				
				creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(120.0);
				creeper.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.35);
				creeper.setHealth(120.0);
			}
			else if (r.nextInt(1000) < 166) {
				// Death Creeper
				// Spawn chance: 10%
				// Health: 120 HP
				// Movement Speed: 0.35
				// (Base MS: 0.25)
				// 
				// Rarity: Uncommon
				
				creeper.setCustomName(ChatColor.GREEN + "" + ChatColor.BOLD + "Death Creeper");
				creeper.setCustomNameVisible(true);
				
				creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(120.0);
				creeper.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.35);
				creeper.setHealth(120.0);
			}
			else if (r.nextInt(1000) < 199) {
				creeper.setPowered(true);
				creeper.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(100.0);
				creeper.setExplosionRadius(5);
				creeper.setSilent(true);
			}
			
		}
		
		if (event.getEntityType() == EntityType.SKELETON) {
			Skeleton skeleton = (Skeleton) event.getEntity();
			skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100.0);
			skeleton.setHealth(100.0);
			
			if (r.nextInt(1000) < 100) {
				// Shooter
				// Spawn chance: 10%
				// Health: 150 HP
				// Damage: 5
				// 
				// Rarity: Uncommon
				
				skeleton.setCustomName(ChatColor.GREEN + "" + ChatColor.BOLD + "Shooter");
				skeleton.setCustomNameVisible(true);
				
				eq = skeleton.getEquipment();
				eq.setHelmet(new ItemStack(Material.DISPENSER));
				eq.setHelmetDropChance(0.0F);
				
				skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(150.0);
				skeleton.setHealth(150.0);
				skeleton.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(4.0);
			}
			
			
		}
		
		if (event.getEntityType() == EntityType.IRON_GOLEM) {
			IronGolem golem = (IronGolem) event.getEntity();
			
			// Health: 600 HP
			// Damage: 100
			// Attacks 5 times faster
			//
			// Resistance IV and Fire Resistance
			
			PotionEffect pe = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 19999980, 3);
			golem.addPotionEffect(pe);
			pe = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 19999980, 1);
			golem.addPotionEffect(pe);
			
			
			golem.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(600.0);
			golem.setHealth(600.0);
			golem.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(100.0);
			golem.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(
					golem.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getBaseValue()*5);
			
			
			for (Entity entity: golem.getNearbyEntities(4, 4, 4)) {
				if (entity instanceof Player) 
					golem.setTarget((Player) entity);
			}
			
		}
		
		if (event.getEntityType() == EntityType.CAVE_SPIDER) {
			CaveSpider spider = (CaveSpider) event.getEntity();
			
			// Just Cave Spider
			// Health: 40 HP
			// Damage: 4
			// Attacks 2 times faster
			spider.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);
			spider.setHealth(40.0);
			spider.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(4.0);
			spider.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(
					spider.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getBaseValue()*2);
			
		}
		
		if (event.getEntityType() == EntityType.SPIDER) {
			Spider spider = (Spider) event.getEntity();
			
			if (r.nextInt(1000) < 10) {
				// Broodmother
				// Spawn chance: 1%
				// Health: 400 HP
				// Damage: 20
				//
				// Resistance III and Slowness I
				// Regeneration II
				// 
				// Rarity: Calamity
				
				spider.setCustomName(ChatColor.RED + "" + ChatColor.BOLD + "Broodmother");
				spider.setCustomNameVisible(true);
				
				PotionEffect pe = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 19999980, 2);
				spider.addPotionEffect(pe);
				pe = new PotionEffect(PotionEffectType.SLOW, 19999980, 0);
				spider.addPotionEffect(pe);
				pe = new PotionEffect(PotionEffectType.REGENERATION, 19999980, 1);
				spider.addPotionEffect(pe);
				
				spider.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(400.0);
				spider.setHealth(400.0);
				spider.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20.0);
				
				
			}
			else if (r.nextInt(1000) < 50){
				// Silent Killer
				// Spawn chance: 4.95%
				// Health: 10 HP
				// Damage: 30 
				//
				// Speed VII
				// 
				// Rarity: Rare
				
				spider.setCustomName(ChatColor.BLUE + "" + ChatColor.BOLD + "Silent Killer");
				spider.setCustomNameVisible(false);
				
				PotionEffect pe = new PotionEffect(PotionEffectType.INVISIBILITY, 19999980, 2);
				spider.addPotionEffect(pe);
				spider.setSilent(true);
				
				spider.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10.0);
				spider.setHealth(10.0);
				spider.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(30.0);
				spider.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.8);
			}
			else {
				// Normal Spider
				// Spawn chance: 94.05%
				// Health:  75 HP
				// Damage: 6
				// Rarity: Common
				
				
				
				spider.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(75.0);
				spider.setHealth(75.0);
				spider.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(6.0);
				spider.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.5);
			}
		}
		
		if (event.getEntityType() == EntityType.ZOMBIE) {
			Zombie zombie = (Zombie) event.getEntity();
			zombie.setBaby(false);
			
			if (r.nextInt(1000) < 150) {
				// Hungry Zombie
				// Spawn Chance: 15%
				// Health:  120 HP
				// Damage: 6
				//
				// Rarity: Uncommon
				zombie.setCustomName(ChatColor.GREEN + "" + ChatColor.BOLD + "Hungry");
				zombie.setCustomNameVisible(true);
				
				eq = zombie.getEquipment();
				eq.setItemInMainHand(plugin.getItem("hungry_bone"));
				
				zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(120.0);
				zombie.setHealth(120.0);
				zombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(6.0);
				zombie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
			}
			else if (r.nextInt(1000) < 150) {
				// Swarm
				// Spawn Chance: 12.75%
				// Health: 80 HP
				// Damage: 7
				//
				// Speed III
				//
				// Rarity: Uncommon
				zombie.setCustomName(ChatColor.GREEN + "" + ChatColor.BOLD + "Swarm");
				zombie.setCustomNameVisible(true);
				
				eq = zombie.getEquipment();
				eq.setHelmet(new ItemStack(Material.SPAWNER));
				eq.setItemInMainHand(new ItemStack(Material.ZOMBIE_HEAD));
				eq.setItemInOffHand(new ItemStack(Material.ZOMBIE_HEAD));
				eq.setHelmetDropChance(0.0F);
				
				
				zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(80.0);
				zombie.setHealth(80.0);
				zombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(7.0);
				zombie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.35);
			}
			else if (r.nextInt(1000) < 50) {
				// Zombie Knight
				// Spawn Chance: 3.6 %
				// Health: 200 HP
				// Damage: 10 True Damage
				//
				// Resistance III and Speed II
				//
				// Rarity: Rare
				zombie.setCustomName(ChatColor.BLUE + "" + ChatColor.BOLD + "Zombie Knight");
				zombie.setCustomNameVisible(true);
				
				eq = zombie.getEquipment();
				eq.setHelmet(new ItemStack(Material.IRON_HELMET));
				eq.setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
				eq.setLeggings(new ItemStack(Material.IRON_LEGGINGS));
				eq.setBoots(new ItemStack(Material.IRON_BOOTS));
				eq.setItemInMainHand(plugin.getItem("heavy_sword"));
				eq.setItemInOffHand(new ItemStack(Material.SHIELD));
				eq.setItemInMainHandDropChance(1.0F);
				
				
				PotionEffect pe = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 19999980, 2);
				zombie.addPotionEffect(pe);
				
				zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(200.0);
				zombie.setHealth(200.0);
				zombie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.35);
			}
			
			else {
				// Normal Zombie
				// Spawn Chance: 68.64 %
				// Health: 75 HP
				// Damage: 6
				// Random Buff: Strength I-IV, Speed I-III, Jump I-II, Fire Res I-II
				//
				// Rarity: Common
				PotionEffect pe;
				
				for (int i = 0, j = r.nextInt(2); i <= j; i++) {
					int effectNUM = r.nextInt(4);
					if (effectNUM == 0) {
						int str_of_effect = r.nextInt(4);
						pe = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 19999980, str_of_effect);
					}
					else if (effectNUM == 1) {
						int str_of_effect = r.nextInt(4);
						pe = new PotionEffect(PotionEffectType.SPEED, 19999980, str_of_effect);
					}
					else if (effectNUM == 2) {
						int str_of_effect = r.nextInt(4);
						pe = new PotionEffect(PotionEffectType.JUMP, 19999980, str_of_effect);
					}
					else {
						int str_of_effect = r.nextInt(4);
						pe = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 19999980, str_of_effect);
					}
					
					zombie.addPotionEffect(pe);
				}
				
				zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(75.0);
				zombie.setHealth(75.0);
				zombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(6.0);
			}
		}
	}
	
}
