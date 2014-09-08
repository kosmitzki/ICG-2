package Animation;

import ogl.app.Input;
import scenegraph.basics.GroupNode;
import scenegraph.basics.Node;




public class MakeVisible extends Animation {
	public GroupNode parent;

	public MakeVisible(Node node, int key, GroupNode parent) {
		super(node, key);
		this.parent = parent;

	}

	public void animate(Input input){
		if (input.isKeyToggled(key)){
			parent.addChild(node);
		}
	}

}
