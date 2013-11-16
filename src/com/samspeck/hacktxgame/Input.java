package com.samspeck.hacktxgame;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Input implements KeyListener {
	
	private HashMap<Integer, Buttons> buttonMap;
	private InputState inputState;
	
	public Input(Component c) {
		c.addKeyListener(this);
		
		buttonMap = new HashMap<Integer, Buttons>();
		buttonMap.put(KeyEvent.VK_UP, Buttons.Up);
		buttonMap.put(KeyEvent.VK_DOWN, Buttons.Down);
		buttonMap.put(KeyEvent.VK_LEFT, Buttons.Left);
		buttonMap.put(KeyEvent.VK_RIGHT, Buttons.Right);
		
		inputState = new InputState();
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		int key = ke.getKeyCode();
		Buttons button = buttonMap.get(key);
		inputState.setButtonState(button, true);
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		int key = ke.getKeyCode();
		Buttons button = buttonMap.get(key);
		inputState.setButtonState(button, false);
	}
	
	public InputState getState() {
		InputState s = new InputState();
		
		for (Buttons button : Buttons.values()) {
			s.setButtonState(button, inputState.isButtonDown(button));
		}
		
		return s;
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		// TODO Auto-generated method stub

	}

}
