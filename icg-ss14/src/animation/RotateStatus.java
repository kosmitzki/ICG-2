package animation;

import objects.Node;
import objects.Priority;
import objects.Status;
import ogl.app.Input;

public class RotateStatus extends Rotate {

	
	public RotateStatus(Node node) {
		super(node);
	}

	@Override
	public void animate(Input input) {
		
		if (node.getPrio() == Priority.WICHTIG && node.getStatus() == Status.UNBEARBEITET) {
			animate(axisY, 0.8f);
		}
		
		//gibt an das sich die Objekte immer rotieren wenn sie markiert sind
		if (node.getStatus() == Status.MARKIERT || node.getStatus() == Status.ABMARKIERT){
			animate(axisX, 3f);
		}		
	}
}
