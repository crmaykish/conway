package com.cmaykish.conway;

import org.junit.Assert;
import org.junit.Test;

public class GameTest {

    private int[][] startingBoard = { // Sample board given in assignment
            {0,0,0,0,0,0,1,0},
            {1,1,1,0,0,0,1,0},
            {0,0,0,0,0,0,1,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,1,1,0,0,0},
            {0,0,0,1,1,0,0,0}
    };

    private int[][] endingBoard = { // Sample board given in assignment
            {0,1,0,0,0,0,0,0},
            {0,1,0,0,0,1,1,1},
            {0,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,1,1,0,0,0},
            {0,0,0,1,1,0,0,0}
    };

    @Test
    public void testOneStep() throws Exception {
        Game game = new Game(startingBoard);
        int[][] result = game.step();
        Assert.assertArrayEquals(endingBoard, result);
    }
}