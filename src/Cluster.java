import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cluster extends Point{
	private List<Point> listOfPoints;
	private static Random randomValue;
	private static Color[] colors = {Color.GREEN, Color.ORANGE, Color.RED, Color.WHITE, Color.YELLOW, Color.BLUE, Color.CYAN, Color.LIGHT_GRAY, Color.MAGENTA, Color.DARK_GRAY, Color.PINK, Color.BLACK, Color.GRAY};
	
	public Cluster(int x, int y, Color c){
		super(x,y,c);
		listOfPoints = new ArrayList<Point>();
	}
	
	public static List<Cluster> createCluster(int howMany, int screenSize){
		randomValue = new Random();
		List<Cluster> listOfClusters = new ArrayList<Cluster>();
		for (int i = 0; i < howMany; i++) {
			Cluster c = new Cluster(randomValue.nextInt(screenSize + 1), randomValue.nextInt(screenSize+1), colors[i]);
			listOfClusters.add(c);
		}
		return listOfClusters;
	}
	
	public List<Point> getListOfPoints(){
		return listOfPoints;
	}
	
	public void addPoint(Point point){
		listOfPoints.add(point);
	}
	
	public void clearListOfPoints(){
		listOfPoints.clear();
	}
	
	
}
