import java.awt.Graphics;
import java.util.Random;

/**
* @author Graham Wright
*
* Represents a generation of cells
*/
public class Generation {

    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;
    //public int[] ruleset = {0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1};
    private Cell[][] cells;
    private Cell[][] newCells;
    private Random rand;
    
    /**
    * Constructor for a generation of cells
    */
    public Generation() {
        cells = new Cell[WIDTH][HEIGHT];
        rand = new Random();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                cells[i][j] = new Cell(i, j);
                if (rand.nextInt(20) == 0) {
                    cells[i][j].setIsAlive(true);
                }
            }
        }
        
        newCells = this.deepCopy(cells);
    }
 
    /**
    * Clears the generation/screen
    */ 
    public void clear() {
        cells = new Cell[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
        newCells = this.deepCopy(cells);
    }
    
    /**
    * Changes the state of the cell
    *
    * @param i, j Index of Cell
    * @param alive New State of cell
    */
    public void modifyCell(int i, int j, boolean alive) {
        cells[i][j].setIsAlive(alive);
    }
    
    /**
    * Toggles the state of the cell
    *
    * @param i, j Index of Cell
    */
    public void toggleCell(int i, int j) {
        modifyCell(i, j, !cells[i][j].isAlive());
    }
    
    /**
    * Deep copy of toCopy
    *
    * @return The copied array of cells
    */
    public static Cell[][] deepCopy(Cell[][] toCopy) {
        Cell[][] result = new Cell[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                result[i][j] = new Cell(toCopy[i][j]);
            }
        }
        return result;
    }
 
    /**
    * Updates the generation using the specified rules
    */ 
    public void update() {
        int total = 0;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                total += neighborValue(i-1, j+1);
                total += neighborValue(i, j+1);
                total += neighborValue(i+1, j+1);
                total += neighborValue(i+1, j);
                total += neighborValue(i+1, j-1);
                total += neighborValue(i, j-1);
                total += neighborValue(i-1, j-1);
                total += neighborValue(i-1, j);
                
                boolean newState = rules(total, cells[i][j].isAlive());
                newCells[i][j].setIsAlive(newState);
                total = 0;
            }
        }
        cells = this.deepCopy(newCells);
    }

    
    private int neighborValue(int i, int j) {
        if (i < 0 || i >= WIDTH || j < 0 || j >= HEIGHT) {
            return 0;
        } else {
            return cells[i][j].isAlive()? 1 : 0;
        }
    }
    
    private boolean rules(int total, boolean isAlive) {
        if (isAlive) {
            if (total < 2 || total > 3) {
                return false;
            } else {
                return true;
            }
        } else {
            if (total == 3) {
                return true;
            }
        }
        return false;
    }
    
    //Rules for checking the exact states of neighboring cells
    // private int rules(int a, int b, int c, int d, int e, int f, int g, int h) {
        // String s = "" + a + b + c + d + e + f + g + h;
        // int index = Integer.parseInt(s,2);
        // return ruleset[index];
    // }
    
    /**
    * Draws generation to GUI
    */
    public void draw(Graphics g) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                cells[i][j].draw(g);
            }
        }
    }
}