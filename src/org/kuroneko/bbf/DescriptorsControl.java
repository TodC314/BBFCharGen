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

package org.kuroneko.bbf;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import org.kuroneko.bbf.model.descriptors.Descriptors;

/**
 *
 * @author Tod Casasent
 */
public class DescriptorsControl implements Initializable
{
	private ObservableList<String> mList1000 = FXCollections.observableArrayList();
	private ObservableList<String> mListDeca = FXCollections.observableArrayList();
	@FXML private ListView mDescriptorsList1000;
	@FXML private ListView mDescriptorsListDeca;
	@FXML private Button mDescriptor1000_1;
	@FXML private Button mDescriptorDeca_1;
	@FXML private Button mDescriptor1000_2;
	@FXML private Button mDescriptorDeca_2;
	@FXML private Button mDescriptor1000_3;
	@FXML private Button mDescriptorDeca_3;
	@FXML private TextArea mDescriptorValue1;
	@FXML private TextArea mDescriptorValue2;
	@FXML private TextArea mDescriptorValue3;

	private SimpleStringProperty mDescriptor1 = new SimpleStringProperty("");
	private SimpleStringProperty mDescriptor2 = new SimpleStringProperty("");
	private SimpleStringProperty mDescriptor3 = new SimpleStringProperty("");
	
	/**
	 *
	 */
	public void setCharacter()
	{
		try
		{
			String [] descList = BBFCharGen.mPC.getDescriptors();
			SimpleStringProperty [] descProp = { mDescriptor1, mDescriptor2, mDescriptor3 };
			for(int x=0;x<descList.length;x++)
			{
				descList[x] = descProp[x].getValue();
			}
			BBFCharGen.mPC.setDescriptors(descList);
		}
		catch(Exception exp)
		{
			exp.printStackTrace(System.err);
		}
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		Descriptors desc = new Descriptors();
		try
		{
			desc.load();
			mList1000.addAll(desc.m1000);
			mDescriptorsList1000.setItems(mList1000);
			mListDeca.addAll(desc.mDeca);
			mDescriptorsListDeca.setItems(mListDeca);
			//
			String [] descList = BBFCharGen.mPC.getDescriptors();
			SimpleStringProperty [] descProp = { mDescriptor1, mDescriptor2, mDescriptor3 };
			Button [] button1000 = { mDescriptor1000_1, mDescriptor1000_2, mDescriptor1000_3 };
			Button [] buttonDeca = { mDescriptorDeca_1, mDescriptorDeca_2, mDescriptorDeca_3 };
			TextArea [] textArea = { mDescriptorValue1, mDescriptorValue2, mDescriptorValue3 };
			for(int x=0;x<3;x++)
			{
				descProp[x].set("");
				button1000[x].setDisable(true);
				buttonDeca[x].setDisable(true);
				textArea[x].setDisable(true);
			}
			for(int x=0;x<descList.length;x++)
			{
				descProp[x].set(descList[x]);
				button1000[x].setDisable(false);
				buttonDeca[x].setDisable(false);
				textArea[x].textProperty().bindBidirectional(descProp[x]);
				textArea[x].setDisable(false);
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace(System.err);
		}
	}

	@FXML private void descriptor1000Action1(ActionEvent event)
	{
		mDescriptor1.set(mList1000.get(Dice.roll(mList1000.size())-1));
		setCharacter();
	}

	@FXML private void descriptorDecaAction1(ActionEvent event)
	{
		mDescriptor1.set(mListDeca.get(Dice.roll(mListDeca.size())-1));
		setCharacter();
	}

	@FXML private void descriptor1000Action2(ActionEvent event)
	{
		mDescriptor2.set(mList1000.get(Dice.roll(mList1000.size())-1));
		setCharacter();
	}

	@FXML private void descriptorDecaAction2(ActionEvent event)
	{
		mDescriptor2.set(mListDeca.get(Dice.roll(mListDeca.size())-1));
		setCharacter();
	}

	@FXML private void descriptor1000Action3(ActionEvent event)
	{
		mDescriptor3.set(mList1000.get(Dice.roll(mList1000.size())-1));
		setCharacter();
	}

	@FXML private void descriptorDecaAction3(ActionEvent event)
	{
		mDescriptor3.set(mListDeca.get(Dice.roll(mListDeca.size())-1));
		setCharacter();
	}

	@FXML private void assign1000to1Action(ActionEvent event)
	{
		if(-1!=mDescriptorsList1000.getSelectionModel().getSelectedIndex())
		{
			mDescriptor1.set((String)(mDescriptorsList1000.getSelectionModel().getSelectedItem()));
			setCharacter();
		}
	}

	@FXML private void assign1000to2Action(ActionEvent event)
	{
		if(-1!=mDescriptorsList1000.getSelectionModel().getSelectedIndex())
		{
			mDescriptor2.set((String)(mDescriptorsList1000.getSelectionModel().getSelectedItem()));
			setCharacter();
		}
	}

	@FXML private void assign1000to3Action(ActionEvent event)
	{
		if(-1!=mDescriptorsList1000.getSelectionModel().getSelectedIndex())
		{
			mDescriptor3.set((String)(mDescriptorsList1000.getSelectionModel().getSelectedItem()));
			setCharacter();
		}
	}

	@FXML private void assignDecato1Action(ActionEvent event)
	{
		if(-1!=mDescriptorsListDeca.getSelectionModel().getSelectedIndex())
		{
			mDescriptor1.set((String)(mDescriptorsListDeca.getSelectionModel().getSelectedItem()));
			setCharacter();
		}
	}

	@FXML private void assignDecato2Action(ActionEvent event)
	{
		if(-1!=mDescriptorsListDeca.getSelectionModel().getSelectedIndex())
		{
			mDescriptor2.set((String)(mDescriptorsListDeca.getSelectionModel().getSelectedItem()));
			setCharacter();
		}
	}

	@FXML private void assignDecato3Action(ActionEvent event)
	{
		if(-1!=mDescriptorsListDeca.getSelectionModel().getSelectedIndex())
		{
			mDescriptor3.set((String)(mDescriptorsListDeca.getSelectionModel().getSelectedItem()));
			setCharacter();
		}
	}
	
	
}
