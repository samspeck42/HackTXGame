package com.samspeck.hacktxgame.Entitys;

import com.samspeck.hacktxgame.Entity;

public class Zeta extends Alpha {

	public Zeta(Entity human) {
		super(human);
	}

	@Override
	public void makeMove() {
		if(human.position.x > position.x)
			velocity.x = -1.5f*movement_speed;
		else
			velocity.x = 1.5f*movement_speed;

	}

}
