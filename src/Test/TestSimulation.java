package Test;

import Force.AppliedForce;
import MainObject.Cube;
import Surface.Surface;
import Simulation.Simulation;

public class TestSimulation {
    public static void main(String[] args) {
        // Khởi tạo các đối tượng
        Cube cube = new Cube(10, 0, 0, 0, 0, 2); // mass = 10kg, positionX = 0, velocity = 0
        Surface surface = new Surface(0.4, 0.3); // hệ số ma sát tĩnh = 0.4, động = 0.3
        AppliedForce appliedForce = new AppliedForce(50, 1); // lực áp dụng = 50N

        // Tạo đối tượng Simulation
        Simulation simulation = new Simulation(cube, surface, appliedForce);

        // Chạy mô phỏng trong 10 giây, bước thời gian là 0.1 giây
        simulation.runSimulation(10, 0.1); 
    }
}
