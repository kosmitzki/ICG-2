package Animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;

import org.lwjgl.input.Keyboard;

import Objects.Cube;
import ogl.app.Input;
import ogl.vecmath.Color;
import scenegraph.basics.Node;

public class ChangeColor extends Animation{
	
	public Node node;

	private Color[] co = { 
		      col(0, 0, 0), 
		      col(0, 0, 0), 
		      col(0, 0, 0), 
		      col(0, 0, 0),
		      col(0, 0, 0), 
		      col(0, 0, 0), 
		      col(0, 0, 0), 
		      col(0, 0, 0) 
	  };
	
	public ChangeColor (Node node, int key) {
		super (node, key);
	}
	
	private Color col(float r, float g, float b) {
		return vecmath.color(r, g, b);
	}
	

	@Override
	public void animate(Input input) {
		if (input.isKeyDown(key)) {
			node.setC(co);
		}
		
	}

}
