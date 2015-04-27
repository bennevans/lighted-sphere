
public class Point {
	
	public double x,y,z;
	
	public static final Point ORIGIN = new Point(0, 0, 0);
	
	public Point(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public String toString(){
		return "("+x+","+y+","+z+")";
	}
	
	public double distance(double x1, double y1, double z1){
		return Math.sqrt((x1-x)*(x1-x) + (y1-y)*(y1-y) + (z1-z)*(z1-z));
	}
	
	public double distance(Point p){
		return distance(p.x, p.y, p.z);
	}
	
}
