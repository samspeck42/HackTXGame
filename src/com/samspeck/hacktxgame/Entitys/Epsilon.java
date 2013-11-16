package com.samspeck.hacktxgame.Entitys;

import com.samspeck.hacktxgame.Entity;

public class Epsilon extends Alpha {

	public Epsilon(Entity human) {
		super(human);
	}
	
	@Override
	public void makeMove() {
		super.makeMove();
		if(Math.abs(position.x - human.position.x) < 150)
			acceleration.x = -5f;
		else
			acceleration.x = 0;
		
	}


}
