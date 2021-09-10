import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MonochromeImage extends BufferedImage {

	public MonochromeImage(int width, int height, int imageType) {
		super(width, height, TYPE_BYTE_GRAY);
	}
	
	public MonochromeImage(String filePath) throws IOException{
		this(ImageIO.read(new File(filePath)));
	}
	
	public MonochromeImage(int[][] image) throws Exception {
		super(image.length, image[0].length, TYPE_BYTE_GRAY);
		
		for(int i = 0; i < image.length; ++i) {
			if(image[i].length != image[0].length) throw new Exception("Wrong image grid");
			for(int j = 0; j < image[i].length; ++j) {
				int x = image[i][j];
				this.setRGB(i, j, new Color(x, x, x).getRGB());
			}
		}
	}
	
	public MonochromeImage(BufferedImage image) {
		super(image.getWidth(), image.getHeight(), TYPE_BYTE_GRAY);
		
		Graphics g = this.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
	}
}
