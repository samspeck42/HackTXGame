package com.samspeck.hacktxgame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.samspeck.hacktxgame.Entitys.Alpha;
import com.samspeck.hacktxgame.Entitys.Beta;
import com.samspeck.hacktxgame.Entitys.Delta;
import com.samspeck.hacktxgame.Entitys.Enemy;
import com.samspeck.hacktxgame.Entitys.Epsilon;
import com.samspeck.hacktxgame.Entitys.Gamma;
import com.samspeck.hacktxgame.Entitys.Goal;
import com.samspeck.hacktxgame.Entitys.Player;
import com.samspeck.hacktxgame.Entitys.Zeta;

public class Game extends BaseGame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 938348731821944192L;

	private static JFrame frame;
	public static final boolean DEBUG = false;
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = SCREEN_WIDTH * 2 / 3;
	public static final Random rand = new Random();
	public static final int NUM_ENEMY_TYPES = 6;

	Camera camera = new Camera();
	public Player player;
	public ArrayList<Enemy> enemies;
	public Goal goal;
	public Level level;
	public int footsteps;

	public Game(String file) {
		player = new Player(this);
		player.position = new Vector2D(SCREEN_WIDTH / 2, 0);
		enemies = new ArrayList<Enemy>();
		level = new Level("./levels/" + file + ".level");
		goal = new Goal(this);
		int goal_height = 0;
		for(int i = level.tileMap.length-1; i >= 0 ; i--)
		{
			if(level.tileMap[i][level.tileMap[0].length-1] == -1)
			{
				goal_height = i * Level.TILE_HEIGHT;
				break;
			}
		}
		goal.position = new Vector2D(level.width - Level.TILE_WIDTH, goal_height);
		enemies.add(goal);
		spawnEnemy();
	}

	public void spawnEnemy() {
		footsteps = 0;
		int currentLocation = (int) (player.position.x + 500);
		Enemy nextEnemy;
		switch (rand.nextInt(NUM_ENEMY_TYPES)) {
		case 0:
			nextEnemy = new Alpha(this);
			System.out.println("added alpha");
			break;
		case 1:
			nextEnemy = new Beta(this);
			System.out.println("added beta");
			break;
		case 2:
			nextEnemy = new Delta(this);
			System.out.println("added delta");
			break;
		case 3:
			nextEnemy = new Epsilon(this);
			System.out.println("added epsilon");
			break;
		case 4:
			nextEnemy = new Gamma(this);
			System.out.println("added gamma");
			break;
		case 5:
			nextEnemy = new Zeta(this);
			break;
		default:
			nextEnemy = new Alpha(this);
			break;
		}
		nextEnemy.position = new Vector2D(currentLocation, 0);
		enemies.add(nextEnemy);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		if (footsteps > 250+rand.nextInt(100))
			spawnEnemy();
		player.update();
		for (Enemy enemy : enemies)
			enemy.update();

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
				player.reactToEnemyCollision(enemy);
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
		for (Enemy enemy : enemies) {
			enemy.render(g, this, camera);
		}
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
		JOptionPane.showMessageDialog(panel, "Oh noes! you died!\n"
				+ "click OK to restart", "GAMEOVER",
				JOptionPane.INFORMATION_MESSAGE);
		frame.setVisible(false);
		frame.dispose();
		main(null);
	}

	public void win() {
		JPanel panel = new JPanel();
		JOptionPane.showMessageDialog(panel, "YOU WON!\n"
				+ "click OK to restart", "YISSSS",
				JOptionPane.INFORMATION_MESSAGE);
		main(null);
	}

}
