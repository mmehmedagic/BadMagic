package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {
	
	private final int centerBoxHeight = 100;
	private final int numHelpTexts = 10;
	private Rectangle playButton;
	private Rectangle helpButton;
	private Rectangle quitButton;
	private Rectangle resumeButton;
	private Rectangle menuButton;
	private Rectangle backButton;
	private Game game;
	
	public Menu(Game game) {
		playButton = new Rectangle((Game.WIDTH - (Game.WIDTH / 3 - 100)) / 2, Game.HEIGHT / 2 - 350, Game.WIDTH / 3 - 100, 175);
		helpButton = new Rectangle((Game.WIDTH - (Game.WIDTH / 3 - 100)) / 2, Game.HEIGHT / 2 - 50, Game.WIDTH / 3 - 100, 175);
		quitButton = new Rectangle((Game.WIDTH - (Game.WIDTH / 3 - 100)) / 2, Game.HEIGHT / 2 + 250, Game.WIDTH / 3 - 100, 175);
		resumeButton = new Rectangle((Game.WIDTH - (Game.WIDTH / 3 - 100)) / 2, Game.HEIGHT / 2 - 375, Game.WIDTH / 3 - 100, 175);
		menuButton = new Rectangle((Game.WIDTH - (Game.WIDTH / 3 - 100)) / 2, Game.HEIGHT / 2 - 75, Game.WIDTH / 3 - 100, 175);
		backButton = new Rectangle((Game.WIDTH - (Game.WIDTH / 3 - 100)) / 2, Game.HEIGHT / 2 + 225, Game.WIDTH / 3 - 100, 175);
		this.game = game;
	}
	
	public void mousePressed(MouseEvent e) {
		Point mouse = e.getPoint();
		if (game.getState() == Game.MENU) {
			if (playButton.contains(mouse)) {
				game.enterGame();
			} else if (helpButton.contains(mouse)) {
				game.setState(Game.HELP);
				System.out.println("here");
			} else if (quitButton.contains(mouse)) {
				System.exit(0);
			}
		} else if (game.getState() == Game.PAUSE) {
			if (resumeButton.contains(mouse)) {
				game.setState(Game.GAME);
			} else if (menuButton.contains(mouse)) {
				game.setState(Game.MENU);
			}
		}
		else if (game.getState() == Game.HELP) {
			if (backButton.contains(mouse)) {
				game.setState(Game.MENU);
			}
		}
	}

	public void mouseReleased(MouseEvent e) {

	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(new Color(0, 33, 163));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		Font menuHeadFont = new Font("arial", 1, Game.HEIGHT / 12);
		Font menuBttnFont = new Font("arial", 1, 70);
		if (game.getState() == Game.MENU) { 
			Point center = centerText(g, "Bad Magic", new Rectangle(0, 0, Game.WIDTH, centerBoxHeight), menuHeadFont);
			g.setFont(menuHeadFont);
			g.setColor(Color.LIGHT_GRAY);
			g.drawString("Bad Magic", (int) center.getX(), (int) center.getY());
	
			g.setFont(menuBttnFont);
			g2d.draw(playButton);
			Point p = centerText(g, "Play", playButton, menuBttnFont);
			g.drawString("Play", (int) p.getX(), (int) p.getY());
	
			g2d.draw(helpButton);
			p = centerText(g, "Help", helpButton, menuBttnFont);
			g.drawString("Help", (int) p.getX(), (int) p.getY());
	
			g2d.draw(quitButton);
			p = centerText(g, "Quit", quitButton, menuBttnFont);
			g.drawString("Quit", (int) p.getX(), (int) p.getY());
		} else if (game.getState() == Game.HELP){
			Font helpFont = new Font("arial", 1, 30);
			g.setColor(Color.LIGHT_GRAY);
			Point center = centerText(g, "Help", new Rectangle(0, 0, Game.WIDTH, centerBoxHeight), menuHeadFont);
			g.setFont(menuHeadFont);
			g.drawString("Help", (int) center.getX(), (int) center.getY());
			
			g.setFont(helpFont);
			String[] helpMessages = new String[numHelpTexts];
			helpMessages[0] = "Welcome to Bad Magic! This two-player game offers a variation of using the WASD and arrow keys.";
			helpMessages[1] = "Player 1 is the red wizard and uses the WASD keys, while Player 2 is the blue wizard and uses the arrow keys.";
			helpMessages[2] = " W and the UP key is to move forward.";
			helpMessages[3] = " A and LEFT is to turn 90 degrees to the left, while D and the RIGHT key is to turn 90 degrees right.";
			helpMessages[4] = "Finally, S and DOWN is to shoot fireballs in the direction the wizard is facing.";
			helpMessages[5] = "In this game, the two players control their wizards who are using fireball magic against each other.";
			helpMessages[6] = "There are different maps in which the wizards battle each other, and the map changes every 60 seconds.";
			helpMessages[7] = "If you happen to die while the timer is still running, you will respawn where you spawned originally.";
			helpMessages[8] = "When one player dies, the other play is given a point. Whoever has more points after 5 rounds wins.";
			helpMessages[9] = "Press the escape key to pause the game.";
			
			for(int i = 0; i < helpMessages.length; i++) {
				Point p = centerText(g, helpMessages[i], new Rectangle(0, 0, Game.WIDTH, Game.HEIGHT), helpFont);
			    g.drawString(helpMessages[i], (int) p.getX(), i * 50 + 200);
			}
			
			g2d.draw(quitButton);
			g.setFont(menuBttnFont);
			Point p = centerText(g, "Back", quitButton, menuBttnFont);
			g.drawString("Back", (int) p.getX(), (int) p.getY());
		} else if (game.getState() == Game.PAUSE) {
			Point p;
			g.setFont(menuBttnFont);
			g.setColor(Color.LIGHT_GRAY);
			g2d.draw(resumeButton);
			p = centerText(g, "Resume", resumeButton, menuBttnFont);
			g.drawString("Resume", (int) p.getX(), (int) p.getY());
			
			g2d.draw(menuButton);
			p = centerText(g, "Main menu", menuButton, menuBttnFont);
			g.drawString("Main menu", (int) p.getX(), (int) p.getY());
		}
	}
	
	/**
	 * Returns a point that can be used to center the given text within a specified Rectangle.
	 * @param g A graphics object that is used to obtain the FontMetrics
	 * @param text The text to be centered
	 * @param rect The rectangle to center the text in
	 * @param font The font the text will be drawn in
	 * @return A point with the x and y coordinates that center the given text.
	 */
	public static Point centerText(Graphics g, String text, Rectangle rect, Font font) {
	    FontMetrics metrics = g.getFontMetrics(font);
	    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    return new Point(x, y);
	}
}
