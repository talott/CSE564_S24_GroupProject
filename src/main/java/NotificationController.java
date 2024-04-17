import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NotificationController {
    private final ConcurrentHashMap<Integer, Boolean> waterWarningsIssued = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, Boolean> fertilizerWarningsIssued = new ConcurrentHashMap<>();

    public synchronized void waterReservoirWarning(int reservoirIndex, double percentLeft) {
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

    public synchronized void fertilizerReservoirWarning(int reservoirIndex, double percentLeft) {
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

    public synchronized void plantNeedsFertilizerWarning() {
        String message = "Fertilizer is needed by controller #TODO";
        System.out.println(message);

        if (SimulationUI.simulationUI != null) {
            SimulationUI.simulationUI.addNotification(message);
        }
    }
}
