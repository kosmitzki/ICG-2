package Animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;

import org.lwjgl.input.Keyboard;

import ogl.app.Input;
import ogl.vecmath.Matrix;
import ogl.vecmath.Vector;
import scenegraph.basics.Node;

public class Move extends Animation {

	public Move(Node node, int key) {
		super(node, key);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void animate(Input input) {
		//TODO neue animationklasse mit 4 key die gestestet werden...
		//TODO so gehts nicht
			float up = 0.01f;
			float down = 0.01f;
			float left = 0.01f;
			float right = 0.01f;

			if (input.isKeyDown(key)) {
					Matrix mU = node.getTransformation().mult(vecmath.translationMatrix(0, up, 0));
					node.setTransformation(mU);
			
					Matrix mD = node.getTransformation().mult(vecmath.translationMatrix(0, down, 0));
					node.setTransformation(mD);
					
					Matrix mL = node.getTransformation().mult(vecmath.translationMatrix(left, 0, 0));
					node.setTransformation(mL);
					
					Matrix mR = node.getTransformation().mult(vecmath.translationMatrix(right, 0, 0));
					node.setTransformation(mR);

			
			}

		
	}

}
