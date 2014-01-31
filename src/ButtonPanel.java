import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
* @author Graham Wright
*
* The Button Panel for the game of life
*/
public class ButtonPanel extends JPanel {
    
    private CellGUI life;
    private JButton toggleStart;
    private JLabel generationLabel;
    private int generation;

    /**
    * Constructor for the Button Panel
    *
    * @param life The CellGUI for the Game of life
    */
    public ButtonPanel(CellGUI life) {
        generation = 0;
        this.life = life;
        toggleStart = new JButton("Stop");
        toggleStart.addActionListener(new ToggleStartListener());
        add(toggleStart);
        JButton clear = new JButton("Clear");
        clear.addActionListener(new ClearListener());
        add(clear);
        setPreferredSize(new Dimension(150, 1000));
        generationLabel = new JLabel("Generation Number: 0");
        add(generationLabel);
    }
    
    public void incrementGeneration() {
        generation++;
        generationLabel.setText("Generation Number: " + generation);
    }
    
    private class ToggleStartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (life.toggleTimer()) {
                toggleStart.setText("Stop");
            } else {
                toggleStart.setText("Start");
            }
        }
    }
    
    private class ClearListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            life.clear();
        }
    }
}