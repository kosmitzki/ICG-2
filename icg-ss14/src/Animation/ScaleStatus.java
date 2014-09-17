package Animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;
import ogl.app.Input;
import ogl.vecmath.Matrix;
import scenegraph.basics.Node;
import scenegraph.basics.Status;

public class ScaleStatus extends Animation {

	public ScaleStatus(Node node) {
		super(node);
		
	}

	@Override
	public void animate(Input input) {
		
		float small = 0.7f;
		
		if (node.getStatus() == Status.ABMARKIERT || node.getStatus() == Status.ABGEARBEITET ){
			
			Matrix help = node.getTransformation().mult(vecmath.scaleMatrix(small, small, small));
			node.setTransformation(help);
		}
	}

}
