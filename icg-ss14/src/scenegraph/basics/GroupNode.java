package scenegraph.basics;

import ogl.vecmath.Color;
import ogl.vecmath.Matrix;

public class GroupNode extends Node {

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

//	@Override
//	public Matrix getlookatMatrix() {
//		return null;
//	}


	// Matritzen multiplizieren damit sich Haus dreht



}
