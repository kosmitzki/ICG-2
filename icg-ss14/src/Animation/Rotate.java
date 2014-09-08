package Animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;

import org.lwjgl.input.Keyboard;

import ogl.app.Input;
import ogl.vecmath.Matrix;
import ogl.vecmath.Vector;
import scenegraph.basics.GroupeNode;
import scenegraph.basics.Node;


public class Rotate extends Animation{

	float angle;
	
	//dreht irgendwas, aber laesst auch den cube verschwinden wenn 0.0.0
		Vector axisX = vecmath.vector(1, 0, 0);
		Vector axisY = vecmath.vector(0, 1, 0);
		Vector axisZ = vecmath.vector(0, 0, 1);
	
	
//	public Rotate(float angle, Vector axis, float elapsed) {
//		super(angle, axis);
//		this.elapsed = elapsed;
//	}
	//TODO yo zwischen den beiden constructoren und so haengt glaube ich der fehler
	public Rotate(Node node, int key, float angle) {
		super(node, key);
		this.angle = angle*0.1f;
	}


	@Override
	public void animate(Input input) {	
		if (input.isKeyDown(Keyboard.KEY_X)) {
			Matrix hopp = node.getTransformation().mult(vecmath.rotationMatrix(axisX, angle));
			node.setTransformation(hopp);
//			angle += 90 * elapsed;
//			axis = vecmath.vector(1, 0, 0);
		
		}
		if (input.isKeyDown(Keyboard.KEY_Y)) {
			Matrix hopp1 = node.getTransformation().mult(vecmath.rotationMatrix(axisY, angle));
			node.setTransformation(hopp1);
			
		} 
		if (input.isKeyDown(Keyboard.KEY_Z)) {
			Matrix hopp2 = node.getTransformation().mult(vecmath.rotationMatrix(axisZ, angle));
			node.setTransformation(hopp2);
			
		}
	}

}
