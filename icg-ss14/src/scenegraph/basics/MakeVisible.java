package scenegraph.basics;

import ogl.app.Input;

//TODO analog hierzu alle animations in eigene klassen stecken



public class MakeVisible extends Animation {
	public GroupeNode parent;

	public MakeVisible(Node node, int key, GroupeNode parent) {
		super(node, key);
		this.parent = parent;

	}

	public void animate(Input input){
	//CUBE ERSCHEINT
	if (input.isKeyToggled(key)){
		parent.addChild(node);
	}
	}

}
