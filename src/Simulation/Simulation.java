package Simulation;

import Force.AppliedForce;
import MainObject.MainObject;
import Surface.Surface;

public class Simulation {
	private MainObject object;
	private Surface surface;
	private AppliedForce appliedForce;
	
    public Simulation(MainObject object, Surface surface, AppliedForce appliedForce) {
        this.object = object;
        this.surface = surface;
        this.appliedForce = appliedForce;
    }
    

    
    public void update(double deltaTime) {
        double appliedForceMagnitude = appliedForce.getMagnitude();
    	
        object.update(deltaTime, surface, appliedForceMagnitude);
    }
    
    // Chạy toàn bộ mô phỏng qua các bước thời gian
    public void runSimulation(double totalTime, double timeStep) {
        double currentTime = 0.0;

        while (currentTime < totalTime) {
            System.out.println("Time: " + currentTime + "s");
            System.out.println("Position: " + object.getPositionX() + "m");
            System.out.println("Velocity: " + object.getVelocity() + "m/s");
            System.out.println("Acceleration: " + object.getAcceleration() + "m/s²");
            System.out.println("----------------------------------");

            // Cập nhật trạng thái của hệ thống
            update(timeStep);

            // Tăng thời gian
            currentTime += timeStep;
        }
    }
    
    
}
