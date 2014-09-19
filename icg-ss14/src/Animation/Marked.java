package Animation;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.input.Keyboard;

import ogl.app.Input;
import scenegraph.basics.GroupNode;
import scenegraph.basics.Node;
import scenegraph.basics.Status;


public class Marked extends Animation {
	
	Map<Integer, Node> knotenliste = new HashMap<Integer, Node>();
	int aktive = 0;
	boolean schalter = false;

	public Marked(Node node) {//TODO vll nur ne Groupnode ��bergeben
		super(node);
	}
	
	public void setNode(Node node){
		new Marked(node);
	}
	
	public void enter(){	
		//TODO noch füllen 
		//wenn enter gedrückt wird, soll diese methode aufgerufen werden und die
		//nodes auf der aktuellen ebene als abgearbeitet markieren
		//und auf die nächst tiefere ebene gehen.
		//wenn es nicht mehr tiefer geht, zur Übersicht
	}
	
	//ruft marked fuer neue obenen (zB a1objekte2) auf
	public void setEbene(GroupNode ebene){
		new Marked(ebene);	
	}

	@Override
	public void animate(Input input) {
		//System.out.println(node.getName());

		for (int i = 0; i < node.getChildNode().size(); i++) {
			knotenliste.put(i, node.getChildNode().get(i));
		//	System.out.println(knotenliste.toString());
		}
		//TODO funktioniert noch nicht mit nach rechts und links switchen
		if (input.isKeyDown(Keyboard.KEY_RIGHT)) { // rechteres objekt auf der ebene auswaehlen
		if (schalter == true){
			if (aktive < (node.getChildNode().size()-1)){ //-1 wegen dem naechsten knotenpunkt
				aktive++;
				System.out.println(aktive);
			//	System.out.println(knotenliste.get(aktive).getStatus());


				if (knotenliste.get(aktive - 1).getStatus() == Status.MARKIERT) {
					knotenliste.get(aktive - 1).setStatus(Status.UNBEARBEITET);
					schalter = false;
				}
				if (knotenliste.get(aktive - 1).getStatus() == Status.ABMARKIERT) {
					knotenliste.get(aktive - 1).setStatus(Status.ABGEARBEITET);
					schalter = false;
				}  //2 ifs fuer die alten
				
				
				
				if (knotenliste.get(aktive).getStatus() == Status.ABGEARBEITET) {
					knotenliste.get(aktive).setStatus(Status.ABMARKIERT);
					schalter = false;
				}
				if (knotenliste.get(aktive).getStatus() == Status.UNBEARBEITET) {
					knotenliste.get(aktive).setStatus(Status.MARKIERT);
					schalter = false;
				}		schalter = false; // eigentliches markieren	
				}
			} else { schalter = true; }
		}
		if (input.isKeyDown(Keyboard.KEY_LEFT)) {  // linkes objekt auf der ebene auswaehlen
			if (aktive > 0){
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
		if (input.isKeyToggled(Keyboard.KEY_M)) {
			if (knotenliste.get(aktive).getStatus() == Status.MARKIERT) {
				knotenliste.get(aktive).setStatus(Status.ABMARKIERT);
			}
			if (knotenliste.get(aktive).getStatus() == Status.ABMARKIERT) {
				knotenliste.get(aktive).setStatus(Status.MARKIERT);
			}
		}
		
		
	}

}
