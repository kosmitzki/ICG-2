package Animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;

import org.lwjgl.input.Keyboard;

import ogl.app.Input;
import scenegraph.basics.Node;


//TODO ich weiﬂ nicht wie man die in Start aufrufen muss
public class Scale extends Animation {

	public Scale(Node node, int key) {
		super(node, key);
	}

	@Override
	public void animate(Input input) {
		if (input.isKeyDown(Keyboard.KEY_B)) {
			node.setTransformation(vecmath.scaleMatrix(2, 2, 2));
		} if (input.isKeyDown(Keyboard.KEY_S)) {
			node.setTransformation(vecmath.scaleMatrix(0.5f, 0.5f, 0.5f));
		} if (input.isKeyDown(Keyboard.KEY_N)) {
			node.setTransformation(vecmath.scaleMatrix(1, 1, 1));
		}
	}

}
