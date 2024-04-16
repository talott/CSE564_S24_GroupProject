public class NutrientPump {
	private int formulaIndex;

	// FOR USE IN SIMULATION ONLY
	public boolean isRunning = false;

	public void start() {
		isRunning = true;
	}

	public void stop() {
		isRunning = false;
	}

	public boolean isFluidAvailable() {
		return true;
	}

}
