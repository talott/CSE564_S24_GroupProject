public class NutrientController {
	private static final int MAX_PUMPS = 6;

	private int desiredFormula;

	private double desiredEC;

	private NutrientPump[] pumps = {
			new NutrientPump(),
			new NutrientPump(),
			new NutrientPump(),
			new NutrientPump(),
			new NutrientPump(),
			new NutrientPump()
	};

	private NutrientSensor sensor;

	private boolean issuedWarning = false;

	/**
	 * If the sensor reports that the plants need nutrients, then the controller will activate the plants' desired NutrientPump. If the plants' need nutrients and their desired formula is not available from one of the NutrientPumps, then a notification is issued to the user to remind them to fertilize their plants.
	 */
	public void round() {
		if (sensor.read() < desiredEC && desiredFormula < MAX_PUMPS && pumps[desiredFormula].isFluidAvailable()) {
			pumps[desiredFormula].start();
		} else if (sensor.read() >= desiredEC && desiredFormula < MAX_PUMPS) {
			pumps[desiredFormula].stop();
		} else if (desiredFormula >= MAX_PUMPS) { // TODO emit notification event
			System.out.println("Please supply plant with nutrients.");
		}
	}

	public void checkNutrientsAvailable() {
		if (desiredFormula >= MAX_PUMPS || pumps[desiredFormula].isFluidAvailable()) {
			issuedWarning = false;
			return;
		}

		// issue warning if we're out of nutrients
		if (!issuedWarning) { // TODO emit notification event
			System.out.println("Nutrients are not available.");
			issuedWarning = true;
		}
	}
}
