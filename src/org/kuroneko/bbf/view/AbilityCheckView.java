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

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.kuroneko.bbf.model.AbilityChecks;
import org.kuroneko.bbf.model.BBFCharacter;

/**
 *
 * @author Tod Casasent
 */
public class AbilityCheckView implements Comparable<AbilityCheckView>
{

	/**
	 *
	 */
	protected SimpleStringProperty mAbility = null;

	/**
	 *
	 */
	protected SimpleStringProperty mDescription = null;

	/**
	 *
	 */
	protected SimpleIntegerProperty mValue = null;
	
	/**
	 *
	 * @param theCheck
	 * @param theMod
	 * @param thePC
	 */
	public AbilityCheckView(AbilityChecks theCheck, int theMod, BBFCharacter thePC)
	{
		mAbility = new SimpleStringProperty(theCheck.mAbility.mShortName);
		mDescription = new SimpleStringProperty(theCheck.mName);
		mValue = new SimpleIntegerProperty((new Double(Math.ceil(thePC.getAbilityTotal(theCheck.mAbility)*0.5)).intValue())+theMod);
	}
	
	/**
	 *
	 * @return
	 */
	public SimpleStringProperty mAbilityProperty()
	{
		return mAbility;
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
	public SimpleIntegerProperty mValueProperty()
	{
		return mValue;
	}

	@Override
	public int compareTo(AbilityCheckView o)
	{
		int comp = this.mAbility.get().compareTo(o.mAbility.get());
		if (0==comp)
		{
			comp = this.mDescription.get().compareTo(o.mDescription.get());
		}
		return comp;
	}
}
