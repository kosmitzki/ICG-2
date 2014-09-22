package Animation;


import org.lwjgl.input.Keyboard;

import ogl.app.Input;
import scenegraph.basics.Node;

public class CamRotateChild extends Rotate {


	int key;
	float angle;
	float angle2;
	boolean switchTurn = false;


	public CamRotateChild(Node node, int key) {
		super(node);
		this.key = key;
		this.angle = angle+0.3f;
		this.angle2 = angle-0.3f;
	}


//per U und O wird die Kamera in einem oben vorgegeben Winkel gedreht
	@Override
	public void animate(Input input) {	
		
		if (input.isKeyDown(Keyboard.KEY_U)) {
			animate(axisZ, angle);
		}  
		if (input.isKeyDown(Keyboard.KEY_O)) {
			animate(axisZ, angle2);
		}
		
	}}
