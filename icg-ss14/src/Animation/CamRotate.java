package animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;


import ogl.vecmath.Matrix;
import ogl.vecmath.Vector;
import scenegraph.basics.Node;



//TODO das ist das gleiche wie Rotation aber warum haben wir das alles abstract?
public abstract class CamRotate extends Animation{

	Vector axisX = vecmath.vector(1, 0, 0);
	Vector axisY = vecmath.vector(0, 1, 0);
	Vector axisZ = vecmath.vector(0, 0, 1);


	public CamRotate(Node node){
		super(node);
	}
	
	
	public void animate(Vector axis, float angle) {	
		
			Matrix hopp = vecmath.rotationMatrix(axis, angle).mult(node.getTransformation());
			node.setTransformation(hopp);
		
	}


		
		
	

}
