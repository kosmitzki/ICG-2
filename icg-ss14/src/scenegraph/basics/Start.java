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
import Animation.Checked;
import Animation.MakeVisible;
import Animation.Move;
import Animation.Rotate;
import Animation.Scale;
import Objects.Cube;
import Objects.CubePoly;
import Objects.Plane;
import Objects.Sechseck;
import Objects.Triangle;


// A simple but complete OpenGL 2.0 ES application.
public class Start implements App {


	// app.OpenGLApp
	static public void main(String[] args) {
		new OpenGLApp("ToDo Liste", new Start()).start();
	}

	public MouseEvent me;
	public GroupNode root;
	public Camera camera;
	public Cube cube1;
	public Triangle triangle1;
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
	Scale scale;
	Input input;
	Checked checked;
	private Color[] c1 = { 
			vecmath.color(1, 1, 0), 
			vecmath.color(1, 1, 0), 
			vecmath.color(1, 1, 0), 
			vecmath.color(1, 1, 0), 
	};
	private Color[] c2 = { 
			vecmath.color(1, 0, 1), 
			vecmath.color(1, 0, 1),
			vecmath.color(1, 0, 1),
			vecmath.color(1, 0, 1),
	};

	ArrayList<Animation> animationList = new ArrayList<Animation>();



	// setter
	public void start(int x, int y) {
		this.x = x;
		this.y = y;
	}


	@Override
	public void init() {
		// Set background color to black.
		glClearColor(0.0f, 0.0f, 1.0f, 0.0f);

		// Enable depth testing.
		glEnable(GL11.GL_DEPTH_TEST);

		// ruft den shader auf
		defaultshader = new Shader();

		root = new GroupNode();

		camera = new Camera(camera);		
		camera.setTransformation(vecmath.translationMatrix(0f, 0f, 2f));




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
		cube1.setTransformation(vecmath.translationMatrix(0f, 0f, 0f));

		cube2 = new CubePoly();
		cube2.init(defaultshader);  //initialisiert mit o.g. shader
		cube2.setTransformation(vecmath.translationMatrix((float)1.5, 0, 0)); 


		triangle1 = new Triangle();  //ist eigentlich pyramide
		triangle1.init(defaultshader);  //dito
		triangle1.setTransformation(vecmath.translationMatrix(0f, 0f, -6f));

		sechseck1 = new Sechseck();
		sechseck1.init(defaultshader);
		sechseck1.setTransformation(vecmath.translationMatrix(0f, 0f, -12f));

		// ==translationVerschiebt   (-links +rechts, -runter +hoch, -vor +zurück)
		//triangle1.setTransformation(vecmath.translationMatrix(0, (float) 0.5, 0)); 

		// verbindet die 2 objekte
		//parent = new GroupNode();


		root.addChild(camera);
		//root.addChild(parent);
		root.addChild(cube1);
		root.addChild(triangle1);
		root.addChild(sechseck1);
		root.addChild(plane1);
		root.addChild(plane2);
		root.addChild(plane3);
		root.addChild(plane4);
		root.addChild(plane5);
		root.addChild(plane6);
		//		parent.addChild(cube1);
		//		parent.addChild(triangle1);
		//		parent.addChild(sechseck1);

		me = new MouseEvent(parent);
		//		me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//		me.setVisible(true);
		//		




		//  macht komischerweise gar nichts, vll schon und die camera geht mit	
		//	parent.setTransformation(vecmath.translationMatrix(-1, 1, 2));
		//		animationList.add(new MakeVisible(cube1, Keyboard.KEY_C, parent));
		//		animationList.add(new MakeVisible(triangle1, Keyboard.KEY_T, parent));
		//		animationList.add(new MakeVisible(cube2, Keyboard.KEY_2, parent));
		//		animationList.add(new MakeVisible(sechseck1, Keyboard.KEY_E, parent));

		


		//TODO funktioniert jetzt nicht mehr wg parent
		//(es werden alle kleiner und dadurch verschiebt sich das in den hinteren Ebenen)
//		animationList.add(new Checked(parent, Keyboard.KEY_F));

		animationList.add(new ChangeColor(cube1, Keyboard.KEY_P));


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






	@Override
	public void simulate(float elapsed, Input input) {
		//	Scale.animate(input);
		//		Animation.move(input);

		ArrayList<Animation> animationTempList = new ArrayList<Animation>();
		
		
		//TODO im Moment noch dirty aber jetzt lässt sich jedes objekt einzeln bewegen, bessere Lsg finden
		if (input.isKeyDown(Keyboard.KEY_C)){
			animationTempList.add(new Move(cube1, Keyboard.KEY_UP));
			animationTempList.add(new Move(cube1, Keyboard.KEY_DOWN));
			animationTempList.add(new Move(cube1, Keyboard.KEY_LEFT));
			animationTempList.add(new Move(cube1, Keyboard.KEY_RIGHT));
			animationTempList.add(new Move(cube1, Keyboard.KEY_COMMA)); //vor
			animationTempList.add(new Move(cube1, Keyboard.KEY_PERIOD)); //zurück
			
			animationTempList.add(new Rotate(cube1, Keyboard.KEY_X, angle));
			animationTempList.add(new Rotate(cube1, Keyboard.KEY_Y, angle));
			animationTempList.add(new Rotate(cube1, Keyboard.KEY_Z, angle));
			
			animationList.add(new Scale(cube1, Keyboard.KEY_B));
			animationList.add(new Scale(cube1, Keyboard.KEY_S));
			animationList.add(new Scale(cube1, Keyboard.KEY_N));
			
			animationList.add(new Checked(cube1, Keyboard.KEY_F));
		}
		if (input.isKeyDown(Keyboard.KEY_T)){
			animationTempList.add(new Move(triangle1, Keyboard.KEY_UP));
			animationTempList.add(new Move(triangle1, Keyboard.KEY_DOWN));
			animationTempList.add(new Move(triangle1, Keyboard.KEY_LEFT));
			animationTempList.add(new Move(triangle1, Keyboard.KEY_RIGHT));
			animationTempList.add(new Move(triangle1, Keyboard.KEY_COMMA)); //vor
			animationTempList.add(new Move(triangle1, Keyboard.KEY_PERIOD)); //zurück
			
			animationTempList.add(new Rotate(triangle1, Keyboard.KEY_X, angle));
			animationTempList.add(new Rotate(triangle1, Keyboard.KEY_Y, angle));
			animationTempList.add(new Rotate(triangle1, Keyboard.KEY_Z, angle));
			
			animationList.add(new Scale(triangle1, Keyboard.KEY_B));
			animationList.add(new Scale(triangle1, Keyboard.KEY_S));
			animationList.add(new Scale(triangle1, Keyboard.KEY_N));
			
			animationList.add(new Checked(triangle1, Keyboard.KEY_F));
		}
		if (input.isKeyDown(Keyboard.KEY_H)){ //"H" wie hexagon
			animationTempList.add(new Move(sechseck1, Keyboard.KEY_UP));
			animationTempList.add(new Move(sechseck1, Keyboard.KEY_DOWN));
			animationTempList.add(new Move(sechseck1, Keyboard.KEY_LEFT));
			animationTempList.add(new Move(sechseck1, Keyboard.KEY_RIGHT));
			animationTempList.add(new Move(sechseck1, Keyboard.KEY_COMMA)); //vor
			animationTempList.add(new Move(sechseck1, Keyboard.KEY_PERIOD)); //zurück
			
			animationTempList.add(new Rotate(sechseck1, Keyboard.KEY_X, angle));
			animationTempList.add(new Rotate(sechseck1, Keyboard.KEY_Y, angle));
			animationTempList.add(new Rotate(sechseck1, Keyboard.KEY_Z, angle));
			
			animationList.add(new Scale(sechseck1, Keyboard.KEY_B));
			animationList.add(new Scale(sechseck1, Keyboard.KEY_S));
			animationList.add(new Scale(sechseck1, Keyboard.KEY_N));
			
			animationList.add(new Checked(sechseck1, Keyboard.KEY_F));
		}

		if (input.isKeyDown(Keyboard.KEY_K)){
			animationTempList.add(new Move(camera, Keyboard.KEY_UP));
			animationTempList.add(new Move(camera, Keyboard.KEY_DOWN));
			animationTempList.add(new Move(camera, Keyboard.KEY_LEFT));
			animationTempList.add(new Move(camera, Keyboard.KEY_RIGHT));
			animationTempList.add(new Move(camera, Keyboard.KEY_COMMA)); //vor
			animationTempList.add(new Move(camera, Keyboard.KEY_PERIOD)); //zurück
			
			animationTempList.add(new Rotate(camera, Keyboard.KEY_X, angle));
			animationTempList.add(new Rotate(camera, Keyboard.KEY_Y, angle));
			animationTempList.add(new Rotate(camera, Keyboard.KEY_Z, angle));
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


		animationList.addAll(animationTempList);

		//TODO ich stecke fest aber bin na dran, man muss die position der camera nehmen und diese an der z koordinate um 6 verändern, sodass man wieder auf der höheren plane ist
		if (input.isKeyToggled(Keyboard.KEY_F)) {
			//float back = 1f;
//			
			for (float i = 0; i < 6f; i++) {
				camera.setTransformation(vecmath.translationMatrix(0,0,i));
//				Matrix help = vecmath.translationMatrix(0,0,i).mult(camera.getTransformation());
//				camera.setTransformation(help);
			}
			

		}

		for (Animation a : animationList) {
			a.animate(input);
		}
		animationList.removeAll(animationTempList);



	}







}
