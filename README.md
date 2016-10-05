# muse-collect

Working with arrays has always being difficult. This library aims to solve that problem. Currently, several of classes has Fluent API to interact with arrays. From Wiki, in software engineering, a fluent interface (as first coined by Eric Evans and Martin Fowler) is an implementation of an object oriented API that aims to provide more readable code.

At the heart of this library lies 3 important classes i.e. ```IntArray```, ```LongArray``` and ```DoubleArray``` which encapsulates ```int[]```, ```long[]``` and ```double[]``` respectively. One additional detail about this 3 classes is that they all are Immutable.

Let us start with an example of what this library aims in terms of arrays. For example we will use class ```IntArray```.
 1. Creating an instance of class.
    * Use Static factory to create an instance. If argument is null NullPointerException is thrown.
        ```
        IntArray intArray0 = IntArray.of(new int[] {});//contains int[] with length 0.
	assertEquals(0, intArray0.size());
            
        IntArray intArray1 = IntArray.of(1);//contains int[] with value as 1.
        assertArrayEquals(new int[] { 1 }, intArray1.toArray());
	
        IntArray intArray2 = IntArray.of(1, 2);//contains int[] with values as 1 and 2.
        assertArrayEquals(new int[] { 1, 2 }, intArray2.toArray());
	
        IntArray intArray3 = IntArray.of(1, 2, 3);//contains int[] with values as 1, 2 and 3.
        assertArrayEquals(new int[] { 1, 2, 3 }, intArray3.toArray());
	
        IntArray intArray4 = IntArray.of(1, 2, 3, 4);//contains int[] with values as 1, 2, 3 and 4.
        assertArrayEquals(new int[] { 1, 2, 3, 4 }, intArray4.toArray());
	
        IntArray intArrayN = IntArray.of(new int[] { 1, 2, 3, 4, 5, 6, 7, 8 });//The specified array in parameter is copied to new array and stored in IntArray.
	assertArrayEquals(new int[] { 1, 2, 3, 4, 5, 6, 7, 8 }, intArrayN.toArray());
            
        IntArray intArrayColl = IntArray.of(Arrays.asList(1, 2, 3, 4));//The specified list in parameter is copied to new array and stored in IntArray.
	assertArrayEquals(new int[] { 1, 2, 3, 4 }, intArrayColl.toArray());
            
        IntArray zeros = IntArray.zeros(10);//array of length 10 filled with 0's.
        assertArrayEquals(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, values.toArray());
	
        IntArray ones = IntArray.ones(10);//array of length 10 filled with 1's.
        assertArrayEquals(new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, values.toArray());
	
        IntArray values = IntArray.ofValue(-1, 10); //array of length 10 filled with -1's.
	assertArrayEquals(new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }, values.toArray());
	
        ```
    * Use Static factory for several arrays. 
        ```
        int[] arr1 = { 1, 2, 3 };
		
		int[] arr2 = { 11, 12, 13 };
		
		int[] arr3 = { 21, 22 };
		
		IntArray values = IntArray.of(arr1, arr2, arr3);
		
		assertArrayEquals(new int[] { 1, 2, 3, 11, 12, 13, 21, 22 }, values.toArray());
        ```
    * Use Static factory for several IntArray's.
        ```
        IntArray values1 = IntArray.of(new int[] { 1, 2, 3 });
		
		IntArray values2 = IntArray.of(new int[] { 11, 12, 13 });
		
		IntArray values3 = IntArray.of(new int[] { 21, 22 });
		
		IntArray values = IntArray.of(values1, values2, values3);
		
		assertArrayEquals(new int[] { 1, 2, 3, 11, 12, 13, 21, 22 }, values.toArray());
        ```
 2. Adding value to IntArray.
    * Appending value.
        ```
        IntArray values = IntArray.of(1,2,3,4); //contains int[] of values 1, 2, 3 and 4.
        
        values = values.add(40); //contains int[] of values 1, 2, 3, 4 and 40.
        
        values = values.add(40).add(50).add(60); //contains int[] of values 1, 2, 3, 4, 40, 50 and 60.
        ```
    * Adding a value to specified index.
        ```
        
        IntArray values = IntArray.of(4, 5, 6).add(2, 7); //contains int[] of values 4, 5, 7, 6.
        ```
    * Adding a collection.
        ```
       
        IntArray values = IntArray.of(1, 2, 3, 4).addAll(Arrays.asList(45, 123, 78)); //contains int[] of values 1, 2, 3, 4, 45, 123, 78.
        ```
 3. Removing elements.
    * Removing all elements. 
        ```
        IntArray values = IntArray.of(1, 2, 3, 4); //contains int[] of values 1, 2, 3, 4.
        
        values = values.clear(); //returns int[] with length 0.
        ```
 4. Checking size.
    * Checking size. 
        ```
        IntArray values = IntArray.of(1, 2, 3, 4);
        
        int size = values.size(); //returns size of int[].
        ```
    * Checking if IntArray is empty or not.
        ```
        IntArray values = IntArray.of(1, 2, 3, 4);
        
        boolean isEmpty = values.isEmpty(); //returns true if int[] length = 0, false otherwise.
        ```
 5. Retrieving values.
    * Retriving single value.
        ```
        IntArray values = IntArray.of(1, 2, 3, 4);
        
        int value = values.get(2); //return elements at index 2.
        ```
    * Iterating using Iterator<Integer>.
        ```
        
        Iterator<Integer> valuesIterator = IntArray.of(1, 2, 3, 4).iterator();
        ```
    * Iterating using ListIterator<Integer>.
        ```
        
        Iterator<Integer> valuesIterator = IntArray.of(1, 2, 3, 4).listIterator();
        
        Iterator<Integer> valuesIterator = IntArray.of(1, 2, 3, 4).listIterator(2);//starts iteration with specified index.
        ```
 6. Bridge.
    * Converting IntArray to LongArray.
        ```
        
        LongArray values = IntArray.of(1, 2, 3, 4).toLongArray();
        ```
    * Converting IntArray to DoubleArray.
        ```
        
        DoubleArray values = IntArray.of(1, 2, 3, 4).toDoubleArray();
        ```
    * Converting IntArray to List<Integer>.
        ```
        
        List<Integer> values = IntArray.of(1, 2, 3, 4).toList();
        ```
    * Converting IntArray to int[].
        ```
        
        int[] values = IntArray.of(1, 2, 3, 4).toArray();
        ```
 7. Mathematical operations.
    * Addition
        * Adding 1 to all values in IntArray.
            ```
            IntArray intArray = IntArray.of(1, 2, 3, 4).plusOne();
           
            assertArrayEquals(new int[] { 2, 3, 4, 5 }, intArray.toArray())
            ```
        * Add all values in IntArray with some value.
            ```
            IntArray values = IntArray.of(1, 2, 3, 4).plus(10);
           
            assertArrayEquals(new int[] { 11, 12, 13, 14 }, values.toArray());
            ```
        * Add two IntArray's.
            ```
            IntArray intArray1 = IntArray.of(1, 2, 3, 4);
    		
    		IntArray intArray2 = IntArray.of(5, 6, 7, 8);
    
    		IntArray result = intArray1.plus(intArray2);
    		
    		assertArrayEquals(new int[] { 6, 8, 10, 12 }, result.toArray());
            ```
    
    * Subtraction
        * Subtract all values in IntArray with some value.
            ```
            IntArray intArray1 = IntArray.of(1, 2, 3, 4).minus(10);
    		
    		assertArrayEquals(new int[] { -9, -8, -7, -6 }, intArray1.toArray());
            ```
        * Subtract two IntArray's
            ```
            IntArray intArray1 = IntArray.of(1, 2, 3, 4);
    	
    		IntArray intArray2 = IntArray.of(5, 6, 7, 8);

	    	IntArray result = intArray1.minus(intArray2);
		    
		    assertArrayEquals(new int[] { -4, -4, -4, -4 }, result.toArray());
            ```
    * Multiply
        * Multiple all values in IntArray with some value.
            ```
            IntArray intArray1 = IntArray.of(1, 2, 3, 4).multiply(10);
		    
		    assertArrayEquals(new int[] { 10, 20, 30, 40 }, intArray1.toArray());   

		    IntArray intArray2 = IntArray.of(1, 2, 3, 4).multiply(-3);
		    
		    assertArrayEquals(new int[] { -3, -6, -9, -12 }, intArray2.toArray());
            ```
        * Multiple two IntArray's
            ```
            IntArray intArray1 = IntArray.of(1, 2, 3, 4);
		    
		    IntArray intArray2 = IntArray.of(5, 6, 7, 8);

		    IntArray result = intArray1.multiply(intArray2);
		    
		    assertArrayEquals(new int[] { 5, 12, 21, 32 }, result.toArray());
            ```
    * Divide
        * Divide all values in IntArray with some value.
            ```
            IntArray intArray1 = IntArray.of(10, 20, 30, 45).divide(10);
		    
		    assertArrayEquals(new int[] { 1, 2, 3, 4 }, intArray1.toArray());

		    IntArray intArray2 = IntArray.of(11, 12, 13, 14).divide(-3);
		    
		    assertArrayEquals(new int[] { -3, -4, -4, -4 }, intArray2.toArray());
            ```
        * Divide two IntArray's.
            ```
            IntArray intArray1 = IntArray.of(11, 12, 33, 14);
		    
		    IntArray intArray2 = IntArray.of(5, 6, 7, 8);

		    IntArray result = intArray1.divide(intArray2);
		    
		    assertArrayEquals(new int[] { 2, 2, 4, 1 }, result.toArray());
            ```
 8. Misc operations.
    * Sort IntArray.
        ```
        IntArray values = IntArray.of(110, 20, 310, 89).sort();
		
		assertArrayEquals(new int[] { 20, 89, 110, 310 }, values.toArray());
        ```
    * Remove all duplicate elements from IntArray.
        ```
        IntArray values = IntArray.of(7, 6, 3, 4, 5, 4, 5).distinct();
		
		assertArrayEquals(new int[] { 7, 6, 3, 4, 5 }, values.toArray());
        ```
    * Reverse elements in IntArray.
        ```
        IntArray values = IntArray.of(1, 2, 3, 4, 5).reverse();
		
		assertArrayEquals(new int[] { 5, 4, 3, 2, 1 }, values.toArray());
        ```
    * Find maximum value in IntArray.
        ```
        OptionalInt max = IntArray.of(110, 20, 310, 89).max();
		
		assertEquals(OptionalInt.of(310), max);
        ```
    * Find minimum value in IntArray.
        ```
        OptionalInt min = IntArray.of(110, 20, 310, 89).min();
		
		assertEquals(OptionalInt.of(20), min);
        ```
    * Summation of all values in IntArray.
        ```
        OptionalInt sum = IntArray.of(1, 2, 3, 4, 5).sum();
		
		assertEquals(OptionalInt.of(15), sum);
        ```
        
