package Objects;

import static ogl.vecmathimp.FactoryDefault.vecmath;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import ogl.vecmath.Matrix;
import scenegraph.basics.Node;
import scenegraph.basics.Shader;
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


//http://slabode.exofire.net/circle_draw.shtml


//http://www.swiftless.com/tutorials/opengl/circle.html
//typedef struct
//{
//float x;
//float y;
//}CIRCLE;
//

//TODO -> analog zu 	??? 
//// Width, depth and height of the cube divided by 2.
//float w2 = 0.6f;
//float h2 = 0.7f;
//float d2 = 0.5f;

//CIRCLE circle;
//
//float rot = 0;
//
//void createcircle (int k, int r, int h) {
//    
//    for (int i = 0; i < 180; i++)
//    {
//    circle.x = r * cos(i) - h;
//    circle.y = r * sin(i) + k;
//    glVertex3f(circle.x + k,circle.y - h,0);
//    
//    circle.x = r * cos(i + 0.1) - h;
//    circle.y = r * sin(i + 0.1) + k;
//    glVertex3f(circle.x + k,circle.y - h,0);
//    }


public class Circle extends Node {

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
	
	
	@Override
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

	
	// The attribute indices for the vertex data.
	public static int vertexAttribIdx = 0;
	public static int colorAttribIdx = 1;

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
	
	private FloatBuffer positionData;
	private FloatBuffer colorData;

	// Initialize the rotation angle of the cube.
	private float angle = 0;
	
	
}







