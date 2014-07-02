package ogl.circle;

import ogl.vecmath.Matrix;
import scenegraph.basics.Node;
import scenegraph.basics.Shader;

public class Circle extends Node {

	private Shader defaultshader;

	public void init(Shader defaultshader) {
		this.defaultshader = defaultshader;
	}
	
	
	@Override
	public void display(Matrix m) {
		// TODO Auto-generated method stub
		
	}

}
