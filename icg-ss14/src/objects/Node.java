package objects;

import java.util.ArrayList;
import java.util.List;

import animation.RotateStatus;
import animation.ScaleStatus;
import ogl.vecmath.Color;
import ogl.vecmath.Matrix;
import static ogl.vecmathimp.FactoryDefault.vecmath;

public abstract class Node {
	
	public Status status = Status.UNBEARBEITET;
	public Priority prio = Priority.DEFAULT;
	public boolean scaled = false;
	
	
	// enthaellt eine liste von kindknoten
	List<Node> childNode = new ArrayList<Node>();
	Matrix transformation;
	String name;
	
	
	// wie bei RotatingCube -> und gleiches interface
	public Node() {
		setTransformation(vecmath.identityMatrix());
		//ruft RotateStatus und ScaleStatus f��r die Node auf
		new RotateStatus(this);
		new ScaleStatus(this);
	}
	
	public void setScaled() {
		scaled = true;
	}
	
	public boolean getScaled() {
		return scaled;
	}
	
	public abstract void display(Matrix m);

	
	public Priority getPrio() {
		return prio;
	}

	public void setPrio(Priority prio) {
		this.prio = prio;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getChildDone(){
		int zahl = 1;
		for (int i = 0; i < getChildNode().size()-1; i++) {
			if (getChildNode().get(i).getScaled()) {
				zahl++;
				System.out.println("Zahl "+zahl);
			}
		}
		return zahl;
	}

	
	// 2 methoden zum hinzufuegen und entfernen von kindknoten
	public List<Node> deleteChild(int position) {
		childNode.remove(position);
		return childNode;
	}
	public List<Node> addChild (Node child) {
		childNode.add(child);
		return childNode;
	}
	
//gibt die aktuelle Position
	public Matrix getTransformation() {
		return transformation;
	}
	
	public List<Node> getChildNode() {
		return childNode;
	}
	
	
	/**
	 * 
	 * @return aufmultiplizierte Inversen von Objekt zu Kamera
	 */
	public Matrix isCamera() {
		for (Node i : childNode) {
			Matrix camMatrix = i.isCamera();
			if (camMatrix != null){
				return getTransformation().invertFull().mult(camMatrix);
			}	
		}
		return null;
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
	
	public abstract Color[] getC();


	public abstract void setC(Color[] c);


}
