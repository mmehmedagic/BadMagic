package com.game.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.game.main.Game;
import com.game.main.Handler;
import com.game.objects.Player;

public class KeyInput extends KeyAdapter {

	private Handler handler;
	private FireballShooter shooter;
	private Game game;

	public KeyInput(Handler handler, FireballShooter shooter, Game game) {
		this.handler = handler;
		this.shooter = shooter;
		this.game = game;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		Player player1 = getPlayer(1);
		Player player2 = getPlayer(2);
		if(game.getState() == Game.GAME) {
			if(key == KeyEvent.VK_W) player1.setMoving(true);
			else if(key == KeyEvent.VK_A) player1.turn(false);
			else if(key == KeyEvent.VK_D) player1.turn(true);
			else if(key == KeyEvent.VK_S) shooter.shoot(player1);
			else if(key == KeyEvent.VK_UP) player2.setMoving(true);
			else if(key == KeyEvent.VK_LEFT) player2.turn(false);
			else if(key == KeyEvent.VK_RIGHT) player2.turn(true);
			else if(key == KeyEvent.VK_DOWN) shooter.shoot(player2);
			else if(key == KeyEvent.VK_ESCAPE) game.setState(Game.PAUSE);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		Player player1 = getPlayer(1);
		Player player2 = getPlayer(2);
		if(key == KeyEvent.VK_W) player1.setMoving(false);
		else if(key == KeyEvent.VK_UP) player2.setMoving(false);
	}
	
	public Player getPlayer(int playerNum) {
		for(int i = 0; i < handler.objects.size(); i++) {
			GameObject obj = handler.objects.get(i);
			if(playerNum == 1 && obj.getType() == ObjectType.Player1) return (Player) obj;
			else if(playerNum == 2 && obj.getType() == ObjectType.Player2) return (Player) obj;
		}
		return null;
	}
}


