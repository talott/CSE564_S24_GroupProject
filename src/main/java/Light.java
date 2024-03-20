public class Light {
    private boolean isOn;

    public Light() {
        this.isOn = false; // Lights are initially off
    }

    public boolean isOn() {
        return isOn;
    }

    public void turnOn() {
        isOn = true;
        // Implementation to turn on the light
    }

    public void turnOff() {
        isOn = false;
        // Implementation to turn off the light
    }
}