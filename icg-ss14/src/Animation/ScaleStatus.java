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
		float normal = 1.0f;


		if (node.getStatus() == Status.ABMARKIERT || node.getStatus() == Status.ABGEARBEITET ){
			if (!(node.getScaled())){
				animate(small, small, small);
				System.out.println("scaled");
				node.setScaled();
			}
		} 

//		if (node.getStatus() == Status.UNBEARBEITET || node.getStatus() == Status.MARKIERT ){
//			//			node.setTransformation(vecmath.scaleMatrix(normal, normal, normal));
//
//		animate(normal, normal, normal);
//		}



	}

}
