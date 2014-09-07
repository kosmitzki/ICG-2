package Animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;

import org.lwjgl.input.Keyboard;

import ogl.app.Input;
import ogl.vecmath.Matrix;
import ogl.vecmath.Vector;
import scenegraph.basics.GroupeNode;
import scenegraph.basics.Node;


public class Rotate extends Animation{

	
	
	
	public Rotate(float angle, Vector axis, float elapsed) {
		super(angle, axis);
		this.elapsed = elapsed;
	}
	//TODO yo zwischen den beiden constructoren und so haengt glaube ich der fehler
	public Rotate(Node node, int key) {
		super(node, key);
	}


	@Override
	public void animate(Input input) {	
		if (input.isKeyDown(Keyboard.KEY_X)) {
			angle += 90 * elapsed;
			axis = vecmath.vector(1, 0, 0);
		
		}
		if (input.isKeyDown(Keyboard.KEY_Y)) {
			axis = vecmath.vector(0, 1, 0);
			angle += 90 * elapsed;
			
		} 
		if (input.isKeyDown(Keyboard.KEY_Z)) {
			axis = vecmath.vector(0, 0, 1);
			angle += 90 * elapsed;
			
		}
	}

}
