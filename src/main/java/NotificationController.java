import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NotificationController {
    private final ConcurrentHashMap<Integer, Boolean> waterWarningsIssued = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, Boolean> fertilizerWarningsIssued = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, Boolean> needsManualFertilizingWarningsIssued = new ConcurrentHashMap<>();

    public synchronized void waterReservoirWarning(int reservoirIndex) {
        if (waterWarningsIssued.containsKey(reservoirIndex)) {
            return;
        }

        waterWarningsIssued.putIfAbsent(reservoirIndex, true);

        String message = "Water is not available in tank #" + (reservoirIndex + 1);
        System.out.println(message);

        if (SimulationUI.simulationUI != null) {
            SimulationUI.simulationUI.addNotification(message);
        }
    }

    public synchronized void resetWaterReservoirWarning(int reservoirIndex) {
        waterWarningsIssued.remove(reservoirIndex);
    }

    public synchronized void fertilizerReservoirWarning(int reservoirIndex) {
        if (fertilizerWarningsIssued.containsKey(reservoirIndex)) {
            return;
        }

        fertilizerWarningsIssued.putIfAbsent(reservoirIndex, true);

        String message = "Fertilizer is not available in tank #" + (reservoirIndex + 1);
        System.out.println(message);

        if (SimulationUI.simulationUI != null) {
            SimulationUI.simulationUI.addNotification(message);
        }
    }

    public synchronized void resetFertilizerReservoirWarning(int reservoirIndex) {
        fertilizerWarningsIssued.remove(reservoirIndex);
    }

    public synchronized void plantNeedsManualFertilizing(int nutrientControllerIndex) {
        if (needsManualFertilizingWarningsIssued.containsKey(nutrientControllerIndex)) {
            return;
        }

        needsManualFertilizingWarningsIssued.putIfAbsent(nutrientControllerIndex, true);

        String message = "Fertilizer is needed by controller #" + nutrientControllerIndex;
        System.out.println(message);

        if (SimulationUI.simulationUI != null) {
            SimulationUI.simulationUI.addNotification(message);
        }
    }

    public synchronized void resetPlantNeedsManualFertilizing(int nutrientControllerIndex) {
        needsManualFertilizingWarningsIssued.remove(nutrientControllerIndex);
    }
}
