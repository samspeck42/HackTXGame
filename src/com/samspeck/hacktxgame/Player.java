package com.samspeck.hacktxgame;

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
		
		currentSprite = new Sprite("/circle.png", 32, 32, 2, 40);
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
		
		if (inputState.isButtonDown(Buttons.Jump) && prevInputState.isButtonUp(Buttons.Jump)) {
			velocity.y = -JUMP_VELOCITY;
		}
		
		prevInputState = inputState;
		
		super.update();
	}
}