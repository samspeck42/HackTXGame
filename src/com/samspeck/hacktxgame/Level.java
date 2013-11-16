package com.samspeck.hacktxgame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Level {
	static final int TILE_WIDTH = 34;
	static final int TILE_HEIGHT = 34;
	
	public static final int TILE_PASSABLE = 0;
	public static final int TILE_IMPASSABLE = 1;
	public static final int TILE_OBSTACLE = 2;
	
	public int width;
	public int height;
	
	int[][] tileMap;
	ArrayList<Image> tileImages;
	ArrayList<Integer> tileCollisions;
	
	public Level(String levelPath) {
		tileImages = new ArrayList<Image>();
		tileCollisions = new ArrayList<Integer>();
		
		ArrayList<ArrayList<Integer>> levelData = loadLevel(levelPath);
		
		if (levelData.size() > 0) { 
			tileMap = new int[levelData.size()][levelData.get(0).size()];
			
			for (int r = 0; r < levelData.size(); r++) {
				for (int c = 0; c < levelData.get(r).size(); c++) {
					tileMap[r][c] = levelData.get(r).get(c);
				}
			}
			
			width = tileMap[0].length * TILE_WIDTH;
			height = tileMap.length * TILE_HEIGHT;
		}
		else {
			tileMap = new int[0][0];
			width = 0;
			height = 0;
		}
	}

	private ArrayList<ArrayList<Integer>> loadLevel(String levelPath) {
		BufferedReader in;
		String line;
		ArrayList<ArrayList<Integer>> levelData = new ArrayList<ArrayList<Integer>>();
		boolean readingLayout = false;
		
		try {
			System.out.println(levelPath);
			in = new BufferedReader(new FileReader(levelPath));
			
			while ((line = in.readLine()) != null) {
				if (line.isEmpty()) {
					continue;
				}
				
				if(line.contains("URL: ")){
					continue;
				}
				
				if(line.equals("[Layout]")) {
					readingLayout = true;
					continue;
				}
				
				if (!readingLayout) {
					String[] row = line.split(" ");
					ImageIcon i = new ImageIcon(Game.class.getResource(row[0]));
					Image im = i.getImage();
					tileImages.add(im);
					
					tileCollisions.add(Integer.parseInt(row[1]));
				}
				else if (readingLayout) {
					String[] row = line.split(" ");
					ArrayList<Integer> rowData = new ArrayList<Integer>();
					
					for (String s : row) {
						rowData.add(Integer.parseInt(s) - 1);
					}
					
					levelData.add(rowData);
				}
			}
			
			in.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return levelData;
	}

	public int getCollisionAtCell(Point cell) {
		if (cell.x < 0 || cell.x >= tileMap[0].length ||
				cell.y < 0 || cell.y >= tileMap.length)
			return TILE_PASSABLE;
		
		int index = tileMap[cell.y][cell.x];
		
		if (index < 0)
			return TILE_PASSABLE;
		else
			return tileCollisions.get(index);
	}
	
	public void render(Graphics g, ImageObserver obs, Camera cam) {
		for (int r = 0; r < tileMap.length; r++) {
			for (int c = 0; c < tileMap[r].length; c++) {
				int index = tileMap[r][c];
				
				if (index > -1) {
					Point pos = new Point((int)Math.round((c * TILE_WIDTH) - cam.position.x), 
							(int)Math.round((r * TILE_HEIGHT) - cam.position.y));
					
					g.drawImage(tileImages.get(index), pos.x, pos.y, TILE_WIDTH, TILE_HEIGHT, obs);
				}
			}
		}
	}
}
