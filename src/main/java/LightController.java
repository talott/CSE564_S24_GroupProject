import java.util.ArrayList;
import java.util.List;

public class LightController {
    private double desiredLightLevel;
    private List<Light> lights;
    private LightSensor lightSensor;

    public LightController(double desiredLightLevel) {
        this.desiredLightLevel = desiredLightLevel;
        this.lights = new ArrayList<>();
        this.lightSensor = new LightSensor();
    }

    public void addLight(Light light) {
        lights.add(light);
    }

    private void controlLights() {
        lightSensor.incrementTimesRead();

        double currentLightLevel = lightSensor.read();
        if (currentLightLevel < desiredLightLevel) {
            // If current light level is below desired, turn on lights
            turnOnLights();
        } else if (currentLightLevel > desiredLightLevel){
            // If current light level is equal or above desired, turn off lights
            turnOffLights();
        }
    }

    public void round() {
        controlLights();
	}

    private void turnOnLights() {
        int index = 0;
        double currentLightLevel = lightSensor.read();

        while (currentLightLevel < desiredLightLevel && index < lights.size()) {
            Light light = lights.get(index);
            if (!light.isOn()) {
                light.turnOn();
                lightSensor.numberOfLightsActive++;
                currentLightLevel = lightSensor.read();
            }
            index++;
        }
    }

    private void turnOffLights() {
        int index = 0;
        double currentLightLevel = lightSensor.read();

        while (index < lights.size() && currentLightLevel > desiredLightLevel) {
            Light light = lights.get(index);
            if (light.isOn()) {
                light.turnOff();
                lightSensor.numberOfLightsActive--;
                currentLightLevel = lightSensor.read();
            }
            index++;
        }
    }

    // FOR USE IN SIMULATION ONLY
    public double getLux() {
        return lightSensor.read();
    }

    // FOR USE IN SIMULATION ONLY
    public int getNumberOfLightsActive() {
        return lightSensor.numberOfLightsActive;
    }
}