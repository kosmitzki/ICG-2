package scenegraph.basics;

import java.util.ArrayList;
import java.util.List;

import ogl.app.MatrixUniform;
import ogl.vecmath.Color;
import ogl.vecmath.Matrix;
import static ogl.vecmathimp.FactoryDefault.vecmath;

public abstract class Node {
	
	
	// enthaellt eine liste von kindknoten
	List<Node> childNode = new ArrayList<Node>();
	Matrix transformation;
	String name;
	
	
	// wie bei RotatingCube -> und gleiches interface
	public Node() {
		setTransformation(vecmath.identityMatrix());
	}
	public abstract void display(Matrix m);

	
	// 2 methoden zum hinzufuegen und entfernen von kindknoten
	public List<Node> deleteChild(int position) {
		childNode.remove(position);
		return childNode;
	}
	public List<Node> addChild (Node child) {
		childNode.add(child);
		return childNode;
	}
	
	// getter setter machen sinn
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
	
	public abstract Color[] getC();


	public abstract void setC(Color[] c);

	

}
