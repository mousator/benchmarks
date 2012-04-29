/**
 * 
 */
package sk.emandem.michal;

import objectexplorer.MemoryMeasurer;

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
	
	private long measurerMemorySize = 0L;
	
	private int nrUnits;
	
	private boolean preciseMeasureMethod;
	
	public ResourceUsage(boolean preciseMeasureMethod) {
		this.preciseMeasureMethod = preciseMeasureMethod;
	}
	
	public void start(int nrUnits){
		this.nrUnits=nrUnits;
		startTime = System.currentTimeMillis();
		if(!preciseMeasureMethod){
			runtime.gc();
			startTotalMemory = runtime.totalMemory();
			startFreeMemory = runtime.freeMemory();
		}
	}
	
	public void stop(Object o){
		stopTime = System.currentTimeMillis();
		totalIterations++;
		if(!preciseMeasureMethod){
			stopTotalMemory = runtime.totalMemory();
			stopFreeMemory = runtime.freeMemory();
			measurerMemorySize = (stopTotalMemory-stopFreeMemory) - (startTotalMemory-startFreeMemory);
		} else {
			measurerMemorySize = MemoryMeasurer.measureBytes(o);
		}
		totalMemory+=getUsedMemory();
		totalTime+=stopTime-startTime;
		System.out.println("Diff memory: " + toString());
	}
	
	public void reset(){
		totalMemory=0L;
		totalTime=0L;
		totalIterations=0L;
	}
	
	public long getUsedMemory(){
		return measurerMemorySize;
	}
	
	public long getUsedTime(){
		return stopTime-startTime;
	}
	
	@Override
	public String toString() {
		return String.format("%.2f KB of memory, took %.2f seconds (memory size per unit: %d)", getUsedMemory()/1024.0, getUsedTime()/1000.0, getUsedMemory()/nrUnits);
	}
	
	public String toAverageString() {
		return String.format("Average: %.2f KB of memory, took %.2f seconds (memory size per unit: %d)", getAverageMemory()/1024.0, getAverageTime()/1000.0, getAverageMemory()/nrUnits);
	}
	
	public long getAverageMemory(){
		return totalMemory/totalIterations;
	}
	
	public long getAverageTime(){
		return totalTime/totalIterations + 1; //to avoid div by 0
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
