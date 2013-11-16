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
	static final int TILE_WIDTH = 32;
	static final int TILE_HEIGHT = 32;
	
	int[][] tileMap;
	ArrayList<Image> tileImages;
	
	public Level(String levelPath) {
		tileImages = new ArrayList<Image>();
		
		ArrayList<ArrayList<Integer>> levelData = loadLevel(levelPath);
		
		if (levelData.size() > 0) { 
			tileMap = new int[levelData.size()][levelData.get(0).size()];
			
			for (int r = 0; r < levelData.size(); r++) {
				for (int c = 0; c < levelData.get(r).size(); c++) {
					tileMap[r][c] = levelData.get(r).get(c);
				}
			}
		}
		else {
			tileMap = new int[0][0];
		}
	}

	private ArrayList<ArrayList<Integer>> loadLevel(String levelPath) {
		BufferedReader in;
		String line;
		ArrayList<ArrayList<Integer>> levelData = new ArrayList<ArrayList<Integer>>();
		boolean readingLayout = false;
		
		try {
			in = new BufferedReader(new FileReader(Game.class.getResource(levelPath).getFile()));
			
			while ((line = in.readLine()) != null) {
				if (line.isEmpty()) {
					continue;
				}
				
				if(line.equals("[Layout]")) {
					readingLayout = true;
					continue;
				}
				
				if (!readingLayout) {
					ImageIcon i = new ImageIcon(Game.class.getResource(line));
					Image im = i.getImage();
					tileImages.add(im);
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
	
	public void render(Graphics g, ImageObserver obs) {
		for (int r = 0; r < tileMap.length; r++) {
			for (int c = 0; c < tileMap[r].length; c++) {
				int index = tileMap[r][c];
				
				if (index > -1) {
					Point pos = new Point(c * TILE_WIDTH, r * TILE_HEIGHT);
					
					g.drawImage(tileImages.get(index), pos.x, pos.y, TILE_WIDTH, TILE_HEIGHT, obs);
				}
			}
		}
	}
}