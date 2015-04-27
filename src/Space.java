

public class Space {

	public Point observer, light;
	public Sphere sphere;
	public Window window;
	
	public Space(Window window, Point observer, Point light, Sphere sphere){
		this.window = window;
		this.observer = observer;
		this.light = light;
		this.sphere = sphere;
	}
	
	public boolean collidesWithSphere(int xPixel, int yPixel){
		Vector towardsSphere = new Vector(observer, window.getPointFromPixel(xPixel, yPixel));
		return sphere.vectorHits(towardsSphere);
	}
	
	public double calculateIntensity(int xPixel, int yPixel){
		if(!collidesWithSphere(xPixel, yPixel))
			return -1.0;
		
		Vector towardsSphere = new Vector(observer, window.getPointFromPixel(xPixel, yPixel));
		Point collisionPoint = sphere.intersection(towardsSphere);
		
		double intensity = Math.abs(sphere.unitNormalVector(collisionPoint).cosAngle(new Vector(light, collisionPoint)));
		
		return intensity;
	}
		
}
