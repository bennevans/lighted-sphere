
public class Window{
	public double yMin, yMax, xMin, xMax;
	int xPixels, yPixels;
		
	public Window(double xMin, double xMax, double yMin, double yMax, int xPixels, int yPixels){
		this.yMin = yMin;
		this.yMax = yMax;
		this.xMin = xMin;
		this.xMax = xMax;
		this.xPixels = xPixels;
		this.yPixels = yPixels;
	}
	
	
	public double xPixelsPerUnit(){
		return xPixels/(xMax-xMin);
	}
	
	public double yPixelsPerUnit(){
		return yPixels/(yMax-yMin);
	}
	
	public double xUnitsPerPixel(){
		return 1/xPixelsPerUnit();
	}
	
	public double yUnitsPerPixel(){
		return 1/xPixelsPerUnit();
	}
	
	public Point getPointFromPixel(double xPixel, double yPixel){
		return new Point(xPixel * xUnitsPerPixel(), yPixel * yUnitsPerPixel(), 0);
	}
	
	public String toString(){
		return String.format("%dx%d [%1.2f,%1.2f] x [%1.2f,%1.2f]", xPixels,yPixels,xMin,xMax,yMin,yMax);
	}
}
