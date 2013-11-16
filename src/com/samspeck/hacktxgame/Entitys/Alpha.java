package com.samspeck.hacktxgame.Entitys;

import com.samspeck.hacktxgame.Game;
import com.samspeck.hacktxgame.Sprite;

public class Alpha extends Enemy {
	
	public Alpha(Game game) {
		super(game);
		currentSprite = new Sprite("/obake_a.png", 40, 40, 1, 40);
	}
	
	@Override
	public void makeMove() {;
		if(stuckAtWall)
		{
			stuckAtWall = false;
			velocity.x = -velocity.x;
		}
		else
		{
			velocity.x = walk_speed;
		}
	}

	@Override
	public void attack() {
		// Alpha doesn't attack
	}

}
