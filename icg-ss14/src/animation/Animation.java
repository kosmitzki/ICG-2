package animation;


import java.util.ArrayList;

import objects.Node;
import ogl.app.Input;


public abstract class Animation {    
	
	public Node node;
	public float elapsed;
	
	// an animationList werden alle Animationsklassen mit dem entsprechenden Keystroke angehaengt
	private static ArrayList<Animation> animationList = new ArrayList<Animation>();
	
	public Animation(Node node){
		this.node = node;
		animationList.add(this);
	}
	public static ArrayList<Animation> getList() {
		return animationList;
	}
	
	//jede Animationsklasse hat diese Methode
	abstract public void animate(Input input);


}
