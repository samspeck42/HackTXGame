package com.samspeck.hacktxgame.Entitys;

import com.samspeck.hacktxgame.Game;

public class Alpha extends Enemy {
	
	public Alpha(Game game) {
		super(game);
	}
	
	@Override
	public void makeMove() {;
		velocity.x = walk_speed;
		if(false)
			velocity.x = -walk_speed;
	}

	@Override
	public void attack() {
		// Alpha doesn't attack
	}

}
