package Animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;

import org.lwjgl.input.Keyboard;

import ogl.app.Input;
import ogl.vecmath.Matrix;
import ogl.vecmath.Vector;
import scenegraph.basics.Node;

public abstract class Animation {
	
	public Node node;
	public int key;
	public float angle;
	public Vector axis;
	
	public Animation(Node node, int key){
		this.node = node;
		this.key = key;
		
	}
	
	public Animation(float angle, Vector axis){
		this.angle = angle;
		this.axis = axis;
	}
	
	
	abstract public void animate(Input input);
	
	

	
//	public static void scale (Input input) {
//		if (input.isKeyDown(Keyboard.KEY_B)) {
//			node.setTransformation(vecmath.scaleMatrix(2, 2, 2));
//		} if (input.isKeyDown(Keyboard.KEY_S)) {
//			node.setTransformation(vecmath.scaleMatrix(0.5f, 0.5f, 0.5f));
//		} if (input.isKeyDown(Keyboard.KEY_N)) {
//			node.setTransformation(vecmath.scaleMatrix(1, 1, 1));
//		}
//	}

	
	
	//ich wollte das in while schleifen machen weil wir ja noch einen ��bergang brauchen
	//aber jetzt isses nur so, wenn ich X als erstes dr��ck und dann Y oder Z wird die Rotation
	// nur schneller. vllt kann einer von euch was damit anfangen
//	public void rotate(float elapsed, Input input) {
//		
//		// TODO mit rausziehen (angle von Cube uebegeben)
//		while (input.isKeyDown(Keyboard.KEY_X)) {
//			angle += 90 * elapsed;
//			axis = vecmath.vector(1, 0, 0);
//			break;
//		}
//		while (input.isKeyDown(Keyboard.KEY_Y)) {
//			axis = vecmath.vector(0, 1, 0);
//			angle += 90 * elapsed;
//			break;
//		} 
//		while (input.isKeyDown(Keyboard.KEY_Z)) {
//			axis = vecmath.vector(0, 0, 1);
//			angle += 90 * elapsed;
//			break;
//		}
//	}

}
