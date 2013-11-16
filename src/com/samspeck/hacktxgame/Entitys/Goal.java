package com.samspeck.hacktxgame.Entitys;

import com.samspeck.hacktxgame.Game;
import com.samspeck.hacktxgame.Sprite;

public class Goal extends Enemy {

	public Goal(Game game) {
		super(game);
		currentSprite = new Sprite("/goal.png", 40, 64, 1, 40);
	}

	@Override
	public void makeMove() {
		// do nothing
	}
	@Override
	public void update()
	{
		if (currentSprite != null) {
			currentSprite.updateAnimation();
		}
	}

	@Override
	public void attack() {
		// finish line can't attack! what are you talking about
	}

}
