package animation;


import org.lwjgl.input.Keyboard;

import objects.Node;
import ogl.app.Input;

//TODO wird nirgends in Start aufgerufen und macht das gleiche wie RotateKey?!
public class CamRotateChild extends Rotate {


	int key;
	float angle;
	float angle2;


	public CamRotateChild(Node node, int key, float angle) {
		super(node);
		this.key = key;
		this.angle = angle+0.2f;
		this.angle2 = angle-0.2f;
	}


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
		
	}}
