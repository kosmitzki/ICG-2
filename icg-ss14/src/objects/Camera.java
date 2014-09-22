package objects;

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
	
	/**
	 * gibt die invertierte Transformation der Kamera zurück
	 */
	@Override
	public Matrix isCamera(){
		return getTransformation().invertFull();
		}
	/**
	 * Konstruktor mit festen Objektkoordinaten
	 * @param camera
	 */
	public Camera(Camera camera) { //default Camera
		
		this(vecmath.vector(0, 0, 0), vecmath.vector(0, 0, 1), vecmath.vector(0, 1, 0));
		this.camera = camera;
	}
	
/**
 * Konstruktor, mit übergebenem Objektkoordinaten
 * @param pos
 * @param forward
 * @param up
 */
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