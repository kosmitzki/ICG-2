package scenegraph.basics;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.input.Keyboard;

import ogl.vecmath.Color;
import ogl.vecmath.Matrix;

public class GroupNode extends Node {

	@Override
	public void display(Matrix m) {
		for (int i = 0; i < childNode.size(); i++) {
			childNode.get(i).display(m.mult(getTransformation()));
//			if (childNode.get(i).getStatus() == Status.MARKIERT) {
//
//			}
		}

//		Map<Integer, Node> knotenliste = new HashMap<Integer, Node>();
//		for (int i = 0; i < childNode.size(); i++) {
//			knotenliste.put(i, childNode.get(i));
//		}
//
//		int aktive = 1; //
//		
//		if (key == Keyboard.KEY_UP) {
//			aktive++;
//			if (knotenliste.get(aktive - 1) == Status.MARKIERT) {
//				knotenliste.get(aktive - 1).setStatus(Status.UNBEARBEITET);
//			}
//			if (knotenliste.get(aktive - 1) == Status.ABMARKIERT) {
//				knotenliste.get(aktive - 1).setStatus(Status.ABGEARBEITET);
//			}
//			knotenliste.get(aktive).setStatus(Status.MARKIERT);
//		}
//		if (key == Keyboard.KEY_DOWN) {
//			aktive--;
//			if (knotenliste.get(aktive + 1) == Status.MARKIERT) {
//				knotenliste.get(aktive + 1).setStatus(Status.UNBEARBEITET);
//			}
//			if (knotenliste.get(aktive + 1) == Status.ABMARKIERT) {
//				knotenliste.get(aktive + 1).setStatus(Status.ABGEARBEITET);
//			}
//			knotenliste.get(aktive).setStatus(Status.MARKIERT);
//		}
//		
//		if (key == Keyboard.KEY_M) {
//			knotenliste.get(aktive).setStatus(Status.ABMARKIERT);
//		}
	}

	@Override
	public Color[] getC() {
		return null;
	}

	@Override
	public void setC(Color[] c) {

	}

	// @Override
	// public Matrix getlookatMatrix() {
	// return null;
	// }

}
