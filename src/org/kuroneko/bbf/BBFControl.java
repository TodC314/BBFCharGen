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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.TreeSet;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.kuroneko.bbf.model.Abilities;
import org.kuroneko.bbf.model.AbilityChecks;
import org.kuroneko.bbf.model.race.Race;
import org.kuroneko.bbf.model.race.Special;
import org.kuroneko.bbf.model.skills.Aspect;
import org.kuroneko.bbf.model.skills.Skill;
import org.kuroneko.bbf.view.AbilityCheckView;
import org.kuroneko.bbf.view.AspectView;

/**
 * BBFControl is the Control Class for the BBFView.fxml view.
 * @author Tod Casasent
 */
public class BBFControl implements Initializable
{
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		//System.out.println("BBFControl::initialize");
		// Abilities
		mStrengthBase.setItems(mAbilityList);
		mDexterityBase.setItems(mAbilityList);
		mLogicBase.setItems(mAbilityList);
		mWillpowerBase.setItems(mAbilityList);
		pcUpdate();
		mAbilityUpdateInProgress = false;
		// Skill Scores
		updateAspectsFromPC();
		// Races
		updateRacesFromPC();
		// descriptors
		updateDescriptorsFromPC();
		// moral code
		updateMoralCodeFromPC();
		// level, primary, secondary
		setSkills();
		//
		updateLevelPriSecFromPC();
		updateLangFromPC();
		updateAbilityChecksFromPC();
		updateClericSelectablesFromPC();
		updateSpellcasterSelectablesFromPC();
		updateScholarSelectablesFromPC();
		updateRacialSelectablesFromPC();
	}

	/**
	 * Get the skill settings from the character model and race model.
	 */
	protected void setSkills()
	{
		//System.out.println("update skill list for control");
		String proh = BBFCharGen.mPC.getRace().getProhibitedSkill();
		String prim = BBFCharGen.mPC.getRace().getPrimarySkill();
		mObsLevelSkill.clear();
		mObsPrimSkill.clear();
		mObsSecoSkill.clear();
		for (Skill sk : BBFCharGen.mPC.getSkills())
		{
			if (false == sk.getName().equals(proh))
			{
				mObsLevelSkill.add(sk.getName());
				if (false == sk.getName().equals(prim))
				{
					mObsPrimSkill.add(sk.getName());
				}
				mObsSecoSkill.add(sk.getName());
			}
		}
	}

	/**
	 * Update the control information from the character model.
	 */
	protected void pcUpdate()
	{
		//System.out.println("pcUpdate");
		mAbilityUpdateInProgress = true;
		setSkills();
		updateAbilitiesFromPC();
		updateAspectsFromPC();
		updateDescriptorsFromPC();
		updateMoralCodeFromPC();
		updateLevelPriSecFromPC();
		updateLangFromPC();
		updateAbilityChecksFromPC();
		updateClericSelectablesFromPC();
		updateSpellcasterSelectablesFromPC();
		updateScholarSelectablesFromPC();
	}
	
	////////////////////////////////////
	// Moral Code
	////////////////////////////////////
	@FXML private ComboBox mKindOrCruelDegree;
	@FXML private ComboBox mKindOrCruelSelect;
	@FXML private ComboBox mFocusedOrUnfocusedDegree;
	@FXML private ComboBox mFocusedOrUnfocusedSelect;
	@FXML private ComboBox mSelflessOrSelfishDegree;
	@FXML private ComboBox mSelflessOrSelfishSelect;
	@FXML private ComboBox mHonorableOrDeceitfulDegree;
	@FXML private ComboBox mHonorableOrDeceitfulSelect;
	@FXML private ComboBox mBraveOrCowardlyDegree;
	@FXML private ComboBox mBraveOrCowardlySelect;
	private ObservableList<String> mDegreeList = FXCollections.observableArrayList();
	private ObservableList<String> mKindOrCruelSelectList = FXCollections.observableArrayList();
	private ObservableList<String> mFocusedOrUnfocusedSelectList = FXCollections.observableArrayList();
	private ObservableList<String> mSelflessOrSelfishSelectList = FXCollections.observableArrayList();
	private ObservableList<String> mHonorableOrDeceitfulSelectList = FXCollections.observableArrayList();
	private ObservableList<String> mBraveOrCowardlySelectList = FXCollections.observableArrayList();

	private void updateMoralCodeFromPC()
	{
		//System.out.println("BBFControl::updateMoralCodeFromPC");
		mDegreeList.clear();
		mDegreeList.addAll(BBFCharGen.mPC.getDegreeList());
		mKindOrCruelDegree.setItems(mDegreeList);
		mFocusedOrUnfocusedDegree.setItems(mDegreeList);
		mSelflessOrSelfishDegree.setItems(mDegreeList);
		mHonorableOrDeceitfulDegree.setItems(mDegreeList);
		mBraveOrCowardlyDegree.setItems(mDegreeList);
		//
		mKindOrCruelSelectList.clear();
		mKindOrCruelSelectList.addAll(BBFCharGen.mPC.getKindOrCruelSelectList());
		mKindOrCruelSelect.setItems(mKindOrCruelSelectList);
		mFocusedOrUnfocusedSelectList.clear();
		mFocusedOrUnfocusedSelectList.addAll(BBFCharGen.mPC.getFocusedOrUnfocusedSelectList());
		mFocusedOrUnfocusedSelect.setItems(mFocusedOrUnfocusedSelectList);
		mSelflessOrSelfishSelectList.clear();
		mSelflessOrSelfishSelectList.addAll(BBFCharGen.mPC.getSelflessOrSelfishSelectList());
		mSelflessOrSelfishSelect.setItems(mSelflessOrSelfishSelectList);
		mHonorableOrDeceitfulSelectList.clear();
		mHonorableOrDeceitfulSelectList.addAll(BBFCharGen.mPC.getHonorableOrDeceitfulSelectList());
		mHonorableOrDeceitfulSelect.setItems(mHonorableOrDeceitfulSelectList);
		mBraveOrCowardlySelectList.clear();
		mBraveOrCowardlySelectList.addAll(BBFCharGen.mPC.getBraveOrCowardlySelectList());
		mBraveOrCowardlySelect.setItems(mBraveOrCowardlySelectList);
		//
		mKindOrCruelDegree.setValue(BBFCharGen.mPC.getKindOrCruelDegree());
		mFocusedOrUnfocusedDegree.setValue(BBFCharGen.mPC.getFocusedOrUnfocusedDegree());
		mSelflessOrSelfishDegree.setValue(BBFCharGen.mPC.getSelflessOrSelfishDegree());
		mHonorableOrDeceitfulDegree.setValue(BBFCharGen.mPC.getHonorableOrDeceitfulDegree());
		mBraveOrCowardlyDegree.setValue(BBFCharGen.mPC.getBraveOrCowardlyDegree());
		mKindOrCruelSelect.setValue(BBFCharGen.mPC.getKindOrCruelSelect());
		mFocusedOrUnfocusedSelect.setValue(BBFCharGen.mPC.getFocusedOrUnfocusedSelect());
		mSelflessOrSelfishSelect.setValue(BBFCharGen.mPC.getSelflessOrSelfishSelect());
		mHonorableOrDeceitfulSelect.setValue(BBFCharGen.mPC.getHonorableOrDeceitfulSelect());
		mBraveOrCowardlySelect.setValue(BBFCharGen.mPC.getBraveOrCowardlySelect());
	}

	@FXML private void updateMoralCodeToPCAction(ActionEvent event)
	{
		//System.out.println("BBFControl::updateMoralCodeToPCAction");
		updateMoralCodeToPC();
	}

	private void updateMoralCodeToPC()
	{
		//System.out.println("BBFControl::updateMoralCodeToPC");
		if (false == mAbilityUpdateInProgress)
		{
			BBFCharGen.mPC.setKindOrCruelDegree((String) (mKindOrCruelDegree.getValue()));
			BBFCharGen.mPC.setFocusedOrUnfocusedDegree((String) mFocusedOrUnfocusedDegree.getValue());
			BBFCharGen.mPC.setSelflessOrSelfishDegree((String) mSelflessOrSelfishDegree.getValue());
			BBFCharGen.mPC.setHonorableOrDeceitfulDegree((String) mHonorableOrDeceitfulDegree.getValue());
			BBFCharGen.mPC.setBraveOrCowardlyDegree((String) mBraveOrCowardlyDegree.getValue());
			BBFCharGen.mPC.setKindOrCruelSelect((String) mKindOrCruelSelect.getValue());
			BBFCharGen.mPC.setFocusedOrUnfocusedSelect((String) mFocusedOrUnfocusedSelect.getValue());
			BBFCharGen.mPC.setSelflessOrSelfishSelect((String) mSelflessOrSelfishSelect.getValue());
			BBFCharGen.mPC.setHonorableOrDeceitfulSelect((String) mHonorableOrDeceitfulSelect.getValue());
			BBFCharGen.mPC.setBraveOrCowardlySelect((String) mBraveOrCowardlySelect.getValue());
		}
	}
	////////////////////////////////////
	// Descriptors (linked to Race)
	////////////////////////////////////
	@FXML private ListView mDescriptors;
	private ObservableList<String> mDescriptorsList = FXCollections.observableArrayList();

	private void updateDescriptorsFromPC()
	{
		//System.out.println("BBFControl::updateDescriptorsFromPC");
		mDescriptorsList.clear();
		mDescriptorsList.addAll(BBFCharGen.mPC.getDescriptors());
		mDescriptors.setItems(mDescriptorsList);
	}

	@FXML private void descriptorsAction(ActionEvent event)
	{
		//System.out.println("BBFControl::descriptorsAction");
		descriptorDialog();
		updateDescriptorsFromPC();
	}

	/**
	 *
	 */
	public void descriptorDialog()
	{
		try
		{
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AspectView.class.getResource("DescriptorsView.fxml"));
			ScrollPane page = (ScrollPane) loader.load();
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Select Descriptors");
			dialogStage.setOnCloseRequest(event -> 
			{
				// If the user enters descriptors manually, need to save them here
				// I tried onTextChange events, but they were always one character behind
				((DescriptorsControl)loader.getController()).setCharacter();
			});
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(BBFCharGen.M_STAGE);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
		}
		catch (IOException exp)
		{
			exp.printStackTrace(System.err);
			showException(exp);
		}
	}
	////////////////////////////////////
	// Race
	////////////////////////////////////
	private ObservableList<String> mRaceList = FXCollections.observableArrayList();
	private ObservableList<String> mRaceAvailList = FXCollections.observableArrayList();
	@FXML private ComboBox mRace;
	@FXML private ListView mRaceAvailView;

	private void updateRacesFromPC()
	{
		//System.out.println("BBFControl::updateRacesFromPC");
		for (Race race : BBFCharGen.mPC.getRaces())
		{
			// combo box
			mRaceList.add(race.getName());
			// available list
			String descr = race.getName();
			descr = descr + "\r\n\t" + "Move=" + race.getMove() + " Desc=" + race.getDescriptors() + " Skills Level 1=" + race.getSkillOneCount();
			descr = descr + "\r\n\t" + "Primaries=" + race.getPrimaryCount() + " Secondaries=" + race.getSecondaryCount() + " BP Mod=" + race.getBPMod();
			descr = descr + "\r\n\t" + "EquipCostMod=" + race.getEquipCostMod() + " WeaponDamageMulti=" + race.getWeaponDamageMulti();
			descr = descr + "\r\n\t" + "Lang Pick=" + race.getLanguagePick() + " NativeLanguageCostMod=" + race.getNativeLanguageCostMod();
			descr = descr + "\r\n\t" + "Languages: ";
			for (String str : race.getLanguage())
			{
				descr = descr + " " + str;
			}
			for (Entry<Abilities, Integer> entry : race.getAbilityBonus().entrySet())
			{
				descr = descr + "\r\n\tAbility Mod: " + entry.getKey().mShortName + " " + entry.getValue();
			}
			for (Entry<AbilityChecks, Integer> entry : race.getAbilityChecks().entrySet())
			{
				descr = descr + "\r\n\tAbility Check Mod: " + entry.getKey().mAbility.mShortName + " " + entry.getKey().mName + " " + entry.getValue();
			}
			for (Entry<String, String> entry : race.getRacialChars().entrySet())
			{
				descr = descr + "\r\n\tRacial Char: " + entry.getKey() + " " + entry.getValue();
			}
			for (Entry<String, Integer> entry : race.getSkillChecks().entrySet())
			{
				descr = descr + "\r\n\tSkill Check Mod: " + entry.getKey() + " " + entry.getValue();
			}
			descr = descr + "\r\n\t" + "Skill as Unskilled: ";
			for (String str : race.getSkillsAsUnskilled())
			{
				descr = descr + " " + str;
			}
			for (Special entry : race.getSpecials())
			{
				try
				{
					descr = descr + "\r\n\tSpecial: " + entry.describeSpecial();
				}
				catch (Exception e)
				{
					e.printStackTrace(System.err);
					showException(e);
				}
			}
			mRaceAvailList.add(descr);
		}
		mRace.setItems(mRaceList);
		mRaceAvailView.setItems(mRaceAvailList);
		mRace.setValue(BBFCharGen.mPC.getRace().getName());
	}

	@FXML private void raceAction(ActionEvent event)
	{
		//System.out.println("BBFControl::raceAction");
		try
		{
			BBFCharGen.mPC.setRace(((String) mRace.getValue()));
		}
		catch (Exception exp)
		{
			exp.printStackTrace(System.err);
			showException(exp);
		}
		pcUpdate();
		updateRacialSelectablesFromPC();
		mAbilityUpdateInProgress = false;
	}
	////////////////////////////////////
	// Skill Scores
	////////////////////////////////////
	private ObservableList<AspectView> mAspectList = FXCollections.observableArrayList();
	private SortedList<AspectView> mSortedAspectList = null;
	@FXML private TableView mAspectTable;

	private void updateExistingScore(Aspect theAspect)
	{
		//System.out.println("BBFControl::updateExistingScore - " + theAspect.getSkillName() + " - " + theAspect.getName());
		for (AspectView sa : mAspectList)
		{
			if (sa.mSkillNameProperty().get().equals(theAspect.getSkillName()))
			{
				if (sa.mAspectNameProperty().get().equals(theAspect.getName()))
				{
					//System.out.println("BBFControl::updateExistingScore - call updateView");
					sa.updateView(theAspect);
				}
			}
		}
	}

	private void updateAspectsFromPC()
	{
		//System.out.println("BBFControl::updateAspectsFromPC");
		for (Aspect aspect : BBFCharGen.mPC.getAspects())
		{
			//System.out.println("updateAspectsFromPC aspect=" + aspect.getSkillName() + " - " + aspect.getName());
			if (null == mSortedAspectList)
			{
				mAspectList.add(new AspectView(aspect));
			}
			else
			{
				//update existing entry
				updateExistingScore(aspect);
			}
		}
		if (null == mSortedAspectList)
		{
			mSortedAspectList = mAspectList.sorted();
			mAspectTable.setItems(mSortedAspectList);
		}
	}
	////////////////////////////////////
	// Abilities
	////////////////////////////////////
	private ObservableList<Integer> mAbilityList = FXCollections.observableArrayList();
	@FXML private ComboBox mStrengthBase;
	@FXML private ComboBox mDexterityBase;
	@FXML private ComboBox mLogicBase;
	@FXML private ComboBox mWillpowerBase;
	@FXML private Label mStrengthBonus;
	@FXML private Label mDexterityBonus;
	@FXML private Label mLogicBonus;
	@FXML private Label mWillpowerBonus;
	@FXML private Label mStrengthTotal;
	@FXML private Label mDexterityTotal;
	@FXML private Label mLogicTotal;
	@FXML private Label mWillpowerTotal;
	@FXML private Label mBodypointBase;
	@FXML private Label mBodypointBonus;
	@FXML private Label mBodypointTotal;
	private boolean mAbilityUpdateInProgress = false;

	private void updateAbilitiesFromPC()
	{
		//System.out.println("BBFControl::updateAbilitiesFromPC");
		mAbilityUpdateInProgress = true;
		mAbilityList.clear();
		for (int val : BBFCharGen.mPC.getAbilityValues())
		{
			mAbilityList.add(val);
		}
		// base
		mStrengthBase.setValue(BBFCharGen.mPC.getAbilityBase(Abilities.STR));
		mDexterityBase.setValue(BBFCharGen.mPC.getAbilityBase(Abilities.DEX));
		mLogicBase.setValue(BBFCharGen.mPC.getAbilityBase(Abilities.LOG));
		mWillpowerBase.setValue(BBFCharGen.mPC.getAbilityBase(Abilities.WIL));
		mBodypointBase.setText(Integer.toString(BBFCharGen.mPC.getBodypointsBase()));
		// bonus
		mStrengthBonus.setText(Integer.toString(BBFCharGen.mPC.getAbilityBonus(Abilities.STR)));
		mDexterityBonus.setText(Integer.toString(BBFCharGen.mPC.getAbilityBonus(Abilities.DEX)));
		mLogicBonus.setText(Integer.toString(BBFCharGen.mPC.getAbilityBonus(Abilities.LOG)));
		mWillpowerBonus.setText(Integer.toString(BBFCharGen.mPC.getAbilityBonus(Abilities.WIL)));
		mBodypointBonus.setText(Integer.toString(BBFCharGen.mPC.getBodypointsBonus()));
		// total
		mStrengthTotal.setText(Integer.toString(BBFCharGen.mPC.getAbilityTotal(Abilities.STR)));
		mDexterityTotal.setText(Integer.toString(BBFCharGen.mPC.getAbilityTotal(Abilities.DEX)));
		mLogicTotal.setText(Integer.toString(BBFCharGen.mPC.getAbilityTotal(Abilities.LOG)));
		mWillpowerTotal.setText(Integer.toString(BBFCharGen.mPC.getAbilityTotal(Abilities.WIL)));
		mBodypointTotal.setText(Integer.toString(BBFCharGen.mPC.getBodypointsTotal()));
	}

	@FXML private void defaultStatsAction(ActionEvent event)
	{
		//System.out.println("BBFControl::defaultStatsAction");
		if (false == mAbilityUpdateInProgress)
		{
			BBFCharGen.mPC.setDefaultAbilities();
			pcUpdate();
			mAbilityUpdateInProgress = false;
		}
	}

	@FXML private void rollStatsAction(ActionEvent event)
	{
		//System.out.println("BBFControl::rollStatsAction");
		if (false == mAbilityUpdateInProgress)
		{
			BBFCharGen.mPC.setRolledAbilities();
			pcUpdate();
			mAbilityUpdateInProgress = false;
		}
	}

	@FXML private void strAction(ActionEvent event)
	{
		//System.out.println("BBFControl::strAction");
		if (false == mAbilityUpdateInProgress)
		{
			BBFCharGen.mPC.setAbilityBase(Abilities.STR, ((Integer) mStrengthBase.getValue()));
			pcUpdate();
			mAbilityUpdateInProgress = false;
		}
	}

	@FXML private void dexAction(ActionEvent event)
	{
		//System.out.println("BBFControl::dexAction");
		if (false == mAbilityUpdateInProgress)
		{
			BBFCharGen.mPC.setAbilityBase(Abilities.DEX, ((Integer) mDexterityBase.getValue()));
			pcUpdate();
			mAbilityUpdateInProgress = false;
		}
	}

	@FXML private void logAction(ActionEvent event)
	{
		//System.out.println("BBFControl::logAction");
		if (false == mAbilityUpdateInProgress)
		{
			//System.out.println("log action set");
			BBFCharGen.mPC.setAbilityBase(Abilities.LOG, ((Integer) mLogicBase.getValue()));
			//System.out.println("pc update");
			pcUpdate();
			mAbilityUpdateInProgress = false;
		}
	}

	@FXML private void wilAction(ActionEvent event)
	{
		//System.out.println("BBFControl::wilAction");
		if (false == mAbilityUpdateInProgress)
		{
			BBFCharGen.mPC.setAbilityBase(Abilities.WIL, ((Integer) mWillpowerBase.getValue()));
			pcUpdate();
			mAbilityUpdateInProgress = false;
		}
	}
	////////////////////////////////////
	//  level 1, primary and secondary skills
	////////////////////////////////////
	@FXML private ComboBox mLevel1SkillA;
	@FXML private ComboBox mLevel1SkillB;
	@FXML private ComboBox mLevel1SkillC;
	@FXML private ComboBox mPrimarySkillA;
	@FXML private ComboBox mPrimarySkillB;
	@FXML private ComboBox mPrimarySkillC;
	@FXML private ComboBox mSecondarySkillA;
	@FXML private ComboBox mSecondarySkillB;
	@FXML private ComboBox mSecondarySkillC;
	ObservableList<String> mObsLevelSkill = FXCollections.observableArrayList();
	ObservableList<String> mObsPrimSkill = FXCollections.observableArrayList();
	ObservableList<String> mObsSecoSkill = FXCollections.observableArrayList();

	private void updateLevelPriSecFromPC()
	{
		//System.out.println("BBFControl::updateLevelPriSecFromPC");
		TreeMap<String, Integer> skillsAtLevels = BBFCharGen.mPC.getRace().getSkillAtLevel();
		String prim = BBFCharGen.mPC.getRace().getPrimarySkill();
		////////////////////////////////
		ArrayList<String> selLevel = new ArrayList<>();
		ArrayList<String> selPrim = new ArrayList<>();
		ArrayList<String> selSeco = new ArrayList<>();
		for (Skill sk : BBFCharGen.mPC.getSkills())
		{
			if (1 == sk.getLevel())
			{
				if (false == skillsAtLevels.keySet().contains(sk.getName()))
				{
					selLevel.add(sk.getName());
				}
			}
			if (sk.getPrimary())
			{
				if (false == sk.getName().equals(prim))
				{
					selPrim.add(sk.getName());
				}
			}
			if (sk.getSecondary())
			{
				selSeco.add(sk.getName());
			}
		}
		////////////////////////////////
		int levels = BBFCharGen.mPC.getRace().getSkillOneCount();
		ComboBox[] levelCombos =
		{
			mLevel1SkillA, mLevel1SkillB, mLevel1SkillC
		};
		for (ComboBox cb : levelCombos)
		{
			//System.out.println("set Level skills");
			cb.setItems(mObsLevelSkill);
			cb.setDisable(true);
			cb.setValue(null);
		}
		for (int x = 0; x < levels; x++)
		{
			levelCombos[x].setDisable(false);
			if (selLevel.size() > x)
			{
				//System.out.println("set level");
				levelCombos[x].setValue(selLevel.get(x));
			}
			else
			{
				//System.out.println("do not set level");
			}
		}
		////////////////////////////////
		int primaries = BBFCharGen.mPC.getRace().getPrimaryCount();
		ComboBox[] primCombos =
		{
			mPrimarySkillA, mPrimarySkillB, mPrimarySkillC
		};
		for (ComboBox cb : primCombos)
		{
			//System.out.println("set prim skills");
			cb.setItems(mObsPrimSkill);
			cb.setDisable(true);
			cb.setValue(null);
		}
		for (int x = 0; x < primaries; x++)
		{
			primCombos[x].setDisable(false);
			if (selPrim.size() > x)
			{
				primCombos[x].setValue(selPrim.get(x));
			}
		}
		////////////////////////////////
		int secondaries = BBFCharGen.mPC.getRace().getSecondaryCount();
		ComboBox[] secoCombos =
		{
			mSecondarySkillA, mSecondarySkillB, mSecondarySkillC
		};
		for (ComboBox cb : secoCombos)
		{
			//System.out.println("set seco skills");
			cb.setItems(mObsSecoSkill);
			cb.setDisable(true);
			cb.setValue(null);
		}
		for (int x = 0; x < secondaries; x++)
		{
			secoCombos[x].setDisable(false);
			if (selSeco.size() > x)
			{
				secoCombos[x].setValue(selSeco.get(x));
			}
		}
		////////////////////////////////
	}

	private void updatePCfromLevelPriSec()
	{
		//System.out.println("BBFControl::updatePCfromLevelPriSec");
		////////////////////////////////
		ArrayList<String> selLevel = new ArrayList<>();
		ArrayList<String> selPrim = new ArrayList<>();
		ArrayList<String> selSeco = new ArrayList<>();
		////////////////////////////////
		int levels = BBFCharGen.mPC.getRace().getSkillOneCount();
		ComboBox[] levelCombos =
		{
			mLevel1SkillA, mLevel1SkillB, mLevel1SkillC
		};
		for (int x = 0; x < levels; x++)
		{
			if (false == levelCombos[x].isDisabled())
			{
				selLevel.add((String) (levelCombos[x].getValue()));
			}
		}
		////////////////////////////////
		int primaries = BBFCharGen.mPC.getRace().getPrimaryCount();
		ComboBox[] primCombos =
		{
			mPrimarySkillA, mPrimarySkillB, mPrimarySkillC
		};
		for (int x = 0; x < primaries; x++)
		{
			if (false == primCombos[x].isDisabled())
			{
				selPrim.add((String) (primCombos[x].getValue()));
			}
		}
		////////////////////////////////
		int secondaries = BBFCharGen.mPC.getRace().getSecondaryCount();
		ComboBox[] secoCombos =
		{
			mSecondarySkillA, mSecondarySkillB, mSecondarySkillC
		};
		for (int x = 0; x < secondaries; x++)
		{
			if (false == secoCombos[x].isDisabled())
			{
				selSeco.add((String) (secoCombos[x].getValue()));
			}
		}
		////////////////////////////////
		for (Skill sk : BBFCharGen.mPC.getSkills())
		{
			if (selLevel.contains(sk.getName()))
			{
				sk.setLevel(1, BBFCharGen.mPC);
			}
			else
			{
				sk.setLevel(0, BBFCharGen.mPC);
			}
			if (selPrim.contains(sk.getName()))
			{
				sk.setPrimary(true, BBFCharGen.mPC);
			}
			else
			{
				sk.setPrimary(false, BBFCharGen.mPC);
			}
			if (selSeco.contains(sk.getName()))
			{
				sk.setSecondary(true, BBFCharGen.mPC);
			}
			else
			{
				sk.setSecondary(false, BBFCharGen.mPC);
			}
		}
		BBFCharGen.mPC.setPrimaryAndLevelsFromRace();
	}

	@FXML private void level1SkillAAction(ActionEvent event)
	{
		System.out.println("BBFControl::level1SkillAAction");
		if (false == mAbilityUpdateInProgress)
		{
			System.out.println("BBFControl::level1SkillAAction call updatePCfromLevelPriSec");
			updatePCfromLevelPriSec();
			pcUpdate();
			mAbilityUpdateInProgress = false;
		}
	}

	@FXML private void level1SkillBAction(ActionEvent event)
	{
		//System.out.println("BBFControl::level1SkillBAction");
		if (false == mAbilityUpdateInProgress)
		{
			updatePCfromLevelPriSec();
			pcUpdate();
			mAbilityUpdateInProgress = false;
		}
	}

	@FXML private void level1SkillCAction(ActionEvent event)
	{
		if (false == mAbilityUpdateInProgress)
		{
			updatePCfromLevelPriSec();
			pcUpdate();
			mAbilityUpdateInProgress = false;
		}
	}

	@FXML private void primarySkillAAction(ActionEvent event)
	{
		if (false == mAbilityUpdateInProgress)
		{
			updatePCfromLevelPriSec();
			pcUpdate();
			mAbilityUpdateInProgress = false;
		}
	}

	@FXML private void primarySkillBAction(ActionEvent event)
	{
		if (false == mAbilityUpdateInProgress)
		{
			updatePCfromLevelPriSec();
			pcUpdate();
			mAbilityUpdateInProgress = false;
		}
	}

	@FXML private void primarySkillCAction(ActionEvent event)
	{
		if (false == mAbilityUpdateInProgress)
		{
			updatePCfromLevelPriSec();
			pcUpdate();
			mAbilityUpdateInProgress = false;
		}
	}

	@FXML private void secondarySkillAAction(ActionEvent event)
	{
		if (false == mAbilityUpdateInProgress)
		{
			updatePCfromLevelPriSec();
			pcUpdate();
			mAbilityUpdateInProgress = false;
		}
	}

	@FXML private void secondarySkillBAction(ActionEvent event)
	{
		if (false == mAbilityUpdateInProgress)
		{
			updatePCfromLevelPriSec();
			pcUpdate();
			mAbilityUpdateInProgress = false;
		}
	}

	@FXML private void secondarySkillCAction(ActionEvent event)
	{
		if (false == mAbilityUpdateInProgress)
		{
			updatePCfromLevelPriSec();
			pcUpdate();
			mAbilityUpdateInProgress = false;
		}
	}
	////////////////////////////////////
	// Language
	////////////////////////////////////
	@FXML private ComboBox<String> mLanguageA;
	@FXML private ComboBox<String> mLanguageB;
	@FXML private ComboBox<String> mLanguageC;
	@FXML private Label mKnownLanguages;
	ObservableList<String> mObsLanguageA = FXCollections.observableArrayList();
	ObservableList<String> mObsLanguageB = FXCollections.observableArrayList();
	ObservableList<String> mObsLanguageC = FXCollections.observableArrayList();

	/**
	 *
	 */
	protected void updateLangFromPC()
	{
		//System.out.println("updateLangFromPC");
		try
		{
			TreeSet<String> availLang = BBFCharGen.mPC.getAvailLanguages();
			TreeSet<String> knownLang = BBFCharGen.mPC.getKnownLanguages();
			String known = "";
			for (String kn : knownLang)
			{
				//System.out.println("Known language " + kn);
				known = known + kn + " ";
				availLang.remove(kn);
			}
			//
			//System.out.println("updateLangFromPC setTExt");
			mKnownLanguages.setText(known);
			//System.out.println("updateLangFromPC setAll");
			ObservableList[] obsLang =
			{
				mObsLanguageA, mObsLanguageB, mObsLanguageC
			};
			for (ObservableList obs : obsLang)
			{
				obs.clear();
				obs.addAll(availLang);
			}
			//
			//System.out.println("updateLangFromPC setItems");
			mLanguageA.setItems(mObsLanguageA);
			mLanguageB.setItems(mObsLanguageB);
			mLanguageC.setItems(mObsLanguageC);
			////////////////////////////////
			int allowedCount = BBFCharGen.mPC.getPickableLangCount();
			ComboBox[] langCombos =
			{
				mLanguageA, mLanguageB, mLanguageC
			};
			//System.out.println("updateLangFromPC disable");
			for (int x = 0; x < langCombos.length; x++)
			{
				//System.out.println("updateLangFromPC disable " + x);
				langCombos[x].setItems(obsLang[x]);
				langCombos[x].setDisable(true);
				langCombos[x].setValue(null);
			}
			//System.out.println("updateLangFromPC enable");
			for (int x = 0; x < allowedCount; x++)
			{
				//System.out.println("updateLangFromPC enable " + x);
				langCombos[x].setDisable(false);
				langCombos[x].setValue(BBFCharGen.mPC.getLang(x));
			}
			//System.out.println("updateLangFromPC after");
			// TODO updatePCfromLang
		}
		catch (Exception exp)
		{
			exp.printStackTrace(System.err);
			showException(exp);
		}
	}

	/**
	 *
	 */
	protected void updatePCfromLang()
	{
		// TODO updatePCfromLang
		ComboBox[] langCombos =
		{
			mLanguageA, mLanguageB, mLanguageC
		};
		//System.out.println("updatePCfromLang");
		for (int x = 0; x < langCombos.length; x++)
		{
			//System.out.println("updatePCfromLang index " + x);
			try
			{
				BBFCharGen.mPC.setLang(x, ((ComboBox<String>) langCombos[x]).getValue());
			}
			catch (Exception exp)
			{
				exp.printStackTrace(System.err);
				showException(exp);
			}
		}
	}

	@FXML private void languageAAction(ActionEvent event)
	{
		if (false == mAbilityUpdateInProgress)
		{
			updatePCfromLang();
			pcUpdate();
			mAbilityUpdateInProgress = false;
		}
	}

	@FXML private void languageBAction(ActionEvent event)
	{
		if (false == mAbilityUpdateInProgress)
		{
			updatePCfromLang();
			pcUpdate();
			mAbilityUpdateInProgress = false;
		}
	}

	@FXML private void languageCAction(ActionEvent event)
	{
		if (false == mAbilityUpdateInProgress)
		{
			updatePCfromLang();
			pcUpdate();
			mAbilityUpdateInProgress = false;
		}
	}
	////////////////////////////////////
	// AbilityCheckTable
	////////////////////////////////////
	private ObservableList<AbilityCheckView> mAbilityCheckList = FXCollections.observableArrayList();
	private SortedList<AbilityCheckView> mSortedAbilityCheckList = null;
	@FXML private TableView mAbilityCheckTable;

	private void updateAbilityChecksFromPC()
	{
		TreeMap<AbilityChecks, Integer> totals = BBFCharGen.mPC.getAbilityCheckTotals();
		TreeMap<Abilities, Integer> alls = new TreeMap<>();
		for (Entry<AbilityChecks, Integer> entry : totals.entrySet())
		{
			AbilityChecks ac = entry.getKey();
			Integer mod = entry.getValue();
			if ("all".equals(ac.mName))
			{
				alls.put(ac.mAbility, mod);
				totals.put(ac, mod);
			}
			else
			{
				Integer totVal = totals.get(ac);
				if (null == totVal)
				{
					totVal = new Integer(0);
				}
				totVal = totVal + mod;
				totals.put(ac, totVal);
			}
		}
		mAbilityCheckList.clear();
		for (Entry<AbilityChecks, Integer> entry : totals.entrySet())
		{
			AbilityChecks ac = entry.getKey();
			Integer mod = entry.getValue();
			Integer all = alls.get(ac.mAbility);
			if (null == all)
			{
				all = new Integer(0);
			}
			mAbilityCheckList.add(new AbilityCheckView(ac, mod + all, BBFCharGen.mPC));
		}
		mSortedAbilityCheckList = mAbilityCheckList.sorted();
		mAbilityCheckTable.setItems(mSortedAbilityCheckList);
	}

	////////////////////////////////////
	// Selections Generic Function
	////////////////////////////////////
	private void updateSelectablesFromPC(Aspect theAspect, String theSelectKey,
			Label theSelected, Label theAllowed,
			ListView theOptions, ObservableList<String> theObservables)
	{
		if (null == theAspect)
		{
			theOptions.setDisable(true);
			theSelected.setText("0");
			theAllowed.setText("0");
		}
		else
		{
			boolean skilled = theAspect.getSkilledRequired();
			int allowed = theAspect.getAllowedSelections();
			// from character
			TreeSet<String> selected = BBFCharGen.mPC.getSelected(theSelectKey);
			if (null == selected)
			{
				selected = new TreeSet<>();
			}
			// if not allowed, disable
			if ((true == skilled) && (allowed < 1))
			{
				theOptions.setDisable(true);
				theSelected.setText("0");
				theAllowed.setText("0");
			}
			else // if allowed, setup
			{
				theOptions.setDisable(false);
				theSelected.setText(Integer.toString(selected.size()));
				theAllowed.setText(Integer.toString(allowed));
				theOptions.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				for (String sel : selected)
				{
					theOptions.getSelectionModel().select(sel);
				}
			}
		}
	}

	private boolean selectSelectables(Label theSelected, Label theAllowed,
			ListView theOptions, ObservableList<String> theObservables,
			String theNew)
	{
		boolean unselected = false;
		int allowed = Integer.parseInt(theAllowed.getText());
		int selected = theOptions.getSelectionModel().getSelectedItems().size();
		// used in case clearSelection can be used without an exception
		if (selected > allowed)
		{
			theSelected.setText(Integer.toString(selected));
			// TODO: figure out how to enforce this
			//int index = theObservables.indexOf(theNew);
			//theOptions.getSelectionModel().clearSelection(theObservables.indexOf(theNew));
			unselected = true;
		}
		else
		{
			theSelected.setText(Integer.toString(selected));
		}
		return unselected;
	}

	/**
	 *
	 * @param theSelectKey
	 * @param theOptions
	 */
	protected void updatePCfromSelectables(String theSelectKey, ListView<String> theOptions)
	{
		TreeSet<String> selected = new TreeSet<>();
		for (String str : theOptions.getSelectionModel().getSelectedItems().toArray(new String[0]))
		{
			selected.add(str);
		}
		BBFCharGen.mPC.setSelected(theSelectKey, selected);
	}

	/**
	 *
	 */
	protected void updatePCfromSelectables()
	{
		updatePCfromSelectables("Miracles", mClericSelectables);
		updatePCfromSelectables("High Wizardry", mSpellcasterSelectables);
		updatePCfromSelectables("High Scholar", mScholarSelectables);
	}
	////////////////////////////////////
	// Cleric Selections
	////////////////////////////////////
	@FXML private Label mClericSelected;
	@FXML private Label mClericSelectable;
	@FXML private ListView mClericSelectables;
	private ObservableList<String> mClericObservables = FXCollections.observableArrayList();
	private boolean mClericFirst = true;

	private void updateClericSelectablesFromPC()
	{
		Aspect myAspect = BBFCharGen.mPC.getSkillAspect("Cleric", "Miracles");
		if (true == mClericFirst)
		{
			// set displayed list
			mClericObservables.clear();
			if (null != myAspect)
			{
				mClericObservables.addAll(myAspect.getSelectPerLevelList());
			}
			mClericSelectables.setItems(mClericObservables);
		}
		updateSelectablesFromPC(myAspect, "Miracles", mClericSelected, mClericSelectable, mClericSelectables, mClericObservables);
		// setup callback
		if (true == mClericFirst)
		{
			mClericFirst = false;
			mClericSelectables.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
			{
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
				{
					selectSelectables(mClericSelected, mClericSelectable, mClericSelectables, mClericObservables, newValue);
					updatePCfromSelectables("Miracles", mClericSelectables);
				}
			});
		}
	}
	////////////////////////////////////
	// Spellcaster Selections
	////////////////////////////////////
	@FXML private Label mSpellcasterSelected;
	@FXML private Label mSpellcasterSelectable;
	@FXML private ListView mSpellcasterSelectables;
	private ObservableList<String> mSpellcasterObservables = FXCollections.observableArrayList();
	private boolean mSpellcasterFirst = true;

	private void updateSpellcasterSelectablesFromPC()
	{
		Aspect myAspect = BBFCharGen.mPC.getSkillAspect("Spellcaster", "High Wizardry");
		if (true == mSpellcasterFirst)
		{
			// set displayed list
			mSpellcasterObservables.clear();
			if (null != myAspect)
			{
				mSpellcasterObservables.addAll(myAspect.getSelectPerLevelList());
			}
			mSpellcasterSelectables.setItems(mSpellcasterObservables);
		}
		updateSelectablesFromPC(myAspect, "High Wizardry", mSpellcasterSelected, mSpellcasterSelectable, mSpellcasterSelectables, mSpellcasterObservables);
		// setup callback
		if (true == mSpellcasterFirst)
		{
			mSpellcasterFirst = false;
			mSpellcasterSelectables.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
			{
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
				{
					selectSelectables(mSpellcasterSelected, mSpellcasterSelectable, mSpellcasterSelectables, mSpellcasterObservables, newValue);
					updatePCfromSelectables("High Wizardry", mSpellcasterSelectables);
				}
			});
		}
	}
	////////////////////////////////////
	// Scholar Selections
	////////////////////////////////////
	@FXML private Label mScholarSelected;
	@FXML private Label mScholarSelectable;
	@FXML private ListView mScholarSelectables;
	private ObservableList<String> mScholarObservables = FXCollections.observableArrayList();
	private boolean mScholarFirst = true;

	private void updateScholarSelectablesFromPC()
	{
		Aspect myAspect = BBFCharGen.mPC.getSkillAspect("Scholar", "High Scholar");
		if (true == mScholarFirst)
		{
			// set displayed list
			mScholarObservables.clear();
			if (null != myAspect)
			{
				mScholarObservables.addAll(myAspect.getSelectPerLevelList());
			}
			mScholarSelectables.setItems(mScholarObservables);
		}
		updateSelectablesFromPC(myAspect, "High Scholar", mScholarSelected, mScholarSelectable, mScholarSelectables, mScholarObservables);
		// setup callback
		if (true == mScholarFirst)
		{
			mScholarFirst = false;
			mScholarSelectables.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
			{
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
				{
					selectSelectables(mScholarSelected, mScholarSelectable, mScholarSelectables, mScholarObservables, newValue);
					updatePCfromSelectables("High Scholar", mScholarSelectables);
				}
			});
		}
	}
	////////////////////////////////////
	// Racial Selectables
	////////////////////////////////////
	@FXML private ListView mRacialSelectables;
	private ObservableList<String> mRacialSelectablesObservables = FXCollections.observableArrayList();
	private boolean mRacialFirst = true;

	private void updateRacialSelectablesFromPC()
	{
		if (true == mRacialFirst)
		{
			mRacialSelectables.setItems(mRacialSelectablesObservables);
		}
		Special special = BBFCharGen.mPC.getRace().getRacialOption();
		if (null == special)
		{
			mRacialSelectablesObservables.clear();
			BBFCharGen.mPC.resetRacialOptions();
		}
		else
		{
			try
			{
				mRacialSelectablesObservables.clear();
				TreeMap<String, Integer> options = special.getSpecialOptions();
				for (Entry<String, Integer> entry : options.entrySet())
				{
					mRacialSelectablesObservables.add(entry.getKey() + " " + entry.getValue());
				}
			}
			catch (Exception exp)
			{
				exp.printStackTrace(System.err);
				showException(exp);
			}
		}
		// setup callback
		if (true == mRacialFirst)
		{
			mRacialFirst = false;
			mRacialSelectables.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
			{
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
				{
					//System.out.println("changed racial to: " + newValue);
					try
					{
						int index = mRacialSelectablesObservables.indexOf(newValue);
						if (-1 != index)
						{
							//System.out.println("index: " + index);
							Special special = BBFCharGen.mPC.getRace().getRacialOption();
							//System.out.println("special: " + special);
							TreeMap<String, Integer> map = special.getSpecialOptions();
							TreeSet<String> keys = new TreeSet<>(map.keySet());
							int count = 0;
							String strKey = null;
							int intVal = 0;
							for (String key : keys)
							{
								if (index == count)
								{
									strKey = key;
									intVal = map.get(key);
								}
								count = count + 1;
							}
							//System.out.println("strKey: " + strKey);
							//System.out.println("intVal: " + intVal);
							//System.out.println("special.getType(): " + special.getType());
							// TODO: move strings into model somewhere
							if (null != strKey)
							{
								if ("Ability".equals(special.getType()))
								{
									BBFCharGen.mPC.setRacialOptionsAbility(strKey, intVal);
								}
								else if ("RacialAbility".equals(special.getType()))
								{
									BBFCharGen.mPC.setRacialOptionsRA(newValue);
								}
								else if ("Skills".equals(special.getType()))
								{
									BBFCharGen.mPC.setRacialOptionsSkill(strKey, intVal);
								}
								pcUpdate();
								mAbilityUpdateInProgress = false;
							}
						}
					}
					catch (Exception exp)
					{
						exp.printStackTrace(System.err);
						showException(exp);
					}
				}
			});
		}
	}

	////////////////////////////////////
	// menus
	////////////////////////////////////
	@FXML private void showLicense(ActionEvent event)
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setResizable(true);
		alert.setTitle("CC BY-NC-SA 3.0 Licensing");
		alert.setHeaderText("This program and its contents are licensed under CC BY-NC-SA 3.0");
		alert.setContentText("This program and its contents are licensed under CC BY-NC-SA 3.0");
		TextArea textArea = new TextArea("Licensing\n\n"
				+ "This software is licensed under the Creative Commons Attribution-Noncommercial-ShareAlike 3.0 Unported License; (CC BY-NC-SA 3.0); Some Rights Reserved.\n\n"
				+ "BareBones Fantasy™ and Keranak Kingdoms™ are copyright 2012, and are trademarks of DwD Studios. \n\n"
				+ "These trademarks are used under the Creative Commons Attribution-Noncommercial-ShareAlike 3.0 Unported License; (CC BY-NC-SA 3.0); Some Rights Reserved.\n\n"
				+ "To view a copy of this license, visit: http://creativecommons.org/licenses/by-nc-sa/3.0\n\n"
				+ "All data is (CC BY-NC-SA 3.0) and used with permission.\n\n"
				+ "Basic game setup, races, and Decahedron Descriptors are from DwDStudios http://dwdstudios.com/ (They also make Covert Ops)\n\n"
				+ "Available 1000 Descriptors by Mark Hassman http://mithrilandmages.com (check out his cool NPC/character generators)\n\n");
		textArea.setEditable(false);
		textArea.setWrapText(true);
		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(textArea, 0, 1);
		alert.getDialogPane().setExpandableContent(expContent);
		alert.showAndWait();
	}

	@FXML private void about(ActionEvent event)
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.getDialogPane().setPrefWidth(400);
		alert.getDialogPane().setPrefHeight(350);
		alert.setResizable(true);
		alert.setTitle("About This Software");
		alert.setHeaderText("Software by Tod Casasent (muhgi@yahoo.com)");
		alert.setContentText("Version: BBFCharGen 2015-10-03-1730\n\n"
				+ "This program and its contents are licensed under CC BY-NC-SA 3.0\n\n"
				+ "Basic game setup, races, and Decahedron Descriptors are from DwDStudios http://dwdstudios.com/ (They also make Covert Ops)\n\n"
				+ "Available 1000 Descriptors by Mark Hassman http://mithrilandmages.com (check out his cool NPC/character generators)\n\n");
		alert.showAndWait();
	}

	/**
	 *
	 * @param theText
	 * @return
	 */
	public int[] getSpaceIndexes(String theText)
	{
		ArrayList<Integer> indexes = new ArrayList<>();
		for (int i = 0; i < theText.length(); i++)
		{
			if (" ".equals(theText.substring(i, i + 1)))
			{
				indexes.add(i);
			}
		}
		int[] result = new int[indexes.size()];
		for (int i = 0; i < indexes.size(); i++)
		{
			result[i] = indexes.get(i);
		}
		return result;
	}

	/**
	 *
	 * @param theText
	 * @return
	 * @throws IOException
	 */
	public ArrayList<String> getLines(String theText) throws IOException
	{
		ArrayList<String> result = new ArrayList<>();
		int[] possibleWrapPoints = getSpaceIndexes(theText);
		int start = 0;
		for (int wrapIndex = 0; wrapIndex < possibleWrapPoints.length; wrapIndex++)
		{
			String tempStr = theText.substring(start, possibleWrapPoints[wrapIndex]);
			float width = PDType1Font.HELVETICA_BOLD.getStringWidth(tempStr) / 1000 * 12;
			if (width > M_WIDTH)
			{
				result.add(theText.substring(start, possibleWrapPoints[wrapIndex - 1]));
				start = possibleWrapPoints[wrapIndex - 1];
			}
		}
		// Last piece of text
		result.add(theText.substring(start));
		return result;
	}

	private int writeStringToPdfwrapped(String theString, int theRow, boolean theLeftCol, PDPageContentStream theContentStream) throws IOException
	{
		ArrayList<String> lines = getLines(theString);
		int count = 0;
		for (String line : lines)
		{
			theContentStream.beginText();
			theContentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
			theContentStream.moveTextPositionByAmount((theLeftCol) ? M_X_LEFT : M_X_RIGHT, M_Y_TOP - ((theRow + count) * M_HEIGHT));
			theContentStream.drawString(line);
			theContentStream.endText();
			count = count + 1;
		}
		return count;
	}

	private void writeStringToPdf(String theString, int theRow, boolean theLeftCol, PDPageContentStream theContentStream) throws IOException
	{
		theContentStream.beginText();
		theContentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
		theContentStream.moveTextPositionByAmount((theLeftCol) ? M_X_LEFT : M_X_RIGHT, M_Y_TOP - (theRow * M_HEIGHT));
		theContentStream.drawString(theString);
		theContentStream.endText();
	}

	private void writeStringToPdf(String theString, int theRow, float theX, PDPageContentStream theContentStream) throws IOException
	{
		theContentStream.beginText();
		theContentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
		theContentStream.moveTextPositionByAmount(theX, M_Y_TOP - (theRow * M_HEIGHT));
		theContentStream.drawString(theString);
		theContentStream.endText();
	}

	private String treeSetToString(TreeSet<String> theTree)
	{
		String list = null;
		for (String str : theTree)
		{
			if (null == list)
			{
				list = str;
			}
			else
			{
				list = list + ", " + str;
			}
		}
		return list;
	}

	/**
	 *
	 */
	static protected float M_Y_TOP = 745;

	/**
	 *
	 */
	static protected float M_HEIGHT = 15;

	/**
	 *
	 */
	static protected float M_X_LEFT = 30;

	/**
	 *
	 */
	static protected float M_X_RIGHT = 350;

	/**
	 *
	 */
	static protected float M_WIDTH = 500;

	@FXML private void saveToPdf(ActionEvent event)
	{
		int row = 0;
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("PDF", "*.pdf"));
		fileChooser.setInitialFileName("bbf.pdf");
		File selectedFile = fileChooser.showSaveDialog(BBFCharGen.M_STAGE);
		if (selectedFile != null)
		{
			String pdfFile = selectedFile.getAbsolutePath();
			try
			{
				// Create a document and add a page to it
				PDDocument document = new PDDocument();
				PDPage page = new PDPage();
				M_WIDTH = page.getMediaBox().getWidth() - M_X_LEFT - M_X_LEFT;
				document.addPage(page);
				// Start a new content stream which will "hold" the to be created content
				PDPageContentStream contentStream = new PDPageContentStream(document, page);
				// first group
				writeStringToPdf("Name ________________________", 0, true, contentStream);
				writeStringToPdf("Hair/Eyes _____________________", 1, true, contentStream);
				writeStringToPdf("Gender _______________________", 2, true, contentStream);
				writeStringToPdf("Languages: " + treeSetToString(BBFCharGen.mPC.getKnownLanguages()), 3, true, contentStream);
				writeStringToPdf("INIT _______" + BBFCharGen.mPC.calcInit() + "______________", 0, false, contentStream);
				writeStringToPdf("DR _______________________", 1, false, contentStream);
				writeStringToPdf("MOV _______" + BBFCharGen.mPC.getRace().getMove() + "______________", 2, false, contentStream);
				writeStringToPdf("Race _" + BBFCharGen.mPC.getRace().getName() + "_", 3, false, contentStream);
				String ra = BBFCharGen.mPC.getRacialOptionsRA();
				if ((null != ra) && (false == "".equals(ra)))
				{
					writeStringToPdf("Racial Ability: " + ra, 4, false, contentStream);
				}
				// second group
				int group2row = 6;
				writeStringToPdf(BBFCharGen.mPC.getAbilityTotal(Abilities.STR) + " Strength (STR) ", group2row, true, contentStream);
				writeStringToPdf(BBFCharGen.mPC.getAbilityTotal(Abilities.DEX) + " Dexterity (DEX) ", group2row + 1, true, contentStream);
				writeStringToPdf(BBFCharGen.mPC.getAbilityTotal(Abilities.LOG) + " Logic (LOG) ", group2row + 2, true, contentStream);
				writeStringToPdf(BBFCharGen.mPC.getAbilityTotal(Abilities.WIL) + " Willpower (WIL) ", group2row + 3, true, contentStream);
				writeStringToPdf(BBFCharGen.mPC.getBodypointsTotal() + " Body Points (BP) ", group2row + 4, true, contentStream);
				writeStringToPdf(BBFCharGen.mPC.getKindOrCruelDegree() + " " + BBFCharGen.mPC.getKindOrCruelSelect(), group2row, false, contentStream);
				writeStringToPdf(BBFCharGen.mPC.getFocusedOrUnfocusedDegree() + " " + BBFCharGen.mPC.getFocusedOrUnfocusedSelect(), group2row + 1, false, contentStream);
				writeStringToPdf(BBFCharGen.mPC.getSelflessOrSelfishDegree() + " " + BBFCharGen.mPC.getSelflessOrSelfishSelect(), group2row + 2, false, contentStream);
				writeStringToPdf(BBFCharGen.mPC.getHonorableOrDeceitfulDegree() + " " + BBFCharGen.mPC.getHonorableOrDeceitfulSelect(), group2row + 3, false, contentStream);
				writeStringToPdf(BBFCharGen.mPC.getBraveOrCowardlyDegree() + " " + BBFCharGen.mPC.getBraveOrCowardlySelect(), group2row + 4, false, contentStream);
				// third group
				int group3row = 12;
				String[] desc = BBFCharGen.mPC.getDescriptors();
				for (int i = 0; i < desc.length; i++)
				{
					writeStringToPdf(desc[i], group3row + i, true, contentStream);
				}
				// fourth group
				int group4row = group3row + 1 + desc.length;
				writeStringToPdf("Skill", group4row, 30, contentStream);
				writeStringToPdf("Aspect", group4row, 145, contentStream);
				writeStringToPdf("Score", group4row, 300, contentStream);
				writeStringToPdf("Level", group4row, 340, contentStream);
				writeStringToPdf("Mod", group4row, 380, contentStream);
				writeStringToPdf("P(20)", group4row, 415, contentStream);
				writeStringToPdf("S(10)", group4row, 450, contentStream);
				writeStringToPdf("Description", group4row, 500, contentStream);
				int count = 1;
				for (int i = 0; i < mSortedAspectList.size(); i++)
				{
					if (mSortedAspectList.get(i).mScoreProperty().getValue() > 0)
					{
						writeStringToPdf(mSortedAspectList.get(i).mSkillNameProperty().getValue(), group4row + count, 30, contentStream);
						writeStringToPdf(mSortedAspectList.get(i).mAspectNameProperty().getValue(), group4row + count, 145, contentStream);
						writeStringToPdf(mSortedAspectList.get(i).mScoreProperty().getValue().toString(), group4row + count, 300, contentStream);
						writeStringToPdf(mSortedAspectList.get(i).mLevelProperty().getValue().toString(), group4row + count, 340, contentStream);
						writeStringToPdf(mSortedAspectList.get(i).mModifierProperty().getValue().toString(), group4row + count, 380, contentStream);
						writeStringToPdf(mSortedAspectList.get(i).mPrimaryProperty().getValue().toString(), group4row + count, 415, contentStream);
						writeStringToPdf(mSortedAspectList.get(i).mSecondaryProperty().getValue().toString(), group4row + count, 450, contentStream);
						writeStringToPdf(mSortedAspectList.get(i).mDescriptionProperty().getValue(), group4row + count, 500, contentStream);
						count = count + 1;
					}
				}
				// fifth group
				int group5row = group4row + 1 + count;
				TreeSet<String> selected = BBFCharGen.mPC.getSelected("Miracles");
				if ((null != selected) && (selected.size() > 0))
				{
					writeStringToPdf("Miracles: " + treeSetToString(selected), group5row, true, contentStream);
					group5row = group5row + 1;
				}
				selected = BBFCharGen.mPC.getSelected("High Wizardry");
				if ((null != selected) && (selected.size() > 0))
				{
					writeStringToPdf("High Wizardry: " + treeSetToString(selected), group5row, true, contentStream);
					group5row = group5row + 1;
				}
				selected = BBFCharGen.mPC.getSelected("High Scholar");
				if ((null != selected) && (selected.size() > 0))
				{
					writeStringToPdf("High Scholar: " + treeSetToString(selected), group5row, true, contentStream);
					group5row = group5row + 1;
				}
				// Make sure that the content stream is closed:
				contentStream.close();
				////////////////////////////
				page = new PDPage();
				document.addPage(page);
				contentStream = new PDPageContentStream(document, page);
				int newPageRow = 0;
				newPageRow = newPageRow + writeStringToPdfwrapped("EquipCostMod=" + BBFCharGen.mPC.getRace().getEquipCostMod() + " WeaponDamageMulti=" + BBFCharGen.mPC.getRace().getWeaponDamageMulti(), newPageRow, true, contentStream);
				for (Entry<AbilityChecks, Integer> entry : BBFCharGen.mPC.getRace().getAbilityChecks().entrySet())
				{
					newPageRow = newPageRow + writeStringToPdfwrapped("Ability Check Mod: " + entry.getKey().mAbility.mLongName + " " + entry.getKey().mName + " " + entry.getValue(), newPageRow, true, contentStream);
				}
				for (Entry<String, String> entry : BBFCharGen.mPC.getRace().getRacialChars().entrySet())
				{
					newPageRow = newPageRow + writeStringToPdfwrapped("Racial Char: " + entry.getKey() + " " + entry.getValue(), newPageRow, true, contentStream);
				}
				for (Special entry : BBFCharGen.mPC.getRace().getSpecials())
				{
					newPageRow = newPageRow + writeStringToPdfwrapped("Special: " + entry.describeSpecial(), newPageRow, true, contentStream);
				}
				// Make sure that the content stream is closed:
				contentStream.close();
				////////////////////////////
				// Save the results and ensure that the document is properly closed:
				document.save(pdfFile);
				document.close();
			}
			catch (Exception exp)
			{
				exp.printStackTrace(System.err);
				showException(exp);
			}
		}
	}

	private void showException(Exception theExp)
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setResizable(true);
		alert.setTitle("Exception: " + theExp.getMessage());
		alert.setHeaderText(theExp.getMessage());
		alert.setContentText(theExp.getMessage());
		alert.showAndWait();
	}
	////////////////////////////////////
	// 
	////////////////////////////////////
}
