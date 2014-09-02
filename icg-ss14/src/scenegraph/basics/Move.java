package scenegraph.basics;

import static ogl.vecmathimp.FactoryDefault.vecmath;

import org.lwjgl.input.Keyboard;

import ogl.app.Input;
import ogl.vecmath.Matrix;
import ogl.vecmath.Vector;

public class Move extends Animation {

	public Move(Node node, int key) {
		super(node, key);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void animate(Input input) {
		//TODO neue animationklasse mit 4 key die gestestet werden...
			float up = 0.01f;
			float down = 0.0f;
			float left = 0.0f;
			float right = 0.0f;

			if (input.isKeyDown(key)) {
					
					Matrix m = node.getTransformation().mult(vecmath.translationMatrix(0, up, 0));
					node.setTransformation(m);

			
			}

		
	}

}
