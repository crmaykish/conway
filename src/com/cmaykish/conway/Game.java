package com.cmaykish.conway;

/**
 * Conway's Game of Life
 * ---
 * The board is represented by a 2D array of cells and each cell is an integer 1 (alive) or 0 (dead).
 *
 * Height and width is determined by the starting board size.
 *
 * The starting state is processed based on the rules of the game and the state after one
 * step is generated and displayed.
 *
 * @author Colin Maykish (crmaykish@gmail.com)
 * 9/29/2015
 */
public class Game {
    private int rows = 0;      // HEIGHT
    private int columns = 0;   // WIDTH

    private int[][] currentState;   // Current state of the board
    private int[][] nextState;      // Next state of the board, where the updates take place

    /**
     *
     * @param startingBoard 2D array of integers. Must only include 1's and 0's
     */
    public Game(int[][] startingBoard){

        // Quick check to make sure the starting board is valid
        for (int[] i : startingBoard){
            for (int j : i){
                if (j < 0 || j > 1){
                    System.err.println("Board contains invalid cells. Cells must be 1 or 0 only.");
                    System.exit(1);
                }
            }
        }

        currentState = startingBoard;

        rows = startingBoard.length;

        if (rows > 0){
            columns = startingBoard[0].length;
        }

        nextState = new int[rows][columns];
    }

    /**
     * Process each cell on the board based on the rules of the game and update status accordingly.
     *
     * @return Board state after moving forward one step.
     */
    public int[][] step(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // Count the cell's living neighbors
                int livingNeighbors = countLivingNeighbors(currentState, i, j);

                // If the cell has fewer than 2 or more than 3 neighbors, it dies
                if (livingNeighbors < 2 || livingNeighbors > 3) {
                    nextState[i][j] = 0;
                }
                // If a dead cell has exactly 3 living neighbors, it comes to life
                else if (livingNeighbors == 3 && currentState[i][j] == 0){
                    nextState[i][j] = 1;
                }
                // In any other case, the cell maintains its previous state
                else {
                    nextState[i][j] = currentState[i][j];
                }
            }
        }

        // Once the next board state has been determined, set it to the current state and reset the next state array
        currentState = nextState;
        nextState = new int[rows][columns];

        return currentState;
    }

     /*
      * Given a board of cells and x- and y-coordinates, count up the living neighbors.
      *
      * Neighbors are cells exactly 1 cell away. For normal cells, there are 8 neighbors represented by these offsets:
      * [-1,-1] [0,-1]  [1,-1]
      * [-1,0]    C     [1,0]
      * [-1,1]  [0,1]   [1,1]
      *
      * For cells on the edge of the board, only consider neighbors also on the board.
      */
    private int countLivingNeighbors(int[][] board, int x, int y){
        int living = 0; // Running total of live neighbors for a cell

        // Loop through the offsets and make sure they are valid cell positions then count live neighbors
        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                if (
                        x + i >= 0 &&       // Neighbor plus offset should be above zero in the x-direction
                        y + j >= 0 &&       // Neighbor plus offset should be above zero in the y-direction
                        x + i < rows &&     // Neighbor plus offset should be below the height of the board
                        y + j < columns &&  // Neighbor plus offset should be below the width of the board
                        !(i==0 && j ==0)    // Don't consider the cell its own neighbor
                ){
                    living += board[x+i][y+j];  // If the neighbor is on the board, add its state to the living count
                }
            }
        }

        return living;
    }

    // Render the game state to the console
    public void display(){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                String cell = currentState[i][j] == 1 ? "O" : ".";
                System.out.print(cell);
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
