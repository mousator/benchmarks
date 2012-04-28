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
		int iterations = 10000000;
		ResourceUsage resourceUsagePrimType= new ResourceUsage();
		ResourceUsage resourceUsageWrapper= new ResourceUsage();

		System.out.println("##Measuring integers##");
		for(int expNumber=0; expNumber < 5; expNumber++ ){
			resourceUsagePrimType.start();
			measureInt(iterations, true);
			resourceUsagePrimType.stop();
			System.out.println("Diff memory: " + resourceUsagePrimType.toString());
			
			resourceUsageWrapper.start();
			measureInt(iterations, false);
			resourceUsageWrapper.stop();
			System.out.println("Diff memory: " + resourceUsageWrapper.toString());
		}
		System.out.println("primitive Type: " + resourceUsagePrimType.toAverageString());
		System.out.println("wrapper Type: " + resourceUsageWrapper.toAverageString());
		resourceUsagePrimType.reset();
		resourceUsageWrapper.reset();
		
		System.out.println("##Measuring long numbers##");
		for(int expNumber=0; expNumber < 5; expNumber++ ){
			resourceUsagePrimType.start();
			measureLong(iterations, true);
			resourceUsagePrimType.stop();
			System.out.println("Diff memory: " + resourceUsagePrimType.toString());
			
			resourceUsageWrapper.start();
			measureLong(iterations, false);
			resourceUsageWrapper.stop();
			System.out.println("Diff memory: " + resourceUsageWrapper.toString());
		}
		System.out.println("primitive Type: " + resourceUsagePrimType.toAverageString());
		System.out.println("wrapper Type: " + resourceUsageWrapper.toAverageString());
		resourceUsagePrimType.reset();
		resourceUsageWrapper.reset();
		
		System.out.println("##Measuring empty class instantiating ##");
		for(int expNumber=0; expNumber < 5; expNumber++ ){
			resourceUsagePrimType.start();
			measureEmptyClass(iterations);
			resourceUsagePrimType.stop();
			System.out.println("Diff memory: " + resourceUsagePrimType.toString());
		}
		System.out.println("empty class: " + resourceUsagePrimType.toAverageString());
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

	private static void measureInt(int iterations, boolean primitiveType) {
		System.out.println("Measuring array of size " + iterations + " of integers type " + (primitiveType?"primitive":"wrapped"));
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
		} else {
			Integer[] arr = new Integer[iterations];
			for (int i = 0; i < iterations; i++) {
				// objectInt=Integer.valueOf(10*i);
				objectInt = i;
//				objectInt = objectInt * i;
				arr[i] = objectInt;
			}
			objectInt = arr[0];
		}
	}
	
	private static void measureLong(int iterations, boolean primitiveType) {
		System.out.println("Measuring array of size " + iterations + " of long numbers type " + (primitiveType?"primitive":"wrapped"));
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
		} else {
			Long[] arr = new Long[iterations];
			for (long i = 0; i < iterations; i++) {
				// objectInt=Integer.valueOf(10*i);
				objectLong = i;
//				objectLong = objectLong * i;
				arr[(int)i] = objectLong;
			}
			objectLong = arr[0];
		}
	}
	
	private static void measureEmptyClass(int iterations) {
		System.out.println("Measuring array of size " + iterations + " of empty class");
		EmptyClass emptyClassInstance = null;
		EmptyClass[] arr = new EmptyClass[iterations];
		for (int i = 0; i < iterations; i++) {
			// objectInt=Integer.valueOf(10*i);
			emptyClassInstance = new EmptyClass();
			arr[i] = emptyClassInstance;
		}
		emptyClassInstance = arr[0];
	}
	
	public static class EmptyClass{
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
