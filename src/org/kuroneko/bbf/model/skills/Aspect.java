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

package org.kuroneko.bbf.model.skills;

import java.util.TreeSet;
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
public class Aspect implements Comparable<Aspect>
{

	/**
	 *
	 */
	@XmlAttribute(name = "mName", required = true)
	protected String mName = "";

	/**
	 *
	 */
	@XmlAttribute(name = "mSelectPerLevel", required = false)
	protected int mSelectPerLevel = 0;

	/**
	 *
	 */
	@XmlElementWrapper(name="SelectPerLevelList")
	@XmlElement(name="entry")
	protected TreeSet<String> mSelectPerLevelList = null;

	/**
	 *
	 */
	@XmlAttribute(name = "mSpecialFlag", required = false)
	protected String mSpecialFlag = "";

	/**
	 *
	 */
	@XmlAttribute(name = "mEvenOnly", required = false)
	protected boolean mEvenOnly = false;

	/**
	 *
	 */
	@XmlAttribute(name = "mBonus", required = false)
	protected int mBonus = 0;

	/**
	 *
	 */
	@XmlAttribute(name = "mPrimaryPerLevel", required = false)
	protected int mPrimaryPerLevel = 0;

	/**
	 *
	 */
	@XmlAttribute(name = "mSkilledRequired", required = true)
	protected boolean mSkilledRequired = false;
	//

	/**
	 *
	 */
		protected Score mScore = null;

	/**
	 *
	 */
	protected Skill mSkill = null;

	/**
	 *
	 */
	protected BBFCharacter mPC = null;

	/**
	 *
	 */
	protected int mSkillCheckMod = 0;
	//

	/**
	 *
	 */
		protected TreeSet<String> mSelectedExtras = null;

	/**
	 *
	 */
	public Aspect()
	{
		mName = "";
		mSelectPerLevel = 0;
		mSelectPerLevelList = new TreeSet<>();
		mSpecialFlag= "";
		mSelectedExtras = new TreeSet<>();
		mSkilledRequired = false;
	}
	
	/**
	 *
	 * @param theScore
	 * @param theSkill
	 * @param thePC
	 */
	public void init(Score theScore, Skill theSkill, BBFCharacter thePC)
	{
		mScore = theScore;
		mSkill = theSkill;
		mPC = thePC;
	}

	@Override
	public int compareTo(Aspect o)
	{
		int compareTo = 0;
		if (mSkill!=null)
		{
			compareTo = mSkill.mName.compareTo(o.mSkill.mName);
		}
		if (0==compareTo)
		{
			compareTo = this.mName.compareTo(o.mName);
		}
		return compareTo;
	}

	/**
	 *
	 * @return
	 */
	public String getSkillName()
	{
		String name = mSkill.getName();
		if (false==name.equals(mScore.getName()))
		{
			name = name + ": " + mScore.getName();
		}
		return name;
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
	public int getScore()
	{
		return mScore.calcScore(mPC, mSkill, this);
	}
	
	/**
	 *
	 * @return
	 */
	public int getLevel()
	{
		return mSkill.getLevel();
	}
	
	/**
	 *
	 * @return
	 */
	public boolean getSkilledRequired()
	{
		return mSkilledRequired;
	}
	
	/**
	 *
	 * @return
	 */
	public boolean getPrimary()
	{
		return mSkill.getPrimary();
	}
	
	/**
	 *
	 * @return
	 */
	public boolean getSecondary()
	{
		return mSkill.getSecondary();
	}
	
	/**
	 *
	 * @return
	 */
	public String getDescription()
	{
		return mScore.getDescription() + ((true==mSkilledRequired)?"*":"");
	}

	/**
	 *
	 * @return
	 */
	public int getSkillCheckMod()
	{
		return mSkillCheckMod;
	}

	/**
	 *
	 * @param mSkillCheckMod
	 */
	public void setSkillCheckMod(int mSkillCheckMod)
	{
		this.mSkillCheckMod = mSkillCheckMod;
	}
	
	/**
	 *
	 */
	public void setSkillsAsUnskilled()
	{
		this.mSkilledRequired = false;
	}

	/**
	 *
	 * @return
	 */
	public int getSelectPerLevel()
	{
		return mSelectPerLevel;
	}
	
	/**
	 *
	 * @return
	 */
	public TreeSet<String> getSelectPerLevelList()
	{
		return mSelectPerLevelList;
	}
	
	/**
	 *
	 * @return
	 */
	public int getAllowedSelections()
	{
		int allowedSelections = 0;
		boolean skilledOnly = getSkilledRequired();
		int level = getLevel();
		int perLevel = mSelectPerLevel;
		// fix to add using primary per level options
		if (mPrimaryPerLevel>0)
		{
			if (mPC.getSkill("Spellcaster").getPrimary())
			{
				level = mPrimaryPerLevel;
			}
		}
		boolean even = mEvenOnly;
		if ((true==skilledOnly)&&(level<1))
		{
			// cannot use
			allowedSelections = 0;
		}
		else
		{
			if (true==even)
			{
				// 1 is for level 1
				allowedSelections = (1 + (int)(level / 2))*perLevel;
			}
			else
			{
				allowedSelections = level*perLevel;
			}
		}
		return allowedSelections;
	}
	
}
