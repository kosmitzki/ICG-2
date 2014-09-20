package Objects;

import static ogl.vecmathimp.FactoryDefault.vecmath;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

import java.nio.FloatBuffer;

import ogl.vecmath.Color;
import ogl.vecmath.Matrix;
import ogl.vecmath.Vector;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import scenegraph.basics.Node;
import shader.Shader;

public class Plane extends Node {

	private Shader defaultshader;

	public void init(Shader defaultshader) {
		this.defaultshader = defaultshader;

		// Prepare the vertex data arrays.
		// Compile vertex data into a Java Buffer data structures that can be
		// passed to the OpenGL API efficently.
		positionData = BufferUtils.createFloatBuffer(vertices().length
				* vecmath.vectorSize());
		colorData = BufferUtils.createFloatBuffer(vertices().length
				* vecmath.colorSize());

		for (Vertex v : vertices()) {
			positionData.put(v.position.asArray());
			colorData.put(v.color.asArray());
		}
		positionData.rewind();
		colorData.rewind();
	}





	public void display(Matrix m) { 

		defaultshader.setModelMatrixUniform(m.mult(getTransformation()));
		

		// Enable the vertex data arrays (with indices 0 and 1). We use a vertex
		// position and a vertex color.
		glVertexAttribPointer(vertexAttribIdx, 3, false, 0, positionData);
		glEnableVertexAttribArray(vertexAttribIdx);
		glVertexAttribPointer(colorAttribIdx, 3, false, 0, colorData);
		glEnableVertexAttribArray(colorAttribIdx);

		// Draw the triangles that form the cube from the vertex data arrays.
		glDrawArrays(GL11.GL_TRIANGLES, 0, vertices().length);
	}



	// The attribute indices for the vertex data.bbb
	public static int vertexAttribIdx = 0;
	public static int colorAttribIdx = 1;

	// Width, depth and height of the cube divided by 2.
	public static float w2 = 2.5f;
	public static float h2 = 2.5f;
	public static float d2 = 2.5f;



	// Auxillary class to represent a single vertex.
	private class Vertex {
		Vector position;
		Color color;

		Vertex(Vector p, Color c) {
			position = p;
			color = c;
		}
	}

	// Make construction of vertices easy on the eyes.
	private Vertex v(Vector p, Color c) {
		return new Vertex(p, c);
	}

	// Make construction of vectors easy on the eyes.
	private Vector vec(float x, float y, float z) {
		return vecmath.vector(x, y, z);
	}

	// Make construction of colors easy on the eyes.
	private Color col(float r, float g, float b) {
		return vecmath.color(r, g, b);
	}

	//
	//  3 ------- 2   
	//  |         |  
	//  |         |
	//  |         | 
	//  0 ------- 1
	//

	// The positions of the cube vertices.
	private Vector[] p = { 
			vec(w2, -h2, d2),  //1
			vec(-w2, -h2, d2), //0
			vec(-w2, h2, d2),  //3
			vec(w2, h2, d2)  //2
	};

//	 The colors of the cube vertices.
	  private Color[] c = { 


			  col(0, 255, 255), 
			  col(0, 255, 255), 
			  col(0, 255, 255), 
			  col(0, 255, 255)
	  };

	public Color[] getC() {
		return c;
	}



	public void setC(Color[] c) {
		this.c = c;
		init(defaultshader);
	}



	// Vertices combine position and color information. Every four vertices define
	// one side of the cube.
	private Vertex[] vertices() {  return new Vertex[]{

			//front
			v(p[1], c[1]), v(p[3], c[3]), v(p[0], c[0]), 
			v(p[1], c[1]), v(p[2], c[2]), v(p[3], c[3]),	
	};
	}

	private FloatBuffer positionData;
	private FloatBuffer colorData;

//	@Override
//	public Matrix getlookatMatrix() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	// Initialize the rotation angle of the cube.
	//	private float angle = 0;
}

