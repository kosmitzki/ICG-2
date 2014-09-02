package scenegraph.basics;

import static ogl.vecmathimp.FactoryDefault.vecmath;

import org.lwjgl.input.Keyboard;

import ogl.app.Input;

public class Animation {
	
	public Node node;
	public int key;
	
	public Animation(Node node, int key){
		this.node = node;
		this.key = key;
	}
	
	public void animate(Input input){
		
		final boolean keyC = input.isKeyToggled(Keyboard.KEY_C);
		final boolean keyT = input.isKeyToggled(Keyboard.KEY_T);
		final boolean keyC2 = input.isKeyToggled(Keyboard.KEY_2);
		
		//CUBE ERSCHEINT
				if (keyC){
					cube1.display(modelMatrix);
				}
				//Trinagle ERSCHEINT
				if (keyT){
					triangle1.display(modelMatrix);
				}
				if (keyC2) {
					cube2.display(modelMatrix);
				}


				//	house.display(modelMatrix);
	}
	
	public void scale (Input input) {
		if (input.isKeyDown(Keyboard.KEY_B)) {
			cube1.setTransformation(vecmath.scaleMatrix(2, 2, 2));
		} if (input.isKeyDown(Keyboard.KEY_S)) {
			cube1.setTransformation(vecmath.scaleMatrix(0.5f, 0.5f, 0.5f));
		} if (input.isKeyDown(Keyboard.KEY_N)) {
			cube1.setTransformation(vecmath.scaleMatrix(1, 1, 1));
		}
	}

	//TODO boolean wieder false damit es klappt
	public void move(Input input) {	
		float up = 0.0f;
		float down = 0.0f;
		float left = 0.0f;
		float right = 0.0f;

		while (input.isKeyDown(Keyboard.KEY_UP)) {
			if (isC && !isT) {
				cube1.setTransformation(vecmath.translationMatrix(0, up, 0));
				cube1.display(vecmath.translationMatrix(0, up, 0));
				up += 0.25f;
				if (up == 2) {
					break;
				}
			}
			if (isC && isT) {
				house.setTransformation(vecmath.translationMatrix(0, up, 0));
				house.display(vecmath.translationMatrix(0, up, 0));
				up += 0.25f;
				if (up == 2) {
					break;
				}
			}
			if (!isC && isT) {
				triangle1.setTransformation(vecmath.translationMatrix(0, up, 0));
				triangle1.display(vecmath.translationMatrix(0, up, 0));
				up += 0.25f;
				if (up == 2) {
					break;
				}
			}
		}
		while (input.isKeyDown(Keyboard.KEY_DOWN)) {
			cube1.setTransformation(vecmath.translationMatrix(0, down, 0));
			cube1.display(vecmath.translationMatrix(0, down, 0));
			down -= 0.25f;
			if (down == -2) {
				break;
			}
		}
		while (input.isKeyDown(Keyboard.KEY_LEFT)) {
			cube1.setTransformation(vecmath.translationMatrix(left, 0, 0));
			cube1.display(vecmath.translationMatrix(left, 0, 0));
			left -= 0.25f;
			if (left == -2) {
				break;
			}
		}
		while (input.isKeyDown(Keyboard.KEY_RIGHT)) {
			cube1.setTransformation(vecmath.translationMatrix(right, 0, 0));
			cube1.display(vecmath.translationMatrix(right, down, 0));
			right += 0.25f;
			if (right == 2) {
				break;
			}
		}

	}

}
