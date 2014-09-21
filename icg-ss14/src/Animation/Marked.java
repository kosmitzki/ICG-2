package Animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.input.Keyboard;

import camera.Camera;
import ogl.app.Input;
import scenegraph.basics.Node;
import scenegraph.basics.Start;
import scenegraph.basics.Status;


public class Marked extends Animation {

	Map<Integer, Node> knotenliste = new HashMap<Integer, Node>();
	int aktive = 0;
	boolean schalter = false;
	Scale scale;
	Camera camera;
	boolean help = false;
	public int count = 0;


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public Marked(Node node, Camera camera) {
		super(node);
		this.camera = camera;
	}

	public void setNode(Node node2){
		this.node = node2;

		for (int i = 0; i < node.getChildNode().size(); i++) {
			if (node.getChildNode().get(i).getStatus() == Status.MARKIERT) {
				node.getChildNode().get(i).setStatus(Status.UNBEARBEITET);
			}
			if (node.getChildNode().get(i).getStatus() == Status.ABMARKIERT) {
				node.getChildNode().get(i).setStatus(Status.ABGEARBEITET);
			} 
		}
	}

	public Node getMarkedNode() {
		return knotenliste.get(aktive);
	}


	@Override
	public void animate(Input input) {
		//System.out.println(node.getName());

		for (int i = 0; i < node.getChildNode().size()-1; i++) {
			knotenliste.put(i, node.getChildNode().get(i));
		}
		knotenliste.get(aktive).setStatus(Status.MARKIERT);

		if (input.isKeyDown(Keyboard.KEY_4)) { // rechteres objekt auf der ebene auswaehlen
			input.remove(Keyboard.KEY_4);
			if (aktive < (node.getChildNode().size()-2)){ 
				aktive++;
				System.out.println(aktive);

				if (knotenliste.get(aktive - 1).getStatus() == Status.MARKIERT) {
					knotenliste.get(aktive - 1).setStatus(Status.UNBEARBEITET);
				}
				if (knotenliste.get(aktive - 1).getStatus() == Status.ABMARKIERT) {
					knotenliste.get(aktive - 1).setStatus(Status.ABGEARBEITET);
				}  



				if (knotenliste.get(aktive).getStatus() == Status.ABGEARBEITET) {
					knotenliste.get(aktive).setStatus(Status.ABMARKIERT);
				}
				if (knotenliste.get(aktive).getStatus() == Status.UNBEARBEITET) {
					knotenliste.get(aktive).setStatus(Status.MARKIERT);
				}		
			}
		}

		if (input.isKeyDown(Keyboard.KEY_3)) {  // linkes objekt auf der ebene auswaehlen
			if (aktive > 0){
				input.remove(Keyboard.KEY_3);
				aktive--;
				System.out.println(aktive);

				if (knotenliste.get(aktive + 1).getStatus() == Status.MARKIERT) {
					knotenliste.get(aktive + 1).setStatus(Status.UNBEARBEITET);
				}
				if (knotenliste.get(aktive + 1).getStatus() == Status.ABMARKIERT) {
					knotenliste.get(aktive + 1).setStatus(Status.ABGEARBEITET);
				}


				if (knotenliste.get(aktive).getStatus() == Status.ABGEARBEITET) {
					knotenliste.get(aktive).setStatus(Status.ABMARKIERT);
				}
				if (knotenliste.get(aktive).getStatus() == Status.UNBEARBEITET) {
					knotenliste.get(aktive).setStatus(Status.MARKIERT);
				}
			} 
		}


		// TODO das letzte markierte Objekt vor dem Ebenen switch verschwindet immer
		if (input.isKeyDown(Keyboard.KEY_M)) {
			if (knotenliste.get(aktive).getStatus() == Status.MARKIERT) { 
				knotenliste.get(aktive).setStatus(Status.ABMARKIERT);
				input.remove(Keyboard.KEY_M);
				System.out.println(knotenliste.get(aktive).getStatus());
			}
			System.out.println("M");


			if (node.getChildNode().get(aktive).getStatus() == Status.ABGEARBEITET ||
					node.getChildNode().get(aktive).getStatus() == Status.ABMARKIERT) {
				System.out.println("if");
				count++;
				System.out.println("count = " + count);
				if (count == node.getChildNode().size()-1) {
					System.out.println("2. if");

					if (camera.getTransformation().equals(vecmath.translationMatrix(2.99f, 0f, -10f))) {
						camera.setTransformation(vecmath.translationMatrix(0f, 0f, -4f));
						setNode(Start.getA1objekte2());
					}
					else if (camera.getTransformation().equals(vecmath.translationMatrix(0f, 0f, -4f))) {
						camera.setTransformation(vecmath.translationMatrix(0f, 0f, 2f));
						setNode(Start.getA1objekte1());
					}
					else if (camera.getTransformation().equals(vecmath.translationMatrix(3.01f, 0f, -10f))) {
						camera.setTransformation(vecmath.translationMatrix(6f, 0f, -4f));
						setNode(Start.getA2objekte2());
					}
					else if (camera.getTransformation().equals(vecmath.translationMatrix(6f, 0f, -4f))) {
						camera.setTransformation(vecmath.translationMatrix(6f, 0f, 2f));
						setNode(Start.getA2objekte1());
					}
					count = 0;



				}

			}


		}
//		//TODO N setzt jetzt wieder auf normal aber in der ScaleKey, aber irgendwie ändert sich der Status nicht
//		if (input.isKeyDown(Keyboard.KEY_N)) {
//			System.out.println("n");
//			input.remove(Keyboard.KEY_N);
//			if (knotenliste.get(aktive).getStatus() == Status.ABMARKIERT) {
//				System.out.println("if");
//				knotenliste.get(aktive).setStatus(Status.MARKIERT);
//				input.remove(Keyboard.KEY_N);
//				System.out.println(knotenliste.get(aktive).getStatus());
//			}
//		}
	}
}

