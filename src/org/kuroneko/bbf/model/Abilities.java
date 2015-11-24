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

package org.kuroneko.bbf.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 *
 * @author Tod Casasent
 */
@XmlEnum
public enum Abilities
{

	/**
	 *
	 */
	@XmlEnumValue("STR")
	STR("STR", "Strength"), 
	
	/**
	 *
	 */
	@XmlEnumValue("DEX")
	DEX("DEX", "Dexterity"), 
	
	/**
	 *
	 */
	@XmlEnumValue("LOG")
	LOG("LOG", "Logic"), 
	
	/**
	 *
	 */
	@XmlEnumValue("WIL")
	WIL("WIL", "Willpower");

	/**
	 *
	 */
	public String mShortName = null;

	/**
	 *
	 */
	public String mLongName = null;

	private Abilities(String theShortName, String theLongName)
	{
		mShortName = theShortName;
		mLongName = theLongName;
	}
}
