package com.samspeck.hacktxgame;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.ImageObserver;


public class Entity {
	
	public Vector2D position;
	public Vector2D velocity;
	public Vector2D acceleration;
	
	public Sprite currentSprite;
	
	public Entity() {
		position = new Vector2D(0, 0);
		velocity = new Vector2D(0, 0);
		acceleration = new Vector2D(0, 0);
		
		currentSprite = null;
	}
	
	public void update() {
		velocity.x += acceleration.x;
		velocity.y += acceleration.y;
		
		position.x += velocity.x;
		position.y += velocity.y;
		
		if (currentSprite != null) {
			currentSprite.updateAnimation();
		}
	}
	
	public void render(Graphics g, ImageObserver obs) {
		if (currentSprite != null) {
			currentSprite.render(g, obs, new Point((int)Math.round(position.x), (int)Math.round(position.y)));
		}
	}
}
