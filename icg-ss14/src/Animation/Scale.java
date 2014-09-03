package Animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;
import ogl.app.Input;
import scenegraph.basics.Node;


//TODO ich weiﬂ nicht wie man die in Start aufrufen muss
public class Scale extends Animation {

	public Scale(Node node, int key) {
		super(node, key);
	}

	@Override
	public void animate(Input input) {
		if (input.isKeyDown(key)) {
			node.setTransformation(vecmath.scaleMatrix(2, 2, 2));
		} if (input.isKeyDown(key)) {
			node.setTransformation(vecmath.scaleMatrix(0.5f, 0.5f, 0.5f));
		} if (input.isKeyDown(key)) {
			node.setTransformation(vecmath.scaleMatrix(1, 1, 1));
		}
	}

}
