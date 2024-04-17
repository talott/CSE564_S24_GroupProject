import java.util.concurrent.ConcurrentHashMap;

public class WaterPump implements IPump {
	/**
	 * The tank the pump is pulling water from.
	 */
	private final int tankIndex;

	// FOR USE IN SIMULATION ONLY
	public boolean isRunning = false;

	// FOR USE IN SIMULATION ONLY
	private static ConcurrentHashMap<Integer, Double> fluidAvailable = new ConcurrentHashMap<>();

	public WaterPump(int tankIndex) {
		this.tankIndex = tankIndex;
	}

	public void start() { // TODO tie into greenhouse simulation
		if (!isRunning) {
			System.out.println("Started water pump #" + tankIndex); // TODO remove print statement?
		}

		fluidAvailable.put(tankIndex, fluidAvailable.getOrDefault(tankIndex, 1.0) - 0.1);

		isRunning = true;
	}

	public void stop() { // TODO tie into greenhouse simulation
		if (isRunning) {
			System.out.println("Stopped water pump #" + tankIndex); // TODO remove print statement?
		}

		isRunning = false;
	}

	public double getFluidAvailable() { // TODO track water remaining
		return fluidAvailable.getOrDefault(tankIndex, 1.0);
	}
}
