package scenegraph.basics;

import ogl.vecmath.Matrix;

public class GroupeNode extends Node {

	@Override
	public void display(Matrix m) {
		for (int i = 0; i < childNode.size(); i++) {
			childNode.get(i).display(m.mult(getTransformation()));
			
		}

	}


	// Matritzen multiplizieren damit sich Haus dreht



}
