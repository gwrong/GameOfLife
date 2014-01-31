import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;
import javax.swing.Timer;

/**
* @author Graham Wright
*
* The GUI for the cells
*/
public class CellGUI extends JPanel {
    
    private ButtonPanel buttonPanel;
    private Generation gen;
    private Timer gameTimer;
    
    /**
    * The GUI for the game constructor
    */
    public CellGUI() {
        gen = new Generation();
        setPreferredSize(new Dimension(1000, 1000));
        repaint();
        
        ClickListener list = new ClickListener();
        addMouseListener(list);
        addMouseMotionListener(list);
        
        gameTimer = new Timer(50, new TimeListener());
        gameTimer.start();
    }
    
    public void passPanel(ButtonPanel bp) {
        buttonPanel = bp;
    }
    
    /**
    * Deals with the drawing of the components for the GUI
    *
    * @param g The Graphics object used to draw things to the screen
    */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gen.draw(g);
    }
    
    /**
    * Clears the Cell GUI
    */
    public void clear() {
        gen.clear();
        repaint();
    }
    
    /**
    * Starts or stops the timer
    *
    * @return Whether or not the text should be set to "Stop"
    */
    public boolean toggleTimer() {
        if (gameTimer.isRunning()) {
            gameTimer.stop();
            return false;
            
        } else {
            gameTimer.start();
            return true;
        }
    }
    
    private void incrementGeneration() {
        buttonPanel.incrementGeneration();
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Automata");
        CellGUI life = new CellGUI();
        ButtonPanel buttonPanel = new ButtonPanel(life);
        life.passPanel(buttonPanel);
        frame.add(buttonPanel, BorderLayout.WEST);
        frame.add(life);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
    }
    
    private class TimeListener implements ActionListener {
    
        /**
        * Required actionPerformed method
        *
        * @param e The ActionEvent that happens with each action
        */
        public void actionPerformed(ActionEvent e) {
            gen.update();
            repaint();
            System.out.println("Update");
            incrementGeneration();
        }
    }
    
    private class ClickListener extends MouseAdapter {

        /**
        * Required mouseDragged method
        *
        * @param e The MouseEvent that happens with a drag of the Mouse in
        * the Cel Panel
        */
        public void mouseDragged(MouseEvent e) {
            Point point = e.getPoint();
            int x = ((int) point.getX()) / Cell.WIDTH;
            int y = ((int) point.getY()) / Cell.HEIGHT;
            gen.modifyCell(x, y, true);
            repaint();
        }
        
        /**
        * Required mousePressed method
        *
        * @param e The MouseEvent that happens with a press of the Mouse in
        * the Cel Panel
        */
        public void mousePressed(MouseEvent e) {
            Point point = e.getPoint();
            int x = ((int) point.getX()) / Cell.WIDTH;
            int y = ((int) point.getY()) / Cell.HEIGHT;
            gen.toggleCell(x, y);
            repaint();
        }
    } 
}