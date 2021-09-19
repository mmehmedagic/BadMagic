package com.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.game.framework.FireballShooter;
import com.game.framework.HUD;
import com.game.framework.KeyInput;
import com.game.framework.ObjectType;
import com.game.framework.Points;
import com.game.framework.Texture;
import com.game.framework.Timer;
import com.game.objects.Player;
import com.game.objects.Wall;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private static Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int WIDTH = (int) resolution.getWidth(), HEIGHT = (int) resolution.getHeight();
	public static final int GAME = 0;
	public static final int HELP = 1;
	public static final int MENU = 2;
	public static final int PAUSE = 3;
	public static final int GAME_OVER = 4;
	private final int levelWidth = 1600, levelHeight = 896;
	private final int numLevels = 5;
	private int levelsPlayed = 0;
	private boolean running = false;
	private boolean gameOver = false;
	private int currentLevel = -1;
	private int state = MENU;
	private Thread thread;
	private Handler handler;
	private KeyInput input;
	private Texture texture;
	private HUD hud;
	private Timer timer;
	private FireballShooter shooter;
	private Points points;
	private Random random;
	private Menu menu;
	
	public Game() {
		menu = new Menu(this);
		random = new Random();
		points = new Points();
		handler = new Handler();
		hud = new HUD(timer, points);
		shooter = new FireballShooter(handler);
		input = new KeyInput(handler, shooter, this);
		texture = new Texture();
		newLevel();
		addKeyListener(input);
		addMouseListener(menu);
		new Window(WIDTH, HEIGHT, "Bad Magic", this);
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try {
			thread.join();
			running = false;
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running) {	
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}	
			render();
			frames++;				
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}
	
	private void tick(){
		if(state == GAME) {
			handler.tick();
			timer.tick();
		}
		if(gameOver && hud.getMessageTime() == 0) {
			reset();
		}
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		if(state == GAME) {
			g2d.translate((WIDTH - levelWidth) / 2, (HEIGHT - levelHeight) / 2);
			g.setColor(new Color(200, 200, 200));
			g.fillRect(-(WIDTH - levelWidth) / 2, -(HEIGHT - levelHeight) / 2, WIDTH, HEIGHT);
			handler.render(g);
			g2d.translate(-(WIDTH - levelWidth) / 2, -(HEIGHT - levelHeight) / 2);
			hud.render(g);
		} else menu.render(g);
		bs.show();
	}
	
	private void reset() {
		state = MENU;
		levelsPlayed = 0;
		gameOver = false;
		points = new Points();
	}
	
	public void enterGame() {
		state = GAME;
		newLevel();
	}
	
	public void newLevel() {
		timer = new Timer(this);
		hud.setTimer(timer);
		boolean running = true;
		int i = 0;
		while(running) {
			i = random.nextInt(5) + 1;
			if(i != currentLevel) running = false;
		}
		currentLevel = i;
		loadImageLevel(texture.loadImage("/level_" + i + ".png"));
	}
	
	private void loadImageLevel(BufferedImage image){
		handler.objects.clear();
		int width = image.getWidth();
		int height = image.getHeight();
		
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				int pixel = image.getRGB(x, y);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				if(red == 255 && blue == 255 && green == 255) handler.objects.add(new Wall(x * 32, y * 32, ObjectType.Wall, this));
				else if(red == 255 && blue == 0 && green == 0) handler.objects.add(new Player(x * 32, y * 32, ObjectType.Player1, this, handler));
				else if(red == 0 && blue == 255 && green == 0) handler.objects.add(new Player(x * 32, y * 32, ObjectType.Player2, this, handler));
			}
		}
	}
	
	public void completeLevel() {
		levelsPlayed++;
		if(levelsPlayed == numLevels) {
			String message;
			if(points.getPlayer1points() > points.getPlayer2points()) message = "Player 1 WINS!";
			else if(points.getPlayer1points() == points.getPlayer2points()) message = "Its a TIE!";
			else message = "Player 2 WINS!";
			hud.dispMessage(800, message);
			gameOver = true;
		}
	}
	
	public boolean isGameOver() {
		return gameOver;
	}
	
	// Getters and setters for application state or screen
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}

	public Texture getTexture() {
		return texture;
	}
	
	public Points getPoints() {
		return points;
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
