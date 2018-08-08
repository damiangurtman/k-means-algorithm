
public class Main{
	public static int points = 1000000;
	public static int clusters = 13;
	public static int windowSize = 500;
	
	public static void main(String[] args) throws InterruptedException {
		// 1 - miejska
		// 2 - euklidesowa
		Frame myFrame = new Frame(points, clusters, 2, windowSize);
		myFrame.runThis();
	}
}
