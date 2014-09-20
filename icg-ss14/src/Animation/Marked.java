package Animation;

import static ogl.vecmathimp.FactoryDefault.vecmath;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.input.Keyboard;

import camera.Camera;
import ogl.app.Input;
import scenegraph.basics.GroupNode;
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
	public int count2 = 1;

	public Marked(Node node, Camera camera) {//TODO vll nur ne Groupnode ��bergeben
		super(node);
		this.camera = camera;
	}

	public void setNode(Node node2){

		for (int i = 0; i < node.getChildNode().size(); i++) {
			if (node.getChildNode().get(i).getStatus() == Status.MARKIERT) {
				node.getChildNode().get(i).setStatus(Status.UNBEARBEITET);
			}
			if (node.getChildNode().get(i).getStatus() == Status.ABMARKIERT) {
				node.getChildNode().get(i).setStatus(Status.ABGEARBEITET);
			}  //2 ifs fuer die alten nodes damit da nichts ausgewaehlt bleiben kann
		}
		this.node = node2;


	}
	
	public Node getMarkedNode() {
		return knotenliste.get(aktive);
	}

	public void enter(){	
		//TODO noch füllen 
		//wenn enter gedrückt wird, soll diese methode aufgerufen werden und die
		//nodes auf der aktuellen ebene als abgearbeitet markieren
		//und auf die nächst tiefere ebene gehen.
		//wenn es nicht mehr tiefer geht, zur Übersicht
	}

	//ruft marked fuer neue obenen (zB a1objekte2) auf
	//	public void setEbene(GroupNode ebene){
	//		new Marked(ebene);	
	//	}


	@Override
	public void animate(Input input) {
		//System.out.println(node.getName());

		for (int i = 0; i < node.getChildNode().size(); i++) {
			knotenliste.put(i, node.getChildNode().get(i));
		}
		knotenliste.get(aktive).setStatus(Status.MARKIERT);

		if (input.isKeyDown(Keyboard.KEY_RIGHT)) { // rechteres objekt auf der ebene auswaehlen
			input.remove(Keyboard.KEY_RIGHT);
				if (aktive < (node.getChildNode().size()-2)){ //-1 wegen dem naechsten knotenpunkt
					aktive++;
					System.out.println(aktive);
					//	System.out.println(knotenliste.get(aktive).getStatus());


					if (knotenliste.get(aktive - 1).getStatus() == Status.MARKIERT) {
						knotenliste.get(aktive - 1).setStatus(Status.UNBEARBEITET);
					}
					if (knotenliste.get(aktive - 1).getStatus() == Status.ABMARKIERT) {
						knotenliste.get(aktive - 1).setStatus(Status.ABGEARBEITET);
					}  //2 ifs fuer die alten



					if (knotenliste.get(aktive).getStatus() == Status.ABGEARBEITET) {
						knotenliste.get(aktive).setStatus(Status.ABMARKIERT);
					}
					if (knotenliste.get(aktive).getStatus() == Status.UNBEARBEITET) {
						knotenliste.get(aktive).setStatus(Status.MARKIERT);
					}		
				}
			}

		if (input.isKeyDown(Keyboard.KEY_LEFT)) {  // linkes objekt auf der ebene auswaehlen
			if (aktive > 0){
				input.remove(Keyboard.KEY_LEFT);
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


		// gerade sehen wir noch nicht den unterschieden aber sollte funktioneieren
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
					if (help  == false) {
						if (camera.getTransformation().equals(vecmath.translationMatrix(3f, 0f, -10f))) {
							camera.setTransformation(vecmath.translationMatrix(0f, 0f, -4f));
							Start.setMarked(Start.getA1objekte2());
							help = false;
						}
						else if (camera.getTransformation().equals(vecmath.translationMatrix(0f, 0f, -4f))) {
							camera.setTransformation(vecmath.translationMatrix(0f, 0f, 2f));
							Start.setMarked(Start.getA1objekte1());
							help = false;
						}
						else if (camera.getTransformation().equals(vecmath.translationMatrix(3f, 0f, -10f))) {
							camera.setTransformation(vecmath.translationMatrix(6f, 0f, -4f));
							help = false;
						}
						else if (camera.getTransformation().equals(vecmath.translationMatrix(6f, 0f, -4f))) {
							camera.setTransformation(vecmath.translationMatrix(6f, 0f, 2f));
							help = false;
						}
						count = 0;
					}else
						help = true;
				}

				//				
			}

			//				if (node.getChildNode().get(count2).getStatus() == Status.ABGEARBEITET ||
			//						node.getChildNode().get(count2).getStatus() == Status.ABMARKIERT) {
			//					System.out.println("if.");
			//					count2++;
			//					System.out.println("count2 = " + count2);
			//					if (count == node.getChildNode().size()-2) {
			//						System.out.println("2. if.");
			//						if (help  == true) {
			//							if (camera.getTransformation().equals(vecmath.translationMatrix(0f, 0f, -10f))) {
			//								camera.setTransformation(vecmath.translationMatrix(0f, 0f, -4f));
			//								help = false;
			//							}
			//							else if (camera.getTransformation().equals(vecmath.translationMatrix(0f, 0f, -4f))) {
			//								camera.setTransformation(vecmath.translationMatrix(0f, 0f, 2f));
			//								help = false;
			//							}
			//							else if (camera.getTransformation().equals(vecmath.translationMatrix(6f, 0f, -10f))) {
			//								camera.setTransformation(vecmath.translationMatrix(6f, 0f, -4f));
			//								help = false;
			//							}
			//							else if (camera.getTransformation().equals(vecmath.translationMatrix(6f, 0f, -4f))) {
			//								camera.setTransformation(vecmath.translationMatrix(6f, 0f, 2f));
			//								help = false;
			//							}
			//						}else
			//							help = true;
			//					}
			//				
			//				//				
			//			}
		}
		if (input.isKeyDown(Keyboard.KEY_N)) {
			if (schalter == true){
				if (knotenliste.get(aktive).getStatus() == Status.ABMARKIERT) {
					knotenliste.get(aktive).setStatus(Status.MARKIERT);
					System.out.println(knotenliste.get(aktive).getStatus());
					schalter = false;
				}
			} else
				schalter = true;
		}





	}
}
