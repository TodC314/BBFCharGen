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

import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

/**
 *
 * @author Tod Casasent
 */
public class MakeChoice
{

	/**
	 *
	 * @param theList
	 * @param theTitle
	 * @param theContext
	 * @param theHeader
	 * @param theExpContent
	 * @return
	 */
	static public int getSelected(ArrayList<String> theList,
			String theTitle, String theContext, String theHeader, Node theExpContent)
	{
		ChoiceDialog<String> dialog = new ChoiceDialog<>(theList.get(0), theList);
		dialog.setTitle(theTitle);
		if (null!=theHeader)
		{
			dialog.setHeaderText(theHeader);
		}
		if (null!=theContext)
		{
			dialog.setContentText(theContext);
		}
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initStyle(StageStyle.UNDECORATED);
		if (null!=theExpContent)
		{
			dialog.getDialogPane().setExpandableContent(theExpContent);
		}
		// TODO FUTURE: disable cancel button
		Optional<String> result = Optional.empty();
		// popup for selected experience
		while (false == result.isPresent())
		{
			result = dialog.showAndWait();
		}
		return theList.indexOf(result.get());
	}
	
}
