package com.samspeck.hacktxgame.Entitys;

import com.samspeck.hacktxgame.Game;
import com.samspeck.hacktxgame.Player;

public class Zeta extends Alpha {
	private Player player;

	public Zeta(Game game) {
		super(game);
		this.player = game.player;
	}

	@Override
	public void makeMove() {
		
		if(player.position.x > position.x)
			velocity.x = -1.5f*walk_speed;
		else
			velocity.x = 1.5f*walk_speed;

	}

}
