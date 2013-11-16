package com.samspeck.hacktxgame.Entitys;

import com.samspeck.hacktxgame.Game;

public class Alpha extends Enemy {
	
	public Alpha(Game game) {
		super(game);
	}
	
	@Override
	public void makeMove() {;
		velocity.x = walk_speed;
		if(false/*ran into concrete*/)
			velocity.x = -walk_speed;
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
