package com.samspeck.hacktxgame.Entitys;

public class Alpha extends Enemy {
	public static final int MOVEMENT_SPEED = 10;
	
	@Override
	public void makeMove() {
		velocity.x = MOVEMENT_SPEED;
		if(false/*ran into concrete*/)
			velocity.x = -MOVEMENT_SPEED;
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
