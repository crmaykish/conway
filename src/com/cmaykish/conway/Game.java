package com.cmaykish.conway;

/**
 * Conway's Game of Life
 * ---
 * The board is represented by a 2D array of cells and each cell is an integer 1 (alive) or 0 (dead).
 *
 * The starting state is processed based on the rules of the game and the state after one
 * step is generated and displayed.
 *
 * @author Colin Maykish (crmaykish@gmail.com)
 * 9/29/2015
 */
public class Game {
    private static final int ROWS = 6;      // HEIGHT
    private static final int COLUMNS = 8;   // WIDTH

    private int[][] currentState;   // Current state of the board
    private int[][] nextState;      // Next state of the board, where the updates take place

    private int[][] sampleBoard = { // Sample board given in assignment
            {0,0,0,0,0,0,1,0},
            {1,1,1,0,0,0,1,0},
            {0,0,0,0,0,0,1,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,1,1,0,0,0},
            {0,0,0,1,1,0,0,0}
    };

    public Game(){
        init();
    }

    // Begin running the game
    public void run(){
        System.out.println("Initial state:");
        display();
        step();
        System.out.println("Next state:");
        display();
    }

    // Instantiate the game state variables and get ready for the first step
    private void init(){
        currentState = sampleBoard;
        nextState = new int[ROWS][COLUMNS];
    }

    // Process each cell on the board and update its status based on the rules of the game
    private void step(){
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
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
        nextState = new int[ROWS][COLUMNS];
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
                        x + i < ROWS &&     // Neighbor plus offset should be below the height of the board
                        y + j < COLUMNS &&  // Neighbor plus offset should be below the width of the board
                        !(i==0 && j ==0)    // Don't consider the cell its own neighbor
                ){
                    living += board[x+i][y+j];  // If the neighbor is on the board, add its state to the living count
                }
            }
        }

        return living;
    }

    // Render the game state to the console
    private void display(){
        for (int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLUMNS; j++){
                String cell = currentState[i][j] == 1 ? "O" : ".";
                System.out.print(cell);
            }
            System.out.println("");
        }
    }
}
