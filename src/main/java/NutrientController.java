public class NutrientController {
	private static final int MAX_PUMPS = 6;

	private int desiredFormula;

	private double desiredEC;

	private NutrientPump[] pumps = {
			new NutrientPump(0),
			new NutrientPump(1),
			new NutrientPump(2),
			new NutrientPump(3),
			new NutrientPump(4),
			new NutrientPump(5)
	};

	private NutrientSensor sensor = new NutrientSensor();
	private final NotificationController notificationController;
	private boolean issuedWarning = false;

	public NutrientController(NotificationController notificationController, double desiredEC, int desiredFormula) {
		this.desiredEC = desiredEC;
		this.desiredFormula = desiredFormula;
		this.notificationController = notificationController;
	}

	/**
	 * If the sensor reports that the plants need nutrients, then the controller will activate the plants' desired NutrientPump. If the plants' need nutrients and their desired formula is not available from one of the NutrientPumps, then a notification is issued to the user to remind them to fertilize their plants.
	 */
	public void round() {
		if (sensor.read() < desiredEC && desiredFormula < MAX_PUMPS && pumps[desiredFormula].getFluidAvailable() > 0.01) {
			pumps[desiredFormula].start();
			sensor.incrementValue();
		} else if (sensor.read() >= desiredEC && desiredFormula < MAX_PUMPS) {
			pumps[desiredFormula].stop();
			sensor.decrementValue();
		} else if (desiredFormula >= MAX_PUMPS && sensor.read() < desiredEC) {
			if (issuedWarning) {
				notificationController.plantNeedsFertilizerWarning();
				issuedWarning = true;
			}
			sensor.decrementValue();
		} else if (desiredFormula >= MAX_PUMPS) {
			issuedWarning = false;
		}
	}

	public void checkNutrientsAvailable() {
		if (desiredFormula >= MAX_PUMPS || pumps[desiredFormula].getFluidAvailable() > 0.01) {
			notificationController.resetFertilizerReservoirWarning(desiredFormula);
			return;
		}

		// issue warning if we're out of nutrients
		notificationController.fertilizerReservoirWarning(desiredFormula, 0.0);
	}

	// FOR USE IN SIMULATION ONLY
	public int getPumpActive() {
		for (int i = 0; i < pumps.length; i++) {
			if (pumps[i].isRunning) {
				return i;
			}
		}

		return -1;
	}

	// FOR USE IN SIMULATION ONLY
	public double getSensor() {
		return sensor.read();
	}

	// FOR USE IN SIMULATION ONLY
	public double getCapacity() {
		return pumps[desiredFormula].getFluidAvailable();
	}

	// FOR USE IN SIMULATION ONLY
	public int getTankIndex() {
		return desiredFormula;
	}
}
