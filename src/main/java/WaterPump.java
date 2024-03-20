import java.io.*;

public class WaterPump implements IPump {
	/**
	 * The tank the pump is pulling water from.
	 */
	private int tankIndex;

	public void start() { // TODO tie into greenhouse simulation
		System.out.println("Started water pump #" + tankIndex);
	}

	public void stop() { // TODO tie into greenhouse simulation
		System.out.println("Stopped water pump #" + tankIndex);
	}

	public boolean isFluidAvailable() { // TODO track water remaining
		return true;
	}
}
