package com.samspeck.hacktxgame.Entitys;

import com.samspeck.hacktxgame.Game;

public class Gamma extends Alpha {
	private int count = 0;
	
	public Gamma(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
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
