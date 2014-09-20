/*******************************************************************************
 * Copyright (c) 2013 Henrik Tramberend, Marc Latoschik. SWAGer
 * All rights reserved.
 *******************************************************************************/

//TODO Hauptklasse aufr�umen!!

package scenegraph.basics; 

import static ogl.vecmathimp.FactoryDefault.vecmath;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glViewport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mouseEvent.MouseEvent;
import ogl.app.App;
import ogl.app.Input;
import ogl.app.OpenGLApp;
import ogl.vecmath.Color;
import ogl.vecmath.Matrix;
import ogl.vecmath.Vector;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import shader.Shader;
import camera.Camera;
import Animation.Animation;
import Animation.ChangeColor;
import Animation.MakeVisible;
import Animation.Marked;
import Animation.Move;
import Animation.MoveCam;
import Animation.Rotate;
import Animation.RotateKey;
import Animation.RotateStatus;
import Animation.Scale;
import Animation.ScaleKey;
import Objects.Cube;
import Objects.CubePoly;
import Objects.House;
import Objects.Plane;
import Objects.Hexagon;
import Objects.Pyramide;
import Objects.Triangle;


// A simple but complete OpenGL 2.0 ES application.
public class Start implements App {


	// app.OpenGLApp
	static public void main(String[] args) {
		new OpenGLApp("ToDo Liste", new Start()).start();
	}

	//	public MouseEvent me;
	public GroupNode root;
	public Camera camera;

	public GroupNode aufgabe1;
	public GroupNode aufgabe2;

	public GroupNode a1knoten1;
	public GroupNode a1knoten2;
	public GroupNode a1knoten3;
	public GroupNode a1knoten4;
	
	public GroupNode a2knoten1;
	public GroupNode a2knoten2;


	public static GroupNode a1objekte1;
	public static GroupNode a1objekte2;
	public static GroupNode a1objekte3;
	
	public static GroupNode a2objekte1;
	public static GroupNode a2objekte2;


	public Hexagon hexagon1;
	
	public Cube cube1;
	public Triangle triangle1;
	public Triangle triangle2;
	
	public Triangle triangle3;
	public Triangle triangle4;
	
	public House house1;
	public CubePoly cube2;
	public Pyramide pyramide1;
	public Shader defaultshader;
	public Plane plane1;
	public Plane plane2;
	public Plane plane3;
	public Plane plane4;
	public Plane plane5;
	public int x;
	public int y;
	public boolean help = false;
	Scale scale;
	ScaleKey scaleKey;
	//	Input input;
	public static Marked markedKnotenpunkt;

	Node activeObject;
	Node activeEbene;
	int count;
	
	public static void setMarked(Node node){
		markedKnotenpunkt.setNode(node);
	}
	
	


	//	Map<Integer, Node> knotenliste = new HashMap<Integer, Node>();
	//	int aktive = 0;
	//	public Node nodeaktive = a1objekte1;



	public static Node getA1objekte1() {
		return a1objekte1;
	}


	public static Node getA1objekte2() {
		return a1objekte2;
	}

	public static Node getA2objekte2() {
		return a2objekte2;
	}




	public static Node getA2objekte1() {
		return a2objekte1;
	}


	private Color col(float r, float g, float b) {
		return vecmath.color(r, g, b);
	}

	private Color[] c1 = { 
			col(1, 1, 0), 
			col(1, 1, 0), 
			col(1, 1, 0), 
			col(1, 1, 0), 
	};
	private Color[] c2 = { 
			col(1, 0, 1), 
			col(1, 0, 1),
			col(1, 0, 1),
			col(1, 0, 1),
	};
	private Color[] c = { 
			col(0, 0, 0), 
			col(0, 0, 0), 
			col(0, 0, 0), 
			col(0, 0, 0),
			col(0, 0, 0), 
			col(0, 0, 0), 
			col(0, 0, 0), 
			col(0, 0, 0)
	};

	// setter
	public void start(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void init() {
		// Set background color
		glClearColor(0.0f, 0.0f, 1.0f, 0.0f);

		// Enable depth testing.
		glEnable(GL11.GL_DEPTH_TEST);

		// ruft den shader auf
		defaultshader = new Shader();

		//		activeEbene = root.getChildNode().get(0).getChildNode().get(0);
		//
		//		activeObject = activeEbene.getChildNode().get(count);

		root = new GroupNode();
		aufgabe1= new GroupNode();
		aufgabe1.setName("aufgabe1");
		aufgabe2= new GroupNode();
		aufgabe2.setName("aufgabe2");

		a1knoten1 = new GroupNode();
		a1knoten1.setName("a1knoten1");
		a1knoten2 = new GroupNode();
		a1knoten2.setName("a1knoten2");
		a1knoten3 = new GroupNode();
		a1knoten3.setName("a1knoten3");
		a1knoten4 = new GroupNode();
		a1knoten4.setName("a1knoten4");
		
		a2knoten1 = new GroupNode();
		a2knoten1.setName("a2knoten1");
		a2knoten2 = new GroupNode();
		a2knoten2.setName("a2knoten2");

		a1objekte1= new GroupNode();
		a1objekte1.setName("a1objekte1");
		a1objekte2= new GroupNode();
		a1objekte2.setName("a1objekte2");
		a1objekte3= new GroupNode();
		a1objekte3.setName("a1objekte3");
		
		a2objekte1= new GroupNode();
		a2objekte1.setName("a2objekte1");
		a2objekte2= new GroupNode();
		a2objekte2.setName("a2objekte2");

		camera = new Camera(camera);
		camera.setName("camera");
		camera.setTransformation(vecmath.translationMatrix(0f, 0f, 5f));

		plane1 = new Plane();
		plane1.init(defaultshader);
		plane1.setTransformation(vecmath.translationMatrix(0f, 0f, -3f));
		plane1.setC(c1);

		plane2 = new Plane();
		plane2.init(defaultshader);
		plane2.setTransformation(vecmath.translationMatrix(0f, 0f, -9f));
		plane2.setC(c2);

		plane3 = new Plane();
		plane3.init(defaultshader);
		plane3.setTransformation(vecmath.translationMatrix(3f, 0f, -15f));

		plane4 = new Plane();
		plane4.init(defaultshader);
		plane4.setTransformation(vecmath.translationMatrix(6f, 0f, -3f));
		plane4.setC(c1);

		plane5 = new Plane();
		plane5.init(defaultshader);
		plane5.setTransformation(vecmath.translationMatrix(6f, 0f, -9f));
		plane5.setC(c2);

		cube1 = new Cube();
		cube1.init(defaultshader);  //initialisiert mit o.g. shader
		cube1.setTransformation(vecmath.translationMatrix(0f, -0.3f, -6f));

		triangle1 = new Triangle();  //ist eigentlich pyramide
		triangle1.init(defaultshader);  //dito
		triangle1.setTransformation(vecmath.translationMatrix(-0.6f, 0.3f, -6f).mult(vecmath.rotationMatrix(vecmath.yAxis(), 90)).mult(vecmath.rotationMatrix(vecmath.xAxis(), -90)));

		triangle2 = new Triangle();  //ist eigentlich pyramide
		triangle2.init(defaultshader);  //dito
		triangle2.setTransformation(vecmath.translationMatrix(0.6f, 0.3f, -6f).mult(vecmath.rotationMatrix(vecmath.yAxis(), 90)).mult(vecmath.rotationMatrix(vecmath.xAxis(), 90)));

		triangle3 = new Triangle();  //ist eigentlich pyramide
		triangle3.init(defaultshader);  //dito
		triangle3.setTransformation(vecmath.translationMatrix(2.9f, 0f, -12f).mult(vecmath.rotationMatrix(vecmath.yAxis(), 90)).mult(vecmath.rotationMatrix(vecmath.xAxis(), -45)));

		triangle4 = new Triangle();  //ist eigentlich pyramide
		triangle4.init(defaultshader);  //dito
		triangle4.setTransformation(vecmath.translationMatrix(3.1f, 0f, -12f).mult(vecmath.rotationMatrix(vecmath.yAxis(), 90)).mult(vecmath.rotationMatrix(vecmath.xAxis(), 135)));
//		triangle4.setPrio(Priority.WICHTIG);

		hexagon1 = new Hexagon();
		hexagon1.init(defaultshader);
		hexagon1.setTransformation(vecmath.translationMatrix(0f, 0f, 0f));
		
		house1 = new House();
		house1.init(defaultshader);
		house1.setTransformation(vecmath.translationMatrix(6f, 0f, 0f));

		cube2 = new CubePoly();
		cube2.init(defaultshader);  //initialisiert mit o.g. shader
		cube2.setTransformation(vecmath.translationMatrix(6f, -0.3f, -6f));
		
		pyramide1 = new Pyramide();
		pyramide1.init(defaultshader);
		pyramide1.setTransformation(vecmath.translationMatrix(6f, 0.3f, -6f));
		
		


		// ==translationVerschiebt   (-links +rechts, -runter +hoch, -vor +zurück)
		//triangle1.setTransformation(vecmath.translationMatrix(0, (float) 0.5, 0)); 

		// verbindet die 2 objekte
		//parent = new GroupNode();


		root.addChild(camera);

		root.addChild(aufgabe1);
		root.addChild(aufgabe2);
		aufgabe1.addChild(a1knoten1);
		aufgabe2.addChild(a2knoten1);

		a1knoten1.addChild(plane1);
		a1knoten1.addChild(a1objekte1);
		a1objekte1.addChild(hexagon1);
		a1objekte1.addChild(a1knoten2);

		a2knoten1.addChild(plane4);
		a2knoten1.addChild(a2objekte1);
		a2objekte1.addChild(house1);
		a2objekte1.addChild(a2knoten2);

		a1knoten2.addChild(plane2);
		a1knoten2.addChild(a1objekte2);
		a1objekte2.addChild(triangle1);
		a1objekte2.addChild(cube1);
		a1objekte2.addChild(triangle2);
		a1objekte2.addChild(a1knoten3);
		
		a2knoten2.addChild(plane5);
		a2knoten2.addChild(a2objekte2);
		a2objekte2.addChild(cube2);
		a2objekte2.addChild(pyramide1);
		a2objekte2.addChild(a1knoten3);
		
		a1knoten3.addChild(plane3);
		a1knoten3.addChild(a1objekte3);

		a1objekte3.addChild(triangle3);
		a1objekte3.addChild(triangle4);
		a1objekte3.addChild(a1knoten4);






		//	me = new MouseEvent(parent);
		//		me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//		me.setVisible(true);
		//		




		//  macht komischerweise gar nichts, vll schon und die camera geht mit	
		//	parent.setTransformation(vecmath.translationMatrix(-1, 1, 2));
		//		animationList.add(new MakeVisible(cube1, Keyboard.KEY_C, parent));
		//		animationList.add(new MakeVisible(triangle1, Keyboard.KEY_T, parent));
		//		animationList.add(new MakeVisible(cube2, Keyboard.KEY_2, parent));
		//		animationList.add(new MakeVisible(hexagon1, Keyboard.KEY_E, parent));


		//TODO funktioniert nicht
		//Animation.getList().add(new ChangeColor(cube1, Keyboard.KEY_P));

		//TODO soll nicht immer hardgecoded sein
		markedKnotenpunkt = new Marked(a1objekte1, camera);
		markedKnotenpunkt.setNode(a1objekte2);


	}


	Vector axis = vecmath.vector(0, 1, 0);

	// in die cube bringen, (ist schon aber nicht implementiert scheinbar)
	// Initialize the rotation angle of the cube.
	private float angle = 0;


	/*
	 * (non-Javadoc)
	 * 
	 * @see cg2.cube.App#display(int, int, javax.media.opengl.GL2ES2)
	 */
	@Override
	public void display(int width, int height) {
		// Adjust the the viewport to the actual window size. This makes the
		// rendered image fill the entire window.
		glViewport(0, 0, width, height);

		// Clear all buffers.
		glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		// Assemble the transformation matrix that will be applied to all
		// vertices in the vertex shader.
		float aspect = (float) width / (float) height;

		// The perspective projection. Camera space to NDC.
		Matrix projectionMatrix = vecmath.perspectiveMatrix(60f, aspect, 0.1f,
				100f);

		// The inverse camera transformation. World space to camera space.



		Matrix viewMatrix = camera.isCamera();


		// damit dreht sich der W�rfel, weil sich angle immer ver�ndert
		// setzt neue Transformationsmatrix je nachdem ob triangle steht, wird
		// triangle aufgerufen und bei cube cube
		// The modeling transformation. Object space to world space.
		Matrix modelMatrix = vecmath.rotationMatrix(axis, angle);


		//	Matrix modelMatrix2 = vecmath.translationMatrix(position_x, position_y, position_z);
		// parent.setTransformation(modelMatrix);

		defaultshader.activate();

		defaultshader.setModelMatrixUniform(modelMatrix);
		defaultshader.setProjectionMatrixUniform(projectionMatrix);
		defaultshader.setViewMatrixUniform(viewMatrix);
		//defaultshader.setProjectionMatrixUniform(cameraMatrix);


		root.display(modelMatrix);

	}

	// The attribute indices for the vertex data.
	public static int vertexAttribIdx = 0;
	public static int colorAttribIdx = 1;

	// Width, depth and height of the cube divided by 2.
	float w2 = 0.5f;
	float h2 = 0.5f;
	float d2 = 0.5f;




	public float timeElapsed = 0;
	public float diff = 0;

	@Override
	public void simulate(float elapsed, Input input) {
		//	Scale.animate(input);
		//		Animation.move(input);
		timeElapsed += elapsed;
		//	System.out.println(timeElapsed);

		ArrayList<Animation> animationTempList = new ArrayList<Animation>();





		//TODO im Moment noch dirty aber jetzt lässt sich jedes objekt einzeln bewegen, bessere Lsg finden
		
			animationTempList.add(new Move(markedKnotenpunkt.getMarkedNode(), Keyboard.KEY_W));
			animationTempList.add(new Move(markedKnotenpunkt.getMarkedNode(), Keyboard.KEY_S));
			animationTempList.add(new Move(markedKnotenpunkt.getMarkedNode(), Keyboard.KEY_A));
			animationTempList.add(new Move(markedKnotenpunkt.getMarkedNode(), Keyboard.KEY_D));
			animationTempList.add(new Move(markedKnotenpunkt.getMarkedNode(), Keyboard.KEY_Q)); //vor
			animationTempList.add(new Move(markedKnotenpunkt.getMarkedNode(), Keyboard.KEY_E)); //zurück

//			animationTempList.add(new RotateKey(markedKnotenpunkt.getMarkedNode(), Keyboard.KEY_X, angle));
//			animationTempList.add(new RotateKey(markedKnotenpunkt.getMarkedNode(), Keyboard.KEY_Y, angle));
//			animationTempList.add(new RotateKey(markedKnotenpunkt.getMarkedNode(), Keyboard.KEY_Z, angle));

			animationTempList.add(new ScaleKey(markedKnotenpunkt.getMarkedNode(), Keyboard.KEY_C));
			animationTempList.add(new ScaleKey(markedKnotenpunkt.getMarkedNode(), Keyboard.KEY_V));
			animationTempList.add(new ScaleKey(markedKnotenpunkt.getMarkedNode(), Keyboard.KEY_B));

		
	

			animationTempList.add(new MoveCam(camera, Keyboard.KEY_I));
			animationTempList.add(new MoveCam(camera, Keyboard.KEY_K));
			animationTempList.add(new MoveCam(camera, Keyboard.KEY_J));
			animationTempList.add(new MoveCam(camera, Keyboard.KEY_L));
			animationTempList.add(new MoveCam(camera, Keyboard.KEY_P)); //vor
			animationTempList.add(new MoveCam(camera, Keyboard.KEY_COLON)); //zurück

			animationTempList.add(new RotateKey(camera, Keyboard.KEY_Y, angle));
			animationTempList.add(new RotateKey(camera, Keyboard.KEY_H, angle));

			animationTempList.add(new RotateKey(camera, Keyboard.KEY_8, angle));
			animationTempList.add(new RotateKey(camera, Keyboard.KEY_9, angle));
		
			animationTempList.add(new RotateKey(camera, Keyboard.KEY_O, angle));
			animationTempList.add(new RotateKey(camera, Keyboard.KEY_O, angle));
		



		//überblick über alle planes
		if (input.isKeyDown(Keyboard.KEY_0)){
			camera.setTransformation(vecmath.translationMatrix(3f, 8f, 20f));
		}
		if (input.isKeyDown(Keyboard.KEY_1)){
			camera.setTransformation(vecmath.translationMatrix(0f, 0f, 2f));
			markedKnotenpunkt.setNode(a1objekte1);
		}
		if (input.isKeyDown(Keyboard.KEY_2)){
			camera.setTransformation(vecmath.translationMatrix(0f, 0f, -4f));
			markedKnotenpunkt.setNode(a1objekte2);

			
			//			if (count == a1objekte2.getChildNode().size()-2) {
			//				camera.setTransformation(vecmath.translationMatrix(0f, 0f, 2f));
			//				markedKnotenpunkt.setNode(a1objekte1);
			//			}

		}
		if (input.isKeyDown(Keyboard.KEY_3)){
			camera.setTransformation(vecmath.translationMatrix(3f, 0f, -10f));
			markedKnotenpunkt.setNode(a1objekte3);

		}
		if (input.isKeyDown(Keyboard.KEY_4)){
			camera.setTransformation(vecmath.translationMatrix(6f, 0f, 2f));
			markedKnotenpunkt.setNode(a2objekte1);
		}
		if (input.isKeyDown(Keyboard.KEY_5)){
			camera.setTransformation(vecmath.translationMatrix(6f, 0f, -4f));
			markedKnotenpunkt.setNode(a2objekte2);
		}

		
		

		Animation.getList().addAll(animationTempList);


		if (input.isKeyDown(Keyboard.KEY_RETURN)) {
			diff = timeElapsed;
			//if (diff == 1.0) {
			if (help == true) {
				if (camera.getTransformation().equals(vecmath.translationMatrix(0f, 0f, -10f))) {
					camera.setTransformation(vecmath.translationMatrix(0f, 0f, -4f));
					help = false;
				}
				else if (camera.getTransformation().equals(vecmath.translationMatrix(0f, 0f, -4f))) {
					camera.setTransformation(vecmath.translationMatrix(0f, 0f, 2f));
					help = false;
				}
				else if (camera.getTransformation().equals(vecmath.translationMatrix(6f, 0f, -10f))) {
					camera.setTransformation(vecmath.translationMatrix(6f, 0f, -4f));
					help = false;
				}
				else if (camera.getTransformation().equals(vecmath.translationMatrix(6f, 0f, -4f))) {
					camera.setTransformation(vecmath.translationMatrix(6f, 0f, 2f));
					help = false;
				}
			}else
				help = true;
		}



		for (Animation a : Animation.getList()) {
			a.animate(input);
		}
		Animation.getList().removeAll(animationTempList);

	}
}
