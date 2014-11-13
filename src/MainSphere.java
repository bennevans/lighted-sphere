import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 
 * @author Ben Evans
 * 
 * This project generates a sphere given an observer location,
 * window size, light source location, and sphere center and radius.
 * 
 * Outputs a file plottable with the following gnuplot command:
 * plot "sphere.plot" with rgbimage
 *
 *
 */

public class MainSphere {

	
	public static void main(String args[]) throws IOException{	
		
		
		String filename = "sphere.plot";
		double sphereX = 2.5;
		double sphereY = 1;
		double sphereZ = 4;
		double sphereRadius = 2;
		int xpixels = 1920, ypixels = 1080;
		int xViewMin = 0;
		int xViewMax = 5;
		int yViewMin = -5;
		int yViewMax = 10;
		double lightX = 2.5;
		double lightY = 60;
		double lightZ = 4;
		double observerX = 2.5;
		double observerY = 1;
		double observerZ = -1;
		
		double lightIntensity = 0.5;
		
		int r = 255;
		int g = 0;
		int b = 150;
		
		int backgroundR = 0;
		int backgroundG = 0;
		int backgroundB = 150;
		
		Sphere s = new Sphere(new Point(sphereX, sphereY, sphereZ), sphereRadius);
		Window w = new Window(xViewMin, xViewMax, yViewMin, yViewMax, xpixels, ypixels);
		Point light = new Point(lightX, lightY, lightZ);
		Point observer = new Point(observerX,observerY,observerZ);
		Space space = new Space(w, observer, light, s);
		
		File f = new File(filename);
		FileWriter fw = new FileWriter(f);
		
		double[][] pixels = new double[xpixels][ypixels];
		
		System.out.printf("Generating sphere:\n\tSphere: %s\n\tObserver: %s\n\tLight: %s\n\tWindow: %s\n",s,observer,light,w);
		
		
		System.out.print("Calculating points... ");
		
		for(int i = 0; i < ypixels; i++){
			for(int j = 0; j < xpixels; j++){
				pixels[j][i] = space.calculateIntensity(j, i);
			}
		}
		
		System.out.printf("done!\nWriting %d pixels to %s...", xpixels*ypixels, filename);
		
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < ypixels; i++){
			for(int j = 0; j < xpixels; j++){
				
				int rl = (int)(pixels[j][i]*r*lightIntensity);
				int gl = (int)(pixels[j][i]*g*lightIntensity);
				int bl = (int)(pixels[j][i]*b*lightIntensity);
				
				if(pixels[j][i] == -1){
					rl = backgroundR;
					gl = backgroundG;
					bl = backgroundB;
				}
				//fw.write(String.format("%d %d %d %d %d\n", j,i, rl, gl, bl));
				fw.write(j+" "+i+" "+rl+" "+gl+" "+bl+"\n");
			}
		}
		fw.close();
		System.out.println(" done in " + (System.currentTimeMillis()-startTime)/1000. + " seconds!");
	}
	
}
