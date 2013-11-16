package com.samspeck.hacktxgame.Entitys;

import com.samspeck.hacktxgame.Entity;

public class Gamma extends Alpha {
	public Gamma(Entity human) {
		super(human);
		// TODO Auto-generated constructor stub
	}
	public final int JUMPING_SPEED = -10;
	private int count = 0;
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
			velocity.y = JUMPING_SPEED;
		}
		
	}
}
