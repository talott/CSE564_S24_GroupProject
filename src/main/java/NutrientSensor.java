import java.util.Random;

public class NutrientSensor implements ISensor {

	private double value = new Random().nextDouble();

	//Randomly Assign Sensor input Array
	private final int id = new Random().nextInt(5) + 1;

	public double read() { // TODO tie into greenhouse simulation

		return value;//selectedArray[timesRead - 1];
	}

	//Used to reflect the increase in moisture when pump is active
	public void incrementValue()
	{
		value+=0.1;
	}

	public void decrementValue()
	{
		if(value<0)
		{
			value=0;
		}
		if (new Random().nextBoolean() && value >= 0.1)
		{
			value-=0.1;
		}

	}

}
