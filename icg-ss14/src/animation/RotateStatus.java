package animation;

import objects.Node;
import objects.Status;
import ogl.app.Input;

public class RotateStatus extends Rotate {

	
	public RotateStatus(Node node) {
		super(node);
	}

	//TODO Priorit???ten raus?
	@Override
	public void animate(Input input) {
//		
//		float angle = 0.7f;
//		float angleW = 0.8f;
//		float angleSW = 0.9f;
//		
//		if (node.getPrio() == Priority.WICHTIG && node.getStatus() == Status.UNBEARBEITET) {
//			animate(axisY, angleW);
//		}
//		
//		if (node.getPrio() == Priority.WICHTIG && node.getStatus() == Status.MARKIERT) {
//			animate(axisX, 3f);
//		}
//		
//		if (node.getPrio() == Priority.SEHRWICHTIG && node.getStatus() == Status.UNBEARBEITET) {
//			animate(axisY, angleSW);
//		}
//		
//		if (node.getPrio() == Priority.SEHRWICHTIG && node.getStatus() == Status.MARKIERT) {
//			animate(axisX, 3f);
//		}
//		
		
		//gibt an das sich die Objekte immer rotieren wenn sie markiert sind
		if (node.getStatus() == Status.MARKIERT || node.getStatus() == Status.ABMARKIERT){
			animate(axisX, 3f);
		}
		
		if (node.getStatus() == Status.ABMARKIERT || node.getStatus() == Status.ABGEARBEITET) {
			animate(axisZ, 0.5f);
		}
		
		
	}

}
