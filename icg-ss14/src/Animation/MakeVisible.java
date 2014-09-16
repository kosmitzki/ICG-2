package Animation;

import ogl.app.Input;
import scenegraph.basics.GroupNode;
import scenegraph.basics.Node;



public class MakeVisible extends Animation {
	public GroupNode parent;
	public int key;


	public MakeVisible(Node node, int key, GroupNode parent) {
		super(node);
		this.parent = parent;
		this.key = key;

	}

	public void animate(Input input){
		if (input.isKeyToggled(key)){
			parent.addChild(node);
		}
	}

}
