package com.samspeck.hacktxgame.Entitys;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.ImageObserver;

import com.samspeck.hacktxgame.Camera;
import com.samspeck.hacktxgame.Game;
import com.samspeck.hacktxgame.Level;
import com.samspeck.hacktxgame.Sprite;
import com.samspeck.hacktxgame.Vector2D;


public class Entity {
	
	public Vector2D position;
	public Vector2D velocity;
	public Vector2D acceleration;
	
	public Sprite currentSprite;
	public boolean onGround = false;
	public boolean stuckAtWall = false;
	
	Game game;
	
	public Entity(Game game) {
		position = new Vector2D(0, 0);
		velocity = new Vector2D(0, 0);
		acceleration = new Vector2D(0, 0);
		
		currentSprite = null;
		
		this.game = game;
	}
	
	public void update() {
		velocity.x += acceleration.x;
		velocity.y += acceleration.y;
		
		// handle tile collisions
		onGround = false;
		int topCellY = (int)Math.round(position.y) / Level.TILE_HEIGHT;
        int bottomCellY = ((int)Math.round(position.y + currentSprite.frameHeight) - 1) / Level.TILE_HEIGHT;
        int leftCellX = (int)Math.round(position.x) / Level.TILE_WIDTH;
        int rightCellX = ((int)Math.round(position.x + currentSprite.frameWidth) - 1) / Level.TILE_WIDTH;
        int x = 0, y = 0;
        int collisionDist = 0;
        Point curCell = new Point();
        boolean collided = false;
        
        if (velocity.x > 0 || velocity.x < 0) //moving horizontally
        {
            for (int c = 0; c < 2; c++)
            {
                if (velocity.x > 0) //moving right
                {
                    x = rightCellX + 1 + c;
                    collisionDist = Math.abs((x * Level.TILE_WIDTH) - (int)Math.round(position.x + currentSprite.frameWidth));
                }
                else //moving left
                {
                    x = leftCellX - 1 - c;
                    collisionDist = Math.abs(((x + 1) * Level.TILE_WIDTH) - (int)Math.round(position.x));
                }
                for (y = topCellY; y <= bottomCellY; y++)
                {
                    curCell = new Point(x, y);
                    if (game.level.getCollisionAtCell(curCell) == Level.TILE_IMPASSABLE &&
                        collisionDist < Math.abs(velocity.x))
                    {
                        //a collision with an impassable tile will occur
                        if (velocity.x > 0)
                        {
                            position.x = x * Level.TILE_WIDTH - currentSprite.frameWidth;
                        }
                        else
                        {
                            position.x = x * Level.TILE_WIDTH + Level.TILE_WIDTH;
                        }
                        velocity.x = 0f;
                        acceleration.x = 0f;
                        collided = true;
                        stuckAtWall = true;
                        break;
                    }
                }

                if (collided)
                {
                    break;
                }
            }
        }
        position.x += velocity.x;
        
        topCellY = (int)Math.round(position.y) / Level.TILE_HEIGHT;
        bottomCellY = ((int)Math.round(position.y + currentSprite.frameHeight) - 1) / Level.TILE_HEIGHT;
        leftCellX = (int)Math.round(position.x) / Level.TILE_WIDTH;
        rightCellX = ((int)Math.round(position.x + currentSprite.frameWidth) - 1) / Level.TILE_WIDTH;
        collided = false;
		
        if (velocity.y > 0 || velocity.y < 0) //moving vertically
        {
            for (int r = 0; r < 2; r++)
            {
                if (velocity.y > 0) //moving down
                {
                    y = bottomCellY + 1 + r;
                    collisionDist = Math.abs((y * Level.TILE_HEIGHT) - (int)Math.round(position.y + currentSprite.frameHeight));
                }
                else //moving up
                {
                    y = topCellY - 1 - r;
                    collisionDist = Math.abs(((y + 1) * Level.TILE_HEIGHT) - (int)Math.round(position.y));
                }
                for (x = leftCellX; x <= rightCellX; x++)
                {
                    curCell = new Point(x, y);
                    if (game.level.getCollisionAtCell(curCell) == Level.TILE_IMPASSABLE &&
                        collisionDist < Math.abs(velocity.y))
                    {
                        //a collision with an impassable tile will occur
                        if (velocity.y > 0)
                        {
                            position.y = y * Level.TILE_HEIGHT - currentSprite.frameHeight;
                            onGround = true;
                        }
                        else
                        {
                            position.y = y * Level.TILE_HEIGHT + Level.TILE_HEIGHT;
                        }
                        velocity.y = 0f;
                        collided = true;
                        break;
                    }
                }

                if (collided)
                    break;
            }
        }
        position.y += velocity.y;
        
        
		if (currentSprite != null) {
			currentSprite.updateAnimation();
		}
	}
	
	public void render(Graphics g, ImageObserver obs, Camera cam) {
		if (currentSprite != null) {
			Point pos = new Point((int)Math.round(position.x - cam.position.x), (int)Math.round(position.y - cam.position.y));
			currentSprite.render(g, obs, pos);
		}
	}
}
