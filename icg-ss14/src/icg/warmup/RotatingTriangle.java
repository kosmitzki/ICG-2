/*******************************************************************************
 * Copyright (c) 2013 Henrik Tramberend, Marc Latoschik. YOLO
 * All rights reserved.
 *******************************************************************************/
package icg.warmup;

import icg.warmup.data.Fragment;
import icg.warmup.data.Line;
import icg.warmup.data.Triangle;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.vecmath.Point2d;

public class RotatingTriangle extends JFrame{
	/**
	 * hinzugefügt damit gelbes weg ist
	 */
	private static final long serialVersionUID = 1L;
	// the image holder containing the rendered image
	private ImageIcon imageHolder = new ImageIcon();

	public static void main(String[] args) {
		// simply call the constructor
		new RotatingTriangle(600, 600);
	}
	
	private Point2d rotate(Point2d center, double angle, Point2d p){
		angle = Math.toRadians(angle);
		// drehpunkt ins center verschieben
		double x0 = center.getX();
		double y0 = center.getY();
		double x = p.getX();
		double y = p.getY();
		double xneu = x0 + (x - x0) * Math.cos(angle) - (y - y0) * Math.sin(angle);
		double yneu = y0 + (x - x0) * Math.sin(angle) + (y - y0) * Math.cos(angle);
		p.setX(xneu);
		p.setY(yneu);
		return p;
	}

	public RotatingTriangle(int width, int height){
		Point2d center = new Point2d(width/2, height/2);
		// desired rendering speed
		long fps = 60;

		// create an image (our canvas)
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// set up the window
		initWindow(img, width, height);
		
		// set up a triangle
		Point2d p1 = new Point2d(  width/3,  7*height/18);
		Point2d p2 = new Point2d(2*width/3,  7*height/18);
		Point2d p3 = new Point2d(  width/2, 13*height/18);
		Triangle tri = new Triangle(new Line(p1, p2), new Line(p2, p3), new Line(p3, p1));

		while (true){
			long preTime = System.currentTimeMillis();
			// reset image
			clearImg(img);
			// update line endings (rotate around screen center)
			for (Line l : tri.getLines()){
				l.setPoint1(rotate(center, Math.PI/200.0, l.getPoint1()));
				l.setPoint2(rotate(center, Math.PI/200.0, l.getPoint2()));
			}
			// render triangle to image
			for (Fragment f : tri.render())
				f.render(img, height);
			// calculate time needed for rendering
			long duration = System.currentTimeMillis() - preTime;
			try {
				// sleep for until the next frame shall be drawn
				Thread.sleep(Math.max(0, 1000/fps - duration));
				// put image on screen
				displayImage(img);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void initWindow(BufferedImage img, int width, int height){
		// create image holder
		JLabel imgLabel = new JLabel(imageHolder);
		imgLabel.setSize(new Dimension(width, height));
		
		// add image holder, set size and close op
		getContentPane().add(imgLabel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// draw image for the first time
		clearImg(img);
		displayImage(img);
		
		// adjust size
		pack();
		
		// display window
		setVisible(true);
	}
	
	private void clearImg(BufferedImage img){
		// get graphics context
		Graphics imgGfx = img.getGraphics();
		// fill the image with white color
		imgGfx.setColor(java.awt.Color.WHITE);
		imgGfx.fillRect(0, 0, img.getWidth(), img.getHeight());
		//put a red circle to the center of the image
		imgGfx.setColor(java.awt.Color.RED);
		imgGfx.fillOval(img.getWidth()/2-4,img.getHeight()/2-4, 8, 8);
	}

	private void displayImage(BufferedImage img){
		// update image holder and call repaint
		imageHolder.setImage(img);
		repaint();
	}
}
