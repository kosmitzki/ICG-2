package Animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;




import ogl.vecmath.Matrix;
import scenegraph.basics.Node;


public abstract class Scale extends Animation {

	//	public int key;



	public Scale(Node node) {
		super(node);
	}



	public void animate(float float1, float float2, float float3) {

			Matrix hopp = node.getTransformation().mult(vecmath.scaleMatrix(float1, float2, float3));
			node.setTransformation(hopp);
	
	}



}
