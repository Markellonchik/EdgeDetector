import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main {
	static int width = 2000, height = 1000;
	static String fileName = "samolet";
	static String fileExtension = "jpg";
	public static void main(String[] args) {
		JFrame frame = new JFrame("MyImage");
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageCanvas canvas = new ImageCanvas(frame.getWidth(), frame.getHeight());
		frame.add(canvas);
				
		EdgeDetection detection = new EdgeDetection(new SobelOperatorAlgorithm());
		MonochromeImage image = null;
		
		try {
			image = new MonochromeImage(fileName + "." + fileExtension);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		canvas.addImage(image);

		System.out.println(image.getWidth() + "x" + image.getHeight());
		
		MonochromeImage imageEdges = null;
		try {
			imageEdges = new MonochromeImage(detection.detectEdges(image));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(detection.algorithm.lastExecutionTime() / 1000000.0);
		
		canvas.addImage(imageEdges);

		frame.setVisible(true);
		saveResult(image, imageEdges);
	}
	
	public static void saveResult(MonochromeImage image, MonochromeImage imageEdges) {
		BufferedImage combined = new BufferedImage(image.getWidth() + imageEdges.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		Graphics g = combined.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.drawImage(imageEdges, image.getWidth(), 0, null);
		g.dispose();
		
		try {
			ImageIO.write(combined, "PNG", new File(fileName + "Result.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
