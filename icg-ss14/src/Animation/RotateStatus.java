package Animation;

import ogl.app.Input;
import scenegraph.basics.Node;
import scenegraph.basics.Priority;
import scenegraph.basics.Status;

public class RotateStatus extends Rotate {

	
	public RotateStatus(Node node) {
		super(node);
	}

	@Override
	public void animate(Input input) {
		
		float angle = 0.7f;
		float angleW = 0.8f;
		float angleSW = 0.9f;
		
		if (node.getPrio() == Priority.WICHTIG) {
			animate(axisY, angleW );
		}
		
		if (node.getPrio() == Priority.SEHRWICHTIG) {
			animate(axisY, angleSW );
		}
		
		if (node.getStatus() == Status.MARKIERT || node.getStatus() == Status.ABMARKIERT){
			animate(axisX, angle );
		}
		
		
		
		
	}

}
