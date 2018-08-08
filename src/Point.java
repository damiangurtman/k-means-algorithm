import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Point {
	private int x;
	private int y;
	private Color color;
	private static Random randomValue;
	private Cluster cluster;
	public double distance;
	
	
	public Point(int x, int y, Color c){
		this.x = x;
		this.y = y;
		this.color = c;
		this.distance = 1000;
	}
	
	public static List<Point> createPoint(int howMany, int screenSize){
		randomValue = new Random();
		List<Point> listOfPoints = new ArrayList<Point>();
		for (int i = 0; i < howMany; i++) {
			Point p = new Point(randomValue.nextInt(screenSize + 1), randomValue.nextInt(screenSize+1), Color.BLACK );
			listOfPoints.add(p);
		}
		return listOfPoints;
	}
	
	public static double odlegloscEuklidesowa(Point firstPoint, Point secondPoint){
		double x = Math.pow(firstPoint.getX() - secondPoint.getX(), 2);
		double y = Math.pow(firstPoint.getY() - secondPoint.getY(), 2);
		
		return Math.sqrt(x+y);
	}
	
	public static double odlegloscMiejska(Point firstPoint, Point secondPoint){
		double x = Math.abs(firstPoint.getX() - secondPoint.getX());
		double y = Math.abs(firstPoint.getY() - secondPoint.getY());
		
		return x+y;
	}
	
	public Cluster getCluster() {
		return cluster;
	}


	public void setCluster(Cluster cluster, double distance) {
		this.cluster = cluster;
		setColor(cluster.getColor());
		setDistance(distance);
	}

	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public double getDistance(){
		return distance;
	}
	
	public void setDistance(double distance){
		this.distance = distance;
	}
}
