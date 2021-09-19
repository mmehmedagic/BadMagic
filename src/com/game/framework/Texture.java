package com.game.framework;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {

	private BufferedImage levelSheet;
	public BufferedImage[] levelSprites;
	
	public Texture(){
		levelSheet = loadImage("/level_sheet.png");
		levelSprites = new BufferedImage[9];
		getTextures();
	}
	
	private void getTextures(){
		levelSprites[0] = levelSheet.getSubimage(0, 0, 16, 16);
		levelSprites[1] = levelSheet.getSubimage(32, 0, 32, 32);
		levelSprites[2] = levelSheet.getSubimage(64, 0, 32, 32);
		levelSprites[3] = levelSheet.getSubimage(96, 0, 32, 32);
		levelSprites[4] = levelSheet.getSubimage(128, 0, 32, 32);
		levelSprites[5] = levelSheet.getSubimage(32, 32, 32, 32);
		levelSprites[6] = levelSheet.getSubimage(64, 32, 32, 32);
		levelSprites[7] = levelSheet.getSubimage(96, 32, 32, 32);
		levelSprites[8] = levelSheet.getSubimage(128, 32, 32, 32);
	}
	
	public BufferedImage loadImage(String path){
		try {
			return ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
