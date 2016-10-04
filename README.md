# muse-collect

![Under major construction](http://www.wauchopeshowsociety.com.au/images/sl_website-under-construction.jpeg)

muse-collect is born out of curiosity. Working with arrays has always being difficult. This library aims to solve that problem. Currently, several of classes has Fluent API to interact with arrays. From Wiki, in software engineering, a fluent interface (as first coined by Eric Evans and Martin Fowler) is an implementation of an object oriented API that aims to provide more readable code.

At the heart of this library lies 3 important classes i.e. ```IntArray```, ```LongArray``` and ```DoubleArray``` which encapsulates ```int[]```, ```long[]``` and ```double[]``` respectively. One additional detail about this 3 classes is that they all are Immutable.

Let us start with an example of what this library aims in terms of arrays. For example we will use class ```IntArray```.
 1. Creating an instance of class.
    * Use Static factory to create an instance. If argument is null NullPointerException is thrown.
        ```
        IntArray intArray0 = IntArray.of(new int[] {});//contains int[] with length 0.
        
        IntArray intArray1 = IntArray.of(1);//contains int[] with value as 1.
        
        IntArray intArray2 = IntArray.of(1, 2);//contains int[] with values as 1 and 2.
        
        IntArray intArray3 = IntArray.of(1, 2, 3);//contains int[] with values as 1, 2 and 3.
        
        IntArray intArray4 = IntArray.of(1, 2, 3, 4);//contains int[] with values as 1, 2, 3 and 4.
        
        IntArray intArrayN = IntArray.of(new int[] { 1, 2, 3, 4, 5, 6, 7, 8 });//The specified array in parameter is copied to new array and stored in IntArray.
        
        IntArray intArrayColl = IntArray.of(Arrays.asList(1, 2, 3, 4));//The specified list in parameter is copied to new array and stored in IntArray.
        
        IntArray zeros = IntArray.zeros(10);//array of length 10 filled with 0's.
        
        IntArray ones = IntArray.ones(10);//array of length 10 filled with 1's.
        
        IntArray values = IntArray.ofValue(-1, 10); //array of length 10 filled with -1's.
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
 5. Bridge.
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
