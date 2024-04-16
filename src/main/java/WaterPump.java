import java.io.*;

public class WaterPump implements IPump {
	/**
	 * The tank the pump is pulling water from.
	 */
	private final int tankIndex;

	// FOR USE IN SIMULATION ONLY
	public boolean isRunning = false;

	public WaterPump(int tankIndex) {
		this.tankIndex = tankIndex;
	}

	public void start() { // TODO tie into greenhouse simulation
		if (!isRunning) {
			System.out.println("Started water pump #" + tankIndex); // TODO remove print statement?
		}

		isRunning = true;
	}

	public void stop() { // TODO tie into greenhouse simulation
		if (isRunning) {
			System.out.println("Stopped water pump #" + tankIndex); // TODO remove print statement?
		}

		isRunning = false;
	}

	public boolean isFluidAvailable() { // TODO track water remaining
		return false;
	}
}
