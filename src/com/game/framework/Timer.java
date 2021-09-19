package com.game.framework;

import com.game.main.Game;

public class Timer {
	private int time;
	private Game game;
	
	public Timer(Game game) {
		time = 3600;
		this.game = game;
	}

	public void tick() {
		time--;
		if(time == 0) {
			game.completeLevel();
			if(!game.isGameOver()) game.newLevel();
		}
	}

	public int getTime() {
		return time / 60;
	}
}