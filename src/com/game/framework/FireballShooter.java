package com.game.framework;

import com.game.main.Handler;
import com.game.objects.Fireball;
import com.game.objects.Player;

public class FireballShooter {

	private Handler handler;
	
	public FireballShooter(Handler handler) {
		this.handler = handler;
	}
	
	public void shoot(Player player) {
		int direction = player.getDirection();
		if(direction == Player.UP) handler.objects.add(new Fireball(player.getX() + 6, player.getY() + 6, ObjectType.Fireball, 270, handler, player));
		else if(direction == Player.RIGHT) handler.objects.add(new Fireball(player.getX() + 6, player.getY() + 6, ObjectType.Fireball, 0, handler, player));
		else if(direction == Player.DOWN) handler.objects.add(new Fireball(player.getX() + 6, player.getY() + 6, ObjectType.Fireball, 90, handler, player));
		else if(direction == Player.LEFT) handler.objects.add(new Fireball(player.getX() + 6, player.getY() + 6, ObjectType.Fireball, 180, handler, player));
	}
}
