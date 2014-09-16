package Animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;

import org.lwjgl.input.Keyboard;

import ogl.app.Input;
import ogl.vecmath.Matrix;
import ogl.vecmath.Vector;
import scenegraph.basics.Node;

public class RotateKey extends Rotate {

	
	int key;
	float angle;
	
	//dreht irgendwas, aber laesst auch den cube verschwinden wenn 0.0.0
	
	
	public RotateKey(Node node, int key, float angle) {
		// TODO Auto-generated constructor stub
		super(node);
		this.key = key;
		this.angle = angle+0.9f;
	}



	@Override
	public void animate(Input input) {	
		if (input.isKeyDown(Keyboard.KEY_X)) {
			animate(axisX, angle);
		}
		if (input.isKeyDown(Keyboard.KEY_Y)) {
			animate(axisY, angle);

			
		} 
		if (input.isKeyDown(Keyboard.KEY_Z)) {
			animate(axisZ, angle);

		}
}}
	
