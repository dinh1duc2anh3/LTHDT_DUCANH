package MainObject;

import Simulation.PhysicsCalculator;
import Surface.Surface;

public class Sylinder extends MainObject implements Rotatable{

	private double radius;
    private double angularVelocity;
    private double angularAcceleration;
    private boolean isSliding;
	


	public Sylinder(double mass, double initialPositionX, double initialPositionY,double velocity, double acceleration,double radius) {
		super(mass, initialPositionX, initialPositionY,velocity,acceleration);
		this.radius = radius;
        this.angularVelocity = 0;
        this.angularAcceleration = 0;
	}
	
	
	 
	 public double calculateFriction(double appliedForce,  Surface surface) {
			double normalForce = PhysicsCalculator.calculateNormalForce(getMass());
			double staticCo = surface.getStaticCoefficient();
			double kineticCo = surface.getKineticCoefficient();
			
			if (appliedForce == 0) {
				if (!isMoving()) {
					setMoving(false);
				}
				else {
					setMoving(true);
				}
				setSliding(false);
				return appliedForce / 3;
			}
			else if ( appliedForce > 0 && appliedForce <= 3 * normalForce * staticCo ) {
				setMoving(true);
				// di chuyen lan ko truot
				setSliding(false);
				return appliedForce / 3;
			}
			else {
				setMoving(true);
				// di chuyen lan co truot
				setSliding(true);
				return normalForce * kineticCo;
			}

		}
	 
		
		
		@Override
		public void update(double deltaTime , Surface surface, double appliedForce) {
		    double frictionForce = calculateFriction(appliedForce, surface); // Lực ma sát

		    double netForce = PhysicsCalculator.calculateNetForce(appliedForce, frictionForce); // Lực tổng
		    
		    if (!isMoving()) {
	        	
	        	setAcceleration(0);
	        	setPositionX(getPositionX());
	        	setVelocity(0);
	        	setAngularAcceleration(0);
	        	setAngularVelocity(0);
	        }
		    else {
		    	
		    	// Tính gia tốc tịnh tiến (linear acceleration)
		        double newAcceleration = PhysicsCalculator.calculateAcceleration(netForce, getMass());

		        // Tính vận tốc tịnh tiến mới
		        double newVelocity = PhysicsCalculator.calculateVelocity(getVelocity(), newAcceleration, deltaTime);

		        
		        
		    	// Nếu silinder không trượt (rolling without slipping)
		    	if (!isSliding()) {
		    		
			        // Tính vị trí mới
			        double newPosition = PhysicsCalculator.calculatePosition(getPositionX(), getVelocity(), newAcceleration, deltaTime);
			        
			        // Tính gia tốc góc (angular acceleration) từ gia tốc tịnh tiến
			        double newAngularAcceleration = newAcceleration / getRadius(); // α = a / r

			        // Tính vận tốc góc từ vận tốc tịnh tiến (v = ω * r)
			        double newAngularVelocity = newVelocity / getRadius(); 


			        // Cập nhật các giá trị
			        setAcceleration(newAcceleration);
			        setVelocity(newVelocity);
			        setPositionX(newPosition);
			        setAngularVelocity(newAngularVelocity);
			        setAngularAcceleration(newAngularAcceleration);
			    
		    	}
		    	// Nếu silinder đang trượt
		    	else {

			        // Tính vị trí mới
			        double newPosition = PhysicsCalculator.calculatePosition(getPositionX(), newVelocity, newAcceleration, deltaTime);

			        // Tính gia tốc quay (angular acceleration) từ lực ma sát
			        double angularAcceleration = PhysicsCalculator.calculateAngularAcceleration(frictionForce, getMass(), getRadius());

			        // Tính vận tốc góc mới
			        double newAngularVelocity = PhysicsCalculator.calculateAngularVelocity( getAngularVelocity() ,angularAcceleration, deltaTime) ;

			        // Tính vị trí góc mới (điều chỉnh góc)
			        double deltaAngularPosition = PhysicsCalculator.calculateDeltaAngularPosition(getAngularVelocity(), angularAcceleration, deltaTime, getRadius()) ;

			        // Cập nhật các giá trị
			        setAcceleration(newAcceleration);
			        setVelocity(newVelocity);
			        setAngularVelocity(newAngularVelocity);
			        setPositionX(newPosition+deltaAngularPosition); /// ?


			   
		    	}
			
		    }
		    
		}

	
	
	
	



	//getters + setters
	public double getRadius() {
        return radius;
    }
    public double getAngularVelocity() {
        return angularVelocity;
    }
    public double getAngularAcceleration() {
		return angularAcceleration;
	}
	public boolean isSliding() {
		return isSliding;
	}
    

	public void setRadius(double radius) {
		this.radius = radius;
	}
	public void setAngularVelocity(double angularVelocity) {
		this.angularVelocity = angularVelocity;
	}
	public void setAngularAcceleration(double angularAcceleration) {
        this.angularAcceleration = angularAcceleration;
    }
	public void setSliding(boolean isSliding) {
		this.isSliding = isSliding;
	}
	
	

	@Override
	public void Rotatable() {
		// TODO Auto-generated method stub
		
	}

}
