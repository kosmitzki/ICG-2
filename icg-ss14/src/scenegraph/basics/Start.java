/*******************************************************************************
 * Copyright (c) 2013 Henrik Tramberend, Marc Latoschik. SWAGer
 * All rights reserved.
 *******************************************************************************/

//TODO Hauptklasse aufr�umen!!

package scenegraph.basics; 

import static ogl.vecmathimp.FactoryDefault.vecmath;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glViewport;

import java.awt.event.KeyEvent;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Position;

import ogl.app.App;
import ogl.app.Input;
import ogl.app.MatrixUniform;
import ogl.app.OpenGLApp;
import ogl.app.Util;
import ogl.vecmath.Color;
import ogl.vecmath.Matrix;
import ogl.vecmath.Vector;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import camera.Camera;
import Animation.Animation;
import Animation.ChangeColor;
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

	public Node root;
	public Camera camera;
	public Cube cube1;
	public Triangle triangle1;
	public Sechseck sechseck1;
	public CubePoly cube2;
	public GroupNode parent;
	public Shader defaultshader;
	public Plane plane1;
	public int x;
	public int y;
	Scale scale;
	Input input;

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
		
		camera = new Camera();		
		camera.setTransformation(vecmath.translationMatrix(0f, 0f, 6f));

		plane1 = new Plane();
		plane1.init(defaultshader);
		plane1.setTransformation(vecmath.translationMatrix(0f, 0f, 3f));
		
		cube1 = new Cube();
		cube1.init(defaultshader);  //initialisiert mit o.g. shader

		cube2 = new CubePoly();
		cube2.init(defaultshader);  //initialisiert mit o.g. shader
		cube2.setTransformation(vecmath.translationMatrix((float)1.5, 0, 0)); 


		triangle1 = new Triangle();  //ist eigentlich pyramide
		triangle1.init(defaultshader);  //dito

		sechseck1 = new Sechseck();
		sechseck1.init(defaultshader);

		// ==translationVerschiebt   (-links +rechts, -runter +hoch, -vor +zurück)
		triangle1.setTransformation(vecmath.translationMatrix(0, (float) 0.5, 0)); 
		
		// verbindet die 2 objekte
		parent = new GroupNode();

		
		root.addChild(camera);
		root.addChild(parent);
		root.addChild(plane1);
		
		
		
		//  macht komischerweise gar nichts, vll schon und die camera geht mit	
		//	parent.setTransformation(vecmath.translationMatrix(-1, 1, 2));
		animationList.add(new MakeVisible(cube1, Keyboard.KEY_C, parent));
		animationList.add(new MakeVisible(triangle1, Keyboard.KEY_T, parent));
		animationList.add(new MakeVisible(cube2, Keyboard.KEY_2, parent));
		animationList.add(new MakeVisible(sechseck1, Keyboard.KEY_E, parent));
	
		
		animationList.add(new Scale(parent, Keyboard.KEY_B));
	//	animationList.add(new Scale(parent, Keyboard.KEY_S));
		animationList.add(new Scale(parent, Keyboard.KEY_N));
		

		
		animationList.add(new Rotate(parent, Keyboard.KEY_X, angle));
		animationList.add(new Rotate(parent, Keyboard.KEY_Y, angle));
		animationList.add(new Rotate(parent, Keyboard.KEY_Z, angle));
		
		animationList.add(new ChangeColor(cube1, Keyboard.KEY_P));


	}

	
	Vector axis = vecmath.vector(0, 1, 0);
	
	// TODO in die cube bringen, (ist schon aber nicht implementiert scheinbar)
		// Initialize the rotation angle of the cube.
		private float angle = 00;


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
				

		// TODO damit dreht sich der W�rfel, weil sich angle immer ver�ndert
		// setzt neue Transformationsmatrix je nachdem ob triangle steht, wird
		// triangle aufgerufen und bei cube cube
		// The modeling transformation. Object space to world space.
		Matrix modelMatrix = vecmath.rotationMatrix(axis, angle);

		//TODO
		Matrix cameraMatrix = vecmath.lookatMatrix(camera.getForward(), camera.getPos(), camera.getUp());

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
		
		if (input.isKeyDown(Keyboard.KEY_1)){
			animationTempList.add(new Move(parent, Keyboard.KEY_UP));
			animationTempList.add(new Move(parent, Keyboard.KEY_DOWN));
			animationTempList.add(new Move(parent, Keyboard.KEY_LEFT));
			animationTempList.add(new Move(parent, Keyboard.KEY_RIGHT));
			animationTempList.add(new Move(parent, Keyboard.KEY_COMMA)); //vor
			animationTempList.add(new Move(parent, Keyboard.KEY_PERIOD)); //zurück
		} else if (input.isKeyDown(Keyboard.KEY_3)){
		animationTempList.add(new Move(camera, Keyboard.KEY_UP));
		animationTempList.add(new Move(camera, Keyboard.KEY_DOWN));
		animationTempList.add(new Move(camera, Keyboard.KEY_LEFT));
		animationTempList.add(new Move(camera, Keyboard.KEY_RIGHT));
		animationTempList.add(new Move(camera, Keyboard.KEY_COMMA)); //vor
		animationTempList.add(new Move(camera, Keyboard.KEY_PERIOD)); //zurück
		}
		
		
		//TODO Toggleproblem Loesen
		if (input.isKeyToggled(Keyboard.KEY_7)){
			camera.setTransformation(vecmath.translationMatrix(0f, 0f, 5f));
		}
		if (input.isKeyToggled(Keyboard.KEY_8)){
			camera.setTransformation(vecmath.translationMatrix(0f, 0f, 10f));
		}
		if (input.isKeyToggled(Keyboard.KEY_9)){
			camera.setTransformation(vecmath.translationMatrix(0f, 0f, 15f));}
		
		animationList.addAll(animationTempList);
		
		
		
		for (Animation a : animationList) {
			a.animate(input);
		}
		animationList.removeAll(animationTempList);
		
		
		if (input.isKeyDown(Keyboard.KEY_K)) {
			camera.getLeft();
		}
		
		
	}







}
