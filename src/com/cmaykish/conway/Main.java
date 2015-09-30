package com.cmaykish.conway;

public class Main {

    private static int[][] sampleBoard = { // Sample board given in assignment
            {0,0,0,0,0,0,1,0},
            {1,1,1,0,0,0,1,0},
            {0,0,0,0,0,0,1,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,1,1,0,0,0},
            {0,0,0,1,1,0,0,0}
    };

    public static void main(String[] args) {
        Game game = new Game(sampleBoard);
        game.display();
        game.step();
        game.display();
    }
}
