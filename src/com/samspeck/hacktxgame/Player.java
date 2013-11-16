package com.samspeck.hacktxgame;

import java.awt.Point;

import javax.swing.text.Position;

import com.samspeck.hacktxgame.Entitys.Enemy;

public class Player extends Entity {
	
	static final float gravitationalAcceleration = .5f;
	private static final float WALK_SPEED = 5;
	private static final int JUMP_VELOCITY = 10;
	
	Input input;
	InputState inputState;
	InputState prevInputState;
	
	public Player(Game game) {
		super(game);
		input = new Input(game);
		inputState = input.getState();
		prevInputState = inputState;
		
		currentSprite = new Sprite("/bone.png", 40, 64, 2, 40);
	}
	
	@Override
	public void update() {
		acceleration.y = gravitationalAcceleration;
		
		// handle input
		inputState = input.getState();

		float xMotion = 0;
		if (inputState.isButtonDown(Buttons.Left))
			xMotion--;
		if (inputState.isButtonDown(Buttons.Right))
			xMotion++;

		velocity.x = xMotion * WALK_SPEED;
		
		if (onGround && inputState.isButtonDown(Buttons.Jump) && prevInputState.isButtonUp(Buttons.Jump)) {
			velocity.y = -JUMP_VELOCITY;
		}
		
		prevInputState = inputState;
		
		super.update();
		
		Point tl = new Point((int)(position.x / (float)Level.TILE_WIDTH), (int)(position.y / (float)Level.TILE_HEIGHT));
        Point br = new Point((int)((position.x + currentSprite.frameWidth - 1) / (float)Level.TILE_WIDTH), 
        		(int)((position.y + currentSprite.frameHeight - 1) / (float)Level.TILE_HEIGHT));
        for (int y = tl.y; y <= br.y; y++)
        {
            for (int x = tl.x; x <= br.x; x++)
            {
                Point cell = new Point(x, y);
                int collision = game.level.getCollisionAtCell(cell);
                if (collision == Level.TILE_OBSTACLE)
                    reactToObstacleCollision();
            }
        }
	}

	private void reactToObstacleCollision() {
		System.out.println("ouch");
		game.gameover();
	}

	public void reactToEnemyCollision(Enemy enemy) {
		System.out.println("ouch");
		game.gameover();
		
	}
}
