package Animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;



import org.lwjgl.input.Keyboard;

import ogl.app.Input;
import ogl.vecmath.Matrix;
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

	@Override
	public void animate(Input input) {
		
		Matrix hopp = node.getTransformation().mult(vecmath.scaleMatrix(0.5f, 0.5f, 0.5f));
		node.setTransformation(hopp);
		
	}
	



	
	

}
