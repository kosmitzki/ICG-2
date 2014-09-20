package Animation;


import org.lwjgl.input.Keyboard;

import ogl.app.Input;
import scenegraph.basics.Node;

public class RotateKey extends Rotate {


	int key;
	float angle;
	float angle2;
	boolean switchTurn = false;

	//dreht irgendwas, aber laesst auch den cube verschwinden wenn 0.0.0


	public RotateKey(Node node, int key, float angle) {
		super(node);
		this.key = key;
		this.angle = angle+0.3f;
		this.angle2 = angle-0.3f;
	}



	@Override
	public void animate(Input input) {	
		
		if (input.isKeyDown(Keyboard.KEY_Y)) {
			animate(axisX, angle);
		} 
		if (input.isKeyDown(Keyboard.KEY_H)) {
			animate(axisX, angle2);
		} 
		if (input.isKeyDown(Keyboard.KEY_8)) {
			animate(axisY, angle);
		}  
		if (input.isKeyDown(Keyboard.KEY_9)) {
			animate(axisY, angle2);
		} 
		if (input.isKeyDown(Keyboard.KEY_U)) {
			animate(axisZ, angle);
		}  
		if (input.isKeyDown(Keyboard.KEY_O)) {
			animate(axisZ, angle2);
		}
	}}

