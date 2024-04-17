import javax.swing.*;
import java.awt.*;
import java.util.*;


public class SimulationUI {
    public static SimulationUI simulationUI;

    public final ControlUnit controlUnit;

    public JPanel parent;

    private JPanel waterTankList;
    private JPanel waterTankLabels;
    private JPanel fertilizerTankList;
    private JPanel fertilizerTankLabels;
    private JPanel moistureControllerList;
    private JPanel fertilizerControllerList;
    private JLabel lightControllerStatus;
    private JTextPane notificationArea;
    private String notificationMessages = "";

    public SimulationUI() {
        simulationUI = this;

        controlUnit = new ControlUnit();

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

    public void addNotification(String message) {
        notificationMessages += message + "\n";
        notificationArea.setText(notificationMessages);
        notificationArea.validate();
        notificationArea.repaint();
    }

    private void updateWaterTankList() {
        waterTankList.removeAll();
        waterTankLabels.removeAll();

        Map<Integer, Double> capacityMapping = new TreeMap<>();

        for (MoistureController controller: controlUnit.moistureControllers) {
            capacityMapping.putIfAbsent(controller.getTankIndex(), controller.getCapacity());
        }

        for (Map.Entry<Integer, Double> entry: capacityMapping.entrySet()) {
            JProgressBar progressBar = new JProgressBar();
            progressBar.setOrientation(SwingConstants.VERTICAL);
            progressBar.setValue((int)(entry.getValue() * 100.0));
            progressBar.setMaximum(100);

            waterTankList.add(progressBar);
            waterTankLabels.add(new JLabel("Water tank #" + (entry.getKey() + 1)));
        }

        waterTankList.validate();
        waterTankList.repaint();
        waterTankLabels.validate();
        waterTankLabels.repaint();
    }

    private void updateFertilizerTankList() {
        fertilizerTankList.removeAll();
        fertilizerTankLabels.removeAll();

        Map<Integer, Double> capacityMapping = new TreeMap<>();

        for (NutrientController controller: controlUnit.nutrientControllers) {
            if (controller.getTankIndex() >= 6) {
                continue;
            }

            capacityMapping.putIfAbsent(controller.getTankIndex(), controller.getCapacity());
        }

        for (Map.Entry<Integer, Double> entry: capacityMapping.entrySet()) {
            JProgressBar progressBar = new JProgressBar();
            progressBar.setOrientation(SwingConstants.VERTICAL);
            progressBar.setValue((int)(entry.getValue() * 100.0));
            progressBar.setMaximum(100);

            fertilizerTankList.add(progressBar);
            fertilizerTankLabels.add(new JLabel("Nutrient tank #" + (entry.getKey() + 1)));
        }

        fertilizerTankList.validate();
        fertilizerTankList.repaint();
        fertilizerTankLabels.validate();
        fertilizerTankLabels.repaint();
    }

    private void updateMoistureControllerList() {
        moistureControllerList.removeAll();

        for (int i = 0; i < controlUnit.moistureControllers.size(); i++) {
            MoistureController controller = controlUnit.moistureControllers.get(i);

            moistureControllerList.add(
                new JLabel("Moisture Controller #" + (i + 1) + ": "
                        + "Sensor reads " + (int)(controller.getSensor() * 100) + "%, "
                    + (controller.isPumpActive() ? "Pump active" : "Pump inactive")
                )
            );
        }

        moistureControllerList.validate();
        moistureControllerList.repaint();
    }

    private void updateNutrientControllerList() {
        fertilizerControllerList.removeAll();

        for (int i = 0; i < controlUnit.nutrientControllers.size(); i++) {
            NutrientController controller = controlUnit.nutrientControllers.get(i);

            int activePump = controller.getPumpActive();
            String pumpText;
            if (activePump == -1) {
                pumpText = "Pump inactive";
            } else {
                pumpText = "Pump #" + activePump + " active";
            }

            fertilizerControllerList.add(
                new JLabel("Nutrient Controller Pump #" + (i + 1) + ": "
                    + "Sensor reads " + (int)(controller.getSensor() * 100) + "%, "
                    + pumpText
                )
            );
        }

        fertilizerControllerList.validate();
        fertilizerControllerList.repaint();
    }

    private void updateLightController() {
        lightControllerStatus.setText("Light controller: " + (controlUnit.lightController.isLightsOn ? "Active" : "Inactive"));
    }
}
