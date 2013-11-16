package com.samspeck.hacktxgame.Entitys;

import com.samspeck.hacktxgame.Entity;

public class Alpha extends Enemy {
	
	public Alpha(Entity human) {
		super(human);
	}

	public float movement_speed = -1;

	@Override
	public void makeMove() {
		velocity.x = movement_speed;
		if(false/*ran into concrete*/)
			velocity.x = -movement_speed;
	}

	@Override
	public void attack() {
		// Alpha doesn't attack
	}

	@Override
	public void die() {
		// TODO if human stepped on Alpha
		// Alpha die and get removed from the map
		// if Alpha fell off of the cliff
		// Alpha die
	}

}
