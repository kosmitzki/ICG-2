package mouseEvent;

import static ogl.vecmathimp.FactoryDefault.vecmath;

import java.awt.*;
import java.awt.event.*; //MouseEvents

import javax.swing.*;

import scenegraph.basics.Node;

//TODO ich krieg das ganze nicht in unsers einbezogen
//link vom tut: http://www.youtube.com/watch?v=sdUJR_DSyBU


public class MouseEvent {
	private JPanel mousepanel; //area where you can click
	private JLabel statusbar; 
	Node node;
	
	public MouseEvent (Node node) {
		//super("title");
		this.node = node;
		
		mousepanel = new JPanel();
//		mousepanel.setBackground(Color.WHITE);
//		add(mousepanel, BorderLayout.CENTER); //appear in the middle of the screen
		
		statusbar = new JLabel("defult");
//		add(statusbar, BorderLayout.SOUTH); //appear at the bottom
		
		Handlerclass handler = new Handlerclass();
		mousepanel.addMouseListener(handler);
		//mousepanel.addMouseMotionListener(handler);
		
	}
	
	private class Handlerclass implements MouseListener{

//		//MouseMotionEvent
//		@Override
//		public void mouseDragged(java.awt.event.MouseEvent event) {
//			statusbar.setText("you are dragging the mouse");
//			
//		}
//
//		//MouseMotionEvent
//		@Override
//		public void mouseMoved(java.awt.event.MouseEvent event) {
//			statusbar.setText("you move the mouse");
//			
//		}

		public void mouseClick (Node node) {
			node.setTransformation(vecmath.scaleMatrix(0.5f,0.5f,0.5f));
		}
		
		@Override
		public void mouseClicked(java.awt.event.MouseEvent event) {
			//statusbar.setText(String.format("Clicked at %d,  %d", event.getX(), event.getY()));
			node.setTransformation(vecmath.scaleMatrix(0.5f, 0.5f, 0.5f));
			
		}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent event) {
			statusbar.setText("you entered the area");
			mousepanel.setBackground(Color.RED);
			
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent event) {
			statusbar.setText("the mouse has left the window");
			mousepanel.setBackground(Color.WHITE);
			
		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent event) {
			statusbar.setText("you pressed down the mouse");
			
		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent event) {
			statusbar.setText("you relesed the button");			
		}
		
	}
}