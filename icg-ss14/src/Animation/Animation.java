package Animation;


import ogl.app.Input;
import ogl.vecmath.Vector;
import scenegraph.basics.Node;


public abstract class Animation {    
	
	public Node node;
	public int key;
	public float angle;
	public Vector axis;
	public float elapsed;
	
	// das hier ist von der Move Klasse benoetigt
	public Animation(Node node, int key){
		this.node = node;
		this.key = key;
		
	}
	
	
	abstract public void animate(Input input);


}
