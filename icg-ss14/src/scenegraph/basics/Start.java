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

import camera.Camera;
import Animation.Animation;
import Animation.ChangeColor;
import Animation.MakeVisible;
import Animation.Marked;
import Animation.Move;
import Animation.Rotate;
import Animation.RotateKey;
import Animation.RotateStatus;
import Animation.Scale;
import Animation.ScaleKey;
import Objects.Cube;
import Objects.CubePoly;
import Objects.Plane;
import Objects.Sechseck;
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

	public GroupNode a1knoten1;
	public GroupNode a1knoten2;
	public GroupNode a1knoten3;

	public GroupNode a1objekte1;
	public GroupNode a1objekte2;
	public GroupNode a1objekte3;

	public CubePoly testcube1;
	public CubePoly testcube2;
	public CubePoly testcube3;

	
	public Cube cube1;
	public Triangle triangle1;
	public Triangle triangle2;
	public Triangle triangle3;
	public Triangle triangle4;
	public Sechseck sechseck1;
	public CubePoly cube2;
	public GroupNode parent;
	public Shader defaultshader;
	public Plane plane1;
	public Plane plane2;
	public Plane plane3;
	public Plane plane4;
	public Plane plane5;
	public Plane plane6;
	public int x;
	public int y;
	public boolean help = false;
	Scale scale;
	ScaleKey scaleKey;
	//	Input input;
	Marked marked;
	
	Node activeObject;
	Node activeEbene;
	int count;
	
	
//	Map<Integer, Node> knotenliste = new HashMap<Integer, Node>();
//	int aktive = 0;
//	public Node nodeaktive = a1objekte1;



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


		a1knoten1 = new GroupNode();
		a1knoten1.setName("a1knoten1");
		a1knoten2 = new GroupNode();
		a1knoten2.setName("a1knoten2");
		a1knoten3 = new GroupNode();
		a1knoten3.setName("a1knoten3");

		a1objekte1= new GroupNode();
		a1objekte1.setName("a1objekte1");
		a1objekte2= new GroupNode();
		a1objekte2.setName("a1objekte2");
		a1objekte3= new GroupNode();
		a1objekte3.setName("a1objekte3");

		camera = new Camera(camera);
		camera.setName("camera");
		camera.setTransformation(vecmath.translationMatrix(0f, 0f, 2f));




		plane1 = new Plane();
		plane1.init(defaultshader);
		plane1.setTransformation(vecmath.translationMatrix(0f, 0f, -3f));
		plane1.setC(c1);

		testcube1 = new CubePoly();
		testcube1.setName("testCube1");
		testcube1.init(defaultshader);  //initialisiert mit o.g. shader
		testcube1.setTransformation(vecmath.translationMatrix(2f, -0f, 0f));
		testcube2 = new CubePoly();
		testcube2.setName("testCube2");
		testcube2.init(defaultshader);  //initialisiert mit o.g. shader
		testcube2.setTransformation(vecmath.translationMatrix(4f, -0f, 0f));
		testcube2.setStatus(Status.ABGEARBEITET);

		testcube3 = new CubePoly();
		testcube3.setName("testCube3");
		testcube3.init(defaultshader);  //initialisiert mit o.g. shader
		testcube3.setTransformation(vecmath.translationMatrix(6f, -1f, 0f));


		plane2 = new Plane();
		plane2.init(defaultshader);
		plane2.setTransformation(vecmath.translationMatrix(0f, 0f, -9f));
		plane2.setC(c2);

		plane3 = new Plane();
		plane3.init(defaultshader);
		plane3.setTransformation(vecmath.translationMatrix(0f, 0f, -15f));

		plane4 = new Plane();
		plane4.init(defaultshader);
		plane4.setTransformation(vecmath.translationMatrix(6f, 0f, -3f));
		plane4.setC(c1);

		plane5 = new Plane();
		plane5.init(defaultshader);
		plane5.setTransformation(vecmath.translationMatrix(6f, 0f, -9f));
		plane5.setC(c2);

		plane6 = new Plane();
		plane6.init(defaultshader);
		plane6.setTransformation(vecmath.translationMatrix(6f, 0f, -15f));

		cube1 = new Cube();
		cube1.init(defaultshader);  //initialisiert mit o.g. shader
		cube1.setTransformation(vecmath.translationMatrix(0f, -0.3f, -6f));

		cube2 = new CubePoly();
		cube2.init(defaultshader);  //initialisiert mit o.g. shader
		cube2.setTransformation(vecmath.translationMatrix((float)1.5, 0, 0)); 


		triangle1 = new Triangle();  //ist eigentlich pyramide
		triangle1.init(defaultshader);  //dito
		triangle1.setTransformation(vecmath.translationMatrix(-0.6f, 0.3f, -6f).mult(vecmath.rotationMatrix(vecmath.yAxis(), 90)).mult(vecmath.rotationMatrix(vecmath.xAxis(), -90)));

		triangle2 = new Triangle();  //ist eigentlich pyramide
		triangle2.init(defaultshader);  //dito
		triangle2.setTransformation(vecmath.translationMatrix(0.6f, 0.3f, -6f).mult(vecmath.rotationMatrix(vecmath.yAxis(), 90)).mult(vecmath.rotationMatrix(vecmath.xAxis(), 90)));

		triangle3 = new Triangle();  //ist eigentlich pyramide
		triangle3.init(defaultshader);  //dito
		triangle3.setTransformation(vecmath.translationMatrix(-0.1f, 0f, -12f).mult(vecmath.rotationMatrix(vecmath.yAxis(), 90)).mult(vecmath.rotationMatrix(vecmath.xAxis(), -45)));

		triangle4 = new Triangle();  //ist eigentlich pyramide
		triangle4.init(defaultshader);  //dito
		triangle4.setTransformation(vecmath.translationMatrix(0.1f, 0f, -12f).mult(vecmath.rotationMatrix(vecmath.yAxis(), 90)).mult(vecmath.rotationMatrix(vecmath.xAxis(), 135)));
		triangle4.setPrio(Priority.WICHTIG);

		sechseck1 = new Sechseck();
		sechseck1.init(defaultshader);
		sechseck1.setTransformation(vecmath.translationMatrix(6f, 0f, -3));
		sechseck1.setStatus(Status.MARKIERT);



		// ==translationVerschiebt   (-links +rechts, -runter +hoch, -vor +zurück)
		//triangle1.setTransformation(vecmath.translationMatrix(0, (float) 0.5, 0)); 

		// verbindet die 2 objekte
		//parent = new GroupNode();


		root.addChild(camera);

		root.addChild(aufgabe1);
		aufgabe1.addChild(a1knoten1);

		a1knoten1.addChild(plane1);
		a1knoten1.addChild(a1objekte1);
		a1objekte1.addChild(testcube1);
		a1objekte1.addChild(testcube2);
		a1objekte1.addChild(testcube3);



		a1objekte1.addChild(a1knoten2);
		a1knoten2.addChild(plane2);
		a1knoten2.addChild(a1objekte2);
		a1objekte2.addChild(cube1);
		a1objekte2.addChild(triangle1);
		a1objekte2.addChild(triangle2);

		a1objekte2.addChild(a1knoten3);
		a1knoten3.addChild(plane3);
		a1knoten3.addChild(a1objekte3);

		a1objekte3.addChild(triangle3);
		a1objekte3.addChild(triangle4);

		
		
		root.addChild(plane4);		a1objekte3.addChild(sechseck1);

		root.addChild(plane5);
		root.addChild(plane6);

		
		
		

		//	me = new MouseEvent(parent);
		//		me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//		me.setVisible(true);
		//		




		//  macht komischerweise gar nichts, vll schon und die camera geht mit	
		//	parent.setTransformation(vecmath.translationMatrix(-1, 1, 2));
		//		animationList.add(new MakeVisible(cube1, Keyboard.KEY_C, parent));
		//		animationList.add(new MakeVisible(triangle1, Keyboard.KEY_T, parent));
		//		animationList.add(new MakeVisible(cube2, Keyboard.KEY_2, parent));
		//		animationList.add(new MakeVisible(sechseck1, Keyboard.KEY_E, parent));


		//TODO funktioniert nicht
		//Animation.getList().add(new ChangeColor(cube1, Keyboard.KEY_P));

		//TODO soll nicht immer hardgecoded sein
		marked = new Marked(a1objekte1);



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
		if (input.isKeyDown(Keyboard.KEY_C)){
			animationTempList.add(new Move(cube1, Keyboard.KEY_UP));
			animationTempList.add(new Move(cube1, Keyboard.KEY_DOWN));
			animationTempList.add(new Move(cube1, Keyboard.KEY_LEFT));
			animationTempList.add(new Move(cube1, Keyboard.KEY_RIGHT));
			animationTempList.add(new Move(cube1, Keyboard.KEY_COMMA)); //vor
			animationTempList.add(new Move(cube1, Keyboard.KEY_PERIOD)); //zurück

			animationTempList.add(new RotateKey(cube1, Keyboard.KEY_X, angle));
			animationTempList.add(new RotateKey(cube1, Keyboard.KEY_Y, angle));
			animationTempList.add(new RotateKey(cube1, Keyboard.KEY_Z, angle));

			animationTempList.add(new ScaleKey(cube1, Keyboard.KEY_B));
			animationTempList.add(new ScaleKey(cube1, Keyboard.KEY_S));
			animationTempList.add(new ScaleKey(cube1, Keyboard.KEY_N));

		}
		if (input.isKeyDown(Keyboard.KEY_T) && input.isKeyDown(Keyboard.KEY_9)){
			animationTempList.add(new Move(triangle1, Keyboard.KEY_UP));
			animationTempList.add(new Move(triangle1, Keyboard.KEY_DOWN));
			animationTempList.add(new Move(triangle1, Keyboard.KEY_LEFT));
			animationTempList.add(new Move(triangle1, Keyboard.KEY_RIGHT));
			animationTempList.add(new Move(triangle1, Keyboard.KEY_COMMA)); //vor
			animationTempList.add(new Move(triangle1, Keyboard.KEY_PERIOD)); //zurück

			animationTempList.add(new RotateKey(triangle1, Keyboard.KEY_X, angle));
			animationTempList.add(new RotateKey(triangle1, Keyboard.KEY_Y, angle));
			animationTempList.add(new RotateKey(triangle1, Keyboard.KEY_Z, angle));
			
			animationTempList.add(new ScaleKey(triangle1, Keyboard.KEY_B));
			animationTempList.add(new ScaleKey(triangle1, Keyboard.KEY_S));
			animationTempList.add(new ScaleKey(triangle1, Keyboard.KEY_N));

		}

		if (input.isKeyDown(Keyboard.KEY_T) && input.isKeyDown(Keyboard.KEY_8)){
			animationTempList.add(new Move(triangle2, Keyboard.KEY_UP));
			animationTempList.add(new Move(triangle2, Keyboard.KEY_DOWN));
			animationTempList.add(new Move(triangle2, Keyboard.KEY_LEFT));
			animationTempList.add(new Move(triangle2, Keyboard.KEY_RIGHT));
			animationTempList.add(new Move(triangle2, Keyboard.KEY_COMMA)); //vor
			animationTempList.add(new Move(triangle2, Keyboard.KEY_PERIOD)); //zurück

			animationTempList.add(new RotateKey(triangle2, Keyboard.KEY_X, angle));
			animationTempList.add(new RotateKey(triangle2, Keyboard.KEY_Y, angle));
			animationTempList.add(new RotateKey(triangle2, Keyboard.KEY_Z, angle));
			
			animationTempList.add(new ScaleKey(triangle2, Keyboard.KEY_B));
			animationTempList.add(new ScaleKey(triangle2, Keyboard.KEY_S));
			animationTempList.add(new ScaleKey(triangle2, Keyboard.KEY_N));

		}

		if (input.isKeyDown(Keyboard.KEY_T) && input.isKeyDown(Keyboard.KEY_7)){
			animationTempList.add(new Move(triangle3, Keyboard.KEY_UP));
			animationTempList.add(new Move(triangle3, Keyboard.KEY_DOWN));
			animationTempList.add(new Move(triangle3, Keyboard.KEY_LEFT));
			animationTempList.add(new Move(triangle3, Keyboard.KEY_RIGHT));
			animationTempList.add(new Move(triangle3, Keyboard.KEY_COMMA)); //vor
			animationTempList.add(new Move(triangle3, Keyboard.KEY_PERIOD)); //zurück

			animationTempList.add(new RotateKey(triangle3, Keyboard.KEY_X, angle));
			animationTempList.add(new RotateKey(triangle3, Keyboard.KEY_Y, angle));
			animationTempList.add(new RotateKey(triangle3, Keyboard.KEY_Z, angle));
			
			animationTempList.add(new ScaleKey(triangle3, Keyboard.KEY_B));
			animationTempList.add(new ScaleKey(triangle3, Keyboard.KEY_S));
			animationTempList.add(new ScaleKey(triangle3, Keyboard.KEY_N));

		}

		if (input.isKeyDown(Keyboard.KEY_T) && input.isKeyDown(Keyboard.KEY_6)){
			animationTempList.add(new Move(triangle4, Keyboard.KEY_UP));
			animationTempList.add(new Move(triangle4, Keyboard.KEY_DOWN));
			animationTempList.add(new Move(triangle4, Keyboard.KEY_LEFT));
			animationTempList.add(new Move(triangle4, Keyboard.KEY_RIGHT));
			animationTempList.add(new Move(triangle4, Keyboard.KEY_COMMA)); //vor
			animationTempList.add(new Move(triangle4, Keyboard.KEY_PERIOD)); //zurück

			animationTempList.add(new RotateKey(triangle4, Keyboard.KEY_X, angle));
			animationTempList.add(new RotateKey(triangle4, Keyboard.KEY_Y, angle));
			animationTempList.add(new RotateKey(triangle4, Keyboard.KEY_Z, angle));

			animationTempList.add(new ScaleKey(triangle4, Keyboard.KEY_B));
			animationTempList.add(new ScaleKey(triangle4, Keyboard.KEY_S));
			animationTempList.add(new ScaleKey(triangle4, Keyboard.KEY_N));

		}

		if (input.isKeyDown(Keyboard.KEY_H)){ //"H" wie hexagon
			animationTempList.add(new Move(sechseck1, Keyboard.KEY_UP));
			animationTempList.add(new Move(sechseck1, Keyboard.KEY_DOWN));
			animationTempList.add(new Move(sechseck1, Keyboard.KEY_LEFT));
			animationTempList.add(new Move(sechseck1, Keyboard.KEY_RIGHT));
			animationTempList.add(new Move(sechseck1, Keyboard.KEY_COMMA)); //vor
			animationTempList.add(new Move(sechseck1, Keyboard.KEY_PERIOD)); //zurück

			animationTempList.add(new RotateKey(sechseck1, Keyboard.KEY_X, angle));
			animationTempList.add(new RotateKey(sechseck1, Keyboard.KEY_Y, angle));
			animationTempList.add(new RotateKey(sechseck1, Keyboard.KEY_Z, angle));

			animationTempList.add(new ScaleKey(sechseck1, Keyboard.KEY_B));
			animationTempList.add(new ScaleKey(sechseck1, Keyboard.KEY_S));
			animationTempList.add(new ScaleKey(sechseck1, Keyboard.KEY_N));

		}

		if (input.isKeyDown(Keyboard.KEY_K)){
			animationTempList.add(new Move(camera, Keyboard.KEY_UP));
			animationTempList.add(new Move(camera, Keyboard.KEY_DOWN));
			animationTempList.add(new Move(camera, Keyboard.KEY_LEFT));
			animationTempList.add(new Move(camera, Keyboard.KEY_RIGHT));
			animationTempList.add(new Move(camera, Keyboard.KEY_COMMA)); //vor
			animationTempList.add(new Move(camera, Keyboard.KEY_PERIOD)); //zurück

			animationTempList.add(new RotateKey(camera, Keyboard.KEY_X, angle));
			animationTempList.add(new RotateKey(camera, Keyboard.KEY_Y, angle));
			animationTempList.add(new RotateKey(camera, Keyboard.KEY_Z, angle));
		}



		//überblick über alle planes
		if (input.isKeyDown(Keyboard.KEY_0)){
			camera.setTransformation(vecmath.translationMatrix(3f, 8f, 20f));
		}
		if (input.isKeyDown(Keyboard.KEY_1)){
			camera.setTransformation(vecmath.translationMatrix(0f, 0f, 2f));

		}
		if (input.isKeyDown(Keyboard.KEY_2)){
			camera.setTransformation(vecmath.translationMatrix(0f, 0f, -4f));

		}
		if (input.isKeyDown(Keyboard.KEY_3)){
			camera.setTransformation(vecmath.translationMatrix(0f, 0f, -10f));

		}
		if (input.isKeyDown(Keyboard.KEY_4)){
			camera.setTransformation(vecmath.translationMatrix(6f, 0f, 2f));
		}
		if (input.isKeyDown(Keyboard.KEY_5)){
			camera.setTransformation(vecmath.translationMatrix(6f, 0f, -4f));
		}
		if (input.isKeyDown(Keyboard.KEY_6)){
			camera.setTransformation(vecmath.translationMatrix(6f, 0f, -10f));
		}


		Animation.getList().addAll(animationTempList);

		//TODO ich stecke fest aber bin na dran, man muss die position der camera nehmen und diese an der z koordinate um 6 verändern, sodass man wieder auf der höheren plane ist
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
