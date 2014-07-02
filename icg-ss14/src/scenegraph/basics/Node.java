package scenegraph.basics;

import java.util.ArrayList;
import java.util.List;

import ogl.app.MatrixUniform;
import ogl.vecmath.Matrix;
import static ogl.vecmathimp.FactoryDefault.vecmath;

public abstract class Node {
	
	List<Node> childNode = new ArrayList<Node>();
	Matrix transformation;
	String name;
	
	public Node() {
		setTransformation(vecmath.identityMatrix());
	}
	
	public abstract void display(Matrix m);

	public List<Node> deleteChild(int position) {
		childNode.remove(position);
		return childNode;
	}
	
	public List<Node> addChild (Node child) {
		childNode.add(child);
		return childNode;
	}
	

	public Matrix getTransformation() {
		return transformation;
	}

	public void setTransformation(Matrix transformation) {
		this.transformation = transformation;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	

}
