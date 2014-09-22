package animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;

import org.lwjgl.input.Keyboard;

import objects.Node;
import ogl.app.Input;
import ogl.vecmath.Matrix;

public class Move extends Animation {

	public int key;
	
	public Move(Node node, int key) {
		super(node);
		this.key = key;
	}

	//da die Methode ganz oft pro Sekunde aufgerufen wird, bewegen sich die Objekte per
	//Tastendruck fl???ssig rauf runter (haben kleine float gew???hlt)
	@Override
	public void animate(Input input) {

			float up = 0.002f;
			float down = -0.002f;
			float left = -0.002f;
			float right = 0.002f;
			float front = 0.002f;
			float back = -0.002f;

			if (input.isKeyDown(Keyboard.KEY_W)) {
				Matrix mU = vecmath.translationMatrix(0, up, 0).mult(node.getTransformation());
				node.setTransformation(mU);}
			if (input.isKeyDown(Keyboard.KEY_S)) {
				Matrix mD = vecmath.translationMatrix(0, down, 0).mult(node.getTransformation());
				node.setTransformation(mD);}
			if (input.isKeyDown(Keyboard.KEY_A)) {		
				Matrix mL = vecmath.translationMatrix(left, 0, 0).mult(node.getTransformation());
				node.setTransformation(mL);}
			if (input.isKeyDown(Keyboard.KEY_D)) {
				Matrix mR = vecmath.translationMatrix(right, 0, 0).mult(node.getTransformation());
				node.setTransformation(mR);}
			if (input.isKeyDown(Keyboard.KEY_Q)) {
				Matrix mF = vecmath.translationMatrix(0, 0, front).mult(node.getTransformation());
				node.setTransformation(mF);}
			if (input.isKeyDown(Keyboard.KEY_E)) {
				Matrix mB = vecmath.translationMatrix(0, 0, back).mult(node.getTransformation());
				node.setTransformation(mB);}
		
	}
}
