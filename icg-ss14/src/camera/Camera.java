package camera;

import objects.Node;
import ogl.vecmath.Color;
import ogl.vecmath.Matrix;
import ogl.vecmath.Vector;
import static ogl.vecmathimp.FactoryDefault.vecmath;



public class Camera extends Node {
	public static final Vector yAxis = vecmath.vector(0, 1, 0); //absolut up in the world
	Camera camera;
	Vector pos;
	Vector forward;
	Vector up;
	
	//TODO gibt das die Matrix der Kamera invertiert wieder?
	@Override
	public Matrix isCamera(){
		return getTransformation().invertFull();
		}
	
//TODO setzt das die Kamera?
	public Camera(Camera camera) { //default Camera
		
		this(vecmath.vector(0, 0, 0), vecmath.vector(0, 0, 1), vecmath.vector(0, 1, 0));
		this.camera = camera;
	}
	

	public Camera(Vector pos, Vector forward, Vector up) {
		this.pos = pos;
		this.forward = forward;
		this.up = up;
	}


	@Override
	public void display(Matrix m) {		
	}


	@Override
	public Color[] getC() {
		return null;
	}


	@Override
	public void setC(Color[] c) {	
	}
}