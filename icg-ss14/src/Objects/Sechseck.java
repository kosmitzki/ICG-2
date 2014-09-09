package Objects;

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

public class Sechseck extends Node {

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

		// Draw the triangles that form the hexagon from the vertex data arrays.
		glDrawArrays(GL11.GL_TRIANGLES, 0, vertices.length);
	}



	// The attribute indices for the vertex data.
	public static int vertexAttribIdx = 0;
	public static int colorAttribIdx = 1;

	// Width, depth and height of the cube divided by 2.
	float w2 = 0.5f;
	float h2 = 0.5f;
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
	//        7 ------- 6
	//       /         / \
	//      4 ------- 5   \
	//     /           \   \
	//    10      13    \   8
	//   / \       |     \ /
	//  11  \    12|      9
	//   \   \   | |     /
	//    \   3 -|-*--- 2 
	//     \ /   |     / 
	//      0 ---*--- 1
	//

	// The positions of the triangle vertices.
	private Vector[] t = { 
			vec(-w2, -h2*2, d2), //0
			vec(w2, -h2*2, d2), //1
			vec(w2, -h2*2, -d2), //2
			vec(-w2, -h2*2, -d2), //3
			vec(-w2, 0, d2), //4
			vec(w2, 0, d2), //5
			vec(w2, 0, -d2), //6
			vec(-w2, 0, -d2), //7
			vec(w2*2, -h2, -d2), //8
			vec(w2*2, -h2, d2), //9
			vec(-w2*2, -h2, -d2), //10
			vec(-w2*2, -h2, d2), //11
			vec(0, -h2, d2), //12
			vec(0, -h2, -d2) //13

	};

	// The colors of the triangle vertices.
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

	// The colors of the triangle vertices.
	private Color[] c = { 
			col(1, 0, 0), 
			col(1, 0, 0), 
			col(1, 0, 0), 
			col(1, 0, 0),
			col(0, 1, 1), 
			col(0, 1, 1), 
			col(0, 1, 1), 
			col(0, 1, 1),
			col(0, 0, 0),
			col(0, 0, 0), 
			col(0, 0, 0), 
			col(0, 0, 0), 
			col(0, 1, 0),
			col(0, 1, 0)
			
	};

	public Color[] getC() {
		return c;
	}





	public void setC(Color[] c) {
		this.c = c;
	}



	// Vertices combine position and color information. Every tree vertices define
	// one side of the triangle.
	private Vertex[] vertices = {
			
			
			// front
			v(t[11], c[11]), v(t[0], c[0]), v(t[12], c[12]), 
			v(t[0], c[0]), v(t[1], c[1]), v(t[12], c[12]),
			v(t[1], c[1]), v(t[9], c[9]), v(t[12], c[12]),
			v(t[9], c[9]), v(t[5], c[5]), v(t[12], c[12]),
			v(t[5], c[5]), v(t[4], c[4]), v(t[12], c[12]),
			v(t[4], c[4]), v(t[11], c[11]), v(t[12], c[12]),
			//back
			v(t[2], c[2]), v(t[3], c[3]), v(t[13], c[13]),
			v(t[3], c[3]), v(t[10], c[10]), v(t[13], c[13]),
			v(t[10], c[10]), v(t[7], c[7]), v(t[13], c[13]),
			v(t[7], c[7]), v(t[6], c[6]), v(t[13], c[13]),
			v(t[6], c[6]), v(t[8], c[8]), v(t[13], c[13]),
			v(t[8], c[8]), v(t[2], c[2]), v(t[13], c[13]),
			// bottom
			v(t[2], c[2]), v(t[0], c[0]),  v(t[3], c[3]),
			v(t[2], c[2]), v(t[1], c[1]),  v(t[0], c[0]),
			// right-bottom
			v(t[2], c[2]), v(t[9], c[9]), v(t[1], c[1]),
			v(t[2], c[2]), v(t[8], c[8]),  v(t[9], c[9]),
			
			//
			//        7 ------- 6
			//       /         / \
			//      4 ------- 5   \
			//     /           \   \
			//    10      13    \   8
			//   / \       |     \ /
			//  11  \    12|      9
			//   \   \   | |     /
			//    \   3 -|-*--- 2 
			//     \ /   |     / 
			//      0 ---*--- 1
			//
			
			// right-top
			v(t[8], c[8]), v(t[5], c[5]), v(t[9], c[9]), 
			v(t[8], c[8]), v(t[6], c[6]), v(t[5], c[5]), 
			// top
			v(t[5], c[5]), v(t[7], c[7]), v(t[4], c[4]),
			v(t[5], c[5]), v(t[6], c[6]), v(t[7], c[7]), 
			// top-left
			v(t[7], c[7]), v(t[11], c[11]), v(t[4], c[4]),
			v(t[7], c[7]), v(t[10], c[10]), v(t[11], c[11]), 
			//left-bottom
			v(t[10], c[10]), v(t[0], c[0]), v(t[11], c[11]),
			v(t[10], c[10]), v(t[3], c[3]), v(t[0], c[0]), 

	};

	private FloatBuffer positionData;
	private FloatBuffer colorData;

	// Initialize the rotation angle of the triangle.
	//TODO nicht implementiert
	private float angle = 15;


//	@Override
//	public Matrix getlookatMatrix() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}

