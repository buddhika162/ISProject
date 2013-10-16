package is.project.core;

public class Main {

	/**
	 * @param args
	 */
	static Double coolingFactor            = 0.0;

	static Double stabilizingFactor        = 0.0;

	static Double freezingTemperature      = 0.0;

	static Double currentSystemEnergy      = 0.0;

	static Double currentSystemTemperature = 0.0;

	static Double currentStabilizer        = 0.0;
	

	static Queens queens = null;
	    /*    generateNewSolution      = null,
	        generateNeighbor         = null,
	        acceptNeighbor           = null;*/
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		coolingFactor            = 0.05;
        stabilizingFactor        = 1.005;
        freezingTemperature      = 0.0;
        queens      = new Queens();
       // generateNeighbor         = options.generateNeighbor;
        //acceptNeighbor           = options.acceptNeighbor;

        currentSystemEnergy      = queens.generateRandomPositions();
        currentSystemTemperature = 35.0;
        currentStabilizer        = 35.0;

	}
	public boolean probabilityFunction (Double temperature, Double delta) {
	    if (delta < 0) {
	        return true;
	    }
	 
	    Double C = Math.exp(-delta / temperature);
	    Double R = Math.random();
	 
	    if (R < C) {
	        return true;
	    }
	 
	    return false;
	}
	
	public boolean doSimulationStep () {
	    if (currentSystemTemperature > freezingTemperature) {
	        for (int i = 0; i < currentStabilizer; i++) {
	            Double newEnergy = queens.generateNeighbor(),
	                energyDelta = newEnergy - currentSystemEnergy;
	 
	            if (probabilityFunction(currentSystemTemperature, energyDelta)) {
	            	queens.acceptNeighbor ();
	                currentSystemEnergy = newEnergy;
	            }
	        }
	        currentSystemTemperature = currentSystemTemperature - coolingFactor;
	        currentStabilizer = currentStabilizer * stabilizingFactor;
	        return false;
	    }
	    currentSystemTemperature = freezingTemperature;
	    return true;
	}
	
	
	
	
}
