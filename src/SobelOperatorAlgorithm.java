
public class SobelOperatorAlgorithm implements Algorithm {
	private int cnt = 0;
	private long lastExecutionTime = 0;
	int[][] sobelX = {{-1, 0, 1},
					  {-2, 0, 2},
					  {-1, 0, 1}};
	int[][] sobelY = {{-1, -2, -1},
			  		  {0, 0, 0},
			  		  {1, 2, 1}};
	
	@Override
	public int[][] detectEdges(int[][] image) {		
		lastExecutionTime = System.nanoTime();

		int[][] newImage = new int[image.length][image[0].length];
		for(int i = 1; i + 1 < image.length; ++i) {
			for(int j = 1; j + 1 < image[i].length; ++j) {
				int pixelX = 0;
				int pixelY = 0;
				for(int k = -1; k <= 1; ++k) {
					for(int v = -1; v <= 1; ++v) {
						pixelX += sobelX[k + 1][v + 1] * image[i + k][j + v];
						pixelY += sobelY[k + 1][v + 1] * image[i + k][j + v];
					}
				}
				
				newImage[i][j] = (int)(Math.sqrt(pixelX * pixelX + pixelY * pixelY));
				if(newImage[i][j] > 255) newImage[i][j] = 255;
			}
		}
		
		lastExecutionTime = System.nanoTime() - lastExecutionTime;
		return newImage;
	}
	
	

	@Override
	public long lastExecutionTime() {
		return lastExecutionTime;
	}
}
