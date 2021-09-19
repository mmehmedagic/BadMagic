package com.game.main;

import java.awt.Graphics;
import java.util.ArrayList;

import com.game.framework.GameObject;

public class Handler {

	public ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public Handler() {
		
	}
	
	public void tick() {
		for (int i = 0; i < objects.size(); i++) objects.get(i).tick();
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < objects.size(); i++) objects.get(i).render(g);
	}
}
