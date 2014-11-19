
public class Sphere {
	
	public Point center;
	public double radius;
	
	public Sphere(Point center, double radius){
		this.center = center;
		this.radius = radius;
	}
	
	
	private double c(Vector v){
		Vector oc = new Vector(v.start, center);
		return 	Math.pow(v.unitVector().dot(oc),2) - (Math.pow(oc.magnitude(),2) - radius*radius);
	}
	
	public boolean vectorHits(Vector v){
		return c(v) >= 0; 
	}
	
	public Point intersection(Vector v){

		if(!vectorHits(v))
			throw new IllegalArgumentException("vector does not intersect!");
		
		Vector oc = new Vector(v.start, center);
		double a = -(v.unitVector().dot(oc));
		
		
		double b = Math.sqrt(c(v));
		double d1 = a + b;
		double d2 = a - b;
		Vector dv1 = v.unitVector().mult(d1);
		Vector dv2 = v.unitVector().mult(d2);
		
		double xf,yf,zf;
		//this becomes weird when you move the light
		if(dv1.magnitude() > dv2.magnitude()){
			xf = dv1.x;
			yf = dv1.y;
			zf = dv1.z;
		}else{
			xf = dv2.x;
			yf = dv2.y;
			zf = dv2.z;
		}
		return new Point((v.start.x - xf), (v.start.y - yf), (v.start.z - zf));
	}
	
	public static boolean feq(double a, double b, double precision){
		return Math.abs(a-b) <= precision;
	}
	
	public boolean isOnSphere(Point p, double precision){
		return feq(p.distance(center), radius, precision);
	}
	
	public boolean isOnSphere(Point p){
		return feq(p.distance(center), radius, 0.0000001);
	}
	
	public Vector unitNormalVector(Point p){
		if(!isOnSphere(p))
			throw new IllegalArgumentException("Point " + p + " not on sphere!");
		
		Vector direction = new Vector(center, p).unitVector();
		return new Vector(p, direction.x, direction.y, direction.z);
	}
	
	public String toString(){
		return "["+center + ","+radius+"]"; 
	}
}
