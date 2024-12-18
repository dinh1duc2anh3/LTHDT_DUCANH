package MainObject;

import Surface.Surface;

public abstract class MainObject {
	private double id; //question
	
	private double mass;
	protected double positionX;
	protected double positionY;
	protected double velocity;
	protected double acceleration;
	
	protected boolean isMoving; //indicates whether the object is currently in motion or not
	
	public static int nbObject =0;
	
	public MainObject( double mass, double initialPositionX, double initialPositionY, double velocity, double acceleration) {
		if (mass <= 0)  throw new IllegalArgumentException("Mass must be postive");
		this.id = ++nbObject;
		
		this.mass = mass;
		this.positionX = initialPositionX;
		this.positionY = initialPositionY;
		this.velocity = 0; // Initial velocity is 0
		this.acceleration = 0; // Initial acceleration is 0
	}
	
	public abstract double calculateFriction(double appliedForce,  Surface surface);

	public abstract void update(double deltaTime, Surface surface, double appliedForce );
	
	
	public double getId() {
		return id;
	}
	public double getMass() {
		return mass;
	}
	public double getPositionX() {
		return positionX;
	}	
	public double getPositionY() {
		return positionY;
	}	
	public double getVelocity() {
		return velocity;
	}
	public double getAcceleration() {
		return acceleration;
	}
	public boolean isMoving() {
		return isMoving;
	}

	
	public void setMass(double mass) {
		this.mass = mass;
	}
	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}	
	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}	
	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}	
	
	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	
	
	

	
	
	
}	
