package Animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;
import ogl.app.Input;
import ogl.vecmath.Matrix;

import org.lwjgl.input.Keyboard;

import scenegraph.basics.Node;

public class ScaleKey extends Scale {
	
	public int key;

	public ScaleKey(Node node, int key) {
		super(node);
		this.key = key;
	}
	
	public void animate(Input input) {
		float big = 1.001f;
		float small = 0.999f;
		
		if (input.isKeyDown(Keyboard.KEY_B)) {
			Matrix help = node.getTransformation().mult(vecmath.scaleMatrix(big, big, big));
			node.setTransformation(help);
		} if (input.isKeyDown(Keyboard.KEY_S)) {
			Matrix help1 = node.getTransformation().mult(vecmath.scaleMatrix(small, small, small));
			node.setTransformation(help1);
			//reset
		} if (input.isKeyDown(Keyboard.KEY_N)) {
			node.setTransformation(vecmath.scaleMatrix(1, 1, 1));
		}
	}

}
