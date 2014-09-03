package Animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;

import org.lwjgl.input.Keyboard;

import ogl.app.Input;
import ogl.vecmath.Matrix;
import ogl.vecmath.Vector;
import scenegraph.basics.Node;


//TODO ich wei� auch nicht wie man das aufruft
public class Rotate extends Animation{
	public float elapsed;

	
	
	
	public Rotate(float angle, Vector axis, float elapsed) {
		super(angle, axis);
		this.elapsed = elapsed;
	}
	
	
	public Rotate(Node node, int key) {
		super(node, key);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void animate(Input input) {			
		while (input.isKeyDown(Keyboard.KEY_X)) {
			angle += 90 * elapsed;
			axis = vecmath.vector(1, 0, 0);
			break; // brauch man das break hier ueberhaupt
		}
		while (input.isKeyDown(Keyboard.KEY_Y)) {
			axis = vecmath.vector(0, 1, 0);
			angle += 90 * elapsed;
			break;
		} 
		while (input.isKeyDown(Keyboard.KEY_Z)) {
			axis = vecmath.vector(0, 0, 1);
			angle += 90 * elapsed;
			break;
		}
	}

}