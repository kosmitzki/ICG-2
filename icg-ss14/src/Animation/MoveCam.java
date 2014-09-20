package Animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;

import org.lwjgl.input.Keyboard;

import camera.Camera;
import ogl.app.Input;
import ogl.vecmath.Matrix;
import scenegraph.basics.Node;

public class MoveCam extends Animation {

	public MoveCam(Node camera, int key) {
		super(camera);
		this.key = key;
		// TODO Auto-generated constructor stub
	}




	public int key;
	

	@Override
	public void animate(Input input) {

			float up = 0.002f;
			float down = -0.002f;
			float left = -0.002f;
			float right = 0.002f;
			float front = 0.002f;
			float back = -0.002f;
			
			//TODO moved jetzt wild - daten aus dem world koordinatensystem holen

			if (input.isKeyDown(Keyboard.KEY_I)) {
			//	Matrix mU = node.getTransformation().mult(vecmath.translationMatrix(0, up, 0));
				Matrix mU = vecmath.translationMatrix(0, up, 0).mult(node.getTransformation());
				node.setTransformation(mU);}
			if (input.isKeyDown(Keyboard.KEY_K)) {
				Matrix mD = vecmath.translationMatrix(0, down, 0).mult(node.getTransformation());
				node.setTransformation(mD);}
			if (input.isKeyDown(Keyboard.KEY_J)) {		
				Matrix mL = vecmath.translationMatrix(left, 0, 0).mult(node.getTransformation());
				node.setTransformation(mL);}
			if (input.isKeyDown(Keyboard.KEY_L)) {
				Matrix mR = vecmath.translationMatrix(right, 0, 0).mult(node.getTransformation());
				node.setTransformation(mR);}
			if (input.isKeyDown(Keyboard.KEY_PERIOD)) {
				Matrix mF = vecmath.translationMatrix(0, 0, front).mult(node.getTransformation());
				node.setTransformation(mF);}
			if (input.isKeyDown(Keyboard.KEY_COMMA)) {
				Matrix mB = vecmath.translationMatrix(0, 0, back).mult(node.getTransformation());
				node.setTransformation(mB);}
		
	}
}