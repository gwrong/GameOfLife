import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Rectangle;


/**
* @author Graham Wright
*
* Represents a cell in the game of life
*/
public class Cell {
    
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    private Rectangle bounds;
    public boolean previouslyAlive;
    public boolean alive;
    private Color color;
    
    /**
    * Constructor for a Cell
    *
    * @param x The x index of the cell in the array
    * @param y The y index of the cell in the array
    */
    public Cell(int x, int y) {
        bounds = new Rectangle(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
        previouslyAlive = false;
        alive = false;
        color = Color.WHITE;
    }
    
    /**
    * Copy Constructor for a Cell
    *
    * @param that The Cell being copied
    */
    public Cell(Cell that) {
        this.bounds = that.bounds;
        this.previouslyAlive = false;
        this.alive = that.alive;
        this.color = that.color;
    }
    
    /**
    * Returns the state of the cell
    *
    * @return alive Whether or not the cell is alive
    */
    public boolean isAlive() {
        return alive;
    }
    
    public void setIsAlive(boolean alive) {
        previouslyAlive = this.alive;
        this.alive = alive;
        // Random rand = new Random();
        // if (this.alive && this.previouslyAlive) {
            // int red = (rand.nextInt(256));
            // int green = (rand.nextInt(256));
            // int blue = (rand.nextInt(256));
            // color = new Color(red, green, blue);
        // } else if (!this.previouslyAlive) {
            // color = Color.BLACK;
        // }
    }
    
    /**
    * Draws the cell to the screen
    */
    public void draw(Graphics g) {
        if (isAlive()) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.WHITE);
        }
        
        g.fillRect((int) bounds.getX(), (int) bounds.getY(), WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect((int) bounds.getX(), (int) bounds.getY(), WIDTH, HEIGHT);
        
    }
}