package TimerTest;

import edu.ucsc.cross.hse.core.object.HybridSystem;

public class TimerSystem extends HybridSystem<TimerState>
{

	public TimerSystem(TimerState state)
	{
		super(state);
	}

	@Override
	public boolean C(TimerState x)
	{
		if ( Math.floor(x.tau) == x.h) // does not seem to have the priority choices
		{
			return true; 
		}
		return false;
	}

	@Override
	public void F(TimerState x, TimerState x_dot)
	{
		x_dot.i = 0.0;
		x_dot.tau = 1.0;
		x_dot.h = 0.0;
	}

	@Override
	public boolean D(TimerState x)
	{
		Double V = (Math.floor(x.tau) * (1.0 + Math.floor(x.tau)) / 2.0);

		if (( Math.floor(x.tau) > x.h ) && (x.i < V))
			{
				return true;
			} else if (( Math.floor(x.tau) > x.h ) && (x.i == 0.0))
			{
				return true;
			}
		return false;
	}

	@Override
	public void G(TimerState x, TimerState x_plus)
	{
		Double iplus = x.i;
		Double tauplus = x.tau;
		Double hplus = x.h;
		Double V = (Math.floor(x.tau) * (1.0 + Math.floor(x.tau)) / 2.0);

		iplus = x.i + 1.0; 
		
		if ( iplus == V )
			{
				hplus = Math.floor(x.tau);
			}

		x_plus.i = iplus;
		x_plus.tau = tauplus;
		x_plus.h = hplus;
	}

}
