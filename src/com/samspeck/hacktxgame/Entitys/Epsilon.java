package com.samspeck.hacktxgame.Entitys;

import com.samspeck.hacktxgame.Game;
import com.samspeck.hacktxgame.Player;

public class Epsilon extends Alpha {
	private Player player;

	public Epsilon(Game game) {
		super(game);
		this.player = game.player;
	}

	@Override
	public void makeMove() {
		super.makeMove();
		if(Math.abs(position.x - player.position.x) < 150)
			acceleration.x = -5f;
		else
			acceleration.x = 0;
		
	}


}
