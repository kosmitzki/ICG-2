package Animation;


import ogl.app.Input;
import scenegraph.basics.Node;
import scenegraph.basics.Status;

public class ScaleStatus extends Scale {

	public ScaleStatus(Node node) {
		super(node);

	}
	boolean help = false;

	@Override
	public void animate(Input input) {

		float small = 0.5f;

		//scaliert die Objekte wenn M gedrückt wird (weil die da auf ABMARKIERT gesetzt werden)
		if (node.getStatus() == Status.ABMARKIERT || node.getStatus() == Status.ABGEARBEITET ){
			//get scaled gibt in der Node Klasse an, ob die Objekte schonmal skaliert wurden
			if (!(node.getScaled())){
				animate(small, small, small);
				node.setScaled();
			}
		} 
	}
}
