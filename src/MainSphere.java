import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 * @author Ben Evans
 * 
 * This project generates a sphere given an observer location,
 * window size, light source location, and sphere center and radius.
 * 
 * Outputs a file plottable with the following gnuplot command:
 * plot "image.txt" with rgbimage
 *
 *
 */

public class MainSphere {

	
	public static void main(String args[]) throws IOException{	
		
		
		String filename = "image.txt";
		double sphereX = 15;
		double sphereY = 15;
		double sphereZ = -5000;
		double sphereRadius = 4900;
		int xpixels = 1000, ypixels = 1000;
		int xViewMin = 0;
		int xViewMax = 30;
		int yViewMin = 0;
		int yViewMax = 30;
		double lightX = 1000;
		double lightY = 1000;
		double lightZ = 500;
		double observerX = 15;
		double observerY = 15;
		double observerZ = 2;
		
		double lightIntensity = 1;
		
		int r = 255;
		int g = 0;
		int b = 0;
		
		int backgroundR = 0;
		int backgroundG = 0;
		int backgroundB = 255;
		
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
		
		BufferedImage bufferedImage = new BufferedImage(xpixels, ypixels, BufferedImage.TYPE_INT_RGB);
		
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
				fw.write(j+" "+i+" "+rl+" "+gl+" "+bl+"\n");
				bufferedImage.setRGB(i, j, new Color(rl,gl,bl).getRGB());
			}
		}
		fw.close();
		System.out.println(" done in " + (System.currentTimeMillis()-startTime)/1000. + " seconds!");
		JFrame j =  new JFrame();
		j.getContentPane().setLayout(new FlowLayout());
		j.getContentPane().add(new JLabel(new ImageIcon(bufferedImage)));
		j.pack();
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}
