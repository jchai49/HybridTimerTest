package TimerTest;

import com.be3short.io.format.ImageFormat;
// import edu.cross.ucsc.hse.core.chart.ChartConfiguration;
import edu.ucsc.cross.hse.core.environment.Environment;
import edu.ucsc.cross.hse.core.setting.ComputationSettings.IntegratorType;
import edu.ucsc.cross.hse.core.task.TaskManager;

public class TimerRun extends TaskManager
{

	/*
	 * Queue method that executes everything listed within when application starts*
	 */
	@Override
	public void taskQueue()
	{
		runTimerSimulation();
	}

	/*
	 * Main class needed to run java application
	 */
	public static void main(String args[])
	{
		launch();
	}

	/*
	 * Create an environment that is already configured
	 */
	public static Environment getConfiguredEnvironment()
	{

		Environment env = new Environment(); // Environment with default settings

		///// Execution Parameters

		// Maximum time allowed before execution terminates
		env.getSettings().getExecutionParameters().maximumTime = 20.0;
		// Maximum number of jumps allowed before execution terminates
		env.getSettings().getExecutionParameters().maximumJumps = 1000000;

		///// Log settings

		// Total progress updates to print, ie 10 prints an update at 10% intervals. -1 disables progress output
		env.getSettings().getLogSettings().numProgressUpdateOutputs = 10;
		// Inverval between status print outs
		env.getSettings().getLogSettings().statusPrintInterval = 10.0;
		// Print debug messages
		env.getSettings().getLogSettings().printDebug = true;
		// Print error messages
		env.getSettings().getLogSettings().printError = true;
		// Print information messages
		env.getSettings().getLogSettings().printInfo = true;
		// Print integrator exception warnings
		env.getSettings().getLogSettings().printIntegratorExceptions = false;
		// Print warning messages
		env.getSettings().getLogSettings().printWarning = true;

		///// Output settings

		// Flag indicating if file names should include the date at the end (for uniqueness)
		env.getSettings().getOutputSettings().appendFilesWithNumericDate = false;
		// Default image file format to be used when exporting charts with no format specified
		env.getSettings().getOutputSettings().chartFileFormat = ImageFormat.EPS;
		// Name of the environment configuration file if it were to be saved
		env.getSettings().getOutputSettings().configurationFileName = "environmentConfig";
		// Time between data point storage
		env.getSettings().getOutputSettings().dataPointInterval = 1.0e-6;
		// Name of the environment file if it were to be saved
		env.getSettings().getOutputSettings().environmentFileName = "environment";
		// Location where results will be automatically stored if auto storage is enabled
		env.getSettings().getOutputSettings().outputDirectory = "output";
		// Flag indicating if environment configuration should be saved in an output file when not specified by the user
		env.getSettings().getOutputSettings().saveChartsToFile = true;
		// Flag indicating if environment configuration should be saved in an output file when not specified by the user
		env.getSettings().getOutputSettings().saveConfigurationToFile = false;
		// Flag indicating if environment should be saved in an output file when not specified by the user
		env.getSettings().getOutputSettings().saveEnvironmentToFile = false;
		// Flag indicating if log should be saved to file
		env.getSettings().getOutputSettings().saveLogToFile = true;

		///// Computation Settings

		// Factor that event handling convergence value will be reduced when event handling error occurs
		env.getSettings().getComputationSettings().convergenceErrorCorrectionFactor = 1.0;
		// Convergence threshold of an event
		env.getSettings().getComputationSettings().eventHandlerConvergenceThreshold = 1.0e-8;
		// Event handler maximum interval to check for an event
		env.getSettings().getComputationSettings().eventHandlerMaximumCheckInterval = 1.0e-8;
		// Integrator type to be used
		env.getSettings().getComputationSettings().integratorType = IntegratorType.DORMAND_PRINCE_853;
		// Factor that event handling interval value will be reduced when event handling error occurs
		env.getSettings().getComputationSettings().intervalErrorCorrectionFactor = 1.0;
		// Factor that iteration count will be multiplied by when iteration count error occurs
		env.getSettings().getComputationSettings().iterationCountErrorCorrectionFactor = 2;
		// Maximum number of event handler iterations
		env.getSettings().getComputationSettings().maxEventHandlerIterations = 10;
		// Maximum step size for variable step integrator
		env.getSettings().getComputationSettings().odeMaximumStepSize = 1.0e-3;
		// Ode step size if using a fixed step integrator, or minimum ode step size of a variable step integrator
		env.getSettings().getComputationSettings().odeMinimumStepSize = 1.0e-6;
		// Relative tolerance of the ode solver
		env.getSettings().getComputationSettings().odeRelativeTolerance = 1.0e-4;
		// Absolute tolerance of the ode solver
		env.getSettings().getComputationSettings().odeSolverAbsoluteTolerance = 1.0e-4;
		// Factor to reduce the maximum /fixed step size when a step size related error occurs
		env.getSettings().getComputationSettings().stepSizeErrorMaxCorrectionFactor = 1.0;
		// Factor to reduce minimum step size (if using variable step integrator) when a step size related error occurs
		env.getSettings().getComputationSettings().stepSizeErrorMinCorrectionFactor = 1.0;

		return env;
	}
/*
	public static ChartConfiguration multiChart()
	{
		// Create a new plot configuration with specified width (600.0) and height (600.0)
		ChartConfiguration plot = new ChartConfiguration(1000.0, 800.0);
		// plot.
		// Set layout to generate two horizontal plots with plot 0 on top and plot 1 on the bottom
		plot.setLayout(new Integer[][]
		{
				{ 0, 0, 0, 1, 2, 3 },
				{ 0, 0, 0, 4, 5, 5 } });

		// Select data to display
		// * selections should be a string that matches the variable name of the data to be selected
		// * null is used to select time as the x axis values
		plot.chartProperties(0).setAxisSelections("iL", "vC");
		plot.chartProperties(1).setAxisSelections(null, "p");
		plot.chartProperties(2).setAxisSelections(null, "q");
		plot.chartProperties(3).setAxisSelections(null, "iL");
		plot.chartProperties(4).setAxisSelections(null, "vC");
		plot.chartProperties(5).setAxisSelections(null, "tau");
		// Select axis labels
		// * null is used to hide an axis label
		plot.chartProperties(0).setAxisLabels(null, null);
		plot.chartProperties(1).setAxisLabels(null, null);
		plot.chartProperties(2).setAxisLabels(null, null);
		plot.chartProperties(3).setAxisLabels(null, null);
		plot.chartProperties(4).setAxisLabels(null, null);
		plot.chartProperties(5).setAxisLabels(null, null);

		// Specify legend visibility
		plot.chartProperties(0).setDisplayLegend(false);
		plot.chartProperties(1).setDisplayLegend(false);
		plot.chartProperties(2).setDisplayLegend(false);
		plot.chartProperties(3).setDisplayLegend(false);
		plot.chartProperties(4).setDisplayLegend(false);
		plot.chartProperties(5).setDisplayLegend(false);

		// Specify overall title for the plot
		// * null is used to indicate no sub plot title
		// * there are no sub plot titles by default so following lines can be ommitted for no sub plot titles
		plot.chartProperties(0).setTitle("vC vs iL");
		plot.chartProperties(1).setTitle("p");
		plot.chartProperties(2).setTitle("q");
		plot.chartProperties(3).setTitle("iL");
		plot.chartProperties(4).setTitle("vC");
		plot.chartProperties(5).setTitle("tau");

		return plot;
	}
*/
	public static TimerSystem getTestTimerSystem()
	{
		Double i0 = 0.0;
		Double tau0 = 0.0;
		Double h0 = 0.0;
		TimerState state = new TimerState(i0, tau0, h0);
		TimerSystem timSys = new TimerSystem(state);
		return timSys;
	}

	public static void runTimerSimulation()
	{
		Environment env = getConfiguredEnvironment();
		TimerSystem s = getTestTimerSystem();
		env.add(s);
		// env.add(multiChart()); // adding the chart to the environment automatically generates output files
		env.start(20.0, 1000000);
		// multiChart().createChart(env);
		env.getData().exportToCSVFile();
	}
}