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
		int xpixels = 1920, ypixels = 1080;
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
		int b = 0;
		
		int backgroundR = 0;
		int backgroundG = 255;
		int backgroundB = 0;
		
		Sphere s = new Sphere(new Point(sphereX, sphereY, sphereZ), sphereRadius);
		Window w = new Window(xViewMin, xViewMax, yViewMin, yViewMax, xpixels, ypixels);
		Point light = new Point(lightX, lightY, lightZ);
		Point observer = new Point(observerX,observerY,observerZ);
		Space space = new Space(w, observer, light, s);
		
		File f = new File(filename);
		PrintStream ps = new PrintStream(f);
		
		double[][] pixels = new double[xpixels][ypixels];
		
		System.out.printf("Generating sphere:\n\tSphere: %s\n\tObserver: %s\n\tLight: %s\n\tWindow: %s\n",s,observer,light,w);
		
		
		System.out.print("Calculating points... ");
		
		for(int i = 0; i < ypixels; i++){
			for(int j = 0; j < xpixels; j++){
				pixels[j][i] = space.calculateIntensity(j, i);
			}
		}
		
		System.out.printf("done!\nWriting %d pixels to %s...", xpixels*ypixels, filename);
		
		for(int i = 0; i < ypixels; i++){
			for(int j = 0; j < xpixels; j++){
				
				int rl = (int)(pixels[j][i]*r);
				int gl = (int)(pixels[j][i]*r);
				int bl = (int)(pixels[j][i]*r);
				
				if(pixels[j][i] == -1){
					rl = backgroundR;
					gl = backgroundG;
					bl = backgroundB;
				}
					
				ps.printf("%d %d %d %d %d\n", j,i, rl, gl, bl);
			}
		}
		System.out.println("done!");
		ps.close();
	}
	
}
