import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class SimulationUI {
    private ArrayList<SimulationTank> waterTanks = new ArrayList<>(Arrays.asList(
        new SimulationTank(),
        new SimulationTank(),
        new SimulationTank(),
        new SimulationTank()
    ));

    private ArrayList<SimulationTank> fertilizerTanks = new ArrayList<>(Arrays.asList(
        new SimulationTank(),
        new SimulationTank(),
        new SimulationTank(),
        new SimulationTank()
    ));

    public ControlUnit controlUnit = new ControlUnit();

    public JPanel parent;

    private JPanel waterTankList;
    private JPanel waterTankLabels;
    private JPanel fertilizerTankList;
    private JPanel fertilizerTankLabels;
    private JPanel moistureControllerList;
    private JPanel fertilizerControllerList;
    private JLabel lightControllerStatus;

    public SimulationUI() {
        waterTankList.setLayout(new GridLayout(1, 0, 10, 10));
        waterTankLabels.setLayout(new GridLayout(1, 0, 10, 10));

        fertilizerTankList.setLayout(new GridLayout(1, 0, 10, 10));
        fertilizerTankLabels.setLayout(new GridLayout(1, 0, 10, 10));

        moistureControllerList.setLayout(new GridLayout(0, 1, 10, 10));
        fertilizerControllerList.setLayout(new GridLayout(0, 1, 10, 10));
    }

    public void update() {
        controlUnit.controlUnit();

        updateWaterTankList();
        updateFertilizerTankList();
        updateMoistureControllerList();
        updateNutrientControllerList();
        updateLightController();
    }

    private void updateWaterTankList() {
        waterTankList.removeAll();

        for (int i = 0; i < waterTanks.size(); i++) {
            SimulationTank waterTank = waterTanks.get(i);

            JProgressBar progressBar = new JProgressBar();
            progressBar.setOrientation(SwingConstants.VERTICAL);
            progressBar.setValue((int)(waterTank.available * 100.0));
            progressBar.setMaximum(100);

            waterTankList.add(progressBar);
            waterTankList.validate();
            waterTankList.repaint();

            waterTankLabels.add(new JLabel("Water tank #" + (i + 1)));
        }
    }

    private void updateFertilizerTankList() {
        fertilizerTankList.removeAll();

        for (int i = 0; i < waterTanks.size(); i++) {
            SimulationTank waterTank = waterTanks.get(i);

            JProgressBar progressBar = new JProgressBar();
            progressBar.setOrientation(SwingConstants.VERTICAL);
            progressBar.setValue((int)(waterTank.available * 100.0));
            progressBar.setMaximum(100);

            fertilizerTankList.add(progressBar);
            fertilizerTankList.validate();
            fertilizerTankList.repaint();

            fertilizerTankLabels.add(new JLabel("Fertilizer tank #" + (i + 1)));
        }
    }

    private void updateMoistureControllerList() {
        for (int i = 0; i < controlUnit.moistureControllers.size(); i++) {
            MoistureController controller = controlUnit.moistureControllers.get(i);

            moistureControllerList.add(
                new JLabel("Moisture Controller Pump #" + (i + 1) + ": " + (controller.isPumpActive() ? "Active" : "Inactive"))
            );
        }

        moistureControllerList.validate();
        moistureControllerList.repaint();
    }

    private void updateNutrientControllerList() {
        for (int i = 0; i < controlUnit.nutrientControllers.size(); i++) {
            NutrientController controller = controlUnit.nutrientControllers.get(i);

            int activePump = controller.getPumpActive();
            String pumpText = "";
            if (activePump == -1) {
                pumpText = "Inactive";
            } else {
                pumpText = "#" + activePump + " active";
            }

            fertilizerControllerList.add(
                new JLabel("Nutrient Controller Pump #" + (i + 1) + ": " + pumpText)
            );
        }

        fertilizerControllerList.validate();
        fertilizerControllerList.repaint();
    }

    private void updateLightController() {
        lightControllerStatus.setText("Light controller: " + (controlUnit.lightController.isLightsOn ? "Active" : "Inactive"));
    }
}
