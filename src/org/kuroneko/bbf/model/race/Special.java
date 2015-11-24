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

package org.kuroneko.bbf.model.race;

import java.util.Map.Entry;
import java.util.TreeMap;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import org.kuroneko.bbf.model.BBFCharacter;

/**
 *
 * @author Tod Casasent
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Special implements Comparable<Special>
{

	/**
	 *
	 */
	@XmlAttribute(name = "mType", required = true)
	protected String mType = "";

	/**
	 *
	 */
	@XmlAttribute(name = "mName", required = false)
	protected String mName = "";

	/**
	 *
	 */
	@XmlAttribute(name = "mSkill", required = false)
	protected String mSkill = "";

	/**
	 *
	 */
	@XmlAttribute(name = "mAspect", required = false)
	protected String mAspect = "";

	/**
	 *
	 */
	@XmlAttribute(name = "mUnskilled", required = false)
	protected boolean mUnskilled = false;

	/**
	 *
	 */
	@XmlAttribute(name = "mCount", required = false)
	protected int mCount = -1;

	/**
	 *
	 */
	@XmlAttribute(name = "mLevel", required = false)
	protected String mLevel = "";

	/**
	 *
	 */
	@XmlAttribute(name = "mCost", required = false)
	protected int mCost = -1;

	/**
	 *
	 */
	@XmlAttribute(name = "mDice", required = true)
	protected String mDice = "";

	/**
	 *
	 */
	@XmlAttribute(name = "mDescription", required = false)
	protected String mDescription = "";

	/**
	 *
	 */
	@XmlAttribute(name = "mMod", required = false)
	protected int mMod = -1;

	/**
	 *
	 */
	@XmlAttribute(name = "mCause", required = false)
	protected String mCause = "";

	/**
	 *
	 */
	@XmlAttribute(name = "mCondition", required = false)
	protected String mCondition = "";

	/**
	 *
	 */
	@XmlAttribute(name = "mWeapon", required = false)
	protected String mWeapon = "";

	/**
	 *
	 */
	@XmlAttribute(name = "mToHit", required = false)
	protected String mToHit = "";

	/**
	 *
	 */
	@XmlAttribute(name = "mCheck", required = false)
	protected String mCheck = "";

	/**
	 *
	 */
	@XmlAttribute(name = "mAbility", required = false)
	protected String mAbility = "";

	/**
	 *
	 */
	@XmlAttribute(name = "mMulti", required = false)
	protected double mMulti = -1.0;

	/**
	 *
	 */
	@XmlAttribute(name = "mStack", required = false)
	protected boolean mStack = false;

	/**
	 *
	 */
	@XmlAttribute(name = "mWearOther", required = false)
	protected boolean mWearOther = false;

	/**
	 *
	 */
	@XmlAttribute(name = "mUpgrade", required = false)
	protected int mUpgrade = -1;
	//

	/**
	 *
	 */
		@XmlElementWrapper(name="Options")
	@XmlElement(name="entry")
	protected TreeMap<String, Integer> mOptions = null;
	
	/**
	 *
	 */
	public Special()
	{
		mOptions = new TreeMap<>();
	}

	@Override
	public int compareTo(Special o)
	{
		return mType.compareTo(o.mType);
	}
	
	/**
	 *
	 * @return
	 * @throws Exception
	 */
	public String describeSpecial() throws Exception
	{
		String result = "";
		if ("Ability".equals(mType))
		{
			result = "Select from " + mType;
			for (Entry<String, Integer> entry : mOptions.entrySet())
			{
				result = result + " " + entry.getKey() + "(" + entry.getValue() + ")";
			}
		}
		else if ("RacialAbility".equals(mType))
		{
			result = "Select from " + mType;
			for (Entry<String, Integer> entry : mOptions.entrySet())
			{
				result = result + " " + entry.getKey() + "(" + entry.getValue() + ")";
			}
		}
		else if ("Shift".equals(mType))
		{
			result = "During " + mType;
			for (Entry<String, Integer> entry : mOptions.entrySet())
			{
				result = result + " " + entry.getKey() + "(" + entry.getValue() + ")";
			}
		}
		else if ("Skills".equals(mType))
		{
			result = "Select from " + mType;
			for (Entry<String, Integer> entry : mOptions.entrySet())
			{
				result = result + " " + entry.getKey() + "(" + entry.getValue() + ")";
			}
		}
		else if ("Weapon".equals(mType))
		{
			result = mType + " " + mName + "(" + mDice + ") " + mSkill;
		}
		else if ("Effect".equals(mType))
		{
			result = mType + " " + mName + "(" + mDescription + ")";
		}
		else if ("Spellcaster (any armor, any STR)".equals(mType))
		{
			result = mType;
		}
		else if ("Spellcaster (low wizardry, unskilled)".equals(mType))
		{
			result = mType;
		}
		else if ("Spellcaster (chain or lighter, any STR)".equals(mType))
		{
			result = mType;
		}
		else if ("Disadvantage".equals(mType))
		{
			result = mType + " " + mCause + "(" + (("".equals(mCondition))?mMulti:mCondition) + ")";
		}
		else if ("WeaponBonus".equals(mType))
		{
			result = mType + " " + mWeapon + "(" + mToHit + ")";
		}
		else if ("TargetCheck".equals(mType))
		{
			result = mType + " " + mCheck + "(" + mMod + ")";
		}
		else if ("Required Armor Str".equals(mType))
		{
			result = mType + " " + "(" + mMod + ")";
		}
		else if ("Required Weapon Str".equals(mType))
		{
			result = mType + " " + "(" + mMod + ")";
		}
		else if ("Fly".equals(mType))
		{
			result = mType + "(" + mMod + ")" + " " + mDescription;
		}
		else if ("Armor".equals(mType))
		{
			result = mType + " " + mName + "(" + mMod + ")" + " stackable(" + mStack + ")" + " wear-other-armor(" + mWearOther + ")" + " upgradeCostMulti(" + mUpgrade + ")";
		}
		else if ("Swim".equals(mType))
		{
			result = mType + "(" + mMod + ")";
		}
		else if ("Immune".equals(mType))
		{
			result = mType + "(" + mName + ")";
		}
		else if ("Leap".equals(mType))
		{
			result = mType + "(" + mMulti + ")";
		}
		else if ("Leap (flat footed)".equals(mType))
		{
			result = mType + "(" + mMulti + ")";
		}
		else if ("Move".equals(mType))
		{
			result = mType + "(" + mMod + ") " + mDescription;
		}
		else if ("Invisible to Mortals".equals(mType))
		{
			result = mType + "(" + mMod + ")";
		}
		else if ("Move Desert Sand".equals(mType))
		{
			result = mType + "(" + mMod + ")";
		}
		else
		{
			throw new Exception("'" + mType + "' not a recognized Special type");
		}
		return result;
	}
	
	/**
	 *
	 * @param thePC
	 * @throws Exception
	 */
	public void applyRace(BBFCharacter thePC) throws Exception
	{
		// TODO: applyRace - for future other race settings
	}

	/**
	 *
	 * @return
	 * @throws Exception
	 */
	public TreeMap<String, Integer> getSpecialOptions() throws Exception
	{
		TreeMap<String, Integer> result = null;
		if ("Ability".equals(mType))
		{
			result = mOptions;
		}
		else if ("RacialAbility".equals(mType))
		{
			result = mOptions;
		}
		else if ("Skills".equals(mType))
		{
			result = mOptions;
		}
		else
		{
			throw new Exception("'" + mType + "' not a recognized Special type");
		}
		return result;
	}
	
	/**
	 *
	 * @return
	 */
	public String getType()
	{
		return mType;
	}
}
