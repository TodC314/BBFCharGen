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

package org.kuroneko.bbf.view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.kuroneko.bbf.model.skills.Aspect;

/**
 *
 * @author Tod Casasent
 */
public class AspectView
{

	/**
	 *
	 */
	protected SimpleStringProperty mSkillName = null;

	/**
	 *
	 */
	protected SimpleStringProperty mAspectName = null;

	/**
	 *
	 */
	protected SimpleIntegerProperty mScore = null;

	/**
	 *
	 */
	protected SimpleIntegerProperty mLevel = null;

	/**
	 *
	 */
	protected SimpleBooleanProperty mPrimary = null;

	/**
	 *
	 */
	protected SimpleBooleanProperty mSecondary = null;

	/**
	 *
	 */
	protected SimpleStringProperty mDescription = null;

	/**
	 *
	 */
	protected SimpleIntegerProperty mModifier = null;
	
	/**
	 *
	 * @param theAspect
	 */
	public AspectView(Aspect theAspect)
	{
		mSkillName = new SimpleStringProperty(theAspect.getSkillName());
		mAspectName = new SimpleStringProperty(theAspect.getName());
		mScore = new SimpleIntegerProperty(theAspect.getScore());
		mLevel = new SimpleIntegerProperty(theAspect.getLevel());
		mPrimary = new SimpleBooleanProperty(theAspect.getPrimary());
		mSecondary = new SimpleBooleanProperty(theAspect.getSecondary());
		mDescription = new SimpleStringProperty(theAspect.getDescription());
		mModifier = new SimpleIntegerProperty(theAspect.getSkillCheckMod());
	}

	/**
	 *
	 * @param theAspect
	 */
	public void updateView(Aspect theAspect)
	{
		mSkillName.setValue(theAspect.getSkillName());
		mAspectName.setValue(theAspect.getName());
		mScore.setValue(theAspect.getScore());
		mLevel.setValue(theAspect.getLevel());
		mPrimary.setValue(theAspect.getPrimary());
		mSecondary.setValue(theAspect.getSecondary());
		mDescription.setValue(theAspect.getDescription());
		mModifier.setValue(theAspect.getSkillCheckMod());
	}
	
	/**
	 *
	 * @return
	 */
	public SimpleStringProperty mSkillNameProperty()
	{
		return mSkillName;
	}

	/**
	 *
	 * @return
	 */
	public SimpleStringProperty mAspectNameProperty()
	{
		return mAspectName;
	}

	/**
	 *
	 * @return
	 */
	public SimpleStringProperty mDescriptionProperty()
	{
		return mDescription;
	}

	/**
	 *
	 * @return
	 */
	public SimpleIntegerProperty mScoreProperty()
	{
		return mScore;
	}
	
	/**
	 *
	 * @return
	 */
	public SimpleIntegerProperty mLevelProperty()
	{
		return mLevel;
	}
	
	/**
	 *
	 * @return
	 */
	public SimpleIntegerProperty mModifierProperty()
	{
		return mModifier;
	}

	/**
	 *
	 * @return
	 */
	public SimpleBooleanProperty mPrimaryProperty()
	{
		return mPrimary;
	}

	/**
	 *
	 * @return
	 */
	public SimpleBooleanProperty mSecondaryProperty()
	{
		return mSecondary;
	}
}
