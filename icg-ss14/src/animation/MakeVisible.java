package animation;

import objects.GroupNode;
import objects.Node;
import ogl.app.Input;

/*hatten wir gemacht, damit wir per Tastendruck (in der Start angegeben)
 * die einzelnen Objekte aufrufen k???nnen
 */

public class MakeVisible extends Animation {
	//parent nutzen wir jetzt nicht mehr
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
