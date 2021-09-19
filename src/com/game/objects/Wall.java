package com.game.objects;

import java.awt.Graphics;

import com.game.framework.GameObject;
import com.game.framework.ObjectType;
import com.game.main.Game;

public class Wall extends GameObject {

	private final int size = 32;
	private Game game;
	
	public Wall(int x, int y, ObjectType type, Game game) {
		super(x, y, type);
		width = size;
		height = size;
		this.game = game;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(game.getTexture().levelSprites[0], (int) x, (int) y, 32, 32, null);
	}

}
