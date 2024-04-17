import java.awt.event.MouseAdapter;

public class MoistureController {
	private final double desiredMoisture;

	private final WaterPump pump;
	private final MoistureSensor sensor = new MoistureSensor();
	private final NotificationController notificationController;
	private final int tankIndex;

	public MoistureController(NotificationController notificationController, double desiredMoisture, int tankIndex) {
		this.pump = new WaterPump(tankIndex);
		this.desiredMoisture = desiredMoisture;
		this.notificationController = notificationController;
		this.tankIndex = tankIndex;
	}

	public void round() {
		if (sensor.read() < desiredMoisture && pump.getFluidAvailable() > 0.01) {
			pump.start();
			sensor.incrementValue();
		} else {
			pump.stop();
			sensor.decrementValue();
		}
	}

	/**
	 * Returns true if the moisture control has water available to water plants. If water is not available, then the MoistureController will issue a notification to the user reminding them to fill their water tanks.
	 */
	public void checkWaterAvailable() {
		if (pump.getFluidAvailable() > 0.01) {
			notificationController.resetWaterReservoirWarning(tankIndex);
			return;
		}

		notificationController.waterReservoirWarning(tankIndex, 0.0);
	}

	// FOR USE IN SIMULATION ONLY
	public boolean isPumpActive() {
		return pump.isRunning;
	}

	// FOR USE IN SIMULATION ONLY
	public double getSensor() {
		return sensor.read();
	}

	// FOR USE IN SIMULATION ONLY
	public double getCapacity() {
		return pump.getFluidAvailable();
	}

	// FOR USE IN SIMULATION ONLY
	public int getTankIndex() {
		return tankIndex;
	}
}
