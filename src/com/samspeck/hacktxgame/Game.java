package com.samspeck.hacktxgame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.samspeck.hacktxgame.Entitys.Alpha;
import com.samspeck.hacktxgame.Entitys.Enemy;

public class Game extends BaseGame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 938348731821944192L;

	private static JFrame frame;
	public static final boolean DEBUG = false;
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = SCREEN_WIDTH * 2 / 3;

	Camera camera = new Camera();
	public Player player;
	public ArrayList<Enemy> enemies;
	public Level level;

	public Game(String file) {
		player = new Player(this);
		player.position = new Vector2D(SCREEN_WIDTH / 2, 0);
		enemies = new ArrayList<Enemy>();
		enemies.add(new Alpha(this));
		enemies.get(0).position = new Vector2D(SCREEN_WIDTH / 2 + 300, 0);
		level = new Level("./levels/" + file + ".level");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		player.update();

		for (Enemy enemy : enemies)
			enemies.get(0).update();

		// enemy collision detection
		Rectangle playerRectangle = new Rectangle(
				(int) Math.round(player.position.x),
				(int) Math.round(player.position.y),
				player.currentSprite.frameWidth,
				player.currentSprite.frameHeight);
		for (Enemy enemy : enemies) {
			Rectangle enemyRectangle = new Rectangle(
					(int) Math.round(enemy.position.x),
					(int) Math.round(enemy.position.y),
					enemy.currentSprite.frameWidth,
					enemy.currentSprite.frameHeight);
			if (playerRectangle.intersects(enemyRectangle)) {
				player.reactToEnemyCollision();
			}
		}

		camera.lockToTarget(player.position, player.currentSprite.frameWidth,
				player.currentSprite.frameHeight, SCREEN_WIDTH, SCREEN_HEIGHT);
		// camera.clampToArea(level.width - SCREEN_WIDTH, level.height -
		// SCREEN_WIDTH);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		level.render(g, this, camera);
		player.render(g, this, camera);
		enemies.get(0).render(g, this, camera);
	}

	public static void main(String[] args) {
		String url;
		if (!DEBUG) {
			JSOUP soup = new JSOUP();
			url = soup.promptUser();
			if (url == null)
				return;
			url = "" + url.hashCode();
		} else {
			url = "test.level";
		}
		Game game = new Game(url);
		frame = new JFrame();
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

	public void gameover() {
		JPanel panel = new JPanel();
		Object[] options = { "Restart", "Exit"};
		int n = JOptionPane.showOptionDialog(panel,
				"Oh noes! you died! ",
				"GAMEOVER", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		if(n == 0)
			main(null);
		else
			System.exit(0);
	}

}
