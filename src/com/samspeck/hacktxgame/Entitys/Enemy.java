package com.samspeck.hacktxgame.Entitys;

import com.samspeck.hacktxgame.Entity;

public abstract class Enemy extends Entity {

	public Entity human;
	
	public Enemy(Entity human)
	{
		this.human = human;
	}
	// movement pattern
		//left right turn around on obstacle (air or land)
		//up down turn around on obstacle (air or land)
		//move towards the character but don't turn around
		//follow the character and turn around
		//stand in place
		//jump
	public abstract void makeMove();

	// attack
		// touch the player
		// throw item at the player
		// spawn another enemy
	
	public abstract void attack();

	// die
		// being stepped on
		// fell off of the map
		// being hit by the player's ability
		// invincible
		// Suicide attack
	public abstract void die();
	
	// dodge player's attack
		// instantly move one direction when player approach from above
	// mutate?
	// sounds?

	@Override
	public void update() {
		makeMove();
		super.update();
	}
}
