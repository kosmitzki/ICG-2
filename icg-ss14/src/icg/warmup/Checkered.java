/*******************************************************************************
 * Copyright (c) 2013 Henrik Tramberend, Marc Latoschik.
 * All rights reserved.
 *******************************************************************************/
package icg.warmup;

import static ogl.vecmathimp.FactoryDefault.vecmath;
import ogl.vecmath.Color;

public class Checkered implements Painter {

	public static void main(String[] args) {
		new ImageGenerator(new Checkered(), 512, 512, "checkered.png");
	}
	
	

	@Override
	public Color pixelColorAt(int x, int y, int width, int height) {	
		//wenn 8 pixel nebeneinander schwarz sein sollen... durch 8 teilen
		int teilerW = width/8;
		int teilerH = height/8;
		if ((x/teilerW + y/teilerH) % 2 == 0)
			return vecmath.color(0, 0, 0);
		else
			return vecmath.color(1, 1, 1);

	}
}
