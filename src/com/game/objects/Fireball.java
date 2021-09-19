package com.game.objects;

import java.awt.Color;
import java.awt.Graphics;

import com.game.framework.GameObject;
import com.game.framework.ObjectType;
import com.game.main.Handler;

public class Fireball extends GameObject {

	private final int size = 20;
	private final int speed = 5;
	private Player owner;
	private Handler handler;
	
	public Fireball(float x, float y, ObjectType type, int rotation, Handler handler, Player owner) {
		super(x, y, type);
		width = size;
		height = size;
		xspeed = (float) Math.cos(Math.toRadians(rotation)) * speed;
		yspeed = (float) Math.sin(Math.toRadians(rotation)) * speed;
		this.handler = handler;
		this.owner = owner;
	}

	@Override
	public void tick() {
		x += xspeed;
		y += yspeed;
		collision();
	}
	
	private void collision() {
		for(int i = 0; i < handler.objects.size(); i++) {
			GameObject obj = handler.objects.get(i);
			if(obj != owner && obj.getBounds().intersects(getBounds())) { 
				if(obj.getType() == ObjectType.Player1 || obj.getType() == ObjectType.Player2) {
					((Player) obj).respawn();
					handler.objects.remove(this);
				}
				else if(obj.getType() == ObjectType.Wall) handler.objects.remove(this);
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillOval((int) x, (int) y, (int) width, (int) height);
	}

}
