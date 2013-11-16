package com.samspeck.hacktxgame.Entitys;

import com.samspeck.hacktxgame.Entity;

public class Delta extends Alpha {
	private int count = 0;
	public final int JUMPING_SPEED = -14;
	
	public Delta(Entity human) {
		super(human);
	}

	@Override
	public void makeMove() {
		super.makeMove();
		if(count == 75)
		{
			// jump
			velocity.y = JUMPING_SPEED;
		}
		count = (count > 0 )? count-1 : count;
	}
	public void jump()
	{
		if(count <1)
			count = 75;
	}

}
