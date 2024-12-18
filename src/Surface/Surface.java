package Surface;


public class Surface {
	 private double staticCoefficient;
	 private double kineticCoefficient;
	
	 public Surface(double staticCoefficient, double kineticCoefficient) {
		 //note : đưa ra lỗi nếu như staticCoeeficient <= kineticCoefficient
		 if (staticCoefficient <= kineticCoefficient) {
		        throw new IllegalArgumentException("Static coefficient must be greater than kinetic coefficient");
		 }
		 
		 this.staticCoefficient = staticCoefficient;
		 this.kineticCoefficient = kineticCoefficient;
	 }
	


	public double getStaticCoefficient() {
        return staticCoefficient;
    }

    public double getKineticCoefficient() {
        return kineticCoefficient;
    }
    
	public void setStaticCoefficient(double staticCoefficient) {
		this.staticCoefficient = staticCoefficient;
	}

	public void setKineticCoefficient(double kineticCoefficient) {
		this.kineticCoefficient = kineticCoefficient;
	}
	
	 
}
