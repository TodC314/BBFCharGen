/*
* Licensing
* This software is licensed under the Creative Commons Attribution-Noncommercial-ShareAlike 3.0 Unported License; (CC BY-NC-SA 3.0); Some Rights Reserved.
* BareBones Fantasy™ and Keranak Kingdoms™ are copyright 2012, and are trademarks of DwD Studios. 
* These trademarks are used under the Creative Commons Attribution-Noncommercial-ShareAlike 3.0 Unported License; (CC BY-NC-SA 3.0); Some Rights Reserved.
* To view a copy of this license, visit: http://creativecommons.org/licenses/by-nc-sa/3.0
* All data is (CC BY-NC-SA 3.0) and used with permission.
* Basic game setup, races, and Decahedron Descriptors are from DwDStudios http://dwdstudios.com/ (They also make Covert Ops)
* Available 1000 Descriptors by Mark Hassman http://mithrilandmages.com (check out his cool NPC/character generators)
*/

package org.kuroneko.bbf;

import java.util.Random;

/**
 *
 * @author Tod Casasent
 */
public class Dice
{

	/**
	 *
	 */
	protected static Random M_RAND = new Random();
	
	/**
	 *
	 * @return
	 */
	public static int roll2D10()
	{
		int retVal = (roll10()*10) + roll10();
		return retVal;
	}
	
	/**
	 *
	 * @return
	 */
	protected static int roll10()
	{
		// inefficient - done this way to demonstrate how to use
		int max = 9;
		int min = 0;
		return (M_RAND.nextInt((max-min) + 1) + min);
	}

	/**
	 *
	 * @param theSize
	 * @param theCount
	 * @return
	 */
	public static int rollSum(int theSize, int theCount)
	{
		int retVal = 0;
		for(int x=0;x<theCount;x++)
		{
			retVal = retVal + roll(theSize);
		}
		return retVal;
	}
	
	/**
	 *
	 * @param theSize
	 * @return
	 */
	protected static int roll(int theSize)
	{
		// inefficient - done this way to demonstrate how to use
		int max = theSize;
		int min = 1;
		return (M_RAND.nextInt((max-min) + 1) + min);
	}

}
