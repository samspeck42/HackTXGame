package com.samspeck.hacktxgame;

import java.awt.Graphics;

import javax.swing.JFrame;

public class Game extends BaseGame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 938348731821944192L;

	public static final boolean DEBUG = true;
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = SCREEN_WIDTH * 2 / 3;

	Camera camera = new Camera();
	Player player;
	public Level level;

	public Game() {
		player = new Player(this);
		player.position = new Vector2D(SCREEN_WIDTH / 2, 0);
		// circle.acceleration.y = 0.01f;
		level = new Level("/test.level");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		player.update();

		camera.lockToTarget(player.position, player.currentSprite.frameWidth,
				player.currentSprite.frameHeight, SCREEN_WIDTH, SCREEN_HEIGHT);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		level.render(g, this, camera);
		player.render(g, this, camera);
	}

	public static void main(String[] args) {
		if (!DEBUG) {
			JSOUP soup = new JSOUP();
			soup.promptUser();
		}
		Game game = new Game();
		JFrame frame = new JFrame();
		frame.add(game);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
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
