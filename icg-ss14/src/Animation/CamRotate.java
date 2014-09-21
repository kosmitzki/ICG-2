package Animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;

import org.lwjgl.input.Keyboard;

import ogl.app.Input;
import ogl.vecmath.Matrix;
import ogl.vecmath.Vector;
import scenegraph.basics.Node;




public abstract class CamRotate extends Animation{

//	public Rotate(float angle, Vector axis, float elapsed) {
//		super(angle, axis);
//		this.elapsed = elapsed;
//	}
	Vector axisX = vecmath.vector(1, 0, 0);
	Vector axisY = vecmath.vector(0, 1, 0);
	Vector axisZ = vecmath.vector(0, 0, 1);


	public CamRotate(Node node){
		super(node);
//		this.angle = angle+0.7f;
	}
	
	
	public void animate(Vector axis, float angle) {	
		
			Matrix hopp = node.getTransformation().mult(vecmath.rotationMatrix(axis, angle));
			node.setTransformation(hopp);
		
	}


		
		
	

}
