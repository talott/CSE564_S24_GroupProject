import java.util.ArrayList;
import java.util.List;

public class ControlUnit {
    private List<MoistureController> moistureControllers;
    private List<NutrientController> nutrientControllers;
    private LightController lightController;
    private int desiredLightHours;

    public ControlUnit() {
        moistureControllers = new ArrayList<>();
        nutrientControllers = new ArrayList<>();
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