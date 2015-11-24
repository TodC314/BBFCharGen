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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.xml.bind.JAXBException;
import org.kuroneko.bbf.Dice;
import org.kuroneko.bbf.model.moralcode.MoralCodeFactory;
import org.kuroneko.bbf.model.race.Race;
import org.kuroneko.bbf.model.race.RaceFactory;
import org.kuroneko.bbf.model.skills.Aspect;
import org.kuroneko.bbf.model.skills.Score;
import org.kuroneko.bbf.model.skills.Skill;
import org.kuroneko.bbf.model.skills.SkillFactory;

/**
 *
 * @author Tod Casasent
 */
public class BBFCharacter
{

	/**
	 *
	 * @throws IOException
	 * @throws JAXBException
	 */
	public BBFCharacter() throws IOException, JAXBException
	{
		// Races
		mRaces = RaceFactory.loadRaces();
		// setRace will load mSkills too
		setRace("Human");
		// Abilities
		setDefaultAbilities();
		// Moral Code
		setupMoralCode();
	}
		
	/**
	 *
	 * @return
	 */
	public int calcInit()
	{
		return 1 + 
				(getAbilityTotal(Abilities.LOG)>=65?1:0) +
				(getAbilityTotal(Abilities.DEX)>=65?1:0);
	}

	////////////////////////////////////
	// Moral Code
	////////////////////////////////////
	
	/**
	 *
	 */
		
	protected MoralCodeFactory mMoralCode = null;

	/**
	 *
	 */
	protected String mKindOrCruelDegree = null;

	/**
	 *
	 */
	protected String mKindOrCruelSelect = null;

	/**
	 *
	 */
	protected String mFocusedOrUnfocusedDegree = null;

	/**
	 *
	 */
	protected String mFocusedOrUnfocusedSelect = null;

	/**
	 *
	 */
	protected String mSelflessOrSelfishDegree = null;

	/**
	 *
	 */
	protected String mSelflessOrSelfishSelect = null;

	/**
	 *
	 */
	protected String mHonorableOrDeceitfulDegree = null;

	/**
	 *
	 */
	protected String mHonorableOrDeceitfulSelect = null;

	/**
	 *
	 */
	protected String mBraveOrCowardlyDegree = null;

	/**
	 *
	 */
	protected String mBraveOrCowardlySelect = null;
	
	/**
	 *
	 * @throws IOException
	 * @throws JAXBException
	 */
	final public void setupMoralCode() throws IOException, JAXBException
	{
		mMoralCode = MoralCodeFactory.loadMoralCode();
		mKindOrCruelDegree = mMoralCode.mDegree.get(Dice.rollSum(3, 1)-1);
		mKindOrCruelSelect = mMoralCode.mMoralCodeA.get(Dice.rollSum(2, 1)-1);
		mFocusedOrUnfocusedDegree = mMoralCode.mDegree.get(Dice.rollSum(3, 1)-1);
		mFocusedOrUnfocusedSelect = mMoralCode.mMoralCodeB.get(Dice.rollSum(2, 1)-1);
		mSelflessOrSelfishDegree = mMoralCode.mDegree.get(Dice.rollSum(3, 1)-1);
		mSelflessOrSelfishSelect = mMoralCode.mMoralCodeC.get(Dice.rollSum(2, 1)-1);
		mHonorableOrDeceitfulDegree = mMoralCode.mDegree.get(Dice.rollSum(3, 1)-1);
		mHonorableOrDeceitfulSelect = mMoralCode.mMoralCodeD.get(Dice.rollSum(2, 1)-1);
		mBraveOrCowardlyDegree = mMoralCode.mDegree.get(Dice.rollSum(3, 1)-1);
		mBraveOrCowardlySelect = mMoralCode.mMoralCodeE.get(Dice.rollSum(2, 1)-1);
	}
	
	/**
	 *
	 * @return
	 */
	public ArrayList<String> getDegreeList()
	{
		return mMoralCode.mDegree;
	}
	
	/**
	 *
	 * @return
	 */
	public ArrayList<String> getKindOrCruelSelectList()
	{
		return mMoralCode.mMoralCodeA;
	}
	
	/**
	 *
	 * @return
	 */
	public ArrayList<String> getFocusedOrUnfocusedSelectList()
	{
		return mMoralCode.mMoralCodeB;
	}
	
	/**
	 *
	 * @return
	 */
	public ArrayList<String> getSelflessOrSelfishSelectList()
	{
		return mMoralCode.mMoralCodeC;
	}
	
	/**
	 *
	 * @return
	 */
	public ArrayList<String> getHonorableOrDeceitfulSelectList()
	{
		return mMoralCode.mMoralCodeD;
	}
	
	/**
	 *
	 * @return
	 */
	public ArrayList<String> getBraveOrCowardlySelectList()
	{
		return mMoralCode.mMoralCodeE;
	}
	
	/**
	 *
	 * @return
	 */
	public String getKindOrCruelDegree()
	{
		return mKindOrCruelDegree;
	}
	
	/**
	 *
	 * @return
	 */
	public String getFocusedOrUnfocusedDegree()
	{
		return mFocusedOrUnfocusedDegree;
	}
	
	/**
	 *
	 * @return
	 */
	public String getSelflessOrSelfishDegree()
	{
		return mSelflessOrSelfishDegree ;
	}
	
	/**
	 *
	 * @return
	 */
	public String getHonorableOrDeceitfulDegree()
	{
		return mHonorableOrDeceitfulDegree;
	}
	
	/**
	 *
	 * @return
	 */
	public String getBraveOrCowardlyDegree()
	{
		return mBraveOrCowardlyDegree;
	}
	
	/**
	 *
	 * @return
	 */
	public String getKindOrCruelSelect()
	{
		return mKindOrCruelSelect;
	}
	
	/**
	 *
	 * @return
	 */
	public String getFocusedOrUnfocusedSelect()
	{
		return mFocusedOrUnfocusedSelect;
	}
	
	/**
	 *
	 * @return
	 */
	public String getSelflessOrSelfishSelect()
	{
		return mSelflessOrSelfishSelect ;
	}
	
	/**
	 *
	 * @return
	 */
	public String getHonorableOrDeceitfulSelect()
	{
		return mHonorableOrDeceitfulSelect;
	}
	
	/**
	 *
	 * @return
	 */
	public String getBraveOrCowardlySelect()
	{
		return mBraveOrCowardlySelect;
	}

	/**
	 *
	 * @param theValue
	 */
	public void setKindOrCruelDegree(String theValue)
	{
		mKindOrCruelDegree = theValue;
	}
	
	/**
	 *
	 * @param theValue
	 */
	public void setFocusedOrUnfocusedDegree(String theValue)
	{
		mFocusedOrUnfocusedDegree = theValue;
	}
	
	/**
	 *
	 * @param theValue
	 */
	public void setSelflessOrSelfishDegree(String theValue)
	{
		mSelflessOrSelfishDegree  = theValue;
	}
	
	/**
	 *
	 * @param theValue
	 */
	public void setHonorableOrDeceitfulDegree(String theValue)
	{
		mHonorableOrDeceitfulDegree = theValue;
	}
	
	/**
	 *
	 * @param theValue
	 */
	public void setBraveOrCowardlyDegree(String theValue)
	{
		mBraveOrCowardlyDegree = theValue;
	}
	
	/**
	 *
	 * @param theValue
	 */
	public void setKindOrCruelSelect(String theValue)
	{
		mKindOrCruelSelect = theValue;
	}
	
	/**
	 *
	 * @param theValue
	 */
	public void setFocusedOrUnfocusedSelect(String theValue)
	{
		mFocusedOrUnfocusedSelect = theValue;
	}
	
	/**
	 *
	 * @param theValue
	 */
	public void setSelflessOrSelfishSelect(String theValue)
	{
		mSelflessOrSelfishSelect  = theValue;
	}
	
	/**
	 *
	 * @param theValue
	 */
	public void setHonorableOrDeceitfulSelect(String theValue)
	{
		mHonorableOrDeceitfulSelect = theValue;
	}
	
	/**
	 *
	 * @param theValue
	 */
	public void setBraveOrCowardlySelect(String theValue)
	{
		mBraveOrCowardlySelect = theValue;
	}

	////////////////////////////////////
	// Descriptors (from race)
	////////////////////////////////////
	
	/**
	 *
	 */
		
	protected ArrayList<String> mDescriptors = new ArrayList<>();
	
	/**
	 *
	 * @return
	 */
	public String [] getDescriptors()
	{
		return mDescriptors.toArray(new String[0]);
	}
	
	/**
	 *
	 * @param theNew
	 * @throws Exception
	 */
	public void setDescriptors(String [] theNew) throws Exception
	{
		if (theNew.length!=mDescriptors.size())
		{
			throw new Exception("Incorrect number of descriptors");
		}
		mDescriptors.clear();
		mDescriptors.addAll(Arrays.asList(theNew));
	}
	
	////////////////////////////////////
	// Races
	////////////////////////////////////
	
	/**
	 *
	 */
		
	protected TreeSet<Race> mRaces = null;

	/**
	 *
	 */
	protected Race mRace = null;

	/**
	 *
	 */
	protected String mRacialAbility = "";

	/**
	 *
	 */
	protected String mRaceAbility = "";

	/**
	 *
	 */
	protected String mRaceSkill = "";

	/**
	 *
	 */
	protected int mRaceMod = 0;
	
	/**
	 *
	 * @return
	 */
	public TreeSet<Race> getRaces()
	{
		return mRaces;
	}
	
	/**
	 *
	 * @return
	 */
	public Race getRace()
	{
		return mRace;
	}
	
	/**
	 *
	 * @param theRace
	 * @throws IOException
	 * @throws JAXBException
	 */
	public void setRace(String theRace) throws IOException, JAXBException
	{
		resetRacialOptions();
		//System.out.println("setRace");
		for(Race race : mRaces)
		{
			if(theRace.equals(race.getName()))
			{
				mRace = race;
			}
		}
		// reset all skill modifications
		mSkills = SkillFactory.loadSkills(this);
		////
		//// set racial settings
		////
		// abilities bonuses (if any)
		mStrengthBonus = 0;
		mDexterityBonus = 0;
		mLogicBonus = 0;
		mWillpowerBonus = 0;
		mBodypointsBonus = mRace.getBPMod();
		for(Entry<Abilities, Integer> entry : mRace.getAbilityBonus().entrySet())
		{
			setAbilityBonus(entry.getKey(), entry.getValue());
		}
		// descriptors
		int desc = mRace.getDescriptors();
		while (mDescriptors.size()>desc)
		{
			mDescriptors.remove(mDescriptors.size()-1);
		}
		while (mDescriptors.size()<desc)
		{
			mDescriptors.add("Enter descriptor here.");
		}
		// clear extra level skills
		// clear extra primary skills
		// clear extra secondary skills
		int level = mRace.getSkillOneCount();
		int prim = mRace.getPrimaryCount();
		int seco = mRace.getSecondaryCount();
		for(Skill skill : mSkills)
		{
			level = level -1;
			prim = prim -1;
			seco = seco -1;
			if (level<0)
			{
				skill.setLevel(0, this);
			}
			if (prim<0)
			{
				skill.setPrimary(false, this);
			}
			if (seco<0)
			{
				skill.setSecondary(false, this);
			}
		}
		// set SkillChecksList modifications
		for(Entry<String, Integer> entry : mRace.getSkillChecks().entrySet())
		{
			String aspectString = entry.getKey();
			String [] splitted = aspectString.split(":", -1);
			String skillName = splitted[0];
			String aspectName = null;
			if (splitted.length>1)
			{
				aspectName = splitted[1];
			}
			int modifier = entry.getValue();
			for(Skill skill : mSkills)
			{
				if (skill.getName().equals(skillName))
				{
					for(Score score: skill.getScores())
					{
						for(Aspect aspect : score.getAspects())
						{
							if ((null==aspectName)||(aspect.getName().equals(aspectName)))
							{
								aspect.setSkillCheckMod(modifier);
							}
						}
					}
				}
			}
		}
		// set SkillsAsUnskilled modifications
		for(String skillString : mRace.getSkillsAsUnskilled())
		{
			String [] splitted = skillString.split(":", -1);
			String skillName = splitted[0];
			String aspectName = null;
			if (splitted.length>1)
			{
				aspectName = splitted[1];
			}
			for(Skill skill : mSkills)
			{
				if (skill.getName().equals(skillName))
				{
					for(Score score: skill.getScores())
					{
						for(Aspect aspect : score.getAspects())
						{
							if ((null==aspectName)||(aspect.getName().equals(aspectName)))
							{
								aspect.setSkillsAsUnskilled();
							}
						}
					}
				}
			}
		}
		setPrimaryAndLevelsFromRace();
	}
	
	/**
	 *
	 */
	public void setPrimaryAndLevelsFromRace()
	{
		// set getPrimarySkill and getSkillAtLevel
		TreeMap<String, Integer> freeLevel = mRace.getSkillAtLevel();
		String primary = mRace.getPrimarySkill();
		for(Skill skill : mSkills)
		{
			Integer levelset = freeLevel.get(skill.getName());
			if (null!=levelset)
			{
				skill.setLevel(levelset, this);
			}
			if (skill.getName().equals(primary))
			{
				skill.setPrimary(true, this);
			}
		}
		// TODO: set other racial settings when implemented
		setAbilityTotals();
	}
	
	/**
	 *
	 */
	public void resetRacialOptions()
	{
		// remove applications of this first
		setRacialOptionsAbility(mRaceAbility, 0);
		setRacialOptionsSkill(mRaceSkill, 0);
		//
		mRacialAbility = "";
		mRaceAbility = "";
		mRaceSkill = "";
		mRaceMod = 0;
	}
	
	/**
	 *
	 * @param theAbility
	 * @param theMod
	 */
	public void setRacialOptionsAbility(String theAbility, int theMod)
	{
		if(false=="".equals(mRaceAbility))
		{
			Abilities ab = Abilities.valueOf(mRaceAbility);
			setAbilityBonus(ab, 0);
		}
		mRaceAbility = theAbility;
		mRaceMod = theMod;
		// apply applications of this
		if(false=="".equals(mRaceAbility))
		{
			Abilities ab = Abilities.valueOf(mRaceAbility);
			setAbilityBonus(ab, mRaceMod);
		}
	}
	
	private void setRacialOptionsSkillInternal(String theSkill, int theMod)
	{
		// apply applications of this
		String aspectString = theSkill;
		String [] splitted = aspectString.split(":", -1);
		String skillName = splitted[0];
		//System.out.println("skillName="+skillName);
		String aspectName = null;
		if (splitted.length>1)
		{
			aspectName = splitted[1];
		}
		if (null!=mSkills)
		{
			for(Skill skill : mSkills)
			{
				if (skill.getName().equals(skillName))
				{
					for(Score score: skill.getScores())
					{
						for(Aspect aspect : score.getAspects())
						{
							if ((null==aspectName)||(aspect.getName().equals(aspectName)))
							{
								//System.out.println("set skillName="+skillName);
								aspect.setSkillCheckMod(theMod);
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 *
	 * @param theSkill
	 * @param theMod
	 */
	public void setRacialOptionsSkill(String theSkill, int theMod)
	{
		if(false=="".equals(mRaceSkill))
		{
			//System.out.println("setRacialOptionsSkill clear old skill=" + mRaceSkill);
			setRacialOptionsSkillInternal(mRaceSkill, 0);
		}
		mRaceSkill = theSkill;
		mRaceMod = theMod;
		//System.out.println("setRacialOptionsSkill set new skill=" + mRaceSkill);
		setRacialOptionsSkillInternal(mRaceSkill, mRaceMod);
	}
	
	/**
	 *
	 * @param theRA
	 */
	public void setRacialOptionsRA(String theRA)
	{
		mRacialAbility = theRA;
	}
	
	/**
	 *
	 * @return
	 */
	public String getRacialOptionsRA()
	{
		return mRacialAbility;
	}
	
	////////////////////////////////////
	// Skills
	////////////////////////////////////
	
	/**
	 *
	 */
		
	protected TreeSet<Skill> mSkills = null;
	
	/**
	 *
	 * @return
	 */
	public TreeSet<Skill> getSkills()
	{
		TreeSet<Skill> mySkills = new TreeSet<>();
		mySkills.addAll(mSkills);
		String noSkill = mRace.getProhibitedSkill();
		Skill rem = null;
		for(Skill sk : mSkills)
		{
			if (sk.getName().equals(noSkill))
			{
				rem = sk;
			}
		}
		if (null!=rem)
		{
			mySkills.remove(rem);
		}
		return mySkills;
	}
	
	/**
	 *
	 * @param theName
	 * @return
	 */
	public Skill getSkill(String theName)
	{
		Skill mySkill = null;
		for(Skill sk : getSkills())
		{
			if (sk.getName().equals(theName))
			{
				mySkill = sk;
			}
		}
		return mySkill;
	}
	
	/**
	 *
	 * @param theSkill
	 * @param theAspect
	 * @return
	 */
	public Aspect getSkillAspect(String theSkill, String theAspect)
	{
		Skill skill = getSkill(theSkill);
		Aspect myAspect = null;
		if (null!=skill)
		{
			for(Score score: skill.getScores())
			{
				for(Aspect aspect : score.getAspects())
				{
					if(aspect.getName().equals(theAspect))
					{
						myAspect = aspect;
					}
				}
			}
		}
		return myAspect;
	}
	
	/**
	 *
	 * @return
	 */
	public TreeSet<Aspect> getAspects()
	{
		TreeSet<Aspect> result = new TreeSet<>();
		for(Skill skill : mSkills)
		{
			for(Score score: skill.getScores())
			{
				result.addAll(score.getAspects());
			}
		}
		return result;
	}

	////////////////////////////////////
	// calculated values
	////////////////////////////////////
	
	/**
	 *
	 */
		
	protected int mBodypointsBase = 0;

	/**
	 *
	 */
	protected int mBodypointsBonus = 0;

	/**
	 *
	 */
	protected int mBodypointsTotal = 0;
	
	/**
	 *
	 * @param theBPBonus
	 */
	public void setBodypointsBonus(int theBPBonus)
	{
		mBodypointsBonus = theBPBonus;
		setAbilityTotals();
	}

	/**
	 *
	 * @return
	 */
	public int getBodypointsBase()
	{
		return mBodypointsBase;
	}
	
	/**
	 *
	 * @return
	 */
	public int getBodypointsBonus()
	{
		return mBodypointsBonus;
	}
	
	/**
	 *
	 * @return
	 */
	public int getBodypointsTotal()
	{
		return mBodypointsTotal;
	}
	
	////////////////////////////////////
	// Abilities
	////////////////////////////////////
	
	/**
	 *
	 */
		
	protected int mStrengthBase = 0;

	/**
	 *
	 */
	protected int mDexterityBase = 0;

	/**
	 *
	 */
	protected int mLogicBase = 0;

	/**
	 *
	 */
	protected int mWillpowerBase = 0;
	//

	/**
	 *
	 */
		protected int mStrengthBonus = 0;

	/**
	 *
	 */
	protected int mDexterityBonus = 0;

	/**
	 *
	 */
	protected int mLogicBonus = 0;

	/**
	 *
	 */
	protected int mWillpowerBonus = 0;
	//

	/**
	 *
	 */
		protected int mStrengthTotal = 0;

	/**
	 *
	 */
	protected int mDexterityTotal = 0;

	/**
	 *
	 */
	protected int mLogicTotal = 0;

	/**
	 *
	 */
	protected int mWillpowerTotal = 0;
	//

	/**
	 *
	 */
		protected int [] mAvailableAbilityValues = new int[4];
	
	/**
	 *
	 */
	protected void setAbilityTotals()
	{
		mStrengthTotal = mStrengthBase + mStrengthBonus;
		mDexterityTotal = mDexterityBase + mDexterityBonus;
		mLogicTotal = mLogicBase + mLogicBonus;
		mWillpowerTotal = mWillpowerBase + mWillpowerBonus;
		//
		mBodypointsBase = new Double(Math.ceil(mStrengthTotal*0.5)).intValue();
		mBodypointsTotal = mBodypointsBase + mBodypointsBonus;
	}
	
	/**
	 *
	 */
	protected void setAbilities()
	{
		mStrengthBase = mAvailableAbilityValues[0];
		mDexterityBase = mAvailableAbilityValues[1];
		mLogicBase = mAvailableAbilityValues[2];
		mWillpowerBase = mAvailableAbilityValues[3];
		setAbilityTotals();
	}
	
	/**
	 *
	 */
	final public void setDefaultAbilities()
	{
		mAvailableAbilityValues[0] = 50;
		mAvailableAbilityValues[1] = 55;
		mAvailableAbilityValues[2] = 60;
		mAvailableAbilityValues[3] = 65;
		Arrays.sort(mAvailableAbilityValues);
		setAbilities();
	}
	
	/**
	 *
	 */
	public void setRolledAbilities()
	{
		mAvailableAbilityValues[0] = Dice.rollSum(10, 5) + 30;
		mAvailableAbilityValues[1] = Dice.rollSum(10, 5) + 30;
		mAvailableAbilityValues[2] = Dice.rollSum(10, 5) + 30;
		mAvailableAbilityValues[3] = Dice.rollSum(10, 5) + 30;
		Arrays.sort(mAvailableAbilityValues);
		setAbilities();
	}

	/**
	 *
	 * @param theAbility
	 * @return
	 */
	public int getAbilityBase(Abilities theAbility)
	{
		int result = 0;
		switch(theAbility)
		{
			case STR:
				result = mStrengthBase;
				break;
			case DEX:
				result = mDexterityBase;
				break;
			case LOG:
				result = mLogicBase;
				break;
			case WIL:
				result = mWillpowerBase;
				break;
		}
		return result;
	}

	/**
	 *
	 * @param theAbility
	 * @return
	 */
	public int getAbilityBonus(Abilities theAbility)
	{
		int result = 0;
		switch(theAbility)
		{
			case STR:
				result = mStrengthBonus;
				break;
			case DEX:
				result = mDexterityBonus;
				break;
			case LOG:
				result = mLogicBonus;
				break;
			case WIL:
				result = mWillpowerBonus;
				break;
		}
		return result;
	}

	/**
	 *
	 * @param theAbility
	 * @return
	 */
	public int getAbilityTotal(Abilities theAbility)
	{
		int result = 0;
		switch(theAbility)
		{
			case STR:
				result = mStrengthTotal;
				break;
			case DEX:
				result = mDexterityTotal;
				break;
			case LOG:
				result = mLogicTotal;
				break;
			case WIL:
				result = mWillpowerTotal;
				break;
		}
		return result;
	}

	/**
	 *
	 * @param theAbility
	 * @param theValue
	 */
	public void setAbilityBonus(Abilities theAbility, int theValue)
	{
		switch(theAbility)
		{
			case STR:
				mStrengthBonus = theValue;
				break;
			case DEX:
				mDexterityBonus = theValue;
				break;
			case LOG:
				mLogicBonus = theValue;
				break;
			case WIL:
				mWillpowerBonus = theValue;
				break;
		}
		setAbilityTotals();
	}

	/**
	 *
	 * @param theAbility
	 * @param theValue
	 */
	public void setAbilityBase(Abilities theAbility, int theValue)
	{
		//System.out.println("setAbilityBase");
		switch(theAbility)
		{
			case STR:
				int oldValue = mStrengthBase;
				mStrengthBase = theValue;
				if (theValue==mDexterityBase)
				{
					mDexterityBase = oldValue;
				}
				else if (theValue==mLogicBase)
				{
					mLogicBase = oldValue;
				}
				else if (theValue==mWillpowerBase)
				{
					mWillpowerBase = oldValue;
				}
				break;
			case DEX:
				oldValue = mDexterityBase;
				mDexterityBase = theValue;
				if (theValue==mStrengthBase)
				{
					mStrengthBase = oldValue;
				}
				else if (theValue==mLogicBase)
				{
					mLogicBase = oldValue;
				}
				else if (theValue==mWillpowerBase)
				{
					mWillpowerBase = oldValue;
				}
				break;
			case LOG:
				oldValue = mLogicBase;
				mLogicBase = theValue;
				if (theValue==mStrengthBase)
				{
					mStrengthBase = oldValue;
				}
				else if (theValue==mDexterityBase)
				{
					mDexterityBase = oldValue;
				}
				else if (theValue==mWillpowerBase)
				{
					mWillpowerBase = oldValue;
				}
				break;
			case WIL:
				oldValue = mWillpowerBase;
				mWillpowerBase = theValue;
				if (theValue==mStrengthBase)
				{
					mStrengthBase = oldValue;
				}
				else if (theValue==mDexterityBase)
				{
					mDexterityBase = oldValue;
				}
				else if (theValue==mLogicBase)
				{
					mLogicBase = oldValue;
				}
				break;
		}
		setAbilityTotals();
	}
	
	/**
	 *
	 * @return
	 */
	public int [] getAbilityValues()
	{
		return mAvailableAbilityValues;
	}

	////////////////////////////////////
	// Lanaguages
	////////////////////////////////////
	
	/**
	 *
	 */
		
	protected String mLangA = null;

	/**
	 *
	 */
	protected String mLangB = null;

	/**
	 *
	 */
	protected String mLangC = null;

	/**
	 *
	 * @param theIndex
	 * @return
	 * @throws Exception
	 */
	public String getLang(int theIndex) throws Exception
	{
		String result = "";
		switch(theIndex)
		{
			case 0:
				result = mLangA;
				break;
			case 1:
				result = mLangB;
				break;
			case 2:
				result = mLangC;
				break;
			default:
				throw new Exception("getLang index out of bounds");
		}
		return result;
	}

	/**
	 *
	 * @param theIndex
	 * @param theLang
	 * @throws Exception
	 */
	public void setLang(int theIndex, String theLang) throws Exception
	{
		switch(theIndex)
		{
			case 0:
				mLangA = theLang;
				break;
			case 1:
				mLangB = theLang;
				break;
			case 2:
				mLangC = theLang;
				break;
			default:
				throw new Exception("setLang index out of bounds");
		}
	}

	/**
	 *
	 * @return
	 */
	public TreeSet<String> getAvailLanguages()
	{
		TreeSet<String> languages = new TreeSet<>();
		for (Race race : mRaces)
		{
			languages.addAll(race.getLanguage());
		}
		return languages;
	}
	
	/**
	 *
	 * @return
	 */
	public TreeSet<String> getKnownLanguages()
	{
		TreeSet<String> languages = new TreeSet<>();
		languages.addAll(mRace.getLanguage());
		return languages;
	}
	
	/**
	 *
	 * @return
	 */
	public int getPickableLangCount()
	{
		return mRace.getLanguagePick();
	}

	////////////////////////////////////
	// get ability check totals
	////////////////////////////////////

	/**
	 *
	 * @return
	 */
	
	public TreeMap<AbilityChecks, Integer> getAbilityCheckTotals()
	{
		//TODO getAbilityCheckTotals
		TreeMap<AbilityChecks, Integer> checks = new TreeMap<>();
		for(Entry<AbilityChecks, Integer> entry : mRace.getAbilityChecks().entrySet())
		{
			Integer value = checks.get(entry.getKey());
			if (null==value)
			{
				value = new Integer(0);
			}
			value = value + entry.getValue();
			checks.put(entry.getKey(), value);
		}
		return checks;
	}

	////////////////////////////////////
	// selectables
	////////////////////////////////////

	/**
	 *
	 */
	
	protected TreeMap<String, TreeSet<String>> mSelectableMap = new TreeMap<>();
	
	/**
	 *
	 * @param theSelect
	 * @return
	 */
	public TreeSet<String> getSelected(String theSelect)
	{
		return mSelectableMap.get(theSelect);
	}

	/**
	 *
	 * @param theSelect
	 * @param theSelected
	 */
	public void setSelected(String theSelect, TreeSet<String> theSelected)
	{
		mSelectableMap.put(theSelect, theSelected);
	}
	
	////////////////////////////////////
	// 
	////////////////////////////////////

}
