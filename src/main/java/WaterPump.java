import java.io.*;

public class WaterPump implements IPump {
	/**
	 * The tank the pump is pulling water from.
	 */
	private int tankIndex;

	// FOR USE IN SIMULATION ONLY
	public boolean isRunning = false;

	public void start() { // TODO tie into greenhouse simulation
		System.out.println("Started water pump #" + tankIndex);
		isRunning = true;
	}

	public void stop() { // TODO tie into greenhouse simulation
		System.out.println("Stopped water pump #" + tankIndex);
		isRunning = false;
	}

	public boolean isFluidAvailable() { // TODO track water remaining
		return true;
	}
}
