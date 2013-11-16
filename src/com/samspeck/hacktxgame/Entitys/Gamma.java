package com.samspeck.hacktxgame.Entitys;

import com.samspeck.hacktxgame.Game;
import com.samspeck.hacktxgame.Sprite;

public class Gamma extends Alpha {
	private int count = 0;
	
	public Gamma(Game game) {
		super(game);
		currentSprite = new Sprite("/dokuro.png", 40, 40, 1, 40);
	}
	@Override
	public void makeMove() {
		super.makeMove();
		jump();
	}
	private void jump()
	{
		if(count++ > 75)
		{
			count = 0;
			// jump
			velocity.y = jump_velocity;
		}
		
	}
}
