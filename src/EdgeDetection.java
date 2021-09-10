import java.awt.image.Raster;

public class EdgeDetection {
	Algorithm algorithm;
	
	public EdgeDetection(Algorithm algo) {
		this.algorithm = algo;
	}
	
	public int[][] detectEdges(MonochromeImage image) {
		return thin(image.getData());
	}
	public int[][] thin(Raster raster) {
		int[][] ar = new int[raster.getWidth()][raster.getHeight()];
		for(int i = 0; i < raster.getWidth(); ++i) {
			for(int j = 0; j < raster.getHeight(); ++j) {
				int c[] = new int[3];
				ar[i][j] = raster.getPixel(i, j, c)[0];
			}
		}
		return detectEdges(ar);
	}
	
	public int[][] detectEdges(int[][] image) {
		int n = image.length;
		int m = image[0].length;
		int[][] ar = new int[n][m];
		for(int i = 0; i < n; ++i) {
			for(int j = 0; j < m; ++j) {
				ar[i][j] = image[i][j];
			}
		}
		return algorithm.detectEdges(ar);
	}
	
}
