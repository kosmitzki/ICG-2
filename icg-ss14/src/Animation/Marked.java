package Animation;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.input.Keyboard;

import ogl.app.Input;
import scenegraph.basics.Node;
import scenegraph.basics.Status;


public class Marked extends Animation {
	
	Map<Integer, Node> knotenliste = new HashMap<Integer, Node>();

	public Marked(Node node) {//TODO vll nur ne Groupnode übergeben
		super(node);
		
	}
	
	

	@Override
	public void animate(Input input) {
		
		for (int i = 0; i < node.getChildNode().size(); i++) {
			knotenliste.put(i, node.getChildNode().get(i));
		}

		int aktive = 1; // weil mit planes nichts passieren soll, werden die jeweils übersprungen
		
		if (input.isKeyToggled(Keyboard.KEY_UP)) {
			aktive++;
			if (knotenliste.get(aktive - 1).getStatus() == Status.MARKIERT) {
				knotenliste.get(aktive - 1).setStatus(Status.UNBEARBEITET);
			}
			if (knotenliste.get(aktive - 1).getStatus() == Status.ABMARKIERT) {
				knotenliste.get(aktive - 1).setStatus(Status.ABGEARBEITET);
			}
			knotenliste.get(aktive).setStatus(Status.MARKIERT);
		}
		if (input.isKeyToggled(Keyboard.KEY_DOWN)) {
			aktive--;
			if (knotenliste.get(aktive + 1).getStatus() == Status.MARKIERT) {
				knotenliste.get(aktive + 1).setStatus(Status.UNBEARBEITET);
			}
			if (knotenliste.get(aktive + 1).getStatus() == Status.ABMARKIERT) {
				knotenliste.get(aktive + 1).setStatus(Status.ABGEARBEITET);
			}
			knotenliste.get(aktive).setStatus(Status.MARKIERT);
		}
		
		if (input.isKeyDown(Keyboard.KEY_M)) {
			knotenliste.get(aktive).setStatus(Status.ABMARKIERT);
		}

	}

}
