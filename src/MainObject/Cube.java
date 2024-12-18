package MainObject;

import Simulation.PhysicsCalculator;
import Surface.Surface;

public class Cube extends MainObject {
	
	private double sideLength;

    public Cube(double mass, double positionX, double positionY, double velocity, double acceleration, double sideLength) {
        super(mass, positionX, positionY,velocity,acceleration);
        this.sideLength = sideLength;
    }
    
    
    
    //getter and setters
    public double getSideLength() {
    	return sideLength;
    }
    
    public void setSideLength(double sideLength) {
		this.sideLength = sideLength;
	}
    
    
    @Override
    public double calculateFriction(double appliedForce, Surface surface) {
		double normalForce = PhysicsCalculator.calculateNormalForce(getMass());
		double staticCo = surface.getStaticCoefficient();
		double kineticCo = surface.getKineticCoefficient();
		
		if (appliedForce <= normalForce * staticCo ) {
			if (!isMoving()) {
				setMoving(false);
			}
			else {
				setMoving(true);
			}
			return appliedForce;
		}
		else {
			setMoving(true);
			return normalForce * kineticCo;
		}
		
		
		
		
		
		
		
		//throw exception
		//them ca phan code throw exception khi nhap vao mot so sai format ? la phan cua controller hay gi ?

		
		
		
		
		
		
		
		
		
	}
    
    
    
    @Override
    public void update(double deltaTime , Surface surface, double appliedForce) {
        
        double frictionForce = calculateFriction(appliedForce, surface );
        double netForce = PhysicsCalculator.calculateNetForce(appliedForce, frictionForce);
        
        //neu dang dung yen
        if (!isMoving()) {
        	
        	setAcceleration(0);
        	setPositionX(getPositionX());
        	setVelocity(0);
        }
        else {
        	double newAcceleration = PhysicsCalculator.calculateAcceleration(netForce, getMass());
            
            double newVelocity = PhysicsCalculator.calculateVelocity(getVelocity(), newAcceleration, deltaTime);
            //nếu như chuyển động chậm dần tới v =0 thì cho nó dừng lại
            if (newVelocity <0) {
            	setMoving(false);
            	return;
            }
            
            double newPosition = PhysicsCalculator.calculatePosition(getPositionX(), velocity, newAcceleration, deltaTime);
            
            setAcceleration(newAcceleration);
            setPositionX(newPosition);
            setVelocity(newVelocity);	
        }
        
    	return;
    }


}
