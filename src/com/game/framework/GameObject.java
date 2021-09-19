package com.game.framework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class GameObject {

	protected float x, y;
	protected float width, height;
	protected float xspeed, yspeed;
	protected ObjectType type;
	
	public GameObject(float x, float y, ObjectType type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public void drawBounds(Graphics g) {
		g.setColor(Color.red);
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(getBoundsBottom());
		g2d.draw(getBoundsTop());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public ObjectType getType() {
		return type;
	}

	public void setType(ObjectType type) {
		this.type = type;
	}

	public float getXspeed() {
		return xspeed;
	}

	public void setXspeed(float xspeed) {
		this.xspeed = xspeed;
	}

	public float getYspeed() {
		return yspeed;
	}

	public void setYspeed(float yspeed) {
		this.yspeed = yspeed;
	}
	
	/**
	 * Returns a Rectangle that represents the top border of the object
	 * @return The top bounding Rectangle
	 */
	public Rectangle getBoundsTop() {
		return new Rectangle((int) x + 5, (int) y, (int) width - 10, 1);
	}
	
	/**
	 * Returns a Rectangle that represents the bottom border of the object
	 * @return The bottom bounding Rectangle
	 */
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) x + 5, (int) (y + height), (int) width - 10, 1);
	}
	
	/**
	 * Returns a Rectangle that represents the bottom border of the object
	 * @return The left bounding Rectangle
	 */
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) y + 5, 1, (int) height - 10);
	}
	
	/**
	 * Returns a Rectangle that represents the bottom border of the object
	 * @return The right bounding Rectangle
	 */
	public Rectangle getBoundsRight() {
		return new Rectangle((int) (x + width), (int) y + 5, 1, (int) height - 10);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}
}
