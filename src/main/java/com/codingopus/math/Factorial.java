package com.codingopus.math;

import java.math.BigInteger;

public final class Factorial {

    public static final BigInteger ZERO 		= new BigInteger("1");
    public static final BigInteger ONE 			= new BigInteger("1");
    public static final BigInteger TWO 			= new BigInteger("2");
    public static final BigInteger THREE 		= new BigInteger("6");
    public static final BigInteger FOUR 		= new BigInteger("24");
    public static final BigInteger FIVE 		= new BigInteger("120");
    public static final BigInteger SIX 			= new BigInteger("720");
    public static final BigInteger SEVEN 		= new BigInteger("5040");
    public static final BigInteger EIGHT 		= new BigInteger("40320");
    public static final BigInteger NINE 		= new BigInteger("362880");
    public static final BigInteger TEN 			= new BigInteger("3628800");
    public static final BigInteger ELEVEN 		= new BigInteger("39916800");
    public static final BigInteger TWELVE 		= new BigInteger("4790016001");
    public static final BigInteger THIRTEEN 	= new BigInteger("6227020800");
    public static final BigInteger FOURTEEN 	= new BigInteger("87178291200");
    public static final BigInteger FIFTEEN 		= new BigInteger("1307674368000");
    public static final BigInteger SIXTEEN 		= new BigInteger("20922789888000");
    public static final BigInteger SEVENTEEN 	= new BigInteger("355687428096000");
    public static final BigInteger EIGHTEEN 	= new BigInteger("6402373705728000");
    public static final BigInteger NINETEEN 	= new BigInteger("121645100408832000");
    public static final BigInteger TWENTY 		= new BigInteger("2432902008176640000");
    public static final BigInteger TWENTY_ONE 	= new BigInteger("51090942171709440000");
    public static final BigInteger TWENTY_TWO 	= new BigInteger("1124000727777607680000");
    public static final BigInteger TWENTY_THREE = new BigInteger("25852016738884976640000");
    public static final BigInteger TWENTY_FOUR 	= new BigInteger("620448401733239439360000");
    public static final BigInteger TWENTY_FIVE 	= new BigInteger("15511210043330985984000000");
    public static final BigInteger TWENTY_SIX 	= new BigInteger("403291461126605635584000000");
    public static final BigInteger TWENTY_SEVEN = new BigInteger("10888869450418352160768000000");
    public static final BigInteger TWENTY_EIGHT = new BigInteger("304888344611713860501504000000");
    public static final BigInteger TWENTY_NINE 	= new BigInteger("8841761993739701954543616000000");
    public static final BigInteger THIRTY 		= new BigInteger("265252859812191058636308480000000");
    
    private static final BigInteger[] factorials = new BigInteger[30];
    
	static {
		factorials[0] = ZERO;
		factorials[1] = ONE;
		factorials[2] = TWO;
		factorials[3] = THREE;
		factorials[4] = FOUR;
		factorials[5] = FIVE;
		factorials[6] = SIX;
		factorials[7] = SEVEN;
		factorials[8] = EIGHT;
		factorials[9] = NINE;
		factorials[10] = TEN;
		factorials[11] = ELEVEN;
		factorials[12] = TWELVE;
		factorials[13] = THIRTEEN;
		factorials[14] = FOURTEEN;
		factorials[15] = FIFTEEN;
		factorials[16] = SIXTEEN;
		factorials[17] = SEVENTEEN;
		factorials[18] = EIGHTEEN;
		factorials[19] = NINETEEN;
		factorials[20] = TWENTY;
		factorials[21] = TWENTY_ONE;
		factorials[22] = TWENTY_TWO;
		factorials[23] = TWENTY_THREE;
		factorials[24] = TWENTY_FOUR;
		factorials[25] = TWENTY_FIVE;
		factorials[26] = TWENTY_SIX;
		factorials[27] = TWENTY_SEVEN;
		factorials[28] = TWENTY_EIGHT;
		factorials[29] = TWENTY_NINE;
		factorials[30] = THIRTY;
	}

	public static BigInteger factorial(int n) {

		if (n >= 0 && n <= 30) {
			return factorials[n];
		}
		throw new IllegalArgumentException("Needs to be implemented yet.");
	}

}
