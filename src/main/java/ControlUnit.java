import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControlUnit {
    public List<MoistureController> moistureControllers;
    public List<NutrientController> nutrientControllers;
    public LightController lightController;
    private int desiredLightHours = 0;

    public ControlUnit() {
        moistureControllers = new ArrayList<>(Arrays.asList(
            new MoistureController(0.0),
            new MoistureController(0.1),
            new MoistureController(0.2),
            new MoistureController(0.3),
            new MoistureController(0.4)
        ));
        nutrientControllers = new ArrayList<>(Arrays.asList(
            new NutrientController(0.1, 0),
            new NutrientController(0.2, 1),
            new NutrientController(0.3, 2),
            new NutrientController(0.4, 3),
            new NutrientController(0.5, 4),
            new NutrientController(0.6, 5),
            new NutrientController(0.7, 6)
        ));

        lightController = new LightController(desiredLightHours);
    }

    public void controlUnit() {
        double[] moistureSensorReadings = {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
        double[] electricalConductivitySensorReadings = {3, 3, 3, 3, 3, 3, 3};
        double[] lightLuxValueReadings = {100, 102, 95, 65, 74, 99, 105};

        List<Double> plantWaterOutput = new ArrayList<>();
        List<Double> plantFormulaOutput = new ArrayList<>();

        // Moisture control round
        for (MoistureController moistureController : moistureControllers) {
            moistureController.round();
        }

        // Nutrient control round
        for (NutrientController nutrientController : nutrientControllers) {
            nutrientController.round();
        }

        // Light control round
        
        lightController.round();
    }

    public void changeSettings() {
        // Somehow change the settings, along with verification that the settings are correct
    }

    public void waterReservoirWarning(int reservoirIndex, double percentLeft) {
        // Implementation of water reservoir warning
        //May be placed inside the Moisture Controller instead
    }

    public void fertilizerReservoirWarning(int reservoirIndex, double percentLeft) {
        // Implementation of fertilizer reservoir warning
        //May be placed inside the Nutrient Controller instead
    }
}