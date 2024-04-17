import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ControlUnit {
    public List<MoistureController> moistureControllers;
    public List<NutrientController> nutrientControllers;
    public LightController lightController;
    public NotificationController notificationController = new NotificationController();

    private double desiredLightHours = 0.5;

    public ControlUnit() {
        moistureControllers = new ArrayList<>(Arrays.asList(
            new MoistureController(notificationController, 0.0, 0),
            new MoistureController(notificationController, 0.1, 0),
            new MoistureController(notificationController, 0.2, 0),
            new MoistureController(notificationController, 0.3, 1),
            new MoistureController(notificationController, 0.4, 1)
        ));
        nutrientControllers = new ArrayList<>(Arrays.asList(
            new NutrientController(notificationController, 0.1, 0),
            new NutrientController(notificationController, 0.2, 1),
            new NutrientController(notificationController, 0.3, 2),
            new NutrientController(notificationController, 0.4, 3),
            new NutrientController(notificationController, 0.5, 4),
            new NutrientController(notificationController, 0.6, 5),
            new NutrientController(notificationController, 0.7, 6)
        ));

        lightController = new LightController(desiredLightHours);
    }

    public void controlUnit() {
        double[] moistureSensorReadings = {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
        double[] electricalConductivitySensorReadings = {3, 3, 3, 3, 3, 3, 3};
        double[] lightLuxValueReadings = {100, 102, 95, 65, 74, 99, 105};

        List<Double> plantWaterOutput = new ArrayList<>();
        List<Double> plantFormulaOutput = new ArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(moistureControllers.size() + nutrientControllers.size() + 1);

        // Moisture control round
        for (MoistureController moistureController : moistureControllers) {
            executor.submit(() -> {
                moistureController.checkWaterAvailable();
                moistureController.round();
            });
            
        }

        // Nutrient control round
        for (NutrientController nutrientController : nutrientControllers) {
            executor.submit(() -> {
                nutrientController.checkNutrientsAvailable();
                nutrientController.round();
            });
        }

        // Light control round
        executor.submit(() -> {
            lightController.round();
        });

        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void changeSettings() {
        // Somehow change the settings, along with verification that the settings are correct
    }
}