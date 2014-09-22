
package ogl.app; 

import static ogl.vecmathimp.FactoryDefault.vecmath;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glViewport;

import java.util.ArrayList;

import objects.Camera;
import objects.Cube;
import objects.CubePoly;
import objects.GroupNode;
import objects.Hexagon;
import objects.House;
import objects.Node;
import objects.Plane;
import objects.Priority;
import objects.Pyramide;
import objects.Triangle;
import ogl.vecmath.Color;
import ogl.vecmath.Matrix;
import ogl.vecmath.Vector;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import animation.Animation;
import animation.CamRotateChild;
import animation.Marked;
import animation.Move;
import animation.MoveCam;
import animation.Scale;
import animation.ScaleKey;
import shader.Shader;


public class Start implements App {


	// app.OpenGLApp
	static public void main(String[] args) {
		new OpenGLApp("Geometrische Formen", new Start()).start();
	}

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
	public Triangle triangle5;


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
	Scale scale;
	ScaleKey scaleKey;

	public static Marked markedKnotenpunkt;

	Node activeObject;
	Node activeEbene;
	public int count1 = 0;
	public int count2 = 0;
	public int count3 = 0;
	public int count4 = 0;
	public int count5 = 0;
	public int count6 = 0;
	

	Vector axis = vecmath.vector(0, 1, 0);

	// Initialize the rotation angle of the cube.
	private float angle = 0;

	//um den unterschiedlichen Planes verschiedene Farben zu geben
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
	
	private Color col(float r, float g, float b) {
		return vecmath.color(r, g, b);
	}

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


	public void start(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void init() {
		// Hintergrundfarbe
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		//depth testing.
		glEnable(GL11.GL_DEPTH_TEST);

		// erzeugt einen neuen Shader
		defaultshader = new Shader();

		root = new GroupNode();
		aufgabe1= new GroupNode();
		aufgabe2= new GroupNode();

		a1knoten1 = new GroupNode();
		a1knoten2 = new GroupNode();
		a1knoten3 = new GroupNode();
		a1knoten4 = new GroupNode();

		a2knoten1 = new GroupNode();
		a2knoten2 = new GroupNode();

		a1objekte1= new GroupNode();
		a1objekte2= new GroupNode();
		a1objekte3= new GroupNode();

		a2objekte1= new GroupNode();
		a2objekte2= new GroupNode();

		camera = new Camera(camera);
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

		triangle1 = new Triangle();  
		triangle1.init(defaultshader); 
		triangle1.setTransformation(vecmath.translationMatrix(-0.6f, 0.3f, -6f).mult(vecmath.rotationMatrix(vecmath.yAxis(), 90)).mult(vecmath.rotationMatrix(vecmath.xAxis(), -90)));

		triangle2 = new Triangle();
		triangle2.init(defaultshader); 
		triangle2.setTransformation(vecmath.translationMatrix(0.6f, 0.3f, -6f).mult(vecmath.rotationMatrix(vecmath.yAxis(), 90)).mult(vecmath.rotationMatrix(vecmath.xAxis(), 90)));

		triangle3 = new Triangle(); 
		triangle3.init(defaultshader); 
		triangle3.setTransformation(vecmath.translationMatrix(2.99f, 0.5f, -12f).mult(vecmath.rotationMatrix(vecmath.yAxis(), 90)).mult(vecmath.rotationMatrix(vecmath.xAxis(), -45)));
		triangle3.setPrio(Priority.WICHTIG);

		triangle4 = new Triangle();  
		triangle4.init(defaultshader); 
		triangle4.setTransformation(vecmath.translationMatrix(3.01f, -0.5f, -12f).mult(vecmath.rotationMatrix(vecmath.yAxis(), 90)).mult(vecmath.rotationMatrix(vecmath.xAxis(), 135)));
		triangle4.setPrio(Priority.WICHTIG);
		
		hexagon1 = new Hexagon();
		hexagon1.init(defaultshader);
		hexagon1.setTransformation(vecmath.translationMatrix(0f, 0f, 0f));

		house1 = new House();
		house1.init(defaultshader);
		house1.setTransformation(vecmath.translationMatrix(6f, 0f, 0f));

		cube2 = new CubePoly();
		cube2.init(defaultshader); 
		cube2.setTransformation(vecmath.translationMatrix(6f, -0.3f, -6f));

		pyramide1 = new Pyramide();
		pyramide1.init(defaultshader);
		pyramide1.setTransformation(vecmath.translationMatrix(6f, 0.3f, -6f));


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

		//Am Anfang ist das Sechseck markiert
		markedKnotenpunkt = new Marked(a1objekte1, camera);
	}


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


		Matrix viewMatrix = camera.isCamera();


		// damit dreht sich der W�rfel, weil sich angle immer ver�ndert
		// The modeling transformation. Object space to world space.
		Matrix modelMatrix = vecmath.rotationMatrix(axis, angle);


		defaultshader.activate();
		defaultshader.setModelMatrixUniform(modelMatrix);
		defaultshader.setProjectionMatrixUniform(projectionMatrix);
		defaultshader.setViewMatrixUniform(viewMatrix);


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

		ArrayList<Animation> animationTempList = new ArrayList<Animation>();

		animationTempList.add(new Move(markedKnotenpunkt.getMarkedNode(), Keyboard.KEY_W));
		animationTempList.add(new Move(markedKnotenpunkt.getMarkedNode(), Keyboard.KEY_S));
		animationTempList.add(new Move(markedKnotenpunkt.getMarkedNode(), Keyboard.KEY_A));
		animationTempList.add(new Move(markedKnotenpunkt.getMarkedNode(), Keyboard.KEY_D));
		animationTempList.add(new Move(markedKnotenpunkt.getMarkedNode(), Keyboard.KEY_Q)); //vor
		animationTempList.add(new Move(markedKnotenpunkt.getMarkedNode(), Keyboard.KEY_E)); //zurück

		animationTempList.add(new ScaleKey(markedKnotenpunkt.getMarkedNode(), Keyboard.KEY_V));
		animationTempList.add(new ScaleKey(markedKnotenpunkt.getMarkedNode(), Keyboard.KEY_B));


		animationTempList.add(new MoveCam(camera, Keyboard.KEY_I));
		animationTempList.add(new MoveCam(camera, Keyboard.KEY_K));
		animationTempList.add(new MoveCam(camera, Keyboard.KEY_J));
		animationTempList.add(new MoveCam(camera, Keyboard.KEY_L));
		animationTempList.add(new MoveCam(camera, Keyboard.KEY_T)); //vor
		animationTempList.add(new MoveCam(camera, Keyboard.KEY_G)); //zurück

		animationTempList.add(new CamRotateChild(camera, Keyboard.KEY_Y, angle));
		animationTempList.add(new CamRotateChild(camera, Keyboard.KEY_H, angle));

		animationTempList.add(new CamRotateChild(camera, Keyboard.KEY_8, angle));
		animationTempList.add(new CamRotateChild(camera, Keyboard.KEY_9, angle));

		animationTempList.add(new CamRotateChild(camera, Keyboard.KEY_O, angle));
		animationTempList.add(new CamRotateChild(camera, Keyboard.KEY_U, angle));


		//überblick über alle planes
		if (input.isKeyDown(Keyboard.KEY_0)){
			camera.setTransformation(vecmath.translationMatrix(3f, 8f, 20f));
		}
		//start
		if (input.isKeyDown(Keyboard.KEY_1)){
			camera.setTransformation(vecmath.translationMatrix(0f, 0f, 2f));
			markedKnotenpunkt.setNode(a1objekte1);
		}	
		if (input.isKeyDown(Keyboard.KEY_UP)) {
			if (camera.getTransformation().equals(vecmath.translationMatrix(0f, 0f, -4f))) {
				camera.setTransformation(vecmath.translationMatrix(2.99f, 0f, -10f));
				markedKnotenpunkt.setNode(a1objekte3);
				input.remove(Keyboard.KEY_UP);
			} if (camera.getTransformation().equals(vecmath.translationMatrix(0f, 0f, 2f))) {
				camera.setTransformation(vecmath.translationMatrix(0f, 0f, -4f));
				markedKnotenpunkt.setNode(a1objekte2);
				input.remove(Keyboard.KEY_UP);
			} if (camera.getTransformation().equals(vecmath.translationMatrix(6f, 0f, -4f))) {
				camera.setTransformation(vecmath.translationMatrix(3.01f, 0f, -10f));
				markedKnotenpunkt.setNode(a1objekte3);
				input.remove(Keyboard.KEY_UP);
			} if (camera.getTransformation().equals(vecmath.translationMatrix(6f, 0f, 2f))) {
				camera.setTransformation(vecmath.translationMatrix(6f, 0f, -4f));
				markedKnotenpunkt.setNode(a2objekte2);
				input.remove(Keyboard.KEY_UP);
			}

		}
		if (input.isKeyDown(Keyboard.KEY_DOWN)) {
			if (camera.getTransformation().equals(vecmath.translationMatrix(0f, 0f, -4f))) {
				camera.setTransformation(vecmath.translationMatrix(0f, 0f, 2f));
				markedKnotenpunkt.setNode(a1objekte1);
				input.remove(Keyboard.KEY_DOWN);
			} if (camera.getTransformation().equals(vecmath.translationMatrix(2.99f, 0f, -10f))) {
				camera.setTransformation(vecmath.translationMatrix(0f, 0f, -4f));
				markedKnotenpunkt.setNode(a1objekte2);
				input.remove(Keyboard.KEY_DOWN);
			} if (camera.getTransformation().equals(vecmath.translationMatrix(6f, 0f, -4f))) {
				camera.setTransformation(vecmath.translationMatrix(6f, 0f, 2f));
				markedKnotenpunkt.setNode(a2objekte1);
				input.remove(Keyboard.KEY_DOWN);
			} if (camera.getTransformation().equals(vecmath.translationMatrix(3.01f, 0f, -10f))) {
				camera.setTransformation(vecmath.translationMatrix(6f, 0f, -4f));
				markedKnotenpunkt.setNode(a2objekte2);
				input.remove(Keyboard.KEY_DOWN);
			}

		}
		if (input.isKeyDown(Keyboard.KEY_RIGHT)) {
			if (camera.getTransformation().equals(vecmath.translationMatrix(0f, 0f, -4f))) {
				camera.setTransformation(vecmath.translationMatrix(6f, 0f, -4f));
				markedKnotenpunkt.setNode(a2objekte2);
				input.remove(Keyboard.KEY_RIGHT);
			} if (camera.getTransformation().equals(vecmath.translationMatrix(0f, 0f, 2f))) {
				camera.setTransformation(vecmath.translationMatrix(6f, 0f, 2f));
				markedKnotenpunkt.setNode(a2objekte1);
				input.remove(Keyboard.KEY_RIGHT);
			} 
			//
		}
		if (input.isKeyDown(Keyboard.KEY_LEFT)) {
			if (camera.getTransformation().equals(vecmath.translationMatrix(6f, 0f, -4f))) {
				camera.setTransformation(vecmath.translationMatrix(0f, 0f, -4f));
				markedKnotenpunkt.setNode(a1objekte2);
				input.remove(Keyboard.KEY_LEFT);
			} if (camera.getTransformation().equals(vecmath.translationMatrix(6f, 0f, 2f))) {
				camera.setTransformation(vecmath.translationMatrix(0f, 0f, 2f));
				markedKnotenpunkt.setNode(a1objekte1);
				input.remove(Keyboard.KEY_LEFT);
			} 

		}

		Animation.getList().addAll(animationTempList);

		for (Animation a : Animation.getList()) {
			a.animate(input);
		}
		Animation.getList().removeAll(animationTempList);

	}
}
