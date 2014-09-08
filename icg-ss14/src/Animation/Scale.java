package Animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;

import javax.swing.text.Position;

import org.lwjgl.input.Keyboard;

import ogl.app.Input;
import ogl.vecmath.Matrix;
import scenegraph.basics.Node;


//TODO ich weiﬂ nicht wie man die in Start aufrufen muss
public class Scale extends Animation {

	public Scale(Node node, int key) {
		super(node, key);
	}

	@Override
	public void animate(Input input) {
		float big = 1.01f;
		float small = 0.99f;
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
