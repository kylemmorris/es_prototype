package graphics.screens;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class OE_Camera {
	private Vector3f position;	 // position in the world
	private Matrix4f projection; // orthographic projection
	
	public OE_Camera(int width, int height) {
		position = new Vector3f(0,0,0); // origin
		projection = new Matrix4f().setOrtho2D(-width/2, width/2, -height/2, height/2);
	}
	
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	public void addPosition(Vector3f position) {
		this.position.add(position);
	}
	
	public Vector3f getPosition() { return position; }
	
	public Matrix4f getProjection() {
		Matrix4f target = new Matrix4f();
		Matrix4f pos = new Matrix4f().setTranslation(position);
		
		target = projection.mul(pos, target);
		return target;
	}
}
