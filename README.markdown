This project represents few small benchmark classes to see how primitive type compares to wrapper.

the results are as:

##Measuring integers##
primitive Type: Average: 39698.87 KB of memory, took 0.02 seconds
wrapper Type: Average: 274911.96 KB of memory, took 1.13 seconds
memory wrapper overhead: 6
consumed time wrapper overhead: 56

##Measuring long numbers##
primitive Type: Average: 78763.07 KB of memory, took 0.02 seconds
wrapper Type: Average: 285569.55 KB of memory, took 1.08 seconds
memory wrapper overhead: 3
consumed time wrapper overhead: 46

##Measuring empty class instantiating ##
empty class: Average: 274008.82 KB of memory, took 0.84 seconds

I used MacBookAir 1.7Ghz CPU, JDK1.6.0_31 64bit by [Michal Antolik]
