import java.util.HashMap;
import java.util.Map;

public class NotificationController {
    private final HashMap<Integer, Boolean> waterWarningsIssued = new HashMap<>();
    private final HashMap<Integer, Boolean> fertilizerWarningsIssued = new HashMap<>();

    public void waterReservoirWarning(int reservoirIndex, double percentLeft) {
        if (waterWarningsIssued.containsKey(reservoirIndex)) {
            return;
        }

        waterWarningsIssued.putIfAbsent(reservoirIndex, true);

        String message = "Water is not available in tank #" + reservoirIndex;
        System.out.println(message);

        if (SimulationUI.simulationUI != null) {
            SimulationUI.simulationUI.addNotification(message);
        }
    }

    public void resetWaterReservoirWarning(int reservoirIndex) {
        waterWarningsIssued.remove(reservoirIndex);
    }

    public void fertilizerReservoirWarning(int reservoirIndex, double percentLeft) {
        if (fertilizerWarningsIssued.containsKey(reservoirIndex)) {
            return;
        }

        fertilizerWarningsIssued.putIfAbsent(reservoirIndex, true);

        String message = "Fertilizer is not available in tank #" + reservoirIndex;
        System.out.println(message);

        if (SimulationUI.simulationUI != null) {
            SimulationUI.simulationUI.addNotification(message);
        }
    }

    public void resetFertilizerReservoirWarning(int reservoirIndex) {
        fertilizerWarningsIssued.remove(reservoirIndex);
    }

    public void plantNeedsFertilizerWarning() {
        String message = "Fertilizer is needed by controller #TODO";
        System.out.println(message);

        if (SimulationUI.simulationUI != null) {
            SimulationUI.simulationUI.addNotification(message);
        }
    }
}
