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
	 * Returns true if there's enough fluid available to start pumping.
	 */
	public abstract boolean isFluidAvailable();

}
