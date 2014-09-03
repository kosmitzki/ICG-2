package Animation;

import ogl.app.Input;
import scenegraph.basics.GroupeNode;
import scenegraph.basics.Node;




public class MakeVisible extends Animation {
	public GroupeNode parent;

	public MakeVisible(Node node, int key, GroupeNode parent) {
		super(node, key);
		this.parent = parent;

	}

	public void animate(Input input){
		if (input.isKeyToggled(key)){
			parent.addChild(node);
		}
	}

}
