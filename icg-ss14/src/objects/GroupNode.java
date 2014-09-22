package objects;

import ogl.vecmath.Color;
import ogl.vecmath.Matrix;

public class GroupNode extends Node {


	/**
	 * Haengt die Kinder an den Gruppenknoten
	 * holt das Objekt an seinem Ort und multipliziert
	 * sie mit der uebergebenen Matrix
	 */
	@Override
	public void display(Matrix m) {
		for (int i = 0; i < childNode.size(); i++) {
			childNode.get(i).display(m.mult(getTransformation()));
		}
	}

	@Override
	public Color[] getC() {
		return null;
	}

	@Override
	public void setC(Color[] c) {
	}

}
