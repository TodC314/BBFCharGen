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
public enum AbilityChecks
{

	/**
	 *
	 */
	@XmlEnumValue("STR_ALL")
	STR_ALL(Abilities.STR, "all"),
	
	/**
	 *
	 */
	@XmlEnumValue("STR_LIFT")
	STR_LIFT(Abilities.STR, "lift"),
	
	/**
	 *
	 */
	@XmlEnumValue("STR_MAGIC")
	STR_MAGIC(Abilities.STR, "magic"),
	
	/**
	 *
	 */
	@XmlEnumValue("STR_EXPOSURE")
	STR_EXPOSURE(Abilities.STR, "exposure"),
	
	/**
	 *
	 */
	@XmlEnumValue("STR_POISON")
	STR_POISON(Abilities.STR, "poison"),

	/**
	 *
	 */
	@XmlEnumValue("DEX_ALL")
	DEX_ALL(Abilities.DEX, "all"),

	/**
	 *
	 */
	@XmlEnumValue("DEX_MAGIC")
	DEX_MAGIC(Abilities.DEX, "magic"),

	/**
	 *
	 */
	@XmlEnumValue("DEX_JUMP")
	DEX_JUMP(Abilities.DEX, "jump"),

	/**
	 *
	 */
	@XmlEnumValue("DEX_DODGE")
	DEX_DODGE(Abilities.DEX, "dodge"),

	/**
	 *
	 */
	@XmlEnumValue("DEX_PARRY")
	DEX_PARRY(Abilities.DEX, "parry"),

	/**
	 *
	 */
	@XmlEnumValue("LOG_ALL")
	LOG_ALL(Abilities.LOG, "all"),

	/**
	 *
	 */
	@XmlEnumValue("LOG_PERCEIVE")
	LOG_PERCEIVE(Abilities.LOG, "perceive"),

	/**
	 *
	 */
	@XmlEnumValue("LOG_DEDUCE")
	LOG_DEDUCE(Abilities.LOG, "deduce"),

	/**
	 *
	 */
	@XmlEnumValue("LOG_CONFUSION")
	LOG_CONFUSION(Abilities.LOG, "confusion"),

	/**
	 *
	 */
	@XmlEnumValue("LOG_BUILDNEW")
	LOG_BUILDNEW(Abilities.LOG, "build new things"),

	/**
	 *
	 */
	@XmlEnumValue("LOG_MAGIC")
	LOG_MAGIC(Abilities.LOG, "magic"),

	/**
	 *
	 */
	@XmlEnumValue("WIL_ALL")
	WIL_ALL(Abilities.WIL, "all"),

	/**
	 *
	 */
	@XmlEnumValue("WIL_BLUFF")
	WIL_BLUFF(Abilities.WIL, "bluff"),

	/**
	 *
	 */
	@XmlEnumValue("WIL_FINESSE")
	WIL_FINESSE(Abilities.WIL, "finesse"),

	/**
	 *
	 */
	@XmlEnumValue("WIL_MAGIC")
	WIL_MAGIC(Abilities.WIL, "magic"),

	/**
	 *
	 */
	@XmlEnumValue("WIL_PERSUADE")
	WIL_PERSUADE(Abilities.WIL, "persuade"),

	/**
	 *
	 */
	@XmlEnumValue("WIL_INTIMIDATE")
	WIL_INTIMIDATE(Abilities.WIL, "intimidate"),

	/**
	 *
	 */
	@XmlEnumValue("WIL_CHARMSPELLS")
	WIL_CHARMSPELLS(Abilities.WIL, "charm spells"),

	/**
	 *
	 */
	@XmlEnumValue("WIL_FEAR")
	WIL_FEAR(Abilities.WIL, "fear"),

	/**
	 *
	 */
	@XmlEnumValue("WIL_TARGETEDMAGIC")
	WIL_TARGETEDMAGIC(Abilities.WIL, "magic targetting character"),
	
	/**
	 *
	 */
	@XmlEnumValue("WIL_SOCIAL")
	WIL_SOCIAL(Abilities.WIL, "social interactions"),
	
	/**
	 *
	 */
	@XmlEnumValue("WIL_SOCIALCIV")
	WIL_SOCIALCIV(Abilities.WIL, "social interactions with civilized races"),
	
	/**
	 *
	 */
	@XmlEnumValue("WIL_SOCIALSMALLER")
	WIL_SOCIALSMALLER(Abilities.WIL, "social interactions with smaller races"),
	
	// multi value

	/**
	 *
	 */
		@XmlEnumValue("DEX_CLIMBTREE")
	DEX_CLIMBTREE(Abilities.DEX, "climb tree"),

	/**
	 *
	 */
	@XmlEnumValue("STR_CLIMBTREE")
	STR_CLIMBTREE(Abilities.STR, "climb tree"),
	
	/**
	 *
	 */
	@XmlEnumValue("DEX_CLIMB")
	DEX_CLIMB(Abilities.DEX, "climb"),

	/**
	 *
	 */
	@XmlEnumValue("STR_CLIMB")
	STR_CLIMB(Abilities.STR, "climb"),
	
	/**
	 *
	 */
	@XmlEnumValue("DEX_SWIM")
	DEX_SWIM(Abilities.DEX, "swim"),

	/**
	 *
	 */
	@XmlEnumValue("STR_SWIM")
	STR_SWIM(Abilities.STR, "swim");

	/**
	 *
	 */
	public Abilities mAbility = null;

	/**
	 *
	 */
	public String mName = null;

	private AbilityChecks(Abilities theAbility, String theName)
	{
		mAbility = theAbility;
		mName = theName;
	}
}
