package Animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;

import org.lwjgl.input.Keyboard;

import ogl.app.Input;
import ogl.vecmath.Matrix;
import ogl.vecmath.Vector;
import scenegraph.basics.Node;

public class CamRotateChild extends Rotate {


	int key;
	float angle;
	float angle2;
	boolean switchTurn = false;

	//dreht irgendwas, aber laesst auch den cube verschwinden wenn 0.0.0


	public CamRotateChild(Node node, int key) {
		super(node);
		this.key = key;
		this.angle = angle+0.3f;
		this.angle2 = angle-0.3f;
	}



	@Override
	public void animate(Input input) {	
		
		if (input.isKeyDown(Keyboard.KEY_U)) {
			animate(axisZ, angle);
		}  
		if (input.isKeyDown(Keyboard.KEY_O)) {
			animate(axisZ, angle2);
		}
		
	}}