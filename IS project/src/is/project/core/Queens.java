package is.project.core;

public class Queens {

	int NUM_QUEENS             = 8,
	        NUM_BOARD_SQUARES      = 64;
	int[][]        currentQueensPositions = null,
	        newQueensPositions     = null;
	
	
	//printing the chess board
	public void printBord(){
		for (int iQueen = 0; iQueen < NUM_QUEENS; iQueen++) {
			System.out.println("Queen "+iQueen+ "x = "+currentQueensPositions[iQueen][0]+" y = "+currentQueensPositions[iQueen][1]);
		}
	}
	
	//generate random state on the board
	public Double generateRandomPositions () {
        boolean done = false;
        currentQueensPositions = new int[NUM_QUEENS][2];
        for (int iQueen = 0; iQueen < NUM_QUEENS; iQueen++) {
            boolean repetitions = true;
            
             
            while (repetitions) {
                currentQueensPositions[iQueen][0] = (int) (Math.random() * NUM_QUEENS);
                currentQueensPositions[iQueen][1] = (int) (Math.random() * NUM_QUEENS);
                //System.out.println(repetitions+"Queen "+iQueen+ "x = "+currentQueensPositions[iQueen][0]+" y = "+currentQueensPositions[iQueen][1]);
                if (!checkRepetitions(currentQueensPositions,iQueen)) {
                    repetitions = false;
                }
            }
        }

        return calculateAttacks(currentQueensPositions);
    }
	
	
	//calculate no of attackes possible
	public Double calculateAttacks (int[][] board) {
        Double numAttacks = 0.0;

        for (int iQueen = 0; iQueen < NUM_QUEENS - 1; iQueen++) {
            for (int iAttackingQueen = iQueen + 1; iAttackingQueen < NUM_QUEENS; iAttackingQueen++) {
                if (board[iQueen][0] == board[iAttackingQueen][0]) {
                    numAttacks++;
                }
                else if (board[iQueen][1] == board[iAttackingQueen][1]) {
                    numAttacks++;
                }
                else if (board[iQueen][0] + board[iQueen][1] ==
                         board[iAttackingQueen][0] + board[iAttackingQueen][1]) {
                    numAttacks++;
                }
                else if (board[iQueen][1] - board[iQueen][0] ==
                         board[iAttackingQueen][1] - board[iAttackingQueen][0]) {
                    numAttacks++;
                }
            }
        }

        return numAttacks;
    }
	
	
	//generate a neighbor
	 public Double generateNeighbor () {
		 newQueensPositions = new int[NUM_QUEENS][2];
	        for (int iQueen = 0; iQueen < NUM_QUEENS; iQueen++) {
	            newQueensPositions[iQueen][0] = currentQueensPositions[iQueen][0];
	            newQueensPositions[iQueen][1] = currentQueensPositions[iQueen][1];
	                
	           
	        }

	        int changingQueen = (int)(Math.random() * NUM_QUEENS);
	        boolean repetitions = true;

	        while (repetitions) {
	            int oldX = newQueensPositions[changingQueen][0];
	            int oldY = newQueensPositions[changingQueen][1];

	            newQueensPositions[changingQueen][0] = (((newQueensPositions[changingQueen][0] + ((int)(Math.random() * 3) - 1)) % NUM_QUEENS) + NUM_QUEENS) % NUM_QUEENS;
	            newQueensPositions[changingQueen][1] = (((newQueensPositions[changingQueen][1] + ((int)(Math.random() * 3) - 1)) % NUM_QUEENS) + NUM_QUEENS) % NUM_QUEENS;

	            if (!checkRepetitions(newQueensPositions,NUM_QUEENS-1)) {
	                repetitions = false;
	            }
	            else {
	                newQueensPositions[changingQueen][0] = oldX;
	                newQueensPositions[changingQueen][1] = oldY;
	            }
	        }

	        return calculateAttacks(newQueensPositions);
	    }

	 	//check if repete position
	    public boolean checkRepetitions (int[][] board,int howMany) {
	        
	    	howMany = howMany+1;
	       
	        for (int iQueen = 0; iQueen < howMany - 1; iQueen++) {
	            for (int iCheckQueen = iQueen + 1; iCheckQueen < howMany; iCheckQueen++) {
	                if (board[iQueen][0] == board[iCheckQueen][0] &&
	                    board[iQueen][1] == board[iCheckQueen][1]) {
	                	
	                	return true;
	                }
	            }
	        }

	        return false;
	    }

	    //accepting the neighbor
	    public void acceptNeighbor () {
	        for (int iQueen = 0; iQueen < NUM_QUEENS; iQueen++) {
	            currentQueensPositions[iQueen][0] =newQueensPositions[iQueen][0];
	            currentQueensPositions[iQueen][1] =newQueensPositions[iQueen][1];
	        }
	    }
	    
	    
	

}
