package is.project.core;

public class Queens {

	int NUM_QUEENS             = 8,
	        NUM_BOARD_SQUARES      = 64;
	int[][]        currentQueensPositions = null,
	        newQueensPositions     = null;
	
	
	
	
	public Double generateRandomPositions () {
        boolean done = false;

        for (int iQueen = 0; iQueen < NUM_QUEENS; iQueen++) {
            boolean repetitions = true;

            //currentQueensPositions[iQueen] = {};
            while (repetitions) {
                currentQueensPositions[iQueen][0] = (int) (Math.random() * 8);
                currentQueensPositions[iQueen][1] = (int) (Math.random() * 8);

                if (!checkRepetitions(currentQueensPositions)) {
                    repetitions = false;
                }
            }
        }

        return calculateAttacks(currentQueensPositions);
    }
	
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
	
	
	
	 public Double generateNeighbor () {
	        for (int iQueen = 0; iQueen < NUM_QUEENS; iQueen++) {
	            newQueensPositions[iQueen][0] = currentQueensPositions[iQueen][0];
	            newQueensPositions[iQueen][1] = currentQueensPositions[iQueen][1];
	                
	           
	        }

	        int changingQueen = (int)(Math.random() * NUM_QUEENS);
	        boolean repetitions = true;

	        while (repetitions) {
	            int oldX = newQueensPositions[changingQueen][0];
	            int oldY = newQueensPositions[changingQueen][1];

	            newQueensPositions[changingQueen][0] = (((newQueensPositions[changingQueen][0] + ((int)(Math.random() * 3) - 1)) % 8) + 8) % 8;
	            newQueensPositions[changingQueen][1] = (((newQueensPositions[changingQueen][1] + ((int)(Math.random() * 3) - 1)) % 8) + 8) % 8;

	            if (!checkRepetitions(newQueensPositions)) {
	                repetitions = false;
	            }
	            else {
	                newQueensPositions[changingQueen][0] = oldX;
	                newQueensPositions[changingQueen][1] = oldY;
	            }
	        }

	        return calculateAttacks(newQueensPositions);
	    }

	    public boolean checkRepetitions (int[][] board) {
	        int howMany = board.length;

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

	    public void acceptNeighbor () {
	        for (int iQueen = 0; iQueen < NUM_QUEENS; iQueen++) {
	            currentQueensPositions[iQueen][0] =newQueensPositions[iQueen][0];
	            currentQueensPositions[iQueen][1] =newQueensPositions[iQueen][1];
	        }
	    }
	    
	    public int[] getCurrentPositions () {
	        int[] positions = null ;

	        for (int iQueen = 0; iQueen < NUM_QUEENS; iQueen++) {
	            positions[iQueen] = currentQueensPositions[iQueen][0] + (currentQueensPositions[iQueen][1] * 8);
	        }

	        return positions;
	    }
	
	

}
