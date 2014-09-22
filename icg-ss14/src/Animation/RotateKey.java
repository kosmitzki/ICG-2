package animation;


import org.lwjgl.input.Keyboard;

import ogl.app.Input;
import scenegraph.basics.Node;

public class RotateKey extends Rotate {


	int key;
	float angle;
	float angle2;
	boolean switchTurn = false;

	public RotateKey(Node node, int key, float angle) {
		super(node);
		this.key = key;
		this.angle = angle+0.3f;
		this.angle2 = angle-0.3f;
	}

	//damit wird die Kamera, um die x, y und z Achse in beide richtungen gedreht
	//TODO warum gibt es CamRotateChild, macht doch auch U und O?
	@Override
	public void animate(Input input) {	

		if (input.isKeyDown(Keyboard.KEY_Y)) { //x Achse runter
			animate(axisX, angle);
		} 
		if (input.isKeyDown(Keyboard.KEY_H)) { //x Achse hoch
			animate(axisX, angle2);
		} 
		if (input.isKeyDown(Keyboard.KEY_8)) { //y Achse links
			animate(axisY, angle);
		}  
		if (input.isKeyDown(Keyboard.KEY_9)) { //y Achse rechts
			animate(axisY, angle2);
		} 
		if (input.isKeyDown(Keyboard.KEY_O)) { //z Achse rechts
			animate(axisZ, angle);
		} 
		if (input.isKeyDown(Keyboard.KEY_U)) { //z Achse links
			animate(axisZ, angle2);
		} 
	}
}

