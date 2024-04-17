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
            new NutrientController(notificationController, 0.2, 6)
        ));

        lightController = new LightController(desiredLightHours);
    }

    public void controlUnit() {

        ExecutorService executor = Executors.newFixedThreadPool(moistureControllers.size() + nutrientControllers.size() + 1);

        // Moisture control round
        for (MoistureController moistureController : moistureControllers) {
            executor.submit(() -> {
                moistureController.round();
                moistureController.checkWaterAvailable();
            });
            
        }

        // Nutrient control round
        for (NutrientController nutrientController : nutrientControllers) {
            executor.submit(() -> {
                nutrientController.round();
                nutrientController.checkNutrientsAvailable();
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