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

import java.util.TreeMap;
import java.util.TreeSet;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import org.kuroneko.bbf.model.Abilities;
import org.kuroneko.bbf.model.BBFCharacter;
import org.kuroneko.bbf.model.AbilityChecks;

/**
 *
 * @author Tod Casasent
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Race implements Comparable<Race>
{

	/**
	 *
	 */
	@XmlAttribute(name = "mName", required = true)
	protected String mName = "";

	/**
	 *
	 */
	@XmlAttribute(name = "mMove", required = true)
	protected int mMove = 0;

	/**
	 *
	 */
	@XmlAttribute(name = "mDescriptors", required = true)
	protected int mDescriptors = 0;
	//

	/**
	 *
	 */
		@XmlElementWrapper(name="LanguageList")
	@XmlElement(name="Language")
	protected TreeSet<String> mLanguage = null;
	//

	/**
	 *
	 */
		@XmlElementWrapper(name="AbilityBonusList")
	@XmlElement(name="entry")
	protected TreeMap<Abilities, Integer> mAbilityBonus = null;
	//

	/**
	 *
	 */
		@XmlElementWrapper(name="RacialCharList")
	@XmlElement(name="entry")
	protected TreeMap<String, String> mRacialChars = null;
	//

	/**
	 *
	 */
		@XmlElementWrapper(name="AbilityChecksList")
	@XmlElement(name="entry")
	protected TreeMap<AbilityChecks, Integer> mAbilityChecks = null;
	//

	/**
	 *
	 */
		@XmlElementWrapper(name="SkillChecksList")
	@XmlElement(name="entry")
	protected TreeMap<String, Integer> mSkillChecks = null;
	//

	/**
	 *
	 */
		@XmlElementWrapper(name="SpecialList")
	@XmlElement(name="Special")
	protected TreeSet<Special> mSpecials = null;
	//

	/**
	 *
	 */
		@XmlElementWrapper(name="SkillsAsUnskilled")
	@XmlElement(name="entry")
	protected TreeSet<String> mSkillsAsUnskilled = null;
	//

	/**
	 *
	 */
		@XmlAttribute(name = "mSkillOneCount", required = true)
	protected int mSkillOneCount = 0;

	/**
	 *
	 */
	@XmlAttribute(name = "mPrimaryCount", required = true)
	protected int mPrimaryCount = 0;

	/**
	 *
	 */
	@XmlAttribute(name = "mSecondaryCount", required = true)
	protected int mSecondaryCount = 0;

	/**
	 *
	 */
	@XmlAttribute(name = "mLanguagePick")
	protected int mLanguagePick = 0;

	/**
	 *
	 */
	@XmlAttribute(name = "mNativeLanguageCostMod")
	protected int mNativeLanguageCostMod = 0;

	/**
	 *
	 */
	@XmlAttribute(name = "mBPMod")
	protected int mBPMod = 0;

	/**
	 *
	 */
	@XmlAttribute(name = "mEquipCostMod")
	protected double mEquipCostMod = 1.0;

	/**
	 *
	 */
	@XmlAttribute(name = "mWeaponDamageMulti")
	protected double mWeaponDamageMulti = 1.0;
	//

	/**
	 *
	 */
		@XmlElementWrapper(name="SkillAtLevel")
	@XmlElement(name="entry")
	protected TreeMap<String, Integer> mSkillAtLevel = null;
	//

	/**
	 *
	 */
		@XmlAttribute(name = "mPrimarySkill")
	protected String mPrimarySkill = "";

	/**
	 *
	 */
	@XmlAttribute(name = "mProhibitedSkill")
	protected String mProhibitedSkill = "";

	/**
	 *
	 */
	public Race()
	{
		mName = "";
		mMove = 0;
		mDescriptors = 0;
		mLanguage = new TreeSet<>();
		mAbilityBonus = new TreeMap<>();
		mRacialChars = new TreeMap<>();
		mAbilityChecks = new TreeMap<>();
		mSkillOneCount = 0;
		mPrimaryCount = 0;
		mSecondaryCount = 0;
		mSpecials = new TreeSet<>();
		mLanguagePick = 0;
		mNativeLanguageCostMod = 0;
		mBPMod = 0;
		mEquipCostMod = 1.0;
		mWeaponDamageMulti = 1.0;
		mSkillAtLevel = new TreeMap<>();
		mPrimarySkill = "";
		mProhibitedSkill = "";
		mSkillsAsUnskilled = new TreeSet<>();
		mSkillChecks = new TreeMap<>();
	}

	@Override
	public int compareTo(Race o)
	{
		return this.mName.compareTo(o.mName);
	}

	/**
	 *
	 * @return
	 */
	public String getName()
	{
		return mName;
	}

	/**
	 *
	 * @return
	 */
	public int getMove()
	{
		return mMove;
	}

	/**
	 *
	 * @return
	 */
	public int getDescriptors()
	{
		return mDescriptors;
	}

	/**
	 *
	 * @return
	 */
	public TreeSet<String> getLanguage()
	{
		return mLanguage;
	}

	/**
	 *
	 * @return
	 */
	public TreeMap<Abilities, Integer> getAbilityBonus()
	{
		return mAbilityBonus;
	}

	/**
	 *
	 * @return
	 */
	public TreeMap<String, String> getRacialChars()
	{
		return mRacialChars;
	}

	/**
	 *
	 * @return
	 */
	public TreeMap<AbilityChecks, Integer> getAbilityChecks()
	{
		return mAbilityChecks;
	}

	/**
	 *
	 * @return
	 */
	public TreeSet<Special> getSpecials()
	{
		return mSpecials;
	}
	
	/**
	 *
	 * @return
	 */
	public int getSkillOneCount()
	{
		return mSkillOneCount;
	}

	/**
	 *
	 * @return
	 */
	public int getPrimaryCount()
	{
		return mPrimaryCount;
	}

	/**
	 *
	 * @return
	 */
	public int getSecondaryCount()
	{
		return mSecondaryCount;
	}
	
	/**
	 *
	 * @param thePC
	 * @throws Exception
	 */
	public void applyRace(BBFCharacter thePC) throws Exception
	{
		for(Special sp : mSpecials)
		{
			sp.applyRace(thePC);
		}
	}
	
	/**
	 *
	 * @return
	 */
	public int getBPMod()
	{
		return mBPMod;
	}
	
	/**
	 *
	 * @return
	 */
	public int getLanguagePick()
	{
		return mLanguagePick;
	}
	
	/**
	 *
	 * @return
	 */
	public int getNativeLanguageCostMod()
	{
		return mNativeLanguageCostMod;
	}
	
	/**
	 *
	 * @return
	 */
	public TreeMap<String, Integer> getSkillChecks()
	{
		return mSkillChecks;
	}
	
	/**
	 *
	 * @return
	 */
	public TreeSet<String> getSkillsAsUnskilled()
	{
		return mSkillsAsUnskilled;
	}

	/**
	 *
	 * @return
	 */
	public double getWeaponDamageMulti()
	{
		return mWeaponDamageMulti;
	}
	
	/**
	 *
	 * @return
	 */
	public double getEquipCostMod()
	{
		return mEquipCostMod;
	}
	
	/**
	 *
	 * @return
	 */
	public String getPrimarySkill()
	{
		return mPrimarySkill;
	}
	
	/**
	 *
	 * @return
	 */
	public String getProhibitedSkill()
	{
		return mProhibitedSkill;
	}
	
	/**
	 *
	 * @return
	 */
	public TreeMap<String, Integer> getSkillAtLevel()
	{
		return mSkillAtLevel;
	}
	
	/**
	 *
	 * @return
	 */
	public Special getRacialOption()
	{
		Special sp = null;
		for (Special spec : mSpecials)
		{
			if ("Ability".equals(spec.mType))
			{
				sp = spec;
			}
			else if ("RacialAbility".equals(spec.mType))
			{
				sp = spec;
			}
			else if ("Skills".equals(spec.mType))
			{
				sp = spec;
			}
		}
		return sp;
	}
}
