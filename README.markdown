This project represents few small benchmark classes to see how primitive types compares to theirs wrappers.

I measured three times the creation of one million of *int*, *long*, 
*empty custom class vs Object* and *custom class containing long primitive*

From the quick benchmark I found out:

- measuring integers
 * wrapper class needs 4.5 (20/4) times more memory than int
 * the time overhead is about 12 times

- measuring long 
 * wrapper class took 3.5 (28/8) more memory than long
 * the time overhead was comparable to int overhead, about 12 times 
 * Long wrapper is comparable to creating custom class with long primitive field (quite obvious from the definition)

- measuring empty class vs empty object
 * not significant difference

- measuring methods
 * using Runtime class to get used memory is not precise enough
 * to get precise output I used object explorer library (http://code.google.com/p/memory-measurer/)
 * I used MacBookAir 1.7Ghz CPU, JDK1.6.0_31 64bit by [Michal Antolik]



##console output##
	
	--- more precise using object explorer library
	Measuring integers
		primitive Type: Average: 3906.27 KB of memory, took 0.00 seconds (memory size per unit: 4)
		wrapper Type: Average: 19531.27 KB of memory, took 0.05 seconds (memory size per unit: 20)
		memory wrapper overhead: 4
		consumed time wrapper overhead: 12
	Measuring long numbers
		primitive Type: Average: 7812.52 KB of memory, took 0.01 seconds (memory size per unit: 8)
		wrapper Type: Average: 27343.77 KB of memory, took 0.06 seconds (memory size per unit: 28)
		memory wrapper overhead: 3
		consumed time wrapper overhead: 12
	Measuring empty class instantiating
		Empty class: Average: 19531.27 KB of memory, took 0.04 seconds (memory size per unit: 20)
		Empty object: Average: 19531.27 KB of memory, took 0.05 seconds (memory size per unit: 20)
		Measuring emulated long class instantiating
		Emulated long class: Average: 27343.77 KB of memory, took 0.07 seconds (memory size per unit: 28)
	
	 
	--- less precise
	Measuring integers
		primitive Type: Average: 4331.07 KB of memory, took 0.03 seconds (memory size per unit: 4)
		wrapper Type: Average: 25162.66 KB of memory, took 0.15 seconds (memory size per unit: 25)
		memory wrapper overhead: 5
		consumed time wrapper overhead: 4
	Measuring long numbers
		primitive Type: Average: 8107.86 KB of memory, took 0.03 seconds (memory size per unit: 8)
		wrapper Type: Average: 27542.90 KB of memory, took 0.07 seconds (memory size per unit: 28)
		memory wrapper overhead: 3
		consumed time wrapper overhead: 2
	Measuring empty class instantiating
		Empty class: Average: 25156.73 KB of memory, took 0.17 seconds (memory size per unit: 25)
		Empty object: Average: 25176.66 KB of memory, took 0.18 seconds (memory size per unit: 25)
		Measuring emulated long class instantiating
		Emulated long class: Average: 27376.90 KB of memory, took 0.12 seconds (memory size per unit: 28)


