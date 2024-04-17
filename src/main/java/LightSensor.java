public class LightSensor implements ISensor {
    private double[] values = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};

    private int timesRead = 0;
    @Override
    public double read() {
        // Implementation to read the amount of uv light (lux) in the room
        // Return a dummy value for demonstration purposes
        timesRead++;
        if(timesRead == values.length) {
            timesRead = 1;
        }
        return values[timesRead - 1];
    }
}