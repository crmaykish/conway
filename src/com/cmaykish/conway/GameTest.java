package com.cmaykish.conway;

import org.junit.Assert;
import org.junit.Test;

public class GameTest {

    /**
     * Two living cells die from starvation
     */
    @Test
    public void testStarvationRule(){
        int[][] start = {
                {1,0},
                {0,1}
        };

        int[][] end = {
                {0,0},
                {0,0}
        };

        Game game = new Game(start);
        int[][] result = game.step();
        Assert.assertArrayEquals(end, result);
    }

    /**
     * Center cell dies from overcrowding
     */
    @Test
    public void testOvercrowdingRule(){
        int[][] start = {
                {1,1,0},
                {0,1,0},
                {1,0,1}
        };

        int[][] end = {
                {1,1,0},
                {0,0,1},
                {0,1,0}
        };

        Game game = new Game(start);
        int[][] result = game.step();
        Assert.assertArrayEquals(end, result);
    }

    /**
     * Center cell comes to life for having 3 neighbors
     */
    @Test
    public void testNewLifeRule(){
        int[][] start = {
                {1,0,1},
                {0,0,0},
                {1,0,0}
        };

        int[][] end = {
                {0,0,0},
                {0,1,0},
                {0,0,0}
        };

        Game game = new Game(start);
        int[][] result = game.step();
        Assert.assertArrayEquals(end, result);
    }

    /**
     * All living cells have exactly three neighbors and no dead cells have three so board state remains the same
     */
    @Test
    public void testMaintainStateRule(){
        int[][] start = {
                {1,1,0},
                {1,1,0},
                {0,0,0}
        };

        int[][] end = {
                {1,1,0},
                {1,1,0},
                {0,0,0}
        };

        Game game = new Game(start);
        int[][] result = game.step();
        Assert.assertArrayEquals(end, result);
    }

    /**
     * Complete board test - test all rules in one step
     */
    @Test
    public void testFullStep(){

        int[][] start = { // Sample starting board given in assignment
                {0,0,0,0,0,0,1,0},
                {1,1,1,0,0,0,1,0},
                {0,0,0,0,0,0,1,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,1,1,0,0,0},
                {0,0,0,1,1,0,0,0}
        };

        int[][] end = { // Expected result after one step of the game
                {0,1,0,0,0,0,0,0},
                {0,1,0,0,0,1,1,1},
                {0,1,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,1,1,0,0,0},
                {0,0,0,1,1,0,0,0}
        };

        Game game = new Game(start);
        int[][] result = game.step();
        Assert.assertArrayEquals(end, result);
    }
}