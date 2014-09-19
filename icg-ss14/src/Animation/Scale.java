package Animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;




import org.lwjgl.input.Keyboard;

import ogl.app.Input;
import ogl.vecmath.Matrix;
import ogl.vecmath.Vector;
import scenegraph.basics.Node;
import scenegraph.basics.Status;


//TODO ich wei� nicht wie man die in Start aufrufen muss
public abstract class Scale extends Animation {
	
//	public int key;


	public Scale(Node node) {
		super(node);
	}
	
//	public Scale(Node node, int key) {
//		super(node);
//		this.key = key;
//	}


	public void animate(float float1, float float2, float float3) {
		
		Matrix hopp = node.getTransformation().mult(vecmath.scaleMatrix(float1, float2, float3));
		node.setTransformation(hopp);
		
	}
	



	
	

}
