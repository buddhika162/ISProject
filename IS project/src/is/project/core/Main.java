package is.project.core;

import java.sql.Time;

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
		Main main = new Main();
		// TODO Auto-generated method stub
		main.coolingFactor            = 0.05;
        main.stabilizingFactor        = 1.005;
        main.freezingTemperature      = 0.0;
        main.queens      = new Queens();
       // generateNeighbor         = options.generateNeighbor;
        //acceptNeighbor           = options.acceptNeighbor;

        main.currentSystemEnergy      = queens.generateRandomPositions();
        long starttime = System.currentTimeMillis();
        main.queens.printBord();
        main.currentSystemTemperature = 50.0;
        main.currentStabilizer        = 20.0;
        boolean check =false;
        check = main.doSimulationStep();
        while(!check){
        	check = main.doSimulationStep();
        }
        long endtime = System.currentTimeMillis();
        System.out.println("used mili secounds = "+(endtime-starttime));
        System.out.println("temp " +currentSystemTemperature+" energy "+currentSystemEnergy);
        main.queens.printBord();

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
	
	public  boolean doSimulationStep () {
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
	        
	        //System.out.println("temp " +currentSystemTemperature+" energy "+currentSystemEnergy);
	        return false;
	    }
	    currentSystemTemperature = freezingTemperature;
	    
        return true;
	}
	
	
	
	
}
