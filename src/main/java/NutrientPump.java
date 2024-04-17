public class NutrientPump {
	private int formulaIndex;

	// FOR USE IN SIMULATION ONLY
	public boolean isRunning = false;

	private double fluidAvailable = 1.0;

	public NutrientPump(int formulaIndex) {this.formulaIndex = formulaIndex;}

	public void start() {
		if (!isRunning) {
			System.out.println("Started water pump #" + formulaIndex); // TODO remove print statement?
		}
		fluidAvailable -= 0.1;
		isRunning = true;
	}

	public void stop() {
		if (isRunning) {
			System.out.println("Stopped water pump #" + formulaIndex); // TODO remove print statement?
		}

		isRunning = false;

	}

	public double getFluidAvailable() {
		return fluidAvailable;
	}

}
