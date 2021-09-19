package com.game.framework;

public class Points {
	private int player1points = 0;
	private int player2points = 0;

	public void addPoints(boolean player) {
		if (player) {
			player1points = player1points + 1;
		}
	
		if (!player) {
			player2points = player2points + 1;
		}
	}

	public int getPlayer1points() {
		return player1points;
	}

	public int getPlayer2points() {
		return player2points;
	}
}
