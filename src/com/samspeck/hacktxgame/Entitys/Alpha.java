package com.samspeck.hacktxgame.Entitys;

public class Alpha extends Enemy {

	@Override
	public void makeMove() {
		position.x += velocity.x;
		// if(ran into concrete)
		// 	Move Right
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
