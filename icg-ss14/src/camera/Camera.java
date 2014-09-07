package camera;

import ogl.vecmath.Vector;

import org.lwjgl.opengl.GL11;
import static ogl.vecmathimp.FactoryDefault.vecmath;



public class Camera
{
	private Vector initialCenter;
	private Vector center;
	private double rotX;
	private double rotY;
	private double zoom;
	

	public Camera(float x, float y, float z, double rotX, double rotY, double zoom){
		
		this.initialCenter = vecmath.vector(x, y, z);
		this.center = vecmath.vector(x, y, z);
		this.rotX = rotX;
		this.rotY = rotY;
		this.zoom = zoom;
	}
	
	public void rotateAroundX(double x)
	{
		this.rotX = this.rotX + (float)x;
		if(this.rotX > 0)
			this.rotX = 0;
		if(this.rotX < -90)
			this.rotX = -90;
	}

	public void rotateAroundY(double y)
	{
		this.rotY = this.rotY + (float)y;
	}
	
	public void setZoom(double zoom)
	{
		this.zoom = zoom;
	}

	

	public void reset()
	{
//		float x = center.x();
//		float y = center.y();
//		float z = center.z();
		center = initialCenter;
//		x = initialCenter.x();
//		y = initialCenter.y();
//		z = initialCenter.z();
		this.rotX = 0.0;
		this.rotY = 0.0;
		this.zoom = 1.0;
	}

	public void lookThrough()
	{
		GL11.glLoadIdentity();
		
		GL11.glTranslated(0.0f, 0.0f, -5.0/zoom);
		GL11.glRotated(rotX, 1.0, 0.0, 0.0);
		GL11.glRotated(rotY, 0.0, 0.0, 1.0);
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
	}



	/**
	 * Getters and setters
	 */
	
	public Vector getCenter()
	{
		return center;
	}
	
	public Vector getPosition()
	{
		Vector position;
		
		double z = Math.sin(Math.toRadians(90+rotX)) * 5.0/zoom;
		
		double s = Math.cos(Math.toRadians(90+rotX)) * 5.0/zoom;
		
		float position_x = (float)(Math.cos(Math.toRadians(90+rotY)) * s);
		float position_y = (float)(-Math.sin(Math.toRadians(90+rotY)) * s);
		float position_z = (float)z;
		
		position = vecmath.vector(position_x, position_y, position_z);
		return position;
	}
	
	public double getRotX()
	{
		return rotX;
	}
	
	public double getRotY()
	{
		return rotY;
	}
	
	public double getPitch()
	{
		double cameraPitch = -90 - rotX;
		return cameraPitch;
	}
	
	public double getYaw()
	{
		double cameraYaw = rotY;
		return cameraYaw;
	}
}