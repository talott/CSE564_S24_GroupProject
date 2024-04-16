import javax.swing.*;
import java.awt.*;

public class Main {
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Simulation UI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(700, 700));

        SimulationUI simulationUI = new SimulationUI();
        frame.getContentPane().add(simulationUI.parent);

        frame.pack();
        frame.setVisible(true);

        simulationUI.update();
    }

    public static void main(String[] args) {
        new SimulationUI();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
