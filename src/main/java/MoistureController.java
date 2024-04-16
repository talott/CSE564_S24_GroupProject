import java.awt.event.MouseAdapter;

public class MoistureController {
	private final double desiredMoisture;

	private final WaterPump pump = new WaterPump();
	private final MoistureSensor sensor = new MoistureSensor();
	private boolean issuedWarning = false;

	public MoistureController(double desiredMoisture) {
		this.desiredMoisture = desiredMoisture;
	}

	public void round() {
		if (sensor.read() < desiredMoisture && pump.isFluidAvailable()) {
			pump.start();
		} else {
			pump.stop();
		}
	}

	/**
	 * Returns true if the moisture control has water available to water plants. If water is not available, then the MoistureController will issue a notification to the user reminding them to fill their water tanks.
	 */
	public void checkWaterAvailable() {
		if (pump.isFluidAvailable()) {
			issuedWarning = false;
			return;
		}

		// issue warning if we're out of water
		if (!issuedWarning) { // TODO emit notification event
			System.out.println("Water is not available.");
			issuedWarning = true;
		}
	}

	// FOR USE IN SIMULATION ONLY
	public boolean isPumpActive() {
		return pump.isRunning;
	}
}
