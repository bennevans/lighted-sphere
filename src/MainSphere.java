import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * 
 * @author Ben Evans
 * 
 * This project generates a sphere given an observer location,
 * window size, light source location, and sphere center and radius.
 * 
 * Outputs a file plottable with the following gnuplot command:
 * plot "sphere.txt" with rgbimage
 *
 *
 */

public class MainSphere {

	
	public static void main(String args[]) throws FileNotFoundException{	
		
		
		String filename = "sphere.txt";
		double sphereX = 2.5;
		double sphereY = 0;
		double sphereZ = 4;
		double sphereRadius = 2;
		int xpixels = 500, ypixels = 500;
		int xViewMin = 0;
		int xViewMax = 7;
		int yViewMin = 0;
		int yViewMax = 7;
		double lightX = -2;
		double lightY = 6;
		double lightZ = 7;
		double observerX = 3.6;
		double observerY = 5;
		double observerZ = -2.7;
		
		int r = 255;
		int g = 0;
		int b = 255;
		
		Sphere s = new Sphere(new Point(sphereX, sphereY, sphereZ), sphereRadius);
		Window w = new Window(xViewMin, xViewMax, yViewMin, yViewMax, xpixels, ypixels);
		Point light = new Point(lightX, lightY, lightZ);
		Point observer = new Point(observerX,observerY,observerZ);
		Space space = new Space(w, observer, light, s);
		
		File f = new File(filename);
		PrintStream ps = new PrintStream(f);
		
		System.out.print("Writing to " + filename + "... ");
		
		for(int i = 0; i <= ypixels; i++){
			for(int j = 0; j <= xpixels; j++){
				ps.printf("%d %d %d %d %d\n", j,i, (int)(space.calculateIntensity(j, i)*r),(int)(space.calculateIntensity(j, i)*g),(int)(space.calculateIntensity(j, i)*b));
			}
		}
		System.out.println("done!");
		ps.close();
	}
	
}
