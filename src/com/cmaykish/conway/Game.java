package com.cmaykish.conway;


public class Game {
    private static final int WIDTH = 8;
    private static final int HEIGHT = 6;

    private static boolean RUNNING = true;

    private int[][] board;

    public Game(){
        init();

        // Game loop - run until the game is over
        while (RUNNING){
            step();
            display();
        }
    }

    // Instantiate the game state variables and get ready for the first step
    public void init(){
        board = new int[WIDTH][HEIGHT];
    }

    // Process one cycle of the game
    public void step(){

    }

    // Render the game state to the console
    public void display(){

    }
}
