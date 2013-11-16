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
}
