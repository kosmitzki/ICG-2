package Animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;
import ogl.app.Input;
import ogl.vecmath.Matrix;

import org.lwjgl.input.Keyboard;

import scenegraph.basics.Node;

public class Checked extends Animation{
	
	
	public Checked(Node node, int key) {
		super(node, key);
	}

	@Override
	public void animate(Input input) {
		
		if (input.getMousePosition().equals(node)) {
			node.setTransformation(vecmath.scaleMatrix(0.5f, 0.5f, 0.5f));
			node.check();
		}
		
		if (input.isKeyDown(Keyboard.KEY_F)) {
			node.setTransformation(vecmath.scaleMatrix(0.5f, 0.5f, 0.5f));
			node.check();
			//reset
		} if (input.isKeyDown(Keyboard.KEY_N)) {
			node.setTransformation(vecmath.scaleMatrix(1, 1, 1));
			node.uncheck();
		}
	}
	
	

}
