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


	public Marked(Node node, Camera camera) {
		super(node);
		this.camera = camera;
	}

	public void setNode(Node node2){
		this.node = node2;

		//immer wenn die plane gewechselt wird, sollen markierte Objekte sich abmarkieren
		for (int i = 0; i < node.getChildNode().size(); i++) {
			if (node.getChildNode().get(i).getStatus() == Status.MARKIERT) {
				node.getChildNode().get(i).setStatus(Status.UNBEARBEITET);
			}
			//und markierte und abgearbeitet Objekte werden abgearbeitet gesetzt
			if (node.getChildNode().get(i).getStatus() == Status.ABMARKIERT) {
				node.getChildNode().get(i).setStatus(Status.ABGEARBEITET);
			} 
		}
	}

	//gibt das derzeit markierte Objekt zurück
	public Node getMarkedNode() {
		return knotenliste.get(aktive);
	}


	@Override
	public void animate(Input input) {

		//setzt alle Kindknoten in die Knotenliste
		for (int i = 0; i < node.getChildNode().size()-1; i++) {
			knotenliste.put(i, node.getChildNode().get(i));
		}
		knotenliste.get(aktive).setStatus(Status.MARKIERT);

		if (input.isKeyDown(Keyboard.KEY_4)) { // rechteres objekt auf der ebene auswaehlen
			input.remove(Keyboard.KEY_4);
			//solange aktive kleiner ist als die Anzahl der Objekte (anhängende Knoten nicht mitgezählt
			if (aktive < (node.getChildNode().size()-2)){ 
				aktive++;

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

		//wenn M gedrückt ird soll das Objeket abgearbeitet und markiert werden
		if (input.isKeyDown(Keyboard.KEY_M)) {
			if (knotenliste.get(aktive).getStatus() == Status.MARKIERT) { 
				knotenliste.get(aktive).setStatus(Status.ABMARKIERT);
				input.remove(Keyboard.KEY_M);	
			}

			//getChildDone gibt in Node zurück wie viele Kinder auf einer Ebene markiert sind
			if (node.getChildDone() == node.getChildNode().size()-1) {

				//es wird immer entsprechend die Kameraposition gewechselt
				if (camera.getTransformation().equals(vecmath.translationMatrix(2.99f, 0f, -10f))) {
					camera.setTransformation(vecmath.translationMatrix(0f, 0f, -4f));
					//damit die Objekte in der vorherigen Ebene "umgesetzt" werden
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
			}
		}
	}
}

