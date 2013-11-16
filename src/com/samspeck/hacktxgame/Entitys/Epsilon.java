package com.samspeck.hacktxgame.Entitys;

import com.samspeck.hacktxgame.Game;
import com.samspeck.hacktxgame.Sprite;

public class Epsilon extends Alpha {
	private Player player;

	public Epsilon(Game game) {
		super(game);
		this.player = game.player;
		currentSprite = new Sprite("/obake_a.png", 40, 40, 1, 40);
	}

	@Override
	public void makeMove() {
		super.makeMove();
		if(Math.abs(position.x - player.position.x) < 150)
		{
			currentSprite = new Sprite("/obake_b.png", 40, 40, 1, 40);
			acceleration.x = -5f;
		}
		else
		{
			currentSprite = new Sprite("/obake_a.png", 40, 40, 1, 40);
			acceleration.x = 0;
		}
		
	}


}
