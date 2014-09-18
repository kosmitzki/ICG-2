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
	float angle2;
	boolean switchTurn = false;

	//dreht irgendwas, aber laesst auch den cube verschwinden wenn 0.0.0


	public RotateKey(Node node, int key, float angle) {
		// TODO Auto-generated constructor stub
		super(node);
		this.key = key;
		this.angle = angle+0.9f;
		this.angle2 = angle-0.8f;
	}



	@Override
	public void animate(Input input) {	
		
		if (input.isKeyDown(Keyboard.KEY_X) && input.isKeyDown(Keyboard.KEY_A)) {
			animate(axisX, angle);
		} 
		if (input.isKeyDown(Keyboard.KEY_X) && input.isKeyDown(Keyboard.KEY_D)) {
			animate(axisX, angle2);
		} 
		if (input.isKeyDown(Keyboard.KEY_Y) && input.isKeyDown(Keyboard.KEY_A)) {
			animate(axisY, angle);
		}  
		if (input.isKeyDown(Keyboard.KEY_Y) && input.isKeyDown(Keyboard.KEY_D)) {
			animate(axisY, angle2);
		} 
		if (input.isKeyDown(Keyboard.KEY_Z) && input.isKeyDown(Keyboard.KEY_A)) {
			animate(axisZ, angle);
		}  
		if (input.isKeyDown(Keyboard.KEY_Z) && input.isKeyDown(Keyboard.KEY_D)) {
			animate(axisZ, angle2);
		}
	}}

