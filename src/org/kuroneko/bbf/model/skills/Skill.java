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
public class Skill implements Comparable<Skill>
{

	/**
	 *
	 */
	@XmlAttribute(name = "mName", required = true)
	protected String mName = "";

	/**
	 *
	 */
	@XmlElementWrapper(name="ScoreList")
	@XmlElement(name="Score")
	protected TreeSet<Score> mScores = null;
	//

	/**
	 *
	 */
		@XmlAttribute(name = "mLevel", required = false)
	protected int mLevel = 0;

	/**
	 *
	 */
	@XmlAttribute(name = "mPrimary", required = false)
	protected boolean mPrimary = false;

	/**
	 *
	 */
	@XmlAttribute(name = "mSecondary", required = false)
	protected boolean mSecondary = false;
	
	/**
	 *
	 */
	public Skill()
	{
		mName = "";
		mLevel = 0;
		mScores = new TreeSet<>();
	}
	
	/**
	 *
	 * @param thePC
	 */
	public void init(BBFCharacter thePC)
	{
		for(Score score : mScores)
		{
			score.init(this, thePC);
		}
	}
	
	@Override
	public int compareTo(Skill o)
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
	public int getLevel()
	{
		return mLevel;
	}
	
	/**
	 *
	 * @return
	 */
	public boolean getPrimary()
	{
		return mPrimary;
	}
	
	/**
	 *
	 * @return
	 */
	public boolean getSecondary()
	{
		return mSecondary;
	}
	
	/**
	 *
	 * @param mLevel
	 * @param thePC
	 */
	public void setLevel(int mLevel, BBFCharacter thePC)
	{
		this.mLevel = mLevel;
	}

	/**
	 *
	 * @param mPrimary
	 * @param thePC
	 */
	public void setPrimary(boolean mPrimary, BBFCharacter thePC)
	{
		this.mPrimary = mPrimary;
	}

	/**
	 *
	 * @param mSecondary
	 * @param thePC
	 */
	public void setSecondary(boolean mSecondary, BBFCharacter thePC)
	{
		this.mSecondary = mSecondary;
	}
	
	/**
	 *
	 * @return
	 */
	public TreeSet<Score> getScores()
	{
		return mScores;
	}
	
}
