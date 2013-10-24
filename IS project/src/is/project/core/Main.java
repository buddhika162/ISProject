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
	   
	
	
	public static void main(String[] args) {
		Main main = new Main();
		// TODO Auto-generated method stub
		main.coolingFactor            = 0.05;
        main.stabilizingFactor        = 1.005;
        main.freezingTemperature      = 0.0;
        main.queens      = new Queens();
       

        main.currentSystemEnergy      = queens.generateRandomPositions();
        main.queens.printBord();
        long starttime = System.currentTimeMillis();
       
        main.currentSystemTemperature = 80.0;
        main.currentStabilizer        = 25.0;
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
	
	
	//probability function to select the neighbor
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
	
	
	//step to select a good neighbor
	public  boolean doSimulationStep () {
	    if (currentSystemTemperature > freezingTemperature) {
	        for (int i = 0; i < currentStabilizer; i++) {
	            Double newEnergy = queens.generateNeighbor(),
	                energyDelta = newEnergy - currentSystemEnergy;
	 
	            if (probabilityFunction(currentSystemTemperature, energyDelta)) {
	            	queens.acceptNeighbor ();
	                currentSystemEnergy = newEnergy;
	            }
	            // some improvitation to the algo
	            if(energyDelta<0){
	            	break;
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
