package icg.warmup.data;

import java.util.Vector;

import javax.vecmath.Point2d;

import org.lwjgl.opengl.PixelFormat;

public class Line {
	private Point2d p1;
	private Point2d p2;

	// constructor
	public Line(Point2d p1, Point2d p2){
		this.setPoint1(p1);
		this.setPoint2(p2);
	}

	// returns a vector containing all fragments belonging to this triangle
	public Vector<Fragment> render(){
		return render(
				new IntWrapper((int)getPoint1().x), 
				new IntWrapper((int)getPoint1().y), 
				new IntWrapper((int)getPoint2().x), 
				new IntWrapper((int)getPoint2().y)
				);
	}

	// create a vector containing all fragments that belong to a line between (x0,y0) and (x1,y1)
	private Vector<Fragment> render(IntWrapper x0, IntWrapper y0, IntWrapper x1, IntWrapper y1){
		Vector<Fragment> retVal = new Vector<Fragment>();
		//TODO delta muss positiv sein
		int x_0 = x0.value;
		int x_1 = x1.value;
		int y_0 = y0.value;
		int y_1 = y1.value;

		int deltax = x_1 - x_0;
		int deltay = y_1 - y_0;
		int error = deltax/2; //kurze Richtung
		
		for (int i = x_0; i < x_1; i++) {
		//	int temp = y_0;
			error = error - deltay;
			if (error < 0) {
				for (int j = y_0 + 1; j < y_1; j++) {
					retVal.add(new Fragment(i, j));
				}
				//TODO mal an Stelle temp an
			} else {
				retVal.add(new Fragment(i, y_0));
			}//TODO y bleibt auf gleicher Höhe --> mal an
			error = error + deltax;
		}

		
		// create a point with the size of 5 (in der Schleife -2 und 2) pixels at the end of the line
		// NOTE: this is meant to be replaced by the bresenham line algorithm
		for (int i = -5; i < 5; i++){
			for (int j = -5; j < 5; j++){
				retVal.add(new Fragment(x0.value + i, y0.value + j));
				retVal.add(new Fragment(x1.value + i, y1.value + j));
			}
		}
		return retVal;
	}

	public Point2d getPoint1() {
		return p1;
	}

	public void setPoint1(Point2d p1) {
		this.p1 = p1;
	}

	public Point2d getPoint2() {
		return p2;
	}

	public void setPoint2(Point2d p2) {
		this.p2 = p2;
	}
}
