package com.samspeck.hacktxgame;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;

public class Game extends BaseGame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 938348731821944192L;
	
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = SCREEN_WIDTH * 2/3;
	
	Entity circle;
	
	public Game() {
		circle = new Entity();
		circle.currentSprite = new Sprite("/circle.png", 32, 32, 0, 0);
		circle.position = new Vector2D(SCREEN_WIDTH / 2, 0);
		circle.velocity.y = 1f;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		circle.update();
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		circle.render(g, this);
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

}
