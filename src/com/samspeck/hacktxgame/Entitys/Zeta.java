package com.samspeck.hacktxgame.Entitys;

import com.samspeck.hacktxgame.Game;
import com.samspeck.hacktxgame.Sprite;

public class Zeta extends Alpha {
	private Player player;

	public Zeta(Game game) {
		super(game);
		this.player = game.player;
		currentSprite = new Sprite("/obake_b.png", 40, 40, 1, 40);
	}

	@Override
	public void makeMove() {
		
		if(player.position.x > position.x)
			velocity.x = -1.75f*walk_speed;
		else
			velocity.x = 1.75f*walk_speed;

	}

}
