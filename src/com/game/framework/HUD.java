package com.game.framework;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import com.game.main.Game;

public class HUD {

	private Timer timer;
	private Points points;
	private final int centerBoxHeight = 200;
	private int messageTime;
	private String message;
	private final Font messageFont = new Font("arial", Font.PLAIN, 120);
	
	public HUD(Timer timer, Points points) {
		this.timer = timer;
		this.points = points;
	}
	
	public void render(Graphics g) {
		int time = timer.getTime();
		int player1 = points.getPlayer1points();
		int player2 = points.getPlayer2points();
		g.setColor(Color.black);
		g.setFont(new Font("Monospaced", Font.PLAIN, 30));
		String text = "Player 1: " + Integer.toString(player1) + " Time: " + Integer.toString(time) + " Player 2: " + Integer.toString(player2);
		Point center = centerText(g, text, new Rectangle(0, 0, Game.WIDTH, centerBoxHeight), g.getFont());
		g.drawString(text, (int) center.getX(), (int) center.getY()); 
		if(messageTime > 0) {
			messageTime--;
			center = centerText(g, message, new Rectangle(0, 0, Game.WIDTH, Game.HEIGHT), messageFont);
			g.setColor(Color.WHITE);
			g.setFont(messageFont);
			g.drawString(message, (int) center.getX(), (int) center.getY()); 
		}
	}
	
	public void dispMessage(int duration, String message) {
		this.message = message;
		messageTime = duration;
	}
	
	public int getMessageTime() {
		return messageTime;
	}
	
	public static Point centerText(Graphics g, String text, Rectangle rect, Font font) {
	    FontMetrics metrics = g.getFontMetrics(font);
	    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    return new Point(x, y);
	}
	
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
}
