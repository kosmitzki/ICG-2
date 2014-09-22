package animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;
import objects.Node;
import ogl.vecmath.Matrix;
import ogl.vecmath.Vector;

//RotateStatus CamRotateChild erben von dieser Klasse
public abstract class Rotate extends Animation{

	Vector axisX = vecmath.vector(1, 0, 0);
	Vector axisY = vecmath.vector(0, 1, 0);
	Vector axisZ = vecmath.vector(0, 0, 1);


	public Rotate(Node node){
		super(node);
	}
	
	
	public void animate(Vector axis, float angle) {	
		
			Matrix hopp = node.getTransformation().mult(vecmath.rotationMatrix(axis, angle));
			node.setTransformation(hopp);
		
	}


		
		
	

}
