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
	}

	@Override
	public void animate(Input input) {

			float up = 0.01f;
			float down = -0.01f;
			float left = -0.01f;
			float right = 0.01f;
			float front = 0.01f;
			float back = -0.01f;
			
			//TODO moved jetzt wild - daten aus dem world koordinatensystem holen

			if (input.isKeyDown(Keyboard.KEY_UP)) {
			//	Matrix mU = node.getTransformation().mult(vecmath.translationMatrix(0, up, 0));
				Matrix mU = vecmath.translationMatrix(0, up, 0).mult(node.getTransformation());
				node.setTransformation(mU);}
			if (input.isKeyDown(Keyboard.KEY_DOWN)) {
				Matrix mD = vecmath.translationMatrix(0, down, 0).mult(node.getTransformation());
				node.setTransformation(mD);}
			if (input.isKeyDown(Keyboard.KEY_LEFT)) {		
				Matrix mL = vecmath.translationMatrix(left, 0, 0).mult(node.getTransformation());
				node.setTransformation(mL);}
			if (input.isKeyDown(Keyboard.KEY_RIGHT)) {
				Matrix mR = vecmath.translationMatrix(right, 0, 0).mult(node.getTransformation());
				node.setTransformation(mR);}
			if (input.isKeyDown(Keyboard.KEY_COMMA)) {
				Matrix mF = vecmath.translationMatrix(0, 0, front).mult(node.getTransformation());
				node.setTransformation(mF);}
			if (input.isKeyDown(Keyboard.KEY_PERIOD)) {
				Matrix mB = vecmath.translationMatrix(0, 0, back).mult(node.getTransformation());
				node.setTransformation(mB);}
		
	}
}
