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
import org.kuroneko.bbf.model.Abilities;
import org.kuroneko.bbf.model.BBFCharacter;

/**
 *
 * @author Tod Casasent
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Score implements Comparable<Score>
{

	/**
	 *
	 */
	@XmlAttribute(name = "mName", required = true)
	protected String mName = "";

	/**
	 *
	 */
	@XmlAttribute(name = "mBaseAbility", required = true)
	protected Abilities mBaseAbility = null;

	/**
	 *
	 */
	@XmlAttribute(name = "mMultiplier", required = true)
	protected double mMultiplier = 0.5;

	/**
	 *
	 */
	@XmlAttribute(name = "mPercPerLevel", required = true)
	protected int mPercPerLevel = 10;
	//

	/**
	 *
	 */
		@XmlElementWrapper(name="AspectList")
	@XmlElement(name="Aspect")
	protected TreeSet<Aspect> mAspects = null;

	/**
	 *
	 */
	public Score()
	{
		mName = "";
		mBaseAbility = null;
		mMultiplier = 0.5;
		mPercPerLevel = 10;
	}
	
	/**
	 *
	 * @param theSkill
	 * @param thePC
	 */
	public void init(Skill theSkill, BBFCharacter thePC)
	{
		for(Aspect aspect : mAspects)
		{
			aspect.init(this, theSkill, thePC);
		}
	}
	
	/**
	 *
	 * @return
	 */
	public String getDescription()
	{
		return "(" + mMultiplier + "(" + mBaseAbility.mShortName + ")+" + mPercPerLevel + ")";
	}
		
	/**
	 *
	 * @param thePC
	 * @param theSkill
	 * @param theAspect
	 * @return
	 */
	public int calcScore(BBFCharacter thePC, Skill theSkill, Aspect theAspect)
	{
		//System.out.println("Calc score for " + theAspect.getSkillName() + " - " + theAspect.getName());
		int score = 0;
		if ((true==theAspect.mSkilledRequired)&&(0==theSkill.mLevel))
		{
			score = 0;
		}
		else
		{
			int abil = thePC.getAbilityTotal(mBaseAbility);
			score = new Double(Math.ceil(abil*mMultiplier)).intValue() + (theAspect.mSkillCheckMod) +
					(theSkill.mPrimary?20:0) + (theSkill.mSecondary?10:0) + (theSkill.mLevel*mPercPerLevel);
		}
		return score;
	}

	@Override
	public int compareTo(Score o)
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
	public Abilities getAbility()
	{
		return mBaseAbility;
	}
	
	/**
	 *
	 * @return
	 */
	public double getMultiplier()
	{
		return mMultiplier;
	}
	
	/**
	 *
	 * @return
	 */
	public int getPerLevel()
	{
		return mPercPerLevel;
	}
	
	/**
	 *
	 * @return
	 */
	public TreeSet<Aspect> getAspects()
	{
		return mAspects;
	}
}
