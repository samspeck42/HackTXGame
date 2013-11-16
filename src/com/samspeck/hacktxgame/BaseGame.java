package com.samspeck.hacktxgame;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public abstract class BaseGame extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 1608170509571061249L;
	private static final long FRAMERATE = 60;
	
	public BaseGame(){
		setBackground(Color.GRAY);
		setDoubleBuffered(true);
	}
	
	public void start(){
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		render(g);
		g.dispose();
	}

	@Override
	public void run() {
		init();
		long msPerFrame = 1000 / FRAMERATE;
		long lastTime = System.currentTimeMillis();
		long timeDiff = 0, sleepTime = 0;
		while(true){
			update();
			repaint();
			
			timeDiff = System.currentTimeMillis() - lastTime;
			sleepTime = msPerFrame - timeDiff;
			
			try {
				if(sleepTime > 0)
					Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			lastTime = System.currentTimeMillis();
		}
	}
	
	public abstract void init();
	public abstract void update();
	public abstract void render(Graphics g);
}
