package com.samspeck.hacktxgame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Sprite {
	
	private Image sprite;
	
	private int frameWidth;
	private int frameHeight;
	private int numFrames;
	private int delay;
	
	public Sprite(String imagePath, int frameWidth, int frameHeight, int numFrames, int delay) {
		ImageIcon i = new ImageIcon(Game.class.getResource(imagePath));
		sprite = i.getImage();
		
		this.frameWidth= frameWidth;
		this.frameHeight = frameHeight;
		this.numFrames = numFrames;
		this.delay = delay;
	}
	
	public void updateAnimation() {
		// TODO
	}
	
	public void render(Graphics g, ImageObserver obs, Point pos) {
		g.drawImage(sprite, pos.x, pos.y, pos.x + frameWidth, pos.y + frameHeight, 
				0, 0, frameWidth, frameHeight, obs);
	}

}
