
public class Vector {

	public double x,y,z;
	public Point start;
	
	public Vector(Point start, double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
		this.start = start;
	}
	
	public Vector(double x, double y, double z){
		this(Point.ORIGIN, x, y, z);
	}
	
	public Vector(Point start, Point finish){
		this.x = finish.x - start.x;
		this.y = finish.y - start.y;
		this.z = finish.z - start.z;
		this.start = start;
	}
	

	
	public Vector add(double scalar){
		return new Vector(this.x + scalar, this.y + scalar, this.z + scalar);
	}
	
	public Vector subtract(double scalar){
		return add(-scalar);
	}
	
	public Vector mult(double scalar){
		return new Vector(this.x * scalar, this.y * scalar, this.z * scalar);
	}
	
	public Vector divide(double scalar){
		return mult(1/scalar);
	}
	
	public Vector cross(Vector v){
		return new Vector(y*v.z - z*v.y, z*v.x - x*v.z, x*v.y - y*v.x);
	}
	
	public double dot(Vector v){
		return x*v.x+y*v.y+z*v.z;
	}
	
	public double cosAngle(Vector v){
		return dot(v)/(magnitude()*v.magnitude());
	}
	
	public double angle(Vector v){
		return Math.acos(cosAngle(v));
	}
	
	public double magnitude(){
		return Math.sqrt(x*x+y*y+z*z);
	}
	
	public Vector unitVector(){
		return this.divide(this.magnitude());
	}
	
	public String toString(){
		return "["+x+","+y+","+z+"]";
	}
}
