package com.samspeck.hacktxgame.Entitys;

import com.samspeck.hacktxgame.Entity;
import com.samspeck.hacktxgame.Game;
import com.samspeck.hacktxgame.Sprite;

public abstract class Enemy extends Entity {	

	public static final float gravitationalAcceleration = .5f;
	protected float walk_speed = -1;
	protected int jump_velocity = -10;
	
	public Enemy(Game game) {
		super(game);
		
		currentSprite = new Sprite("/circle.png", 32, 32, 2, 40);
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
	public void die(){
		System.out.println("et you, brutus?");
	}
	
	// dodge player's attack
		// instantly move one direction when player approach from above
	// mutate?
	// sounds?

	@Override
	public void update() {
		acceleration.y = gravitationalAcceleration;
		makeMove();
		super.update();
	}
}
