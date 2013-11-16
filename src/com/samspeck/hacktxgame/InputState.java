package com.samspeck.hacktxgame;

import java.util.HashMap;

public class InputState {
	
	private HashMap<Buttons, Boolean> state;
	
	public InputState() {
		state = new HashMap<Buttons, Boolean>();
		for (Buttons button : Buttons.values()) {
			state.put(button, false);
		}
	}
	
	public boolean isButtonDown(Buttons button) {
		return state.get(button);
	}
	
	public boolean isButtonUp(Buttons button) {
		return !state.get(button);
	}
	
	public void setButtonState(Buttons button, boolean isDown) {
		state.put(button, isDown);
	}
}
