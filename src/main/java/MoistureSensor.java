import java.util.Random;

public class MoistureSensor implements ISensor {

	private double value = new Random().nextDouble();

	//Fake Sensor Inputs
	private final double[][] values = {
			{0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0}, // Array 1
			{0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 0.1}, // Array 2
			{0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 0.1, 0.2}, // Array 3
			{0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 0.1, 0.2, 0.3}, // Array 4
			{0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 0.1, 0.2, 0.3, 0.4}  // Array 5
	};

	private int timesRead = 0;


	//Randomly Assign Sensor input Array
	private final int id = new Random().nextInt(5) + 1;
	private final double[] selectedArray = values[id - 1];

	public double read() { // TODO tie into greenhouse simulation
		timesRead++;
		/*if(timesRead == values.length) {
			timesRead = 1;
		}*/
		return value;//selectedArray[timesRead - 1];
	}

	//Used to reflect the increase in moisture when pump is active
	public void incrementValue()
	{
		value+=0.1;
	}

	public void decrementValue()
	{
		if (new Random().nextBoolean() && value != 0.0)
		{
			value-=0.1;
		}

	}
}
