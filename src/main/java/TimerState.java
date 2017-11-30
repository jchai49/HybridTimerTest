package TimerTest;

import edu.ucsc.cross.hse.core.object.ObjectSet;

public class TimerState extends ObjectSet
{

	public double i; // the number of jumps recorded
	public double tau; // the time recorded
	public double h; // the memory state for floor time

	public TimerState(double i, double tau, double h)
	{
		this.i = i;
		this.tau = tau;
		this.h = h;
	}
}
