package com.samspeck.hacktxgame;

public class Camera {
	
	Vector2D position;
	
	public Camera() {
		position = new Vector2D(0, 0);
	}
	
	public void lockToTarget(Vector2D targetPos, int targetWidth, int targetHeight,
			int screenWidth, int screenHeight) {
		position.x = targetPos.x - (screenWidth / 2 - targetWidth / 2);
        position.y = targetPos.y - (screenHeight / 2 - targetHeight / 2);
	}
	
	public void clampToArea(int width, int height) {
		if(position.x > width)
			position.x = width;
		if(position.y > height)
			position.y = height;
		
		if(position.x < 0)
			position.x = 0;
		if(position.y < 0)
			position.y = 0;
	}
}
