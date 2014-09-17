package Animation;

import ogl.app.Input;
import scenegraph.basics.Node;
import scenegraph.basics.Status;

public class RotateStatus extends Rotate {

	
	public RotateStatus(Node node) {
		super(node);
	}

	@Override
	public void animate(Input input) {
		
		float angle = 0.7f;
		
		if (node.getStatus() == Status.MARKIERT){
			animate(axisX, angle );
		}
		
	}

}
