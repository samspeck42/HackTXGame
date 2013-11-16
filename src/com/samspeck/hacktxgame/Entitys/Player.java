package com.samspeck.hacktxgame.Entitys;

import java.awt.Point;

import com.samspeck.hacktxgame.Buttons;
import com.samspeck.hacktxgame.Game;
import com.samspeck.hacktxgame.Input;
import com.samspeck.hacktxgame.InputState;
import com.samspeck.hacktxgame.Level;
import com.samspeck.hacktxgame.Sprite;

public class Player extends Entity {
	
	static final float gravitationalAcceleration = .5f;
	private static final float WALK_SPEED = 5;
	private static final int JUMP_VELOCITY = 10;
	
	private boolean playingAnimation = false;
	private boolean walking = false;
	private boolean startedWalking = false;
	private boolean walkingRight = false;
	private boolean rightBtn = false;
	private boolean standing = true;
	
	Input input;
	InputState inputState;
	InputState prevInputState;
	
	public Player(Game game) {
		super(game);
		input = new Input(game);
		inputState = input.getState();
		prevInputState = inputState;
		
		currentSprite = new Sprite("/bone.png", 40, 64, 2, 40);
		playingAnimation = true;
	}
	
	@Override
	public void update() {
		if(position.y > game.level.height)
			game.gameover();
		
		acceleration.y = gravitationalAcceleration;
		
		// handle input
		inputState = input.getState();

		float xMotion = 0;
		if (inputState.isButtonDown(Buttons.Left))
		{
			game.footsteps--;
			xMotion--;
			startedWalking = true;
			playingAnimation = false;
			rightBtn = false;
			System.out.println("right button pressed");
		}
		else if (inputState.isButtonDown(Buttons.Right))
		{
			xMotion++;
			game.footsteps++;
			startedWalking = true;
			playingAnimation = false;
			rightBtn = true;
			System.out.println("left button pressed");
		}
		else
		{
			System.out.println("no button pressed");
			startedWalking = false;
			walking = false;
			if(!standing)
			{
				playingAnimation = false;
				System.out.println("not standing, gonna stand");
			}
		}
		if(!playingAnimation)
		{
			if(startedWalking && !walking)
			{
				walking = true;
				standing = false;
				if(rightBtn)
				{
					currentSprite = new Sprite("/walkingBoneRight.png", 40, 64, 2, 10);
					walkingRight = true;
					System.out.println("right button pressed gonna walk right");
				}
				else
				{
					currentSprite = new Sprite("/walkingBoneLeft.png", 40, 64, 2, 10);
					walkingRight = false;
					System.out.println("right button pressed gonna walk left");
				}
			}
			else if(!startedWalking)
			{
				walking = false;
				standing = true;
				currentSprite = new Sprite("/bone.png", 40, 64, 2, 40);
				System.out.println("no button pressed gonna stand");
			}
			playingAnimation = true;
		}

		if(walking && rightBtn && !walkingRight)
		{
			currentSprite = new Sprite("/walkingBoneRight.png", 40, 64, 2, 10);
			System.out.println("walking the wrong way, switch to right");
		}
		else if(walking && !rightBtn && walkingRight)
		{
			currentSprite = new Sprite("/walkingBoneLeft.png", 40, 64, 2, 10);
			System.out.println("walking the wrong way, switch to left");
		}
		
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
		//if(!(enemy instanceof Spiky) && position.y > enemy.position.y)
		//		enemy.die();
		//else
		if(enemy instanceof Goal)
		{
			game.win();
		}
		game.gameover();
	}
}
