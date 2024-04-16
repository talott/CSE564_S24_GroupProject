import javax.swing.*;
import java.awt.*;

public class Main {
    private static javax.swing.Timer updateTimer = null;

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Simulation UI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(700, 700));

        SimulationUI simulationUI = new SimulationUI();
        frame.getContentPane().add(simulationUI.parent);

        frame.pack();
        frame.setVisible(true);

        simulationUI.update();

        updateTimer = new Timer(1000, e -> {
            simulationUI.update();
        });
        updateTimer.start();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
