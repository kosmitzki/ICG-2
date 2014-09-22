package animation;


import org.lwjgl.input.Keyboard;

import objects.Node;
import ogl.app.Input;

//TODO wird nirgends in Start aufgerufen und macht das gleiche wie RotateKey?!
public class CamRotateChild extends Rotate {


	int key;
	float angle;
	float angle2;


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
