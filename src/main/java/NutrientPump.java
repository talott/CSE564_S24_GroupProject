import java.util.concurrent.ConcurrentHashMap;

public class NutrientPump {
	public int formulaIndex;

	// FOR USE IN SIMULATION ONLY
	public boolean isRunning = false;

	// FOR USE IN SIMULATION ONLY
	private static ConcurrentHashMap<Integer, Double> fluidAvailable = new ConcurrentHashMap<>();

	public NutrientPump(int formulaIndex) {this.formulaIndex = formulaIndex;}

	public void start() {
		if (!isRunning) {
			System.out.println("Started nutrient pump #" + formulaIndex); // TODO remove print statement?
		}

		fluidAvailable.put(formulaIndex, fluidAvailable.getOrDefault(formulaIndex, 1.0) - 0.1);

		isRunning = true;
	}

	public void stop() {
		if (isRunning) {
			System.out.println("Stopped nutrient pump #" + formulaIndex); // TODO remove print statement?
		}

		isRunning = false;

	}

	public double getFluidAvailable() { // TODO track water remaining
		return fluidAvailable.getOrDefault(formulaIndex, 1.0);
	}

}
