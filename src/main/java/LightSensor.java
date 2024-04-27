import java.util.Random;

public class LightSensor implements ISensor {
    private double[] baseValues = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 0.9, 0.8, 0.7, 0.6, 0.5, 0.4, 0.3, 0.2};

    // FOR USE IN SIMULATION ONLY
    public int numberOfLightsActive = 0;

    private int timesRead = 0;
    @Override
    public double read() {
        return Math.min(baseValues[timesRead % baseValues.length] + (double)numberOfLightsActive * 0.1, 1.0);
    }

    // FOR USE IN SIMULATION ONLY
    public void incrementTimesRead() {
        timesRead++;
    }
}