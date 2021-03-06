/**
 * 
 */
package sk.emandem.michal;


import java.util.Date;

/**
 * @author Michal Antolik
 * 
 */
public class AutoboxingTypeBenchmark {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		long memory = MemoryMeasurer.measureBytes(new EmptyClass());
//		System.out.println(memory);
//		
//		memory = MemoryMeasurer.measureBytes(new Object());
//		System.out.println(memory);
		
//		int iterations = 10000000;
		int iterations = 1000000;
		int repetition = 3;
		boolean preciseMemoryMethod = true;
		
		ResourceUsage resourceUsagePrimType= new ResourceUsage(preciseMemoryMethod);
		ResourceUsage resourceUsageWrapper= new ResourceUsage(preciseMemoryMethod);

		System.out.println("##Measuring integers##");
		for(int expNumber=0; expNumber < repetition; expNumber++ ){
			resourceUsagePrimType.start(iterations);
			Object o = measureInt(iterations, true);
			resourceUsagePrimType.stop(o);
			
			resourceUsageWrapper.start(iterations);
			o = measureInt(iterations, false);
			resourceUsageWrapper.stop(o);
		}
		System.out.println("primitive Type: " + resourceUsagePrimType.toAverageString());
		System.out.println("wrapper Type: " + resourceUsageWrapper.toAverageString());
		System.out.println("memory wrapper overhead: " + resourceUsageWrapper.getAverageMemory() / resourceUsagePrimType.getAverageMemory());
		System.out.println("consumed time wrapper overhead: " + resourceUsageWrapper.getAverageTime() / resourceUsagePrimType.getAverageTime());
		resourceUsagePrimType.reset();
		resourceUsageWrapper.reset();
		
		System.out.println("##Measuring long numbers##");
		for(int expNumber=0; expNumber < repetition; expNumber++ ){
			resourceUsagePrimType.start(iterations);
			Object o = measureLong(iterations, true);
			resourceUsagePrimType.stop(o);
			
			resourceUsageWrapper.start(iterations);
			o = measureLong(iterations, false);
			resourceUsageWrapper.stop(o);
		}
		System.out.println("primitive Type: " + resourceUsagePrimType.toAverageString());
		System.out.println("wrapper Type: " + resourceUsageWrapper.toAverageString());
		System.out.println("memory wrapper overhead: " + resourceUsageWrapper.getAverageMemory() / resourceUsagePrimType.getAverageMemory());
		System.out.println("consumed time wrapper overhead: " + resourceUsageWrapper.getAverageTime() / resourceUsagePrimType.getAverageTime());
		resourceUsagePrimType.reset();
		resourceUsageWrapper.reset();

		System.out.println("##Measuring empty class instantiating ##");
		for(int expNumber=0; expNumber < repetition; expNumber++ ){
			resourceUsagePrimType.start(iterations);
			Object o = measureEmptyClass(iterations, false);
			resourceUsagePrimType.stop(o);
			
			resourceUsageWrapper.start(iterations);
			o = measureEmptyClass(iterations, true);
			resourceUsageWrapper.stop(o);
		}
		System.out.println("Empty class: " + resourceUsagePrimType.toAverageString());
		System.out.println("Empty object: " + resourceUsageWrapper.toAverageString());
		resourceUsagePrimType.reset();
		resourceUsageWrapper.reset();
		
		System.out.println("##Measuring emulated long class instantiating ##");
		for(int expNumber=0; expNumber < repetition; expNumber++ ){
			resourceUsagePrimType.start(iterations);
			Object o = measureEmulatedLongClass(iterations);
			resourceUsagePrimType.stop(o);
		}
		System.out.println("Emulated long class: " + resourceUsagePrimType.toAverageString());
		resourceUsagePrimType.reset();
		

//		measureWrapperInt(iterations, true);
		// System.out.println(measureWrapperInt(iterations, false));
		// System.out.println(measureArrayInt(iterations/100, true));
		// System.out.println(measureArrayInt(iterations/100, false));


//		System.out.println(measureArrayVsWrapperInt(iterations, true));
		
//		System.out.println(measureArrayVsWrapperInt(iterations, false));
//
//		System.out.println(measureArrayVsWrapperInt(iterations, false));
//		System.out.println(measureArrayVsWrapperInt(iterations, true));

	}

	private static Object measureInt(int iterations, boolean primitiveType) {
//		System.out.println("Measuring array of size " + iterations + " of integers type " + (primitiveType?"primitive":"wrapped"));
		int primitiveInt = 0;
		Integer objectInt = 0;
		if (primitiveType) {
			int[] arr = new int[iterations];
			for (int i = 0; i < iterations; i++) {
				primitiveInt = i;
//				primitiveInt = primitiveInt * 10;
				arr[i] = primitiveInt;
			}
			primitiveInt = arr[0];
			return arr;
		} else {
			Integer[] arr = new Integer[iterations];
			for (int i = 0; i < iterations; i++) {
				// objectInt=Integer.valueOf(10*i);
				objectInt = i;
//				objectInt = objectInt * i;
				arr[i] = objectInt;
			}
			objectInt = arr[0];
			return arr;
		}
	}
	
	private static Object measureLong(int iterations, boolean primitiveType) {
//		System.out.println("Measuring array of size " + iterations + " of long numbers type " + (primitiveType?"primitive":"wrapped"));
		long primitiveLong = 0L;
		Long objectLong = 0L;
		if (primitiveType) {
			long[] arr = new long[iterations];
			for (int i = 0; i < iterations; i++) {
				primitiveLong = i;
//				primitiveLong = primitiveLong * 10L;
				arr[i] = primitiveLong;
			}
			primitiveLong = arr[0];
			return arr;
		} else {
			Long[] arr = new Long[iterations];
			for (long i = 0; i < iterations; i++) {
				// objectInt=Integer.valueOf(10*i);
				objectLong = i;
//				objectLong = objectLong * i;
				arr[(int)i] = objectLong;
			}
			objectLong = arr[0];
			return arr;
		}
	}
	
	private static Object measureEmulatedLongClass(int iterations) {
//		System.out.println("Measuring array of size " + iterations + " of emulated long class");
		EmulatedLongClass emulatedLongClassInstance = null;
		EmulatedLongClass[] arr = new EmulatedLongClass[iterations];
		for (int i = 0; i < iterations; i++) {
			// objectInt=Integer.valueOf(10*i);
			emulatedLongClassInstance = new EmulatedLongClass(i);
			arr[i] = emulatedLongClassInstance;
		}
		emulatedLongClassInstance = arr[0];
		return arr;
	}
	
	private static Object measureEmptyClass(int iterations, boolean asObject) {
//		System.out.println("Measuring array of size " + iterations + " of empty class, using plain object " + asObject);
		if(asObject){
			Object emptyClassInstance = null;
			Object[] arr = new Object[iterations];
			for (int i = 0; i < iterations; i++) {
				// objectInt=Integer.valueOf(10*i);
				emptyClassInstance = new Object();
				arr[i] = emptyClassInstance;
			}
			emptyClassInstance = arr[0];
			return arr;
		} else {
			EmptyClass emptyClassInstance = null;
			EmptyClass[] arr = new EmptyClass[iterations];
			for (int i = 0; i < iterations; i++) {
				// objectInt=Integer.valueOf(10*i);
				emptyClassInstance = new EmptyClass();
				arr[i] = emptyClassInstance;
			}
			emptyClassInstance = arr[0];
			return arr;
		}
	}
	
	public static class EmptyClass{
	}
	
	public static class EmulatedLongClass{
		public long value;
		public EmulatedLongClass(long l) {
			value=l;
		}
	}

	@SuppressWarnings("unused")
	private static long measureWrapperInt(int iterations, boolean primitiveType) {
		long start = System.currentTimeMillis();
		IntWrapper primitiveInt;
		IntegerWrapper objectInt;
		if (primitiveType) {
			for (int i = 0; i < iterations; i++) {
				primitiveInt = new IntWrapper();
				primitiveInt.value = i;
				primitiveInt.strValue = "hello";
				primitiveInt.dateValue = new Date();
			}
		} else {
			for (int i = 0; i < iterations; i++) {
				objectInt = new IntegerWrapper();
				objectInt.value = i;
				objectInt.strValue = "hello";
				objectInt.dateValue = new Date();
			}
		}
		long end = System.currentTimeMillis();
		return end - start;
	}

	@SuppressWarnings("unused")
	static private class IntWrapper {
		public int value;
		public String strValue;
		public Date dateValue;
	}

	@SuppressWarnings("unused")
	static private class IntegerWrapper {
		public Integer value;
		public String strValue;
		public Date dateValue;
	}

	@SuppressWarnings("unused")
	private static long measureArrayVsWrapperInt(int iterations,
			boolean primitiveType) {
		long start = System.currentTimeMillis();
		Object value;
		if (primitiveType) {
			IntWrapper[] arr = new IntWrapper[iterations];
			for (int i = 0; i < iterations; i++) {
				IntWrapper primitiveInt = new IntWrapper();
				primitiveInt.value = i;
				primitiveInt.strValue = "hello";
				primitiveInt.dateValue = new Date();
				arr[i] = primitiveInt;
			}
			value = arr[0];
		} else {
			Object[] arr = new Object[iterations];
			for (int i = 0; i < iterations; i++) {
				Object[] valueArr = new Object[3];
				valueArr[0] = i;
				valueArr[1] = "hello";
				valueArr[2] = new Date();
				arr[i] = valueArr;
			}
			value = arr[0];
		}
		long end = System.currentTimeMillis();
		return end - start;
	}

	@SuppressWarnings("unused")
	private static long measureArrayInt(int iterations, boolean primitiveType) {
		int arraySize = 100;
		long start = System.currentTimeMillis();
		int[] primitiveInt;
		Integer[] objectInt;
		if (primitiveType) {
			for (int i = 0; i < iterations; i++) {
				primitiveInt = new int[arraySize];
				for (int j = 0; j < arraySize; j++) {
					primitiveInt[j] = i;
				}
			}
		} else {
			for (int i = 0; i < iterations; i++) {
				objectInt = new Integer[arraySize];
				for (int j = 0; j < arraySize; j++) {
					objectInt[j] = i;
				}
			}
		}
		long end = System.currentTimeMillis();
		return end - start;
	}

}
