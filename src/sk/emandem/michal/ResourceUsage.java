/**
 * 
 */
package sk.emandem.michal;

/**
 * @author misiak
 *
 */
public class ResourceUsage {

	private Runtime runtime = Runtime.getRuntime();
	
	private long startTotalMemory;
	private long startFreeMemory;
	private long stopTotalMemory;
	private long stopFreeMemory;
	private long startTime;
	private long stopTime;
	
	private long totalTime=0L;
	private long totalMemory=0L;
	private long totalIterations=0L;
	
	public void start(){
		runtime.gc();
		startTotalMemory = runtime.totalMemory();
		startFreeMemory = runtime.freeMemory();
		startTime = System.currentTimeMillis();
	}
	
	public void stop(){
		stopTotalMemory = runtime.totalMemory();
		stopFreeMemory = runtime.freeMemory();
		stopTime = System.currentTimeMillis();
		totalIterations++;
		totalMemory+=getUsedMemory();
		totalTime+=stopTime-startTime;
	}
	
	public void reset(){
		totalMemory=0L;
		totalTime=0L;
		totalIterations=0L;
	}
	
	public long getUsedMemory(){
		return (stopTotalMemory-stopFreeMemory) - (startTotalMemory-startFreeMemory);
	}
	
	public long getUsedTime(){
		return stopTime-startTime;
	}
	
	@Override
	public String toString() {
		return String.format("%.2f KB of memory, took %.2f seconds", getUsedMemory()/1024.0, getUsedTime()/1000.0);
	}
	
	public String toAverageString() {
		return String.format("Average: %.2f KB of memory, took %.2f seconds", totalMemory/totalIterations/1024.0, totalTime/totalIterations/1000.0);
	}

	public long getStartTotalMemory() {
		return startTotalMemory;
	}

	public long getStartFreeMemory() {
		return startFreeMemory;
	}

	public long getStopTotalMemory() {
		return stopTotalMemory;
	}

	public long getStopFreeMemory() {
		return stopFreeMemory;
	}

	public long getStartTime() {
		return startTime;
	}

	public long getStopTime() {
		return stopTime;
	}
}
