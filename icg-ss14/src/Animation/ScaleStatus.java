package Animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;
import ogl.app.Input;
import ogl.vecmath.Matrix;
import scenegraph.basics.Node;
import scenegraph.basics.Status;

public class ScaleStatus extends Scale {

	public ScaleStatus(Node node) {
		super(node);
		
	}

	@Override
	public void animate(Input input) {
		
		float small = 0.4f;
		float normal = 1.0f;
		
		
		if (node.getStatus() == Status.ABMARKIERT || node.getStatus() == Status.ABGEARBEITET ){
			
		//	Matrix help = node.getTransformation().mult(vecmath.scaleMatrix(small, small, small));
			node.setTransformation(vecmath.scaleMatrix(small, small, small));
		} 
			
		if (node.getStatus() == Status.UNBEARBEITET || node.getStatus() == Status.MARKIERT ){
				
					Matrix help = node.getTransformation().mult(vecmath.scaleMatrix(normal, normal, normal));
					node.setTransformation(help);
				}
		
		

	}

}
