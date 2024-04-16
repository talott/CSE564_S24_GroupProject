public interface IPump {

	/**
	 * Starts pumping fluid.
	 */
	public abstract void start();

	/**
	 * Stops pumping fluid.
	 */
	public abstract void stop();

	/**
	 * Returns the percentage of the fluid that is available to the pump.
	 */
	public abstract double getFluidAvailable();

}
