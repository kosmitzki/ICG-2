package Animation;


import java.util.ArrayList;

import ogl.app.Input;
import scenegraph.basics.Node;


public abstract class Animation {    
	
	public Node node;
	public float elapsed;
	
	private static ArrayList<Animation> animationList = new ArrayList<Animation>();


//	public Animation(Node node, int key){
//		this.node = node;
//		this.key = key;
//		
//	}
	
	public Animation(Node node){
		this.node = node;
		animationList.add(this);
	}
	public static ArrayList<Animation> getList() {
		return animationList;
	}
	
	abstract public void animate(Input input);


}
