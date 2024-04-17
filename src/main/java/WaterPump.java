public class WaterPump implements IPump {
	/**
	 * The tank the pump is pulling water from.
	 */
	private final int tankIndex;

	// FOR USE IN SIMULATION ONLY
	public boolean isRunning = false;

	private double fluidAvailable = 1.0;

	public WaterPump(int tankIndex) {
		this.tankIndex = tankIndex;
	}

	public void start() { // TODO tie into greenhouse simulation
		if (!isRunning) {
			System.out.println("Started water pump #" + tankIndex); // TODO remove print statement?
		}
		fluidAvailable -= 0.1;
		isRunning = true;
	}

	public void stop() { // TODO tie into greenhouse simulation
		if (isRunning) {
			System.out.println("Stopped water pump #" + tankIndex); // TODO remove print statement?
		}

		isRunning = false;
	}

	public double getFluidAvailable() { // TODO track water remaining
		return fluidAvailable;
	}
}
