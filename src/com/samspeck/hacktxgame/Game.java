package com.samspeck.hacktxgame;

import java.awt.Graphics;

import javax.swing.JFrame;

public class Game extends BaseGame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 938348731821944192L;
	
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = SCREEN_WIDTH * 2/3;
	
	Camera camera = new Camera();
	Entity circle;
	Level level;
	Input input;
	InputState inputState;
	InputState prevInputState;
	
	public Game() {
		circle = new Entity();
		circle.currentSprite = new Sprite("/circle.png", 32, 32, 2, 40);
		circle.position = new Vector2D(SCREEN_WIDTH / 2, 0);
		//circle.acceleration.y = 0.01f;
		level = new Level("/test");
		input = new Input(this);
		inputState = input.getState();
		prevInputState = inputState;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		inputState = input.getState();
		
		Vector2D motion = new Vector2D(0, 0);
		if (inputState.isButtonDown(Buttons.Up))
			motion.y--;
		if (inputState.isButtonDown(Buttons.Down))
			motion.y++;
		if (inputState.isButtonDown(Buttons.Left))
			motion.x--;
		if (inputState.isButtonDown(Buttons.Right))
			motion.x++;
		
		circle.velocity.x = motion.x * 5;
		circle.velocity.y = motion.y * 5;
		
		circle.update();
		
		camera.lockToTarget(circle.position, circle.currentSprite.frameWidth, circle.currentSprite.frameHeight, 
				SCREEN_WIDTH, SCREEN_HEIGHT);
		
		prevInputState = inputState;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		level.render(g, this, camera);
		circle.render(g, this, camera);
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame();
		frame.add(game);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		frame.setTitle("Game");
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		game.start();
	}
	
	public void addNotify() {
        super.addNotify();
        requestFocus();
    }

}
