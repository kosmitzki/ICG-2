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

	public Marked(Node node) {//TODO vll nur ne Groupnode ��bergeben
		super(node);
		
	}
	
	public void enter(){	
	}
	
	//ruft marked fuer neue obenen (zB a1objekte2) auf
	public void setEbene(GroupNode ebene){
		new Marked(ebene);	
	}

	@Override
	public void animate(Input input) {
		
		for (int i = 0; i < node.getChildNode().size(); i++) {
			knotenliste.put(i, node.getChildNode().get(i));
		}
		int aktive = 1;
		if (input.isKeyToggled(Keyboard.KEY_RIGHT)) {  // rechteres objekt auf der ebene auswaehlen
			if (aktive < (node.getChildNode().size()-1)){
				aktive++;
				if (knotenliste.get(aktive - 1).getStatus() == Status.MARKIERT) {
					knotenliste.get(aktive - 1).setStatus(Status.UNBEARBEITET);
				}
				if (knotenliste.get(aktive - 1).getStatus() == Status.ABMARKIERT) {
					knotenliste.get(aktive - 1).setStatus(Status.ABGEARBEITET);
				}
				knotenliste.get(aktive).setStatus(Status.MARKIERT);
			}
		}
		if (input.isKeyToggled(Keyboard.KEY_LEFT)) {  // linkes objekt auf der ebene auswaehlen
			if (aktive > 0){
				aktive--;
				if (knotenliste.get(aktive + 1).getStatus() == Status.MARKIERT) {
					knotenliste.get(aktive + 1).setStatus(Status.UNBEARBEITET);
				}
				if (knotenliste.get(aktive + 1).getStatus() == Status.ABMARKIERT) {
					knotenliste.get(aktive + 1).setStatus(Status.ABGEARBEITET);
				}
				knotenliste.get(aktive).setStatus(Status.MARKIERT);
			}
		}
		
		// gerade sehen wir noch nicht den unterschieden aber sollte funktioneieren
		if (input.isKeyDown(Keyboard.KEY_M)) {
			if (knotenliste.get(aktive).getStatus() == Status.MARKIERT) {
				knotenliste.get(aktive).setStatus(Status.ABMARKIERT);
			}
			if (knotenliste.get(aktive).getStatus() == Status.ABMARKIERT) {
				knotenliste.get(aktive).setStatus(Status.MARKIERT);
			}
		}
		
		
	}

}
