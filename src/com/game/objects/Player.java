package com.game.objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.framework.GameObject;
import com.game.framework.ObjectType;
import com.game.main.Game;
import com.game.main.Handler;

public class Player extends GameObject {

	public static final int UP = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	private final int moveSpeed = 3;
	
	private final int size = 32;
	private boolean moving = false;
	private Handler handler;
	private Game game;
	private float spawnx, spawny;
	private int direction;
	
	public Player(int x, int y, ObjectType type, Game game, Handler handler) {
		super(x, y, type);
		this.handler = handler;
		this.game = game;
		width = size;
		height = size;
		spawnx = x;
		spawny = y;
	}

	@Override
	public void tick() {
		updateSpeeds();
		x += xspeed;
		y += yspeed;
		collision();
	}

	@Override
	public void render(Graphics g) {
		BufferedImage[] sprites = game.getTexture().levelSprites;
		if(type == ObjectType.Player1) {
			if(direction == UP) g.drawImage(sprites[3], (int) x, (int) y, null);
			else if(direction == RIGHT) g.drawImage(sprites[4], (int) x, (int) y, null);
			else if(direction == DOWN) g.drawImage(sprites[1], (int) x, (int) y, null);
			else if(direction == LEFT) g.drawImage(sprites[2], (int) x, (int) y, null);
		}
		else {
			if(direction == UP) g.drawImage(sprites[7], (int) x, (int) y, null);
			else if(direction == RIGHT) g.drawImage(sprites[8], (int) x, (int) y, null);
			else if(direction == DOWN) g.drawImage(sprites[5], (int) x, (int) y, null);
			else if(direction == LEFT) g.drawImage(sprites[6], (int) x, (int) y, null);
		}
	}
	
	private void updateSpeeds() {
		if(moving) {
			if(direction == UP) {
				xspeed = 0;
				yspeed = -moveSpeed;
			}
			else if(direction == RIGHT) {
				xspeed = moveSpeed;
				yspeed = 0;
			}
			else if(direction == DOWN) {
				xspeed = 0;
				yspeed = moveSpeed;
			}
			else if(direction == LEFT) {
				xspeed = -moveSpeed;
				yspeed = 0;
			}
		}
		else {
			xspeed = 0;
			yspeed = 0;
		}
	}
	
	private void collision() {
		for(int i = 0; i < handler.objects.size(); i++) {
			GameObject obj = null;
			if(i < handler.objects.size()) obj = handler.objects.get(i);
			else break;
			if((obj.getType() == ObjectType.Wall || obj.getType() == ObjectType.Player1 || obj.getType() == ObjectType.Player2) && obj != this) {
				if(getBoundsTop().intersects(obj.getBounds())) y = obj.getY() + obj.getHeight();		
				if(getBoundsBottom().intersects(obj.getBounds())) y = obj.getY() - height;	
				if(getBoundsRight().intersects(obj.getBounds())) x = obj.getX() - width;
				if(getBoundsLeft().intersects(obj.getBounds())) x = obj.getX() + obj.getWidth();	
			}
		}
	}
	
	public void respawn() {
		x = spawnx;
		y = spawny;
		game.getPoints().addPoints(type == ObjectType.Player1);
	}
	
	public void turn(boolean turn) {
		if(turn) direction = (direction + 1) % 4;
		else direction--;
		if(direction == -1) direction = 3;
		System.out.println(direction);
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	public int getDirection() {
		return direction;
	}
}
