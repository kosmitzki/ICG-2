package ogl.cube;

import static ogl.vecmathimp.FactoryDefault.vecmath;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

import java.nio.FloatBuffer;

import ogl.app.MatrixUniform;
import ogl.vecmath.Color;
import ogl.vecmath.Matrix;
import ogl.vecmath.Vector;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import scenegraph.basics.Node;
import scenegraph.basics.Shader;

public class Cube extends Node {

	private Shader defaultshader;

	public void init(Shader defaultshader) {
		this.defaultshader = defaultshader;

		// Prepare the vertex data arrays.
		// Compile vertex data into a Java Buffer data structures that can be
		// passed to the OpenGL API efficently.
		positionData = BufferUtils.createFloatBuffer(vertices.length
				* vecmath.vectorSize());
		colorData = BufferUtils.createFloatBuffer(vertices.length
				* vecmath.colorSize());

		for (Vertex v : vertices) {
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
		glDrawArrays(GL11.GL_QUADS, 0, vertices.length);
	}



	// The attribute indices for the vertex data.bbb
	public static int vertexAttribIdx = 0;
	public static int colorAttribIdx = 1;

	// Width, depth and height of the cube divided by 2.
	float w2 = 0.6f;
	float h2 = 0.7f;
	float d2 = 0.5f;



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
	//     6 ------- 7 
	//   / |       / | 
	//  3 ------- 2  | 
	//  |  |      |  | 
	//  |  5 -----|- 4 
	//  | /       | / 
	//  0 ------- 1
	//

	// The positions of the cube vertices.
	private Vector[] p = { 
			vec(-w2, -h2, -d2), 
			vec(w2, -h2, -d2),
			vec(w2, h2, -d2), 
			vec(-w2, h2, -d2), 
			vec(w2, -h2, d2), 
			vec(-w2, -h2, d2),
			vec(-w2, h2, d2), 
			vec(w2, h2, d2) 
	};

	// The colors of the cube vertices.
	//  private Color[] d = { 
	//	      col(0, 0, 0), 
	//	      col(1, 0, 0), 
	//	      col(1, 1, 0), 
	//	      col(0, 1, 0),
	//	      col(1, 0, 1), 
	//	      col(0, 0, 1), 
	//	      col(0, 1, 1), 
	//	      col(1, 1, 1) 
	//  };

	// The colors of the cube vertices.
	private Color[] c = { 
			col(1, 0, 0), 
			col(1, 0, 0), 
			col(1, 0, 0), 
			col(1, 0, 0),
			col(0, 0, 0), 
			col(0, 0, 0), 
			col(0, 0, 0), 
			col(0, 0, 0) 
	};

	// Vertices combine position and color information. Every four vertices define
	// one side of the cube.
	private Vertex[] vertices = {
			// front
			v(p[0], c[0]), v(p[1], c[1]), v(p[2], c[2]), v(p[3], c[3]),
			// back
			v(p[4], c[4]), v(p[5], c[5]), v(p[6], c[6]), v(p[7], c[7]),
			// right
			v(p[1], c[1]), v(p[4], c[4]), v(p[7], c[7]), v(p[2], c[2]),
			// top
			v(p[3], c[3]), v(p[2], c[2]), v(p[7], c[7]), v(p[6], c[6]),
			// left
			v(p[5], c[5]), v(p[0], c[0]), v(p[3], c[3]), v(p[6], c[6]),
			// bottom
			v(p[5], c[5]), v(p[4], c[4]), v(p[1], c[1]), v(p[0], c[0]) 
	};

	private FloatBuffer positionData;
	private FloatBuffer colorData;

	// Initialize the rotation angle of the cube.
	private float angle = 0;
}

