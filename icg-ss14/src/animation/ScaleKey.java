package animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;
import objects.Node;
import ogl.app.Input;
import ogl.vecmath.Matrix;

import org.lwjgl.input.Keyboard;

public class ScaleKey extends Scale {

	public int key;

	public ScaleKey(Node node, int key) {
		super(node);
		this.key = key;
	}

	//scaliert dadurch das kleine floats gew???hlt werden und die Methoden oft pro Sekunde aufgerufen
	//wird die Objekte fl???ssig und nicht zu schnell
	public void animate(Input input) {
		float big = 1.001f;
		float small = 0.999f;

		if (input.isKeyDown(Keyboard.KEY_V)) {
			Matrix help = node.getTransformation().mult(vecmath.scaleMatrix(big, big, big));
			node.setTransformation(help);
		} if (input.isKeyDown(Keyboard.KEY_B)) {
			Matrix help1 = node.getTransformation().mult(vecmath.scaleMatrix(small, small, small));
			node.setTransformation(help1);
		}
	}

}
