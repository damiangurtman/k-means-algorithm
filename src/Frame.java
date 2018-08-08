import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JPanel{
	private JFrame jframe;
	private List<Point> listOfPoints;
	private List<Cluster> listOfClusters;
	private List<Cluster> listOfOldClusters;
	private int method;
	private boolean done;
	
	public Frame(int howManyPoints, int howManyClusters, int method, int screenSize){
		done = false;
		this.method = method;
		
		listOfPoints = Point.createPoint(howManyPoints, screenSize);
		listOfClusters = Cluster.createCluster(howManyClusters, screenSize);
		listOfOldClusters = new ArrayList<Cluster>();
			for(Cluster cluster : listOfClusters){
				listOfOldClusters.add(new Cluster(cluster.getX(), cluster.getY(), cluster.getColor()));
			}
			
		jframe = new JFrame("Damian Gurtman : K-Means Clustering");
		jframe.setSize(screenSize, screenSize);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(this);
		jframe.setVisible(true);
		jframe.setLocationRelativeTo(null);
	}
	
	public void runThis() throws InterruptedException{
		
		while(!done){
			Thread.sleep(1000);
			checkMethod();
			jframe.repaint();
			int doneCounter = 0;
				
			for (int i = 0; i < listOfClusters.size(); i++) {
				if(listOfClusters.get(i).getX() == listOfOldClusters.get(i).getX() && listOfClusters.get(i).getY() == listOfOldClusters.get(i).getY())
					doneCounter++;
			}
				
			if(doneCounter == listOfClusters.size())
				done = true;
				
			listOfOldClusters.clear();	
			for(Cluster cluster : listOfClusters)
				listOfOldClusters.add(new Cluster(cluster.getX(), cluster.getY(), cluster.getColor()));
		}
		
	}
	
	public void checkMethod(){
		if(method == 1){
			for(Point point : listOfPoints){
				Cluster c = null;
				for(Cluster cluster : listOfClusters){
					double dist = Point.odlegloscMiejska(point, cluster);
					if(point.distance > dist){
						point.setCluster(cluster, dist);
						c = cluster;
					}
				}
				c.addPoint(point);
				point.setDistance(Double.MAX_VALUE);
			}
		}
		
		if(method == 2){
			for(Point point : listOfPoints){
				Cluster c = null;
				for(Cluster cluster : listOfClusters){
					double dist = Point.odlegloscEuklidesowa(point, cluster);
					if(point.distance > dist){
						point.setCluster(cluster, dist);
						c = cluster;
					}
				}
				c.addPoint(point);
				point.setDistance(Double.MAX_VALUE);
			}
		}
		
		for(Cluster cluster : listOfClusters){
			double x = 0.0, y = 0.0;
			
			for(Point point : cluster.getListOfPoints()){
				x = x + point.getX();
				y = y + point.getY();
			}
			
			x = x / cluster.getListOfPoints().size();
			y = y / cluster.getListOfPoints().size();
			
			cluster.setX((int)x);
			cluster.setY((int)y);
			cluster.clearListOfPoints();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		for(Point point : listOfPoints){
        	g.setColor(point.getColor());
        	g.fillRect(point.getX(), point.getY(), 1, 1);;
    	}
		
    	for(Cluster cluster : listOfClusters){
    		g.setColor(Color.BLACK);
    		g.fillOval(cluster.getX() - 1, cluster.getY() - 1, 12, 12);
    		g.setColor(cluster.getColor());
    		g.fillOval(cluster.getX(), cluster.getY(), 10, 10);
    	}
	}
}
