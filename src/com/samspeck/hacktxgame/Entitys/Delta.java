package com.samspeck.hacktxgame.Entitys;

import com.samspeck.hacktxgame.Buttons;
import com.samspeck.hacktxgame.Game;
import com.samspeck.hacktxgame.Input;
import com.samspeck.hacktxgame.InputState;

public class Delta extends Alpha {
	private int count = 0;

	private Input input;
	private InputState inputState;
	private InputState prevInputState;
	
	public Delta(Game game) {
		super(game);
		input = new Input(game);
		inputState = input.getState();
		prevInputState = inputState;
	}

	@Override
	public void makeMove() {
		super.makeMove();
		
		// handle input
		inputState = input.getState();
		
		if (onGround && inputState.isButtonDown(Buttons.Jump) && prevInputState.isButtonUp(Buttons.Jump)) {
			count = 75;
		}
		prevInputState = inputState;
		
		if(count == 75)
		{
			// jump
			velocity.y = 1.11f*jump_velocity;
		}
		count = (count > 0 )? count-1 : count;
		
	}

}
