package camera;

import ogl.vecmath.Vector;

import org.lwjgl.opengl.GL11;
import static ogl.vecmathimp.FactoryDefault.vecmath;



public class Camera {
	public static final Vector yAxis = vecmath.vector(0, 1, 0); //absolut up in the world
	private Vector pos;
	private Vector forward;
	private Vector up;
	
	public Camera() { //default Camera
		this(vecmath.vector(0, 0, 0), vecmath.vector(0, 0, 1), vecmath.vector(0, 1, 0));
	}
	
	public Camera(Vector pos, Vector forward, Vector up) {
		this.pos = pos;
		this.forward = forward;
		this.up = up;
		
		up.normalize(); //TODO was tut das?
		forward.normalize();
	}

	public void move(Vector dir, float amt) {
		pos = pos.add(dir.mult(amt));
	}

//	//TODO rotation class für vectoren
//	public void rotateY (float angle) { //left and right
//		Vector Haxis = yAxis.cross(forward); //horizontal axis
//		Haxis.normalize();
//		
//		forward.rotate(angle, yAxis);
//		forward.normalize();
//		
//		up = forward.cross(Haxis);
//		up.normalize();
//	}
//	
//	public void rotateX (float angle) { //up and down
//		Vector Haxis = yAxis.cross(forward); //horizontal axis
//		Haxis.normalize();
//		
//		forward.rotate(angle, Haxis);
//		forward.normalize();
//		
//		up = forward.cross(Haxis);
//		up.normalize();
//	}
//	
//	//TODO muss eigentlich in die Vector Klasse, die wir nicht haben
//	public Vector rotate(float angle, Vector axis) {
//		float sinHalfAngle = (float)Math.sin(Math.toRadians(angle / 2));
//		float cosHalfAngle = (float)Math.cos(Math.toRadians(angle / 2));
//		
//		float rX = axis.x() * sinHalfAngle;
//		float rY = axis.y() * sinHalfAngle;
//		float rZ = axis.z() * sinHalfAngle;
//		float rW = cosHalfAngle;
//		
//		
//	}

	public Vector getLeft() {
		Vector left = up.cross(forward);
		left.normalize();
		return left;
	}
	
	public Vector getRight() {
		Vector right = forward.cross(up);
		right.normalize();
		return right;
	}
	
	public Vector getPos() {
		return pos;
	}

	public void setPos(Vector pos) {
		this.pos = pos;
	}

	public Vector getForward() {
		return forward;
	}

	public void setForward(Vector forward) {
		this.forward = forward;
	}

	public Vector getUp() {
		return up;
	}

	public void setUp(Vector up) {
		this.up = up;
	}
	
	
	
//{
//	private Vector initialCenter;
//	private Vector center;
//	private double rotX;
//	private double rotY;
//	private double zoom;
//	
//
//	public Camera(float x, float y, float z, double rotX, double rotY, double zoom){
//		
//		this.initialCenter = vecmath.vector(x, y, z);
//		this.center = vecmath.vector(x, y, z);
//		this.rotX = rotX;
//		this.rotY = rotY;
//		this.zoom = zoom;
//	}
//	
//	public void rotateAroundX(double x)
//	{
//		this.rotX = this.rotX + (float)x;
//		if(this.rotX > 0)
//			this.rotX = 0;
//		if(this.rotX < -90)
//			this.rotX = -90;
//	}
//
//	public void rotateAroundY(double y)
//	{
//		this.rotY = this.rotY + (float)y;
//	}
//	
//	public void setZoom(double zoom)
//	{
//		this.zoom = zoom;
//	}
//
//	
//
//	public void reset()
//	{
////		float x = center.x();
////		float y = center.y();
////		float z = center.z();
//		center = initialCenter;
////		x = initialCenter.x();
////		y = initialCenter.y();
////		z = initialCenter.z();
//		this.rotX = 0.0;
//		this.rotY = 0.0;
//		this.zoom = 1.0;
//	}
//
//	public void lookThrough()
//	{
//		GL11.glLoadIdentity();
//		
//		GL11.glTranslated(0.0f, 0.0f, -5.0/zoom);
//		GL11.glRotated(rotX, 1.0, 0.0, 0.0);
//		GL11.glRotated(rotY, 0.0, 0.0, 1.0);
//		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
//	}
//
//
//
//	/**
//	 * Getters and setters
//	 */
//	
//	public Vector getCenter()
//	{
//		return center;
//	}
//	
//	public Vector getPosition()
//	{
//		Vector position;
//		
//		double z = Math.sin(Math.toRadians(90+rotX)) * 5.0/zoom;
//		
//		double s = Math.cos(Math.toRadians(90+rotX)) * 5.0/zoom;
//		
//		float position_x = (float)(Math.cos(Math.toRadians(90+rotY)) * s);
//		float position_y = (float)(-Math.sin(Math.toRadians(90+rotY)) * s);
//		float position_z = (float)z;
//		
//		position = vecmath.vector(position_x, position_y, position_z);
//		return position;
//	}
//	
//	public double getRotX()
//	{
//		return rotX;
//	}
//	
//	public double getRotY()
//	{
//		return rotY;
//	}
//	
//	public double getPitch()
//	{
//		double cameraPitch = -90 - rotX;
//		return cameraPitch;
//	}
//	
//	public double getYaw()
//	{
//		double cameraYaw = rotY;
//		return cameraYaw;
//	}
}